package geeks_for_geeks.ds.graph.adj_list;

import geeks_for_geeks.ds.nodes.GraphNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayDeque;
import java.util.Arrays;

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
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        values[src].add(new GraphNode(dest));
        inDegree[dest]++;
        return this;
    }

    @Override
    public Graph removeEdge(int src, int dest) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        inDegree[dest]--;
        values[src].remove(new GraphNode(dest));
        return this;
    }

    @Override
    public Graph addEdge(int src, int dest, int weight) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        inDegree[dest]++;
        values[src].add(new GraphNode(dest, weight));
        return this;
    }

    /**
     * t=O(V+E)
     * We can't just check visited array because then it will result 1->2 and 0->2 also cyclic, so we just have to check
     * for back edge which is done using recursion geeks_for_geeks.stack.
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
     * t=O(V+E)
     * Only possible for a DAG.
     * first node have in degree 0;
     * We can look it as decreasing order of finish time.
     */
    public ArrayDeque<Integer> topologicalSort() {
        assert !this.isCyclic();

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
        for (GraphNode neighbour : this.values[vertex]) {

            if (!visited[neighbour.vertex]) {
                visited[neighbour.vertex] = true;
                topologicalSortUtil(visited, neighbour.vertex, stack);
            }
        }
        stack.push(vertex);
    }

    /**
     * t=O(V+E)
     *
     * @param source
     * @return
     */
    public int longestPath(int source) {
        assert !this.isCyclic();

        int[] dist = new int[this.vertices()];
        Arrays.fill(dist, Integer.MIN_VALUE);

        dist[source] = 0;

        ArrayDeque<Integer> stack = this.topologicalSort();

        for (; !stack.isEmpty(); ) {
            int vertex = stack.peek();
//           It will make sure that we start from the source vertex and pick those who are updated
//           ; Only those will be updated which are linked from source vertex.
            if (dist[vertex] != Integer.MIN_VALUE) {

                for (GraphNode neighbour : this.values[vertex]) {
                    dist[neighbour.vertex] = dist[neighbour.vertex] > dist[vertex] + neighbour.weight ?
                            dist[neighbour.vertex] : dist[vertex] + neighbour.weight;
                }
            }
        }

        return Arrays.stream(dist).reduce(Integer.MIN_VALUE, Math::max);
    }

    /**
     * t=O(V+E)
     *
     * @return
     */
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

    private boolean colorGraph(int[] colour, int vertex, int c) {
        for (GraphNode neighbour : this.values[vertex]) {

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

    public boolean isEulerianCircuitExists() {
        for (int i = 0; i < values.length; i++) {
            if (inDegree[i] != values[i].size()) {
                return false;
            }
        }
        return true;
    }

    public boolean isAllVerticesReachableFrom(int source) {
        int vertices = this.values.length;
        boolean[] visited = new boolean[vertices];
        dfsUtil(source, visited);
        for (int i = 0; i < visited.length; i++) {
            if ((inDegree[i] > 0 || this.values[i].size() > 0) && !visited[i]) {
                return false;
            }
        }
        return true;
    }

    public boolean isEulerianPathExists() {
        boolean isOutGreaterFound = false;
        int source = -1;
        boolean isInGreaterFound = false;
        for (int i = 0; i < this.values.length; i++) {
            int outDegree = this.values[i].size();
            if (outDegree > inDegree[i]) {
                if (outDegree - inDegree[i] == 1 && !isOutGreaterFound) {
                    isOutGreaterFound = true;
                    source = i;
                } else {
                    return false;
                }
            } else if (inDegree[i] > outDegree) {
                if (inDegree[i] - outDegree == 1 && !isInGreaterFound) {
                    isInGreaterFound = true;
                } else {
                    return false;
                }
            }
        }
        if (isOutGreaterFound && isInGreaterFound) {
            return isAllVerticesReachableFrom(source);
        } else {
            assert false;
            return false;
        }
    }


}
