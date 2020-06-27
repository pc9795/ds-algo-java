package gfg.algo.bit_algorithms;

public class GetSingle {

    public static int getSingle(int arr[]) {
        int ones = 0;
        int twos = 0;
        int commonBits = 0;
        for (int anArr : arr) {
//            have values that are appeared two times.
            twos |= ones & anArr;
//            it will add the value for first time and xor will remove if it is added second time.
            ones ^= anArr;
//            if values are appeared third time then it will be in both twos and ones so remove it.
            commonBits = ones & twos;
            ones &= ~commonBits;
            twos &= ~commonBits;
        }
        return ones;
    }
}