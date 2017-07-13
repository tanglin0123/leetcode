package interview;

import java.util.*;

// alibaba
public class NonRecursiveQuickSort {
	public void quickSort(int[] nums) {
		if(nums == null || nums.length <= 1) {
			return;
		}
		
		Stack<int[]> stk = new Stack<int[]>();
		stk.push(new int[] {0, nums.length-1});
		
		while(!stk.isEmpty()) {
			
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
