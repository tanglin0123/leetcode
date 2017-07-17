package interview;

import java.util.*;

public class Substring {

	public int maxNoDupSubstring(String s){
		if(s == null){
			return 0;
		}
		
		if(s.length() < 2){
			return s.length();
		}
		
		char[] chs = s.toCharArray();
		
		int totalMax = 0;
		int curMax = 0;
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(int i = 0; i < chs.length; ++i){
			char c = chs[i];
			Integer pre = map.get(c);
			
			if(pre == null){
				++curMax;
				if(curMax > totalMax){
					totalMax = curMax;
				}
			} else {
				int start = i - curMax; // actually it is i - 1 - curMax + 1
				if( pre >= start){
					curMax = i - pre;
				} else {
					++curMax;
					if(curMax > totalMax){
						totalMax = curMax;
					}
				}
			}
			
			map.put(c, i);
		}
		
		return totalMax;
	}
	
	
	// dp. pass but time limit exceeded.
	public String longestPalindrome_DP(String s){
		if(s == null){
			return s;
		}
		
		if(s.length() <= 1){
			return s;
		}
		
		char[] chs = s.toCharArray();
		
		int[][] dp = new int[chs.length+1][chs.length];
		
		for(int i = 0; i < chs.length; ++i){
			dp[0][i] = 0;
			dp[1][i] = 1;
		}
		
		int maxLen = 1;
		int maxStart = 0;
		
		boolean evenBreak = false;

		boolean oddBreak = false;
		
		for(int len = 2; len <= chs.length; ++len){
			int curMax = 0;
			int curMaxStart = -1;
			
			for(int i = 0 ; i <= chs.length - len; ++i ){
				if(dp[len-2][i+1] == len-2){
					if(chs[i] == chs[i+len-1]){
						curMax = len;
						curMaxStart = i;
						dp[len][i] = len;
					}
				}
			}
			
			if(curMax == 0){
				if(len % 2 == 0){
					evenBreak = true;
				} else {
					oddBreak = true;
				}
				
				if(oddBreak && evenBreak){
					break;
				}

			} else {
				maxLen = curMax;
				maxStart = curMaxStart;
			}
			
		}
		
		return new String(chs, maxStart, maxLen);
		
	}
	
	public String longestPalindrome(String s){
		if(s == null){
			return s;
		}
		
		if(s.length() <= 1){
			return s;
		}
		
		char[] chs = s.toCharArray();
		
		int maxLen = 0;
		int mid = -1;
		for(int i = 0; i < chs.length; ++i){
			int len = getPalindrome(chs, i, i);
			if(len > maxLen){
				maxLen = len;
				mid = i;
			}
			if(i+1 < chs.length){
				len = getPalindrome(chs, i, i+1);
				if(len > maxLen){
					maxLen = len;
					mid = i;
				}
			}
		}
		
		int start = maxLen % 2 == 0 ? mid - maxLen/2 + 1: mid - maxLen/2;
		
		return new String(chs, start, maxLen);
	}
	
	private int getPalindrome(char[] chs, int left, int right){
		while(left >= 0 && right < chs.length){
			if(chs[left] != chs[right]){
				break;
			}
			
			--left;
			++right;
		}
		
		return right - left - 1; // actually it's (right-left+1)-2 
	}
	
	public static void main(String[] args) {
		String a = "abceyecdazn";

		Substring s = new Substring();
		System.out.println(s.maxNoDupSubstring(a));
		
		String b = "zabcbbcbcefe";
		//String b = "bb";
		System.out.println(s.longestPalindrome(b));
	}

}
