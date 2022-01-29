package org.example.interview.leetcode;

import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;

/**
 * @author qbq
 * @date 2022/1/28 2:50 PM
 */
public class Q0011 {

    public static void main(String[] args) {

    }

    /**
     * 11. 盛最多水的容器
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * <p>
     * 说明：你不能倾斜容器。
     */
    @LeetCodeMiddle({Tag.greed, Tag.double_linked_list, Tag.array})
    /**
     * 60 / 60 个通过测试用例
     * 状态：通过
     * 执行用时: 3 ms
     * 内存消耗: 51.1 MB
     */
    static class Solution {
        public int maxArea(int[] height) {
            // 双指针
            int len = height.length;
            // 左指针
            int idx = 0;
            // 右指针
            int jdx = len - 1;
            int res = 0;
            while (idx < jdx) {
                int lft = height[idx];
                int rgt = height[jdx];
                int bt = jdx - idx;
                int ht = 0;
                if (lft > rgt) {
                    ht = rgt;
                    jdx--;
                } else {
                    ht = lft;
                    idx++;
                }
                int tmp = ht * bt;
                res = Math.max(res, tmp);
            }
            return res;
        }
    }

}
