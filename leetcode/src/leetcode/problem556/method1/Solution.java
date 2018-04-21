package leetcode.problem556.method1;

import java.util.*;

// failed at  1999999999; expected -1
class Solution {
    public int nextGreaterElement(int num) {
        if(num < 10) return -1;
        
        int last = num % 10;
        int mask = 10;
        int mask2 = 100;
        
        List<Integer> list = new ArrayList<>((num + "").length());
        list.add(last);
        
        while(num / mask > 0) {
            int d = (num % mask2)/mask;
            int size = list.size();
            int preD = list.get(size-1);
            
            if(d < preD){
            	int ret = num - (num % mask2);
                int acc = 0;
                int i = 0;
                for(; i < size && list.get(i) <= d; ++i){
                    acc = acc * 10 + list.get(i);
                }
                int d2 = list.get(i++);
                acc = acc * 10 + d;
                for(; i < size; ++i){
                    acc = acc * 10 + list.get(i);
                }
                return ret + d2 * (int)Math.pow(10, size) + acc;
            } else {
                list.add(d);
            }

            mask *= 10;
            mask2 *= 10;
        }
        
        return -1;
    }


	public static void main(String[] args) {
		//int i = 12;
		//int i = 101;
		int i = 230241;
		//int i = 1999999999; // expected -1
		
		Solution s = new Solution();
		System.out.println(s.nextGreaterElement(i));

	}

}
