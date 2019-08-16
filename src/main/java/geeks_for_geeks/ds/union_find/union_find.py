class UnionFind:
    def __init__(self, size):
        self.parent = [0] * size
        self.rank = [0] * size
        self.disconnected = size
        for i in range(size):
            self.parent[i] = i

    def find(self, element):
        if self.parent[element] != element:
            self.parent[element] = self.find(self.parent[element])
        return self.parent[element]

    def same_set(self, first, second):
        parent1 = self.find(first)
        parent2 = self.find(second)
        if parent1 == parent2:
            return True
        return False

    def union(self, first, second):
        parent1 = self.find(first)
        parent2 = self.find(second)
        if parent1 == parent2:
            return
        if self.rank[parent1] < self.rank[parent2]:
            self.parent[parent1] = parent2
        elif self.rank[parent2] < self.rank[parent1]:
            self.parent[parent2] = parent1
        else:
            self.parent[parent2] = parent1
            self.rank[parent1] += 1
        self.disconnected -= 1
