package tanglin.test;

public class ListNode {
	int val;
	ListNode next;
	public ListNode(int v){
		val = v;
	}
	
	public static ListNode reverse(ListNode head){
		if(head == null || head.next == null){
			return null;
		}
		
		ListNode remainhead = head;
		ListNode newhead = null;
		
		while(remainhead != null){
			ListNode p = remainhead.next;
			remainhead.next = newhead;
			newhead = remainhead;
			remainhead = p;
		}
		
		return newhead;
	}
	
	public static void main(String[] args){
		ListNode head = new ListNode(0);
		ListNode cur = head;
		for(int i = 1; i < 5; ++i){
			ListNode tmp = new ListNode(i);
			cur.next = tmp;
			cur = tmp;
		}
		
		cur = head;
		while(cur != null){
			System.out.println(cur.val);
			cur = cur.next;
		}
		
		cur = reverse(head);
		while(cur != null){
			System.out.println(cur.val);
			cur = cur.next;
		}
	}
}
