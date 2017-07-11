package leetcode.problem552.method1;


// recursive. time limit exceeded
public class Solution {
    public int checkRecord(int n) {
        return check(n, 0, 0);
    }
    
    int check(int n, int acount, int lcount){
        int a = 0;
        int l = 0;
        int p = 0;
        
        if(n == 1){
            if(acount >= 1){
                a = 0;
            } else {
                a = 1;
            }
            
            if(lcount >= 2){
                l = 0;
            } else {
                l = 1;
            }
            
            p = 1;
            
            return (a + l + p);
        }
        
        if(acount >= 1){
            a = 0;
        } else {
            a = check(n-1, acount+1, 0);
        }
        
        if(lcount >= 2){
            l = 0;
        } else {
            l = check(n-1, acount, lcount+1);
        }
        
        p= check(n-1, acount, 0);
        
        return (a + l + p) % 1000000007;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
