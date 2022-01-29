package org.example.interview.leetcode;

import org.example.interview.annotations.LeetCodeEasy;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/29 11:48 AM
 */
public class Q0013 {

    public static void main(String[] args) {

    }

    /**
     * 13. 罗马数字转整数
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
     * <p>
     * 字符          数值
     * I             1
     * V             5
     * X             10
     * L             50
     * C             100
     * D             500
     * M             1000
     * 例如， 罗马数字 2 写做 II ，即为两个并列的 1 。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
     * <p>
     * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
     * <p>
     * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     * 给定一个罗马数字，将其转换成整数。
     */
    @LeetCodeEasy({Tag.string, Tag.math, Tag.hash_table})
    /**
     * 3999 / 3999 个通过测试用例
     * 状态：通过
     * 执行用时: 2 ms
     * 内存消耗: 41.3 MB
     */
    static class Solution {
        public int romanToInt(String s) {
            char[] chs = s.toCharArray();
            int len = s.length();
            int res = 0;
            for (int idx = 0; idx < len; idx++) {
                char ch = chs[idx];
                if (ch == 'M') {
                    res += 1000;
                } else if (ch == 'D') {
                    res += 500;
                } else if (ch == 'C') {
                    if (idx < len - 1) {
                        if (chs[idx + 1] == 'D') {
                            res += 400;
                            idx++;
                            continue;
                        } else if (chs[idx + 1] == 'M') {
                            res += 900;
                            idx++;
                            continue;
                        }
                    }
                    res += 100;
                } else if (ch == 'L') {
                    res += 50;
                } else if (ch == 'X') {
                    if (idx < len - 1) {
                        if (chs[idx + 1] == 'L') {
                            res += 40;
                            idx++;
                            continue;
                        } else if (chs[idx + 1] == 'C') {
                            res += 90;
                            idx++;
                            continue;
                        }
                    }
                    res += 10;
                } else if (ch == 'V') {
                    res += 5;
                } else if (ch == 'I') {
                    if (idx < len - 1) {
                        if (chs[idx + 1] == 'V') {
                            res += 4;
                            idx++;
                            continue;
                        } else if (chs[idx + 1] == 'X') {
                            res += 9;
                            idx++;
                            continue;
                        }
                    }
                    res += 1;
                }
            }
            return res;
        }
    }

}
