package geeks_for_geeks.ds.tree;

import geeks_for_geeks.ds.nodes.N_ryNode;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-01-2019 18:56
 **/
public class N_ryTree {
    public N_ryNode root;

    public N_ryTree(int rootData) {
        this.root = new N_ryNode(rootData);
        this.root.parent = this.root;
    }
}
