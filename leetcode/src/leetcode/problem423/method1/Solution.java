package leetcode.problem423.method1;

import java.util.*;

// backtrace. java.lang.StackOverflowError
public class Solution {
    String[] nums = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    
    public String originalDigits(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for(char c : s.toCharArray()){
            Integer i = map.get(c);
            if(i == null){
                i = 1;
            } else {
                i += 1;
            }
            
            map.put(c, i);
        }
        
        List<Integer> r = new ArrayList<Integer>();
        
        getResult(map, r, 0);
        
        String str = "";
        for(int i : r){
        	str+=i;
        }
        return str;
    }
    
    
    
    boolean getResult(Map<Character, Integer> map, List<Integer> r, int idx){
        boolean allDone = true;
        
        for(int cnt : map.values()){
            if(cnt!=0){
                allDone = false;
                break;
            }
        }
        
        if(allDone){
            return true;
        }
        
        for(int i = idx; i <= 9; ++i){
            
            boolean haveAll = true;
            for(char c : nums[i].toCharArray() ){
                Integer cnt = map.get(c);
                if(cnt == null || cnt == 0 ){
                    haveAll = false;
                    break;
                }
            }
            
            if(!haveAll){
                continue;
            }
            
            for(char c : nums[i].toCharArray() ){
                int cnt = map.get(c) - 1;
                map.put(c, cnt);
            }
            
            r.add(i);
            
            boolean succ = getResult(map, r, i);
            if(succ){
                return true;
            }
            
            r.remove(r.size()-1);
            
            for(char c : nums[i].toCharArray() ){
                int cnt = map.get(c) + 1;
                map.put(c, cnt);
            }
        }
        
        return false;
    } 
    

	public static void main(String[] args) {
		//String a = "owoztneoer";
		String a = "fviefuro";
		
		Solution s = new Solution();
		System.out.println(s.originalDigits(a));

	}

}
