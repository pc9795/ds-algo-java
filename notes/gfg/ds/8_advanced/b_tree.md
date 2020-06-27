B-Trees reduces the number of disk accesses. Most of the tree operations require O(h) disk accesses where h is the height 
of the tree. B-tree is a fat tree. The height of B-Trees is kept low by putting maximum possible keys in a B-Tree node. 
Generally, the B-Tree node size is kept equal to the disk block size.
 
**Properties**
 
* All leaves are at the same level. 
* A B-Tree is defined by the term minimum degree ‘t’. The value of t depends upon disk block size.
* Every node except root must contain at least t-1 keys. The root may contain minimum 1 key.
* All nodes (including root) may contain at most 2t – 1 keys.
* Number of children of a node is equal to the number of keys in it plus 1.
* All keys of a node are sorted in increasing order. The child between two keys k1 and k2 contains all keys in the range 
from k1 and k2.
* B-Tree grows and shrinks from the root which is unlike Binary Search Tree. Binary Search Trees grow downward and also 
shrink from downward.
* Like other balanced Binary Search Trees, time complexity to search, insert and delete is O(log n).
* The minimum height of the B-Tree that can exist with n number of nodes and m is the maximum number of children of a node 
can have is: `h_min =ceil(log_m(n + 1)) - 1`
* The maximum height of the B-Tree that can exist with n number of nodes and d is the minimum number of children that a 
non-root node can have is: `h_max =floor(log_t((n + 1)/2)` and `t = ceil(m/2)`.

**Proactive Insertion**

Before going down to a node, we split it if it is full. The advantage of splitting before is, we never traverse a node 
twice. If we don’t split a node before going down to it and split it only if a new key is inserted (reactive), we may end 
up traversing all nodes again from leaf to root. This happens in cases when all nodes on the path from the root to leaf 
are full. So when we come to the leaf node, we split it and move a key up. Moving a key up will cause a split in parent 
node (because the parent was already full). This cascading effect never happens in this proactive insertion algorithm. There 
is a disadvantage of this proactive insertion though, we may do unnecessary splits.
