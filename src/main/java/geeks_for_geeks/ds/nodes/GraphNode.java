package geeks_for_geeks.ds.nodes;

import java.util.Objects;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-10-2018 01:18
 **/
public class GraphNode {
    public int vertex;
    public int weight;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof GraphNode) {
            GraphNode other = (GraphNode) obj;
            return this.vertex == other.vertex && this.weight == other.weight;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex, weight);
    }
}
