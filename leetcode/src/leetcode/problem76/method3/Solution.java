package leetcode.problem76.method3;

import java.util.*;

class Solution {
    public String minWindow(String s, String t) {
        if(s.length() < t.length() || t.isEmpty()){
            return "";
        }
        
        if(t.length() == 1) {
            return s.contains(t) ? t : "";
        }
        
        Map<Character, ArrayList<Integer>> posMap = new HashMap<>();
        Map<Character, Integer> idxMap = new HashMap<>();
        for(int i = 0; i < t.length(); ++i){
            char c = t.charAt(i);
            ArrayList<Integer> l = posMap.get(c);
            if(l == null){
                l = new ArrayList<Integer>();
                posMap.put(c, l);
                idxMap.put(c, 0);
            }
        }
        
        for(int i = 0; i < s.length(); ++i){
            char c = s.charAt(i);
            ArrayList<Integer> l = posMap.get(c);
            if(l != null){
                l.add(i);
            }
        }
        
        int max = Integer.MIN_VALUE;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(ArrayList<Integer> l : posMap.values()){
            if(l == null || l.isEmpty()){
                return "";
            }
            int pos = l.get(0); 
            pq.add(pos);  
            max = Math.max(max, pos);
        }
        
        int minlen = Integer.MAX_VALUE;
        int minpos = -1;
        int maxpos = -1;
        
        while(true){
            int pos = pq.poll();
            char c = s.charAt(pos);
            ArrayList<Integer> l = posMap.get(c);
            int idx = idxMap.get(c);
            
            int min = l.get(idx);
            int curlen = max - min + 1;
            if(curlen < minlen) {
                minlen = curlen;
                minpos = min;
                maxpos = max;
            }
            
            ++idx;
            if(idx == l.size()){
                break;
            }
            
            int next = l.get(idx);
            idxMap.put(c, idx);
            
            pq.add(next);
            max = Math.max(max, next);
        }
        
        return s.substring(minpos, maxpos+1);
    }


	public static void main(String[] args) {
		String s = "abc";
		String t = "ab";
		
		Solution sln = new Solution();
		
		System.out.println(sln.minWindow(s, t));

	}

}
