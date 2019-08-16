package geeks_for_geeks.ds.union_find;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 01:07
 **/
public class UnionFind {
    private int parent[];
    private int rank[];

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int val) {
        if (parent[val] != val) {
            //path compression
            parent[val] = find(parent[val]);
        }
        return parent[val];
    }

    public void union(int val1, int val2) {
        int parent1 = find(val1);
        int parent2 = find(val2);
        if (parent1 == parent2) {
            return;
        }
        if (rank[parent1] < rank[parent2]) {
            parent[parent1] = parent2;
        } else if (rank[parent2] < rank[parent1]) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
            rank[parent2]++;
        }
    }

}
