package gfg.algo.bit_algorithms;

/**
 * Created By: Prashant Chaubey
 * Created On: 28-10-2018 20:05
 **/
public class CheckOppositeSigns {
    public static boolean checkOppositeSigns(int x, int y) {
        // xor of opposite sign numbers will result in 1 in the most significant digit.
        return (x ^ y) < 0;
    }
}
