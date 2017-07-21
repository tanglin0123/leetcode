package interview;

import java.util.*;

// amazon interview
public class ThreeStrings {
	public boolean validate_dp(String a, String b, String c) {
		if(c == null || c.isEmpty()) {
			return false;
		}
		
		if(a == null || a.isEmpty()) {
			return c.equals(b);
		}
		
		if(b == null || b.isEmpty()) {
			return c.equals(a);
		}
		
		Set<String> dp = new HashSet<String>();
		dp = new HashSet<String>();
		dp.add(0 + "," + 0);
		
		for(int i = 1; i <= c.length(); ++i){
			char ch = c.charAt(i-1);
			
			Set<String> newdp = new HashSet<String>();
			
			for(String s : dp){
				
				String[] ss = s.split(",");
				int apos = Integer.parseInt(ss[0]);
				int bpos = Integer.parseInt(ss[1]);
				
				char acand = apos == a.length() ? 0 : a.charAt(apos);
				char bcand = bpos == b.length() ? 0 : b.charAt(bpos);
				
				if(ch == acand){
					newdp.add((apos + 1) + "," +  bpos);
				} 
				
				if(ch == bcand){
					newdp.add(apos + "," + (bpos + 1));
				} 
				
			}
			
			if(newdp.isEmpty()){
				return false;
			}
			
			dp = newdp;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
//		String a = "abczg";
//		String b = "czdeg";
//		String c = "abczdeczgg";
		
//		String a = "bc";
//		String b = "bc";
//		String c = "bbcc";
		
		
//		String a = "abcg";
//		String b = "cdeg";
//		String c = "abcdecgg";
		
		String a = "abg";
		String b = "cdg";
		String c = "abdcgg";
		
		ThreeStrings t = new ThreeStrings();
		System.out.println(t.validate_dp(a, b, c));
	}

}
