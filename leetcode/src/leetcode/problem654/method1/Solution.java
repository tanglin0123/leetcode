package leetcode.problem654.method1;

import java.util.*;
import leetcode.commons.*;

// with stack
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums == null || nums.length == 0){
            return null;
        }
        
        Stack<TreeNode> stk = new Stack<>();
        TreeNode root = null;
        
        for(int i : nums){
            if(stk.isEmpty()){
                root = new TreeNode(i);
                stk.push(root);
                continue;
            }
            
            if(i < stk.peek().val){
                stk.peek().right = new TreeNode(i);
                stk.push(stk.peek().right);
                continue;
            } else {
                TreeNode t = null;
                while(!stk.isEmpty() && i > stk.peek().val){
                    t = stk.pop();
                }
                
                if(stk.isEmpty()){
                    root = new TreeNode(i);
                    root.left = t;
                    stk.push(root);
                    continue;
                } else {
                    stk.peek().right = new TreeNode(i);
                    stk.peek().right.left = t;
                    stk.push(stk.peek().right);
                    continue;
                }
            }
        }
        
        return root;
    }
    
    public static void main(String[] args) {
    	int[] a = {3,2,1,6,0,5};
    	
    	Solution s = new Solution();
    	s.constructMaximumBinaryTree(a);
    	
    }
}