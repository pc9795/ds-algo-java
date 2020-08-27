Every node of Interval Tree stores following information.
* `i`: An interval which is represented as a pair [low, high]
* `max`: Maximum high value in subtree rooted with this node.

The low value of an interval is used as key to maintain order in BST. The insert and delete operations are same as insert
and delete in self-balancing BST used.
