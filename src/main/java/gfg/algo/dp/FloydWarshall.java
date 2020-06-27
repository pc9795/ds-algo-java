package gfg.algo.dp;

import gfg.ds.graph.adj_matrix.Graph;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 11-12-2018 09:32
 **/
public class FloydWarshall {

    /**
     * t=O(V^3)
     * we can't use adjacency list representation.
     *
     * @param graph
     */
    public static void allPairsShortestPath(Graph graph) {
        assert graph != null;
        int v = graph.vertices();
        int[][] sp = new int[v][v];
        for (int i = 0; i < sp.length; i++) {
            System.arraycopy(graph.values[i], 0, sp[i], 0, sp[i].length);
        }
        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (sp[i][k] != Integer.MAX_VALUE && sp[k][j] != Integer.MAX_VALUE
                            && sp[i][j] >= sp[i][k] + sp[k][j]) {
                        sp[i][j] = sp[i][k] + sp[k][j];
                    }
                }
            }
        }
        System.out.println("All pair shortest paths:");
        for (int i = 0; i < sp.length; i++) {
            System.out.println(Arrays.toString(sp[i]));
        }
    }
}
