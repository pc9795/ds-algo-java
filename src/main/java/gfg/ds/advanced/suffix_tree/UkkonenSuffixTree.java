package gfg.ds.advanced.suffix_tree;

import utils.Pointer;

import java.util.*;

/** @noinspection WeakerAccess */
public class UkkonenSuffixTree {
  private static final Map<Character, Integer> TERMINAL_CHAR_MAP = new HashMap<>();
  private static final Map<Integer, Character> TERMINAL_CHAR_INV_MAP = new HashMap<>();

  static {
    TERMINAL_CHAR_MAP.put('$', 0);
    TERMINAL_CHAR_MAP.put('#', 1);
    for (Character key : TERMINAL_CHAR_MAP.keySet()) {
      TERMINAL_CHAR_INV_MAP.put(TERMINAL_CHAR_MAP.get(key), key);
    }
  }

  private static final int ALPHABET_SIZE = 26;

  private String combinedInput;
  private List<String> inputs;
  private UkkonenSuffixTreeNode root;

  public UkkonenSuffixTree(String input) {
    this.combinedInput = input + TERMINAL_CHAR_INV_MAP.get(0);
    this.inputs = Collections.singletonList(input);
    this.root =
        new UkkonenSuffixTreeNode(
            ALPHABET_SIZE + TERMINAL_CHAR_MAP.size(), -1, new Pointer<>(-1), null);
    buildTree();
    buildSuffixIndices();
  }

  private UkkonenSuffixTree(String... inputs) {
    if (inputs.length > TERMINAL_CHAR_MAP.size()) {
      throw new RuntimeException(
          String.format("Can only store %s strings", TERMINAL_CHAR_MAP.size()));
    }
    StringBuilder temp = new StringBuilder();
    for (int i = 0; i < inputs.length; i++) {
      temp.append(inputs[i]).append(TERMINAL_CHAR_INV_MAP.get(i));
    }
    this.combinedInput = temp.toString();
    this.inputs = Arrays.asList(inputs);
    this.root =
        new UkkonenSuffixTreeNode(
            ALPHABET_SIZE + TERMINAL_CHAR_MAP.size(), -1, new Pointer<>(-1), null);
    buildTree();
    buildSuffixIndices();
  }

  public static UkkonenSuffixTree generalizedSuffixTree(String... inputs) {
    return new UkkonenSuffixTree(inputs);
  }

  public UkkonenSuffixTreeNode getRoot() {
    return root;
  }

  /** t=O(n) */
  private void buildTree() {
    UkkonenSuffixTreeNode lastNewNode;
    ActivePoint activePoint = new ActivePoint(root, -1, 0);
    int remainingSuffixCount = 0;
    Pointer<Integer> leafEnd = new Pointer<>(-1);

    for (int currIndex = 0; currIndex < combinedInput.length(); currIndex++) {
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
          if (combinedInput.charAt(outgoingEdge.start + activePoint.activeLength)
              == combinedInput.charAt(currIndex)) {
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
          split.children[toIndex(combinedInput.charAt(currIndex))] = newNode(currIndex, leafEnd);
          outgoingEdge.start += activePoint.activeLength;
          split.children[toIndex(combinedInput.charAt(outgoingEdge.start))] = outgoingEdge;
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
  }

  private int toIndex(char ch) {
    if (TERMINAL_CHAR_MAP.containsKey(ch)) {
      return TERMINAL_CHAR_MAP.get(ch) + ALPHABET_SIZE;
    }
    return ch - 'a';
  }

  private UkkonenSuffixTreeNode getOutgoingEdge(ActivePoint activePoint) {
    return activePoint.activeNode.children[toIndex(combinedInput.charAt(activePoint.activeEdge))];
  }

  private void createOutgoingEdge(ActivePoint activePoint, int start, Pointer<Integer> end) {
    activePoint.activeNode.children[toIndex(combinedInput.charAt(activePoint.activeEdge))] =
        newNode(start, end);
  }

  private void createOutgoingEdge(ActivePoint activePoint, UkkonenSuffixTreeNode node) {
    activePoint.activeNode.children[toIndex(combinedInput.charAt(activePoint.activeEdge))] = node;
  }

  private UkkonenSuffixTreeNode newNode(int start, Pointer<Integer> end) {
    return new UkkonenSuffixTreeNode(ALPHABET_SIZE + TERMINAL_CHAR_MAP.size(), start, end, root);
  }

  /** APCFWD */
  private boolean walkDown(ActivePoint activePoint, UkkonenSuffixTreeNode node) {
    int nodeEdgeLength = getEdgeLength(node);
    if (activePoint.activeLength < nodeEdgeLength) {
      return false;
    }
    activePoint.activeEdge += nodeEdgeLength;
    activePoint.activeLength -= nodeEdgeLength;
    activePoint.activeNode = node;
    return true;
  }

  private void buildSuffixIndices() {
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
      setSuffixIndexByDfsUtil(child, labelHeight + getEdgeLength(child));
    }

    if (isLeaf) {
      node.suffixIndex = combinedInput.length() - labelHeight;
    }
  }

  /** This method is not inside UkkonenSuffixTreeNode because of special case of `root` */
  private int getEdgeLength(UkkonenSuffixTreeNode node) {
    return node != root ? node.end.data - node.start + 1 : 0;
  }

  public Integer[] toSuffixArray() {
    List<Integer> traversal = new ArrayList<>();
    toSuffixArrayUtil(root, traversal);
    return traversal.toArray(new Integer[] {});
  }

  private void toSuffixArrayUtil(UkkonenSuffixTreeNode node, List<Integer> traversal) {
    if (node == null) {
      return;
    }
    for (int i = 0; i < node.children.length; i++) {
      toSuffixArrayUtil(node.children[i], traversal);
    }
    boolean suffixIndexForTerminalChar = node.suffixIndex == combinedInput.length() - 1;
    if (node.isLeaf() && !suffixIndexForTerminalChar) {
      traversal.add(node.suffixIndex);
    }
  }

  /** t=O(M); M is pattern length */
  public boolean contains(String pattern) {
    return getNodeWherePatternEnds(root, pattern, 0).isPresent();
  }

  private Optional<UkkonenSuffixTreeNode> getNodeWherePatternEnds(
      UkkonenSuffixTreeNode node, String pattern, int index) {
    UkkonenSuffixTreeNode child = node.children[toIndex(pattern.charAt(index))];
    if (child == null) {
      return Optional.empty();
    }
    boolean match = match(child, pattern, index);
    if (!match) {
      return Optional.empty();
    }
    int charLeft = pattern.length() - index;
    if (charLeft <= getEdgeLength(child)) {
      return Optional.of(child);
    }
    return getNodeWherePatternEnds(child, pattern, index + getEdgeLength(child));
  }

  private boolean match(UkkonenSuffixTreeNode node, String pattern, int index) {
    for (int i = node.start, k = index; i <= node.end.data && k < pattern.length(); i++, k++) {
      if (combinedInput.charAt(i) != pattern.charAt(k)) {
        return false;
      }
    }
    return true;
  }

  /** t=O(M+Z); M is pattern length, Z is number of occurrences */
  public List<Integer> getAllOccurrences(String pattern) {
    Optional<UkkonenSuffixTreeNode> maybeNode = getNodeWherePatternEnds(root, pattern, 0);
    if (!maybeNode.isPresent()) {
      return Collections.emptyList();
    }

    List<Integer> indices = new ArrayList<>();
    getAllSuffixEdgesStartingWith(maybeNode.get(), indices);

    return indices;
  }

  private void getAllSuffixEdgesStartingWith(UkkonenSuffixTreeNode node, List<Integer> indices) {
    if (node.isLeaf()) {
      indices.add(node.suffixIndex);
      return;
    }
    for (int i = 0; i < node.children.length; i++) {
      UkkonenSuffixTreeNode child = node.children[i];
      if (child == null) {
        continue;
      }
      getAllSuffixEdgesStartingWith(child, indices);
    }
  }

  /**
   * t=O(n) Finding longest repeated substring boils down to finding the deepest node in suffix tree
   * and then get the path label from root to that deepest internal node
   */
  public String getLongestRepeatedSubString() {
    Pointer<Integer> maxLengthPtr = new Pointer<>(0);
    Pointer<Integer> suffixIndexPtr = new Pointer<>(-1);
    findDeepestInternalNode(root, 0, maxLengthPtr, suffixIndexPtr);

    // No internal node
    if (maxLengthPtr.data == 0) {
      return "";
    }

    return combinedInput.substring(suffixIndexPtr.data, suffixIndexPtr.data + maxLengthPtr.data);
  }

  private void findDeepestInternalNode(
      UkkonenSuffixTreeNode node,
      int pathLength,
      Pointer<Integer> maxLengthPtr,
      Pointer<Integer> suffixIndexPtr) {
    if (node.isLeaf()) {
      int lengthToLastInternalNodeInCurrPath = pathLength - getEdgeLength(node);
      if (lengthToLastInternalNodeInCurrPath > maxLengthPtr.data) {
        maxLengthPtr.data = lengthToLastInternalNodeInCurrPath;
        suffixIndexPtr.data = node.suffixIndex;
      }
      return;
    }

    for (int i = 0; i < node.children.length; i++) {
      UkkonenSuffixTreeNode child = node.children[i];
      if (child == null) {
        continue;
      }
      findDeepestInternalNode(
          child, pathLength + getEdgeLength(child), maxLengthPtr, suffixIndexPtr);
    }
  }

  public String getLongestPalindromicSubString() {
    assert inputs.size() == 2;
    assert inputs.get(0).equals(new StringBuilder(inputs.get(1)).reverse().toString());

    Map<UkkonenSuffixTreeNode, Set<Integer>> forwardIndices = new HashMap<>();
    Map<UkkonenSuffixTreeNode, Set<Integer>> reverseIndices = new HashMap<>();
    updateForwardAndReverseSuffixes(root, forwardIndices, reverseIndices);

    Pointer<Integer> maxLengthPtr = new Pointer<>(-1);
    Pointer<Integer> longestPalindromeStartIndexPtr = new Pointer<>(-1);
    getLongestPalindromeSubStringUtil(
        root, 0, maxLengthPtr, longestPalindromeStartIndexPtr, forwardIndices, reverseIndices);

    if (maxLengthPtr.data == 0) {
      return "";
    }
    return combinedInput.substring(
        longestPalindromeStartIndexPtr.data,
        longestPalindromeStartIndexPtr.data + maxLengthPtr.data);
  }

  private void updateForwardAndReverseSuffixes(
      UkkonenSuffixTreeNode node,
      Map<UkkonenSuffixTreeNode, Set<Integer>> forwardIndices,
      Map<UkkonenSuffixTreeNode, Set<Integer>> reverseIndices) {
    forwardIndices.put(node, new HashSet<>());
    reverseIndices.put(node, new HashSet<>());

    if (node.isLeaf()) {
      int size1 = inputs.get(0).length() + 1; // Including terminal character
      if (node.suffixIndex < size1) {
        forwardIndices.get(node).add(node.suffixIndex);
      } else {
        reverseIndices.get(node).add(node.suffixIndex - size1);
      }
      return;
    }
    for (int i = 0; i < node.children.length; i++) {
      UkkonenSuffixTreeNode child = node.children[i];
      if (child == null) {
        continue;
      }
      updateForwardAndReverseSuffixes(child, forwardIndices, reverseIndices);
      forwardIndices.get(node).addAll(forwardIndices.get(child));
      reverseIndices.get(node).addAll(reverseIndices.get(child));
    }
  }

  private void getLongestPalindromeSubStringUtil(
      UkkonenSuffixTreeNode node,
      int pathLength,
      Pointer<Integer> maxLengthPtr,
      Pointer<Integer> longestPalindromeStartIndex,
      Map<UkkonenSuffixTreeNode, Set<Integer>> forwardIndices,
      Map<UkkonenSuffixTreeNode, Set<Integer>> reverseIndices) {
    if (node.isLeaf()) {
      return;
    }

    for (int i = 0; i < node.children.length; i++) {
      UkkonenSuffixTreeNode child = node.children[i];
      if (child == null) {
        return;
      }

      getLongestPalindromeSubStringUtil(
          child,
          pathLength + getEdgeLength(child),
          maxLengthPtr,
          longestPalindromeStartIndex,
          forwardIndices,
          reverseIndices);

      if (pathLength < maxLengthPtr.data
          || forwardIndices.get(node).size() < 0
          || reverseIndices.get(node).size() < 0) {
        continue;
      }

      int size1 = inputs.get(0).length() + 1; // Including terminal character
      for (int forwardIndex : forwardIndices.get(node)) {
        // Extra -1 for removing terminal character
        int desiredReverseIndex = (size1 - 1 - 1) - (forwardIndex + pathLength - 1);
        if (reverseIndices.get(node).contains(desiredReverseIndex)) {
          maxLengthPtr.data = pathLength;
          // We can also get this information by suffix index of any leaf after this internal node.
          longestPalindromeStartIndex.data = node.end.data - pathLength + 1;
        }
      }
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

    private boolean isLeaf() {
      return suffixIndex != -1;
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
