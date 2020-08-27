package utils;

import org.junit.jupiter.api.Test;

class UtilsTest {

    @Test
    void testAreaOfPolygon() {
        double x[] = {1, 1, 2, 3, 4, 5, 5};
        double y[] = {0, 1, 3, 4, 5, 2, 0};
        System.out.println(Utils.areaOfPolygon(x, y));
    }

    @Test
    void testGetDecimalFromBooleanArray() {
        boolean[] arr = new boolean[]{true, false, true, true, false};

        assert Utils.getDecimalFromBooleanArray(arr) == 22;
    }
}
