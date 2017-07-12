package leetcode.problem221.method1;

public class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix==null || matrix.length == 0 || matrix[0].length==0){
        	return 0;
        }
        
        int h = matrix.length;
        int w = matrix[0].length;
        
        int maxSize = 0;
        int[][] max = new int[h][w];
        for(int i = 0; i < h; ++i){
        	for(int j= 0; j < w; ++j){
        		if(matrix[i][j]=='1'){
        			if(i>0 && j >0){
        				int tmp =max[i-1][j-1]; 
        				max[i][j] = tmp + 1;
        				boolean newSq = false;
        				int k = 1;
        				for(; k <= tmp ; ++k){
        					if(matrix[i-k][j]=='0'){
        						newSq = true;
        						break;
        					}
        					if(matrix[i][j-k]=='0'){
        						newSq = true;
        						break;
        					}
        				}
        				if(newSq){
        					max[i][j] = k;
        				}
        				if(max[i][j] > maxSize){
        					maxSize = max[i][j];
        				}
        			} else { // i==0 || j ==0
        				max[i][j] = 1;
        				if(max[i][j] > maxSize){
        					maxSize = max[i][j];
        				}
        			}
        			 
        		} else { // matrix[i][j]=='0'
        			max[i][j]=0;
        			
        		}
        	}
        }
        
        return maxSize*maxSize;
    }
    
    public static void main(String[] args){
//    	String[] in = {"10100","10111","11111","10010"};
    	String[] in = {"0001","1101","1111","0111","0111"};
    	
    	int h = in.length;
    	int w = in[0].length();
    	
    	char[][] matrix = new char[h][w];
    	for(int i = 0; i < h; ++ i ){
    		for(int j = 0; j < w; ++j){
    			matrix[i][j] = in[i].charAt(j);
    		}
    	}
    	
    	System.out.println(new Solution().maximalSquare(matrix));
    }
}