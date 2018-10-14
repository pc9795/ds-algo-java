package geeks_for_geeks.ds.graph;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:08
 **/
public class Graph {
    int values[][];

    public Graph(int vertices) {
        values = new int[vertices][vertices];
    }

    public Graph addEdge(int src, int dest, int weight) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src][dest] = weight;
        return this;
    }

    public Graph addEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src][dest] = 1;
        return this;
    }


    public boolean isEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        return values[src][dest] == 1;
    }

    public int vertices() {
        return values.length;
    }

}

