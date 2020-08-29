package gfg.algo.divide_and_conquer;

public class Power {
  /** t=O(logn) Works for negative powers also. */
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
