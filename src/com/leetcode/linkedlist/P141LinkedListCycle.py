"""
https://leetcode.com/problems/linked-list-cycle/description/
141. Linked List Cycle
Given head, the head of a linked list, determine if the linked list has a cycle in it.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to. Note that pos is not passed as a parameter.

Return true if there is a cycle in the linked list. Otherwise, return false.
Leve:Easy
"""
from typing import Optional
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class P141LinkedListCycle:
    def hasCycle(self, head: Optional[ListNode]) -> bool:

        if not head or not head.next:
            return False

        slow = head
        fast = head.next

        while fast and fast.next:

            if(slow == fast):
                return True
            slow = slow.next
            fast = fast.next.next

        return False