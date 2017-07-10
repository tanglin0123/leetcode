package leetcode.problem218.method1;

import java.util.*;

public class Solution {
	public List<int[]> getSkyline(int[][] buildings) {
		if (buildings == null || buildings.length == 0) {
			return new ArrayList<int[]>(0);
		}

		List<int[]> hlines = new ArrayList<int[]>(); // startpos, endpos, height
		
		hlines.add(new int[]{buildings[0][0], Integer.MAX_VALUE, 0});
		
		for (int[] b : buildings) {
			insertHLine(hlines, b);
		}

		List<int[]> r = new ArrayList<int[]>(buildings.length * 2);
		for (int[] line : hlines) {
			r.add(new int[] { line[0], line[2] });
		}

        int[] lastLine = hlines.get(hlines.size() - 1);
        if(lastLine[1] == Integer.MAX_VALUE && lastLine[2] > 0){
 	    	r.add(new int[] { lastLine[1], 0 });
        }
        
		return r;
	}
	
	private int mergeSameHeight(List<int[]> hlines, int i){
		if(i<0 || i + 1 >= hlines.size()){
			return 0;
		}
		
		int[] h1 = hlines.get(i);
		int[] h2 = hlines.get(i+1);
		if(h1[2] == h2[2]){
			h1[1] = h2[1];
			hlines.remove(i+1);
			return 1;
		}
		
		return 0;
	}

	private void insertHLine(List<int[]> hlines, int[] line) {

//		if (hlines.isEmpty()) {
//			hlines.add(line);
//			return;
//		}

		int i = hlines.size() - 1;
		while (i >= 0) {
			int[] h = hlines.get(i);
			if (h[0] <= line[0]) {
				break;
			}
			--i;
		}

		while (i < hlines.size()) {
			int[] h = hlines.get(i);
			
			if(h[2] >= line[2]){
				if(h[1] >= line[1]){
					return;
				} else {
					line[0] = h[1];
				}
				
			} else { // h[2] < line[2]
				if(h[1]==line[1]){
					if(h[0] == line[0]){
						h[2] = line[2];
						int merged = this.mergeSameHeight(hlines, i-1); 
						this.mergeSameHeight(hlines, i-merged);
							
						return;
					} else if(h[0] < line[0]){
						h[1] = line[0];
						hlines.add(i+1, line);
						this.mergeSameHeight(hlines, i+1);
						return;
					} else { // h[0] > line[0]
						// no this case
					}
					
				} else if(h[1]<line[1]){
					if(h[0] == line[0]){
						h[2] = line[2];
						line[0] = h[1];
						int merged = this.mergeSameHeight(hlines, i-1);  
						merged += this.mergeSameHeight(hlines, i-merged);
							
						i-=merged; 
					} else if(h[0] < line[0]){
						int tmp = h[1];
						int[] newline = new int[]{line[0], tmp, line[2]};
						h[1] = line[0];
						line[0]=tmp;
						hlines.add(i+1, newline);
						this.mergeSameHeight(hlines, i+1);
						++i;
					} else { // h[0] > line[0]
						// no this case
					}
					
					
				} else { // h[1] > line[1]
					if(h[0] == line[0]){
						h[0] = line[1];
						hlines.add(i, line);
						this.mergeSameHeight(hlines, i-1);
						return; 
					} else if(h[0] < line[0]){
						
						int[] newline = new int[]{line[1], h[1], h[2]};
						h[1] = line[0];
						hlines.add(i+1, line);
						hlines.add(i+2, newline);
						return;
					} else { // h[0] > line[0]
						// no this case
					}
					
				}
			}
			
			++i;

		}
		hlines.add(line);
		this.mergeSameHeight(hlines, hlines.size()-2);
	}

	public static void main(String[] args) {
		 //int[][] in = { { 2, 4, 70 }, { 3, 8, 30 }, { 6, 100, 41 },{ 7, 15, 70 }, { 10, 30, 102 }, { 15, 25, 76 }, { 60, 80, 91 },{ 70, 90, 72 }, { 85, 120, 59 } };
		// int[][] in = new int[][]{{0,5,7},{5,10,7},{5,10,12},{10,15,7},{15,20,7},{15,20,12},{20,25,7}};
		//int[][] in = new int[][]{{0,1,3}};
		//int[][] in = new int[][]{{0,2147483647,2147483647}};
		int[][] in = new int[][]{{2,13,10},{10,17,25},{12,20,14}};
		
		List<int[]> out = new Solution().getSkyline(in);

		for (int[] o : out) {
			System.out.print("[" + o[0] + "," + o[1] + "],");
		}
	}

	// private void insertVLine(List<int[]> vlines, int[] line) {
	//
	// for (int i = vlines.size() - 1; i >= 0; --i) {
	// int[] v = vlines.get(i);
	// if (v[0] < line[0]) {
	// vlines.add(i + 1, line);
	// return;
	// }
	//
	// if (v[0] == line[0]) {
	// if (line[1] > v[1]) {
	// vlines.set(i, line);
	// }
	// return;
	// }
	// }
	//
	// vlines.add(0, line);
	// }

}
