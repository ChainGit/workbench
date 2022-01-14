package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

import java.util.Arrays;

/**
 * @author qbq
 * @date 2022/1/12 5:23 PM
 */
public class Q0003 {

    public static void main(String[] args) {
        //ConsoleUtils.sout(new Solution().lengthOfLongestSubstring("dndnhfbdsddf"));
        //ConsoleUtils.sout(new Solution().lengthOfLongestSubstring("abcabcbb"));
        //ConsoleUtils.sout(new Solution().lengthOfLongestSubstring("abba"));
        ConsoleUtils.sout(new Solution().lengthOfLongestSubstring("abcabcbb"));
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     */
    @LeetCodeMiddle({Tag.hash_table, Tag.string, Tag.sliding_window})
    /**
     * 987 / 987 个通过测试用例
     * 状态：通过
     * 执行用时: 2 ms
     * 内存消耗: 38.5 MB
     */
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            char[] chs = s.toCharArray();
            int len = chs.length;
            // hash数组做标记，数组的长度表示128个ascii字符，数组的元素值存储字符在原字符串出现的下标
            int[] buf = new int[128];
            // 初始化为-1
            Arrays.fill(buf, -1);
            // 最大长度
            int max = 0;
            // 左指针
            int lft = 0;
            // 右指针
            int rgt = 0;
            //String str = null;
            for (; rgt < len; rgt++) {
                int ch = chs[rgt];
                // 是否已经在buf中存在
                if (buf[ch] != -1) {
                    // 将左指针移动到新的未重复位置
                    int prv = lft;
                    lft = buf[ch] + 1;
                    for (int idx = prv; idx < lft; idx++) {
                        // 清除buf中无效字符
                        buf[chs[idx]] = -1;
                    }
                }
                // 将当前右指针的下标存入buf
                buf[ch] = rgt;
                // 计算max
                max = Math.max(max, rgt - lft + 1);
                //str = s.substring(lft, rgt + 1);
                //ConsoleUtils.sout(str + " " + max);
            }
            return max;
        }
    }

}
