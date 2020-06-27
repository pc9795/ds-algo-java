class Graph:
    def __init__(self, vertices):
        self.values = [-1] * vertices
        for i in range(vertices):
            self.values[i] = []
        self.in_degree = [0] * vertices
        self.vertex_exists = [False] * vertices

    def add_edge(self, src, dest):
        self.values[src].append(dest)
        self.in_degree[dest] += 1
        self.vertex_exists[dest] = self.vertex_exists[src] = True

    def dfs_util(self, src, visited):
        visited[src] = True
        neighbours = self.values[src]
        for i in neighbours:
            if not visited[i]:
                self.dfs_util(i, visited)

    def transpose(self):
        vertices = len(self.values)
        transpose = Graph(vertices)
        for i in range(vertices):
            neighbours = self.values[i]
            for neighbour in neighbours:
                transpose.add_edge(neighbour, i)
        return transpose

    def is_all_vertices_reachable(self, source):
        vertices = len(self.values)
        visited = [False] * vertices
        self.dfs_util(source, visited)
        for i in range(vertices):
            if self.vertex_exists[i] and not visited[i]:
                return False
        return True

    def is_eulerian_path_exists(self):
        is_out_greater_found = False
        source = -1
        is_in_greater_found = False
        vertices = len(self.values)
        for i in range(vertices):
            out_degree = len(self.values[i])
            in_degree = self.in_degree[i]
            if out_degree > in_degree:
                if out_degree - in_degree == 1 and not is_out_greater_found:
                    is_out_greater_found = True
                    source = i
                else:
                    return False
            elif in_degree > out_degree:
                if in_degree - out_degree == 1 and not is_in_greater_found:
                    is_in_greater_found = True
                else:
                    return False
        if is_in_greater_found and is_out_greater_found:
            return self.is_all_vertices_reachable(source)
        else:
            for i in range(vertices):
                if self.vertex_exists[i] and len(self.values[i]) > 0:
                    return self.is_all_vertices_reachable(i)
        assert False
