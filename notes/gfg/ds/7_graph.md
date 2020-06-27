### Adjacency Matrix

symmetric for undirected graph.

**Pros**
* Easier to implement
* Removing an edge O(1)
* Querying of edge is O(1)

**Cons**
* More space O(V^2). Even if the graph is sparse it consumes the same space.
* Adding a vertex is O(V^2)

### Adjacency List

**Pros**
* Saves space O(V+E)
* Adding a vertex is easier

**Cons**
* Queries will take O(V)
 
**Advantages of BFS**
1. We always reach a vertex from given source using the minimum no of edges.
2. Depth/levels of the traversed tree can be limited
3. We can find nodes within a given distance k from a node
 
In case of unweighted graphs, any spanning tree is minimum spanning tree and we can use either Depth or Breadth first
traversal for finding a spanning tree.

To detect cycle in undirected graph. -> For every visited vertex ‘v’, if there is an adjacent ‘u’ such that u is already 
visited and u is not parent of v, then there is a cycle in graph.

**Bipartite Graph**
A graph whose vertices can be divided into two independent   sets, U and V such that every edge (u,v) either connects a 
vertex from U to V or a vertex from V to U. There is no edge that connects vertices of same set.
 
A bipartite graph is possible if the graph coloring is possible using two colors such that vertices in a set are colored 
with the same color.

We can maintain DAG structure of a graph by using Topological sort.

### Directed Graph
 
**Transitive closure**
1. Transitive closure is the reachability matrix which shows whether a vertex is reachable from other or not. We can use
Floyd warshall algorithm and instead of storing distance we can store boolean values. O(V^3)
2. Launch dfs for all vertices O(V^2)

### Undirected Graph

Connected components of a Undirected gfg.graph can be obtained using DFS and printing them.

If edge weight is one then BFS will give shortest path.

A path is a sequence v1, v2...vk of distinct vertices such that each adjacent pair vi vi+1 is an edge. If a vertex is
visited more than one in such a sequence, it is called a walk, not a path.