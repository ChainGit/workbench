package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/29 10:57 AM
 */
public class Q0012 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().intToRoman(10));
        ConsoleUtils.sout(new Solution().intToRoman(11));
        ConsoleUtils.sout(new Solution().intToRoman(2344));
    }

    /**
     * 12. 整数转罗马数字
     * <p>
     * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给你一个整数，将其转为罗马数字。
     */
    @LeetCodeMiddle({Tag.array, Tag.math, Tag.hash_table})
    /**
     * 3999 / 3999 个通过测试用例
     * 状态：通过
     * 执行用时: 3 ms
     * 内存消耗: 41 MB
     */
    static class Solution {
        public String intToRoman(int num) {
            int t = num;
            // Integer.MAX_NUM = 2 147 483 647
            char[] buf = new char[100];
            int idx = 0;
            while (t > 0) {
                if (t >= 1000) {
                    t -= 1000;
                    buf[idx++] = 'M';
                } else if (t >= 900) {
                    t -= 900;
                    buf[idx++] = 'C';
                    buf[idx++] = 'M';
                } else if (t >= 500) {
                    t -= 500;
                    buf[idx++] = 'D';
                } else if (t >= 400) {
                    t -= 400;
                    buf[idx++] = 'C';
                    buf[idx++] = 'D';
                } else if (t >= 100) {
                    t -= 100;
                    buf[idx++] = 'C';
                } else if (t >= 90) {
                    t -= 90;
                    buf[idx++] = 'X';
                    buf[idx++] = 'C';
                } else if (t >= 50) {
                    t -= 50;
                    buf[idx++] = 'L';
                } else if (t >= 40) {
                    t -= 40;
                    buf[idx++] = 'X';
                    buf[idx++] = 'L';
                } else if (t >= 10) {
                    t -= 10;
                    buf[idx++] = 'X';
                } else if (t >= 9) {
                    t -= 9;
                    buf[idx++] = 'I';
                    buf[idx++] = 'X';
                } else if (t >= 5) {
                    t -= 5;
                    buf[idx++] = 'V';
                } else if (t >= 4) {
                    t -= 4;
                    buf[idx++] = 'I';
                    buf[idx++] = 'V';
                } else {
                    t -= 1;
                    buf[idx++] = 'I';
                }
            }
            return new String(buf, 0, idx);
        }
    }

}
