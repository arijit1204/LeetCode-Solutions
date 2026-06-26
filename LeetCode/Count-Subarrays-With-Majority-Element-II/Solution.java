import java.util.*;

class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        boolean found = false;
        
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            if (nums[i] == target) {
                arr[i] = 1;
                found = true;
            } else {
                arr[i] = -1;
            }
        }
        if (!found) return 0; 
        
        
        long[] pref = new long[n + 1];
        pref[0] = 0;
        for (int i = 1; i <= n; i++) {
            pref[i] = pref[i - 1] + arr[i - 1];
        }
        
        //Coordinate compression
        long[] sorted = pref.clone();
        Arrays.sort(sorted);
        Map<Long, Integer> comp = new HashMap<>();
        int idx = 1;
        for (long v : sorted) {
            if (!comp.containsKey(v)) {
                comp.put(v, idx++);
            }
        }
        
        // Fenwick tree
        Fenwick fenwick = new Fenwick(idx + 2);
        long ans = 0;
        
        for (int j = 0; j <= n; j++) {
            int pos = comp.get(pref[j]);
           
            ans += fenwick.query(pos - 1);
           
            fenwick.update(pos, 1);
        }
        
        return ans;
    }
    
    
    static class Fenwick {
        long[] bit;
        int n;
        Fenwick(int n) {
            this.n = n;
            bit = new long[n + 1];
        }
        void update(int i, long delta) {
            while (i <= n) {
                bit[i] += delta;
                i += i & -i;
            }
        }
        long query(int i) {
            long sum = 0;
            while (i > 0) {
                sum += bit[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}
