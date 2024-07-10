package com.leetcode.linkedlist;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/merge-k-sorted-lists/description/">Merge k Sorted Lists</a>
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * <p>
 * Merge all the linked-lists into one sorted linked-list and return it.
 * <p>
 * Example 1:
 * <p>
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * merging them into one sorted list:
 * 1->1->2->3->4->4->5->6
 * Example 2:
 * <p>
 * Input: lists = []
 * Output: []
 * Example 3:
 * <p>
 * Input: lists = [[]]
 * Output: []
 * <p>
 * Level:Hard
 */
public class P23MergeKSortedLists {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return divide(lists, 0, lists.length - 1);
    }

    private ListNode divide(ListNode[] lists, int start, int end) {
        if (start >= end) return lists[start];
        int mid = (start + end) / 2;
        ListNode h1 = divide(lists, start, mid);
        ListNode h2 = divide(lists, mid + 1, end);
        return mergeTwo(h1, h2);
    }

    private ListNode mergeTwo(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                curr.next = h1;
                h1 = h1.next;
            } else {
                curr.next = h2;
                h2 = h2.next;
            }
            curr = curr.next;
        }
        if (h1 != null) curr.next = h1;
        if (h2 != null) curr.next = h2;
        return dummy.next;
    }

    public ListNode mergeKListsPriorityQueue(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
                Comparator.comparingInt(f -> f.val)
        );

        for (ListNode listNode : lists) {
            if (listNode != null)
                pq.add(listNode);
        }

        if (pq.isEmpty())
            return null;

        ListNode op = new ListNode(-1);
        ListNode curr = op;

        while (!pq.isEmpty()) {
            curr.next = pq.remove();
            curr = curr.next;
            if (curr.next != null)
                pq.add(curr.next);
        }

        return op.next;
    }


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
    }

}
