package com.scaler.matrix;

import java.util.ArrayList;

/**
 * <a href="https://www.scaler.com/academy/mentee-dashboard/class/38525/assignment/problems/4091?navref=cl_tt_nv">Sum of all Submatrices</a>
 * Problem Description
 * Given a 2D Matrix A of dimensions N*N, we need to return the sum of all possible submatrices.
 * <p>
 * Problem Constraints
 * 1 <= N <=30
 * <p>
 * 0 <= A[i][j] <= 10
 * <p>
 * Input Format
 * Single argument representing a 2-D array A of size N x N.
 * <p>
 * Output Format
 * Return an integer denoting the sum of all possible submatrices in the given matrix.
 * <p>
 * Example Input
 * Input 1:
 * A = [ [1, 1]
 * [1, 1] ]
 * Input 2:
 * A = [ [1, 2]
 * [3, 4] ]
 * <p>
 * Example Output
 * Output 1:
 * 16
 * Output 2:
 * 40
 * <p>
 * Example Explanation
 * Example 1:
 * Number of submatrices with 1 elements = 4, so sum of all such submatrices = 4 * 1 = 4
 * Number of submatrices with 2 elements = 4, so sum of all such submatrices = 4 * 2 = 8
 * Number of submatrices with 3 elements = 0
 * Number of submatrices with 4 elements = 1, so sum of such submatrix = 4
 * Total Sum = 4+8+4 = 16
 * Example 2:
 * The submatrices are [1], [2], [3], [4], [1, 2], [3, 4], [1, 3], [2, 4] and [[1, 2], [3, 4]].
 * Total sum = 40
 */
public class SumOfAllSubmatrices {

    public int solve(ArrayList<ArrayList<Integer>> A) {

        // sum of contribution of A[i,j] in all its submatrix
        int ROW_SIZE = A.size();
        int COL_SIZE = A.get(0).size();

        int ans = 0;
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                ans += A.get(i).get(j) * (i + 1) * (j + 1) * (ROW_SIZE - i) * (COL_SIZE - j);
            }
        }

        return ans;
    }
}
