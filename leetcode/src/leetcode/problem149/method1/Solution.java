package leetcode.problem149.method1;

import java.util.*;
import leetcode.commons.*;

class Solution {
    public int maxPoints(Point[] points) {
        if(points == null || points.length == 0) return 0;
        if(points.length == 1) return 1;
        
        Arrays.sort(points, new Comparator<Point>() {
			@Override
			public int compare(Point a, Point b) {
				if(a.x == b.x && a.y == b.y) {
					return 0;
				} else if (a.x < b.x || (a.x == b.x && a.y < b.y)) {
					return -1;
				} else {
					return 1;
				}
			}
        	
        });
        
        Map<String, Map<String, Integer>> map = new HashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        
        for(int i = 0; i < points.length; ++i){
            Point p1 = points[i];
            String pstr = p1.x + "," + p1.y;
            Integer cnt = countMap.get(pstr);
            if(cnt == null){
            	countMap.put(pstr, 1);
            } else {
            	countMap.put(pstr, cnt+1);
            	continue;
            }
            
            for(int j = i+1; j < points.length; ++j){
                Point p2 = points[j];
                calculate(p1, p2, map);
            }
        }
        
        int max = 0;
        
        for(Point p : points){
            String pstr = p.x + "," + p.y;
            int cnt = countMap.get(pstr);
            max = Math.max(max, cnt);
            
            Map<String, Integer> value = map.get(pstr);
            if(value == null) continue;
            
            for(Integer i : value.values()) {
                max = Math.max(max, i + cnt);
            }
        }
        
        return max;
    }
    
    void calculate(Point p1, Point p2, Map<String, Map<String, Integer>> map){
        int x = p2.x-p1.x;
        int y = p2.y-p1.y;
        
        String slope = null;
        if(x == 0 && y == 0){
            return;
        } else if(y == 0){
            slope = "1/0";
        } else if(x == 0){
            slope = "0/1";
        } else {
            int gcd = gcd(Math.abs(x), Math.abs(y));
            int xx = x / gcd;
            int yy = y / gcd;
            slope = xx + "/" + yy;
        }
        
        String pstr = p1.x + "," + p1.y;
        Map<String, Integer> m = map.get(pstr);
        if(m == null){
            m = new HashMap<String, Integer>();
            map.put(pstr, m);
        }
        
        Integer i = m.get(slope);
        if(i == null){
            i = 0;
        }
        m.put(slope, i + 1);  
    }
    
    int gcd(int x, int y){
        if(x == y){
            return x;
        }
        
        int a = x;
        int b = y;
        
        if(a < b){
            int t = a;
            a = b;
            b = t;
        }
        
        int remain = a % b; 
        while(remain > 0){
            a = b;
            b = remain;
            remain = a % b;
        }
        
        return b;
    }

	public static void main(String[] args) {
//		Point[] points = new Point[] {new Point(1,1), new Point(2,2), new Point(3,3)};
//		Point[] points = new Point[] {new Point(0,0), new Point(1,1), new Point(0,0)};
//		Point[] points = new Point[] {new Point(1,1), new Point(3,2), new Point(5,3), new Point(4,1), new Point(2,3), new Point(1,4)};
		
		int[][] a = {{0,9},{138,429},{115,359},{115,359},{-30,-102},{230,709},{-150,-686},{-135,-613},{-60,-248},{-161,-481},{207,639},{23,79},{-230,-691},{-115,-341},{92,289},{60,336},{-105,-467},{135,701},{-90,-394},{-184,-551},{150,774}};
		
		Point[] points = new Point[a.length];
		for(int i = 0; i < a.length; ++i) {
			points[i] = new Point(a[i][0], a[i][1]);
		}
		
		Solution s = new Solution();
		System.out.println(s.maxPoints(points));
	}

}
