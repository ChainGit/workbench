package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qbq
 * @date 2022/1/29 3:25 PM
 */
public class Q0017 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().letterCombinations("2"));
        ConsoleUtils.sout(new Solution().letterCombinations("234"));
    }

    /**
     * 17. 电话号码的字母组合
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * <p>
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     */
    @LeetCodeMiddle({Tag.string, Tag.recursion, Tag.hash_table})
    /**
     * 25 / 25 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 39.6 MB
     */
    static class Solution {
        /**
         * 因为字符串的长度不固定，所以不能使用循环来做，需要用回溯做法（同dfs）
         */
        public List<String> letterCombinations(String digits) {
            List<String> res = new ArrayList<>();
            if (digits.length() == 0) {
                return res;
            }

            char[][] table = new char[8][];
            table[0] = new char[]{'a', 'b', 'c'};// 2
            table[1] = new char[]{'d', 'e', 'f'};// 3
            table[2] = new char[]{'g', 'h', 'i'};// 4
            table[3] = new char[]{'j', 'k', 'l'};// 5
            table[4] = new char[]{'m', 'n', 'o'};// 6
            table[5] = new char[]{'p', 'q', 'r', 's'};//7
            table[6] = new char[]{'t', 'u', 'v'};// 8
            table[7] = new char[]{'w', 'x', 'y', 'z'};// 9

            char[] chars = digits.toCharArray();
            int idx = 0;
            int len = chars.length;
            char[] ans = new char[len];
            recursion(table, chars, res, ans, len, idx);

            return res;
        }

        private void recursion(final char[][] table, final char[] chars, final List<String> res,
                               final char[] ans, final int len, final int idx) {
            if (idx == len) {
                res.add(new String(ans));
                return;
            }

            int tdx = chars[idx] - '0';
            char[] content = table[tdx - 2];
            for (char cont : content) {
                ans[idx] = cont;
                recursion(table, chars, res, ans, len, idx + 1);
            }
        }

    }

}
