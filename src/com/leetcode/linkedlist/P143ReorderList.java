package com.leetcode.linkedlist;

/**
 * <a href="https://leetcode.com/problems/reorder-list/description/">143. Reorder List</a>
 * You are given the head of a singly linked-list. The list can be represented as:
 * <p>
 * L0 → L1 → … → Ln - 1 → Ln
 * Reorder the list to be on the following form:
 * <p>
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * You may not modify the values in the list's nodes. Only nodes themselves may be changed.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: head = [1,2,3,4]
 * Output: [1,4,2,3]
 * Example 2:
 * <p>
 * <p>
 * Input: head = [1,2,3,4,5]
 * Output: [1,5,2,4,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the list is in the range [1, 5 * 104].
 * 1 <= Node.val <= 1000
 */
public class P143ReorderList {

    public static void main(String[] args) {
        P143ReorderList obj = new P143ReorderList();
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));

        obj.reorderList(head);
        obj.print(head);
    }
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) {
            return;
        }

        // split the lists
        ListNode firstHalf = head;
        ListNode secondHalf = getSecondHalfHead(head);

        // reverse the second list
        secondHalf = reverseList(secondHalf);

        // merge from middle
        mergeList(firstHalf, secondHalf);
    }

    public void print(ListNode head) {
        while(head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println();
    }

    private void mergeList(ListNode h1, ListNode h2) {
        ListNode mp = h1;
        h1 = h1.next;

        while(h1 != null || h2 != null) {

            if(h2 != null) {
                mp.next = h2;
                h2 = h2.next;
                mp = mp.next;
            }

            if(h1 != null) {
                mp.next = h1;
                h1 = h1.next;
                mp = mp.next;
            }
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        while(head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }

        return prev;
    }

    private ListNode getSecondHalfHead(ListNode head) {
        if(head == null)
            return null;

        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode secondHalf = slow.next;
        slow.next = null;

        return secondHalf;
    }
      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
