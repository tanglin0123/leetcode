package leetcode.problem313.method2;

public class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;
        
        int[] index = new int[k];
        for(int i = 0; i < k ; ++i){
        	index[i] = 1;
        }
        
        int[] result = new int[n+1];
        
        result[1] = 1;
        
        for(int i = 2; i <= n; ++i){
        	result[i] = Integer.MAX_VALUE;
        	int primeIndex = -1;
        	for(int j = 0; j < k; ++j){
        		int newone = primes[j] * result[index[j]];
        		if(result[i] > newone){
        			primeIndex = j;
        			result[i] = newone;
        		}
        	}
        	index[primeIndex]++;
        	
        	for(int j = 0; j < k; ++j){
        		int newone = primes[j] * result[index[j]];
        		if(result[i] == newone){
        			index[j]++;
        		}
        	}
        }
        
        return result[n];
    }
	

	
	public static void main(String[] args) {
//		int n = 800;
//		int[] primes = new int[]{37,43,59,61,67,71,79,83,89,97,101,103,113,127,131,157,163,167,173,179,191,193,197,199,211,229,233,239,251,257};
		
//		int n = 100000;
//		int[] primes = new int[]{7,19,29,37,41,47,53,59,61,79,83,89,101,103,109,127,131,137,139,157,167,179,181,199,211,229,233,239,241,251};

		int n = 4000;
		int[] primes = new int[]{2,3,5,13,19,29,31,41,43,53,59,73,83,89,97,103,107,109,127,137,139,149,163,173,179,193,197,199,211,223,227,229,239,241,251,257,263,269,271,281,317,331,337,347,353,359,367,373,379,389,397,409,419,421,433,449,457,461,463,479,487,509,521,523,541,547,563,569,577,593,599,601,613,619,631,641,659,673,683,701,709,719,733,739,743,757,761,769,773,809,811,829,857,859,881,919,947,953,967,971};

		
		Solution s = new Solution();
		System.out.println(s.nthSuperUglyNumber(n, primes));
	}

}
