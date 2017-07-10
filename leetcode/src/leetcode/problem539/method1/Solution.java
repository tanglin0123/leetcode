package leetcode.problem539.method1;

import java.util.*;

public class Solution {
    public int findMinDifference(List<String> timePoints) {
        if(timePoints == null || timePoints.size() <= 1){
            return 0;
        }
        Object[] t = timePoints.toArray();
        Arrays.sort(t);
        
        int min = 24 * 60;
        for(int i = 0; i < t.length; ++i){
            int j = (i + 1) % t.length;
            
            int gap;
            
            if(j != 0){
                gap = timeGap((String)t[i], (String)t[j]);
            } else {
                gap = timeGap((String)t[i], "24:00") + timeGap("00:00", (String)t[j]);
            }
            
            if(gap < min){
                min = gap;
            }
        }
        
        return min;
    }
    
    int timeGap(String s1, String s2){
        String[] ss1 = s1.split(":");
        String[] ss2 = s2.split(":");
            
        int h1 = Integer.parseInt(ss1[0]);
        int m1 = Integer.parseInt(ss1[1]);
        int h2 = Integer.parseInt(ss2[0]);
        int m2 = Integer.parseInt(ss2[1]);
        
        return 60*(h2-h1) + (m2-m1);
    }

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		String[] b = {"23:59", "00:00"};
		
		for(String c : b) {
			a.add(c);
		}
		
		Solution s = new Solution();
		System.out.println(s.findMinDifference(a));

	}

}
