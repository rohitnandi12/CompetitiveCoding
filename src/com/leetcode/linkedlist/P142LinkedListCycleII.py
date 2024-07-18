"""
https://leetcode.com/problems/linked-list-cycle-ii/description/
142. Linked List Cycle II

Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.
Level:Easy
"""

from typing import Optional
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None

class P142LinkedListCycleII:
    def detectCycle(self, head: Optional[ListNode]) -> Optional[ListNode]:

        if not head or not head.next:
            return None

        slow = head
        fast = head

        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next

            if slow == fast:
                break
        else:
            return None

        while head != slow:
            head = head.next
            slow = slow.next

        return head