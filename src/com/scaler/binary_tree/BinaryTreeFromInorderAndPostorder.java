package com.scaler.binary_tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeFromInorderAndPostorder {

    public static void main(String[] args) {
        BinaryTreeFromInorderAndPostorder obj = new BinaryTreeFromInorderAndPostorder();
        obj.buildTree(
                new ArrayList<>(List.of(7,5,6,2,3,1,4)),
                new ArrayList<>(List.of(5,6,3,1,4,2,7))
        );
    }

    public TreeNode buildTree(ArrayList<Integer> inOrder, ArrayList<Integer> postOrder) {
        return aux(0, inOrder.size() - 1, inOrder, postOrder.size() - 1, postOrder);
    }

    private TreeNode aux(int start, int end, ArrayList<Integer> inOrder,
                         int postOrderIndex, ArrayList<Integer> postOrder
    ) {

        if (start > end)
            return null;

        TreeNode root = new TreeNode(postOrder.get(postOrderIndex));

        int index = findIndex(root.val, start, end, inOrder);

        root.right = aux(index + 1, end, inOrder, postOrderIndex - 1, postOrder);
        root.left = aux(start, index - 1, inOrder, postOrderIndex - (end - index) - 1, postOrder);

        return root;
    }

    private int findIndex(int number, int start, int end, ArrayList<Integer> inOrder) {

        while (start <= end) {

            if (inOrder.get(start) == number) {
                return start;
            }
            start += 1;
        }

        return -1;
    }
}
