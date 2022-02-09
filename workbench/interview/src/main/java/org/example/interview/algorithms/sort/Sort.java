package org.example.interview.algorithms.sort;

import cn.hutool.core.util.RandomUtil;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.example.core.util.ConsoleUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qbq
 * @date 2022/2/7 7:30 PM
 */
public class Sort {

    public static void main(String[] args) {
        List<Integer> lst = Arrays.stream(RandomUtil.randomInts(1000))
                .boxed().collect(Collectors.toList());
        ConsoleUtils.sout(lst);

        List<Integer> res = bubble(lst);

        ConsoleUtils.sout(res);
        ConsoleUtils.sout(ArrayUtils.isSorted(res.toArray(new Integer[0])));
    }

    /**
     * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。
     * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
     * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
     * <p>
     * 算法描述：
     * 1、比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2、对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3、针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤1~3，直到排序完成。
     */
    public static <T extends Comparable<? super T>> List<T> bubble(Iterable<T> itr) {
        T[] arr = toArr(itr);
        if (arr == null) {
            return null;
        }
        int len = arr.length;
        // 外部循环len-1次
        for (int idx = 0; idx < len; idx++) {
            // 内部每次从下标0开始，往右操作len-1-idx-1次，每次将相对最大的往右移动
            for (int jdx = 0; jdx < len - idx - 1; jdx++) {
                if (arr[jdx].compareTo(arr[jdx + 1]) > 0) {
                    T t = arr[jdx + 1];
                    arr[jdx + 1] = arr[jdx];
                    arr[jdx] = t;
                }
            }
        }
        return Lists.newArrayList(arr);
    }

    private static <T> T[] toArr(Iterable<T> itr) {
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
