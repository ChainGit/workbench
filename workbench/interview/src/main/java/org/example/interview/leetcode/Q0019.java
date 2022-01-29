package org.example.interview.leetcode;

import org.example.core.util.ConsoleUtils;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;
import org.example.interview.leetcode.support.ListNode;

/**
 * @author qbq
 * @date 2022/1/29 4:02 PM
 */
public class Q0019 {

    public static void main(String[] args) {
        ConsoleUtils.sout(new Solution().removeNthFromEnd(ListNode.of("1,2"), 2));
        ConsoleUtils.sout(new Solution().removeNthFromEnd(ListNode.of("1,2,3,4,5"), 2));
    }

    /**
     * 19. 删除链表的倒数第 N 个结点
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     */
    @LeetCodeMiddle({Tag.linked_list, Tag.double_pointer})
    /**
     * 208 / 208 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 39.7 MB
     */
    static class Solution {
        /**
         * 双指针，ab两个指针距离相差n，这样当a到达链表末尾的时候，b正好在倒数第n个位置
         */
        public ListNode removeNthFromEnd(final ListNode head, int n) {
            if (head == null) {
                return null;
            }
            ListNode cur = head;
            ListNode pb = null;
            ListNode prevPb = null;
            int pos = 1;
            for (; ; ) {
                if (pos == n) {
                    pb = head;
                }
                if (cur.next == null) {
                    break;
                }
                cur = cur.next;
                if (pb != null) {
                    prevPb = pb;
                    pb = pb.next;
                }
                pos++;
            }
            if (pb == null) {
                return null;
            }
            if (pb == head) {
                return head.next;
            }
            prevPb.next = pb.next;
            return head;
        }

    }

}
