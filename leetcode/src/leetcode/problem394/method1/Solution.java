package leetcode.problem394.method1;

import java.util.*;

public class Solution {
    static enum Type{
        Num,
        Text,
        Right
    }
    
    static class Token{
        int nextIdx;
        Type type;
        String text;
        
        Token(int nextIdx, Type type, String text){
            this.nextIdx = nextIdx;
            this.type = type;
            this.text = text;
        }
    }
    
    Stack<Token> stk = new Stack<Token>();
    
    public String decodeString(String s) {
        if(s == null || s.isEmpty()){
            return s;
        }
        
        char[] chs = s.toCharArray();
        
        String result = "";
        
        Token tk = getNextToken(chs, 0);
        while(tk != null){
            int nextIdx = tk.nextIdx;
            
            if(tk.type == Type.Num){
                stk.push(tk);
            
                
            } else if(tk.type == Type.Text){
                if(stk.isEmpty()){
                    result += tk.text;
                } else{
                	Token pre = stk.peek();
                	if(pre.type == Type.Num){
                		stk.push(tk);
                	} else {
                		pre.text += tk.text;
                	}
                    
                }
                
            } else if(tk.type == Type.Right){
                String temp = "";
                Token txtTk = stk.pop();
                Token numTk = stk.pop();
                
                int num = Integer.parseInt(numTk.text);
                
                for(int i = 0; i < num; ++i){
                    temp+=txtTk.text;
                }
                
                if(stk.isEmpty()){
                    result += temp;
                } else{
                	Token pre = stk.peek();
                	if(pre.type == Type.Num){
                		stk.push(new Token(nextIdx, Type.Text, temp));
                	} else {
                		pre.text += temp;
                	}
                }
            }
            
            tk = getNextToken(chs, nextIdx);
        }
        
        return result;
        
    }
    
    Token getNextToken(char[] chs, int idx){
        if(idx >= chs.length){
            return null;
        }
        
        String txt = "";
        if(chs[idx] >= '0' && chs[idx] <= '9'){ 
            while(idx < chs.length && chs[idx] >= '0' && chs[idx] <= '9'){
                txt += chs[idx++];
            }
            return new Token(idx+1/*skip '['*/, Type.Num, txt);
        } else if(chs[idx] == ']'){
            return new Token(idx+1, Type.Right, txt);
        } else {
            while(idx < chs.length && (chs[idx] < '0' || chs[idx] > '9') && chs[idx]!=']'){
                txt += chs[idx++];
            }
            return new Token(idx, Type.Text, txt);
        }
    }
    
	public static void main(String[] args) {
		String str = "sd2[f2[e]g]i";
		
		Solution s = new Solution();
		System.out.println(s.decodeString(str));

	}

}
