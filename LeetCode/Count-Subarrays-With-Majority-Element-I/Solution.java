class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int result = 0;

        
        for (int start = 0; start < n; start++) {
            int countTarget = 0;
            for (int end = start; end < n; end++) {
                
                if (nums[end] == target) {
                    countTarget++;
                }
                int length = end - start + 1;

                
                if (2 * countTarget > length) {
                    result++;
                }
            }
        }

        return result;
    }
}
