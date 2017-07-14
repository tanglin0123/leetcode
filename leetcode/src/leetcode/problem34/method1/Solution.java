public class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1,-1};
        }
        
        return search(nums, 0, nums.length-1, target);
    }
    
    int[] search(int[] nums, int start, int end, int target){
        int min = -2, max = -2;
        
        if(start == end){
            if(nums[start] == target){
                return new int[]{start, start};
            } else {
                return new int[]{-1, -1};
            }
        }
        
        if(nums[start] == target){
            min = start;
        }
        
        if(nums[end] == target){
            max = end;
        }
        
        if(min != -2 && max != -2){ // already got both
            return new int[]{min, max};
        }
        
        if(min != -2){ // already got min
            if(nums[min]<nums[min+1]){
                return new int[]{min, min};
            } else {
                max = searchMax(nums, min+1, end, target);
                return new int[]{min, max};
            }
        }
        
        if(max != -2){ // already got max
            if(nums[max-1]<nums[max]){
                return new int[]{max, max};
            } else {
                min = searchMin(nums, start, max-1, target);
                return new int[]{min, max};
            }
        }
        
        int i = start, j = end;
        while(i < j){
            
        }
        int mid = (start + end) / 2;
        
        if(nums[mid] == target){
            if(mid == start || nums[mid-1] < nums[mid]){
                min = mid;
            }
            if(mid == end || nums[mid+1] > nums[mid]){
                max = mid;
            }
            
            if(min == -2){
                min = searchMin(nums, start, mid-1, target);
            }
            
            if(max == -2){
                max = searchMax(nums, start, mid+1, target);
            }
            
            return new int[]{min, max};
        } else if(nums[mid] < target){
            return search(nums, mid+1, end, target);
        } else { // nums[mid] > target
            return search(nums, start, mid-1, target);
        }
    }
    
    int searchMin(int[] nums, int start, int end, int target){
        if(start == end){
            return start;
        }
        
        int i = start, j = end;
        
        while(i<j){
            int mid = (i + j) / 2;
            
            if(nums[mid] == target){
                if(nums[mid-1] < nums[mid]){ // no need to check mid == start, as it is checked in the calling func
                    return mid;
                } else {
                    j = mid-1;
                }
            } else { // nums[mid] < target 
                if(nums[mid+1] == target){ // no need to check boundary, as it is already made sure the
                    return mid+1;
                } else {
                    i = mid+1;
                }
            } // there's no the case of nums[mid] > target
        }
        
        return -1;
    }
    
    int searchMax(int[] nums, int start, int end, int target){
        if(start == end){
            return start;
        }
        
        int i = start, j = end;
        
        while(i<j){
            int mid = (i + j) / 2;
            
            if(nums[mid] == target){
                if(nums[mid] < nums[mid+1]){ // no need to check mid == start, as it is checked in the calling func
                    return mid;
                } else {
                    i = mid+1;
                }
            } else { // nums[mid] > target 
                if(nums[mid-1] == target){ // no need to check boundary, as it is already made sure the
                    return mid-1;
                } else {
                    j = mid-1;
                }
            } // there's no the case of nums[mid] < target
        }
        
        return -1;
        
    }
    
}
