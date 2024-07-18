"""
https://leetcode.com/problems/minimum-size-subarray-sum/
209. Minimum Size Subarray Sum
Solved
Medium
Topics
Companies
Given an array of positive integers nums and a positive integer target, return the minimal length of a
subarray
 whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.



Example 1:

Input: target = 7, nums = [2,3,1,2,4,3]
Output: 2
Explanation: The subarray [4,3] has the minimal length under the problem constraint.
Example 2:

Input: target = 4, nums = [1,4,4]
Output: 1
Example 3:

Input: target = 11, nums = [1,1,1,1,1,1,1,1]
Output: 0

Level:Medium
"""
class P209MinimumSizeSubarraySum:
    def minSubArrayLen(self, target, nums):
        """
        :type target: int
        :type nums: List[int]
        :rtype: int
        """

        min_length = len(nums) + 1
        start = 0
        current_sum = 0

        for end in range(len(nums)):

            current_sum += nums[end]

            while(start <= end and current_sum >= target):
                min_length = min(min_length, end - start + 1)
                current_sum -= nums[start]
                start += 1

        return 0 if (min_length == len(nums) + 1) else min_length
