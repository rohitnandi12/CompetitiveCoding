package com.leetcode.sliding_window;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <a href="https://leetcode.com/problems/sliding-window-maximum/description/">239. Sliding Window Maximum</a>
 * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
 * <p>
 * Return the max sliding window.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
 * Output: [3,3,5,5,6,7]
 * Explanation:
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * Example 2:
 * <p>
 * Input: nums = [1], k = 1
 * Output: [1]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 * 1 <= k <= nums.length
 */
public class P239SlidingWindowMaximum {

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(
                        maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)
                )
        );
        // Output: [3,3,5,5,6,7]
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int N = nums.length;
        int[] ans = new int[N - k + 1]; // Result array
        Deque<Integer> q = new LinkedList<>(); // Store indices of elements

        for (int i = 0; i < N; i++) {
            // Remove indices that are out of the current window (i - k + 1)
            if (!q.isEmpty() && q.peekFirst() < i - k + 1) {
                q.pollFirst();
            }

            // Remove indices whose corresponding values are smaller than nums[i]
            while (!q.isEmpty() && nums[q.peekLast()] <= nums[i]) {
                q.pollLast();
            }

            // Add current index to deque
            q.addLast(i);

            // Start storing results once we have a valid window (i >= k - 1)
            if (i >= k - 1) {
                ans[i - k + 1] = nums[q.peekFirst()];
            }
        }

        return ans;
    }
}
