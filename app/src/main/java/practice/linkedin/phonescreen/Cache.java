package practice.linkedin.phonescreen;

import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
    a cache solution:
    a cache with max size
    if found, return the value
    if not found in the cache, get from data source and cache the entry
    if reached the max size, evict the entry with lowest rank
    assumption: an entry's rank never change
*/

/*
    followup:
        if implement the cache solution in java in multiple servers, what will be the issue?
        issues:
            inconsistent value in different nodes
            value is invalid if the value in DS is changed, need to invalidate
            contain all values will exceed one node's memory limitation            
*/

public class Cache <K extends Object, V extends Cache.Rankable> {

    private int capacity;
    private DataSource<K, V> dataSource;

    PriorityQueue<Pair<K, V>> pq = new PriorityQueue<>() ; // min-heap

    Map<K, V> map = new HashMap<>();


    public Cache(DataSource<K, V> dataSource, int capacity) {
        this.dataSource = dataSource;
        this.capacity = capacity;
    }

    public V get(K key) {
        V value = map.get(key);
        if (value != null) {
            return value;
        }

        value = dataSource.get(key);

        if (capacity == map.size()) {
            Pair<K, V> entry = pq.poll();
            map.remove(entry.getKey());
        }

        map.put(key, value);
        pq.add(new Pair<K,V>(key, value));

        return value;
    }

    @AllArgsConstructor
    @Getter
    public static class Pair <K extends Object, V extends Cache.Rankable> {
        K key;
        V value;
    }

    public static interface Rankable {
        int getRank();
    }

    public static interface DataSource <K extends Object, V extends Cache.Rankable> {
        V get(K key);
        
    }
}
