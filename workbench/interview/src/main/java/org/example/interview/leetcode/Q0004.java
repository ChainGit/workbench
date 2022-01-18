package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeHard;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/14 2:57 PM
 */
public class Q0004 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        ConsoleUtils.sout(new Solution().findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }

    /**
     * 4. 寻找两个正序数组的中位数
     * <p>
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     * <p>
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * <p>
     * <p>
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @LeetCodeHard({Tag.array, Tag.binary_search, Tag.divide_conquer})
    /**
     * 2094 / 2094 个通过测试用例
     * 状态：通过
     * 执行用时: 2 ms
     * 内存消耗: 39.8 MB
     */
    static class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int len1 = nums1.length;
            int len2 = nums2.length;
            int lenx = len1 + len2;
            // 中位数1下标
            int idx1 = lenx % 2 == 0 ? lenx / 2 - 1 : lenx / 2;
            // 中位数2下标
            int idx2 = lenx / 2;
            // 中位数1数值
            int val1 = 0;
            // 中位数2数值
            int val2 = 0;
            // 双指针
            int cur1 = 0;
            int cur2 = 0;
            while (cur1 + cur2 <= idx2) {
                boolean use1 = cur1 < len1;
                int t1 = 0;
                if (use1) {
                    t1 = nums1[cur1];
                }
                boolean use2 = cur2 < len2;
                int t2 = 0;
                if (use2) {
                    t2 = nums2[cur2];
                }
                int min = 0;
                if (use1 && !use2) {
                    min = t1;
                    cur1++;
                }
                if (!use1 && use2) {
                    min = t2;
                    cur2++;
                }
                if (use1 && use2) {
                    if (t1 < t2) {
                        cur1++;
                        min = t1;
                    } else {
                        cur2++;
                        min = t2;
                    }
                }
                if (lenx % 2 == 0) {
                    val1 = val2;
                    val2 = min;
                } else {
                    val1 = val2 = min;
                }
            }
            return (val1 + val2) / 2d;
        }
    }
}
