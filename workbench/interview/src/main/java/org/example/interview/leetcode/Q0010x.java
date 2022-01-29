package org.example.interview.leetcode;

import cn.hutool.core.comparator.CompareUtil;
import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeEasy;
import org.example.interview.annotations.LeetCodeHard;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/21 3:07 PM
 */
public class Q0010x {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().isMatch("abcd", "a*"));
        ConsoleUtils.sout(new Solution().isMatch("abcdeffffff", "abcdef*"));
    }

    /**
     * 10. 正则表达式匹配
     * <p>
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * <p>
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     */
    @LeetCodeHard({Tag.string, Tag.recursion, Tag.dynamic_programing})
    static class Solution {
        public boolean isMatch(String s, String p) {
            int sen = s.length();
            int pen = p.length();

            char[] shs = s.toCharArray();
            char[] phs = p.toCharArray();

            // 动态规划：定义二维tab数组，其中tab[i][j]表示s的前i个字符和p的前j个字符是否匹配
            // s包含三种字符：普通字母(a-z)、'*'或者是'.'
            boolean[][] tab = new boolean[sen][pen];

            for (int sdx = 0; sdx < sen; sdx++) {
                for (int pdx = 0; pdx < pen; pdx++) {
                    boolean ans;
                    char pch = phs[pdx];
                    if (pch == '*') {
                        if (sdx < 1 || pdx < 1) {
                            ans = true;
                        } else {
                            ans = tab[sdx - 1][pdx - 1] && shs[sdx] == shs[sdx - 1];
                        }
                    } else if (pch == '.') {
                        ans = true;
                    } else {
                        if (sdx < 1 || pdx < 1) {
                            ans = shs[sdx] == phs[pdx];
                        } else {
                            ans = tab[sdx - 1][pdx - 1] && shs[sdx] == phs[pdx];
                        }
                    }
                    tab[sdx][pdx] = ans;
                }
            }

            return tab[sen - 1][pen - 1];
        }
    }

}
