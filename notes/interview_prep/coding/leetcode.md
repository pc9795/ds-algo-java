# Algorithms

* Linear DS 
    * Searching
        * ArrayList
            * Linear - indexOf("val")
            * Binary - Collections.binarySearch(sortedList, "val");
        * Binary search
          * Rotated sorted arrays
          * Continuous space
    * Sorting
        * ArrayList
            * Collections.sort(list);
* Bit manipulation
* Optimization - find the best
    * Greedy
        * Local optimum 
        * Optimal substructure
    * Backtracking
      * Search with pruning and keeping track of the best
      * Permutations - swap (start, i)
      * Combinations - (mask & 1<<i) != 0
    * DP
        * Overlapping subproblems - recursion tree will have repetition
        * Optimal substructure
        * Top-down - recursive function + memoization
        * Bottom-up - iteration + DP array
* Divide & Conquer
* Specific
    * Two pointers
      * Fixed/variable sliding window
      * Tortoise hare
      * Sorted arrays
    * Quick select - k minimum values with t=n and s=1


# Data structures

* Array
  * Insertion (front/middle/end) - n n 1
  * Deletion (front/middle/end) - n n 1
  * Search (by value/by index) - n 1
* Matrix
    * For in place algorithms we can use the input matrix as a storage cleverly
* Linked list
    * Insertion (front/middle/end) - 1 n 1
    * Deletion (front/middle/end) - 1 n 1
    * Search (by value/by index) - n X
    * ArrayList - add(val), add(idx, val), remove(idx), remove(val)
    * Fixed ordering
      * Stack
        * ArrayDeque - push(val), pop(), peek()
        * Monotonic stack
      * Queue
        * ArrayDeque - offer(val), poll(), peek()
        * Monotonic queue - #239
* Hashing
    * HashMap
* BST
    * Insertion/Deletion/Search - ln ln ln
    * TreeMap - floorKey(val), ceilingKey(val), lowerKey(val), higherKey(val), firstKey(), lastKey()
    * TreeSet - for unique BST
* Graph
    * Cycle detection
      * Undirected - Union find
      * Directed - Topological sort
    * Shortest path 
      * Non-negative edge weight
        * Undirected & Directed - Dijkstra
    * Topological sort
* Specific
  * Union find
    * O(1) if implemented by rank and path compression
  * Heap
    * PriorityQueue - add(val), poll(), peek()

  * Prefix tree
  * Segment tree
* Segment tree
* Union find
* Heap
    * PriorityQueue - add(val), poll(), peek()

# Catchall

* Int - 2*10^9 and Long - 9*10^18
* 2^n => 1<<n
* Character
  * isLetterOrDigit, toLowerCase

# Explanations where editorial was not straightforward

## 2505 - Bitwise OR for All Subsequence Sums

"1" at a bit of position X can come from

* Any number in the array
* Two 1's at X-1 positions of any two numbers in the array

You have to OR with the elements in the array to get from source 1. And if you keep doing prefix sum then if there are
two 1's they will give rise to another 1 which will be captured. You are only concerned with pairs so prefix sum works.