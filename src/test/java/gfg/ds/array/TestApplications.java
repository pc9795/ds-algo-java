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
}
