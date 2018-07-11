package leetcode.problem224.method1;

class Solution {
    public int calculate(String s) {
        s = s.trim();
        
        char[] chs = s.toCharArray();
        
        long sum = 0;
        int op = 1;
        long subsum = 0;

        int i = 0;
        for(; i < chs.length; ++i){
            if(chs[i] == ' '){
                continue;
            } else if (chs[i] == '+'){
                sum += subsum * op;
                op = 1;
                subsum = 0;
            } else if(chs[i] == '-'){
                sum += subsum * op;
                op = -1;
                subsum = 0;
            } else if(chs[i] == '('){
                int lcnt = 1;
                int j = i + 1;
                for(; j < chs.length; ++j){
                    if(chs[j] == '(') { 
                        ++lcnt;
                    }
                    else if(chs[j] == ')'){
                        --lcnt;
                        if(lcnt==0){
                            break;
                        }
                    }
                }
                subsum = calculate(new String(chs, i+1, j-i-1));
                i = j;
            } else { // number
                subsum = subsum * 10 + (chs[i] - '0');
            }
        }
        
        sum += subsum * op;
        
        return (int)sum; 
    }


	public static void main(String[] args) {
		String s = "(1+(4+5+2)-3)+(6+8)";
		
		Solution sln = new Solution();
		System.out.println(sln.calculate(s));
	}

}
