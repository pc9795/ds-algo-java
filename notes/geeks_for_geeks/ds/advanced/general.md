**Trie vs Hashing**

Trie is more powerful in case of prefix search. Moreover, time complexity of hashing is O(1) in average case, it may 
have collisions.

**Binary Indexed Tree**
* Segment tree in which each segment start from 0.(Stores consecutive sums)
* Fenwick tree requires less space but the complexity for query and update are same as segment tree.

```cmd
last set bit
>>> value & -value 

for x=1101000
>>> -x = 0010111 + 1 (2's complement)
>>> 0011000
```
```cmd
               0
  /     /       \           \
 1     10     100           1000
       |    /    \      /    |       \
       11   101  110  1001 1010    1100
                  |          |    /    \
                 111       1011  1101 1110
                                         |
                                        1111
                                        
Update Operation
right siblings + right siblings of the parent.
 
1 -> 1 2 4 8 
2 -> 2 4 8 
3 -> 3 4 8 
4 -> 4 8 
5 -> 5 6 8 
6 -> 6 8 
7 -> 7 8 
8 -> 8 
9 -> 9 10 12 
10 -> 10 12 
11 -> 11 12 
12 -> 12

Sum Operation
The child node x of the node y stores the sum of the elements between y(inclusive) and x(exclusive): arr[y,…,x). 
```

**Range Update and Point Query**

Fenwick tree can be used for Range updates and Point query like Difference Array. Instead of storing actual array we
can store Difference array in Fenwick tree. Use `update` to update prefix sum array and `query` to get the actual element
by computing the prefix sum.

Name|Range update|Point query
---|---|---|
Difference Array|O(1)|O(n)
Fenwick Tree|O(log n)|O(log n)

**Range Update and Range Query**
```cmd
suppose we have an array
>>> 0 0 0 0 0 0
if we done a range update from 1,4 with value 5 then resultant array would be
>>> 0 5 5 5 5 0
if we have cumulative sums then we can answer range sum queries quickly. The cumluative sum would look like
>>> 0 5 10 15 20 20

so if we have 2 arrays 
>>> arr1 = 0 5 5 5 5 0
>>> arr2 = 0 0 0 0 0 -20
then cumulative sum can be caluclated using formula (i*arr1[i] - arr2[i])

we can store arrays in BIT if we store their prefix sums. The prefix sums of these arrays would look like
>>> prefix_sum1 = 0 5 0 0 0 -5
>>> prefix_sum2 = 0 0 0 0 0 -20

so starting from start if we have two prefix sums for the original array. (Assuming prefix sums are stored in BIT).
>>> original = 0 0 0 0 0 0
>>> prefix_sum1 = 0 0 0 0 0 0
>>> prefix_sum2 = 0 0 0 0 0 0

in case of a range update (1,4) with value 5 if we use following technique.
update prefix_sum1 at l with val and at r+1 with -val. 1 with 5 and 5 with -5.
update prefix_sum2 at l with val*(l-1) and at r+1 with -val*r. 1 with 0 and 5 with -20
then we can calculate cumulative sum with the help of these. And can calculate range sum.

https://stackoverflow.com/questions/27875691/need-a-clear-explanation-of-range-updates-and-range-queries-binary-indexed-tree/27877427#27877427 
```

**2D Binary Indexed Tree**
```cmd
 (1, 1) -> (1, 1)(1, 2)(2, 1)(2, 2)
 (2, 1) -> (2, 1)(2, 2)
 (3, 1) -> (3, 1)(3, 2)
 (1, 2) -> (1, 2)(2, 2)
 (2, 2) -> (2, 2)
 (3, 2) -> (3, 2)
 (1, 3) -> (1, 3)(2, 3)
 (2, 3) -> (2, 3)
 (3, 3) -> (3, 3)
```
