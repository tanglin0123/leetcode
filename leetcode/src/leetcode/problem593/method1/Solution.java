package leetcode.problem593.method1;

import java.util.*;

class Solution {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[] s = p1;
        long f = 0;
        int[] farest = null;
        List<int[]> bb = new ArrayList<>(2); 
        for(int[] i : new int[][]{p2, p3, p4}){
            long dist = (s[0] - i[0]) * (s[0] - i[0]) + (s[1] - i[1]) * (s[1] - i[1]);
            if(dist > f){
            	if(farest != null){
                    bb.add(farest);
                }
                farest = i;
                f = dist;
            } else {
                bb.add(i);
            }
        }
        
        if(f == 0) return false;
        
        int[] c = bb.get(0);
        int[] d = bb.get(1);
        
        long dist1 = (s[0] - c[0]) * (s[0] - c[0]) + (s[1] - c[1]) * (s[1] - c[1]);
        long dist2 = (farest[0] - c[0]) * (farest[0] - c[0]) + (farest[1] - c[1]) * (farest[1] - c[1]);
        long dist3 = (d[0] - c[0]) * (d[0] - c[0]) + (d[1] - c[1]) * (d[1] - c[1]);
        long dist4 = (d[0] - s[0]) * (d[0] - s[0]) + (d[1] - s[1]) * (d[1] - s[1]);
        
        if(dist3 == f && f == dist1 + dist2 && dist1 == dist2 && dist4 == dist1){
            return true;
        }
        
        return false;
    }

	public static void main(String[] args) {
		int[][] p = {{0,0}, {1,1}, {1,0}, {0,1}};
		
		Solution s = new Solution();
		System.out.println(s.validSquare(p[0], p[1], p[2], p[3]));

	}

}
