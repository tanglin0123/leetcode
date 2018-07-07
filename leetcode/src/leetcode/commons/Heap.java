package leetcode.commons;

import java.util.Arrays;
import java.util.Comparator;

public class Heap<T extends Comparable<?>> {
	int maxLength;
	int length;
	Object[] q;
	Comparator<T> comp;
	
	public Heap(int maxLength, Comparator<T> comp) {
		this.maxLength = maxLength;
		this.q = new Object[maxLength+1];
		this.length = 0;
		this.comp = comp;
	}
	
	public void push(T n) {
		this.q[++length] = n;
		shiftUp(length);
	}
	
	@SuppressWarnings("unchecked")
	public T pop() {
		T r = (T) q[1];
		q[1] = q[length--];
		shiftDown(1);
		return r;
	}
	
	@SuppressWarnings("unchecked")
	public T top() {
		return (T) q[1];
	}
	
	public int size() {
		return this.length;
	}
	
	@SuppressWarnings("unchecked")
	void shiftUp(int i) {
		int cidx = i;
		int pidx = cidx/2;
		while(pidx >= 1 && comp.compare((T)q[pidx], (T)q[cidx]) > 0 ) {
			swap(cidx, pidx);
			
			cidx = pidx;
			pidx = cidx/2;
		}
	}
	
	@SuppressWarnings("unchecked")
	void shiftDown(int i) {
		int pidx = i;
		int cidx = i*2;
		
		while(cidx <= length) {
			if(cidx+1 <= length && comp.compare((T)q[cidx+1], (T)q[cidx]) < 0 ) {
				++cidx;
			}
			
			if(comp.compare((T)q[cidx], (T)q[pidx]) < 0) {
				swap(cidx, pidx);
			} else {
				break;
			}
			
			pidx = cidx;
			cidx = pidx*2;
		}
	}
	
	@SuppressWarnings("unchecked")
	void swap(int i, int j) {
		T tmp = (T)q[i];
		q[i] = q[j];
		q[j] = tmp;
	}
	
	public String toString() {
		return Arrays.toString(Arrays.copyOfRange(q, 0, length+1));
	}
	
	public static class MaxComparator<S extends Comparable<S>> implements Comparator<S> {
		@Override
		public int compare(S o1, S o2) {
			return -o1.compareTo(o2);
		}
	} 
	
	public static class MinComparator<S extends Comparable<S>> implements Comparator<S> {
		@Override
		public int compare(S o1, S o2) {
			return o1.compareTo(o2);
		}
	} 
	
	public static Heap<Integer> newMaxHeap(int maxSize) {
		return new Heap<>(maxSize, new Heap.MaxComparator<Integer>());
	}
	
	public static Heap<Integer> newMinHeap(int maxSize) {
		return new Heap<>(maxSize, new Heap.MinComparator<Integer>());
	}
	
	public static void main(String[] args) {
		Heap<Integer> maxHeap = Heap.newMaxHeap(1000);
		maxHeap.push(1);
		maxHeap.push(2);
		maxHeap.push(3);
		maxHeap.push(4);
		maxHeap.pop();
		System.out.println(maxHeap);
		
		Heap<Integer> minHeap = Heap.newMinHeap(1000);
		minHeap.push(1);
		minHeap.push(2);
		minHeap.push(3);
		minHeap.push(0);
		minHeap.pop();
		System.out.println(minHeap);
	}

}
