package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2019 17:43
 * Purpose: TODO:
 **/
public class BTNodeWithRandom {
    public int data;
    public BTNodeWithRandom left;
    public BTNodeWithRandom right;
    public BTNodeWithRandom random;

    public BTNodeWithRandom(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BTNodeWithRandom{" +
                "data=" + data + "[" + (this.random == null ? "Null" : this.random.data) + "]" +
                '}';
    }
}
