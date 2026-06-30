# | A U B U C | -  | A | - | B | - | C | + | B ∩ C | + | A ∩ C | + | A ∩ B |

result =( n * (n + 1) ) // 2 #  | A U B U C | 

result += include('a') # | A | 
result += include('b') # | B | 
result += include('c') # | C | 
        
result -= exclude('a') #  | B |  + | C |  -  | B ∩ C | 
result -= exclude('b') #  | A | + | C |  -  | A ∩ C | 
result -= exclude('c') #  | A | + | B | - | A ∩ B | 


class Solution {
    public int numberOfSubstrings(String s) {
        int n = s.length();
        int[] count = new int[3]; 
        int res = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            count[s.charAt(right) - 'a']++ ;
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                res += n - right; 
                count[s.charAt(left) - 'a']--;
                left++;
            }
        }
        return res;
    }
}
