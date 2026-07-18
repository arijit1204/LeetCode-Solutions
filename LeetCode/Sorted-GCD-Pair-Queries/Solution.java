1class Solution {
2    public int[] gcdValues(int[] nums, long[] queries) {
3        // Find the maximum value in the array
4        int maxValue = Arrays.stream(nums).max().getAsInt();
5
6        // Count frequency of each number
7        int[] frequency = new int[maxValue + 1];
8        for (int num : nums) {
9            frequency[num]++;
10        }
11
12        // Calculate the count of pairs with GCD equal to each value
13        long[] gcdPairCount = new long[maxValue + 1];
14
15        // Process from largest to smallest to apply inclusion-exclusion principle
16        for (int gcd = maxValue; gcd > 0; gcd--) {
17            // Count elements that are multiples of current gcd
18            int multiplesCount = 0;
19            for (int multiple = gcd; multiple <= maxValue; multiple += gcd) {
20                multiplesCount += frequency[multiple];
21                // Subtract pairs already counted with larger GCDs (inclusion-exclusion)
22                gcdPairCount[gcd] -= gcdPairCount[multiple];
23            }
24            // Add all possible pairs from elements that are multiples of gcd
25            // C(multiplesCount, 2) = multiplesCount * (multiplesCount - 1) / 2
26            gcdPairCount[gcd] += (long) multiplesCount * (multiplesCount - 1) / 2;
27        }
28
29        // Convert to prefix sum array for binary search
30        for (int i = 2; i <= maxValue; i++) {
31            gcdPairCount[i] += gcdPairCount[i - 1];
32        }
33
34        // Process each query using binary search
35        int queryCount = queries.length;
36        int[] result = new int[queryCount];
37        for (int i = 0; i < queryCount; i++) {
38            result[i] = search(gcdPairCount, queries[i]);
39        }
40
41        return result;
42    }
43
44    /**
45     * Binary search to find the smallest index where cumulative count > target.
46     * Uses standard template: feasible = cumulativeCounts[mid] > target
47     * @param cumulativeCounts prefix sum array of GCD pair counts
48     * @param target the query value to search for
49     * @return the GCD value corresponding to the target-th pair
50     */
51    private int search(long[] cumulativeCounts, long target) {
52        int left = 0;
53        int right = cumulativeCounts.length - 1;
54        int firstTrueIndex = cumulativeCounts.length;  // Default: not found
55
56        // Binary search for first position where cumulativeCounts[mid] > target
57        while (left <= right) {
58            int mid = left + (right - left) / 2;
59            if (cumulativeCounts[mid] > target) {
60                firstTrueIndex = mid;
61                right = mid - 1;  // Found one, look for earlier
62            } else {
63                left = mid + 1;
64            }
65        }
66
67        return firstTrueIndex;
68    }
69}