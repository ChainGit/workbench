package org.example.interview.algorithms.sort;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.core.util.ConsoleUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author qbq
 * @date 2022/2/7 7:30 PM
 */
public class Sort {

    public static void main(String[] args) {
        SortAlgorithms<Integer> sortAlgorithms = new SortAlgorithms<>();
        invoke("冒泡排序", sortAlgorithms::bubble);
        invoke("选择排序", sortAlgorithms::select);
        invoke("插入排序", sortAlgorithms::insert);
    }

    private static void invoke(String tip, Function<List<Integer>, List<Integer>> fun) {
        List<Integer> ori = Arrays.stream(RandomUtil.randomInts(
                RandomUtil.randomInt(1000, 5000))).boxed().collect(Collectors.toList());
        prepare(ori, tip);
        List<Integer> res = fun.apply(ori);
        verify(ori, res, tip);
    }

    private static void prepare(List<Integer> lst, String tip) {
        Collections.shuffle(lst);
        ConsoleUtils.sout("----------------------------------");
        ConsoleUtils.sout(tip);
        ConsoleUtils.sout(lst);
        ConsoleUtils.sout(lst.size());
    }

    private static void verify(List<Integer> lst, List<Integer> res, String tip) {
        ConsoleUtils.sout(res);
        ConsoleUtils.sout(res.size());
        //ConsoleUtils.sout(ArrayUtils.isSorted(res.toArray(new Integer[0])));

        List<Integer> ori = Lists.newArrayList(lst);
        Collections.sort(ori);
        String src = StringUtils.joinWith(StringUtils.SPACE, ori);
        String dst = StringUtils.joinWith(StringUtils.SPACE, res);
        boolean equals = Objects.equals(src, dst);
        ConsoleUtils.sout(equals);
        if (!equals) {
            throw new RuntimeException("排序异常:" + tip);
        }
    }

    /**
     * 十种常见排序算法可以分为两大类：
     * <p>
     * 比较类排序：通过比较来决定元素间的相对次序，由于其时间复杂度不能突破O(nlogn)，因此也称为非线性时间比较类排序。
     * 非比较类排序：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。
     * <p>
     * 参考博客 @link(https://www.cnblogs.com/onepixel/p/7674659.html)
     */
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
         * 
         *
         *
         */

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
         */
        /*
         * 插入排序 比较(read)次数较多，移动(write)次数较多
         * 时间复杂度 平均O(n2)，最好O(n)，最差O(n2)
         * 空间复杂度 O(1)
         * 比较类排序、插入排序、稳定排序
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
                    if (arr[idx].compareTo(arr[jdx]) < 0) {
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
         */
        /*
         * 选择排序 比较(read)次数较多，移动(write)次数较少
         * 时间复杂度 平均O(n2)，最好O(n2)，最差O(n2)
         * 空间复杂度 O(1)
         * 比较类排序、选择排序、不稳定排序
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
                    if (arr[mdx].compareTo(arr[jdx]) > 0) {
                        mdx = jdx;
                    }
                }
                if (mdx != idx) {
                    // 两两交换
                    T tmp = arr[idx];
                    arr[idx] = arr[mdx];
                    arr[mdx] = tmp;
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
         * 比较类排序、交换排序、稳定排序
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
                    if (arr[jdx].compareTo(arr[jdx + 1]) > 0) {
                        T t = arr[jdx + 1];
                        arr[jdx + 1] = arr[jdx];
                        arr[jdx] = t;
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

        private T[] toArr(Iterable<T> itr) {
            List<T> lst = IteratorUtils.toList(itr.iterator());
            int size = lst.size();
            if (size < 1) {
                return null;
            }
            T[] arr = (T[]) Array.newInstance(lst.get(0).getClass(), size);
            for (int idx = 0; idx < size; idx++) {
                arr[idx] = lst.get(idx);
            }
            return (T[]) arr;
        }

    }

}
