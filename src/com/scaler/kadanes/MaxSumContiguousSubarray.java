package com.scaler.kadanes;

/**
 * Problem Description
 * Find the maximum sum of contiguous non-empty subarray within an array A of length N.
 *
 * Problem Constraints
 * 1 <= N <= 1e6
 * -1000 <= A[i] <= 1000
 *
 * Input Format
 * The first and the only argument contains an integer array, A.
 *
 * Output Format
 * Return an integer representing the maximum possible sum of the contiguous subarray.
 *
 * Example Input
 * Input 1:
 *
 *  A = [1, 2, 3, 4, -10]
 * Input 2:
 *
 *  A = [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 *
 *
 * Example Output
 * Output 1:
 *  10
 * Output 2:
 *  6
 *
 * Example Explanation
 * Explanation 1:
 *
 *  The subarray [1, 2, 3, 4] has the maximum possible sum of 10.
 * Explanation 2:
 *
 *  The subarray [4,-1,2,1] has the maximum possible sum of 6.
 */
public class MaxSumContiguousSubarray {
    public int maxSubArray(final List<Integer> A) {
        int maxSum = Integer.MIN_VALUE;
        int currentSum = 0;

        for(int i=0; i<A.size(); i++) {
            maxSum = Math.max(maxSum, currentSum + A.get(i));
            currentSum = Math.max(0, currentSum + A.get(i));
        }

        // [-2, 1, -3, 4, -1, 2, 1, -5, 4]
        //  p
        return maxSum;
    }
}
