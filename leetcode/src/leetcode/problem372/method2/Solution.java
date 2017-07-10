package leetcode.problem372.method2;

public class Solution {
    public int superPow(int a, int[] b) {
    	
    	if(b.length == 1){
    		if(b[0] == 1){
    			return a % 1337;
    		} else if(b[0] == 0){
    			return 1;
    		}
    	}
    	
    	int n = b.length;

    	int[] b1;
    	
    	if(b[0] > 1){
    		b1 = new int[n];
    	} else{
    		b1 = new int[n-1];
    	}
    	
    	int borrow = 0;
    	for(int i = 0, j =0; i < n; ++i){
    		int d = (b[i] + borrow * 10) / 2;
    		borrow = b[i] % 2;
    		
    		if(d == 0 && j == 0){
    			continue;
    		}
    		b1[j++] = d;
    	}

    	int r1 = this.superPow(a, b1);
    	int r = (r1 * r1) % 1337;
    	if(b[n-1] % 2 == 1){
    		r = ((a % 1337) * r) % 1337;
    	}
    	
    	return r;
    }
    
    
    
    public static void main(String[] args){
    	
    	int a = 2147483647;
    	int[] b = new int[]{2,0,0};
    	
    	Solution s = new Solution();
    	System.out.println(s.superPow(a, b));
    }
}
