package ctci.ch9_1_arrays_and_strings;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-08-2019 16:23
 **/
class SolutionTest {

    private static Stream<Arguments> testIsUnique() {
        return Stream.of(
                Arguments.of("", true),
                Arguments.of(null, true),
                Arguments.of("abc", true),
                Arguments.of("abcc", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testIsUnique")
    void testIsUnique(String input, boolean expected) {
        assert Solution.isUnique(input) == expected;
    }

    @ParameterizedTest
    @MethodSource("testIsUnique")
    void testIsUnique2(String input, boolean expected) {
        assert Solution.isUnique2(input) == expected;
    }

    private static Stream<Arguments> testCheckPermutation() {
        return Stream.of(
                Arguments.of(null, null, true),
                Arguments.of(null, "abc", false),
                Arguments.of("abc", null, false),
                Arguments.of("abc", "cab", true),
                Arguments.of("abc", "", false),
                Arguments.of("abc", "abb", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testCheckPermutation")
    void testCheckPermutation(String input1, String input2, boolean expected) {
        assert Solution.checkPermutation(input1, input2) == expected;
    }

    private static Stream<Arguments> testUrlify() {
        return Stream.of(
                Arguments.of("abc", 0, "abc"),
                Arguments.of("abc", -1, "abc"),
                Arguments.of(null, 10, ""),
                Arguments.of("Mr John Smith    ", 13, "Mr20%John20%Smith")
        );
    }

    @ParameterizedTest
    @MethodSource("testUrlify")
    void testUrlify(String str, int trueLength, String expected) {
        assert Solution.urlify(str, trueLength).equals(expected);
    }

    private static Stream<Arguments> testPalindromePermutation() {
        return Stream.of(
                Arguments.of("", true),
                Arguments.of("a", true),
                Arguments.of("ab", false),
                Arguments.of("ab A", true),
                Arguments.of("  ", true)
        );
    }

    @ParameterizedTest
    @MethodSource("testPalindromePermutation")
    void testPalindromePermutation(String str, boolean expected) {
        assert Solution.palindromePermutation(str) == expected;
    }

    private static Stream<Arguments> testOneAway() {
        return Stream.of(
                Arguments.of("pale", "ple", true),
                Arguments.of("pales", "pale", true),
                Arguments.of("pale", "bale", true),
                Arguments.of("pale", "bake", false),
                Arguments.of("pale", "palebb", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testOneAway")
    void testOneAway(String str1, String str2, boolean expected) {
        assert Solution.oneAway(str1, str2) == expected;
    }

    private static Stream<Arguments> testStringCompression() {
        return Stream.of(
                Arguments.of(null, ""),
                Arguments.of("", ""),
                Arguments.of("aabcccccaaa", "a2b1c5a3"),
                Arguments.of("aa", "aa")
        );
    }

    @ParameterizedTest
    @MethodSource("testStringCompression")
    void testStringCompression(String str, String expected) {
        assert Solution.stringCompression(str).equals(expected);

    }

    @Test
    void testRotateMatrix() {
        int[][] mat =
                {{1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 16}};
        int[][] expected = {
                {13, 9, 5, 1},
                {14, 10, 6, 2},
                {15, 11, 7, 3},
                {16, 12, 8, 4}
        };
        assert Arrays.deepEquals(Solution.rotateMatrix(mat), expected);
    }

    @Test
    void testZeroMatrix() {
        int[][] mat = {
                {1, 2, 3, 4},
                {5, 0, 7, 8},
                {9, 10, 11, 0},
                {13, 14, 15, 16}
        };
        int[][] expected = {
                {1, 0, 3, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {13, 0, 15, 0}
        };
        assert Arrays.deepEquals(Solution.zeroMatrix(mat), expected);
    }

    private static Stream<Arguments> testStringRotation() {
        return Stream.of(
                Arguments.of("waterbottle", "erbottlewat", true),
                Arguments.of("abcd", "bcdd", false),
                Arguments.of("abc", "abcd", false)
        );
    }

    @ParameterizedTest
    @MethodSource("testStringRotation")
    void testStringRotation(String str1, String str2, boolean expected) {
        assert Solution.stringRotation(str1, str2) == expected;
    }
}

