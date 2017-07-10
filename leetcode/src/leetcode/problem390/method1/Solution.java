package leetcode.problem390.method1;

public class Solution {
    public int lastRemaining(int n) {
        if(n <= 1){
        	return n; 
        }
        
        int direct = 1;
        int gap = 2;
        int remainNum = n;
        int head = 1;
        int tail = n;
        
        while(remainNum>1){
        	int nextTail = head + direct * gap/2; // next tail
    		int nextHead = tail;
        	
    		if(Math.abs(tail-head) % gap == 0){ // cur tail is removed
    			nextHead = tail - direct*gap/2; // next head
    		} // else , cur tail is not removed
    	
        	head = nextHead;
        	tail = nextTail;
        	remainNum /= 2;
        	direct = -direct;
        	gap*=2;
        }
        
        return head;
    }
	
	
	public static void main(String[] args) {
		
		int n = 9;
		
		Solution s = new Solution();
		
		System.out.println(s.lastRemaining(n));

	}

}
