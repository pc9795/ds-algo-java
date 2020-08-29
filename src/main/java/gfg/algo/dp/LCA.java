package gfg.algo.dp;

import gfg.ds.tree.NryTree;

import java.util.HashMap;
import java.util.Map;

public class LCA {

  /** t=O(sqrt(n)) */
  public static NryTree.NryNode lca(
      NryTree tree, int height, NryTree.NryNode a, NryTree.NryNode b) {
    Map<NryTree.NryNode, NryTree.NryNode> map = new HashMap<>();
    traverseUtil(tree.getRoot(), map, (int) (Math.log10(height) / Math.log10(2)));
    return lcaUtil(a, b, map);
  }

  private static void traverseUtil(
      NryTree.NryNode currentNode,
      Map<NryTree.NryNode, NryTree.NryNode> sectionParent,
      int sectionWidth) {
    if (currentNode.getLevel() % sectionWidth == 0) {
      sectionParent.put(currentNode, currentNode.getParent());
    } else {
      sectionParent.put(currentNode, sectionParent.get(currentNode.getParent()));
    }
    for (NryTree.NryNode child : currentNode.getChildren()) {
      traverseUtil(child, sectionParent, sectionWidth);
    }
  }

  private static NryTree.NryNode lcaUtil(
      NryTree.NryNode a, NryTree.NryNode b, Map<NryTree.NryNode, NryTree.NryNode> sectionParent) {
    while (!sectionParent.get(a).equals(sectionParent.get(b))) {
      if (a.getLevel() > b.getLevel()) {
        a = sectionParent.get(a);
      } else {
        b = sectionParent.get(b);
      }
    }

    while (!a.equals(b)) {
      if (a.getLevel() > b.getLevel()) {
        a = a.getParent();
      } else {
        b = b.getParent();
      }
    }
    return a;
  }

  /** todo time complexity */
  public static int lca(int a, int b, int parent[], int level[]) {
    // t=O(N*logN) for a skew tree with H=N.
    int[][] dp = getLcaDpMatrix(parent);
    // t=O(log H)
    return lcaUtil(a, b, parent, level, dp);
  }

  private static int[][] getLcaDpMatrix(int parent[]) {
    int[][] dp =
        new int[parent.length][(int) (Math.ceil(Math.log10(parent.length) / Math.log10(2))) + 1];

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

  private static int lcaUtil(int a, int b, int parent[], int level[], int[][] dp) {
    // if p is situated on a higher level than q then we swap them; higher level means lower value.
    if (level[a] < level[b]) {
      int temp = a;
      a = b;
      b = temp;
    }
    int log = (int) (Math.log10(level[a]) / Math.log10(2));

    // O(log H)
    // we find the ancestor of node a situated on the same level with a using the values in dp
    for (int i = log; i >= 0; i--) {
      if (level[a] - (1 << i) >= level[b]) {
        a = dp[a][i];
      }
    }

    if (a == b) {
      return a;
    }

    // O(log H)
    // we compute LCA(a, b) using the values in dp
    for (int i = log; i >= 0; i--) {
      if (dp[a][i] != -1 && dp[a][i] != dp[b][i]) {
        a = dp[a][i];
        b = dp[b][i];
      }
    }

    return parent[a];
  }
}
