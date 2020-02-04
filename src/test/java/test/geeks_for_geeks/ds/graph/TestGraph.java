package test.geeks_for_geeks.ds.graph;

import geeks_for_geeks.ds.graph.GraphApplications;
import geeks_for_geeks.ds.graph.adj_matrix.Graph;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By: Prashant Chaubey
 * Created On: 11-02-2019 02:06
 * Purpose: TODO:
 **/
class TestGraph {

    @Test
    void testSnakeAndLadderProblem() {
        int n = 30;
        int moves[] = new int[n];
        Arrays.fill(moves, -1);

//        ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;

//         Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;

        Assertions.assertEquals(GraphApplications.snakeAndLadderProblem(n, moves), 3);
    }

    @Test
    void testMinimizeCashFlow() {
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 1000).addEdge(0, 2, 2000).addEdge(1, 2, 5000);
        GraphApplications.minimizeCashFlow(graph);
    }

    @Test
    void testBoggle() {
        Set<String> set = new HashSet<>();
        set.add("GEEKS");
        set.add("FOR");
        set.add("QUIZ");
        set.add("GO");

        char[][] matrix = new char[][]{
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}
        };

        GraphApplications.boggle(matrix, set);
    }

}
