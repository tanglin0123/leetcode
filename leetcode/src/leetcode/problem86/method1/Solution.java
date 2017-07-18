package leetcode.problem86.method1;

import leetcode.commons.*;

// mistake understanding
public class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode newh = new ListNode(Integer.MIN_VALUE);
        newh.next = head;
        
        ListNode smallPre = newh;
        ListNode cur = head;
        
        ListNode bigHead = new ListNode(Integer.MAX_VALUE);
        ListNode bigPre = bigHead;
        int count = 0;
        while(cur != null){
            
            ListNode nextBig = cur;
            
            while(nextBig != null && nextBig.val <= x){
                ++count;
                if(count == x){
                    break;
                }
                smallPre = nextBig;
                nextBig = nextBig.next;
            }
            
            if(nextBig == null || count == x){
                break;
            }
            
            bigPre.next = nextBig;
            bigPre = nextBig;
            smallPre.next = nextBig.next;
            cur = smallPre.next;
        }
        
        if(bigHead.next == null){
            return newh.next;
        }
        
        ListNode temp = smallPre.next;
        smallPre.next = bigHead.next;
        bigPre.next = temp;
        
        return newh.next;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
