package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.*;
import org.example.interview.leetcode.support.ListNode;

/**
 * @author qbq
 * @date 2022/1/12 10:37 AM
 */
public class Q0002 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().addTwoNumbers(ListNode.of("[2,4,3]"), ListNode.of("[5,6,4]")));
        ConsoleUtils.sout(new Solution().addTwoNumbers(ListNode.of("[1,8]"), ListNode.of("[0]")));
    }

    /**
     * 2、链表两数相加
     * <p>
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
    @LeetCodeMiddle({Tag.linked_list, Tag.math, Tag.recursion})
    /**
     * 执行用时 1 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了11.14%的用户
     * 通过测试用例：1568 / 1568
     */
    static class Solution {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode head = new ListNode();
            ListNode cur = head;
            // 双指针
            ListNode n1 = l1;
            ListNode n2 = l2;
            while (n1 != null || n2 != null) {
                int sum = (n1 != null ? n1.val : 0) + (n2 != null ? n2.val : 0) + cur.val;
                int res = sum % 10;
                int mod = sum / 10;
                cur.val = res;
                n1 = (n1 != null && n1.next != null) ? n1.next : null;
                n2 = (n2 != null && n2.next != null) ? n2.next : null;
                if (n1 != null || n2 != null || mod > 0) {
                    cur.next = new ListNode(mod);
                    cur = cur.next;
                }
            }
            return head;
        }

    }

}
