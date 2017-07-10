package leetcode.problem295.method1;

public class MedianFinder {

    /** initialize your data structure here. */


    static class TreeNode{
        int count = 0;
        int leftCount = 0;
        int rightCount = 0;
        TreeNode left;
        TreeNode right;
        int value;
        
        TreeNode(int v){
            value = v;
            ++count;
        }
        
        void add(int num){
            if(num == value){
                ++count;
                return;
            }
            
            if(num < value){
                ++leftCount;
                if(left == null){
                    left = new TreeNode(num);
                } else {
                    left.add(num);
                }
                return;
            }
            
            // num > value
            ++rightCount;
            if(right == null){
                right = new TreeNode(num);
            } else {
                right.add(num);
            }
        }
        
        int findIndex(int index){
            if(index < this.leftCount && left != null){
            	return left.findIndex(index);
            }
            
            index -= this.leftCount;
            if(index < count){
            	return this.value;
            }
            
            index -= this.count;
            return right.findIndex(index);
        }
    } 
    
    TreeNode root = null;

    public MedianFinder() {
        
    }
    
    public void addNum(int num) {
        if(root == null){
            root = new TreeNode(num);
        } else {
            root.add(num);
        }
    }
    
    public double findMedian() {
        
        if(root == null){
            return 0.0d;
        }
        
        int total = root.count + root.leftCount + root.rightCount;
        if(total %2 ==0){
            int m0 = root.findIndex(total/2-1);
            int m1 = root.findIndex(total/2);
            return ((double)m0 + (double)m1)/2;
            
        } else {
            return (double)root.findIndex(total/2);
        }
        
        
    }
    
    public static void main(String[] args){
    	MedianFinder m = new MedianFinder();
    	m.addNum(1);
    	m.addNum(2);
    	System.out.println(m.findMedian());
    	m.addNum(3);
    	System.out.println(m.findMedian());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */