package com.leetcode.two_pointers;

import java.util.Arrays;

/**
 * <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/">167. Two Sum II - Input Array Is Sorted</a>
 * Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order, find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
 * <p>
 * Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
 * <p>
 * The tests are generated such that there is exactly one solution. You may not use the same element twice.
 * <p>
 * Your solution must use only constant extra space.
 * <p>
 * Example 1:
 * <p>
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
 * Example 2:
 * <p>
 * Input: numbers = [2,3,4], target = 6
 * Output: [1,3]
 * Explanation: The sum of 2 and 4 is 6. Therefore index1 = 1, index2 = 3. We return [1, 3].
 * Example 3:
 * <p>
 * Input: numbers = [-1,0], target = -1
 * Output: [1,2]
 * Explanation: The sum of -1 and 0 is -1. Therefore index1 = 1, index2 = 2. We return [1, 2].
 * <p>
 * Constraints:
 * <p>
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers is sorted in non-decreasing order.
 * -1000 <= target <= 1000
 * The tests are generated such that there is exactly one solution.
 * Level:Medium
 */
public class P167TwoSumIIInputArrayIsSorted {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9))); // [1,2]
    }

    public static int[] twoSum(int[] numbers, int target) {
        int ptr1 = 0;
        int ptr2 = numbers.length - 1;

        while (ptr1 < ptr2) {
            if (numbers[ptr1] + numbers[ptr2] == target) {
                return new int[]{ptr1 + 1, ptr2 + 1};
            } else if (numbers[ptr1] + numbers[ptr2] > target) {
                ptr2 -= 1;
            } else {
                ptr1 += 1;
            }
        }

        return new int[]{-1, -1};
    }
}
