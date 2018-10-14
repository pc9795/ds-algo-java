package geeks_for_geeks.ds.graph;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-10-2018 11:59
 **/
public class GraphApplications {

    public static class BoardEntry {
        int moves;
        int pos;

        public BoardEntry(int moves, int pos) {
            this.moves = moves;
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "BoardEntry{" +
                    "moves=" + moves +
                    ", pos=" + pos +
                    '}';
        }
    }

    public static int snakeAndLadderProblem(int n, int[] move) {
        ArrayDeque<> queue = new ArrayDeque<>();
        int[] dist = new int[n];
        dist[0] = 1;
        queue.add(0);
        for (; !queue.isEmpty(); ) {
            int pos = queue.poll();
            for (int i = pos + 1; i <= pos + 6; i++) {

            }
        }
        return -1;
    }
}
