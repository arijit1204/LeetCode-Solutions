1class Solution:
2    def smallestSubsequence(self, s: str) -> str:
3        last = {c: i for i, c in enumerate(s)}
4        stk = []
5        vis = set()
6        for i, c in enumerate(s):
7            if c in vis:
8                continue
9            while stk and stk[-1] > c and last[stk[-1]] > i:
10                vis.remove(stk.pop())
11            stk.append(c)
12            vis.add(c)
13        return "".join(stk)