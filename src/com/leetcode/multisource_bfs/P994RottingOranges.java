package com.leetcode.multisource_bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/rotting-oranges/description/">994. Rotting Oranges</a>
 * You are given an m x n grid where each cell can have one of three values:
 * <p>
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 * <p>
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * Example 2:
 * <p>
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
 * Example 3:
 * <p>
 * Input: grid = [[0,2]]
 * Output: 0
 * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10
 * grid[i][j] is 0, 1, or 2.
 * Level:Medium
 */
public class P994RottingOranges {
    public static void main(String[] args) {
        P994RottingOranges obj = new P994RottingOranges();
        System.out.println(obj.orangesRotting(new int[][]{
                {2, 1, 1}, {1, 1, 0}, {0, 1, 1}
        })); // 4

        System.out.println(obj.orangesRotting(new int[][]{
                {2, 1, 1}, {0, 1, 1}, {1, 0, 1}
        })); // -1
    }

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public int orangesRotting(int[][] grid) {
        Queue<Point> q = new LinkedList<>();
        final int ROWS = grid.length;
        final int COLS = grid[0].length;

        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid[r][c] == 2)
                    q.add(new Point(r, c));
            }
        }
        if (!q.isEmpty())
            q.add(null);

        int time = q.isEmpty() ? 0 : -1;
        while (!q.isEmpty()) {
            Point rottenPoint = q.remove();
            if (rottenPoint == null) {
                time += 1;
                if (!q.isEmpty())
                    q.add(null);
            } else {
                int r = rottenPoint.getX();
                int c = rottenPoint.getY();
                // top
                if (r > 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    q.add(new Point(r - 1, c));
                }
                // bottom
                if (r < ROWS - 1 && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    q.add(new Point(r + 1, c));
                }
                // left
                if (c > 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    q.add(new Point(r, c - 1));
                }
                // right
                if (c < COLS - 1 && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    q.add(new Point(r, c + 1));
                }
            }
        }

        for (int[] ints : grid) {
            for (int c = 0; c < COLS; c++) {
                if (ints[c] == 1)
                    return -1;
            }
        }

        return time;
    }
}
