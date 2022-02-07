package org.example.interview.leetcode;

import org.example.interview.annotations.LeetCodeEasy;
import org.example.interview.annotations.LeetCodeMiddle;
import org.example.interview.annotations.Tag;
import org.example.interview.leetcode.support.ListNode;

/**
 * @author qbq
 * @date 2022/2/2 3:19 PM
 */
public class Q0021 {

    public static void main(String[] args) {

    }

    /**
     * 21. 合并两个有序链表
     * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     */
    @LeetCodeEasy({Tag.linked_list, Tag.recursion})
    /**
     * 208 / 208 个通过测试用例
     * 状态：通过
     * 执行用时: 0 ms
     * 内存消耗: 41 MB
     */
    static class Solution {
        public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
            ListNode p1 = list1;
            ListNode p2 = list2;
            ListNode res = null;
            ListNode cur = null;
            while (p1 != null || p2 != null) {
                ListNode min = null;
                if (p1 != null) {
                    min = p1;
                }
                if (p2 != null) {
                    if (min == null) {
                        min = p2;
                    } else if (min.val > p2.val) {
                        min = p2;
                    }
                }
                if (cur == null) {
                    cur = min;
                    res = cur;
                } else {
                    cur.next = min;
                    cur = cur.next;
                }
                if (min == p1) {
                    p1 = p1.next;
                } else {
                    p2 = p2.next;
                }
            }
            return res;
        }
    }

}
