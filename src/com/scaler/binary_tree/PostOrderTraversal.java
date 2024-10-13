package com.scaler.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class PostOrderTraversal {
    public int[] postorderTraversal(TreeNode A) {

        ArrayList<Integer> ans = new ArrayList<>();
        Deque<TreeNode> st = new ArrayDeque<>();
        st.add(A);

        while(!st.isEmpty()) {
            TreeNode current = st.pop();

            if(current == null) {
                continue;
            }

            ans.add(current.val);
            st.add(current.left);
            st.add(current.right);
        }

        Collections.reverse(ans);

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}
