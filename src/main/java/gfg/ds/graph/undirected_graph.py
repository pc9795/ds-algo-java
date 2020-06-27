class UndirectedGraph:
    def __init__(self, vertices):
        self.values = [-1] * vertices
        self.time = 0
        for i in range(vertices):
            self.values[i] = []

    @property
    def vertices(self):
        return len(self.values)

    def add_edge(self, src, dest):
        self.values[src].append(dest)
        self.values[dest].append(src)

    def get_articulation_points(self):
        vertices = self.vertices
        parent = [-1] * vertices
        low = [-1] * vertices
        visited = [False] * vertices
        ap = [False] * vertices
        time = [0] * vertices
        self.time = 0
        for i in range(vertices):
            if not visited[i]:
                self.get_articulation_points_util(parent, low, visited, ap, time, i)
        articulation_points = set()
        for i in range(vertices):
            if ap[i]:
                articulation_points.add(i)
        return articulation_points

    def get_articulation_points_util(self, parent, low, visited, ap, time, source):
        visited[source] = True
        self.time += 1
        low[source] = time[source] = self.time
        children = 0
        for neighbour in self.values[source]:
            if not visited[neighbour]:
                children += 1
                parent[neighbour] = source
                self.get_articulation_points_util(parent, low, visited, ap, time, neighbour)
                low[source] = min(low[source], low[neighbour])
                if parent[source] == -1 and children > 1:
                    ap[source] = True
                elif parent[source] != -1 and low[neighbour] >= time[source]:
                    ap[source] = True
            elif parent[source] != neighbour:
                low[source] = min(low[source], time[neighbour])
