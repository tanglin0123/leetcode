package leetcode.problem1.method1;

public class NumArray {

    int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length];
        int s = 0; 
        for(int i = 0; i < nums.length ; ++i){
            s+= nums[i];
            sum[i] = s; 
        }
    }
    
    public void update(int i, int val) {
        int num = i==0? sum[0] : sum[i] - sum[i-1];
        int gap = val - num;
        for(int j = i; j < sum.length; ++j){
            sum[j] += gap;
        }
    }
    
    public int sumRange(int i, int j) {
        return sum[j] - (i == 0 ? 0:sum[i-1]); 
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */