1class Solution {
2    public int[] arrayRankTransform(int[] arr) {
3        if (arr == null || arr.length == 0) return new int[0];
4        
5        int[] sorted = arr.clone();
6        Arrays.sort(sorted);
7        
8        Map<Integer, Integer> rankMap = new HashMap<>();
9        int rank = 1;
10        for (int num : sorted) {
11            if (!rankMap.containsKey(num)) {
12                rankMap.put(num, rank++);
13            }
14        }
15        
16        int[] result = new int[arr.length];
17        for (int i = 0; i < arr.length; i++) {
18            result[i] = rankMap.get(arr[i]);
19        }
20        
21        return result;
22    }
23}
24