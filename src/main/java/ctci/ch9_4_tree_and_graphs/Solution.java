package ctci.ch9_4_tree_and_graphs;

import gfg.ds.tree.binary_search_tree.BinarySearchTree;
import gfg.ds.tree.binary_tree.BinaryTree;
import utils.Pointer;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 08-09-2019 14:58
 **/
public class Solution {

    /**
     * t=O(n)
     *
     * @param arr input array
     * @return minimal BST
     */
    public static BinarySearchTree minimalTree(int[] arr) {
        return BinarySearchTree.fromBinaryTree(new BinaryTree().insertAtRoot(minimalTreeUtil(arr, 0, arr.length - 1)));
    }

    private static BinaryTree.BinaryTreeNode minimalTreeUtil(int[] arr, int begin, int end) {
        if (begin > end) {
            return null;
        }
        if (begin == end) {
            return new BinaryTree.BinaryTreeNode(arr[begin]);
        }
        int mid = (begin + end) >> 1;
        BinaryTree.BinaryTreeNode root = new BinaryTree.BinaryTreeNode(arr[mid]);
        root.left = minimalTreeUtil(arr, begin, mid - 1);
        root.right = minimalTreeUtil(arr, mid + 1, end);
        return root;
    }

    /**
     * t=O(N)
     * BFS
     *
     * @param bt binary tree
     * @return list of depths
     */
    public static ArrayList<LinkedList<BinaryTree.BinaryTreeNode>> listOfDepths(BinaryTree bt) {

        class BTNodeInfo {
            BinaryTree.BinaryTreeNode node;
            int level;

            public BTNodeInfo(BinaryTree.BinaryTreeNode node, int level) {
                this.node = node;
                this.level = level;
            }
        }
        ArrayList<LinkedList<BinaryTree.BinaryTreeNode>> listOfLists = new ArrayList<>();
        ArrayDeque<BTNodeInfo> queue = new ArrayDeque<>();
        queue.add(new BTNodeInfo(bt.root, 0));
        while (!queue.isEmpty()) {
            BTNodeInfo info = queue.poll();
            if (listOfLists.size() == info.level) {
                listOfLists.add(new LinkedList<>());
            }
            listOfLists.get(info.level).add(info.node);
            if (info.node.left != null) {
                queue.add(new BTNodeInfo(info.node.left, info.level + 1));
            }
            if (info.node.right != null) {
                queue.add(new BTNodeInfo(info.node.right, info.level + 1));
            }
        }
        return listOfLists;
    }

    //T=O(N)
    public static boolean checkBalanced(BinaryTree bt) {
        Pointer<Boolean> ans = new Pointer<>();
        ans.data = true;
        checkBalancedUtil(bt.root, ans);
        return ans.data;
    }

    static int checkBalancedUtil(BinaryTree.BinaryTreeNode root, Pointer<Boolean> ans) {
        if (root == null) {
            return 0;
        }
        int leftHeight = checkBalancedUtil(root.left, ans);
        int rightHeight = checkBalancedUtil(root.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans.data = false;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    //T=O(depth of deepest node)
    public static BinaryTree.BinaryTreeNode firstCommonAncestor(BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second) {
        int dist1 = distanceFromRoot(first);
        int dist2 = distanceFromRoot(second);
        if (dist1 > dist2) {
            int diff = dist1 - dist2;
            while (diff-- > 0) {
                first = first.parent;
            }
        } else if (dist2 > dist1) {
            int diff = dist2 - dist1;
            while (diff-- > 0) {
                second = second.parent;
            }
        }
        BinaryTree.BinaryTreeNode parent1 = first;
        BinaryTree.BinaryTreeNode parent2 = second;
        while (parent1 != parent2) {
            parent1 = parent1.parent;
            parent2 = parent2.parent;
        }
        return parent1;
    }

    private static int distanceFromRoot(BinaryTree.BinaryTreeNode node) {
        int dist = 0;
        while (node != null) {
            node = node.parent;
            dist++;
        }
        return dist;
    }

    // If we are not traversing the whole tree then T=O(t) size of the subtree of the first common ancestor.
    public static BinaryTree.BinaryTreeNode firstCommonAncestor2(BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second) {
        //Check the nodes are in the tree
        //Null checks
        if (covers(first, second)) {
            return first;
        }
        if (covers(second, first)) {
            return second;
        }
        BinaryTree.BinaryTreeNode sibling = getSibling(first);
        BinaryTree.BinaryTreeNode parent = first.parent;
        while (!covers(sibling, second)) {
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }

    static BinaryTree.BinaryTreeNode getSibling(BinaryTree.BinaryTreeNode node) {
        if (node == null || node.parent == null) {
            return null;
        }
        return node.parent.left == node ? node.parent.right : node.parent.left;
    }

    static boolean covers(BinaryTree.BinaryTreeNode coveringNode, BinaryTree.BinaryTreeNode coveredNode) {
        if (coveringNode == null) {
            return false;
        }
        if (coveringNode.equals(coveredNode)) {
            return true;
        }
        return covers(coveringNode.left, coveredNode) || covers(coveringNode.right, coveredNode);
    }

    //T=O(N); N + N/2 + N/4 ...
    public static BinaryTree.BinaryTreeNode firstCommonAncestorWithoutParentLinks(BinaryTree.BinaryTreeNode root, BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second) {
        //Check the nodes in there or not
        //Null checks
        return firstCommonAncestorWithoutParentLinksUtil(root, first, second);
    }

    static BinaryTree.BinaryTreeNode firstCommonAncestorWithoutParentLinksUtil(BinaryTree.BinaryTreeNode root, BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second) {
        if (root == null) {
            return null;
        }
        boolean inLeft = covers(root.left, first);
        boolean inRight = covers(root.right, second);
        if (inLeft == inRight) {
            return root;
        }
        return firstCommonAncestorWithoutParentLinksUtil(inLeft ? root.left : root.right, first, second);
    }

    static class FirstCommonAncestorResult {
        BinaryTree.BinaryTreeNode node;
        boolean found;

        public FirstCommonAncestorResult(BinaryTree.BinaryTreeNode node, boolean found) {
            this.node = node;
            this.found = found;
        }
    }

    public static BinaryTree.BinaryTreeNode firstCommonAncestorWithoutParentLinks2(BinaryTree.BinaryTreeNode root, BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second) {
        //Check the nodes in there are not
        // Null checks
        FirstCommonAncestorResult result = firstCommonAncestorWithoutParentLinks2Util(root, first, second);
        return result.found ? result.node : null;
    }

    static FirstCommonAncestorResult firstCommonAncestorWithoutParentLinks2Util(BinaryTree.BinaryTreeNode root, BinaryTree.BinaryTreeNode first,
                                                                                BinaryTree.BinaryTreeNode second) {
        if (root == null) {
            return new FirstCommonAncestorResult(null, false);
        }
        FirstCommonAncestorResult left = firstCommonAncestorWithoutParentLinks2Util(root.left, first, second);
        FirstCommonAncestorResult right = firstCommonAncestorWithoutParentLinks2Util(root.right, first, second);
        //Found result already;
        if (left.found) {
            return left;
        }
        //Found result already;
        if (right.found) {
            return right;
        }
        if (left.node != null && right.node != null) {
            return new FirstCommonAncestorResult(root, true);
        }
        if (root == first || root == second) {
            //We need the FirstCommonAncestorResult because if we simply return the root then we can't identify between
            //the case where one node is not present and when one is inside subtree of another.
            boolean found = left.node != null || right.node != null;
            return new FirstCommonAncestorResult(root, found);
        }
        return left.node != null ? left : right;
    }

    public static ArrayList<LinkedList<Integer>> BSTSequences(BinarySearchTree bst) {
        //Null checks
        return BSTSequencesUtil(bst.root);
    }

    private static ArrayList<LinkedList<Integer>> BSTSequencesUtil(BinaryTree.BinaryTreeNode root) {
        ArrayList<LinkedList<Integer>> ans = new ArrayList<>();
        if (root == null) {
            //An empty one is added so that for leaf nodes the weaveLists will return a result with them else it will
            //return empty; We can try running without it.
            ans.add(new LinkedList<>());
            return ans;
        }
        ArrayList<LinkedList<Integer>> left = BSTSequencesUtil(root.left);
        ArrayList<LinkedList<Integer>> right = BSTSequencesUtil(root.right);
        for (LinkedList<Integer> leftList : left) {
            for (LinkedList<Integer> rightList : right) {
                ArrayList<LinkedList<Integer>> result = new ArrayList<>();
                weaveLists(leftList, rightList, result, new LinkedList<>(Arrays.asList(root.data)));
                ans.addAll(result);
            }
        }
        return ans;
    }

    public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> result,
                                  LinkedList<Integer> prefix) {
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> list = new LinkedList<>(prefix);
            list.addAll(first);
            list.addAll(second);
            result.add(list);
            return;
        }
        Integer head = first.removeFirst();
        prefix.add(head);
        weaveLists(first, second, result, prefix);
        prefix.removeLast();
        first.addLast(head);

        head = second.removeFirst();
        prefix.add(head);
        weaveLists(first, second, result, prefix);
        prefix.removeLast();
        second.addLast(head);
    }

    public static boolean isSubtree(BinaryTree bt1, BinaryTree bt2) {
        //Null checks
        //Null tree is always a subtree
        return bt2 == null || isSubtreeUtil(bt1.root, bt2.root);
    }

    private static boolean isSubtreeUtil(BinaryTree.BinaryTreeNode node1, BinaryTree.BinaryTreeNode node2) {
        if (node1 == null) {
            return false;
        }
        if (node1.data == node2.data && matchTree(node1, node2)) {
            return true;
        }
        return isSubtreeUtil(node1.left, node2) || isSubtreeUtil(node1.right, node2);
    }

    private static boolean matchTree(BinaryTree.BinaryTreeNode node1, BinaryTree.BinaryTreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        if (node1.data != node2.data) {
            return false;
        }
        return matchTree(node1.left, node2.left) && matchTree(node1.right, node2.right);
    }

    static class BinaryTreeWithGetRandom {
        BinarySearchTree bst;

        boolean find(int key) {
            return bst.search(key);
        }

        void insert(int key) {
            bst.insert(key);
        }

        void delete(int key) {
            bst.delete(key);
        }

        BinaryTree.BinaryTreeNode getRandomNode() {
            //Null checks
            //In place of this method calls we can put the size fields inside the node object as a field.
            int index = new Random().nextInt(bst.sizeUtil(bst.root));
            return getRandomNodeUtil(this.bst.root, index);
        }

        BinaryTree.BinaryTreeNode getRandomNodeUtil(BinaryTree.BinaryTreeNode node, int index) {
            int leftSize = node.left == null ? 0 : bst.sizeUtil(node.left);
            if (leftSize == index) {
                return node;
            }
            if (leftSize < index) {
                return getRandomNodeUtil(node.left, index);
            }
            return getRandomNodeUtil(node.right, index - (leftSize + 1));
        }
    }

    public static int pathsWithSum(BinaryTree bt, int targetSum) {
        //Null checks
        return pathsWithSumUtil(bt.root, targetSum);
    }

    //T=O(N*logN)(Best Case); Node at depth d is touched by nodes above it and for balanced tree the depth will be logN.
    //T=O(N*N)(Worst Case); Skewed tree
    static int pathsWithSumUtil(BinaryTree.BinaryTreeNode node, int targetSum) {
        if (node == null) {
            return 0;
        }
        int pathsFromNode = pathsWithSumFromNode(node, targetSum, 0);
        pathsFromNode += pathsWithSumUtil(node.left, targetSum);
        pathsFromNode += pathsWithSumUtil(node.right, targetSum);
        return pathsFromNode;
    }

    static int pathsWithSumFromNode(BinaryTree.BinaryTreeNode node, int targetSum, int runningSum) {
        if (node == null) {
            return 0;
        }
        runningSum += node.data;
        int totalPaths = 0;
        if (runningSum == targetSum) {
            totalPaths++;
        }
        totalPaths += pathsWithSumFromNode(node.left, targetSum, runningSum);
        totalPaths += pathsWithSumFromNode(node.right, targetSum, runningSum);
        return totalPaths;
    }

    public static int pathsWithSum2(BinaryTree bt, int targetSum) {
        //Null checks
        return pathsWithSum2Util(bt.root, targetSum, 0, new HashMap<>());
    }

    private static int pathsWithSum2Util(BinaryTree.BinaryTreeNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
        if (node == null) {
            return 0;
        }
        runningSum += node.data;
        int totalPaths = pathCount.getOrDefault(runningSum - targetSum, 0);
        totalPaths += runningSum == targetSum ? 1 : 0;
        pathCount.put(runningSum, pathCount.getOrDefault(runningSum, 0) + 1);
        totalPaths += pathsWithSum2Util(node.left, targetSum, runningSum, pathCount);
        totalPaths += pathsWithSum2Util(node.right, targetSum, runningSum, pathCount);
        pathCount.put(runningSum, pathCount.get(runningSum) - 1);
        if (pathCount.get(runningSum) == 0) {
            pathCount.remove(runningSum);
        }
        return totalPaths;
    }
}
