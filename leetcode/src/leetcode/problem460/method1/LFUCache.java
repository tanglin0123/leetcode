package leetcode.problem460.method1;

import java.util.*;

class LFUCache {

    static class Node {
        final int key;
        int value;
        
        Node prev;
        Node next;
        
        Bucket bucket;
        
        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
        
        public String toString() {
        	return key + ":" + value;
        }
    }
    
    static class Bucket {
        Bucket prev;
        Bucket next;
        
        int times;
        final Node head = new Node(0,0); // pseudo head
        Node tail = head;
        
        int count = 0;
        
        Bucket(int times){
            this.times = times;
        }
        
        public String toString() {
        	return "t=" + times + ", c=" + count; 
        }
        
        void add(Node n){
            tail.next = n;
            n.prev = tail;
            n.bucket = this;
            tail = n;
            ++count;
        }
        
        Node evict(){
            return remove(head.next);
        }
        
        Node remove(Node n) {
            Node pn = n.prev;
            Node nn = n.next;
            
            pn.next = nn;
            if(nn != null){
                nn.prev = pn;
            } else {
            	tail = pn;
            }
            
            n.bucket = null;
            n.next = null;
            n.prev = null;
            --count;
            
            if(count == 0 && this.prev != null){ // do not destroy the first bucket
                this.prev.next = this.next;
                if(this.next != null){
                    this.next.prev = this.prev;
                }
                this.prev = null;
                this.next = null;
            }
            
            return n;
        }
        
    }
    
    Bucket bucketHead = new Bucket(1);
    
    Map<Integer, Node> nodeMap = new HashMap<>();
    
    final int maxSize;
    int curSize = 0;
    
    public LFUCache(int capacity) {
        this.maxSize = capacity;
    }
    
    public int get(int key) {
    	if(maxSize == 0) {
    		return -1;
    	}
        Node node = nodeMap.get(key);
        if(node == null){
            return -1;
        }
        adjust(node);
        return node.value;
    }
    
    public void put(int key, int value) {
    	if(this.maxSize == 0) {
    		return;
    	}
    	
        Node node = nodeMap.get(key);
        if(node == null){
            if(curSize == maxSize){
                Node e = evict();
                nodeMap.remove(e.key);
                --this.curSize;
            }
            node = new Node(key, value);
            nodeMap.put(key, node);
            bucketHead.add(node);
            ++this.curSize;
        } else {
            node.value = value;
            adjust(node);
        }
    }
    
    void adjust(Node node){
        Bucket bucket = node.bucket;
        int newtimes = bucket.times + 1;
        if(bucket.count == 1 && bucket.times != 1){ // keep the first bucket still for the first created node
            // short cut
            ++bucket.times;
        } else {
            Bucket nextBucket = bucket.next;
            if( nextBucket == null || nextBucket.times > newtimes) {
                Bucket newBucket = new Bucket(newtimes);
                bucket.next = newBucket;
                newBucket.prev = bucket;
                newBucket.next = nextBucket;
                if(nextBucket != null){
                    nextBucket.prev = newBucket;
                }
                nextBucket = newBucket;
            }
            bucket.remove(node);
            nextBucket.add(node);
        }
    }
    
    Node evict(){
        if(bucketHead.count > 0){
            return bucketHead.evict();
        } else {
            return bucketHead.next.evict();    
        }
    }


/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
	public static void main(String[] args) {
		int c = 2;
		
		LFUCache cache = new LFUCache(c);
		
		cache.put(1, 1);
		cache.put(2, 2);
		System.out.println(cache.get(1));
		cache.put(3, 3);
		System.out.println(cache.get(2));
		System.out.println(cache.get(3));
		cache.put(4, 4);
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));

	}

}
