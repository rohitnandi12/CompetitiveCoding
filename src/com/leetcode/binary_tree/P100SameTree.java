package com.leetcode.binary_tree;

/**
 * 100. Same Tree
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * <p>
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
 */
public class P100SameTree {

    public static void main(String[] args) {
        System.out.println(isSameTree(null, null));
    }
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        return recursiveTraversal(p, q);
    }

    public static boolean recursiveTraversal(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p != null && q != null && p.val == q.val) {
            return recursiveTraversal(p.left, q.left) && recursiveTraversal(p.right, q.right);
        }

        return false;
    }

    static class TreeNode {
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
