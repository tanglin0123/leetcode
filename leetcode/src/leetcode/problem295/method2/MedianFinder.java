package leetcode.problem295.method2;

import leetcode.commons.*;

public class MedianFinder {

	Heap<Integer> maxHeap = Heap.newMaxHeap(1000);
	Heap<Integer> minHeap = Heap.newMinHeap(1000);
	int count = 0;
	
    public MedianFinder() {
    }
    
    public void addNum(int num) {
    	if(count == 0) {
    		maxHeap.push(num);
    		++count;
    		return;
    	}
    	
        if(count % 2 == 0) {
        	if(num <= minHeap.top()) {
        		maxHeap.push(num);
        	} else {
        		int tmp = minHeap.pop();
        		maxHeap.push(tmp);
        		minHeap.push(num);
        	}
        } else {
        	if(maxHeap.top() > num) {
        		int tmp = maxHeap.pop();
        		minHeap.push(tmp);
        		maxHeap.push(num);
        	} else {
        		minHeap.push(num);
        	}
        }
        
        ++count;
    }
    
    public double findMedian() {
        if(count % 2 == 0) return ((double) maxHeap.top() + (double) minHeap.top()) / (double)2;
        return maxHeap.top();
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