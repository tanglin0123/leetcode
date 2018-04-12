package interview;

import java.util.*;

import leetcode.commons.*;

// amazon intern review
public class MergeSortedLinkedList {

	public ListNode mergeKLists(ListNode[] lists) {
		if(lists == null || lists.length == 0) {
			return null;
		}
		
		if(lists.length == 1) {
			return lists[0];
		}
		
		return mergeLists(lists, 0, lists.length - 1);
	}
	
	ListNode mergeLists(ListNode[] lists, int start, int end) {
		if(start == end) {
			return lists[start];
		}
		
		if(end - start == 1) {
			return merge2Lists(lists[start], lists[end]);
		}
		
		int mid = (end - start) / 2;
		return merge2Lists(mergeLists(lists, start, mid), mergeLists(lists, mid + 1, end));
	}
	
	ListNode merge2Lists(ListNode list1, ListNode list2) {
		if(list1 == null) return list2;
		if(list2 == null) return list1;
		
		ListNode pHead = new ListNode(0);
		ListNode p = pHead;
		ListNode h1 = list1;
		ListNode h2 = list2;
		
		while(h1 != null && h2 != null) {
			if(h1.val < h2.val) {
				p.next = h1;
				h1 = h1.next;
			} else {
				p.next = h2;
				h2 = h2.next;
			}
			p = p.next;
		}
		
		if(h1 == null) p.next = h2;
		else p.next= h1;
		
		return pHead.next;
	}
	
	public ListNode mergeKLists_2(ListNode[] lists) {
		if(lists == null || lists.length == 0) {
			return null;
		}
		
		if(lists.length == 1) {
			return lists[0];
		}
		
		ListNode pHead = new ListNode(0);
		ListNode p = pHead;
		
		PriorityQueue<ListNode> heap = new PriorityQueue<>(new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				return o1.val - o2.val;
			}
		}); 
		
		for(ListNode h : lists) {
			if(h != null) {
				heap.add(h);
			}
		}
		
		while(heap.size() > 0) {
			ListNode h = heap.poll();
			
			p.next = h;
			p = p.next;
			
			if(h.next != null) {
				heap.offer(h.next);
			}
		}
		
		return pHead.next;
		
	}
	
	
	
	public static void main(String[] args) {
		

	}

}
