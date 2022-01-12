package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.leetcode.support.ListNode;

import java.util.List;

/**
 * @author qbq
 * @date 2022/1/12 10:37 AM
 */
public class Q0002 {

    public static void main(String[] args) {
        ListNode node = new Solution().addTwoNumbers(ListNode.of("[2,4,3]"), ListNode.of("[5,6,4]"));
        ConsoleUtils.sout(node);
    }

    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    static class Solution {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            return null;
        }

    }

}
