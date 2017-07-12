package leetcode.problem117.method1;

import leetcode.commons.*;

import java.util.*;

// pass. but with O(n) extra space

public class Solution {
    public void connect(TreeLinkNode root) {
        if(root== null){
            return;
        }
        
        LinkedList<TreeLinkNode> q = new LinkedList<TreeLinkNode>();
        q.add(root);
        
        while(!q.isEmpty()){
            LinkedList<TreeLinkNode> newq = new LinkedList<TreeLinkNode>();
            
            TreeLinkNode pre = q.remove(0);
            
            if(pre.left != null){
                newq.add(pre.left);
            }
            if(pre.right!=null){
                newq.add(pre.right);
            }
            
            while(!q.isEmpty()){
                TreeLinkNode cur = q.remove(0);
                
                if(cur.left != null){
                    newq.add(cur.left);
                }
                if(cur.right!=null){
                    newq.add(cur.right);
                }
                
                pre.next = cur;
                pre = cur;
            }
            
            q = newq;
        }
    }
}