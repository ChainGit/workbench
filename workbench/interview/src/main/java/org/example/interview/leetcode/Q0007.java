package org.example.interview.leetcode;

import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/19 9:56 AM
 */
public class Q0007 {

    public static void main(String[] args) {

    }

    /**
     * 7. 整数反转
     * <p>
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * <p>
     * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
     * <p>
     * 假设环境不允许存储 64 位整数（有符号或无符号）。
     */
    @LeetCodeMiddle({Tag.math})
    /**
     * 1032 / 1032 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 35.3 MB
     */
    static class Solution {
        public int reverse(int x) {
            int t = x;
            int r = 0;
            while (t != 0) {
                if (r < Integer.MIN_VALUE / 10 || r > Integer.MAX_VALUE / 10) {
                    // 超出范围
                    return 0;
                }
                r = r * 10 + t % 10;
                t /= 10;
            }
            return r;
        }
    }

}
