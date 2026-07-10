1class Solution {
2  public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
3    int[] ans = new int[queries.length];
4    int[] indexMap = new int[n];
5    int[] sortedNums = new int[n];
6    Pair<Integer, Integer>[] sortedNumAndIndexes = new Pair[n];
7
8    for (int i = 0; i < n; ++i)
9      sortedNumAndIndexes[i] = new Pair<>(nums[i], i);
10
11    Arrays.sort(sortedNumAndIndexes, Comparator.comparingInt(Pair::getKey));
12
13    for (int i = 0; i < n; ++i) {
14      final int num = sortedNumAndIndexes[i].getKey();
15      final int sortedIndex = sortedNumAndIndexes[i].getValue();
16      sortedNums[i] = num;
17      indexMap[sortedIndex] = i;
18    }
19
20    final int maxLevel = Integer.SIZE - Integer.numberOfLeadingZeros(n) + 1;
21    // jump[i][j] := the index of the j-th ancestor of i
22    int[][] jump = new int[n][maxLevel];
23
24    int right = 0;
25    for (int i = 0; i < n; ++i) {
26      while (right + 1 < n && sortedNums[right + 1] - sortedNums[i] <= maxDiff)
27        ++right;
28      jump[i][0] = right;
29    }
30
31    for (int level = 1; level < maxLevel; ++level)
32      for (int i = 0; i < n; ++i) {
33        final int prevJump = jump[i][level - 1];
34        jump[i][level] = jump[prevJump][level - 1];
35      }
36
37    for (int i = 0; i < queries.length; ++i) {
38      final int u = queries[i][0];
39      final int v = queries[i][1];
40      final int uIndex = indexMap[u];
41      final int vIndex = indexMap[v];
42      final int start = Math.min(uIndex, vIndex);
43      final int end = Math.max(uIndex, vIndex);
44      final int res = minJumps(jump, start, end, maxLevel - 1);
45      ans[i] = res == Integer.MAX_VALUE ? -1 : res;
46    }
47
48    return ans;
49  }
50
51  // Returns the minimum number of jumps from `start` to `end` using binary
52  // lifting.
53  private int minJumps(int[][] jump, int start, int end, int level) {
54    if (start == end)
55      return 0;
56    if (jump[start][0] >= end)
57      return 1;
58    if (jump[start][level] < end)
59      return Integer.MAX_VALUE;
60    int j = level;
61    for (; j >= 0; --j)
62      if (jump[start][j] < end)
63        break;
64    return (1 << j) + minJumps(jump, jump[start][j], end, j);
65  }
66}