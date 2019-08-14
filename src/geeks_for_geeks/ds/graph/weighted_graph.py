class WeightedGraph:
    def __init__(self, vertices):
        self.values = [-1] * vertices
        for i in range(vertices):
            self.values[i] = []

    def add_edge(self, src, dest, weight=0):
        self.values[src].append((dest, weight))
