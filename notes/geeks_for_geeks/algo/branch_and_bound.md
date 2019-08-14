DP solution for Knapsack will not work if weights are not integers.

For combinatorial problems.

For current node in tree, we compute a bound on best possible solution that we get if we down this node. If the bound on
best possible solution itself is worse than current best ,then we ignore the subtree rooted with the node.

Cost through a node includes two costs:
1. Cost of reaching the node from the root( When we reach a node, we have this cost computed)
2. Cost of reaching an answer from the current node to a leaf( We compute a bound on this cost to decide whether to 
ignore subtree with this node or not).

