package leetcode.problem93.method1;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> r = new LinkedList<String>();
        
        if(s.length()<4 || s.length()>12){
            return r;
        }
        
        validIp(s, 4, "", r);
        
        return r;
    }
    
    private void validIp(String s, int n, String prefix, List<String> r){
    	if(s.startsWith("0")){
    		return;
    	}
    	
        if(s.length()<n || s.length()>3*n){
            return;
        }
        
        if(n == 1){
            int i = Integer.parseInt(s);
            if(i >=0 && i<=255){
                r.add(prefix + i);
                return;
            } else {
                return;
            }
        }
        
        for(int i = 1; i <= s.length() && i <=3; ++i){
        	String scut = s.substring(0,i);
            int cut = Integer.parseInt(scut);
            if(cut>=0 && cut <=255){
                validIp(s.substring(i), n-1, prefix + scut+"." , r);
            }
        }
        
    }
    
    public static void main(String[] args){
    	Solution s = new Solution();
    	List<String> r = s.restoreIpAddresses("0000");
    	
    	for(String str : r){
    		System.out.println(str);
    	}
    	
    }
}