package util;

/**
 * Created By: Prashant Chaubey
 * Created On: 16-08-2019 22:57
 * Purpose: Methods to manipulate bits.
 **/
public class BitUtils {

    public static boolean getBit(long num, int i) {
        return ((num & (1 << i)) != 0);
    }

    public static int setBit(int num, int i) {
        return num | (1 << i);
    }

    public static int clearBit(int num, int i) {
        int mask = ~(1 << i);
        return num & mask;
    }

    /**
     * from Most significant bit to i.
     *
     * @param num input number
     * @param i   position of bit from MSB
     * @return number after clearing bits.
     */
    public static int clearBitsMSBthroughI(int num, int i) {
        int mask = (1 << i) - 1;
        return num & mask;
    }

    public static int clearBitsThrough0(int num, int i) {
        int mask = (-1 << (i + 1));
        return num & mask;
    }

    public static int updateBit(int num, int i, boolean bitIs1) {
        int value = bitIs1 ? 1 : 0;
        int mask = (~1 << i);
        return (num & mask) | (value << i);
    }

    public static boolean isPowerOf2(int num) {
        return ((num & (num - 1)) == 0);
    }

    public static int msb(long n) {
        int ans = -1;
        for (int i = 0; n != 0; i++, n >>= 1) {
            if ((n & 1) == 1) {
                ans = i;
            }
        }
        return ans;
    }

    public static int lsb(long n) {
        int ans = -1;
        for (int i = 0; n != 0; i++, n >>= 1) {
            if ((n & 1) == 1) {
                ans = i;
                break;
            }
        }
        return ans;
    }
}

