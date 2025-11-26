package practice.leetcode.problem460;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache2 {
    private static final int K = 0;
    private static final int V = 1;
    private static final int F = 2;


    private final int capacity;

    private int leastFrequency;

    private Map<Integer, LinkedHashSet<int[]>> frequencyMap;

    private Map<Integer, int[]> cacheTable;

    public LFUCache2(int capacity) {
        this.capacity = capacity;
        this.leastFrequency = 0;
        this.frequencyMap = new HashMap<>();
        this.cacheTable = new HashMap<>();
    }

    public int get(int key) {
        int[] item = this.cacheTable.get(key);

        if (item == null) {
            return -1;
        }

        item[F] ++;

        this.moveItem(item);

        return item[V];
    }

    public void put(int key, int value) {
        int[] item = this.cacheTable.get(key);

        if (item == null) { // new item

            if (this.cacheTable.size() == this.capacity) {
                this.removeLeastFrequentItem();
            }

            item = new int[]{key, value, 1};
            this.addItem(item);

        } else {
            item[F] ++;
            item[V] = value;
            moveItem(item);
        }
    }

    private void removeLeastFrequentItem() {
        LinkedHashSet<int[]> set = this.frequencyMap.get(this.leastFrequency);
        int[] item = set.getFirst();

        this.cacheTable.remove(item[K]);

        set.remove(item);
        if(set.isEmpty()) {
            this.frequencyMap.remove(item[F]);
        }

        // no need to update the leastFrequency, as it will be set as 1 in addIntoFrequencyMap()
    }

    private void addItem(int[] item) {
        this.cacheTable.put(item[K], item);

        LinkedHashSet<int[]> newSet = this.frequencyMap.get(1);
        
        if (newSet == null) {
            newSet = new LinkedHashSet<>();
            this.frequencyMap.put(1, newSet);
        }
        
        newSet.add(item);
        
        this.leastFrequency = 1;
    }

    private void moveItem(int[] item) {
        int oldFreq = item[F] - 1;

        LinkedHashSet<int[]> oldSet = this.frequencyMap.get(oldFreq);
        oldSet.remove(item);
        if(oldSet.size() == 0) {
            this.frequencyMap.remove(oldFreq);

            if(this.leastFrequency == oldFreq) {
                this.leastFrequency = item[F];
            }
        }

        LinkedHashSet<int[]> newSet = this.frequencyMap.get(item[F]);
        if (newSet == null) {
            newSet = new LinkedHashSet<>();
            this.frequencyMap.put(item[F], newSet);
        }
        newSet.add(item);
    }

    public static void main(String[] args) {
        LFUCache2 cache = new LFUCache2(2);

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
    
}
