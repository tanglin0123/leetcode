package leetcode.problem862.method1;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// time limit exceeded
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int len = A.length;
        
        long[] sum = new long[len+1];
        sum[0] = 0;
        
        for(int i = 1; i <= len; ++i){
            sum[i] = sum[i-1] + A[i-1];
        }
        
        int minlen = Integer.MAX_VALUE;
        for(int i = 1; i <= len; ++i){
            for(int j = i-1; j >= 0; --j){
                if(sum[i] - sum[j] >= K){
                    minlen = Math.min(i - j, minlen);
                    break;
                }
            }
        }
        
        return minlen == Integer.MAX_VALUE ? -1 : minlen;
    }
    
    
    static int[] getA1() {
    	try {
			List<String> lines = Files.readAllLines(Paths.get(new File("p862.txt").toURI()));
			String[] strs = lines.get(0).split(",");
			int[] r = new int[strs.length];
			for(int i = 0; i < strs.length; ++i) {
				r[i] = Integer.parseInt(strs[i]);
			}
			return r;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
	public static void main(String[] args) {
		int[] A = getA1(); 
		int K = 5837033;
		
		Solution s = new Solution();
		System.out.println(s.shortestSubarray(A, K));
	}
	
	
	

}
