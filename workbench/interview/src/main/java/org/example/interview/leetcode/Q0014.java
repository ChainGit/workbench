package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeEasy;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/29 2:29 PM
 */
public class Q0014 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().longestCommonPrefix(new String[]{"abc", "abcd", "ab"}));
    }

    /**
     * 14. 最长公共前缀
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * <p>
     * 如果不存在公共前缀，返回空字符串 ""。
     */
    @LeetCodeEasy({Tag.string})
    /**
     * 123 / 123 个通过测试用例
     * 状态：通过
     * 执行用时: 1 ms
     * 内存消耗: 39.7 MB
     */
    static class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs.length == 0) {
                return "";
            }
            if (strs.length == 1) {
                return strs[0];
            }
            int max = 0;
            int len = strs.length;
            int shortest = strs[0].length();
            char[][] chars = new char[len][];
            for (int idx = 0; idx < len; idx++) {
                String str = strs[idx];
                chars[idx] = str.toCharArray();
                shortest = Math.min(shortest, str.length());
            }
            for (int idx = 0; idx < shortest; idx++) {
                boolean same = true;
                for (int jdx = 1; jdx < len; jdx++) {
                    if (chars[jdx - 1][idx] != chars[jdx][idx]) {
                        same = false;
                        break;
                    }
                }
                if (!same) {
                    break;
                }
                max++;
            }
            if (max == 0) {
                return "";
            }
            return new String(chars[0], 0, max);
        }
    }

}
