package com.leetcode.array;

import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/kth-largest-element-in-an-array/description/">215. Kth Largest Element in an Array</a>
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * <p>
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * <p>
 * Can you solve it without sorting?
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 * Example 2:
 * <p>
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * <p>
 * Constraints:
 * <p>
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 */
public class P215KthLargestElementInAnArray {

    public static void main(String[] args) {
        System.out.println(
                findKthLargestQuickSelect(new int[]{3,2,1,5,6,4}, 2)
        ); // output 5
    }

    public int findKthLargestPriorityQueue(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int i=0;
        while(i < k) {
            minHeap.add(nums[i]);
            i+=1;
        }

        for(; i<nums.length; i++) {
            if(minHeap.peek() < nums[i]) {
                minHeap.remove();
                minHeap.add(nums[i]);
            }
        }

        return minHeap.peek();
    }

    public static int findKthLargestQuickSelect(int[] nums, int k) {
        k = nums.length - k;  // Convert kth largest to index in sorted order
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int pivot = partition(nums, left, right);
            if (pivot == k) {
                return nums[pivot];
            } else if (pivot < k) {
                left = pivot + 1;
            } else {
                right = pivot - 1;
            }
        }
        return -1;  // This should never be reached
    }

    private static int partition(int[] nums, int left, int right) {
        int pivot = nums[right];  // Choose rightmost element as pivot
        int i = left;

        for (int j = left; j < right; j++) {
            if (nums[j] < pivot) {
                swap(nums, i, j);
                i++;
            }
        }
        swap(nums, i, right);  // Place pivot in its correct position
        return i;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
