Singly Linked List
--

**Disadvantages of Arrays:**
1. Size of arrays is fixed. So we must know the upper limit on number of elements in advance. -> Dynamic Size
2. Inserting an element in the array is expensive. -> Ease of insertion/deletion

**Disadvantages of Linked Lists:**
1. Random access is not allowed.
2. Extra memory space for pointers.
3. Not cache friendly. Arrays are contiguous memory blocks, so large chunks of them will be loaded into the cache upon 
first access. This makes it comparatively quick to access future elements of the array. Linked lists on the other hand 
aren't necessarily in contiguous blocks of memory, and could lead to more cache misses, which increases the time it 
takes to access them.

Array memory is allocated from Data section(global array) or Stack section(local array). However memory for linked list
is allocated from Heap section.

Merge sort in doubly linked list is same as Linked List; only prev pointers have to be updated.

**Explanation of a method of removing floyd cycle detection algorithm**
```
1. Detect Loop using Floyd’s Cycle detection algorithm and get the pointer to a loop node.
2. Count the number of nodes in loop. Let the count be k.(Can be calculated by starting counting from the loop node)
3. Fix one pointer to the head and another to a kth node from the head.
4. Move both pointers at the same pace, they will meet at loop starting node.
5. Get a pointer to the last node of the loop and make next of it as NULL.

Case 1: When the no of nodes outside the loop are more than k. 
( 5, 6 and 7 have a loop)
1 - 2 - 3 - 4 - 5
                6
                7 
Case 2: When the no of nodes outside the loop are less than k.(Equal can be treated as a special case of this)
1 - 2 - 3
        4
        5
        
So for case 1 if we have 'x' nodes outside the loop then we don't have to care about x - (x % k) nodes. The exact 
multiples of the k. As they can be treated as just traversing the loop. In case 1, traversing 2, 3 and 4 is same as 
traversing 5, 6 and 7. So case 1 can be reduced to case 2.
1 - 5
    6
    7
Where 1st pointer is at 1 and second at 7.

Now for case 2. we are placing a pointer in the loop with such a offset that after some steps the both pointers meet at 
the loop starting point.
```

**Proof of a method of removing loop using floyd cycle detection algorithm**:
```
>>> Distance travelled by hare = 2 * Distance travelled by tortoise
>>> Distance from head + (no of times hare travelled the loop) * cycleLength + distance from start of the loop to the point
where they first met = 2 * (Distance from head + (no of times tortoise travelled the loop) * cycleLength + distance from
start of the loop to the point where they first met.)

m = distance from head
x = no of times hare travelled the loop
y = no of times tortoise travelled the loop
L = cycle Length
k = distance from start of the loop to the point where they first met.
 
m + (x * L) + k = 2 * (m + (y * L) + k)
m + k = (x - (2 * y)) * L

m + k is a multiple of Loop length
m + k = K * L
so after traveling (K - 1) * L from m, we will have outside loop + inside loop condition as in below.

[outside loop] [inside loop]
 50 --> 20 --> 15 --> 4
               ^      |
               |      V [outside loop]
               | <-- 10
outside loop + inside loop = loop distance. So one pointer at the head and other pointer with loop distance away from
the head meet at the loop starting point.
```

Quick sort can be implemented for Linked List only when we can pick a fixed point as pivot. Random Quick sort cannot be 
efficiently implemented for Linked lists by piking random pivot.

Circular Linked List
--
**Advantages**
* Any node can be starting point.
* Useful in implementing queue. We don't need `front` and `rear` we only maintain `last`.