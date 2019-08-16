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
2. Maximum number of nodes in a binary tree of height 'h' is (2^h)-1; -> 1+2+2^2+...2^(h-1)
3. In a binary tree with N nodes, minimum possible height is ceil(log2(N+1)) -> from 2
4. A binary tree with L leaves has at least ceil(log2L)+1 levels; L<=2^(l-1)
5. In Binary tree where every node has 0 or 2 children, number of leaf nodes is always one more than nodes with two 
children.

**Handshaking leema**
-----------------
1. In a k-ary tree where every node has either 0 or k children

```cmd
L=(k-1)*I+1 -> L(Number of leaf node), I(Number of internal nodes)

case 1.Root is leaf.
L=1, I=0 proved

case 2. Tree is undirected acyclic geeks_for_geeks.graph
|E|=L+I-1 -> edges=nodes-1

Sum of all degrees = 2* sum of edges
Sum of degrees of leaves+sum of degrees for internal nodes except root+root's degree
=2* (nodes-1)
L+(I-1)*(k+1)+k=2*(L+I-1)
L=(k-1)*I+1 proved

```

2. In a binary tree number of leaf nodes is always one more than nodes with two children.

```cmd
L=T+1 -> L(Number of leaf node), T( Number of internal nodes with two children)

case 1. Root is leaf
T=0, L=1 proved

case 2. Root has two children

Sum of degree of nodes with two children except root + sum of degrees of nodes with one child+
sum of degrees of leaves + root's degree=2*(No. of nodes-1)
(T-1)*3+S*2+L+2=(S+T+L-1)*2
T=L-1 proved

case 3. Root has one child
T*3+(S-1)*2+L+1=(S+T+L-1)*2
T=L-1 proved

```

Types of Binary Trees
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
 
 n=1 -> 1
 ```cmd
o
```
n=2 -> 2
```cmd
  o   o
 /     \
o       o

```
```cmd
T(0)=1, T(1)=1, T(2)=2
T(3)=T(0)*T(2) + T(1)*T(1) + T(2)*T(0)
     =5
     [ 0 nodes in left subtree * 2 nodes in right subtree + 1 node in left subtree * 1 node in right subtree + ...]
T(n) = (2n)!/(n+1)! n! -> nth catalan number
     
// for labelled trees  
T(n)labelled = T(n)*n!   
```

**Time complexity of Tree traversals**
 ```cmd
T(n)=T(k)+T(n-k-1)+c -> k is the number of nodes on one side.

Worst case [skewed tree]
T(n)=T(0)+T(n-1) +c
T(n)=2T(0)+T(n-2) +2c
T(n)=nT(0)+nc
theta(n)

Best case [both right and left subtrees have equal nodes]
T(n)=2T(n/2) + C
theta(n) <- by master algo.

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

**Construct a Binary tree with two traversal sequences**

We can construct only if one of them is inorder.
* inorder and preorder
* inorder and postorder
* inorder and level order

counter ex-
```cmd
   A      A
 /          \
B             B

```
preorder, postorder and levelorder traversals are same for these two trees. I think we can't identify which is left 
child and which is right child.

