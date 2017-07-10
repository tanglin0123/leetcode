package leetcode.problem382.method1;

import java.util.ArrayList;
import java.util.Random;



public class Solution {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
	
	ListNode list = null;
	
	ArrayList<Integer> arr = new ArrayList<Integer>();
	
	Random rand = new Random();
	
	/**
	 * @param head
	 *            The linked list's head. Note that the head is guaranteed to be
	 *            not null, so it contains at least one node.
	 */
	public Solution(ListNode head) {
		this.list = head;
		ListNode cur = head;
		while(cur != null){
			arr.add(cur.val);
			cur = cur.next;
		}
	}

	/** Returns a random node's value. */
	public int getRandom() {
		return arr.get(rand.nextInt(arr.size()));
	}
}

/**
 * Your Solution object will be instantiated and called as such: Solution obj =
 * new Solution(head); int param_1 = obj.getRandom();
 */
