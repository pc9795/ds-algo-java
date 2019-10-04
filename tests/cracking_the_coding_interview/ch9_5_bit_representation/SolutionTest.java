package cracking_the_coding_interview.ch9_5_bit_representation;


import com.sun.org.apache.xpath.internal.Arg;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.Pair;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created By: Prashant Chaubey
 * Created On: 16-08-2019 23:37
 * Purpose: TODO:
 **/
class SolutionTest {

    static Stream<Arguments> testInsertion() {
        return Stream.of(
                Arguments.of(Integer.parseInt("1000000000", 2), Integer.parseInt("10011", 2), 6, 2,
                        Integer.parseInt("1001001100", 2))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testInsertion(int n, int m, int j, int i, int expected) {
        assert expected == Solution.insertion(n, m, j, i);
    }

    static Stream<Arguments> testBinaryToString() {
        return Stream.of(
                Arguments.of(1.2, 32, "ERROR"),
                Arguments.of(.33, 32, "ERROR"),
                Arguments.of(.24, 32, "")
        );
    }

    @ParameterizedTest
    @MethodSource
    void testBinaryToString(double number, int threshold, String answer) {
        assert answer.equals(Solution.binaryToString(number, threshold));
    }

    static Stream<Arguments> testFlipBitToWin() {
        return Stream.of(
                Arguments.of(1775, 8),
                Arguments.of(Integer.parseInt("100", 2), 2),
                Arguments.of(31, 5)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testFlipBitToWin(int integer, int expected) {
        assert Solution.flipBitToWin(integer) == expected;
    }

    static Stream<Arguments> testNextNumber() {
        return Stream.of(
                Arguments.of(Integer.parseInt("11010", 2), new Pair(25, 28)),
                Arguments.of(Integer.parseInt("111", 2), new Pair(-1, 11)),
                Arguments.of(Integer.parseInt("1110", 2), new Pair(13, 19))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testNextNumber(int num, Pair<Integer, Integer> expectedPair) {
        assert Solution.nextNumber(num).equals(expectedPair);
    }

    static Stream<Arguments> testConversion() {
        return Stream.of(
                Arguments.of(29, 15, 2)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testConversion(int num1, int num2, int expected) {
        assert Solution.conversion(num1, num2) == expected;
    }

    static Stream<Arguments> testPairwiseSwap() {
        return Stream.of(
                Arguments.of(12, 12),
                Arguments.of(Integer.parseInt("1010", 2), Integer.parseInt("0101", 2))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testPairwiseSwap(int num, int expected) {
        assert Solution.pairwiseSwap(num) == expected;
    }

    @ParameterizedTest
    @MethodSource("testPairwiseSwap")
    void testPairwiseSwap2(int num, int expected) {
        assert Solution.pairwiseSwap2(num) == expected;
    }

    @Test
    void testDrawLine() {
        byte[] arr = {0, 0, 0, 0, 0, 0};
        //0 based indexes
        Solution.drawLine(arr, 3 * 8, 5, 20, 1);
        assert Arrays.equals(arr, new byte[]{0, 0, 0, 7, (byte) -1, (byte) 248});
    }

    @Test
    void testDrawLine2() {
        byte[] arr = {0, 0, 0, 0, 0, 0};
        //0 based indexes
        Solution.drawLine2(arr, 3 * 8, 5, 20, 1);
        assert Arrays.equals(arr, new byte[]{0, 0, 0, 7, (byte) -1, (byte) 248});
    }

}
