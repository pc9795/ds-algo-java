package geeks_for_geeks.algo.greedy_algorithms;

import geeks_for_geeks.ds.graph.GraphUsingEdges;
import geeks_for_geeks.ds.graph.UnionFind;
import geeks_for_geeks.ds.nodes.Edge;

import java.util.Comparator;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 22:42
 **/
public class Kruskal {
    /**
     * O(E log E)+O(E log V)
     * [sort]     [edges]*[union-find]
     * Maximum value of E is O(V^2)
     * therefore log E= log V
     * <p>
     * T=O(E log E) or (E log V)
     *
     * @param graph
     * @return
     */
    public static GraphUsingEdges kruskalMinimumSpanningTree(GraphUsingEdges graph) {
        GraphUsingEdges mst = new GraphUsingEdges();
        graph.edges.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        int vertices = graph.vertices();
        UnionFind uf = new UnionFind(vertices);
        for (int i = 0, j = 0; i < vertices - 1 && j < graph.edges.size(); j++) {
            Edge edge = graph.edges.get(j);
            if (uf.find(edge.src) != uf.find(edge.dest)) {
                uf.union(edge.src, edge.dest);
                mst.addEdge(edge.src, edge.dest, edge.weight);
                i++;
            } else {
                System.out.println("Discarding edge because it will form a cycle:" + edge);
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        GraphUsingEdges graph = new GraphUsingEdges();
        graph.addEdge(7, 6, 1).addEdge(8, 2, 2).addEdge(6, 5, 2)
                .addEdge(1, 2, 8).addEdge(3, 4, 9).addEdge(5, 4, 10)
                .addEdge(2, 3, 7).addEdge(7, 8, 7).addEdge(0, 7, 8)
                .addEdge(0, 1, 4).addEdge(2, 5, 4).addEdge(8, 6, 6)
                .addEdge(1, 7, 11).addEdge(3, 5, 14);
        System.out.println(kruskalMinimumSpanningTree(graph));
    }
}
