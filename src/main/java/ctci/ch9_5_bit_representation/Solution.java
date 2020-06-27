package ctci.ch9_5_bit_representation;

import utils.BitUtils;
import utils.Pair;

import java.util.ArrayList;

/**
 * Created By: Prashant Chaubey
 * Created On: 16-08-2019 23:32
 **/
public class Solution {

    public static int insertion(int n, int m, int j, int i) {
        //Removing extra bits which are not be inserted and if present.
        m = m & ((1 << (j - i + 1)) - 1);
        //Shifting the needed bits.
        m <<= i;
        int mask = (-1 << (j + 1)) | ((1 << i) - 1);
        //Clearing bits between jth and ith bits.
        n &= mask;
        return n | m;
    }

    public static String binaryToString(double number, int threshold) {
        if (Double.compare(number, 0) < 0 || Double.compare(number, 1) > 0) {
            return "";
        }
        StringBuilder binaryString = new StringBuilder();
        while (threshold-- > 0) {
            if (Double.compare(number, 0) == 0) {
                return binaryString.toString();
            }
            binaryString.append((int) (number * 2));
        }
        // Not able to get the binary representation under threshold.
        return "ERROR";
    }

    //T=O(b), S=O(b); b is the length of the sequence.
    public static int flipBitToWin(int integer) {
        //Length of alternate sequences of 0s and 1s.
        ArrayList<Integer> list = new ArrayList<>();
        int counter = 0;
        int searchingFor = 0;
        for (char ch : Integer.toBinaryString(integer).toCharArray()) {
            if (ch != searchingFor) {
                searchingFor = ch;
                list.add(counter);
                counter = 0;
            }
            counter++;
        }
        list.add(counter);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < list.size(); i += 2) {
            int zeroSeqLength = list.get(i);
            int oneSeqLengthLeft = i - 1 >= 0 ? list.get(i - 1) : 0;
            int oneSeqLengthRight = i + 1 < list.size() ? list.get(i + 1) : 0;
            int curr = 0;
            if (zeroSeqLength == 0) {
                curr = Math.max(oneSeqLengthLeft, oneSeqLengthRight);
            } else if (zeroSeqLength == 1) {
                curr = 1 + oneSeqLengthLeft + oneSeqLengthRight;
            } else if (zeroSeqLength > 1) {
                curr = 1 + Math.max(oneSeqLengthLeft, oneSeqLengthRight);
            }
            max = max > curr ? max : curr;
        }
        return max;
    }

    public static Pair<Integer, Integer> nextNumber(int num) {
        if (num < 0) {
            return new Pair<>(-1, -1);
        }
        return new Pair<>(getPrevious(num), getNext(num));
    }


    private static int getNext(int num) {
        int c0 = 0;
        int c1 = 0;
        int temp = num;
        while ((temp & 1) == 0 && temp != 0) {
            c0++;
            temp >>= 1;
        }
        while ((temp & 1) == 1 && temp != 0) {
            c1++;
            temp >>= 1;
        }
        //A seq of type 111...000...
        if (c0 + c1 == 31 || c0 + c1 == 0) {
            return -1;
        }
        int p = c0 + c1;
        num = BitUtils.setBit(num, p);
        num = BitUtils.clearBitsThrough0(num, p - 1);
        //Add c1-1 ones at the last.
        num |= (1 << (c1 - 1)) - 1;
        return num;
    }

    static int getPrevious(int num) {
        int c0 = 0;
        int c1 = 0;
        int temp = num;
        while ((temp & 1) == 1 && temp != 0) {
            c1++;
            temp >>= 1;
        }
        if (temp == 0) {
            return -1;
        }
        while ((temp & 1) == 0 && temp != 0) {
            c0++;
            temp >>= 1;
        }
        int p = c0 + c1;
        num = BitUtils.clearBit(num, p);
        num = BitUtils.clearBitsThrough0(num, p - 1);
        //Add c1+1 ones after the point followed by zeroes
        num |= ((1 << c1 + 1) - 1) << c0 - 1;
        return num;
    }

    public static int conversion(int num1, int num2) {
        int counter = 0;
        //We can use xor also.
        while (num1 != 0 || num2 != 0) {
            counter += (num1 & 1) != (num2 & 1) ? 1 : 0;
            num1 >>= 1;
            num2 >>= 1;
        }
        return counter;
    }

    public static int pairwiseSwap(int num) {
        int ans = 0;
        int counter = 0;
        while (num != 0) {
            ans += (num & 1) * (int) Math.pow(2, counter + 1);
            num >>= 1;
            ans += (num & 1) * (int) Math.pow(2, counter);
            num >>= 1;
            counter += 2;
        }
        return ans;
    }

    public static int pairwiseSwap2(int num) {
        // We are using unsigned right shift as we want 0 at the sign bit.
        return ((num & 0xaaaaaaaa) >>> 1) | ((num & 0x55555555) << 1);
    }

    public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
        for (int i = x1; i <= x2; i++) {
            byte num = screen[(y * (width / 8)) + (i / 8)];
            screen[(y * (width / 8)) + (i / 8)] = (byte) BitUtils.setBit(num, 7 - (i % 8));
        }
    }

    public static void drawLine2(byte[] screen, int width, int x1, int x2, int y) {
        int heightOffset = y * (width / 8);
        int firstOffset = x1 % 8;
        int firstByte = x1 / 8;
        if (firstOffset != 0) {
            //Move one forward because that byte is not complete.
            firstByte++;
        }

        int lastOffset = x2 % 8;
        int lastByte = x2 / 8;
        if (lastOffset != 7) {
            //Move one backward because that byte is not complete;
            lastByte--;
        }

        for (int b = firstByte; b <= lastByte; b++) {
            screen[heightOffset + b] = (byte) 0xff;
        }
        byte startMask = (byte) (0xff >> firstOffset);
        byte lastMask = (byte) ~(0xff >> lastOffset + 1);
        if ((x1 / 8) == (x2 / 8)) {
            screen[heightOffset + (x1 / 8)] |= (byte) (startMask & lastMask);
        } else {
            if (firstOffset != 0) {
                screen[heightOffset + firstByte - 1] |= startMask;
            }
            if (lastOffset != 7) {
                screen[heightOffset + lastByte + 1] |= lastMask;
            }
        }
    }
}
