package leetcode.problem381.method1;

import java.util.*;

public class RandomizedCollection {

    List<Integer> list = new ArrayList<Integer>();
    Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
    Random rand = new Random();

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        Set<Integer> idxSet = map.get(val);
        
        boolean ret = false;
        if(idxSet == null){
            idxSet = new HashSet<Integer>();
            map.put(val, idxSet);
        }
        
        if(idxSet.isEmpty()){
            ret = true;
        }
        
        idxSet.add(list.size());
        list.add(val);
        
        return ret;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        Set<Integer> idxSet = map.get(val);
        
        if(idxSet == null || idxSet.isEmpty()){
            return false;
        }
        
        int idx = idxSet.iterator().next();
        
        if(idx != list.size()-1){
            int lastVal = list.get(list.size()-1);
            if(lastVal == val){
            	idxSet.remove(list.size()-1);
                list.remove(list.size()-1);
                return true;
            } else {
            	Set<Integer> lastValIdxSet = map.get(lastVal);
                lastValIdxSet.remove(list.size()-1);
                lastValIdxSet.add(idx);
                list.set(idx, lastVal);
                idxSet.remove(idx);
                list.remove(list.size()-1);
                return true;
            }
        } else {
        	idxSet.remove(idx);
            list.remove(list.size()-1);
            return true;
        }
        
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get( rand.nextInt(list.size()) );
    }

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
	public static void main(String[] args) {
		// test1();
		test2();
	}
	
	public static void test1(){
		RandomizedCollection r = new RandomizedCollection();
		
		System.out.println(r.insert(1));
		System.out.println(r.insert(1));
		System.out.println(r.insert(2));
		
		System.out.println(r.getRandom());
		
		System.out.println(r.remove(1));
		System.out.println(r.getRandom());
	}
	
	public static void test2(){
		RandomizedCollection r = new RandomizedCollection();
		
		System.out.println(r.insert(4));
		System.out.println(r.insert(3));
		System.out.println(r.insert(4));
		System.out.println(r.insert(2));
		System.out.println(r.insert(4));
		
		System.out.println(r.remove(4));
		System.out.println(r.remove(3));
		System.out.println(r.remove(4));
		System.out.println(r.remove(4));
	}

}
