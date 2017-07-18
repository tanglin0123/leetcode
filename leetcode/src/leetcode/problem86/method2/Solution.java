package leetcode.problem86.method2;

import leetcode.commons.*;

public class Solution {
	public ListNode partition(ListNode head, int x) {
		if(head == null) {
			return head;
		}
		
		ListNode smallh = new ListNode(Integer.MIN_VALUE);
		ListNode bigh = new ListNode(Integer.MAX_VALUE);
		
		ListNode smallPre = smallh;
		ListNode bigPre = bigh;
		
		ListNode cur = head;
		while(cur != null) {
			if(cur.val < x) {
				smallPre.next = cur;
				smallPre = cur;
			} else {
				bigPre.next = cur;
				bigPre = cur;
			}
			
			cur = cur.next;
		}
		
		bigPre.next = null; // very important!!!
		smallPre.next = bigh.next;
		return smallh.next;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
