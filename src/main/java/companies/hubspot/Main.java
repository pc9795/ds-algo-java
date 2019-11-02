package companies.hubspot;

import java.util.LinkedList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 02-11-2019 03:50
 * Purpose: TODO:
 **/
public class Main {

    private boolean checkSorted(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        int value = list.get(0);
        for (int i = 1; i < list.size() - 1; i++) {
            if (value > list.get(i)) {
                return false;
            }
            value = list.get(i);
        }
        return true;
    }

    public List<Integer> mergeSorted(List<Integer> list1, List<Integer> list2) {
        assert list1 != null && list2 != null;
        assert checkSorted(list1) && checkSorted(list2);
        List<Integer> result = new LinkedList<>();
        int i, j;
        for (i = 0, j = 0; i < list1.size() || j < list2.size(); ) {
            if (list1.get(i) < list2.get(j)) {
                result.add(list1.get(i++));
            } else {
                result.add(list2.get(j++));
            }
        }
        for (; i < list1.size(); i++) {
            result.add(list1.get(i));
        }
        for (; j < list2.size(); j++) {
            result.add(list2.get(j));
        }
        return result;
    }
}
