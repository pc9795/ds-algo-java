package gfg.ds.array;

import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created By: Prashant Chaubey
 * Created On: 20-07-2020 21:37
 **/
class TestApplications {

    @Test
    void testGetLeaders() {
        int arr[] = {16, 17, 4, 3, 5, 2};

        List<Integer> expected = Arrays.asList(17, 5, 2);
        List<Integer> actual = Applications.getLeaders(arr);

        assert actual.equals(expected);
    }

    @Test
    void testFindPairWithSum() {
        int arr[] = {0, -1, 2, -3, 1};

        Optional maybePair = Applications.findPairWithSum(arr, -2);

        assert maybePair.isPresent();
        assert maybePair.get().equals(new Pair<>(-3, 1));
    }

    @Test
    void testFindPairWithSumNotFound() {
        int arr[] = {1, -2, 1, 0, 5};

        Optional maybePair = Applications.findPairWithSum(arr, 0);

        assert !maybePair.isPresent();
    }

    @Test
    void testRotate() {
        assert Arrays.equals(Applications.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 2), new int[]{3, 4, 5, 6, 7, 1, 2});
        assert Arrays.equals(Applications.rotate(new int[]{1, 2, 3, 4, 5, 6, 7}, 7), new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    @Test
    void testRotateBuJuggling() {
        assert Arrays.equals(Applications.rotateByJuggling(new int[]{1, 2, 3, 4, 5, 6}, 6), new int[]{1, 2, 3, 4, 5, 6});
        assert Arrays.equals(Applications.rotateByJuggling(new int[]{1, 2, 3, 4, 5, 6}, 3), new int[]{4, 5, 6, 1, 2, 3});
        assert Arrays.equals(Applications.rotateByJuggling(new int[]{1, 2, 3, 4, 5, 6}, 4), new int[]{5, 6, 1, 2, 3, 4});
        assert Arrays.equals(Applications.rotateByJuggling(new int[]{1, 2, 3, 4, 5, 6, 7}, 2), new int[]{3, 4, 5, 6, 7, 1, 2});
    }

    @Test
    void testGetMedian() {
        assert Applications.getMedian(new int[]{2}, new int[]{3}) == 2.5;
        assert Applications.getMedian(new int[]{1, 2, 3, 4}, new int[]{4, 6, 8, 10}) == 4;
        assert Applications.getMedian(new int[]{1, 4, 7, 8}, new int[]{2, 3, 6, 9}) == 5;
        assert Applications.getMedian(new int[]{1, 12, 15, 26, 38}, new int[]{2, 3, 17, 30, 45}) == 16;
    }

    @Test
    void testMerge() {
        Integer[] expected = new Integer[]{2, 5, 7, 8, 10, 12, 14};
        assert Arrays.equals(Applications.merge(new Integer[]{2, null, 7, null, null, 10, null}, new Integer[]{5, 8, 12, 14}), expected);
    }

    @Test
    void testSearchInRotatedArr() {
        assert Applications.searchInRotatedSortedArr(new int[]{5, 6, 7, 8, 9, 10, 1, 2, 3}, 3);
        assert !Applications.searchInRotatedSortedArr(new int[]{5, 6, 7, 8, 9, 10, 1, 2, 3}, 30);
        assert Applications.searchInRotatedSortedArr(new int[]{30, 40, 50, 10, 20}, 10);
    }

    @Test
    void testFindMissingNumber() {
        assert Applications.findMissingNumber(new int[]{1, 2, 4, 6, 3, 7, 8}, 8) == 5;
    }

    @Test
    void testGetMaxSumContiguousSubArray() {
        assert Applications.getMaxSumContiguousSubarray(new int[]{-2, -3, 4, -1, -2, 1, 5, -3}).equals(new Pair<>(2, 6));
    }

    @Test
    void testGetOddTimesOccuringValue() {
        assert Applications.getOddTimesOccuringValue(new int[]{2, 3, 5, 4, 5, 2, 4, 3, 5, 2, 4, 4, 2}) == 5;
    }

    @Test
    void testGetMajorityElement() {
        assert Applications.getMajorityElement(new int[]{1, 3, 3, 1, 2}) == -1;
        assert Applications.getMajorityElement(new int[]{1, 3, 3, 3, 2}) == 3;
    }
}
