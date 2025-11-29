package practice.meta.leetcode.p50;

import java.util.*;

// Time Limit Exceeded 294 / 307 testcases passed
public class Solution1 {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1.0;
        }

        long m = n < 0 ? -(long)n : (long)n;
        double y = n < 0 ? 1.0 / x : x;

        if (m % 2 == 0) {
            return myPow(y * y, (int)( m / 2));
        } else {
            return y * myPow(y * y, (int)((m - 1) / 2));
        }
    }
    
    public double myPow1(double x, int n) {
        if (n == 0) {
            return 1;
        }

        double[] prod = new double[32];

        prod[0] = x; // 2^0 = 1, x^1 = x

        long m = n > 0 ? (long)n : -(long)n;

        Map<Long, Integer> powerOf2 = new HashMap<>();
        powerOf2.put(1L, 0);
        long i = 2;
        for (int j = 1; j < prod.length && i <= m; j++, i *= 2) {
            prod[j] = prod[j-1] * prod[j-1];
            powerOf2.put(i, j);
        }

        double result = calculate( x, m, prod, powerOf2);

        return n > 0 ? result : 1.0/result;
    }

    private double calculate(double x, long n, double[] prod, Map<Long, Integer> powerOf2) {

        Integer j = powerOf2.get(n);
        if (j != null) {
            return prod[j];
        }

        double prod1 = calculate(x, n / 2, prod, powerOf2);
        double prod2 = calculate(x, n - n / 2, prod, powerOf2);

        return prod1 * prod2;
    }


    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();

        double x = 0.00001;

        int n = 2147483647;

        System.out.println(solution1.myPow(x, n));

        x = 1.00000;
        n = -2147483648;

        System.out.println(solution1.myPow(x, n));


        x = 8.66731;
        n = 4;

        System.out.println(solution1.myPow(x, n));


        x = 2.00000;
        n = -2147483648;

        System.out.println(solution1.myPow(x, n));
    }
}
