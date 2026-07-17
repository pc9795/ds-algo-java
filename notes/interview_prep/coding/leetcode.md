# Learnings

* Size
    * Int - 2*10^9 and Long - 9*10^18
* Algorithms
    * General
        * Use a map to compare if two strings/numbers can be same after rearranging (set will ignore repetitions)
        * Permutations - swap (start, i)
        * Combinations - (mask & 1<<i) != 0
        * 2^n => 1<<n
    * Matrix
        * For in place algorithms we can use the input matrix as a storage cleverly
    * Graph
        * Detect cycles via union find

# Algorithms

* Library
    * Searching
      * ArrayList
        * Linear - indexOf("val")
        * Binary - Collections.binarySearch(sortedList, "val");
      * Sorting
        * Collections.sort(list);
* Techniques
  * Greedy
  * DP
  * Backtracking
  * Divide & Conquer
  * Bit manipulation
* Specific
  * Two pointers
  * Quick select - k minimum values with t=n and s=1 


# Data structures

## Don't need to remember

* Linked list
  * ArrayList - add(val), add(idx, val), remove(idx), remove(val)
* Stack
  * ArrayDeque - push(val), pop(), peek()
* Queue
  * ArrayDeque - offer(val), poll(), peek()
* Binary tree
  * Custom implementation
* Hashing
  * HashMap
* Graph
  * Custom implementation

## Need to remember

* BST
  * TreeMap - floorKey(), ceilingKey(), lowerKey(), higherKey()
  * TreeSet - for unique BST
* Segment tree
  * Custom implementation
* Union find
  * Custom implementation
* Heap
  * PriorityQueue - add(val), poll(), peek()

# Explanations where editorial was not straightforward

## 2505 - Bitwise OR for All Subsequence Sums

"1" at a bit of position X can come from

* Any number in the array
* Two 1's at X-1 positions of any two numbers in the array

You have to OR with the elements in the array to get from source 1. And if you keep doing prefix sum then if there are
two 1's they will give rise to another 1 which will be captured. You are only concerned with pairs so prefix sum works.