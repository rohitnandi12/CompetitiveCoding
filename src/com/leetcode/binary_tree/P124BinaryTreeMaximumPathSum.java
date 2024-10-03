package com.leetcode.binary_tree;

/**
 * <a href="https://leetcode.com/problems/binary-tree-maximum-path-sum/description/">124. Binary Tree Maximum Path Sum</a>
 * A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
 * <p>
 * The path sum of a path is the sum of the node's values in the path.
 * <p>
 * Given the root of a binary tree, return the maximum path sum of any non-empty path.
 * <p>
 * Example 1:
 * <p>
 * Input: root = [1,2,3]
 * Output: 6
 * Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.
 * Example 2:
 * <p>
 * Input: root = [-10,9,20,null,null,15,7]
 * Output: 42
 * Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [1, 3 * 104].
 * -1000 <= Node.val <= 1000
 * Level:Hard
 */
public class P124BinaryTreeMaximumPathSum {
    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        traverse(root);
        return this.max;
    }

    public int traverse(TreeNode root) {
        if (root == null) return 0;

        int left = Math.max(traverse(root.left), 0);
        int right = Math.max(traverse(root.right), 0);

        this.max = Math.max(this.max, root.val + right + left);

        return root.val + Math.max(left, right);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
