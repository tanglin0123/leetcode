package leetcode.problem222.method1;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int possibleMiss = 0;
    int curMaxLevel = -1;
    boolean found = false;
    
    public int countNodes(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        calculate(root, 0);
        
        int total = 1<<(curMaxLevel+1) - 1;
        
        if(!found){
            return total;
        } else{
            return total - possibleMiss;
        }
        
    }
    
    private int calculate(TreeNode node, int level){
        if(found){
            return curMaxLevel;
        }
        
        if(node.right==null && node.left == null){
            possibleMiss+=2;
            if(curMaxLevel==-1){
                curMaxLevel = level;
            }
            return level;
        }
        
        if(node.right==null && node.left != null){
            ++possibleMiss;
            curMaxLevel=level+1;
            found = true;
            return level+1;
        }
        
        // has both children... 
        
        if(level >= curMaxLevel ){ 
            curMaxLevel = level + 1;
            found = true;
            return level+1;
        }
        
        int rightMaxLevel = calculate(node.right, level+1);
        if(found){
            return rightMaxLevel;
        }
        int leftMaxLevel = calculate(node.left, level+1);
        if(found){
            return leftMaxLevel;
        }
        if(leftMaxLevel>rightMaxLevel){
            found = true;
            return leftMaxLevel;
        }
        return rightMaxLevel;
    }

	
	
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int x) { val = x; }
	}
	
	public static void main(String[] args) {
		TreeNode r1 = new TreeNode(1);
		TreeNode r2 = new TreeNode(2);
		
		r1.right = r2;
		
		System.out.println(new Solution().countNodes(r1));

	}

}
