package cracking_the_coding_interview.ch9_8_recursion_and_dynamic_programming;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-10-2019 19:15
 * Purpose: TODO:
 **/
public class SolutionTest {

    @Test
    void testTripleStep() {
        assert Solution.tripleStep(7) == 44;
    }

    @Test
    void testRobotInGrid() {
        boolean[][] mat = {
                {true, true, false, true},
                {true, false, true, true},
                {true, true, true, true}
        };
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        list.add(new Pair<>(0, 0));
        list.add(new Pair<>(1, 0));
        list.add(new Pair<>(2, 0));
        list.add(new Pair<>(2, 1));
        list.add(new Pair<>(2, 2));
        list.add(new Pair<>(2, 3));
        List<Pair<Integer, Integer>> ans = Solution.robotInGrid(mat);
        Collections.reverse(ans);
        assert list.equals(ans);
    }

    @Test
    void testMagicIndex() {
        int[] arr = {-40, -20, -1, 1, 2, 3, 5, 7, 9, 12, 13};
        assert Solution.magicIndex(arr) == 7;
    }

    @Test
    void testMagicIndex2() {
        int[] arr = {-10, -5, 2, 2, 2, 3, 4, 7, 9, 12, 13};
        assert Solution.magicIndex2(arr) == 2;
    }
}
