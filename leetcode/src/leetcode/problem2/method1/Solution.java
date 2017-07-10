package leetcode.problem2.method1;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	ListNode head = new ListNode(0);
    	
    	if(l1 == null || l2 == null) { return head; }
        
    	int tmp = 0;
        
        ListNode lastPos = null;
        ListNode curPos = head;
        while((l1 != null || l2 != null || tmp != 0)) {
        	
        	int n1 = l1 == null ? 0 : l1.val;
        	int n2 = l2 == null ? 0 : l2.val;
        	
        	int v = n1 + n2 + tmp;
        	if(v >= 10){
        		v -= 10;
        		tmp = 1;
        	} else{
        		tmp = 0;
        	}
        	
        	if(curPos == null){
        		curPos = new ListNode(v);
        	} else {
        		curPos.val = v;
        	}
        	
        	if(lastPos != null){
        		lastPos.next = curPos;
        	}
        	
        	
        	l1 = l1 == null? null: l1.next;
        	l2 = l2 == null? null: l2.next;
        	lastPos = curPos;
        	curPos = null;
        } 
        
        return head;
    }
    
    public static void main(String[] args){
    	ListNode n1 = new ListNode(2);
    	ListNode tmp = n1;
    	tmp.next = new ListNode(4); 
    	tmp = tmp.next;
    	tmp.next = new ListNode(3);
    	
    	ListNode n2 = new ListNode(5);
    	tmp = n2;
    	tmp.next = new ListNode(6); 
    	tmp = tmp.next;
    	tmp.next = new ListNode(4);
    	
    	Solution s = new Solution();
    	ListNode r = s.addTwoNumbers(n1, n2);
    	
    	while(r != null){
    		System.out.println(r.val);
    		r = r.next;
    	}
    	
    }
}
