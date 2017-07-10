package leetcode.problem451.method1;

import java.util.*;


// Time Limit Exceeded
public class Solution {
    static class Freq{
        char c;
        int f;
        
        Freq(char c){
            this.c = c;
            this.f = 1;
        }
    }
    
    public String frequencySort(String s) {
        if(s == null || s.isEmpty()){
            return s;
        }
        
        char[] chs = s.toCharArray();
        
        Map<Character, Freq> map = new HashMap<Character, Freq>();
        
        for(char c : chs){
            Freq fr = map.get(c);
            if(fr == null){
                fr = new Freq(c);
                map.put(c, fr);
            } else{
                ++fr.f;
            }
        }
        
        PriorityQueue<Freq> q = new PriorityQueue<Freq>(1, new Comparator<Freq>(){
            public int compare(Freq f1, Freq f2){
                return f2.f-f1.f;
            }
        });
        
        for(Map.Entry<Character, Freq> en : map.entrySet()){
            q.offer(en.getValue());    
        }
        
        String r = "";
        Freq cur = q.poll();
        while(cur != null){
            for(int i = 0; i < cur.f; ++i){
                r+=cur.c;
            }
            
            cur = q.poll();
        }
        
        return r;
    }


	public static void main(String[] args) {
		
	}

}
