package leetcode.problem449.method2;

import leetcode.commons.*;

public class Codec {

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if (root == null)
			return "";

		return root.val
				+ (root.left == null ? "" : "," + serialize(root.left))
				+ (root.right == null ? "" : "," + serialize(root.right));
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if (data == null || data.isEmpty()) {
			return null;
		}

		String[] ss = data.split(",");

		return this.dese(ss, 0, ss.length-1);
	}
	
	TreeNode dese(String[] ss, int start, int end){
		if(start > end){
			return null;
		}
		
		int r = Integer.parseInt(ss[start]);
		
		TreeNode root = new TreeNode(r);
		
		if(end == start){
			return root;
		}
		
		int i = start + 1;
		int j = end;
		
		if(Integer.parseInt(ss[start+1]) > r){
			root.right = this.dese(ss, start+1, end);
			return root;
		}
		
		if(Integer.parseInt(ss[end]) < r){
			root.left = this.dese(ss, start+1, end);
			return root;
		}
		
		while(i < j){
			int mid = (i+j)/2;
			
			int val = Integer.parseInt(ss[mid]);
			
			if(val < r){
				if(mid == end || (mid < end && Integer.parseInt(ss[mid+1]) > r)){
					i = mid;
					break;
				} 
				
				i = mid + 1;
			} else { // val > r
				j = mid - 1;
			}
		}
		
		root.left = this.dese(ss, start+1, i);
		root.right = this.dese(ss, i+1, end);
		
		return root;
	}
	

//	public static class TreeNode {
//		int val;
//		TreeNode left;
//		TreeNode right;
//
//		TreeNode(int x) {
//			val = x;
//		}
//	}

	// Your Codec object will be instantiated and called as such:
	// Codec codec = new Codec();
	// codec.deserialize(codec.serialize(root));
	public static void main(String[] args) {

//		TreeNode root = new TreeNode(2);
//		root.left = new TreeNode(1);
//		root.left = new TreeNode(1);


		String a = "2,1,3";

		Codec c = new Codec();
		System.out.println(c.serialize(c.deserialize(a)));
	}

}
