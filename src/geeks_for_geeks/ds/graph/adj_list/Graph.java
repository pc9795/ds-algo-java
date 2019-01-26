package geeks_for_geeks.ds.graph.adj_list;

import geeks_for_geeks.ds.nodes.Edge;
import geeks_for_geeks.ds.nodes.GraphNode;
import geeks_for_geeks.ds.union_find.UnionFind;
import geeks_for_geeks.util.DoublePointer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:09
 **/
public class Graph extends GraphBase {


    public int[] inDegree;

    public Graph(int vertices) {
        super(vertices);
        inDegree = new int[vertices];
    }

    @Override
    public Graph addEdge(int src, int dest) {
        assert (src >= values.length || dest >= values.length);
        values[src].add(new GraphNode(dest));
        inDegree[dest]++;
        return this;
    }

    @Override
    public Graph removeEdge(int src, int dest) {
        assert (src >= values.length || dest >= values.length);
        inDegree[dest]--;
        values[src].remove(new GraphNode(dest));
        return this;
    }

    @Override
    public Graph addEdge(int src, int dest, int weight) {
        assert (src >= values.length || dest >= values.length);
        inDegree[dest]++;
        values[src].add(new GraphNode(dest, weight));
        return this;
    }

    /**
     * T=O(V+E)
     * We can't just check visited array because then it will result 1->2 and 0->2 also cyclic, so we just have to check
     * for back edge which is done using recursion stack.
     *
     * @return
     */
    public boolean isCyclic() {
        boolean visited[] = new boolean[vertices()];
        boolean recStack[] = new boolean[vertices()];
        for (int i = 0; i < vertices(); i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int vertex, boolean[] visited, boolean[] recStack) {
        visited[vertex] = true;
        recStack[vertex] = true;
        for (int i = 0; i < values[vertex].size(); i++) {
            int neighbour = values[vertex].get(i).vertex;
            if (recStack[neighbour]) {
                return true;
            }
            if (!visited[neighbour]) {
                if (isCyclicUtil(neighbour, visited, recStack)) {
                    return true;
                }
            }
        }
        recStack[vertex] = false;
        return false;
    }

    /**
     * To make it work for undirected graphs the undirected edge must be read as one entry else it will result it as
     * true.
     *
     * @return
     */
    public boolean isCyclicUsingUnionFind() {
        boolean visited[] = new boolean[vertices()];
        UnionFind uf = new UnionFind(vertices());
        for (int i = 0; i < vertices(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (isCyclicUsingUnionFindUtil(i, visited, uf)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUsingUnionFindUtil(int vertex, boolean[] visited, UnionFind uf) {
        for (int i = 0; i < values[vertex].size(); i++) {
            int neighbour = values[vertex].get(i).vertex;
            if (uf.find(vertex) == uf.find(neighbour)) {
                return true;
            } else {
                uf.union(i, neighbour);
            }
            if (!visited[neighbour]) {
                visited[neighbour] = true;
                if (isCyclicUsingUnionFindUtil(neighbour, visited, uf)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Only possible for a DAG.
     * first node have in degree 0;
     * We can look it as decreasing order of finish time.
     */
    public ArrayDeque<Integer> topologicalSort() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[this.vertices()];
        for (int i = 0; i < this.vertices(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                topologicalSortUtil(visited, i, stack);
            }
        }
        return stack;
    }

    private void topologicalSortUtil(boolean[] visited, int vertex, ArrayDeque<Integer> stack) {
        for (int i = 0; i < this.values[vertex].size(); i++) {
            GraphNode neighbour = this.values[vertex].get(i);
            if (!visited[neighbour.vertex]) {
                visited[neighbour.vertex] = true;
                topologicalSortUtil(visited, neighbour.vertex, stack);
            }
        }
        stack.push(vertex);
    }

    public int longestPath(int source) {
        int[] dist = new int[this.vertices()];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[source] = 0;
        ArrayDeque<Integer> stack = this.topologicalSort();
        for (; !stack.isEmpty(); ) {
            int vertex = stack.peek();
//           It will make sure that we start from the source vertex and pick those who are updated
//           ; Only those will be updated which are linked from source vertex.
            if (dist[vertex] != Integer.MIN_VALUE) {
                for (int i = 0; i < this.values[vertex].size(); i++) {
                    GraphNode neighbour = this.values[vertex].get(i);
                    dist[neighbour.vertex] = dist[neighbour.vertex] > dist[vertex] + neighbour.weight ?
                            dist[neighbour.vertex] : dist[vertex] + neighbour.weight;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] > max) {
                max = dist[i];
            }
        }
        return max;
    }

    public boolean checkBipartite() {
        int[] colour = new int[vertices()];
        Arrays.fill(colour, -1);
        for (int i = 0; i < vertices(); i++) {
            if (colour[i] == -1) {
                colour[i] = 1;
                if (!colorGraph(colour, i, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean colorGraph(int[] colour, int vertex, int c) {
        for (int i = 0; i < this.values[vertex].size(); i++) {
            GraphNode neighbour = this.values[vertex].get(i);
            if (colour[neighbour.vertex] == -1) {
                colour[neighbour.vertex] = c;
                if (!colorGraph(colour, neighbour.vertex, 1 - c)) {
                    return false;
                }
            } else if (colour[neighbour.vertex] != c) {
                return false;
            }
        }
        return true;
    }


    /**
     * DFS approach will not work
     * ex- 0->1->2->3 , dfs will call it connected but it is not.
     * <p>
     * Kosaraju's Algorithm(Strongly connected)(Single SCC)
     * T=O(V+E)
     *
     * @return
     */
    @Override
    public boolean isStronglyConnected() {
        int vertices = this.vertices();
        boolean[] visited = new boolean[vertices];
        dfsUtil(0, visited);
        for (boolean i : visited) {
            if (!i) {
                return false;
            }
        }
        Arrays.fill(visited, false);
        Graph transposedGraph = this.transpose();
        transposedGraph.dfsUtil(0, visited);
        for (boolean i : visited) {
            if (!i) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEulerian() {
        throw new NotImplementedException();
    }

    @Override
    public void printEulerPath() {
        throw new NotImplementedException();
    }


}