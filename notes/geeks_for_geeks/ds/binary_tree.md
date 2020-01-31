```cmd

       f  <--root and parent of a
      / \
     a   b <-- child of f
    /
   r  <-- leaf node

```

2 children -> binary tree

**Properties of binary tree**
1. The maximum number of nodes at level 'l' of a binary tree is 2^(l-1);
2. Maximum number of nodes in a binary tree of height 'h' is (2^h)-1. 1 + 2 + 2^2 + ... 2^(h-1)
3. In a binary tree with N nodes, minimum possible height is ceil(log2(N+1)) -> from point 2
4. A binary tree with L leaves has at least ceil(log2L)+1 levels; L <= 2^(l-1); For minimum no of levels we can assume
that all leaves are at last level and all levels are filled.
5. In Binary tree where every node has 0 or 2 children, number of leaf nodes is always one more than nodes with two 
children -> Handshaking leema.

**Handshaking leema**
-----------------
1. In a k-ary tree where every node has either 0 or k children
```cmd
L(Number of leaf node), I(Number of internal nodes)
>>> L = ((k-1)*I) + 1 

case 1. Root is leaf.
>>> L = 1, I = 0 [proved]

case 2. Tree is undirected acyclic graph
edges = nodes - 1
>>> |E| = L + I - 1 

>>> sum of all degrees = 2 * sum of edges
>>> sum of degrees of leaves + sum of degrees for internal nodes except root + root's degree = 2 * (nodes - 1)
>>> L + (I - 1)*(k + 1) + k = 2 *(L + I - 1)
>>> L = ((k - 1)*I) + 1 [proved]

```

2. In a binary tree number of leaf nodes is always one more than nodes with two children.

```cmd
L(Number of leaf node), T( Number of internal nodes with two children)
>>> L = T + 1

case 1. Root is leaf
>>> T=0, L=1 [proved]

case 2. Root has two children
>>> sum of degree of nodes with two children except root + sum of degrees of nodes with one child + 
sum of degrees of leaves + root's degree = 2 *(no of nodes - 1)
>>> (T - 1)*3 + (S * 2) + L + 2 = (S + T + L - 1)*2
>>> T = L - 1 [proved]

case 3. Root has one child
>>> T * 3 + ((S - 1)*2) + L + 1 = (S + T + L - 1)*2
>>> T = L - 1 [proved]
```

**Types of Binary Trees**
1. Full Binary Tree - every node has 0 or 2 children
2. Complete Binary Tree - all levels are completely filled except possibly the last level and the last level has all 
keys as left as possible.
3. Perfect Binary Tree - all internal nodes have two children and all leaves are at the same level.
4. Balanced Binary Tree - height of the tree is O(log n). ex- Red-black and AVL trees.
5. Degenerate/Pathological Tree - every internal node has one child.

**Unlabeled Trees**
```cmd
  o
 / \
o   o

```

**Labelled Trees**
```cmd
  A
 / \
B   C

```

 **Unlabelled Trees with n nodes**
 
n = 1 -> 1(no of possible trees)
 ```cmd
o
```
n = 2 -> 2(no of possible trees)
```cmd
  o   o
 /     \
o       o

```
```cmd
T is no of trees
>>> T(0) = 1
>>> T(1)=1
>>> T(2)=2
0 nodes in left subtree * 2 nodes in right subtree + 1 node in left subtree * 1 node in right subtree
>>> T(3) = T(0)*T(2) + T(1)*T(1) + T(2)*T(0) = 5
nth catalan number     
>>> T(n) = (2n)!/(n+1)! n!
     
For labelled trees. Every unlabelled tree with n nodes can create n! different labeled trees by assigning different 
permutations of labels to all nodes.  
>>> T`(n) = T(n)*n!   
```

**Tree applications**
* Store information that naturally forms a hierarchy. For example, the file system on a computer.
* If we organize keys in form of a tree(with some ordering e.g., BST), we can search for a given key in moderate time
(quicker than linked list and slower than arrays). Self balancing search trees like AVL and Red-Black trees guarantee an 
upper bound of O(log n) for search.
* We can insert/delete keys in moderate time(quicker than Arrays and slower than Unordered Linked Lists). Self-balancing
search trees like AVL and Red-Black trees guarantee an upper bound of O(log n) for insertion/deletion.
* Like Linked lists and unlike arrays, pointer implementation of trees don't have an upper limit on number of nodes as 
nodes are linked using pointers.

**Time complexity of Tree traversals**
 ```cmd
k is the number of nodes on one side
>>> T(n) = T(k) + T(n-k-1)+c -> 

Case 1: Skewed tree(Worst)
>>> T(n) = T(0) + T(n-1) + c
>>> T(n) = (2*T(0)) + T(n-2) + 2c
>>> T(n) = (n*T(0)) + (n*c)
>>> theta(n)

Case 2: Both right and left subtrees have equal nodes(Best)
>>> T(n) = 2T(n/2) + C
>>> theta(n) (by master algorithm)
```

**BFS vs DFS**
* In Trees BFS is Level order traversal
* In Trees DFS is Pre order, Post order and In order traversals

Time complexity of all traversals is O(n) <- each node is visited exactly once.

Level order traversal can also be done by queues.

**Space Complexity**
* BFS = O(width) max for balanced tree -> ceil(n/2) ->O(n)
* DFS = O(height) max for skewed tree -> O(n)

Extra space required for level order traversal is likely to be more when tree is more balanced and extra space for depth
first traversal is likely to be more when tree is less balanced.

**Threaded binary tree**
Binary trees have a lot of wasted space: the leaf nodes each have 2 null pointer. We can use these pointers to help us in 
inorder traversals. We can have the pointers reference the next node in inorder traversal; called threads. We need to know
if a pointer is an actual link or a thread, so we keep a boolean for each pointer.

**Construct a Binary tree with two traversal sequences** - We can construct only if one of them is inorder.
* inorder and pre-order
* inorder and post-order
* inorder and level-order

```cmd
   A      A
 /          \
B             B
pre-order, post-order and level-order traversals are same for these two trees. We can't identify which is left child and 
which is right child.
```
