package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qbq
 * @date 2022/1/29 2:45 PM
 */
public class Q0015 {

    public static void main(String[] args) {
        // -4 -1 -1 0 1 2
        ConsoleUtils.sout(new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     * 15. 三数之和
     * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     */
    @LeetCodeMiddle({Tag.array, Tag.double_pointer, Tag.sort})
    /**
     * 318 / 318 个通过测试用例
     * 状态：通过
     * 执行用时: 22 ms
     * 内存消耗: 45.6 MB
     */
    static class Solution {
        /**
         * 最简单的方法可以使用三重循环，优化的方法：
         * 1、排序
         * 2、第一个指针i：从0到len-1
         * 3、第二、三个指针m,n：分别从i+1,len-1，判断i+m+n=0
         * 4、若i+m+n=0，得到一解
         * 5、若i+m+n<0，说明m偏小，需要m++
         * 6、若i+m+n>0，说明n偏大，需要n--
         * 7、当m>=n时，i++
         */
        public List<List<Integer>> threeSum(int[] nums) {
            int len = nums.length;
            List<List<Integer>> res = new ArrayList<>();
            if (len < 3) {
                return res;
            }
            // 先对数组排序
            Arrays.sort(nums);
            // 第一层指针i
            for (int idx = 0; idx < len - 2; idx++) {
                int ldx = idx + 1;
                int rdx = len - 1;
                int vala = nums[idx];
                if (idx > 0 && vala == nums[idx - 1]) {
                    continue;
                }
                while (ldx < rdx) {
                    int valb = nums[ldx];
                    int valc = nums[rdx];
                    int sum = vala + valb + valc;
                    if (sum == 0) {
                        res.add(Arrays.asList(vala, valb, valc));
                        do {
                            rdx--;
                            ldx++;
                        } while (ldx < rdx &&
                                nums[ldx] == nums[ldx - 1] &&
                                nums[rdx] == nums[rdx + 1]);
                    } else if (sum > 0) {
                        rdx--;
                    } else {
                        ldx++;
                    }
                }
            }
            return res;
        }
    }

}
