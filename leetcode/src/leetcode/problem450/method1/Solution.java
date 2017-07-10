package leetcode.problem450.method1;

import leetcode.commons.*;

public class Solution {


	public TreeNode deleteNode(TreeNode node, int key) {
		if (node == null) {
			return null;
		}

		if (node.val == key) {
			if (node.right == null) {
				return node.left;
			}

			if (node.left == null) {
				return node.right;
			}

			TreeNode parent = null;
			TreeNode cur = node.right;
			while (cur.left != null) {
				parent = cur;
				cur = cur.left;
			}
			
			TreeNode cur2 = cur;
			while(cur2.right != null){
				cur2 = cur2.right;
			}
			
			cur.left = node.left;
            
            if(parent != null){
                cur2.right = node.right;
                parent.left = null;
            }

			return cur;
		}

		if (key < node.val) {
			node.left = deleteNode(node.left, key);
			return node;
		}

		if (key > node.val) {
			node.right = deleteNode(node.right, key);
			return node;
		}

		return null;
	}

	public static void main(String[] args) {
		//TreeNode a = TreeNode.fromString("[5,3,6,2,4,null,7]");
		//int b = 3;
		
		TreeNode a = TreeNode.fromString("[2,0,33,null,1,25,40,null,null,11,31,34,45,10,18,29,32,null,36,43,46,4,null,12,24,26,30,null,null,35,39,42,44,null,48,3,9,null,14,22,null,null,27,null,null,null,null,38,null,41,null,null,null,47,49,null,null,5,null,13,15,21,23,null,28,37,null,null,null,null,null,null,null,null,8,null,null,null,17,19,null,null,null,null,null,null,null,7,null,16,null,null,20,6]");
		int b = 33;
		
		Solution s = new Solution();
		System.out.println(s.deleteNode(a, b).val);
	}


}
