package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.*;

import java.util.*;

/**
 * @author qbq
 * @date 2022/1/10 5:43 PM
 */
public class Q0001 {

    public static void main(String[] args) {
        int[] res = new Solution().twoSum(new int[]{1, 2, 3}, 3);
        ConsoleUtils.jout(res);
    }

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/two-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    @LeetCodeEasy({Tag.array, Tag.hash_table})
    /**
     * 结果超时
     */
    static class Solution {

        public int[] twoSum(int[] nums, int target) {
            // hashtable做缓存，优化双重循环
            Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
            for (int i = 0; i < nums.length; ++i) {
                if (hashtable.containsKey(target - nums[i])) {
                    return new int[]{hashtable.get(target - nums[i]), i};
                }
                hashtable.put(nums[i], i);
            }
            return new int[0];
        }

    }

}
