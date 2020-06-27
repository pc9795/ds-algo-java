Critical intersections are vertices that disconnect the gfg.graph if they are deleted. These are called "articulation points"
or "cut vertices".

1. The root of the DFS tree is an articulation if it has two or more children.
2. Any other internal vertex v in the DFS tree, if it has a subtree rooted at a child of v that does not have an edge
which climbs 'higher' than v, then v is an articulation point.