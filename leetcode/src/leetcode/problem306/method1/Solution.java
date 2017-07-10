package leetcode.problem306.method1;


// pass. using recursive. considering using stack
public class Solution {
    public boolean isAdditiveNumber(String num) {
    	for(int num1end = 0; num1end < num.length()/2; ++ num1end){
    		int num1len = num1end + 1;
    		
    		if(num.charAt(0) == '0' && num1len != 1){
    			break;
    		}
    		
    		int num2start = num1end + 1;
    		int num2end = num2start;
    		int num2len = num2end - num2start + 1;

    		int remainlen = num.length() - num2len - num1len;
    		while(remainlen >= num1len && remainlen >= num2len){
    			if(num.charAt(num2start) == '0' && num2len != 1 ){
        			break;
        		}
    			
    			java.math.BigInteger num1 = new java.math.BigInteger(num.substring(0, num1end+1));
    			java.math.BigInteger num2 = new java.math.BigInteger(num.substring(num2start, num2end+1));
    			
    			if(isAdditive(num.substring(num2end+1), num1, num2)){
    				return true;
    			}
    			++num2end;
    			++num2len;
    			--remainlen;
    		}
 
    	}
 
    	return false;
    }
    
    boolean isAdditive(String num, java.math.BigInteger prenum1, java.math.BigInteger prenum2){
        java.math.BigInteger num1 = prenum2;
        java.math.BigInteger num2 = prenum1.add(prenum2);
        
        int num1len = num1.toString().length();
        int num2len = num2.toString().length();
        
        if(!num.startsWith(num2.toString())){
        	return false;
        } else {
        	if(num.length() == num2len){
        		return true;
        	}
        }
        
        int remainlen = num.length() - num2len;
        if(remainlen < num1len || remainlen < num2len){
        	return false;
        } else {
        	return isAdditive(num.substring(num2len), num1, num2);
        }
    }
    
    public static void main(String[] args){
    	//String num = "112358"; //t
    	//String num = "112359"; //f
    	//String num = "199100199"; // t
    	//String num = "0235813"; //f
    	
    	String num = "199001200"; // f
    	
    	Solution s = new Solution();
    	System.out.println(s.isAdditiveNumber(num));
    }
}
