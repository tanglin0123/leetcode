package interview;

import java.util.*;

public class FindPrimeNumber {

	public List<Integer> findPrime(int n){
		List<Integer> r = new LinkedList<Integer>();
		if(n <= 2){
			return r;
		}
		
		for(int i = 2; i < n; ++i){
			boolean isPrime = true;
			for(int j: r ){
				if(i % j == 0){
					isPrime = false;
					break;
				}
			}
			if(isPrime){
				r.add(i);
			}
		}
		
		return r;
	}
	
	public static void main(String[] args) {
		int n = 100;
				
		FindPrimeNumber f = new FindPrimeNumber();
		List<Integer> r = f.findPrime(n);
		
		for(int i : r){
			System.out.println(i);
		}
	}

}
