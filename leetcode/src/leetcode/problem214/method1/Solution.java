package leetcode.problem214.method1;


// timeout
public class Solution {
    public String shortestPalindrome(String s) {
        if(s == null || s.length()<=1){
            return s;
        }
        
        boolean oddPalin = false;
        boolean evenPalin = false;
        
        for(int pivot = s.length() /2; pivot >=0; --pivot){
            boolean isPalin = true;
            // odd palin
            oddPalin = true;
            if(s.length()%2==0 && pivot == s.length() /2){
                oddPalin = false;
            } else {
                for(int i = 1; i <=pivot ; ++i){
                    int left = pivot - i;
                    int right = pivot +i;
                    if(left < 0 || right > s.length()-1 || s.charAt(left) != s.charAt(right)){
                        oddPalin = false;
                        break;
                    }
                }
            }
            
            if(oddPalin){
                int palinLen = pivot * 2 + 1;
                char[] news = new char[s.length() - palinLen];
                for(int j=0, i = s.length()-1; i >= palinLen; --i, ++j){
                    news[j] = s.charAt(i);
                }
                System.out.print("odd: " + pivot);
                return new String(news) + s;
            }
            
            // even palin
            evenPalin = true;
            for(int i = 1; i <=pivot ; ++i){
                int left = pivot - i;
                int right = pivot +i-1;
                if(left < 0 && right > s.length()-1 || s.charAt(left) != s.charAt(right)){
                    evenPalin = false;
                    break;
                } 
            }
            
            if(evenPalin){
                int palinLen = pivot * 2;
                char[] news = new char[s.length() - palinLen];
                for(int j=0, i = s.length()-1; i >= palinLen; --i, ++j){
                    news[j] = s.charAt(i);
                }
                System.out.print("even: " + pivot);
                return new String(news) + s;
            }
            
        }
        
        return null;
    }
    
	public static void main(String[] args) {
		String s0 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String s1 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		String s2 = "cd";
		
		String s = "";
		for(int i = 0; i < 19; ++i){
			s+= s0;
		}
		s += s1;
		s += s2;
		for(int i = 0; i < 19; ++i){
			s+= s0;
		}
		s+=s1;
		
		System.out.println(new Solution().shortestPalindrome(s));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
