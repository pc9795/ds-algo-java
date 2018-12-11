package geeks_for_geeks.ds.graph;

import geeks_for_geeks.ds.nodes.GraphNode;
import geeks_for_geeks.ds.union_find.UnionFind;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:09
 **/
public class GraphUsingAdjacencyList {


    public ArrayList<GraphNode>[] values;

    public GraphUsingAdjacencyList(int vertices) {
        values = new ArrayList[vertices];
        for (int i = 0; i < values.length; i++) {
            values[i] = new ArrayList<>();
        }
    }

    public GraphUsingAdjacencyList addEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src].add(new GraphNode(dest));
        return this;
    }

    public GraphUsingAdjacencyList addEdge(int src, int dest, int weight) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src].add(new GraphNode(dest, weight));
        return this;
    }


    public boolean isEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        for (int i = 0; i < values[src].size(); i++) {
            if (values[src].get(i).vertex == dest) {
                return true;
            }
        }
        return false;
    }

    public int vertices() {
        return values.length;
    }

    /**
     * T=O(V+E)
     */
    public void bfs() {
        boolean visited[] = new boolean[vertices()];
        for (int i = 0; i < vertices(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                bfsUtil(i, visited);
            }
        }
    }

    private void bfsUtil(int vertex, boolean[] visited) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(vertex);
        for (; !queue.isEmpty(); ) {
            int curr = queue.poll();
            System.out.println("visited:" + curr);
            for (int i = 0; i < values[curr].size(); i++) {
                int neighbour = values[curr].get(i).vertex;
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    public void dfs() {
        boolean visited[] = new boolean[vertices()];
        for (int i = 0; i < vertices(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfsUtil(i, visited);
            }
        }
    }

    private void dfsUtil(int vertex, boolean[] visited) {
        System.out.println("visited:" + vertex);
        for (int i = 0; i < values[vertex].size(); i++) {
            int neighbour = values[vertex].get(i).vertex;
            if (!visited[neighbour]) {
                visited[neighbour] = true;
                dfsUtil(neighbour, visited);
            }
        }
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
                visited[i] = true;
                recStack[i] = true;
                if (isCyclicUtil(i, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int vertex, boolean[] visited, boolean[] recStack) {
        for (int i = 0; i < values[vertex].size(); i++) {
            int neighbour = values[vertex].get(i).vertex;
            if (recStack[neighbour]) {
                return true;
            }
            if (!visited[neighbour]) {
                visited[neighbour] = true;
                recStack[neighbour] = true;
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
     */
    public ArrayDeque<Integer> topologicalSort() {
        if (this.vertices() == 0) {
            System.out.println("Empty graph");
        }
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GraphUsingAdjacencyList{").append(System.lineSeparator());
        for (int i = 0; i < vertices(); i++) {
            sb.append(i).append("=>").append(values[i]);
            sb.append(System.lineSeparator());
        }
        sb.append('}');

        return sb.toString();
    }

    public static void main(String[] args) {
        GraphUsingAdjacencyList graph = new GraphUsingAdjacencyList(6);
        graph.addEdge(5, 2).addEdge(2, 3).addEdge(3, 1).addEdge(4, 1)
                .addEdge(4, 0).addEdge(5, 0);
        System.out.println(graph.topologicalSort());
    }
}
