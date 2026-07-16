1class Solution {
2    public long gcdSum(int[] nums) {
3        int n = nums.length;
4        int[] prefixGcd = new int[n];
5        int mx = 0;
6
7        for (int i = 0; i < n; i++) {
8            int x = nums[i];
9            mx = Math.max(mx, x);
10            prefixGcd[i] = gcd(x, mx);
11        }
12
13        Arrays.sort(prefixGcd);
14
15        long ans = 0;
16        for (int i = 0; i < n / 2; i++) {
17            ans += gcd(prefixGcd[i], prefixGcd[n - i - 1]);
18        }
19
20        return ans;
21    }
22
23    private int gcd(int a, int b) {
24        while (b != 0) {
25            int t = a % b;
26            a = b;
27            b = t;
28        }
29        return a;
30    }
31}