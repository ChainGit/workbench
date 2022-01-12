package org.example.interview.leetcode.support;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode of(int[] arr) {
        ListNode head = null;
        for (int num : arr) {
            ListNode node = new ListNode();
            if (Objects.isNull(head)) {
                head = node;
            } else {
                head.next = node;
            }
            node.val = num;
        }
        return head;
    }

    private static ListNode of(List<Integer> lst) {
        return of(ArrayUtils.toPrimitive(lst.toArray(new Integer[]{})));
    }

    public static ListNode of(String str) {
        return of(Arrays.stream(str.replaceAll("[^,，0-9]", StringUtils.EMPTY)
                .split("[,，]")).map(Integer::valueOf).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        List<Integer> lst = Lists.newArrayList();
        ListNode node = this;
        lst.add(node.val);
        while (Objects.nonNull(node.next)) {
            node = node.next;
            lst.add(node.val);
        }
        return String.format("ListNode[%s]", StringUtils.join(lst, ","));
    }

}