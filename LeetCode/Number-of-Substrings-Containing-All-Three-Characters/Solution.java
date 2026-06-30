# | A U B U C | -  | A | - | B | - | C | + | B ∩ C | + | A ∩ C | + | A ∩ B |

result =( n * (n + 1) ) // 2 #  | A U B U C | 

result += include('a') # | A | 
result += include('b') # | B | 
result += include('c') # | C | 
        
result -= exclude('a') #  | B |  + | C |  -  | B ∩ C | 
result -= exclude('b') #  | A | + | C |  -  | A ∩ C | 
result -= exclude('c') #  | A | + | B | - | A ∩ B | 