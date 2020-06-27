
```cmd
2<--1-->3                     1[0,15]
| ^ ^   |                    / \
|  \  \ |              [1,10]2  3[11,14]
V   \  \V                   |    |
5--->6  4              [2,9]5    4[12,13]
|\    |  |                   /\
| \   V  V                 /   \
|  ->7<--8            [3,6]6   8[7,8]
|--------^                |
                   [4,5]7

```

**Edges**
* Tree edge [in the traversal]
* Forward edge [Go from one level to another level in same branch]
* Cross edge[Go from one subtree to another]
* Back edge [Move up to the tree] [a,b] [DFS entry time, DFS exit time]

1. Forward edge (u,v) [pre(u),post(u)] contains [pre(v),post(v)]
2. Backward edge (u,v) [pre(v),post(v)] contains [pre(u),post(u)]
3. Cross edge(u,v) [pre(u),post(u)] and [pre(v),post(v)] are disjoint.

Directed Graphs
--
**SCC**

We can compute pre/entry and post/time of a gfg.graph using DFS.

Now suppose we construct a new gfg.graph in which
* Vertices: SCC's of original gfg.graph
* Edge: c1->c2 if there is an edge in the original gfg.graph from some v1 in c1 to some v2 in c2.

This new gfg.graph is dag. Observe that if c1->c2 in the SCC dag, the max exit number in c1 is bigger than the max exit number
in c2.

In Grev, we start a dfs in an SCC containing the highest exit number. This DFS can't leave the SCC-thisSCC has only
outgoing edges in the original gfg.graph since it has the highest exit number, so in the reversed gfg.graph it has only incoming
edges. On hte other hand, since this is an scc, whether we follow reversed edges or original edges, all vertices in the
SCC will be reachable by DFS. In other words, the DFS will mark precisely the vertices of this SCC.

Now, we look at unmarked vertex with highest exit number. This is in a different SCC. Once again, if we start a DFS here,
we cannot leave the SCC--the only outgoing (reverse) edge can be to an SCC before it in the original DAG, but that SCC
is already visited.

In this way, if we start each time with the unmarked vertex that highest exit number, we identify its SCC.