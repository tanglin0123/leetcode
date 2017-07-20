package interview;

// amazon interview
public class ThreeStrings {
	public boolean validate(String a, String b, String c) {
		if(c == null || c.isEmpty()) {
			return false;
		}
		
		if(a == null || a.isEmpty()) {
			return c.equals(b);
		}
		
		if(b == null || b.isEmpty()) {
			return c.equals(a);
		}
		
		int[][] dp = new int[c.length() + 1][2];
		dp[0][0] = -1;
		dp[0][1] = -1;
		
		for(int i = 0; i < c.length(); ++i) {
			char ch = c.charAt(i);
			int apos = a.indexOf(ch, dp[i-1])
		}
	}
	
	public static void main(String[] args) {
		
	}

}
