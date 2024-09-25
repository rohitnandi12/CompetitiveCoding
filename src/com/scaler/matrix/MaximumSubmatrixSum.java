package com.scaler.matrix;

import java.util.ArrayList;

/**
 * <a href="https://www.scaler.com/academy/mentee-dashboard/class/38525/assignment/problems/20177?navref=cl_tt_nv">Maximum Submatrix Sum</a>
 * Problem Description
 * Given a row-wise and column-wise sorted matrix A of size N * M.
 * Return the maximum non-empty submatrix sum of this matrix.
 *
 * Problem Constraints
 * 1 <= N, M <= 1000
 * -109 <= A[i][j] <= 109
 *
 * Input Format
 * The first argument is a 2D integer array A.
 *
 * Output Format
 * Return a single integer that is the maximum non-empty submatrix sum of this matrix.
 *
 * Example Input
 * Input 1:-
 *     -5 -4 -3
 * A = -1  2  3
 *      2  2  4
 * Input 2:-
 *     1 2 3
 * A = 4 5 6
 *     7 8 9
 *
 * Example Output
 * Output 1:-
 * 12
 * Output 2:-
 * 45
 *
 * Example Explanation
 * Expanation 1:-
 * The submatrix with max sum is
 * -1 2 3
 *  2 2 4
 *  Sum is 12.
 * Explanation 2:-
 * The largest submatrix with max sum is
 * 1 2 3
 * 4 5 6
 * 7 8 9
 * The sum is 45.
 */
public class MaximumSubmatrixSum {
    public Long solve(ArrayList<ArrayList<Integer>> A) {

        int ROW_SIZE = A.size();
        int COL_SIZE = A.get(0).size();
        long maxSum = Long.MIN_VALUE;

        for(int col1 = 0; col1<COL_SIZE; col1++) {
            long prefixCol[] = new long[ROW_SIZE];
            for(int col2 = col1; col2<COL_SIZE; col2++) {
                long currentSum = 0L;
                for(int row = 0; row<ROW_SIZE; row++) {
                    prefixCol[row] += A.get(row).get(col2);
                    maxSum = Math.max(maxSum, currentSum + prefixCol[row]);
                    currentSum = Math.max(0, currentSum + prefixCol[row]);
                }
            }
        }

        return maxSum;
    }
}
