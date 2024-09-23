package com.leetcode.dfs;

/**
 * <a href="https://leetcode.com/problems/number-of-islands/description/">200. Number of Islands</a>
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
 * <p>
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * Example 1:
 * <p>
 * Input: grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * Output: 1
 * Example 2:
 * <p>
 * Input: grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * Output: 3
 * <p>
 * Constraints:
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 * Level:Medium
 */
public class P200NumberOfIslands {

    public static void main(String[] args) {
        P200NumberOfIslands obj = new P200NumberOfIslands();

        int result = obj.numIslands(new char[][]{
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        });

        System.out.println(result); // 3
    }

    public int numIslands(char[][] grid) {

        int ROW = grid.length;
        int COL = grid[0].length;
        int totalIsland = 0;
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {

                if (grid[r][c] == '1') {
                    totalIsland += 1;
                    captureIsland(grid, r, c);
                }
            }
        }

        return totalIsland;
    }

    public void captureIsland(char[][] grid, int row, int col) {

        grid[row][col] = '0';

        if (row > 0 && grid[row - 1][col] == '1') captureIsland(grid, row - 1, col);
        if (col > 0 && grid[row][col - 1] == '1') captureIsland(grid, row, col - 1);
        if (col < grid[0].length - 1 && grid[row][col + 1] == '1') captureIsland(grid, row, col + 1);
        if (row < grid.length - 1 && grid[row + 1][col] == '1') captureIsland(grid, row + 1, col);

    }
}
