package geeks_for_geeks.algo.greedy_algorithms;

import geeks_for_geeks.ds.graph.adj_list.GraphBase;
import geeks_for_geeks.ds.graph.edge_repr.Graph;
import geeks_for_geeks.ds.graph.adj_list.UndirectedGraph;
import geeks_for_geeks.ds.union_find.UnionFind;
import geeks_for_geeks.ds.nodes.Edge;
import geeks_for_geeks.ds.nodes.GraphNode;
import geeks_for_geeks.ds.nodes.PrimNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 22:42
 **/
public class MST {
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
    public static Graph kruskalMinimumSpanningTree(Graph graph) {
        Graph mst = new Graph();
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


    /**
     * T=O(V^2) for adjacency matrix
     * =O(ElogV) for adjacency list
     * =(E+V) * logV = traversing * remove
     * we get MST for a connected graph only, so in a connected graph V=O(E) therefore O(E+V) -> O(E)
     * In case of densely connected graph E->V^2.
     *
     * @param graph
     * @return
     */
    public static GraphBase primMinimumSpanningTree(GraphBase graph) {
        assert graph != null;
        GraphBase mst = new geeks_for_geeks.ds.graph.adj_list.Graph(graph.vertices());
        PriorityQueue<PrimNode> heap = new PriorityQueue<>(new Comparator<PrimNode>() {
            @Override
            public int compare(PrimNode o1, PrimNode o2) {
                return o1.key - o2.key;
            }
        });
        PrimNode[] nodes = new PrimNode[graph.vertices()];
        boolean[] inHeap = new boolean[graph.vertices()];
        Arrays.fill(inHeap, true);
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new PrimNode(i, Integer.MAX_VALUE);
            nodes[i].parent = -1;
            heap.add(nodes[i]);
        }
        nodes[0].key = 0;
        while (!heap.isEmpty()) {
            PrimNode node = heap.poll();
            if (node.parent != -1) {
                mst.addEdge(node.parent, node.vertex, node.key);
            }
            inHeap[node.vertex] = false;
            for (int i = 0; i < graph.values[node.vertex].size(); i++) {
                GraphNode neighbour = graph.values[node.vertex].get(i);
                if (inHeap[neighbour.vertex]) {
                    PrimNode neighbourNode = nodes[neighbour.vertex];
                    if (neighbourNode.key > neighbour.weight) {
                        neighbourNode.parent = node.vertex;
//                        In java implementation remove will search the position of the node which will take O(n) time
//                        If we implement our own heap with a map to get the position in O(1) time then it will take
//                        O(log n) time.
                        heap.remove(neighbourNode);
                        nodes[neighbour.vertex].key = neighbour.weight;
                        heap.add(nodes[neighbour.vertex]);
                    }


                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(7, 6, 1).addEdge(8, 2, 2).addEdge(6, 5, 2)
                .addEdge(1, 2, 8).addEdge(3, 4, 9).addEdge(5, 4, 10)
                .addEdge(2, 3, 7).addEdge(7, 8, 7).addEdge(0, 7, 8)
                .addEdge(0, 1, 4).addEdge(2, 5, 4).addEdge(8, 6, 6)
                .addEdge(1, 7, 11).addEdge(3, 5, 14);
//        System.out.println(kruskalMinimumSpanningTree(graph));
        GraphBase graph2 = new UndirectedGraph(9);
        graph2.addEdge(0, 1, 4);
        graph2.addEdge(0, 7, 8);
        graph2.addEdge(1, 2, 8);
        graph2.addEdge(1, 7, 11);
        graph2.addEdge(2, 3, 7);
        graph2.addEdge(2, 8, 2);
        graph2.addEdge(2, 5, 4);
        graph2.addEdge(3, 4, 9);
        graph2.addEdge(3, 5, 14);
        graph2.addEdge(4, 5, 10);
        graph2.addEdge(5, 6, 2);
        graph2.addEdge(6, 7, 1);
        graph2.addEdge(6, 8, 6);
        graph2.addEdge(7, 8, 7);
        System.out.println(primMinimumSpanningTree(graph2));
    }
}
