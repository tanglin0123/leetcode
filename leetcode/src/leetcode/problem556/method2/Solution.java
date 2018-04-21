package leetcode.problem556.method2;

class Solution {
    public int nextGreaterElement(int num) {
        if(num < 10) return -1;
        
        char[] chs = ("" + num).toCharArray();
        
        for(int i = chs.length - 2; i >= 0 ; --i) {
        	char d = chs[i];
        	if(d < chs[i+1]) {
        		int idx = -1;
        		int idx2 = -1;
        		
        		int j,k;
        		for(j = chs.length - 1, k = i + 1; j >= k ; --j, ++k) {
        			if(chs[k] > d) {
        				idx2 = j;
        			}
        			if(idx == -1 && chs[j] > d) {
        				idx = k;
        			}
        			char tmp = chs[j];
        			chs[j] = chs[k];
        			chs[k] = tmp;
        		}
        		
        		if(idx == -1) {
        			idx = idx2;
        		}
        		chs[i] = chs[idx];
        		chs[idx] = d;
        		
        		String newS = new String(chs);
        		String maxS = Integer.MAX_VALUE + "";
        		if(newS.length() > maxS.length() || ( newS.length() == maxS.length() && newS.compareTo(maxS) > 0)) {
        			return -1;
        		}
        		return Integer.parseInt(newS);
        	} 
        }
 
        return -1;
    }


	public static void main(String[] args) {
		//int i = 12;
		//int i = 101;
		//int i = 230241;
//		int i = 12222333;
		int i = 1200000;
		//int i = 1999999999; // expected -1
		
		Solution s = new Solution();
		System.out.println(s.nextGreaterElement(i));

	}

}

