package leetcode.problem449.method1;

import java.util.*;

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

		TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
		TreeNode cur = root;

		Stack<TreeNode> stk = new Stack<TreeNode>();

		for (int i = 1; i < ss.length; ++i) {
			String s = ss[i];

			int val = Integer.parseInt(s);

			TreeNode n = new TreeNode(val);

			if (val < cur.val) {
				cur.left = n;

				stk.push(cur);
				cur = n;

			} else { // val > cur.val

				while (!stk.empty() && stk.peek().val < val) {
					cur = stk.pop();
				}

				cur.right = n;
				cur = n;
			}
		}

		return root;
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

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
