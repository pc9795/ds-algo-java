package geeks_for_geeks.ds.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-01-2019 18:57
 **/
public class N_ryNode {
    public int data;
    public List<N_ryNode> children;
    public int level;
    public N_ryNode parent;

    public N_ryNode(int data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    private N_ryNode(int data, int level, N_ryNode parent) {
        this.data = data;
        this.children = new ArrayList<>();
        this.level = level;
        this.parent = parent;
    }

    public void addChild(int... childData) {
        for (int child : childData) {
            this.children.add(new N_ryNode(child, level + 1, this));
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof N_ryNode)) {
            return false;
        }
        N_ryNode other = (N_ryNode) obj;
        return (other.data == this.data) && (other.children.equals(this.children));
    }

    @Override
    public String toString() {
        return "" + this.data;
    }
}
