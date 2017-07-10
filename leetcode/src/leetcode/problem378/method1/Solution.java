package leetcode.problem378.method1;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public int kthSmallest(int[][] matrix, int k) {
    	int n = matrix.length;
    	
    	if(k == 1){
        	return matrix[0][0];
        }
        
        if(k == n*n){
        	return matrix[n-1][n-1];
        }
        
        boolean[][] visited = new boolean[n][n];
        
        int k2 = n*n - k + 1;
        
        if(k <= k2){
        	PriorityQueue<Num> pq = new PriorityQueue<Num>(); 
            
            int count = 1;
            int r = 0; 
            int c = 0;
            while(count < k){
            	List<Num> candidates = new ArrayList<Num>();
            	
            	if(r < n-1 && !visited[r+1][c]){
            		candidates.add(new Num(matrix[r+1][c], r+1, c));
            		visited[r+1][c] = true;
            	} 
            	
            	if(c < n-1 && !visited[r][c+1]){
            		candidates.add(new Num(matrix[r][c+1], r, c+1));
            		visited[r][c+1] = true;
            	} 
            	
            	if(!pq.isEmpty()){
            		candidates.add(pq.poll());
            	}
            	
            	Num min = candidates.get(0);
            	for(int i = 1; i < candidates.size(); ++i){
            		Num cur = candidates.get(i);
            		if(min.value > cur.value){
            			min = cur;
            		}
            	}
            	
            	for(int i = 0; i < candidates.size(); ++i){
            		Num cur = candidates.get(i);
            		if(cur.c != min.c || cur.r != min.r){
            			pq.add(cur);
            		}
            	}
            	
            	c = min.c;
            	r = min.r;
            	count++;
            }
            
            return matrix[r][c];
            
        } else {
        	k = k2;
        	
        	PriorityQueue<Num2> pq2 = new PriorityQueue<Num2>(); 
            
            int count = 1;
            int r = n-1; 
            int c = n-1;
            
            visited[r][c] = true;
            
            while(count < k){
            	List<Num2> candidates = new ArrayList<Num2>();
            	
            	if(r > 0 && !visited[r-1][c]){
            		candidates.add(new Num2(matrix[r-1][c], r-1, c));
            		visited[r-1][c] = true;
            	} 
            	
            	if(c > 0 && !visited[r][c-1]){
            		candidates.add(new Num2(matrix[r][c-1], r, c-1));
            		visited[r][c-1] = true;
            	} 
            	
            	if(!pq2.isEmpty()){
            		candidates.add(pq2.poll());
            	}
            	
            	Num2 max = candidates.get(0);
            	for(int i = 1; i < candidates.size(); ++i){
            		Num2 cur = candidates.get(i);
            		if(max.value < cur.value){
            			max = cur;
            		}
            	}
            	
            	for(int i = 0; i < candidates.size(); ++i){
            		Num2 cur = candidates.get(i);
            		if(cur.c != max.c || cur.r != max.r){
            			pq2.add(cur);
            		}
            	}
            	
            	c = max.c;
            	r = max.r;
            	count++;
            }
            
            return matrix[r][c];
        }

    }
    
    public static class Num implements Comparable<Num>{
    	int value; 
    	int r; 
    	int c;
    	
    	Num(int value, int r, int c){
    		this.value = value;
    		this.r = r;
    		this.c = c;
    	}
    	
		@Override
		public int compareTo(Num o) {
			return this.value - o.value;
		}
    	
    }
    
    public static class Num2 implements Comparable<Num2>{
    	int value; 
    	int r; 
    	int c;
    	
    	Num2(int value, int r, int c){
    		this.value = value;
    		this.r = r;
    		this.c = c;
    	}
    	
		@Override
		public int compareTo(Num2 o) {
			return o.value - this.value;
		}
    	
    }
    
    public static void main(String[] args){
//    	int[][] matrix = new int[][] {{1,3,5},{6,7,12},{11,14,14}};
//    	int k = 4;
    	
//    	int[][] matrix = new int[][] {{1,3,5,7,9},{2,4,6,8,10},{11,13,15,17,19},{12,14,16,18,20},{21,22,23,24,25}};
//    	int k = 13;
    	
    	int[][] matrix = new int[][] {{1,1,3,8,13},{4,4,4,8,18},{9,14,18,19,20},{14,19,23,25,25},{18,21,26,28,29}};
    	int k = 13;
    	
//    	int[][] matrix = new int[][] {{1,2},{1,3}};
//    	int k = 3;
    	
    	Solution s = new Solution();
    	System.out.println(s.kthSmallest(matrix, k));
    }
}
