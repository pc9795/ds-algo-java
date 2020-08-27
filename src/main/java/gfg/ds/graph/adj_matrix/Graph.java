package gfg.ds.graph.adj_matrix;

public class Graph extends GraphBase {
  public Graph(int vertices) {
    super(vertices);
  }

  @Override
  public Graph addEdge(int src, int dest, int weight) {
    assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

    values[src][dest] = weight;
    return this;
  }

  @Override
  public Graph addEdge(int src, int dest) {
    return addEdge(src, dest, 0);
  }

  @Override
  public int vertices() {
    return values.length;
  }
}
