package leetcode.problem814.method1;

import leetcode.commons.TreeNode;

public class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) return null;
        
        root.left = this.pruneTree(root.left);
        root.right = this.pruneTree(root.right);
        
        if(root.left == null && root.right == null) {
        	if(root.val == 1) return root;
        	else return null;
        }
        
        return root;
    }
}
