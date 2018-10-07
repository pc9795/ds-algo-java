package geeks_for_geeks.ds.advanced;

/*
 * XOR Linked list
 * ---------------
 * Instead of storing actual memory address, every node stores the XOR of addresses of previous and next
 * nodes.
 *
 * A->B->C
 * to traverse from B to A
 * npx XOR address(C) [npx is the stored value]
 * addressA XOR addressC XOR address(C)
 * addressA
 *
 * Skip List
 * ----------
 * space - O(sqrt n)
 * time - O(sqrt n)
 *
 * Self Organizing List [for searching in linked list]
 * ---------------------
 * Place more frequently accessed items closet to head.
 * 1. Move-to-front method
 * Any node searched is moved to the front. This strategy is easy to implement, but it may
 * over-reward infrequently accessed items as it always move the item to front
 * 2. Count method
 * Each node stores count of the number of times it was searched. Nodes are ordered by
 * decreasing count. This strategy requires extra space for storing count.
 * 3. Transpose method
 * Any node searched is swapped with the preceding node.
 *
 * T=O(n) -> searched element is always last element.
 *
 * Unrolled Linked List
 * ---------------------
 * Stores an array of elements at a node.
 *
 * Advantages
 * -----------
 * 1. Because of cache behaviour, linear search is much faster in unrolled linked lists.
 * 2. In comparison to ordinary linked list, it required less storage space for pointers/
 * references.
 * 3. It performs operations like insertion, deletion and traversal more quickly than
 * ordinary linked lists. (because search is faster).
 *
 * Disadvantages
 * -------------
 * The overhead per node is comparatively high than singly linked lists.
 *
 *
 *
 * */