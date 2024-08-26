package com.scaler.twopointer;

/**
 * Problem Description
 * Given an array A of N integers and also given two integers B and C. Reverse the elements of the array A within the given inclusive range [B, C].
 * <p>
 * <p>
 * Problem Constraints
 * 1 <= N <= 105
 * 1 <= A[i] <= 109
 * 0 <= B <= C <= N - 1
 * <p>
 * <p>
 * Input Format
 * The first argument A is an array of integer.
 * The second and third arguments are integers B and C
 * <p>
 * <p>
 * Output Format
 * Return the array A after reversing in the given range.
 * <p>
 * <p>
 * Example Input
 * Input 1:
 * <p>
 * A = [1, 2, 3, 4]
 * B = 2
 * C = 3
 * Input 2:
 * <p>
 * A = [2, 5, 6]
 * B = 0
 * C = 2
 * <p>
 * <p>
 * Example Output
 * Output 1:
 * <p>
 * [1, 2, 4, 3]
 * Output 2:
 * <p>
 * [6, 5, 2]
 * <p>
 * <p>
 * Example Explanation
 * Explanation 1:
 * <p>
 * We reverse the subarray [3, 4].
 * Explanation 2:
 * <p>
 * We reverse the entire array [2, 5, 6].
 */
public class ReverseInARange {
    public ArrayList<Integer> solve(ArrayList<Integer> A, int B, int C) {

        int start = B;
        int end = C;

        while (start < end) {
            Collections.swap(A, start, end);
            start += 1;
            end -= 1;
        }

        return A;
    }
}
