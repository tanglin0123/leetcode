package leetcode.problem502.method2;

import java.util.*;

//greedy
public class Solution {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
    	PriorityQueue<int[]> capQ = new PriorityQueue<int[]>(1, new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}});
    	
    	PriorityQueue<int[]> proQ = new PriorityQueue<int[]>(1, new Comparator<int[]>(){

			@Override
			public int compare(int[] o1, int[] o2) {
				return o2[1] - o1[1];
			}});
    	
    	for(int i = 0; i < Profits.length; ++i){
    		capQ.offer(new int[]{Capital[i], Profits[i]});
    	}

    	for(int i = 0; i < k ; ++i ){
    		while(!capQ.isEmpty() && capQ.peek()[0] <= W){
    			proQ.offer(capQ.poll());
    		}
    		
    		if(proQ.isEmpty()){
    			break;
    		}
    		
    		W+= proQ.poll()[1];
    	}
    	
    	return W;
    }
    
    


	public static void main(String[] args) {
		int k = 2;
		int w = 0;
		int[] pro = new int[]{1,2,3};
		int[] cap = new int[]{0,1,1};
		
		Solution s = new Solution();
		System.out.println(s.findMaximizedCapital(k, w, pro, cap));

	}

}
