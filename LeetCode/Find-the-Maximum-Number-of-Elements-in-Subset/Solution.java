1import java.util.*;
2
3class Solution {
4    public int maximumLength(int[] nums) {
5        Map<Integer, Integer> freq = new HashMap<>();
6        for (int num : nums) {
7            freq.put(num, freq.getOrDefault(num, 0) + 1);
8        }
9
10        int ans = 0;
11        if (freq.containsKey(1)) {
12            int count = freq.get(1);
13            ans = Math.max(ans, count % 2 == 0 ? count - 1 : count);
14        }
15
16        for (int x : freq.keySet()) {
17            if (x == 1) continue;
18            int length = 1;
19            int cur = x;
20            while (true) {
21                long next = (long) cur * cur;
22                if (next > 1e9 || !freq.containsKey((int) next)) break;
23                if (freq.get(cur) < 2) break;
24                length += 2;
25                cur = (int) next;
26            }
27            ans = Math.max(ans, length);
28        }
29
30        return ans;
31    }
32}
33