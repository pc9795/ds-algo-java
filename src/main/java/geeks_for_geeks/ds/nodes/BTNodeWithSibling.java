package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-01-2020 13:29
 **/
public class BTNodeWithSibling {
    public int data;
    public BTNodeWithSibling left;
    public BTNodeWithSibling right;
    public BTNodeWithSibling rightSibling;
    public BTNodeWithSibling leftSibling;

    public BTNodeWithSibling(int data) {
        this.data = data;
    }
}
