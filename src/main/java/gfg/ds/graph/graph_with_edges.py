from gfg.ds.union_find.union_find import UnionFind

class GraphWithEdges:
    def __init__(self, vertices):
        self.vertices = vertices
        self.values = [-1] * vertices
        for i in range(vertices):
            self.values[i] = []
        self.edges = []

    def add_edge(self, src, dest, weight=0):
        self.values[src].append(len(self.edges))
        self.edges.append((src, dest, weight))

    @staticmethod
    def compare_edge():
        pass

    def kruskal_mst_cost(self):
        edges = sorted(self.edges, key=lambda edge: edge[2])
        uf = UnionFind(self.vertices)
        current_edges = 0
        mst_cost = 0
        for edge in edges:
            if not uf.same_set(edge[0], edge[1]):
                uf.union(edge[0], edge[1])
                current_edges += 1
                mst_cost += edge[2]
            if current_edges == self.vertices - 1:
                break
        return mst_cost
