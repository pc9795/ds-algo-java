 **Naive pattern searching**
 ```cmd
T=O(n) -> pattern is not in the string_algorithms [best case]
O(m*(n-m+1) -> all characters of pattern and string_algorithms are same or only last character is different. [worst case]

```
If all of the characters in the input string_algorithms are different. When a mismatch occurs after j matches, we know 
that the first character of pattern will not match the j matched characters because all characters of pattern are 
different. So we can always slide the pattern by j without missing any valid shifts.

**KMP**

lps -> longest proper prefix which is also suffix. A proper prefix doesn't consider the whole string_algorithms.
```cmd
                    j             i
a c a c a b a c a c a b a c a c  a  c
0 0 1 2 3 0 1 2 3 4 5 6 7 8 9 10 11

now b!=c
so we have to look into substring a c a c a b a c a c a
at this point lps was 5
___________   ___________
|a c a c a| b |a c a c a| b a c a c  a  c
these part match
          ___________   ____________
a c a c a b |a c a c a| b |a c a c  a|  c
these part match
___________               ____________
|a c a c a| b a c a c a b |a c a c  a|  c
therefore these parts match

we start from lps[j-1]

```

**Rabin Karp**

`hash(txt[s+1..s+m])=(d(hash(txt[s..s+m-1])-txt[s]*h)+txt[s+m])mod q`
eg.,
```cmd
txt=ABCDEF
pattern=XYZ
hash=256^2*A+256*B+C
pattern=256^2*X+256*Y+Z

now for next shift.
h is the highest power of d at a particular time to remove the first character
q is a prime number to reduce the value
d is again multiply to make second highest to highest and add a degree to all.
=(256^2*A+256*B+C -256^2*A)*256+D
=256^2*B+256*C+D

```
 
**Finite Automata** -> Number of states in FA will be M+1 where M is the length of the pattern. Given a character x and 
a state k, we can get  the next state by considering the string "pat[0..k-1]x" and get the length of lps.