package leetcode.problem6.method1;

public class Solution {
    public String convert(String s, int numRows) {
    	if(s == null || s.isEmpty() || numRows == 1) {
    		return s;
    	}
    	
        int fullGroupColNum = numRows - 1;
        int fullGroupSize = fullGroupColNum * 2;
        
        int fullGroupNum = s.length() / fullGroupSize;
        
        int lastGroupSize = s.length() % fullGroupSize;
        
        int lastGroupColNum;
        
        if(lastGroupSize == 0) {
        	lastGroupColNum = 0;
        } else if(lastGroupSize <= fullGroupColNum) {
        	lastGroupColNum = 1;
        } else {
        	lastGroupColNum = fullGroupColNum - (fullGroupSize - lastGroupSize);
        }
        
        int numCols = fullGroupColNum * fullGroupNum + lastGroupColNum ;
        
        char[][] chars = new char[numRows][numCols];
        
        int row = 0;
        int col = 0;
        boolean down = true;
        for(int i = 0; i < s.length(); ++i) {
        	char c = s.charAt(i);
        	chars[row][col] = c;
        	
        	if(down) {
        		if(row < numRows - 1) {
        			row ++;
        		} else {
        			row --;
        			col ++;
        			down = false;
        		}
        	} else {
        		if(row > 0) {
        			row --;
        			col ++;
        		} else {
        			row ++;
        			down = true;
        		}
        	}
        }
    	
        int i = 0;
        char[] chs = new char[s.length()];
        for(row = 0; row < chars.length; ++row) {
        	for(col = 0; col < chars[0].length; ++col) {
        		if(chars[row][col] != '\0') {
        			chs[i++] = chars[row][col];
        		}
        	}
        }
        
        return new String(chs);
    }
	

	public static void main(String[] args) {
		Solution s = new Solution();
		
		System.out.println(s.convert("PAYPALISHIRING", 3));

	}

}
