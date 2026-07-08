1class Solution {
2    static final int MOD = 1000000007;
3
4    public int[] sumAndMultiply(String s, int[][] queries) {
5        int n = s.length();
6        long[] prefixX = new long[n+1];
7        long[] prefixSum = new long[n+1];
8        int[] prefixLen = new int[n+1];
9
10        for (int i = 0; i < n; i++) {
11            char ch = s.charAt(i);
12            prefixX[i+1] = prefixX[i];
13            prefixSum[i+1] = prefixSum[i];
14            prefixLen[i+1] = prefixLen[i];
15            if (ch != '0') {
16                int d = ch - '0';
17                prefixX[i+1] = (prefixX[i] * 10 + d) % MOD;
18                prefixSum[i+1] = (prefixSum[i] + d) % MOD;
19                prefixLen[i+1] = prefixLen[i] + 1;
20            }
21        }
22
23        int[] ans = new int[queries.length];
24        for (int qi = 0; qi < queries.length; qi++) {
25            int l = queries[qi][0], r = queries[qi][1];
26            long sum = (prefixSum[r+1] - prefixSum[l] + MOD) % MOD;
27            int len = prefixLen[r+1] - prefixLen[l];
28            if (len == 0) {
29                ans[qi] = 0;
30                continue;
31            }
32            long x = (prefixX[r+1] - (prefixX[l] * pow10(len) % MOD) + MOD) % MOD;
33            ans[qi] = (int)((x * sum) % MOD);
34        }
35        return ans;
36    }
37
38    private long pow10(int exp) {
39        long res = 1, base = 10;
40        while (exp > 0) {
41            if ((exp & 1) == 1) res = (res * base) % MOD;
42            base = (base * base) % MOD;
43            exp >>= 1;
44        }
45        return res;
46    }
47}
48