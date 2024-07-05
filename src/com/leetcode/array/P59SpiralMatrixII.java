package com.leetcode.array;

/**
 * <a href="https://leetcode.com/problems/spiral-matrix-ii/description/">59. Spiral Matrix II</a>
 * Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.
 *
 * Example 1:
 *
 * Input: n = 3
 * Output: [[1,2,3],[8,9,4],[7,6,5]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 * Constraints:
 * 1 <= n <= 20
 * Level:Medium
 */
public class P59SpiralMatrixII {

    public static void main(String[] args) {
        int[][] ot = generateMatrix(3);
        System.out.println(ot);
    }
    public static int[][] generateMatrix(int n) {
        int di = 0;
        int i = 0;
        int dj = 1;
        int j = 0;
        int topI = -1;
        int botI = n;
        int rightJ = n;
        int leftJ = -1;
        int[][] output = new int[n][n];

        for (int e = 1; e <= n * n; e++) {

            output[i][j] = e;
            i += 1 * di;
            j += 1 * dj;
            // di 0
            // dj 1
            // i  0
            // j  3
            if (j >= rightJ) {
                j -= 1;
                i += 1;
                di = 1;
                dj = 0;
                topI += 1;
            }

            if (i >= botI) {
                i -= 1;
                j -= 1;
                di = 0;
                dj = -1;
                rightJ -= 1;
            }

            if (j <= leftJ) {
                j += 1;
                i -= 1;
                di = -1;
                dj = 0;
                botI -= 1;
            }

            if (i <= topI) {
                i += 1;
                j += 1;
                di = 0;
                dj = 1;
                leftJ += 1;
            }
        }

        return output;
    }
}
