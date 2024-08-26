package com.scaler.prefixsum;

/**
 * Problem Description
 * You are given an array A of length N and Q queries given by the 2D array B of size Q*2. Each query consists of two integers B[i][0] and B[i][1].
 * For every query, the task is to calculate the sum of all even indices in the range A[B[i][0]…B[i][1]].
 *
 * Note : Use 0-based indexing
 *
 *
 * Problem Constraints
 * 1 <= N <= 105
 * 1 <= Q <= 105
 * 1 <= A[i] <= 100
 * 0 <= B[i][0] <= B[i][1] < N
 *
 *
 * Input Format
 * First argument A is an array of integers.
 * Second argument B is a 2D array of integers.
 *
 *
 * Output Format
 * Return an array of integers.
 *
 *
 * Example Input
 * Input 1:
 * A = [1, 2, 3, 4, 5]
 * B = [   [0,2]
 *         [1,4]   ]
 * Input 2:
 * A = [2, 1, 8, 3, 9]
 * B = [   [0,3]
 *         [2,4]   ]
 *
 *
 * Example Output
 * Output 1:
 * [4, 8]
 * Output 2:
 * [10, 17]
 *
 *
 * Example Explanation
 * For Input 1:
 * The subarray for the first query is [1, 2, 3] whose sum of even indices is 4.
 * The subarray for the second query is [2, 3, 4, 5] whose sum of even indices is 8.
 * For Input 2:
 * The subarray for the first query is [2, 1, 8, 3] whose sum of even indices is 10.
 * The subarray for the second query is [8, 3, 9] whose sum of even indices is 17.
 */
public class SumOfEvenIndices {
    public ArrayList<Integer> solve(ArrayList<Integer> A, ArrayList<ArrayList<Integer>> B) {

        ArrayList<Integer> prefixSum = new ArrayList<>();
        prefixSum.add(A.get(0));
        for(int i=1; i<A.size(); i++) {
            if(i%2 == 1) {
                prefixSum.add(prefixSum.get(i-1));
            } else {
                prefixSum.add(prefixSum.get(i-1) + A.get(i));
            }
        }

        ArrayList<Integer> result = new ArrayList<Integer>();
        for(ArrayList<Integer> Q : B) {
            int left = Q.get(0);
            int right = Q.get(1);
            int rangeSum  = prefixSum.get(right);
            rangeSum -= left == 0 ? 0 : prefixSum.get(left-1);
            result.add(rangeSum);
        }
        // prefixSum.forEach(System.out::print);
        // [16 16 19 19 26 26 43 43 50]
        // [0  1   2  3  4  5  6  7  8]

        // [27 24 17]
        return result;
    }
}
