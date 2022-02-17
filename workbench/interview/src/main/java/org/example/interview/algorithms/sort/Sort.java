package org.example.interview.algorithms.sort;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.example.core.util.ConsoleUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author qbq
 * @date 2022/2/7 7:30 PM
 */
public class Sort {

    private static final boolean PRINT_LST_DETAIL = false;
    private static final int RAND_LEN_MIN = 10000;
    private static final int RAND_LEN_MAX = 20000;
    private static final int RAND_VAL_MIN = -1000;
    private static final int RAND_VAL_MAX = 10000;

    private static int LEN = 0;
    private static final Map<String, String> STATS = Maps.newLinkedHashMap();

    public static void main(String[] args) {
        init();
        SortAlgorithms<Integer> sortAlgorithms = new SortAlgorithms<>(Integer.class);
        invoke("冒泡排序", sortAlgorithms::bubble);
        invoke("选择排序", sortAlgorithms::select);
        invoke("插入排序", sortAlgorithms::insert);
        invoke("希尔排序", sortAlgorithms::shell);
        invoke("归并排序（递归实现）", sortAlgorithms::merge);
        invoke("归并排序（非递归实现）", sortAlgorithms::merge2);
        invoke("快速排序（递归实现）", sortAlgorithms::quick);
        stats();
    }

    private static void init() {
        LEN = RandomUtil.randomInt(RAND_LEN_MIN, RAND_LEN_MAX);
    }

    private static void stats() {
        String stats = STATS.entrySet().stream()
                .map(entry -> entry.getKey() + "：" + entry.getValue())
                .collect(Collectors.joining("\n"));
        ConsoleUtils.sout("----------------------------------");
        ConsoleUtils.sout("数据数量：" + LEN);
        ConsoleUtils.sout("性能汇总：\n" + stats);
    }

    private static void invoke(String tip, Function<List<Integer>, List<Integer>> fun) {
        List<Integer> ori = Arrays.stream(randomInts())
                .boxed().collect(Collectors.toList());
        prepare(ori, tip);
        StopWatch sw = StopWatch.createStarted();
        List<Integer> res = fun.apply(ori);
        sw.stop();
        verify(ori, res, tip, sw.getTime());
    }

    private static int[] randomInts() {
        int[] arr = new int[LEN];
        for (int idx = 0; idx < LEN; idx++) {
            // 允许负数、重复
            arr[idx] = RandomUtil.randomInt(RAND_VAL_MIN, RAND_VAL_MAX);
        }
        return arr;
    }

    private static void prepare(List<Integer> lst, String tip) {
        Collections.shuffle(lst);
        ConsoleUtils.sout("----------------------------------");
        ConsoleUtils.sout(tip);
        if (PRINT_LST_DETAIL) {
            ConsoleUtils.sout(lst);
        }
        ConsoleUtils.sout(lst.size());
    }

    private static void verify(List<Integer> lst, List<Integer> res, String tip, long cost) {
        if (PRINT_LST_DETAIL) {
            ConsoleUtils.sout(res);
        }
        ConsoleUtils.sout(res.size());

        ConsoleUtils.sout(cost);

        //ConsoleUtils.sout(ArrayUtils.isSorted(res.toArray(new Integer[0])));
        List<Integer> ori = Lists.newArrayList(lst);
        Collections.sort(ori);
        String src = StringUtils.joinWith(StringUtils.SPACE, ori);
        String dst = StringUtils.joinWith(StringUtils.SPACE, res);
        boolean equals = Objects.equals(src, dst);
        ConsoleUtils.sout(equals);
        if (!equals) {
            throw new RuntimeException("排序异常：" + tip);
        }

        STATS.put(tip, String.valueOf(cost));
    }

    /**
     * 十种常见排序算法可以分为两大类：
     * <p>
     * 比较类排序：通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)，因此也称为非线性时间比较类排序。
     * 非比较类排序：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。
     * <p>
     * 参考博客 @link(https://www.cnblogs.com/onepixel/p/7674659.html)
     */
    @SuppressWarnings({"unchecked", "rawtype"})
    static class SortAlgorithms<T extends Comparable<? super T>> {

        /*
         * 比较类排序：
         * 交换（冒泡、快速），插入（简单插入排序、希尔排序），选择（简单选择排序、堆排序），归并（二路归并排序、多路归并排序）
         * 非比较类排序：
         * 计数排序、基数排序、桶排序
         *
         * 相关概念：
         * 稳定：如果a原本在b前面，而a=b，排序之后a仍然在b的前面。
         * 不稳定：如果a原本在b的前面，而a=b，排序之后 a 可能会出现在 b 的后面。
         * 时间复杂度：对排序数据的总的操作次数。反映当n变化时，操作次数呈现什么规律。
         * 空间复杂度：是指算法在计算机内执行时所需存储空间的度量，它也是数据规模n的函数。
         *
         * 各排序算法复杂度：
         * 冒泡排序：稳定，时间复杂度(平均On2最好On最差On2)，空间复杂度(O1)
         * 选择排序：不稳定，时间复杂度(平均On2最好On2最差On2)，空间复杂度(O1)
         * 插入排序：稳定，时间复杂度(平均On2最好On最差On2)，空间复杂度(O1)
         * 希尔排序：不稳定，时间复杂度(平均On1.3最好On最差On2)，空间复杂度(O1)
         * 归并排序：不稳定，时间复杂度(平均Onlog2n最好Onlog2n最差Onlog2n)，空间复杂度(On)
         *
         *
         */

        private final Class<T> componentType;

        public SortAlgorithms(Class<T> componentType) {
            this.componentType = componentType;
        }

        /**
         * 快速排序的基本思想：
         * 通过一趟排序将待排记录分隔成独立的两部分，
         * 其中一部分记录的关键字均比另一部分的关键字小，
         * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
         * <p>
         * 算法描述：
         * 快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。
         * <p>
         * 具体算法描述如下：
         * 从数列中挑出一个元素，称为 “基准”（pivot）；
         * 重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
         * 在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
         * 递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
         */
        /*
         * 快速排序 比较(read)次数较多，交换(write)次数较多，会有额外的空间消耗
         * 时间复杂度 平均O(nlog2n)，最好O(nlog2n)，最差O(n2)
         * 空间复杂度 O(nlog2n)
         * 比较类排序-交换、不稳定排序
         */
        public List<T> quick(Iterable<T> itr) {
            T[] arr = toArr(itr);
            if (arr == null) {
                return Lists.newArrayList();
            }
            int len = arr.length;
            int lft = 0;
            int rgt = len - 1;
            quick0(arr, lft, rgt);
            return Lists.newArrayList(arr);
        }

        private void quick0(T[] arr, int lft, int rgt) {
            if (lft >= rgt) {
                return;
            }

            // 分区并确定标杆，比标杆小的在左侧，比标杆大的在右侧
            int flg = partition(arr, lft, rgt);
            // 左侧
            quick0(arr, lft, flg - 1);
            // 右侧
            quick0(arr, flg + 1, rgt);
        }

        private int partition(T[] arr, int lft, int rgt) {
            // 标杆
            final int key = lft;
            final T val = arr[key];
            // 双指针
            int idx = lft;
            int jdx = rgt;
            while (true) {
                // 先移动右指针，直到找到比标杆小的
                while (idx < jdx && compare(val, arr[jdx]) <= 0) {
                    jdx--;
                }
                // 后移动左指针，直到找到比标杆大的
                while (idx < jdx && compare(val, arr[idx]) >= 0) {
                    idx++;
                }
                if (idx >= jdx) {
                    break;
                }
                swap(arr, idx, jdx);
            }
            //此时idx==jdx
            arr[lft] = arr[idx];
            arr[idx] = val;
            //此时标杆的下标是idx
            return idx;
        }

        /**
         * 非递归实现归并排序
         */
        public List<T> merge2(Iterable<T> itr) {
            T[] arr = toArr(itr);
            if (arr == null) {
                return Lists.newArrayList();
            }
            int len = arr.length;
            // 额外的空间，O(n)
            T[] tmp = (T[]) Array.newInstance(componentType, len);
            for (int step = 1; step < len; step <<= 1) {
                int lft = 0;
                int mid = lft + step - 1;
                int rgt = mid + step;
                while (rgt < len) {
                    _merge(arr, tmp, lft, mid, rgt);
                    lft = rgt + 1;
                    mid = lft + step - 1;
                    rgt = mid + step;
                }
                if (mid < len) {
                    // 剩下的
                    _merge(arr, tmp, lft, mid, len - 1);
                }
            }
            return Lists.newArrayList(arr);
        }

        /**
         * 归并排序是建立在归并操作上的一种有效的排序算法。
         * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
         * 将已有序的子序列合并，得到完全有序的序列；
         * 即先使每个子序列有序，再使子序列段间有序。
         * 若将两个有序表合并成一个有序表，称为2-路归并。
         * <p>
         * 算法描述：
         * 把长度为n的输入序列分成两个长度为n/2的子序列；
         * 对这两个子序列分别采用归并排序；
         * 将两个排序好的子序列合并成一个最终的排序序列。
         * <p>
         * 归并排序是一种稳定的排序方法。
         * 和选择排序一样，归并排序的性能不受输入数据的影响，但表现比选择排序好的多，
         * 因为始终都是O(nlogn）的时间复杂度。代价是需要额外的内存空间。
         */
        /*
         * 归并排序 比较(read)次数较多，移动(write)次数较多，会有额外的空间消耗
         * 时间复杂度 平均O(nlog2n)，最好O(nlog2n)，最差O(nlog2n)
         * 空间复杂度 O(n)
         * 比较类排序-归并、稳定排序
         */
        public List<T> merge(Iterable<T> itr) {
            T[] arr = toArr(itr);
            if (arr == null) {
                return Lists.newArrayList();
            }
            int len = arr.length;
            int lft = 0;
            int rgt = len - 1;
            // 额外的空间，O(n)
            T[] tmp = (T[]) Array.newInstance(componentType, len);
            // 递归，分治，合并
            merge0(arr, tmp, lft, rgt);
            return Lists.newArrayList(arr);
        }

        private void _merge(T[] arr, T[] tmp, int lft, int mid, int rgt) {
            if (lft >= rgt) {
                return;
            }

            // 临时数组，用于合并两个有序子段
            //T[] tmp = (T[]) Array.newInstance(componentType, rgt - lft + 1);

            // 双指针合并
            int idx = lft;
            int jdx = mid + 1;
            int cur = lft;
            while (idx <= mid || jdx <= rgt) {
                T a = null;
                if (idx <= mid) {
                    a = arr[idx];
                }
                T b = null;
                if (jdx <= rgt) {
                    b = arr[jdx];
                }
                /*if (a == null) {
                    tmp[cur++] = b;
                    jdx++;
                } else if (b == null) {
                    tmp[cur++] = a;
                    idx++;
                } else if (a.compareTo(b) > 0) {
                    tmp[cur++] = b;
                    jdx++;
                } else {
                    tmp[cur++] = a;
                    idx++;
                }*/
                if (a == null || (b != null && compare(a, b) > 0)) {
                    tmp[cur++] = b;
                    jdx++;
                } else {
                    tmp[cur++] = a;
                    idx++;
                }
            }

            // 将临时数组里的顺序重新赋值到源数组
            for (int mdx = lft; mdx <= rgt; mdx++) {
                arr[mdx] = tmp[mdx];
            }
        }

        private void merge0(T[] arr, T[] tmp, int lft, int rgt) {
            if (lft >= rgt) {
                // 只剩下一个元素，最小单元，直接返回
                return;
            }

            // 中间
            int mid = (lft + rgt) / 2;
            // 左侧
            merge0(arr, tmp, lft, mid);
            // 右侧
            merge0(arr, tmp, mid + 1, rgt);
            // 合并左右侧
            _merge(arr, tmp, lft, mid, rgt);
        }


        /**
         * 1959年Shell发明，第一个突破O(n2)的排序算法，是简单插入排序的改进版。
         * 它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
         * <p>
         * 算法描述：
         * 先将整个待排序的记录序列分割成为若干子序列分别进行直接插入排序。
         * 具体算法描述：
         * 选择一个增量序列t1，t2，…，tk，其中ti>tj，tk=1；
         * 按增量序列个数k，对序列进行k 趟排序；
         * 每趟排序，根据对应的增量ti，将待排序列分割成若干长度为m 的子序列，分别对各子表进行直接插入排序。
         * 仅增量因子为1 时，整个序列作为一个表来处理，表长度即为整个序列的长度。
         * <p>
         * 希尔排序的核心在于间隔序列的设定。既可以提前设定好间隔序列，也可以动态的定义间隔序列。
         * 动态定义间隔序列的算法是《算法（第4版）》的合著者Robert Sedgewick提出的。
         */
        /*
         * 希尔排序 比较(read)次数较多，移动(write)次数较多
         * 时间复杂度 平均O(n1.3)，最好O(n)，最差O(n2)
         * 空间复杂度 O(1)
         * 比较类排序-插入、不稳定排序
         */
        public List<T> shell(Iterable<T> itr) {
            T[] arr = toArr(itr);
            if (arr == null) {
                return Lists.newArrayList();
            }
            int len = arr.length;
            for (int gap = len / 2; gap > 0; gap /= 2) {
                // 子序列可以使用冒泡排序，选择排序，插入排序。这里采用插入排序
                for (int idx = gap; idx < len; idx += gap) {
                    int pdx = idx;
                    for (int jdx = 0; jdx < idx; jdx += gap) {
                        if (compare(arr[idx], arr[jdx]) < 0) {
                            pdx = jdx;
                            break;
                        }
                    }
                    if (pdx != idx) {
                        // 挪位置
                        T tmp = arr[idx];
                        for (int mdx = idx; mdx > pdx; mdx -= gap) {
                            arr[mdx] = arr[mdx - gap];
                        }
                        arr[pdx] = tmp;
                    }
                }
            }
            return Lists.newArrayList(arr);
        }

        /**
         * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。
         * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
         * <p>
         * 算法描述：
         * 一般来说，插入排序都采用in-place在数组上实现。具体算法描述如下：
         * <p>
         * 1、从第一个元素开始，该元素可以认为已经被排序；
         * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描；
         * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置；
         * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
         * 5、将新元素插入到该位置后；
         * 重复步骤2~5。
         * <p>
         * 当数据量不多时，插入排序优于快速排序。当数据量比较大时，快速排序优先。
         */
        /*
         * 插入排序 比较(read)次数较多，移动(write)次数较多
         * 时间复杂度 平均O(n2)，最好O(n)，最差O(n2)
         * 空间复杂度 O(1)
         * 比较类排序-插入、稳定排序
         */
        public List<T> insert(Iterable<T> itr) {
            T[] arr = toArr(itr);
            if (arr == null) {
                return Lists.newArrayList();
            }
            int len = arr.length;
            for (int idx = 1; idx < len; idx++) {
                int pdx = idx;
                for (int jdx = 0; jdx < idx; jdx++) {
                    if (compare(arr[idx], arr[jdx]) < 0) {
                        // 找到插入位置
                        pdx = jdx;
                        break;
                    }
                }
                if (pdx != idx) {
                    // 移动从pdx到idx-1段往右，并将原idx放在pdx处
                    T tmp = arr[idx];
                    for (int mdx = idx; mdx > pdx; mdx--) {
                        arr[mdx] = arr[mdx - 1];
                    }
                    arr[pdx] = tmp;
                }
            }
            return Lists.newArrayList(arr);
        }

        /**
         * 选择排序(Selection-sort)是一种简单直观的排序算法。
         * 它的工作原理：
         * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
         * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
         * 以此类推，直到所有元素均排序完毕。
         * <p>
         * 算法描述：
         * n个记录的直接选择排序可经过n-1趟直接选择排序得到有序结果。
         * <p>
         * 具体算法描述如下：
         * 1、初始状态：无序区为R[1..n]，有序区为空；
         * 2、第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。
         * 该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，
         * 使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
         * 3、n-1趟结束，数组有序化了。
         * <p>
         * 表现最稳定的排序算法之一，因为无论什么数据进去都是O(n2)的时间复杂度，所以用到它的时候，数据规模越小越好。
         * 唯一的好处可能就是不占用额外的内存空间了吧。理论上讲，选择排序可能也是平时排序一般人想到的最多的排序方法了吧。
         */
        /*
         * 选择排序 比较(read)次数较多，移动(write)次数较少
         * 时间复杂度 平均O(n2)，最好O(n2)，最差O(n2)
         * 空间复杂度 O(1)
         * 比较类排序-选择、不稳定排序
         */
        public List<T> select(Iterable<T> itr) {
            T[] arr = toArr(itr);
            if (arr == null) {
                return Lists.newArrayList();
            }
            int len = arr.length;
            for (int idx = 0; idx < len - 1; idx++) {
                int mdx = idx;
                for (int jdx = idx + 1; jdx < len; jdx++) {
                    if (compare(arr[mdx], arr[jdx]) > 0) {
                        mdx = jdx;
                    }
                }
                if (mdx != idx) {
                    swap((T[]) arr, idx, mdx);
                }
            }
            return Lists.newArrayList(arr);
        }

        /**
         * 冒泡排序是一种简单的排序算法。
         * 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
         * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
         * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
         * <p>
         * 算法描述：
         * 1、比较相邻的元素。如果第一个比第二个大，就交换它们两个；
         * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
         * 3、针对所有的元素重复以上的步骤，除了最后一个；
         * 重复步骤1~3，直到排序完成。
         */
        /*
         * 冒泡 逐个两两比较，可能需要频繁移动
         * 时间复杂度 平均O(n2)，最好O(n)，最差O(n2)
         * 空间复杂度 O(1)
         * 比较类排序-交换、稳定排序
         * 改进：鸡尾酒排序（即内部两次循环，先将大的往右边移动，然后再将小的往左侧移动）
         */
        public List<T> bubble(Iterable<T> itr) {
            T[] arr = toArr(itr);
            if (arr == null) {
                return Lists.newArrayList();
            }
            int len = arr.length;
            // 外部循环len-1次
            for (int idx = 0; idx < len; idx++) {
                boolean sorted = true;
                // 内部每次从下标0开始，往右操作len-1-idx-1次，每次将相对最大的往右移动
                for (int jdx = 0; jdx < len - idx - 1; jdx++) {
                    if (compare(arr[jdx], arr[jdx + 1]) > 0) {
                        swap(arr, jdx + 1, jdx);
                        sorted = false;
                    }
                }
                if (sorted) {
                    // 恰好已经排好序，直接跳出
                    break;
                }
            }
            return Lists.newArrayList(arr);
        }

        private int compare(T a, T b) {
            return a.compareTo(b);
        }

        private void swap(T[] arr, int idx, int jdx) {
            // 两两交换
            T tmp = arr[idx];
            arr[idx] = arr[jdx];
            arr[jdx] = tmp;
        }

        private T[] toArr(Iterable<T> itr) {
            List<T> lst = IteratorUtils.toList(itr.iterator());
            int size = lst.size();
            if (size < 1) {
                return null;
            }
            T[] arr = (T[]) Array.newInstance(componentType, size);
            for (int idx = 0; idx < size; idx++) {
                arr[idx] = lst.get(idx);
            }
            return (T[]) arr;
        }

    }

}
