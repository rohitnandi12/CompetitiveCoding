package com.leetcode.linkedlist;

/**
 * <a href="https://leetcode.com/problems/reverse-linked-list-ii/description/">92. Reverse Linked List II</a>
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 * <p>
 * Example 1:
 * <p>
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 * <p>
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * TODO: Follow up: Could you do it in one pass?
 * Level:Medium
 */
public class P92ReverseLinkedListII {
    public ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode ln = head, rn = head, prev = null;

        while (right > 0) {
            right -= 1;
            rn = rn.next;
            if (left > 1) {
                prev = ln;
                ln = ln.next;
                left -= 1;
            }
        }

        ListNode revHead = reverse(ln, rn);

        if (prev != null) {
            prev.next = revHead;
        } else {
            head = revHead;
        }

        return head;
    }

    public ListNode reverse(ListNode start, ListNode end) {
        ListNode prev = null, currNode = start;

        while (currNode != end) {
            ListNode nextNode = currNode.next;
            currNode.next = prev;
            prev = currNode;
            currNode = nextNode;
        }

        start.next = end;
        return prev;
    }

    private class ListNode {
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
