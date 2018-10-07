package geeks_for_geeks.ds.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:09
 **/
public class GraphUsingAdjacencyList {

    public static class GraphNode {
        int vertex;
        int weight;

        public GraphNode(int vertex) {
            this.vertex = vertex;
            this.weight = 0;
        }

        public GraphNode(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "GraphNode{" +
                    "vertex=" + vertex +
                    ", weight=" + weight +
                    '}';
        }
    }


    public ArrayList<GraphNode>[] values;

    public GraphUsingAdjacencyList(int vertcies) {
        values = new ArrayList[vertcies];
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

    //    O(V+E)
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

    public boolean isCyclicUtil(int vertex, boolean[] visited, boolean[] recStack) {
        for (int i = 0; i < values[vertex].size(); i++) {
            int neighbour = values[vertex].get(i).vertex;
            if (recStack[neighbour]) {
                return true;
            }
            if (!visited[neighbour]) {
                visited[neighbour] = true;
                recStack[neighbour] = true;
                return isCyclicUtil(neighbour, visited, recStack);
            }
        }
        recStack[vertex] = false;
        return false;
    }

    public static void main(String[] args) {
        GraphUsingAdjacencyList graph = new GraphUsingAdjacencyList(4);
        graph.addEdge(0, 2).addEdge(2, 0).addEdge(0, 1).addEdge(1, 2)
                .addEdge(2, 0).addEdge(3, 3);
//        graph.addEdge(1, 2).addEdge(0, 2);
        System.out.println(graph.isCyclic());
    }

}

class UndirectedGraphUsingAdjacencyList extends GraphUsingAdjacencyList {
    public UndirectedGraphUsingAdjacencyList(int vertices) {
        super(vertices);
    }

    @Override
    public UndirectedGraphUsingAdjacencyList addEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src].add(new GraphNode(dest));
        values[dest].add(new GraphNode(src));
        return this;
    }
}