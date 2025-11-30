package practice.meta.leetcode.p129;

import utils.*;

public class Solution1 {
    public int sumNumbers(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return sum(root, 0);
    }

    public int sum(TreeNode node, int curVal) {
        int curSum = curVal * 10 + node.val;
        if (node.left == null && node.right == null) {
            return curSum;
        }

        int leftSum = 0;
        if (node.left != null) {
            leftSum = sum(node.left, curSum);
        }

        int rightSum = 0;
        if (node.right != null) {
            rightSum = sum(node.right, curSum);
        }

        return leftSum + rightSum;
    }
}
