package geeks_for_geeks.ds.graph;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 01:07
 **/
public class UnionFind {
    int parent[];

    public UnionFind(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int val) {
        if (parent[val] == val) {
            return val;
        } else {
            return find(parent[val]);
        }
    }

    public void union(int val1, int val2) {
        int parent1 = find(val1);
        int parent2 = find(val2);
        parent[parent2] = parent1;
    }

}
