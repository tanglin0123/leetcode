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
	public boolean validate_broadFirst(String a, String b, String c) { 
		if(c == null || c.isEmpty()) {
			return false;
		}
		
		if(a == null || a.isEmpty()) {
			return c.equals(b);
		}
		
		if(b == null || b.isEmpty()) {
			return c.equals(a);
		}
		
		Set<String> posibilities = new HashSet<String>();
		posibilities = new HashSet<String>();
		posibilities.add(0 + "," + 0);
		
		for(int i = 1; i <= c.length(); ++i){
			char ch = c.charAt(i-1);
			
			Set<String> newPosibilities = new HashSet<String>();
			
			for(String s : posibilities){
				
				String[] ss = s.split(",");
				int apos = Integer.parseInt(ss[0]);
				int bpos = Integer.parseInt(ss[1]);
				
				char aCandidate = apos == a.length() ? 0 : a.charAt(apos);
				char bCandidate = bpos == b.length() ? 0 : b.charAt(bpos);
				
				if(ch == aCandidate){
					newPosibilities.add((apos + 1) + "," +  bpos);
				} 
				
				if(ch == bCandidate){
					newPosibilities.add(apos + "," + (bpos + 1));
				} 
				
			}
			
			if(newPosibilities.isEmpty()){
				return false;
			}
			
			posibilities = newPosibilities;
		}
		
		return true;
	}
	
	// deep first. O(2^n) time??
	public boolean validate_deepFirst(String a, String b, String c){
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

		return validate_deepFirst(a, b, c, 0, 0, 0);
	} 
	
	private boolean validate_deepFirst(String a, String b, String c, int astart, int bstart, int cstart){
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
			return validate_deepFirst(a, b, c, astart + 1, bstart, cstart + 1);
		}
		
		if(cch == bch && cch != ach){
			return validate_deepFirst(a, b, c, astart, bstart + 1, cstart + 1);
		}

		// cch == ach && cch == bch
		return validate_deepFirst(a, b, c, astart + 1, bstart, cstart + 1) ||
				validate_deepFirst(a, b, c, astart, bstart + 1, cstart + 1);
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
	
	public boolean validate_dp(String a, String b, String c) { 
		int alen = a.length();
		int blen = b.length();
		int clen = c.length();
		
		boolean[][] dp = new boolean[alen+1][blen+1];
		
		dp[0][0] = true;
		
		for(int i = 1; i <= clen; ++i) {
			char ch = c.charAt(i-1);
			
			boolean found = false;
			
			for(int al = 0; al <= alen && al <= i; ++al) {
				int bl = i - al;
				if(bl > blen) {
					continue;
				}
				if(al == 0 && bl-1 < blen) {
					dp[al][bl] = dp[0][bl-1] && (b.charAt(bl-1) == ch);
				} else if(bl == 0 && al-1 < alen) {
					dp[al][bl] = dp[al-1][0] && (a.charAt(al-1) == ch);
				} else {
					dp[al][bl] = (dp[al-1][bl] && a.charAt(al-1) == ch) || (dp[al][bl-1] && b.charAt(bl-1) == ch);
				}
				
				found |= dp[al][bl];
			}
			
			if(!found) {
				return false;
			}
		}
		
		return dp[alen][blen];
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
		
		System.out.println(t.validate_broadFirst(a, b, c));
		System.out.println(t.validate_deepFirst(a, b, c));
		System.out.println(t.validate_simple(a, b, c));
		System.out.println(t.validate_dp(a, b, c));
	}

}
