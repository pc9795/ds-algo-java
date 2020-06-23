package util;

import org.junit.jupiter.api.Test;
import util.Utils;

/**
 * Created By: Prashant Chaubey
 * Created On: 17-11-2019 16:36
 * Purpose: TODO:
 **/
public class UtilsTest {
    @Test
    void testAreaOfPolygon() {
        double x[] = {1, 1, 2, 3, 4, 5, 5};
        double y[] = {0, 1, 3, 4, 5, 2, 0};
        System.out.println(Utils.areaOfPolygon(x, y));
    }
}
