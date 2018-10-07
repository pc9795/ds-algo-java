package geeks_for_geeks.ds.graph;

/*
 * Adjacency Matrix
 * ----------------
 * Pros
 * ----
 * 1. Removing an edge O(1)
 * 2. Querying of edge is O(1)
 *
 * Cons
 * ----
 * 1. More space O(V^2)
 * 2. Adding a vertex is O(V^2)
 *
 * Adjacency List
 * --------------
 * Pros
 * ----
 * 1. Saves space O(V+E)
 * 2. Adding a vertex is easier
 *
 * Cons
 * -----
 * 1. Queries will take O(V)
 *
 * Advantages of BFS
 * ------------------
 * 1.We always reach a vertex from give source using the minimum no of edges
 * 2.Depth/levels of the built tree can be limited
 * 3.We can find nodes within a given distance k from a node
 *
 *
 *
 *
 *
 * */