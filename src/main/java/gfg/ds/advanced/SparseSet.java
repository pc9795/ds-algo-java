package gfg.ds.advanced;

import java.util.HashSet;
import java.util.Set;

/**
 * Created By: Prashant Chaubey
 * Created On: 21-07-2020 01:44
 **/
public class SparseSet {
    private int[] dense;
    private int[] sparse;
    private int capacity;
    private int maxPossibleValue;
    private int size;

    public SparseSet(int capacity, int maxPossibleValue) {
        this.capacity = capacity;
        this.dense = new int[capacity];
        this.maxPossibleValue = maxPossibleValue;
        this.sparse = new int[maxPossibleValue + 1];
    }

    public boolean canBeInserted(int data) {
        return data <= maxPossibleValue && size < capacity;
    }

    /**
     * t=O(1)
     */
    public SparseSet add(int data) {
        assert canBeInserted(data) : "Can't insert";

        if (search(data)) {
            return this;
        }

        dense[size] = data;
        sparse[data] = size;
        size++;

        return this;
    }

    /**
     * t=O(1)
     */
    public boolean search(int data) {
        return data <= maxPossibleValue && sparse[data] < size && dense[sparse[data]] == data;
    }

    /**
     * t=O(1)
     */
    public SparseSet delete(int data) {
        assert search(data) : "Not found";

        int index = sparse[data];
        dense[index] = dense[size - 1];
        sparse[dense[size - 1]] = index;
        size--;

        return this;
    }

    /**
     * t=O(1)
     */
    public void clear() {
        size = 0;
    }

    /**
     * t=O(n)
     */
    public Set<Integer> values() {
        Set<Integer> values = new HashSet<>();
        for (int i = 0; i < size; i++) {
            values.add(dense[i]);
        }

        return values;
    }

    /**
     * t=O(max(n1, n2))
     */
    public SparseSet union(SparseSet other) {
        assert other != null;

        SparseSet union = new SparseSet(size + other.size, Math.max(maxPossibleValue, other.maxPossibleValue));

        for (int i = 0; i < size; i++) {
            union.add(dense[i]);
        }

        for (int i = 0; i < other.size; i++) {
            union.add(other.dense[i]);
        }

        return union;
    }

    /**
     * t=O(min(n1, n2))
     */
    public SparseSet intersection(SparseSet other) {
        assert other != null;

        SparseSet intersection = new SparseSet(Math.min(size, other.size), Math.max(maxPossibleValue, other.maxPossibleValue));

        SparseSet small = size < other.size ? this : other;
        SparseSet large = size < other.size ? other : this;

        for (int i = 0; i < small.size; i++) {
            if (large.search(small.dense[i])) {
                intersection.add(small.dense[i]);
            }
        }

        return intersection;
    }

}
