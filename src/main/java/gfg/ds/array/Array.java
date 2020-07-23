package gfg.ds.array;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 18:37
 **/
public class Array {

    public int[] values;
    public int size;

    public Array() {
        values = new int[10];
    }

    public Array(int size) {
        values = new int[size];
    }

    public Array(int[] values) {
        this.values = values;
        this.size = values.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == values.length;
    }

    /**
     * t=O(n)
     */
    public boolean search(int data) {
        for (int i = 0; i < size; i++) {
            if (values[i] == data) {
                return true;
            }
        }
        return false;
    }

    /**
     * t=O(1)
     */
    public Array insert(int data) {
        assert !isFull() : "Array is full";

        values[size++] = data;
        return this;
    }

    /**
     * t=O(n)
     */
    public void delete(int index) {
        assert !isEmpty() : "Array is empty";

        System.arraycopy(values, index + 1, values, index, size - 1 - index);
        size--;
    }

    /**
     * t=O(n)
     */
    public Array reverse() {
        for (int i = 0; i < size / 2; i++) {
            int temp = values[i];
            values[i] = values[size - 1 - i];
            values[size - 1 - i] = temp;
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Array array = (Array) o;

        return Arrays.equals(values, array.values);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }
}
