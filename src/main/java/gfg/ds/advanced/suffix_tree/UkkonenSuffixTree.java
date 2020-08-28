package gfg.ds.advanced.suffix_tree;

import utils.Pointer;

public class UkkonenSuffixTree {
  private static int ALPHABET_SIZE = 26 + 1; // +1 for terminal character
  private String input;
  private UkkonenSuffixTreeNode root;

  public UkkonenSuffixTree(String input) {
    this.input = input;
    this.root = new UkkonenSuffixTreeNode(ALPHABET_SIZE, -1, new Pointer<>(-1), null);
    build();
  }

  public UkkonenSuffixTreeNode getRoot() {
    return root;
  }

  /** t=O(n) */
  private void build() {
    UkkonenSuffixTreeNode lastNewNode;
    ActivePoint activePoint = new ActivePoint(root, -1, 0);
    int remainingSuffixCount = 0;
    Pointer<Integer> leafEnd = new Pointer<>(-1);

    for (int currIndex = 0; currIndex < input.length(); currIndex++) {
      leafEnd.data = currIndex; // Trick 3
      remainingSuffixCount++;
      lastNewNode = null;

      while (remainingSuffixCount > 0) {
        if (activePoint.activeLength == 0) {
          activePoint.activeEdge = currIndex; // APCFALZ
        }

        UkkonenSuffixTreeNode outgoingEdge = getOutgoingEdge(activePoint);
        if (outgoingEdge == null) {
          createOutgoingEdge(activePoint, currIndex, leafEnd); // Rule 2
          // Set suffix link of any node waiting in previous extension
          if (lastNewNode != null) {
            lastNewNode.suffixLink = activePoint.activeNode;
            lastNewNode = null;
          }
        } else {
          if (walkDown(activePoint, outgoingEdge)) {
            continue;
          }
          // Rule 3
          if (input.charAt(outgoingEdge.start + activePoint.activeLength)
              == input.charAt(currIndex)) {
            if (lastNewNode != null) {
              lastNewNode.suffixLink = activePoint.activeNode;
            }
            activePoint.activeLength++; // APCFER3
            break;
          }

          Pointer<Integer> splitEnd =
              new Pointer<>(outgoingEdge.start + activePoint.activeLength - 1);
          UkkonenSuffixTreeNode split = newNode(outgoingEdge.start, splitEnd);
          createOutgoingEdge(activePoint, split);
          split.children[toIndex(input.charAt(currIndex))] = newNode(currIndex, leafEnd);
          outgoingEdge.start += activePoint.activeLength;
          split.children[toIndex(input.charAt(outgoingEdge.start))] = outgoingEdge;
          if (lastNewNode != null) {
            lastNewNode.suffixLink = split;
          }
          lastNewNode = split;
        }
        remainingSuffixCount--;
        if (activePoint.activeNode == root && activePoint.activeLength > 0) {
          activePoint.activeLength--;
          activePoint.activeEdge = currIndex - remainingSuffixCount + 1; // APCFER2C1
        } else if (activePoint.activeNode != root) {
          activePoint.activeNode = activePoint.activeNode.suffixLink; // APCFER2C2
        }
      }
    }

    setSuffixIndexByDfs();
  }

  private int toIndex(char ch) {
    if (ch == '$') {
      return 26;
    }
    return ch - 'a';
  }

  private UkkonenSuffixTreeNode getOutgoingEdge(ActivePoint activePoint) {
    return activePoint.activeNode.children[toIndex(input.charAt(activePoint.activeEdge))];
  }

  private void createOutgoingEdge(ActivePoint activePoint, int start, Pointer<Integer> end) {
    activePoint.activeNode.children[toIndex(input.charAt(activePoint.activeEdge))] =
        newNode(start, end);
  }

  private void createOutgoingEdge(ActivePoint activePoint, UkkonenSuffixTreeNode node) {
    activePoint.activeNode.children[toIndex(input.charAt(activePoint.activeEdge))] = node;
  }

  private UkkonenSuffixTreeNode newNode(int start, Pointer<Integer> end) {
    return new UkkonenSuffixTreeNode(ALPHABET_SIZE, start, end, root);
  }

  /** APCFWD */
  private boolean walkDown(ActivePoint activePoint, UkkonenSuffixTreeNode node) {
    int nodeEdgeLength = node.getEdgeLength();
    if (activePoint.activeLength < nodeEdgeLength) {
      return false;
    }
    activePoint.activeEdge += nodeEdgeLength;
    activePoint.activeLength -= nodeEdgeLength;
    activePoint.activeNode = node;
    return true;
  }

  private void setSuffixIndexByDfs() {
    setSuffixIndexByDfsUtil(root, 0);
  }

  private void setSuffixIndexByDfsUtil(UkkonenSuffixTreeNode node, int labelHeight) {
    if (node == null) {
      return;
    }

    boolean isLeaf = true;
    for (int i = 0; i < node.children.length; i++) {
      UkkonenSuffixTreeNode child = node.children[i];
      if (child == null) {
        continue;
      }
      isLeaf = false; // If this node has at least one child then it is not a leaf node
      setSuffixIndexByDfsUtil(child, labelHeight + child.getEdgeLength());
    }

    if (isLeaf) {
      node.suffixIndex = input.length() - labelHeight;
    }
  }

  public static class UkkonenSuffixTreeNode {
    private UkkonenSuffixTreeNode[] children;
    private UkkonenSuffixTreeNode suffixLink;
    private int start;
    private Pointer<Integer> end; // So that many leaf nodes can share a common value
    private int suffixIndex = -1;

    UkkonenSuffixTreeNode(
        int alphabetSize, int start, Pointer<Integer> end, UkkonenSuffixTreeNode suffixLink) {
      this.children = new UkkonenSuffixTreeNode[alphabetSize];
      this.start = start;
      this.end = end;
      this.suffixLink = suffixLink;
    }

    int getEdgeLength() {
      return end.data - start + 1;
    }

    public UkkonenSuffixTreeNode[] getChildren() {
      return children;
    }

    public UkkonenSuffixTreeNode getSuffixLink() {
      return suffixLink;
    }

    public int getStart() {
      return start;
    }

    public Pointer<Integer> getEnd() {
      return end;
    }

    public int getSuffixIndex() {
      return suffixIndex;
    }
  }

  private static class ActivePoint {
    private UkkonenSuffixTreeNode activeNode;
    private int activeEdge;
    private int activeLength;

    private ActivePoint(UkkonenSuffixTreeNode activeNode, int activeEdge, int activeLength) {
      this.activeNode = activeNode;
      this.activeEdge = activeEdge;
      this.activeLength = activeLength;
    }
  }
}
