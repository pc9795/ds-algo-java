package geeks_for_geeks.algo.bit_algorithms;

public class GetSingle {

    public static int getSingle(int arr[]) {
        int ones = 0;
        int twos = 0;
        int commonBits = 0;
        for (int i = 0; i < arr.length; i++) {
//            have values that are appeared two times.
            twos |= ones & arr[i];
//            it will add the value for first time and xor will remove if it is added second time.
            ones ^= arr[i];
//            if values are appeared third time then it will be in both twos and ones so remove it.
            commonBits = ones & twos;
            ones &= ~commonBits;
            twos &= ~commonBits;
        }
        return ones;
    }

    public static void main(String[] args) {
        System.out.println(getSingle(new int[]{3, 3, 10, 3}));
    }
}