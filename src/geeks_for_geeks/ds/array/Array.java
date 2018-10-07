package geeks_for_geeks.ds.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 18:37
 **/
public class Array {

    public int[] values;
    public int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == values.length;
    }

    public Array() {
        values = new int[10];
    }

    public Array(int size) {
        values = new int[size];
    }

    public boolean search(int data) {
        for (int i = 0; i < size; i++) {
            if (values[i] == data) {
                return true;
            }
        }
        return false;
    }

    public Array insert(int data) {
        if (isFull()) {
            throw new RuntimeException("Array is full");
        }
        values[size++] = data;
        return this;
    }

    public void delete(int index) {
        if (isEmpty()) {
            throw new RuntimeException("Array is empty");
        }
        for (int i = index; i < size - 1; i++) {
            values[i] = values[i + 1];
        }
        size--;

    }

    public Array reverse() {
        for (int i = 0; i < size / 2; i++) {
            int temp = values[i];
            values[i] = values[size - 1 - i];
            values[size - 1 - i] = temp;
        }
        return this;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(values[i] + "=>");
        }
    }

    public static void main(String[] args) {
        Array array = new Array();
        array.insert(1).insert(2).insert(3).insert(4).insert(5).reverse().print();
    }
}
