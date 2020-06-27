package geeks_for_geeks.ds.nodes;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 25-06-2020 23:16
 **/
public class KDNode {
    public int degree;
    private int data[];
    public KDNode left, right;

    public KDNode(int degree) {
        this.degree = degree;
        this.data = new int[degree];
    }

    public int get(int dimension) {
        assert dimension < degree;

        return data[dimension];
    }

    public static KDNode of(int degree, int... values) {
        assert values.length == degree;

        KDNode node = new KDNode(degree);
        System.arraycopy(values, 0, node.data, 0, degree);
        return node;
    }

    public KDNode copy() {
        KDNode copy = new KDNode(degree);
        System.arraycopy(data, 0, copy.data, 0, degree);
        return copy;
    }

    @Override
    public String toString() {
        return "KDNode{" +
                "degree=" + degree +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KDNode node = (KDNode) o;

        if (degree != node.degree) return false;
        return Arrays.equals(data, node.data);
    }

    @Override
    public int hashCode() {
        int result = degree;
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
