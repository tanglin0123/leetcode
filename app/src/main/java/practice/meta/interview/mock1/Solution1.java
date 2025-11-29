package practice.meta.interview.mock1;

import java.util.Stack;

// LC P 227
// evaluate a string formula, with + - * / in it only, need to consider precedance. no need to consider ()
public class Solution1 {
    public int evaluate(String s) {
        if (s.startsWith("-")) {
            s = "0" + s;
        }
        
        Stack<Integer> stk = new Stack<>();
        
        char op = 0;

        int curVal = 0;

        for(char c : s.toCharArray()) {

            if (c == ' ') {
                continue;
            } else if (c >= '0' && c <= '9') {
                curVal = curVal * 10 + ( (int)c - (int)'0' );

            } else {
                char preOp = op;
                op = c;

                if (preOp == '*') {
                    int preVal = stk.pop();
                    int result = preVal * curVal;
                    stk.push(result);
                } else if (preOp == '/')  {
                    int preVal = stk.pop();
                    int result = preVal / curVal;
                    stk.push(result);
                } else if (preOp == '-') {
                    stk.push(-curVal);
                } else { // if (preOp == '+') 
                    stk.push(curVal);
                }

                curVal = 0;
            }

        }

        if (op == '*') {
            int preVal = stk.pop();
            int result = preVal * curVal;
            stk.push(result);
        } else if (op == '/')  {
            int preVal = stk.pop();
            int result = preVal / curVal;
            stk.push(result);
        } else if (op == '-') {
            stk.push(-curVal);
        } else { // if (op == '+') 
            stk.push(curVal);
        }

        int sum = 0;
        while(!stk.isEmpty()) {
            sum += stk.pop();
        }

        return sum;
    }

    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();

        String s1 = "-11-22*33+44";

        System.out.println(solution1.evaluate(s1));
    }
}
