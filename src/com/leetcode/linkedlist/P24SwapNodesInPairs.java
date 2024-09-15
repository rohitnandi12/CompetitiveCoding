package com.leetcode.linkedlist;

/**
 * <a href="https://leetcode.com/problems/swap-nodes-in-pairs/description/">24. Swap Nodes in Pairs</a>
 * Given a linked list, swap every two adjacent nodes and return its head. You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 * <p>
 * Example 1:
 * Input: head = [1,2,3,4]
 * Output: [2,1,4,3]
 * <p>
 * Example 2:
 * Input: head = []
 * Output: []
 * <p>
 * Example 3:
 * Input: head = [1]
 * Output: [1]
 * <p>
 * Example 4:
 * Input: head = [1,2,3]
 * Output: [2,1,3]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [0, 100].
 * 0 <= Node.val <= 100
 * Level:Medium
 */
public class P24SwapNodesInPairs {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prevEnd = dummyHead;

        while (head != null && head.next != null) {
            ListNode secondNode = head.next;
            head.next = secondNode.next;
            secondNode.next = head;
            prevEnd.next = secondNode;
            prevEnd = head;
            head = head.next;
        }

        return dummyHead.next;
    }

    private static class ListNode {
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
