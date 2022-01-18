package org.example.interview.leetcode;

import com.google.common.collect.Lists;
import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

import java.util.Arrays;
import java.util.List;

/**
 * @author qbq
 * @date 2022/1/17 8:11 PM
 */
public class Q0006 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().convert("PAYPALISHIRING", 3));
        ConsoleUtils.sout(new Solution().convert("AB", 1));
        ConsoleUtils.sout(new Solution().convert("ABC", 1));
        ConsoleUtils.sout(new Solution().convert("ABCDE", 4));
    }

    /**
     * 6. Z 字形变换（Z 字形变换）
     * <p>
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
     * <p>
     * 请你实现这个将字符串进行指定行数变换的函数：
     * <p>
     * string convert(string s, int numRows);
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @LeetCodeMiddle({Tag.string})
    static class Solution {
        public String convert(String s, int numRows) {
            /**
             *
             *  &               &               &
             *  &           &   &           &   &
             *  &       &       &       &       &
             *  &   &           &   &           &
             *  &               &
             *
             */

            // 一个完整的半边数量，包含 |/
            int one = numRows + Math.max(0, numRows - 2);
            // 一个完整的半边的列数
            int oneCol = 1 + Math.max(0, numRows - 2);
            // 一个完整的半边的行数
            int oneRow = numRows;

            char[] chs = s.toCharArray();
            int len = chs.length;

            // 总列数
            int totalCol = (len / one) * oneCol +
                    (len % one > 0 ? 1 : 0) +
                    Math.max(0, len % one - oneRow);
            // 总行数
            int totalRow = len / oneRow > 0 ? oneRow : len;

            char[][] table = new char[totalRow][totalCol];

            int idx = 0;
            int jdx = 0;
            boolean down = true;
            for (int cdx = 0; cdx < len; cdx++) {
                table[idx][jdx] = chs[cdx];
                if (down && idx == totalRow - 1) {
                    down = false;
                }
                if (!down && idx == 0) {
                    down = true;
                }
                if (down) {
                    idx = Math.min(totalRow - 1, idx + 1);
                }
                if (!down) {
                    idx = Math.max(0, idx - 1);
                    jdx++;
                }
                if (totalRow == 1) {
                    jdx++;
                }
            }

            // 重新行扫描组成字符串
            char[] res = new char[len];
            int rdx = 0;
            for (int adx = 0; adx < totalRow; adx++) {
                for (int bdx = 0; bdx < totalCol; bdx++) {
                    char val = table[adx][bdx];
                    if (val != 0) {
                        res[rdx++] = val;
                    }
                }
            }

            return new String(res);
        }
    }

}
