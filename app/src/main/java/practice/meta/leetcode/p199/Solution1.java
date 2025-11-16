package practice.meta.leetcode.p199;

import java.util.ArrayList;
import java.util.List;
import utils.*;

public class Solution1 {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        List<TreeNode> q = new ArrayList<>();
        q.add(root);

        List<TreeNode> newQ = new ArrayList<>();

        while (!q.isEmpty()) {
            for (TreeNode node : q) {
                if (node.left != null) {
                    newQ.add(node.left);
                }
                if (node.right != null) {
                    newQ.add(node.right);
                }
            }

            result.add(q.getLast().val);

            q = newQ;
            newQ = new ArrayList<>();
        }

        return result;
    }
}
