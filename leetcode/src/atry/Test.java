package atry;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		String[] ss = "1.0".split("\\.");
		for(String s:ss) {
			System.out.println(s);
		}
		
		System.out.println(bitCount(1));
		System.out.println(bitCount(2));
		System.out.println(bitCount(3));
		System.out.println(bitCount(4));
		System.out.println(bitCount(5));
		System.out.println(bitCount(100));


		
	}
	
	static int bitCount(int n) {
		return (int)Math.ceil((Math.log(n)/Math.log(2)));
	}
	

}
