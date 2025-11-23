package practice.leetcode.problem460;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

// Wrong Answer 24 / 25 testcases passed
@Log4j2
public class LFUCache {

    public static class ListNode {
        public int frequency;

        public int key;
        public int value;

        public ListNode next = null;
        public ListNode prev = null;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1;
        }
    }


    private final int capacity;

    private ListNode first;

    private ListNode last;

    private Map<Integer, ListNode> rangeFirstMap;

    private Map<Integer, ListNode> nodeMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.last = null;
        this.rangeFirstMap = new HashMap<>();
        this.nodeMap = new HashMap<>();
    }
    
    public int get(int key) {
        log.info("getting {}", key);

        ListNode node = this.nodeMap.get(key);

        if(node == null) {
            return -1;
        }

        node.frequency ++;

        this.visitNode(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        log.info("putting {} -> {}", key, value);

        ListNode node = this.nodeMap.get(key);

        if(node != null) {
            node.frequency ++;
            node.value = value;
            this.visitNode(node);
            return;

        } else { // new node, frequency = 1
            node = new ListNode(key, value);

            if(this.nodeMap.size() == 0 || this.capacity == 1) { // the first node
                nodeMap.clear();
                nodeMap.put(key, node);
                this.first = node;
                this.last = node;
                this.rangeFirstMap.put(1, node);
                return;
            } 

            if(this.nodeMap.size() == capacity) {
                purgeLast();
            }

            addNode(node);
        } 
        
    }

    private void purgeLast() {
        // remove from cache table
        this.nodeMap.remove(last.key);

        final ListNode tmpPrev = last.prev;
        
        // remove node from list
        last.prev = null;
        tmpPrev.next = null;

        // update range index
        if (last.frequency != tmpPrev.frequency) { // the sole node 
            rangeFirstMap.remove(last.frequency);
        }

        // no need to change the first

        // update the last
        this.last = tmpPrev;

        log.info("After purge last");
        printCache();
    }

    private void addNode(ListNode node) {
        // add to the cache table
        this.nodeMap.put(node.key, node);

        final ListNode rangeFirst = rangeFirstMap.get(1);
        
        // put the node into list
        if (rangeFirst == null) {
            node.prev = this.last;
            if(this.last != null) {
                this.last.next = node;
            }
        } else {
            final ListNode tmpFirstPrev = rangeFirst.prev;

            node.next = rangeFirst;
            rangeFirst.prev = node;

            node.prev = tmpFirstPrev;
            if(tmpFirstPrev != null) {
                tmpFirstPrev.next = node;
            }
        }

        // update the range index
        rangeFirstMap.put(1, node);

        // update the first
        if (this.first.frequency <= node.frequency) {
            this.first = node;
        }

        // update the last
        if (rangeFirst == null) {
            this.last = node;
        }

        log.info("After add node");
        printCache();
    }

    private void visitNode(ListNode node) {
        final ListNode oldRangeFirst = rangeFirstMap.get(node.frequency - 1);
        final ListNode newRangeFirst = rangeFirstMap.get(node.frequency);

        final ListNode tmpPrev = node.prev;
        final ListNode tmpNext = node.next;
        
        
        // move the node in the list
        if(newRangeFirst == null) { // not found the new range first, start a new range
            if (oldRangeFirst == node) {
                // no need to move in the list as this node will be the first one in the new range

            } else { // need to put the node before the old range
                final ListNode tmpFirstPrev = oldRangeFirst.prev;

                if (tmpPrev != null) {
                    tmpPrev.next = tmpNext;
                }
                if (tmpNext != null) {
                    tmpNext.prev = tmpPrev;
                }

                node.next = oldRangeFirst;
                oldRangeFirst.prev = node;

                node.prev = tmpFirstPrev;
                if(tmpFirstPrev != null) {
                    tmpFirstPrev.next = node;
                }
            }
            
        } else {
            if (tmpPrev != null) {
                tmpPrev.next = tmpNext;
            }
            if (tmpNext != null) {
                tmpNext.prev = tmpPrev;
            }

            node.prev = newRangeFirst.prev;
            if(newRangeFirst.prev != null) {
                newRangeFirst.prev.next = node;
            }

            node.next = newRangeFirst;
            newRangeFirst.prev = node;
        }

        // update the index of new range
        rangeFirstMap.put(node.frequency, node);

        // update the index of the old range
        if (oldRangeFirst == node) {
            if (tmpNext != null && tmpNext.frequency == node.frequency - 1) {
                rangeFirstMap.put(node.frequency - 1, tmpNext);
            } else {
                rangeFirstMap.remove(node.frequency - 1);
            }
        }

        // update first
        if (this.first.frequency <= node.frequency) {
            this.first = node;
        }

        // update last
        if (this.last == node && tmpPrev != null) {
            this.last = tmpPrev;
        }

        log.info("After visit node");
        printCache();
    }

    

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);

        cache.get(1);

        cache.put(3, 3);

        cache.get(2);
        cache.get(3);

        cache.put(4, 4);

        cache.get(1);
        cache.get(3);
        cache.get(4);


    }

    private void printCache() {
        ListNode node = this.first;

        log.info("-----");

        while(node != null) {
            log.info("{}({}):{}", node.key, node.frequency, node.value);
            node = node.next;
        }

        log.info("-----");

    }
}
