package org.example.interview.leetcode.jzoffer2;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCode;
import org.example.interview.annotations.Level;
import org.example.interview.annotations.Result;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/4 11:03 AM
 */
public class Jz2001 {

    public static void main(String[] args) {
        ConsoleUtils.sout(Integer.MAX_VALUE);
        ConsoleUtils.sout(Integer.MIN_VALUE);
        ConsoleUtils.sout(new Solution().divide(1, 2));
        ConsoleUtils.sout(new Solution().divide(10, 2));
        ConsoleUtils.sout(new Solution().divide(11, 2));
        ConsoleUtils.sout(new Solution().divide(10, 0));
        ConsoleUtils.sout(new Solution().divide(-10, 2));
        ConsoleUtils.sout(new Solution().divide(-10, -2));
        ConsoleUtils.sout(new Solution().divide(-2147483648, 1));
        ConsoleUtils.sout(new Solution().divide(2147483647, 1));
        ConsoleUtils.sout(new Solution().divide(-2147483648, 2));
        ConsoleUtils.sout(new Solution().divide(2147483647, 2));
    }

    /**
     * 给定两个整数 a 和 b ，求它们的除法的商 a/b ，要求不得使用乘号 '*'、除号 '/' 以及求余符号 '%' 。
     * <p>
     *  
     * <p>
     * 注意：
     * <p>
     * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
     * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231, 231−1]。本题中，如果除法结果溢出，则返回 231 − 1
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/xoh6Oh
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @LeetCode(tags = {Tag.math}, level = Level.easy)
    @Result("结果超时")
    static class Solution {

        public int divide(final int a, final int b) {
            if (b == 0) {
                return Integer.MAX_VALUE;
            }
            final boolean sign = (a >= 0 && b > 0) || (a < 0 && b < 0);
            long z = 0;
            long x = Math.abs((long) a);
            final long y = Math.abs((long) b);
            while (x - y >= 0) {
                z++;
                x = x - y;
            }
            final long r = sign ? z : -z;
            if (r > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (r < Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) r;
        }

    }

}
