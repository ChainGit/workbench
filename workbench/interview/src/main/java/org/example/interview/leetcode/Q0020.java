package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeEasy;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/29 5:15 PM
 */
public class Q0020 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().isValid("{{(([()]))}}"));
        ConsoleUtils.sout(new Solution().isValid("(("));
    }

    /**
     * 20. 有效的括号
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     */
    @LeetCodeEasy({Tag.stack, Tag.string})
    /**
     * 91 / 91 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 39.6 MB
     */
    static class Solution {
        public boolean isValid(String s) {
            char[] chs = s.toCharArray();
            int len = chs.length;
            char[] stack = new char[len];
            int top = 0;
            for (int idx = 0; idx < len; idx++) {
                char ch = chs[idx];
                boolean isPush = ch == '(' || ch == '{' || ch == '[';
                if (isPush) {
                    stack[top++] = ch;
                } else {
                    if (top < 1) {
                        return false;
                    }
                    char pop = stack[--top];
                    boolean match = (pop == '(' && ch == ')') ||
                            (pop == '{' && ch == '}') || (pop == '[' && ch == ']');
                    if (!match) {
                        return false;
                    }
                }
            }
            return top == 0;
        }
    }

}
