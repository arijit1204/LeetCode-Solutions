1import java.util.List;
2import java.util.ArrayList;
3import java.util.HashMap;
4import java.util.Map;
5
6class Solution {
7    public int subsequencePairCount(int[] nums) {
8        int MOD = (int)1e9 + 7;
9        int MAX_VAL = 200;
10
11        int[] mu = new int[MAX_VAL + 1];
12        int[] lp = new int[MAX_VAL + 1];
13        List<Integer> primes = new ArrayList<>();
14        mu[1] = 1;
15
16        for (int i = 2; i <= MAX_VAL; i++) {
17            if (lp[i] == 0) {
18                lp[i] = i;
19                primes.add(i);
20                mu[i] = -1;
21            }
22            for (int p : primes) {
23                if (p > lp[i] || i * p > MAX_VAL) {
24                    break;
25                }
26                lp[i * p] = p;
27                if (p == lp[i]) {
28                    mu[i * p] = 0;
29                } else {
30                    mu[i * p] = -mu[i];
31                }
32            }
33        }
34
35        Map<Integer, Integer> freq = new HashMap<>();
36        for (int num : nums) {
37            freq.put(num, freq.getOrDefault(num, 0) + 1);
38        }
39
40        long total_pairs = 0;
41
42        for (int g = 1; g <= MAX_VAL; g++) {
43            Map<Integer, Integer> T_freq = new HashMap<>();
44            int max_t = 0;
45            for (int j = g; j <= MAX_VAL; j += g) {
46                if (freq.containsKey(j) && freq.get(j) > 0) {
47                    int val = j / g;
48                    T_freq.put(val, freq.get(j));
49                    max_t = Math.max(max_t, val);
50                }
51            }
52
53            if (T_freq.isEmpty()) {
54                continue;
55            }
56
57            int[] count_multiples_t = new int[max_t + 1];
58            for (int m = 1; m <= max_t; m++) {
59                for (int j = m; j <= max_t; j += m) {
60                    if (T_freq.containsKey(j)) {
61                        count_multiples_t[m] += T_freq.get(j);
62                    }
63                }
64            }
65
66            long C_T = 0;
67            for (int d1 = 1; d1 <= max_t; d1++) {
68                int mu1 = mu[d1];
69                if (mu1 == 0) {
70                    continue;
71                }
72                for (int d2 = 1; d2 <= max_t; d2++) {
73                    int mu2 = mu[d2];
74                    if (mu2 == 0) {
75                        continue;
76                    }
77
78                    int l = (d1 * d2) / gcd(d1, d2);
79
80                    int k1 = count_multiples_t[d1];
81                    int k2 = count_multiples_t[d2];
82                    int k12 = 0;
83                    if (l <= max_t) {
84                        k12 = count_multiples_t[l];
85                    }
86
87                    long term_val_part1 = power(3, k12, MOD);
88                    long term_val_part2 = power(2, k1 - k12, MOD);
89                    long term_val_part3 = power(2, k2 - k12, MOD);
90
91                    long term_val = (term_val_part1 * term_val_part2) % MOD;
92                    term_val = (term_val * term_val_part3) % MOD;
93
94                    term_val = (term_val - power(2, k1, MOD) + MOD) % MOD;
95                    term_val = (term_val - power(2, k2, MOD) + MOD) % MOD;
96                    term_val = (term_val + 1) % MOD;
97
98                    if (mu1 * mu2 == 1) {
99                        C_T = (C_T + term_val) % MOD;
100                    } else {
101                        C_T = (C_T - term_val + MOD) % MOD;
102                    }
103                }
104            }
105
106            total_pairs = (total_pairs + C_T) % MOD;
107        }
108
109        return (int)total_pairs;
110    }
111
112    private int gcd(int a, int b) {
113        if (b == 0) {
114            return a;
115        }
116        return gcd(b, a % b);
117    }
118
119    private long power(long base, long exp, long mod) {
120        long res = 1;
121        base %= mod;
122        while (exp > 0) {
123            if (exp % 2 == 1) res = (res * base) % mod;
124            base = (base * base) % mod;
125            exp /= 2;
126        }
127        return res;
128    }
129}