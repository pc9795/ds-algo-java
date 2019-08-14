**XOR Linked list** [In Java no implementation as it doesn't have addressed]

Instead of storing actual memory address, every node stores the XOR of addresses of previous and next nodes.

```cmd
A->B->C
to traverse from B to A
npx XOR address(C) [npx is the stored value ;npx field is having the XOR address
address(A) XOR address(C) XOR address(C)
address(A)

```

**Skip List**
* sqrt(n) nodes on the express lane. It will have two layers
* space - O(sqrt n)
* time - O(sqrt n)

**Self Organizing List** [for searching in linked list]

Place more frequently accessed items closet to head.
1. Move-to-front method -> Any node searched is moved to the front. This strategy is easy to implement, but it may
over-reward infrequently accessed items as it always move the item to front
2. Count method -> Each node stores count of the number of times it was searched. Nodes are ordered by decreasing count.
This strategy requires extra space for storing count.
3. Transpose method -> Any node searched is swapped with the preceding node.

T=O(n) -> searched element is always last element.

**Unrolled Linked List** -> Stores an array of elements at a node.

**Advantages**
1. Because of cache behaviour, linear search is much faster in unrolled linked lists.
2. In comparison to ordinary linked list, it required less storage space for pointers/references.
3. It performs operations like insertion, deletion and traversal more quickly than ordinary linked lists. (because search
is faster).

**Disadvantages** -> The overhead per node is comparatively high than singly linked lists.

**Binary Indexed Tree**
* Segment tree in which each segment start from 0.
* Fenwick tree requires less space but the complexity for query and update are same as segment tree.

```cmd
value & -value -> gives last set bit
ex- x=1101000
 -x=0010111 + 1 (2's complement)
   =0011000
```

