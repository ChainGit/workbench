package org.example.interview.leetcode;

import org.example.interview.annotations.LeetCodeEasy;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/21 2:49 PM
 */
public class Q0009 {

    public static void main(String[] args) {

    }

    /**
     * 9. 回文数
     * <p>
     * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
     * <p>
     * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是。
     */
    @LeetCodeEasy({Tag.math})
    /**
     * 11510 / 11510 个通过测试用例
     * 状态：通过
     * 执行用时: 4 ms
     * 内存消耗: 37.8 MB
     */
    static class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }
            int t = x;
            int r = 0;
            while (t != 0) {
                int q = t % 10;
                r = r * 10 + q;
                t /= 10;
            }
            return r == x;
        }
    }

}
