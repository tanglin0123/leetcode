package tanglin;

import java.util.*;

public class Solution {
	public Set<Integer> getPrimeDiv(int n){
		Set<Integer> r = new HashSet<Integer>();
		
		r.add(1);
		
		for(int i = 2; i <= Math.sqrt(n); ++i) {
			if(n % i == 0) {
				r.add(i);
				n /= i;
			}
		}
		
		return r;
	}
	
	public Set<Integer> getDiv(int n){
		Set<Integer> r = new HashSet<Integer>();
		
		for(int i = 1; i <= Math.sqrt(n); ++i) {
			if(n % i == 0) {
				r.add(i);
				r.add(n/i);
			}
		}
		
		return r;
	}
	
	public boolean mutualPrime(int n1, int n2) {
		for(int i : this.getPrimeDiv(n1)) {
			if(i != 1 && n2 % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		int a = 100;
		
		Solution s = new Solution();
		for(int i : s.getPrimeDiv(a)) {
			System.out.print(i+",");
		}
		System.out.println();
		
		for(int i : s.getDiv(a)) {
			System.out.print(i+",");
		}
		System.out.println();

		System.out.println(s.mutualPrime(100, 63));
		System.out.println(s.mutualPrime(100, 160));
	}

}
