Let us number the planes as 0, 1, 2, …(K – 1). A point (node) at depth D will have A aligned plane where A is calculated 
as: `A = D mod K`

In BST delete, if a node’s left child is empty and right is not empty, we replace the node with right child. In K D Tree, 
doing this would violate the KD tree property as dimension of right child of node is different from node’s dimension. Same
is true when left child is not empty and right is empty.

