**Properties**
1. It's a complete tree.
2. The key at root must be minimum among all keys present in Binary Heap. The same property must be recursively true for
all nodes in Binary tree. (Min heap)

**Indexes**
* (i-1)/2 -> parent
* (2*i)+1 -> left
* (2*i)+2 -> right

**Balancing Binary search tree can be used to implement priority queue**
1. Finding minimum and maximum are not naturally O(1), but can be easily implemented in O(1) by keeping an extra pointer
to minimum or maximum and updating the pointer with insertion and deletion if required. With deletion we can update by
finding inorder predecessor and successor.
2. Inserting is O(log n).
3. Removing maximum/minimum is O(log n).
4. Decrease key can be done in O(log n) by doing a deletion followed by insertion.

**Binary heap is preferred for priority queue**
1. Implemented using arrays - cache friendly and no extra space for pointers.
2. Although operations are of same time complexity, constants in BST are higher
2. We can build a Binary heap in O(n) time. Self Balancing BSTs require O(n*log n) time to construct.
3. Fibonacci Heap can support insert and decrease key in O(1) time

**Disadvantages**
1. Searching is O(n)
2. Printing all elements require O(n logn).

**Binomial Tree** 
A Binomial tree of order 0 has 1 node. A Binomial tree of order k can be constructed by taking two binomial trees of 
order k-1 and making one as leftmost child or other.
1. It has exactly 2^k nodes. 2^(k-1) + 2^(k-1) = 2^k
2. It has depth as k
3. There are exactly kCi nodes at depth i for i-0,1....k
4. The root has degree k and children of root are themselves Binomial trees with order k-1, k-2,..0 from left to right

**Binomial Heap** -> Set of binomial trees where each binomial tree follows min heap property. And there can be at most 
one binomial tree of any degree.

A Binomial heap with n nodes has the number of Binomial trees equal to the number of set bits in the binary representation 
of n. There are O(log n) Binomial trees in a Binomial heap.

**Fibonacci Heap** 
Collection of trees with min-heap or max-heap property. In Fibonacci Heap, trees can have any shape even all trees can be
single nodes. 
1. have pointer to minimum value. All tree roots are connected using circular double linked list, so all of them can be
accessed using single 'min' pointer.
2. do things lazy way - merge operation simply link two heaps, insert operation simply adds a new tree with single node
3. extract min is most complicated operation. It does the delayed work of consolidating the trees. This makes delete also
complicated as delete first decreases key to minus infinite, then calls extract minimum.
4. Although Fibonacci heap looks promising time complexity wise, it has been found slow in practice as hidden constants
are high.
4. Every node has a degree at most O(log n)
5. size of a subtree in a node of degree k is at least Fk+2, whee Fk is kth fibonacci number.

**Tournament Tree**
* Complete binary tree
* Every external node represents a player and internal node represents winner.
* To select the best player among N players, (N-1) players will be eliminated. We need minimum of(N-1) games/comparisons.
* To select the second best player we need to trace the candidates participated with final winner.

We need a tree of height ceil(log2 m) to have at least m external nodes

If n leaf nodes then total nodes are n + n - 1 = 2n-1 for complete binary tree.

Select smallest one million elements from one billion unsorted elements.
* Approach 1-> sort the billion numbers and select first one million. Huge memory requirements 
* Approach 2-> sorting 1000 lists of one million each + tree construction + tournaments; each list will be at the leaf and
from each list one element participate in the tournament and winner is removed from the list.
