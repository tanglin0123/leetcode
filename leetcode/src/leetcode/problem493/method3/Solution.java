package leetcode.problem493.method3;

import java.util.*;

//Given an array nums, we call (i, j) an important reverse pair if i < j and nums[i] > 2*nums[j].
//You need to return the number of important reverse pairs in the given array.
public class Solution {
	public static class AVLTree {

		TreeNode avlroot;
		
		public void add(long i) {
			avlroot = add(avlroot, i);
		}
		
		public static class TreeNode {
			long val;
			int height;
			TreeNode left;
			TreeNode right;
			int count;
			int rcount;
			int lcount;
			
			public TreeNode(long val) {
				this.val = val;
				this.height = 1;
				this.count = 1;
				this.rcount = 0;
				this.lcount = 0;
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
		
		TreeNode add(TreeNode root, long i) {
			if(root == null) {
				return new TreeNode(i);
			}
			
			if(i == root.val) {
				++root.count;
				return root;
			}
			
			if(i < root.val) { // go left
				++root.lcount;
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
				++root.rcount;
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
			
			troot.lcount = tl.rcount;
			tl.rcount = troot.lcount + troot.count + troot.rcount;
			
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
			
			troot.rcount = tr.lcount;
			tr.lcount = troot.lcount + troot.count + troot.rcount;
			
			TreeNode newroot = tr;
			newroot.left = troot;
			newroot.left.right = trl;
			
			calculateHeight(newroot.left);
			calculateHeight(newroot);
			
			return newroot;
		}
		
		int getGCount(long key) {
			return this.getGCount(avlroot, key, 0);
		}
		
		int getGCount(TreeNode root, long key, int candidate) {
			if(root == null) {
				return candidate;
			}
			
			if(root.val == key) {
				return root.rcount + candidate;
			} else if(key < root.val) { // go to left
				return getGCount(root.left, key, root.count + root.rcount + candidate);
			} else { // key > root.val , go to right
				return getGCount(root.right, key, candidate);
			}
		}
	}
	
    public int reversePairs(int[] nums) {
        if(nums == null || nums.length <=1){
            return 0;
        }
        
        AVLTree tree = new AVLTree();
        
        int count = 0;
        for(int n : nums) {
        	count += tree.getGCount(2*(long)n);
        	tree.add(n);
        }
        
        return count;
    }


	public static void main(String[] args) {
		int[] nums = new int[]{2,4,3,5,1};
		//int[] nums = new int[]{1,3,2,3,1};
		
		Solution s = new Solution();
		System.out.println(s.reversePairs(nums));
	}

}
