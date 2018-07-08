package leetcode.commons;

public class AVLTree {

	TreeNode avlroot;
	
	public void add(int i) {
		avlroot = add(avlroot, i);
	}
	
	public static class TreeNode {
		int val;
		int height;
		TreeNode left;
		TreeNode right;
		int count;
		
		public TreeNode(int val) {
			this.val = val;
			this.height = 1;
			this.count = 1;
		}
		
		public String toString() {
			return val + "(" + left + "," + right + ")"; 
		}
	}
	
	void inOrder(TreeNode n) {
		if(n == null) return;
		inOrder(n.left);
		System.out.print(n.val + ",");
		inOrder(n.right);
	}
	
	TreeNode add(TreeNode root, int i) {
		if(root == null) {
			return new TreeNode(i);
		}
		
		if(i == root.val) {
			++root.count;
			return root;
		}
		
		if(i < root.val) { // go left
			root.left = add(root.left, i);
			
			if(height(root.left) - height(root.right) == 2) { // L
				if(i < root.left.val) { // LL, right rotate root
					root = rightRotate(root);
				} else { // LR, left rotate left subtree, then right rotate root
					root.left = leftRotate(root.left);
					root = rightRotate(root);
				}
			}
			
		} else { // i > root.val , go right
			root.right = add(root.right, i);
			
			if(height(root.right) - height(root.left) == 2) { // R
				if(i < root.right.val) { // RL, right rotate subtree, then left rotate root 
					root.right = rightRotate(root.right);
					root = leftRotate(root);
				} else { // RR, left rotate root
					root = leftRotate(root);
				}
			}
		}
		
		calculateHeight(root);
		
		return root;
	}
	
	int height(TreeNode t) {
		if(t == null) return 0;
		return t.height;
	}
	
	void calculateHeight(TreeNode t) {
		t.height = 1 + Math.max(height(t.left), height(t.right));
	}
	
	TreeNode rightRotate(TreeNode root) {
		TreeNode troot = root;
		TreeNode tl = root.left;
		TreeNode tlr = root.left.right;
		
		TreeNode newroot = tl;
		newroot.right = troot;
		newroot.right.left = tlr;
		
		calculateHeight(newroot.right);
		calculateHeight(newroot);
		
		return newroot;
	}
	
	TreeNode leftRotate(TreeNode root) {
		TreeNode troot = root;
		TreeNode tr = root.right;
		TreeNode trl = root.right.left;
		
		TreeNode newroot = tr;
		newroot.left = troot;
		newroot.left.right = trl;
		
		calculateHeight(newroot.left);
		calculateHeight(newroot);
		
		return newroot;
	}
	
	public static void main(String[] args) {
		int[] a = new int[] {3,2,1,4,5,6,7,16,15,14,13,12,11,10,8,9};
		
		AVLTree tree = new AVLTree();
		
		for(int i : a) {
			tree.add(i);
		}

		tree.inOrder(tree.avlroot);
	}

}
