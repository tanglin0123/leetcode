package leetcode.problem385.method1;

import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {
	enum TokenType{
		Left, // [
		Right, // ]
		Num, // 123, 0, -1
		Comma // ,
	}
	
	static class Context{
		int curIdx = 0; // for next token
		String token = "";
		TokenType type = TokenType.Num;
		
		char[] chs = null;
		
		Context(char[] chs){
			this.chs = chs;
		}
	}
	
	Stack<NestedInteger> stk= new Stack<NestedInteger>();
	
    public NestedInteger deserialize(String s) {
    	
    	char[] chs = s.toCharArray();
    	
    	Context ctx = new Context(chs);
    	
    	NestedInteger now = null;
    	
    	String token = getToken(ctx); 
    	while(token!=null){
        	switch(ctx.type){
        	case Left: {
        		now = new NestedInteger();
        		if(!stk.isEmpty()){
        			stk.peek().add(now);
        		}
        		stk.push(now);
        		break;
        	}
        	
        	case Right: {
        		now = stk.pop();
        		break;
        	}
        	
        	case Comma: {
        		
        		break;
        	}
        	
        	case Num: {
        		int num = Integer.parseInt(ctx.token);
        		now = new NestedInteger(num);
        		if(!stk.isEmpty()){
        			stk.peek().add(now);
        		}
        		break;
        	}
        	
        	default:{
        		
        	}
        	
        	}
    		
    		
    		token = getToken(ctx);
        }
    	
    	return now;
        
    }
    
    String getToken(Context ctx){
    	char[]  chs = ctx.chs;
    	
    	if(ctx.curIdx >= chs.length){
    		return null;
    	}

    	switch(chs[ctx.curIdx]){
    	case ',':{
    		ctx.curIdx++;
    		ctx.token = ",";
    		ctx.type = TokenType.Comma;
    		return ctx.token;
    	}
    	
    	case '[':{
    		ctx.curIdx++;
    		ctx.token = "[";
    		ctx.type = TokenType.Left;
    		return ctx.token;
    	}
    	
    	case ']':{
    		ctx.curIdx++;
    		ctx.token = "]";
    		ctx.type = TokenType.Right;
    		return ctx.token;
    	}
    	
    	default:{
    		break;
    	}
    		
    	}
    	
    	String tkStr = "";
    	int i = ctx.curIdx;
    	while(i < chs.length && chs[i] != '[' && chs[i] != ']' && chs[i] != ','){
    		tkStr += chs[i];
    		++i;
    	}
    	
    	ctx.curIdx = i;
    	ctx.token = tkStr;
    	ctx.type = TokenType.Num;
    	
    	return ctx.token;
    }
}