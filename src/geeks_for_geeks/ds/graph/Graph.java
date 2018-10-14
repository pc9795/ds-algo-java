package geeks_for_geeks.ds.graph;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:08
 **/
public class Graph {
    boolean values[][];

    public Graph(int vertices) {
        values = new boolean[vertices][vertices];
    }

    public Graph addEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src][dest] = true;
        return this;
    }

    public boolean isEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        return values[src][dest];
    }

    public int vertices() {
        return values.length;
    }

}

