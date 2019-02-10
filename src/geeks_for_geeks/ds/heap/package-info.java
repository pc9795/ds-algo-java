package geeks_for_geeks.ds.heap;

/*
 * Properties
 * -----------
 * 1. It's a complete tree(All levels are completely filled except possibly the last level and the last
 * level has all keys as left as possible.
 * 2. The key at root must be minimum among all keys present in Binary Heap. The same property must be
 * recursively true for all nodes in Binary tree. (Min heap)
 *
 *
 * (i-1)/2 parent
 * (2*i)+1 left
 * (2*i)+2 right
 *
 * Binary heap is preferred for priority queue
 * -------------------------------------------
 * 1. Implemented using arrays - cache friendly and no extra space for pointers.
 * 2. We can build a Binary heap in O(n) time. Self Balancing BSTs require O(n logn) time to construct.
 * 3. Fibonacci Heap can support insert and decrease key in O(1) time
 *
 * Disadvantages
 * --------------
 * 1. Searching is O(n)
 * 2. Printing all elements require O(n logn).
 *
 *
 * Binomial Tree
 * --------------
 * A Binomial tree of order k can be constructed by taking two binomial trees of order k-1 and making one as
 * leftmost child or other.
 *
 * Properties
 * -----------
 * 1. It has exactly 2^k nodes
 * 2. It has depth as k
 * 3. There are exactly kCi nodes at depth i for i-0,1....k
 * 4. The root has degree k and children of root are themselves Binomial trees with order k-1, k-2,..0 from
 * left to right
 *
 * Binomial Heap
 * -------------
 * Set of binomial trees where each binomial tree follows min heap property. And there can be at most one
 * binomial tree of any degree.
 *
 * A binomial heap with n nodes has the number of Binomial trees equal to the number of set bits in the binary
 * representation of n.
 *
 * Fibonacci Heap
 * --------------
 * 1.have pointer to minimum value
 * 2.do things lazy way - merge operation simply link two heaps, insert operation simply adds a new tree with
 * single node
 * 3.extract min is most complicated operation. It does the delayed work of consolidating the trees.
 * 4.has a degree at most O(log n)
 * 5.size of a subtree in a node of degree is at least Fk+2, whee Fk is kth fibonacci number.
 *
 * Heap sort
 * ---------
 * 1.We heapify from bottom to top; If small elements are present in the bottom and we heapify from top then we will get
 * incorrect results.
 * 2.In place sort
 * 3.Not stable
 * 4.O(N log n)
 *
 * Tournament Tree
 * ---------------
 * Complete binary tree
 * Every external node represents a player and internal node represents winner.
 *
 * Ques -> select smallest one million elements from one billion unsorted elements.
 *
 * Approach 1-> sort the billion numbers and select first one million.
 *
 * NOTE: if n leaf nodes then total nodes are n + n - 1 = 2n-1 for complete binary tree.
 *
 * Approach 2-> sorting 1000 lists of one million each + tree construction + tournaments
 * each list will be at the leaf and from each list one element participate in the
 * tournament and winner is removed from the list.
 *
 *
 *
 *
 *
 * */