 ## Naive pattern searching

 ```
T=O(n) -> pattern is not in the string_algorithms [best case]
O(m*(n-m+1) -> all characters of pattern and string_algorithms are same or only last character is different. [worst case]

```
If all the characters in the input string are different. When a mismatch occurs after j matches, we know 
that the first character of pattern will not match the `j` matched characters because all characters of pattern are 
different. So we can always slide the pattern by `j` without missing any valid shifts.

## KMP

lps -> the longest proper prefix which is also suffix. A proper prefix doesn't consider the whole string_algorithms.

```
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

## Rabin Karp

`hash(txt[s+1..s+m])=(d(hash(txt[s..s+m-1])-txt[s]*h)+txt[s+m])mod q`

```
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
 
## Finite Automata
Number of states in FA will be `M+1` where `M` is the length of the pattern. Given a character `x` and 
a state `k`, we can get  the next state by considering the string `pat[0..k-1]x` and get the length of lps.

## 

If we want to preprocess text then we can use Suffix tree and if we want to preprocess pattern then we can use KMP.
The best possible time complexity achieved by first (preprocessing pattern) is O(n) and by second (preprocessing text) is O(m) where m and n are lengths of pattern and text respectively.

## Kasai Algorithm

LCP Array is an array of size `n` (like Suffix Array). A value `lcp[i]` indicates length of the longest common prefix of the suffixes indexed by `suffix[i]` and `suffix[i+1]`. `suffix[n-1]` is not defined as there is no suffix after it. 

Let lcp of suffix beginning at `txt[i]` be `k`. If `k` is greater than `0`, then lcp for suffix beginning at `txt[i+1]` will be at-least `k-1`. The reason is, relative order of characters remain same. If we delete the first character from both suffixes, we know that at least `k` characters will match.

```
txt[0..n-1] = "banana"
suffix[]  = {5, 3, 1, 0, 4, 2| 
lcp[]     = {1, 3, 0, 0, 2, 0}

Suffixes represented by suffix array in order are:
{"a", "ana", "anana", "banana", "na", "nana"}

for substring “ana”, lcp is 3, so for string “na” lcp will be at-least 2. 
```

## Z algorithm

For a string `str[0..n-1]`, Z array is of same length as string. An element `Z[i]` of Z array stores length of the longest substring starting from `str[i]` which is also a prefix of `str[0..n-1]`.

The idea is to concatenate pattern and text, and create a string P$T where P is pattern, $ is a special character should not be present in pattern and text, and T is text. Build the Z array for concatenated string. In Z array, if Z value at any point is equal to pattern length, then pattern is present at that point. 