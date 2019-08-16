package geeks_for_geeks.algo.divide_and_conquer;

/**
 * Created By: Prashant Chaubey
 * Created On: 28-10-2018 18:01
 **/
public class Power {
    /**
     * T =O(logn)
     * Works for negative powers also.
     *
     * @param num
     * @param power
     * @return
     */
    public static float pow(float num, int power) {
        if (power == 0) {
            return 1;
        } else {
            float half = pow(num, power / 2);
            if (power % 2 == 0) {
                return half * half;
            } else {
                if (power > 0) {
                    return num * half * half;
                } else {
                    return half * half / num;
                }
            }
        }
    }
}
