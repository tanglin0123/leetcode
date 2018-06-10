package leetcode.problem658.method1;

import java.util.*;

class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        LinkedList<Integer> r = new LinkedList<>();
        
        int len = arr.length;
        
        if(x >= arr[len-1]){
            for(int i = len - k; i <= len - 1; ++i){
                r.add(arr[i]);    
            }
            return r;
        }
        
        if(x <= arr[0]){
            for(int i = 0; i < k; ++i){
                r.add(arr[i]);    
            }
            return r;
        }
        
        int s = 0;
        int e = len - 1;
        
        int m1 = -1;
        int m2 = -1;
        while(s <= e) {
            int m = (s + e) / 2;
            if(arr[m] == x){
                m1 = m;
                break;
            }
            
            if(arr[m] < x && m+1 < len && arr[m+1] > x) {
                m1 = m;
                m2 = m+1;
                break;
            }
            
            if(arr[m] > x && m-1 >= 0 && arr[m-1] < x) {
                m1 = m-1;
                m2 = m;
                break;
            }
            
            if(x < arr[m]){
                e = m - 1;
            } else {
                s = m + 1;
            }
        }
        
        int lp = m1;
        int rp = m2 == -1 ? m1+1 : m2;
        
        for(int n = 1; n <= k ; ++n){
            if(rp >= len || (lp >= 0 && Math.abs(arr[lp] - x) <= Math.abs(arr[rp] - x))){
                r.addFirst(arr[lp--]);
            } else {
                r.addLast(arr[rp++]);
            }
        }
        
        return r;
    }

	public static void main(String[] args) {
		int[] arr = {1,2,3,3,6,6,7,7,9,9};
		int k = 8;
		int x = 8;
		
		Solution s = new Solution();
		System.out.println(s.findClosestElements(arr, k, x));

	}

}
