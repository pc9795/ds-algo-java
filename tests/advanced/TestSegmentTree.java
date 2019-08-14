package advanced;

import geeks_for_geeks.ds.advanced.PersistentSegmentTree;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 17-02-2019 02:25
 * Purpose: TODO:
 **/
class TestSegmentTree {

    @Test
    void testPersistentSegmentTree() {
        int arr[] = {1, 2, 3, 4};
        PersistentSegmentTree st = new PersistentSegmentTree(arr);
        assert st.query(0, 1) == 3;
        st.update(0, 5);

        assert st.versions.size() == 2;
        assert st.versions.get(0).data == 10;
        assert st.versions.get(1).data == 14;
    }
}
