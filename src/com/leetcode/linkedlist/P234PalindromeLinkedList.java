package com.leetcode.linkedlist;

/**
 * <a href="https://leetcode.com/problems/palindrome-linked-list/description/">P234PalindromeLinkedList</a>
 * 234. Palindrome Linked List
 * Given the head of a singly linked list, return true if it is a
 * palindrome or false otherwise.
 * Level:Easy
 */
public class P234PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;

        ListNode fast = head.next;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode secondHead = reverseList(slow.next);
        slow.next = null;

        while (head != null && secondHead != null && head.val == secondHead.val) {
            head = head.next;
            secondHead = secondHead.next;
        }

        return secondHead == null || head == null;
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    class ListNode {
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
