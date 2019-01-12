package geeks_for_geeks.algo.dp;

import geeks_for_geeks.ds.nodes.N_ryNode;
import geeks_for_geeks.ds.tree.N_ryTree;
import geeks_for_geeks.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-01-2019 18:39
 * Purpose: TODO:
 **/
public class LCA {


    /**
     * t=O(sqrt(n))
     *
     * @param currentNode
     * @param sectionParent
     * @param sectionWidth
     */
    private static void traverseUtil(N_ryNode currentNode, Map<N_ryNode, N_ryNode> sectionParent, int sectionWidth) {
        if (currentNode.level % sectionWidth == 0) {
            sectionParent.put(currentNode, currentNode.parent);
        } else {
            sectionParent.put(currentNode, sectionParent.get(currentNode.parent));
        }
        for (N_ryNode child : currentNode.children) {
            traverseUtil(child, sectionParent, sectionWidth);
        }
    }

    /**
     * t=O(sqrt(n))
     *
     * @param tree
     * @param height
     * @param a
     * @param b
     * @return
     */
    public static N_ryNode lca(N_ryTree tree, int height, N_ryNode a, N_ryNode b) {
        Map<N_ryNode, N_ryNode> map = new HashMap<>();
        traverseUtil(tree.root, map, (int) (Math.log10(height) / Math.log10(2)));
        return lcaUtil(a, b, map);
    }

    /**
     * t=O(sqrt(n))
     *
     * @param a
     * @param b
     * @param sectionParent
     * @return
     */
    private static N_ryNode lcaUtil(N_ryNode a, N_ryNode b, Map<N_ryNode, N_ryNode> sectionParent) {
        while (!sectionParent.get(a).equals(sectionParent.get(b)))
            if (a.level > b.level)
                a = sectionParent.get(a);
            else
                b = sectionParent.get(b);

        while (!a.equals(b))
            if (a.level > b.level)
                a = a.parent;
            else
                b = b.parent;
        return a;
    }

    /**
     * t=O(N*logN) for a skew tree with H=N.
     *
     * @param parent
     */
    private static int[][] getLCADPMatrix(int parent[]) {
        int[][] dp = new int[parent.length][(int) (Math.ceil(Math.log10(parent.length) / Math.log10(2))) + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = -1;
            }
        }
        for (int i = 0; i < parent.length; i++) {
            dp[i][0] = parent[i];
        }
        for (int j = 1; j < dp[0].length; j++) {
            for (int i = 0; i < parent.length; i++) {
                if (dp[i][j - 1] != -1) {
                    dp[i][j] = dp[dp[i][j - 1]][j - 1];
                }
            }
        }
        return dp;
    }

    /**
     * T = O(log H)
     *
     * @param a
     * @param b
     * @param parent
     * @param level
     * @param dp
     * @return
     */
    private static int lcaUtil(int a, int b, int parent[], int level[], int[][] dp) {
        //if p is situated on a higher level than q then we swap them
        if (level[a] < level[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        int log = (int) (Math.log10(level[a]) / Math.log10(level[b]));


        //we find the ancestor of node p situated on the same level
        //with q using the values in P
//        O(log H)
        for (int i = log; i >= 0; i--) {
            if (level[a] - (1 << i) >= level[b]) {
                a = dp[a][i];
            }
        }

        if (a == b)
            return a;

//        O(log H)
        //we compute LCA(p, q) using the values in P
        for (int i = log; i >= 0; i--) {
            if (dp[a][i] != -1 && dp[a][i] != dp[b][i]) {
                a = dp[a][i];
                b = dp[b][i];
            }
        }
        return parent[a];
    }

    public static int lca(int a, int b, int parent[], int level[]) {
        int[][] dp = getLCADPMatrix(parent);
        Util.prettyPrint2DMatrix(dp);
        System.out.println();
        return -1;
    }

//Tests

    /*             1
     *          /  |  \
     *         2   3   4
     *           / |  \
     *          5  6    7
     *            / \   / \
     *           8   9 10 11
     *                 / \
     *                12  13
     * */

    public static void test1() {
        N_ryTree tree = new N_ryTree(1);
        tree.root.addChild(2, 3, 4);
        N_ryNode secondChild = tree.root.children.get(1);
        secondChild.addChild(5, 6, 7);
        secondChild.children.get(1).addChild(8, 9);
        secondChild.children.get(2).addChild(10, 11);
        secondChild.children.get(2).children.get(0).addChild(12, 13);
        System.out.println(lca(tree, 5, secondChild.children.get(1).children.get(1), secondChild.children.get(2).children.get(0).children.get(0)));
    }

    public static void test2() {
        int parent[] = {-1, 0, 0, 0, 2, 2, 2, 5, 5, 6, 6, 9, 9};
        int level[] = {1, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5};
        System.out.println(lca(9, 12, parent, level));

    }

    public static void main(String[] args) {
        test2();
    }

}
