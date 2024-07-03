package com.leetcode.linkedlist;

/**
 * <a href="https://leetcode.com/problems/intersection-of-two-linked-lists/description/">160. Intersection of Two Linked Lists</a>
 * Given the heads of two singly linked-lists headA and headB, return the node at which the two lists intersect. If the two linked lists have no intersection at all, return null.
 * <p>
 * For example, the following two linked lists begin to intersect at node c1:
 * <p>
 * <p>
 * The test cases are generated such that there are no cycles anywhere in the entire linked structure.
 * <p>
 * Note that the linked lists must retain their original structure after the function returns.
 * <p>
 * Custom Judge:
 * <p>
 * The inputs to the judge are given as follows (your program is not given these inputs):
 * <p>
 * intersectVal - The value of the node where the intersection occurs. This is 0 if there is no intersected node.
 * listA - The first linked list.
 * listB - The second linked list.
 * skipA - The number of nodes to skip ahead in listA (starting from the head) to get to the intersected node.
 * skipB - The number of nodes to skip ahead in listB (starting from the head) to get to the intersected node.
 * The judge will then create the linked structure based on these inputs and pass the two heads, headA and headB to your program. If you correctly return the intersected node, then your solution will be accepted.
 */
public class P160LinkedListIntersection {

    public static void main(String[] args) {
        ListNode intersectedNode = new ListNode(3);
        ListNode headA= (new ListNode(1)).setNext(new ListNode(2).setNext(intersectedNode));
        ListNode headB= new ListNode(4).setNext(new ListNode(5).setNext(intersectedNode));

        ListNode ans = getIntersectionNode(headA, headB);
        System.out.println(ans);
        assert ans == intersectedNode : "ans is null";
    }
    // Definition for singly-linked list.
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }

        ListNode setNext(ListNode nextNode) {
            this.next = nextNode;
            return this;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {

        ListNode ptr1 = headA;
        ListNode ptr2 = headB;

        while (ptr1 != null && ptr2 != null) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        ListNode newPtr1 = headA;
        ListNode newPtr2 = headB;
        if (ptr1 == null) {
            while (ptr2 != null) {
                newPtr2 = newPtr2.next;
                ptr2 = ptr2.next;
            }
        } else {
            while (ptr1 != null) {
                newPtr1 = newPtr1.next;
                ptr1 = ptr1.next;
            }
        }

        while (newPtr1 != null) {
            if (newPtr1 == newPtr2) {
                return newPtr1;
            }
            newPtr1 = newPtr1.next;
            newPtr2 = newPtr2.next;
        }

        return null;

    }
}
