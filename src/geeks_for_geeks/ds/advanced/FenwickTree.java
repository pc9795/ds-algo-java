package geeks_for_geeks.ds.advanced;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 05-12-2018 08:09
 **/
public class FenwickTree {
    public int[] values;

    public FenwickTree(int arr[]) {
        this.values = new int[arr.length + 1];
        build(arr);
    }

    /**
     * t=n*log(n)
     *
     * @param arr
     */
    public void build(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            update(i + 1, arr[i]);
        }
    }

    /**
     * t=O(log n)
     *
     * @param index 1 based index.
     * @param value
     */
    public void update(int index, int value) {
        if (index == 0) {
            return;
        }
        while (index <= this.values.length) {
            this.values[index] += value;
            index += index & (-index);
        }
    }

    /**
     * t=O(log n)
     *
     * @param index1
     * @param index2
     * @return
     */
    public int rq(int index1, int index2) {
        assert index1 <= index2;
        if (index1 == index2) {
            return query(index1 + 1);
        }
        return query(index2 + 1) - query(index1 + 1 - 1);
    }

    /**
     * t=O(log n)
     *
     * @param index 1 based index.
     * @return cumulative frequency at index
     */
    public int query(int index) {
        if (index == 0) {
            return 0;
        }
        int sum = 0;
        while (index > 0) {
            sum += this.values[index];
            index -= index & (-index);
        }
        return sum;
    }

    /**
     * Can be implemented as query(index)- query(index-1)
     *
     * @param index 1 based index.
     * @return
     */
    public int valueAt(int index) {
//         let index is a1b` where b` consists of all zeroes

        int sum = this.values[index];
        if (index > 0) {
//            a0b`
            int z = index - (index & (-index));
//          a0b where b consists of all ones
            index--;
//            their is some point where all ones are removed and z is reached.
            while (index != z) {
                sum -= this.values[index];
                index -= index & (-index);
            }
        }
        return sum;
    }

    public void scale(int c) {
        for (int i = 0; i < values.length; i++) {
            values[i] /= c;
        }
    }

    public int findIndexWithFreqSum(int freqSum) {
//        greatest bit of max index;
        int bitmask = (int) Math.pow(2, (int) (Math.log10(values.length) / Math.log10(2)));
        int index = 0;
        while (bitmask != 0) {
            int tempIndex = index + bitmask;
            bitmask >>= 1;
            if (tempIndex > values.length) {
                continue;
            }
            if (freqSum == values[tempIndex]) {
//                return 0 based index.
                return tempIndex - 1;
            } else if (freqSum > values[tempIndex]) {
                index = tempIndex;
                freqSum -= values[tempIndex];
            }
        }
        if (freqSum != 0) {
            return -1;
        }
//        return 0 based index.
        return index - 1;
    }

//  Tests

    public static void test1() {
        int arr[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        FenwickTree bit = new FenwickTree(arr);
        System.out.println(Arrays.toString(bit.values));
        System.out.println(bit.rq(0, 5));
        for (int i = 0; i < arr.length; i++) {
            System.out.print(bit.valueAt(i + 1) + " ");
        }
        System.out.println();
        System.out.println(bit.findIndexWithFreqSum(12));


    }

    public static void main(String[] args) {
        test1();
    }
}
