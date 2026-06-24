1class Solution {
2    static final int MOD = 1_000_000_007;
3
4    public int zigZagArrays(int n, int l, int r) {
5        int m = r - l + 1;
6        int size = 2 * m;
7        long[][] T = new long[size][size];
8        for (int x = 0; x < m; x++) {
9            for (int y = x + 1; y < m; y++) {
10                T[x][m + y] = 1;
11            }
12            for (int y = 0; y < x; y++) {
13                T[m + x][y] = 1;
14            }
15        }
16        long[] V = new long[size];
17        for (int x = 0; x < m; x++) {
18            V[x] = 1;
19            V[m + x] = 1;
20        }
21        long[][] Texp = matrixPower(T, n - 1);
22        long[] result = multiplyVectorMatrix(V, Texp);
23        long ans = 0;
24        for (long val : result) {
25            ans = (ans + val) % MOD;
26        }
27        return (int) ans;
28    }
29
30    private long[][] matrixPower(long[][] A, int exp) {
31        int n = A.length;
32        long[][] res = new long[n][n];
33        for (int i = 0; i < n; i++) res[i][i] = 1;
34        while (exp > 0) {
35            if ((exp & 1) == 1) res = multiplyMatrix(res, A);
36            A = multiplyMatrix(A, A);
37            exp >>= 1;
38        }
39        return res;
40    }
41
42    private long[][] multiplyMatrix(long[][] A, long[][] B) {
43        int n = A.length;
44        long[][] C = new long[n][n];
45        for (int i = 0; i < n; i++) {
46            for (int k = 0; k < n; k++) {
47                if (A[i][k] == 0) continue;
48                for (int j = 0; j < n; j++) {
49                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
50                }
51            }
52        }
53        return C;
54    }
55
56    private long[] multiplyVectorMatrix(long[] V, long[][] M) {
57        int n = V.length;
58        long[] res = new long[n];
59        for (int j = 0; j < n; j++) {
60            for (int i = 0; i < n; i++) {
61                res[j] = (res[j] + V[i] * M[i][j]) % MOD;
62            }
63        }
64        return res;
65    }
66}
67