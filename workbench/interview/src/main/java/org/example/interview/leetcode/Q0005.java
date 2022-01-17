package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/14 4:19 PM
 */
public class Q0005 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().longestPalindrome("a"));
        ConsoleUtils.sout(new Solution().longestPalindrome("aa"));
        ConsoleUtils.sout(new Solution().longestPalindrome("aba"));
        ConsoleUtils.sout(new Solution().longestPalindrome("aabbcc"));
        ConsoleUtils.sout(new Solution().longestPalindrome("aabcc"));
        ConsoleUtils.sout(new Solution().longestPalindrome("aabaa"));
        ConsoleUtils.sout(new Solution().longestPalindrome("xaabccdccd"));
        ConsoleUtils.sout(new Solution().longestPalindrome("aabbccbbaax"));
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     */
    //可以使用 动态规划 或者 双指针
    @LeetCodeMiddle({Tag.string, Tag.dynamic_programing})
    /**
     * 180 / 180 个通过测试用例
     * 状态：通过
     * 执行用时: 208 ms
     * 内存消耗: 42.1 MB
     */
    static class Solution {
        public String longestPalindrome(String s) {
            // 动态规划：s[x,y]
            // 当len=1时，一定是个回文串
            // 当len=2时，s[i][j] = ((i+1<len) && s[i+1][j] == s[i][j]) || (j>0 && s[i][j-1] == s[i][j])
            // 当len>2时，s[i][j] = s[i+1][j-1] && s[i] == s[j]
            int LEN = s.length();
            char[] chs = s.toCharArray();
            boolean[][] tbl = new boolean[LEN][LEN];
            int mft = 0;
            int mrt = 0;
            int max = 0;
            for (int len = 1; len <= LEN; len++) {
                for (int rgt = 0; rgt < LEN; rgt++) {
                    int lft = rgt - len + 1;
                    if (lft < 0) {
                        continue;
                    }
                    boolean res = false;
                    if (len == 1) {
                        res = true;
                    }
                    if (len == 2) {
                        res = chs[lft] == chs[rgt];
                    }
                    if (len > 2 && lft + 1 < LEN) {
                        res = tbl[lft + 1][rgt - 1] && chs[lft] == chs[rgt];
                    }
                    tbl[lft][rgt] = res;
                    if (res) {
                        int tmx = rgt - lft + 1;
                        if (tmx > max) {
                            mft = lft;
                            mrt = rgt;
                            max = tmx;
                        }
                    }
                }
            }
            return String.copyValueOf(chs, mft, mrt - mft + 1);
        }
    }
}
