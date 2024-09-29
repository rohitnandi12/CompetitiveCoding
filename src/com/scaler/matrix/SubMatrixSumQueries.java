package com.scaler.matrix;

import java.util.ArrayList;

/**
 * <a href="https://www.scaler.com/academy/mentee-dashboard/class/38525/assignment/problems/4088?navref=cl_tt_nv">Sub-matrix Sum Queries</a>
 * Problem Description
 * Given a matrix of integers A of size N x M and multiple queries Q, for each query, find and return the submatrix sum.
 *
 * Inputs to queries are top left (b, c) and bottom right (d, e) indexes of submatrix whose sum is to find out.
 *
 * NOTE:
 * Rows are numbered from top to bottom, and columns are numbered from left to right.
 * The sum may be large, so return the answer mod 109 + 7.
 * Also, select the data type carefully, if you want to store the addition of some elements.
 * Indexing given in B, C, D, and E arrays is 1-based.
 * Top Left 0-based index = (B[i] - 1, C[i] - 1)
 * Bottom Right 0-based index = (D[i] - 1, E[i] - 1)
 *
 * Problem Constraints
 * 1 <= N, M <= 1000
 * -100000 <= A[i] <= 100000
 * 1 <= Q <= 100000
 * 1 <= B[i] <= D[i] <= N
 * 1 <= C[i] <= E[i] <= M
 *
 * Input Format
 * The first argument given is the integer matrix A.
 * The second argument given is the integer array B.
 * The third argument given is the integer array C.
 * The fourth argument given is the integer array D.
 * The fifth argument given is the integer array E.
 * (B[i], C[i]) represents the top left corner of the i'th query.
 * (D[i], E[i]) represents the bottom right corner of the i'th query.
 *
 * Output Format
 * Return an integer array containing the submatrix sum for each query.
 *
 * Example Input
 * Input 1:
 *
 *  A = [   [1, 2, 3]
 *          [4, 5, 6]
 *          [7, 8, 9]   ]
 *  B = [1, 2]
 *  C = [1, 2]
 *  D = [2, 3]
 *  E = [2, 3]
 * Input 2:
 *
 *  A = [   [5, 17, 100, 11]
 *          [0, 0,  2,   8]    ]
 *  B = [1, 1]
 *  C = [1, 4]
 *  D = [2, 2]
 *  E = [2, 4]
 *
 * Example Output
 * Output 1:
 *
 *  [12, 28]
 * Output 2:
 *
 *  [22, 19]
 *
 * Example Explanation
 * Explanation 1:
 *
 *  For query 1: Submatrix contains elements: 1, 2, 4 and 5. So, their sum is 12.
 *  For query 2: Submatrix contains elements: 5, 6, 8 and 9. So, their sum is 28.
 * Explanation 2:
 *
 *  For query 1: Submatrix contains elements: 5, 17, 0 and 0. So, their sum is 22.
 *  For query 2: Submatrix contains elements: 11 and 8. So, their sum is 19.
 *
 */
public class SubMatrixSumQueries {
    public ArrayList<Integer> solve(ArrayList<ArrayList<Integer>> A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E) {
        int ROW = A.size();
        int COL = A.get(0).size();
        Integer[][] prefix_sum = new Integer[ROW][COL];
        int modulus = 1000000007;

        prefix_sum[0][0] = A.get(0).get(0);
        // Making first row
        for(int j = 1; j < COL; j++) {
            prefix_sum[0][j] = prefix_sum[0][j - 1] + A.get(0).get(j);
        }
        // Making first column
        for(int i = 1; i < ROW; i++) {
            prefix_sum[i][0] = prefix_sum[i - 1][0] + A.get(i).get(0);
        }

        for(int i=1; i<ROW; i++) {
            for(int j=1; j<COL; j++) {
                prefix_sum[i][j] = A.get(i).get(j);

                prefix_sum[i][j] += (i > 0 ? prefix_sum[i-1][j] : 0);

                prefix_sum[i][j] += (j > 0 ? prefix_sum[i][j-1] : 0);

                prefix_sum[i][j] -= (i > 0 && j > 0 ? prefix_sum[i-1][j-1] : 0);

                prefix_sum[i][j] = prefix_sum[i][j] % modulus;
                if(prefix_sum[i][j] < 0) {
                    prefix_sum[i][j] = (prefix_sum[i][j] + modulus) % modulus;
                }
            }
        }

        ArrayList<Integer> query_sums = new ArrayList<Integer>();
        int Q = B.size();
        for(int q=0; q<Q; q++) {
            int sx = B.get(q)-1;
            int sy = C.get(q)-1;
            int ex = D.get(q)-1;
            int ey = E.get(q)-1;

            int query_sum = prefix_sum[ex][ey];

            query_sum -= sy > 0 ? prefix_sum[ex][sy-1] : 0;

            query_sum -= sx > 0 ? prefix_sum[sx-1][ey] : 0;

            query_sum += (sx > 0 && sy > 0) ? prefix_sum[sx-1][sy-1] : 0;


            query_sum = query_sum % modulus;
            if(query_sum < 0) {
                query_sum = (query_sum + modulus) % modulus;
            }
            query_sums.add(query_sum);
        }

        return query_sums;
    }
}
