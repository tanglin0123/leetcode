package interview;

import java.util.*;

// amazon interview
public class ThreeStrings {
	
	// given 3 strings, a,b,c, rules: 
	// 1) chars in c are from a and b
	// 2) every char in a and b exists in c and only once
	// 3) in c, the relatively order between 2 chars from a are maintained, 
	// so are with chars from b
	// validate if the given 3 strings obey the rules
	public boolean validate_dp(String a, String b, String c) { // some like a state machine
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
	
	// backtrace. O(2^n) time??
	public boolean validate_bt(String a, String b, String c){
		if(c == null || c.isEmpty()){ // it depends on the rule
			return false;
		}
		
		int alen = a == null ? 0 : a.length();
		int blen = b == null ? 0 : b.length();
		int clen = c.length();
		
		if(clen != alen + blen){
			return false;
		}
		
		if(a == null){
			return c.equals(b);
		}
		
		if(b == null){
			return c.equals(a);
		}

		return backtrace(a, b, c, 0, 0, 0);
	} 
	
	private boolean backtrace(String a, String b, String c, int astart, int bstart, int cstart){
		int alen = a.length();
		int blen = b.length();
		
		if(astart == alen){ // a got end
			return c.substring(cstart).equals(b.substring(bstart));
		}
		
		if(bstart == blen){ // b got end
			return c.substring(cstart).equals(a.substring(astart));
		}
		
		char ach = a.charAt(astart);
		char bch = b.charAt(bstart);
		char cch = c.charAt(cstart);
		
		if(cch != ach && cch != bch){
			return false;
		}
		
		if(cch == ach && cch != bch){
			return backtrace(a, b, c, astart + 1, bstart, cstart + 1);
		}
		
		if(cch == bch && cch != ach){
			return backtrace(a, b, c, astart, bstart + 1, cstart + 1);
		}

		// cch == ach && cch == bch
		boolean aok = backtrace(a, b, c, astart + 1, bstart, cstart + 1);
		if(aok){
			return true;
		}
		
		return backtrace(a, b, c, astart, bstart + 1, cstart + 1);
	}
	
	// without the rule of keep relative order
	public boolean validate_simple(String a, String b, String c){
		if(c == null || c.isEmpty()){ // it depends on the rule
			return false;
		}
		
		int alen = a == null ? 0 : a.length();
		int blen = b == null ? 0 : b.length();
		int clen = c.length();
		
		if(clen != alen + blen){
			return false;
		}
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		if(a != null){
			for(int i = 0; i < alen; ++i){
				char ch = a.charAt(i);
				Integer n = map.get(ch);
				if(n == null){
					n = 0;
				}
				map.put(ch, ++n);
			}
		}
		
		if(b != null){
			for(int i = 0; i < blen; ++i){
				char ch = b.charAt(i);
				Integer n = map.get(ch);
				if(n == null){
					n = 0;
				}
				map.put(ch, ++n);
			}
		}
		
		for(int i = 0; i < clen; ++i){
			char ch = c.charAt(i);
			Integer n = map.get(ch);
			if(n == null || n == 0){
				return false;
			}
			map.put(ch, --n);
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
		
		
		String a = "abcg";
		String b = "cdeg";
		String c = "abcdecgg";
	
//		String a = "abg";
//		String b = "cdg";
//		String c = "abbcgg";
		
//		String a = "abg";
//		String b = "cdg";
//		String c = "abdcgg";
		
		
		ThreeStrings t = new ThreeStrings();
		System.out.println(t.validate_dp(a, b, c));
		System.out.println(t.validate_bt(a, b, c));
		System.out.println(t.validate_simple(a, b, c));
	}

}
