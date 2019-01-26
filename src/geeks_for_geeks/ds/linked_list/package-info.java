package geeks_for_geeks.ds.linked_list;

/*
 * Disadvantages of Arrays:
 * ------------------------
 * 1. Size of arrays is fixed. So we must know the upper limit on number of elements in advance. -> Dynamic Size
 * 2. Inserting an element in the array is expensive. -> Ease of insertion/deletion
 *
 * Disadvantages of Linked Lists:
 * ------------------------------
 * 1. Random access is not allowed.
 * 2. Extra memory space for pointers.
 * 3. Not cache friendly
 *
 * ---
 * Merge sort in doubly linked list is same as Linked List; only prev pointers have to be updated.
 *
 * ---
 * Circular linked list can be used in place of queue. We can maintain a pointer to the last
 * inserted node and front can always be obtained as next of last.
 *
 * ---
 * [outside loop] [inside loop]
 * 50 --> 20 --> 15 --> 4
 *               ^      |
 *               |      V [outside loop]
 *               | <-- 10
 *
 * outside loop+inside loop = loop distance. So one pointer at the head and other pointer with loop distance away from
 * the head meet at the loop starting point.
 *
 * Proof of removing loop using floyd cycle detection algo:
 * --------------------------------------------------------
 * Distance travelled by hare = 2* Distance travelled by tortoise
 *
 * Distance from head + (no of times hare travelled the loop)*cycleLength + distance from start of the loop to the point
 * where they first met = 2*(Distance from head + (no of times tortoise travelled the loop)*cycleLength + distance from start
 * of the loop to the point where they first met.)
 *
 * m = distance from head
 * x = no of times hare travelled the loop
 * y = no of times tortoise travelled the loop
 * L = cycle Length
 * k= distance from start of the loop to the point where they first met.
 *
 * m + x*L +k = 2*(m +y*L +k)
 * m + k = (x-2y)L
 *
 * m + k is a multiple of Loop length
 * m + k = K*L
 * so after traveling (K-1)*L from m, we will have outside loop + inside loop condition as in the above figure.
 *
 * ---
 * Quick sort can be implemented for Linked List only when we can pick a fixed point as pivot. Random Quick sort cannot
 * be efficiently implemented for Linked lists by piking random pivot.
 *
 *
 *
 * */