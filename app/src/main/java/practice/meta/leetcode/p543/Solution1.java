package practice.meta.leetcode.p543;

import utils.*;

public class Solution1 {
    int maxDist = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        this.getDepth(root);

        return maxDist;
    }

    private int getDepth(TreeNode node) {
        if (node.left == null && node.right == null) {
            return 1;
        }

        int leftDepth = 0;
        if (node.left != null) {
            leftDepth = getDepth(node.left);
        }

        int rightDepth = 0;
        if (node.right != null) {
            rightDepth = getDepth(node.right);
        }

        maxDist = Math.max(maxDist, leftDepth + rightDepth);

        return 1 + Math.max(leftDepth, rightDepth);
    }


    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        
        TreeNode root = TreeNode.makeTree(new Integer[]{1,2,3,4,5});

        System.out.println(solution1.diameterOfBinaryTree(root));
    }

}
