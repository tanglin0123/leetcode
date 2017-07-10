package leetcode.commons;

import java.util.*;

public class TreeNode {
	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}
	
	public static TreeNode fromString(String s){
		s = s.substring(1, s.length()-1);
		
		if(s.isEmpty()){
			return null;
		}
		
		String[] ss = s.split(",");
		
		// return fromStringArray(ss, 0); 
		
		List<TreeNode> q = new LinkedList<TreeNode>();
		TreeNode root = new TreeNode(Integer.parseInt(ss[0]));
		
		TreeNode cur = root;
		
		boolean left = true;
		for(int i = 1; i < ss.length; ++i){
			TreeNode newone = null;
			if(!ss[i].equals("null")){
				newone = new TreeNode(Integer.parseInt(ss[i]));
			}
			
			if(newone != null){
				q.add(newone);
			}
			
			if(left){
				cur.left = newone;
			} else{
				cur.right = newone;
				if(!q.isEmpty()){
					cur = q.remove(0);
				} else {
					cur = null;
				}
			}
			
			left = !left;
		}
		
		return root;
	}
	
//	public static TreeNode fromString(String s){
//		s = s.substring(1, s.length()-1);
//		
//		if(s.isEmpty()){
//			return null;
//		}
//		
//		String[] ss = s.split(",");
//		
//		return fromStringArray(ss, 0); 
//	}
	
//	static TreeNode fromStringArray(String[] ss, int idx){
//		if(ss[idx].equals("null")){
//			return null;
//		}
//		
//		TreeNode root = new TreeNode(Integer.parseInt(ss[idx]));
//		if(idx*2+1 < ss.length){
//			root.left = fromStringArray(ss, idx*2+1);
//		}
//		if(idx*2+2 < ss.length){
//			root.right = fromStringArray(ss, idx*2+2);
//		}
//		
//		return root;
//	}
	
	
	
	public String toString(){
		return ""+val;
	}
	
	public static void main(String[] args){
//		TreeNode a = TreeNode.fromString("[5,3,6,2,4,null,7]");
		
		TreeNode a = TreeNode.fromString("[2,0,33,null,1,25,40,null,null,11,31,34,45,10,18,29,32,null,36,43,46,4,null,12,24,26,30,null,null,35,39,42,44,null,48,3,9,null,14,22,null,null,27,null,null,null,null,38,null,41,null,null,null,47,49,null,null,5,null,13,15,21,23,null,28,37,null,null,null,null,null,null,null,null,8,null,null,null,17,19,null,null,null,null,null,null,null,7,null,16,null,null,20,6]");
		
		System.out.println(a.val);
	}
}