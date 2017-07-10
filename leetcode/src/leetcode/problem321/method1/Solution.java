package leetcode.problem321.method1;

// not finished yet
public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        
        int[] r = new int[k];
        
        max(nums1, nums2, k, 0, 0, r);
        
        return r;
    }
    
    int[] max(int[] nums1, int[] nums2, int k, int start1, int start2, int[] r){
        int len1 = nums1.length - start1;
        int len2 = nums2.length - start2;
        
        if(k > len1 + len2){
            return null;
        }
        
        int searchDist1 = k > len2 ? k - len2 : len1;
        int searchDist2 = k > len1 ? k - len1 : len2;
        
        int max1 = nums1[start1];
        int maxIdx1 = start1;
        for(int i = 0; i <= searchDist1; ++i){
            int idx = i + start1;
            if(max1 < nums1[idx]){
                max1 = nums1[idx];
                maxIdx1 = idx;
            }
        }
        
        int max2 = nums2[start2];
        int maxIdx2 = start2;
        for(int i = 0; i <= searchDist2; ++i){
            int idx = i + start2;
            if(max2 < nums2[idx]){
                max2 = nums2[idx];
                maxIdx2 = idx;
            }
        }
        
        /////////// not finished yet ///////////
        return null;
    }
}