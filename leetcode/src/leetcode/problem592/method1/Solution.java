package leetcode.problem592.method1;

import java.util.ArrayList;

class Solution {
    public String fractionAddition(String exp) {
        if(!exp.startsWith("-")){
            exp = "+" + exp;
        }
        
		String[] ss = exp.split("\\+|\\-");
		
		if(ss.length == 0){
			return "0/1";
		}
		
		ArrayList<Integer> up1 = new ArrayList<>(ss.length);
		ArrayList<Integer> down1 = new ArrayList<>(ss.length);
		
		ArrayList<Integer> up2 = new ArrayList<>(ss.length);
		ArrayList<Integer> down2 = new ArrayList<>(ss.length);
		
		int signIdx = 0;
		for(int i = 1; i < ss.length; ++i) {
			String s = ss[i];
			
			boolean pos = exp.charAt(signIdx) == '+'; 
			signIdx += s.length()+1;

			String[] ff = s.split("/");

			if(pos) {
				up1.add(Integer.parseInt(ff[0]));
				down1.add(Integer.parseInt(ff[1]));
			} else {
				up2.add(Integer.parseInt(ff[0]));
				down2.add(Integer.parseInt(ff[1]));
			}
		}
		
		int[] r1 = calculateN(0, up1.size()-1, up1, down1);
		int[] r2 = calculateN(0, up2.size()-1, up2, down2);
		
		int[] r = calculate2(r1[0], r1[1], -r2[0], r2[1]);
		return r[0] + "/" + r[1]; 
    }
    
    int[] calculateN(int start, int end, ArrayList<Integer> up, ArrayList<Integer> down) {
    	if(start > end){
            return new int[] {0, 1};
        }
        
        if(start == end) {
    		int g = gcd(up.get(start), down.get(start));
    		int u = up.get(start) / g;
    		int d = down.get(start) / g;
    		
    		return new int[] {u, d};
    	}
    	
    	int mid = (start + end) / 2;
    	int[] r1 = calculateN(start, mid, up, down);
    	int[] r2 = calculateN(mid+1, end, up, down);
    	
    	return calculate2(r1[0], r1[1], r2[0], r2[1]);
    }
    
    int[] calculate2(int u1, int d1, int u2, int d2) {
    	int u = u1 * d2 +  u2 * d1;
    	int d = d1 * d2;
    	
    	if(u == 0) return new int[] {0, 1};
    	
    	int g = gcd(u > 0? u : -u, d);
    	
    	u /= g;
    	d /= g;
    	
    	return new int[] {u, d};
    }
    
    int gcd(int a, int b) {
        if(b == a) return a;
        if(b > a) {
            int t = a;
            a = b;
            b = t;
        }
        if(b == 0) return a;
        return gcd(b, a % b);
    }

	public static void main(String[] args) {
//		String exp = "-1/2+1/2";
		String exp = "1/3-1/2";
		
		Solution s = new Solution();
		System.out.println(s.fractionAddition(exp));

	}

}
