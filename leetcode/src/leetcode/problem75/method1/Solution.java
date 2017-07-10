package leetcode.problem75.method1;

public class Solution {
    public void sortColors(int[] nums) {
        int rstart=0,rend=nums.length-1;
        int wstart=0,wend=nums.length-1;
        int bstart=0,bend=nums.length-1;
        
        int rcount=0,wcount=0,bcount=0;
        
        for(int i = 0; i < nums.length; ++i){
            switch(nums[i]){
                case 0:{
                    ++rcount;
                    break;
                }
                case 1:{
                    ++wcount;
                    break;
                }
                case 2:{
                    ++bcount;
                    break;
                }
                default:{
                    break;
                }
            }
        }
        
        if(rcount==0){
            rstart=rend=-1;
        } else{
            rend=rcount-1;    
        }
        
        if(wcount ==0){
            wstart=wend=-1;
        } else{
            wstart=rcount;
            wend=wstart+wcount-1;
        }
        
        if(bcount==0){
            bstart=bend=-1;
        }else{
            bstart=bend-bcount+1;
        }
        
        int rindex=rstart;
        int windex=wstart;
        int bindex=bstart;
        
        for(int i=0; i < nums.length; ++i){ 
            
            switch(nums[i]){
                case 0:{
                    if(i>=rstart && i<=rend){
                        rindex++;
                    } else if(i>=wstart && i<=wend){
                        int tmp=nums[rindex];
                        nums[rindex++]=0;
                        nums[i]=tmp;
                        --i;
                    } else if(i>=bstart && i<=bend){
                        int tmp=nums[rindex];
                        nums[rindex++]=0;
                        nums[i]=tmp;
                        --i;
                    }
                    break;
                }
                case 1:{
                    if(i>=rstart && i<=rend){
                        int tmp=nums[windex];
                        nums[windex++]=1;
                        nums[i]=tmp;
                        --i;
                    } else if(i>=wstart && i<=wend){
                        ++windex;
                    } else if(i>=bstart && i<=bend){
                        int tmp=nums[windex];
                        nums[windex++]=1;
                        nums[i]=tmp;
                        --i;
                    }
                    break;
                }
                case 2:{
                    if(i>=rstart && i<=rend){
                        int tmp=nums[bindex];
                        nums[bindex++]=2;
                        nums[i]=tmp;
                        --i;
                    } else if(i>=wstart && i<=wend){
                        int tmp=nums[bindex];
                        nums[bindex++]=2;
                        nums[i]=tmp;
                        --i;
                    } else if(i>=bstart && i<=bend){
                        ++bindex;
                    }
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }
    
    public static void main(String[] args){
    	Solution s = new Solution();
    	int[] nums = new int[]{1,2,0,0};
    	s.sortColors(nums);
    	for(int n : nums){
    		System.out.println(n);
    	}
    }
}