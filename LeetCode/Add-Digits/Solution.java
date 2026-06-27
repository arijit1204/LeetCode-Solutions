1class Solution {
2    public int addDigits(int num) {
3        if (num == 0) return 0;
4        return 1 + (num - 1) % 9;
5    }
6}
7