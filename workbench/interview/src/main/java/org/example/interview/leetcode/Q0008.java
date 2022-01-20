package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/20 11:06 AM
 */
public class Q0008 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().myAtoi("    -12    34  ufhdj"));
    }

    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
     * <p>
     * 函数 myAtoi(string s) 的算法如下：
     * <p>
     * 读入字符串并丢弃无用的前导空格
     * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
     * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
     * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
     * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
     * 返回整数作为最终结果。
     * 注意：
     * <p>
     * 本题中的空白字符只包括空格字符 ' ' 。
     * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @LeetCodeMiddle({Tag.string})
    /**
     * 1082 / 1082 个通过测试用例
     * 状态：通过
     * 执行用时: 1 ms
     * 内存消耗: 38.4 MB
     */
    static class Solution {
        public int myAtoi(String s) {
            char[] chs = s.toCharArray();
            int len = s.length();
            // 是否有符号 + -
            boolean hasSign = false;
            // 符号 默认 +
            boolean sign = true;
            // 是否有数字
            boolean hasNum = false;
            long res = 0;
            for (int idx = 0; idx < len; idx++) {
                if (chs[idx] == ' ') {
                    if (hasNum || hasSign) {
                        break;
                    }
                    continue;
                }
                if (chs[idx] == '-') {
                    if (hasSign || hasNum) {
                        break;
                    }
                    hasSign = true;
                    sign = false;
                    continue;
                }
                if (chs[idx] == '+') {
                    if (hasSign || hasNum) {
                        break;
                    }
                    hasSign = true;
                    sign = true;
                    continue;
                }
                if (chs[idx] < '0' || chs[idx] > '9') {
                    break;
                }
                hasNum = true;
                int val = chs[idx] - '0';
                res = res * 10 + val;
                if (sign && res > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (!sign && -res < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            }
            return (int) (sign ? res : -res);
        }
    }
}
