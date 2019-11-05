package companies.hubspot;

import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 03-11-2019 22:20
 * Purpose: TODO:
 **/
public class MainTest {

    @Test
    public void testMostCommonN_Gram() {
        String str = "aaaab a0a baaab c";
        assert Main.mostCommonN_Gram(str, 3).equals("aaa");
    }

    @Test
    public void testConvertToPalindrome() {
        List<String> list = Arrays.asList("pnaap", "omm");
        List<String> expected = Arrays.asList("panap", "mom");
        assert Main.convertToPalindromes(list).equals(expected);
    }

    @Test
    public void testGetPairWithSumN() {
        int arr[] = {1, 4, 45, 6, 10, -8};
        assert Main.getPairWithSumN(arr, 16).equals(new Pair<>(6, 10));
        assert Main.getPairWithSumN2(arr, 16).equals(new Pair<>(6, 10));
        assert Main.getPairWithSumN(arr, 50) == null;
        assert Main.getPairWithSumN(arr, 50) == null;
    }
}
