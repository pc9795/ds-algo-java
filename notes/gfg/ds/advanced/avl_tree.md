AVL tree is a self-balancing Binary Search Tree where the difference between heights of left and right subtrees cannot be 
more than one for all nodes.

Most of the BST operations take O(h) time where h is the height of the BST. The cost of these operations may become O(n) 
for a skewed Binary tree. If we make sure that height of the tree remains O(Logn) after every insertion and deletion, then 
we can guarantee an upper bound of O(Logn) for all these operations. The height of an AVL tree is always O(Logn) where n 
is the number of nodes in the tree.

The AVL trees are more balanced compared to Red-Black Trees, but they may cause more rotations during insertion and deletion. 
So if your application involves many frequent insertions and deletions, then Red Black trees should be preferred. And if 
the insertions and deletions are less frequent and search is the more frequent operation, then AVL tree should be preferred 
over Red Black Tree.

To store duplicate data we can augment AVL tree node to store count together with regular fields like key, left and right 
pointers. In fact this is applicable for all balanced search trees.