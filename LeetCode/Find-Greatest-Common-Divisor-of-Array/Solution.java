class Solution {
public:
    int findGCD(vector<int>& A) {
        int n = A.size(), mod = n & 1;
        int i = 2 - mod;
        int b = max(A[0], A[1 - mod]);
        int a = b;

        for (; i < n; i += 2) {
            if (A[i] > A[i + 1]) {
                b = max(b, A[i]);
                a = min(a, A[i + 1]);
            } else {
                b = max(b, A[i + 1]);
                a = min(a, A[i]);
            }
        }

        return gcd(a, b);
    }
};