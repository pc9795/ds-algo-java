**Suffix Array**

A suffix array is a sorted array of all suffixes of a given string. A suffix array can be constructed from suffix tree by
doing a DFS traversal of the suffix tree. In fact suffix array and suffix tree both can be constructed from each other in
linear time.

**Advantages of suffix array over suffix tree**
* Improved space requirements
* Simple linear time construction alogrithms
* Improved cache locality

```cmd
rank and next rank is according to alphabets ordering(initially)
banana 1  0
anana  0  13
nana   13 0
ana    0  13
na     13 0
a      0  -1

sort according to rank and next rank
a      0  -1
anana  0  13
ana    0  13
banana 1  0
nana   13 0
na     13 0

reinitialize rank. next rank is now will be based on rank. now when we sort it will be sorted according to 4 characters 
as rank and next rank have the ordering of first 2 letters.
a       0  -1
anana   1  1
ana     1  0
banana  2  3
nana    3  3
na      3  -1
```

**Kasai's Algorithm**

If the LCP of suffix beginning at txt[i] be k. if k is greater than 0, then LCP for suffix beginning at txt[i+1] will be
at-least k-1. 
```cmd
txt = "banana"
suffix array = 5, 3, 1, 0, 4, 2
suffixes = "a", "ana", "anana", "banana", "na", "nana"
lcp array = 1, 3, 0, 0, 2, 0

lcp of "ana" is 3 it means that we have a suffix after "ana" which starts with "ana". In our case that is "anana"
so if we remove "a" from "ana" and "anana" we get "na" and "nana".
we can se that lcp of "na" is atleast k-1 which is 2. 
```

**Suffix Tree**

Compressed Trie of all suffixes.
