package practice.leetcode.problem146;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LRUCache {
    public static class ListNode {
        public int key;
        public int value;

        public ListNode next = null;
        public ListNode prev = null;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    int capacity;

    ListNode first = null;
    ListNode last = null;

    Map<Integer, ListNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {
        ListNode node = map.get(key);

        if(node == null) {
            return -1;
        }

        this.moveToFirst(node);

        return node.value;
    }

    public void put(int key, int value) {
        ListNode node = map.get(key);

        if (node == null) {
            node = new ListNode(key, value);
            map.put(key, node);
        } else {
            node.value = value;
        }

        this.moveToFirst(node);

        if(this.map.size() > capacity) {
            this.purgeTheLast();
        }
        
    }

    private void moveToFirst(ListNode node) {
        if(first == null) { // the first node
            this.first = node;
            this.last = node;
            return;
        } 

        if (first == node && last == node) { // just one node
            return;
        }

        if (first == node) { // and last is a different node
            return;
        }

        if (this.last == node) {
            this.last = node.prev;
        }
        
        ListNode tmpNext = node.next;
        ListNode tmpPrev = node.prev;

        if (tmpPrev != null) {
            tmpPrev.next = tmpNext;
        }

        if (tmpNext != null) {
            tmpNext.prev = tmpPrev;
        }

        node.prev = null;
        node.next = first;

        this.first.prev = node;

        this.first = node;

        log.info("after move to first");
        this.printCache();

    }

    private void purgeTheLast() {
        
        this.map.remove(last.key);

        ListNode tmpNode = this.last;
        ListNode tmpPrev = this.last.prev;
        this.last = tmpPrev;
        tmpPrev.next = null;
        tmpNode.prev = null;
        
        log.info("after purge last");
        this.printCache();

    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);

        cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);

        cache.put(5, 5);

        log.info(1 + "->" + cache.get(1));
        log.info(2 + "->" + cache.get(2));
        log.info(3 + "->" + cache.get(3));
        log.info(4 + "->" + cache.get(4));
        log.info(5 + "->" + cache.get(5));
    }

    private void printCache() {
        ListNode node = this.first;

        while(node != null) {
            log.info(node.key + ":" + node.value);
            node = node.next;
        }

        log.info("-----");

    }
}
