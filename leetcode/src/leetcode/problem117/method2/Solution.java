package leetcode.problem117.method2;

import leetcode.commons.*;


// pass. O(1) space
public class Solution {
	public void connect(TreeLinkNode root) {
		if (root == null) {
			return;
		}

		TreeLinkNode head = root;

		while (head != null) {
			TreeLinkNode nextHead = null;
			
			TreeLinkNode cur = head;
			TreeLinkNode pre = null;
			
			while (cur != null) {
				if (cur.left != null) {
					if (pre == null) {
						nextHead = cur.left;
						pre = cur.left;
					} else {
						pre.next = cur.left;
						pre = pre.next;
					}
				}
				if(cur.right != null) {
					if(pre == null) {
						nextHead = cur.right;
						pre = cur.right;
					} else {
						pre.next = cur.right;
						pre = pre.next;
					}
				}
				cur = cur.next;
			}
			
			head = nextHead;	
		}
	}
}