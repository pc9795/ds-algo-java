package gfg.ds.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-01-2019 18:56
 **/
public class NryTree {
    private NryNode root;

    public NryNode getRoot() {
        return root;
    }

    public void setRoot(NryNode root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Created By: Prashant Chaubey
     * Created On: 06-01-2019 18:57
     **/
    public static class NryNode {
        private int data;
        private List<NryNode> children;
        private int level;
        private NryNode parent;

        public NryNode(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        public int getData() {
            return data;
        }

        public List<NryNode> getChildren() {
            return children;
        }

        public NryNode getParent() {
            return parent;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (!(obj instanceof NryNode)) {
                return false;
            }
            NryNode other = (NryNode) obj;
            return (other.data == this.data);
        }

        @Override
        public String toString() {
            return "" + this.data;
        }
    }
}
