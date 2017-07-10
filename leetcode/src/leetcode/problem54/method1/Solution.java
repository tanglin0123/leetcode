package leetcode.problem54.method1;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix==null||matrix.length==0){
            return new ArrayList<Integer>(0);
        }
        
        int xmax=matrix.length-1;
        int ymax=matrix[0].length-1;
        List<Integer> r = new ArrayList<Integer>((xmax+1)*(ymax+1));
        int xmin=0;
        int ymin=0;
        
        while(xmin<=xmax && ymin<=ymax){
            for(int i = ymin; i <= ymax; ++ i){
                r.add(matrix[xmin][i]);
            }
            xmin++;
            if(xmin>xmax){break;}
            
            for(int i= xmin; i<=xmax; ++i){
                r.add(matrix[i][ymax]);
            }
            ymax--;
            if(ymin>ymax){break;}
            
            for(int i = ymax; i>=ymin; --i){
                r.add(matrix[xmax][i]);
            }
            xmax--;
            if(xmin>xmax){break;}
            
            for(int i=xmax; i>=xmin; --i){
                r.add(matrix[i][ymin]);
            }
            ymin++;
            if(ymin>ymax){break;}
        }
        
        return r;
        
    }
    
    public static void main(String[] args){
    	int[][] m = new int[][]{{1,2},{3,4}};
    	Solution s = new Solution();
    	List<Integer> l = s.spiralOrder(m);
    	for (int i : l) {
			System.out.println(i);
		}      
    }
}