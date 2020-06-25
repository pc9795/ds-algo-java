### Properties
* Every node has a color either red or black.
* Root of tree is always black.
* There are no two adjacent red nodes (A red node cannot have a red parent or red child).
* Every path from a node (including root) to any of its descendant NULL node has the same number of black nodes.

#

**Black height** is number of black nodes on a path from root to a leaf. Leaf nodes are also counted black nodes. From 
properties 3 and 4, we can derive, a Red-Black Tree of height h has black-height >= h/2.

Number of nodes from a node to its farthest descendant leaf is no more than twice as the number of nodes to the nearest 
descendant leaf.

Every Red Black Tree with n nodes has height `<= 2Log2(n+1)`. This can be proved using following facts:
1. For a general Binary Tree, let k be the minimum number of nodes on all root to NULL paths, then n >= 2^k – 1. This 
expression can also be written as k <= Log2(n+1).
```cmd
for k = 3, minimum number of node on all root to NULL paths, we should have at least a complete binary tree.
     o
   /  \
  o    o 
 / \  / \
o   oo   o

We are talking about "minimum" no of nodes here. Another tree with k=3 can be. It still extends a complete binary tree
that have nodes = 2^k-1 

     o
   /  \
  o    o 
 / \  / \
o   oo   o
    /     \
   o       o
          / \
         o   o
```
2. From property 4 of Red-Black trees and above claim, we can say in a Red-Black Tree with n nodes, there is a root to leaf 
path with at-most Log2(n+1) black nodes.
3. From property 3 of Red-Black trees, we can claim that the number black nodes in a Red-Black tree is at least ⌊n/2⌋ where 
n is the total number of nodes.

From above 2 points, we can conclude the fact that Red Black Tree with n nodes has height <= 2Log2(n+1)

### Applications
* Most of the self-balancing BST library functions like map and set in C++ (OR TreeSet and TreeMap in Java) use Red Black 
Tree.
* It is used to implement CPU Scheduling Linux. Completely Fair Scheduler uses it.

#

The main property that violates after insertion is two consecutive reds. In delete, the main violated property is, change 
of black height in subtrees as deletion of a black node may cause reduced black height in one root to leaf path.

