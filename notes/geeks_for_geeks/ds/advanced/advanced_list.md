**XOR Linked list** 

In Java can't implement as no pointers.

Instead of storing actual memory address, every node stores the XOR of addresses of previous and next nodes. While traversing
the list we need to remember the address of the previously accessed node in order to calculate the next node's address.

```cmd
A->B->C
to traverse from B to A
npx is the value stored by each node.
>>> npx XOR addr(C)
>>> addr(A) XOR addr(C) XOR addr(C)
>>>addr(A)
```

**Skip List**
* Two layers; `n` nodes and `sqrt(n)` nodes on the express lane. It will have `sqrt(n)` nodes in the segment.
* space - `O(sqrt n)`
* time - `O(sqrt n)`; The worst case time complexity is number of nodes on "express lane" plus number of nodes in a 
segment.

**Self Organizing List** 

Place more frequently accessed items closet to head.

**Offline**
We know the complete search order in advance. Put the nodes according to decreasing frequencies of search.

**Online**
1. Move-to-front method -> Any node searched is moved to the front. This strategy is easy to implement, but it may
over-reward infrequently accessed items as it always move the item to front
2. Count method -> Each node stores count of the number of times it was searched. Nodes are ordered by decreasing count.
This strategy requires extra space for storing count.
3. Transpose method -> Any node searched is swapped with the preceding node. Unlike Move-to-front this method does not 
adapt quickly to changing access patterns.

`O(n)` -> searched element is always last element.

**Unrolled Linked List**  Stores an array of elements at a node.

**Advantages**
1. Because of cache behaviour, linear search is much faster in unrolled linked lists.
2. In comparison to ordinary linked list, it required less storage space for pointers/references.
3. It performs operations like insertion, deletion and traversal more quickly than ordinary linked lists. (because search
is faster).

**Disadvantages** -> The overhead per node is comparatively high than singly linked lists.