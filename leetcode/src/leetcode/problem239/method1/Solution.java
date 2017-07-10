package leetcode.problem239.method1;

import java.util.*;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null){
            return null;
        }
        
        int len = nums.length;
        
        if(len < k || k <= 0){
            return new int[0];
        }
        
        int[] r = new int[len-k+1]; 
        
        LinkedList<Integer> q = new LinkedList<Integer>();
        
        for(int i = 0; i < k; ++i ){
            int tailIdx = q.isEmpty() ? -1 : q.peekLast();
            while(tailIdx != -1 && nums[i] >= nums[tailIdx]){
                q.removeLast();
                tailIdx = q.isEmpty() ? -1 : q.peekLast();
            }
            
            q.add(i);
        }
        
        int headIdx = q.peek();
        
        r[0] = nums[headIdx];
        
        for(int i = k; i < len; ++i){
            int rmIdx = i - k;
            
            while(headIdx!= -1 && rmIdx >= headIdx) {
                q.removeFirst();
                headIdx = q.isEmpty()? -1 : q.peek();
            }
            
            int tailIdx = q.isEmpty() ? -1 : q.peekLast();
            while(tailIdx != -1 && nums[i] >= nums[tailIdx]){
                q.removeLast();
                tailIdx = q.isEmpty() ? -1 : q.peekLast();
            }
            
            q.add(i);
            
            headIdx = q.peek();
            r[i-k+1] = nums[headIdx];
        }
        
        return r;
    }
    
	public static void main(String[] args) {
		int[] a = {1, -1};
		int b = 1;
		
		
		Solution s = new Solution();
		for(int i : s.maxSlidingWindow(a, b) ) {
			System.out.print(i + ", ");
		}
	}

}
