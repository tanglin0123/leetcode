package leetcode.problem284.method1;

import java.util.*;

//Java Iterator interface reference:
//https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {

	List<Integer> cache = new LinkedList<Integer>();
	Iterator<Integer> itr;
	
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    this.itr = iterator;
	}

 // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
		if(cache.isEmpty()){
			Integer i = itr.next();
			cache.add(i);
			return i;
		} else {
			return cache.get(0);
		}		
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if(cache.isEmpty()){
	    	return itr.next();	    	
	    } else {
	    	return cache.remove(0);
	    }
	    
	}

	@Override
	public boolean hasNext() {
	    return itr.hasNext() || !cache.isEmpty();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
