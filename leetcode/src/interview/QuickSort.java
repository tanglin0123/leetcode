package interview;

import java.util.*;

public class QuickSort {
	public void sort(int[] nums){
		this.sort(nums, 0, nums.length -1);
	}
	
	public void sort2(int[] nums){
		this.sort2(nums, 0, nums.length -1);
	}
	
	// alibaba
	public void sortWithoutRecursive(int[] nums){
		if(nums == null || nums.length <= 1){
			return;
		}
		
		Stack<int[]> stk = new Stack<int[]>();
		
		stk.push(new int[]{0, nums.length-1});
		
		while(!stk.isEmpty()){
			int[] idx = stk.pop();
			
			int i = idx[0];
			int j = idx[1];
			
			int v = nums[i];
			
			while(i < j){
				while(j > i && nums[j] > v){
					--j;
				}
				
				if(j > i) {
					nums[i] = nums[j];
				}
				
				while(i < j && nums[i] <= v){
					++i;
				}
				
				if(i < j){
					nums[j] = nums[i];
				}
			}
			
			nums[i] = v;
			
			if(idx[0] < i-1){
				stk.push(new int[]{idx[0], i-1});
			}
			if(i+1 < idx[1]){
				stk.push(new int[]{i+1, idx[1]});
			}
		}
	}
	
	public void sort2(int[] nums, int start, int end){
		if(start >= end){
			return;
		}
		
		int i = start; 
		int j = end; 
		
		while(i < j){
			int v = nums[i];
			while(j > i && nums[j] > v){
				--j;
			}
			
			if(j > i) {
				nums[i] = nums[j];
				nums[j] = v; 
				++i;
			}
			
			
			while(i < j && nums[i] <= v){
				++i;
			}
			
			if(i < j) {
				nums[j] = nums[i];
				nums[i] = v;
				--j;
			}
			
		}
		
		sort2(nums, start, i -1);
		sort2(nums, i + 1, end);
	}
	
	public void sort(int[] nums, int start, int end){
		if(start >= end){
			return;
		}
		
		int i = start; 
		int j = end; 
		int key = nums[i];
		
		while(i < j){
			while(j > i && nums[j] > key){
				--j;
			}
			
			nums[i] = nums[j];
			
			while(i < j && nums[i] <= key){
				++i;
			}
			
			nums[j] = nums[i];
		}
		
		nums[i] = key;
		sort(nums, start, i -1);
		sort(nums, i + 1, end);
		
	}
	
	public static void main(String[] args) {
		int[] a = {1,5,2,3,2,6,4,7,10,5};

		QuickSort q = new QuickSort();
		
		q.sort(a);
		for(int i : a) {
			System.out.print(i + ", ");
		}
		
		q.sort2(a);
		for(int i : a) {
			System.out.print(i + ", ");
		}
		
		q.sortWithoutRecursive(a);
		for(int i : a) {
			System.out.print(i + ", ");
		}
	}

}
