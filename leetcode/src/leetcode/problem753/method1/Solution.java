package leetcode.problem753.method1;

import java.util.*;

// BFS. time limit exceeded
class Solution {
    public String crackSafe(int n, int k) {
        if(n == 1){
            String s = "";
            for(int i = 0; i < k; ++i){
                s += i;
            }
            return s;
        }
        
        if(k == 1){
            String s = "";
            for(int i = 0; i < n; ++i){
                s += 0;
            }
            return s;
        }
        
        int max = (int)Math.pow(k, n);
        Map<String, Set<String>> candidates = new HashMap<>();
        
        String s = "";
        for(int i = 0; i < n; ++i){
            s += 0;
        }
        
        Set<String> visited = new HashSet<>();
        visited.add(s);
        candidates.put(s, visited);
        
        while(true){
        	System.out.println(candidates);
        	
            Map<String, Set<String>> newcandidates = new HashMap<>();
            
            for(Map.Entry<String, Set<String>> entry : candidates.entrySet()){
                String key = entry.getKey();
                String prefix = key.substring(key.length()-n+1, key.length());
                visited = entry.getValue();
                for(int c = 0; c < k; ++c){
                    String news = prefix + c;
                    if(!visited.contains(news)){
                    	String newkey = key+c;
                    	if(visited.size() == max-1){
                            return newkey;
                        }
                    	
                    	Set<String> newvisited = new HashSet<>(visited);
                        newvisited.add(news);
                        newcandidates.put(newkey, newvisited);
                    }
                }
            }
            
            candidates = newcandidates;
        }
        
    }

	public static void main(String[] args) {
		int n = 2, k = 5;
		
		Solution s = new Solution();
		System.out.println(s.crackSafe(n, k));

	}

}
