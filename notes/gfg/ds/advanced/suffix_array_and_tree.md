## Suffix Array

A suffix array is a sorted array of all suffixes of a given string. A suffix array can be constructed from suffix tree by
doing a DFS traversal of the suffix tree. In fact suffix array and suffix tree both can be constructed from each other in
linear time.

#### Advantages of suffix array over suffix tree
* Improved space requirements
* Simple linear time construction alogrithms
* Improved cache locality

## Kasai's Algorithm

If the LCP of suffix beginning at txt[i] be k. if k is greater than 0, then LCP for suffix beginning at txt[i+1] will be
at-least k-1. 
```
txt = "banana"
suffix array = 5, 3, 1, 0, 4, 2
suffixes = "a", "ana", "anana", "banana", "na", "nana"
lcp array = 1, 3, 0, 0, 2, 0

lcp of "ana" is 3 it means that we have a suffix after "ana" which starts with "ana". In our case that is "anana"
so if we remove "a" from "ana" and "anana" we get "na" and "nana".
we can se that lcp of "na" is atleast k-1 which is 2. 
```

## Suffix Tree

Compressed Trie of all suffixes.
