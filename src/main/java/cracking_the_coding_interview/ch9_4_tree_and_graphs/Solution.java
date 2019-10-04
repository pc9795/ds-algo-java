package cracking_the_coding_interview.ch9_4_tree_and_graphs;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import util.DoublePointer;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 08-09-2019 14:58
 * Purpose: TODO:
 **/
public class Solution {

    //T=O(N)
    static BinarySearchTree minimalTree(int[] arr) {
        return new BinarySearchTree(minimalTreeUtil(arr, 0, arr.length - 1));
    }

    static BTNode minimalTreeUtil(int[] arr, int begin, int end) {
        if (begin > end) {
            return null;
        }
        if (begin == end) {
            return new BTNode(arr[begin]);
        }
        int mid = (begin + end) >> 1;
        BTNode root = new BTNode(arr[mid]);
        root.left = minimalTreeUtil(arr, begin, mid - 1);
        root.right = minimalTreeUtil(arr, mid + 1, end);
        return root;
    }

    //T=O(N); BFS
    static ArrayList<LinkedList<BTNode>> listOfDepths(BinaryTree bt) {

        class BTNodeInfo {
            BTNode node;
            int level;

            public BTNodeInfo(BTNode node, int level) {
                this.node = node;
                this.level = level;
            }
        }
        ArrayList<LinkedList<BTNode>> listOfLists = new ArrayList<>();
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
    static boolean checkBalanced(BinaryTree bt) {
        DoublePointer<Boolean> ans = new DoublePointer<>();
        ans.data = true;
        checkBalancedUtil(bt.root, ans);
        return ans.data;
    }

    static int checkBalancedUtil(BTNode root, DoublePointer<Boolean> ans) {
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
    static BTNode firstCommonAncestor(BTNode first, BTNode second) {
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
        BTNode parent1 = first;
        BTNode parent2 = second;
        while (parent1 != parent2) {
            parent1 = parent1.parent;
            parent2 = parent2.parent;
        }
        return parent1;
    }

    static int distanceFromRoot(BTNode node) {
        int dist = 0;
        while (node != null) {
            node = node.parent;
            dist++;
        }
        return dist;
    }

    // If we are not traversing the whole tree then T=O(t) size of the subtree of the first common ancestor.
    static BTNode firstCommonAncestor2(BTNode first, BTNode second) {
        //Check the nodes are in the tree
        //Null checks
        if (covers(first, second)) {
            return first;
        }
        if (covers(second, first)) {
            return second;
        }
        BTNode sibling = getSibling(first);
        BTNode parent = first.parent;
        while (!covers(sibling, second)) {
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }

    static BTNode getSibling(BTNode node) {
        if (node == null || node.parent == null) {
            return null;
        }
        return node.parent.left == node ? node.parent.right : node.parent.left;
    }

    static boolean covers(BTNode coveringNode, BTNode coveredNode) {
        if (coveringNode == null) {
            return false;
        }
        if (coveringNode.equals(coveredNode)) {
            return true;
        }
        return covers(coveringNode.left, coveredNode) || covers(coveringNode.right, coveredNode);
    }

    //T=O(N); N + N/2 + N/4 ...
    static BTNode firstCommonAncestorWithoutParentLinks(BTNode root, BTNode first, BTNode second) {
        //Check the nodes in there or not
        //Null checks
        return firstCommonAncestorWithoutParentLinksUtil(root, first, second);
    }

    static BTNode firstCommonAncestorWithoutParentLinksUtil(BTNode root, BTNode first, BTNode second) {
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
        BTNode node;
        boolean found;

        public FirstCommonAncestorResult(BTNode node, boolean found) {
            this.node = node;
            this.found = found;
        }
    }

    static BTNode firstCommonAncestorWithoutParentLinks2(BTNode root, BTNode first, BTNode second) {
        //Check the nodes in there are not
        // Null checks
        FirstCommonAncestorResult result = firstCommonAncestorWithoutParentLinks2Util(root, first, second);
        return result.found ? result.node : null;
    }

    static FirstCommonAncestorResult firstCommonAncestorWithoutParentLinks2Util(BTNode root, BTNode first,
                                                                                BTNode second) {
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

    static ArrayList<LinkedList<Integer>> BSTSequences(BinarySearchTree bst) {
        //Null checks
        return BSTSequencesUtil(bst.root);
    }

    static ArrayList<LinkedList<Integer>> BSTSequencesUtil(BTNode root) {
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

    static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> result,
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

    static boolean isSubtree(BinaryTree bt1, BinaryTree bt2) {
        //Null checks
        //Null tree is always a subtree
        return bt2 == null || isSubtreeUtil(bt1.root, bt2.root);
    }

    static boolean isSubtreeUtil(BTNode node1, BTNode node2) {
        if (node1 == null) {
            return false;
        }
        if (node1.data == node2.data && matchTree(node1, node2)) {
            return true;
        }
        return isSubtreeUtil(node1.left, node2) || isSubtreeUtil(node1.right, node2);
    }

    static boolean matchTree(BTNode node1, BTNode node2) {
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

        public BinaryTreeWithGetRandom(int data) {
            this.bst = new BinarySearchTree(data);
        }

        boolean find(int key) {
            return bst.search(key);
        }

        void insert(int key) {
            bst.insert(key);
        }

        void delete(int key) {
            bst.delete(key);
        }

        BTNode getRandomNode() {
            //Null checks
            //In place of this method calls we can put the size fields inside the node object as a field.
            int index = new Random().nextInt(bst.sizeUtil(bst.root));
            return getRandomNodeUtil(this.bst.root, index);
        }

        BTNode getRandomNodeUtil(BTNode node, int index) {
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

    static int pathsWithSum(BinaryTree bt, int targetSum) {
        //Null checks
        return pathsWithSumUtil(bt.root, targetSum);
    }

    //T=O(N*logN)(Best Case); Node at depth d is touched by nodes above it and for balanced tree the depth will be logN.
    //T=O(N*N)(Worst Case); Skewed tree
    static int pathsWithSumUtil(BTNode node, int targetSum) {
        if (node == null) {
            return 0;
        }
        int pathsFromNode = pathsWithSumFromNode(node, targetSum, 0);
        pathsFromNode += pathsWithSumUtil(node.left, targetSum);
        pathsFromNode += pathsWithSumUtil(node.right, targetSum);
        return pathsFromNode;
    }

    static int pathsWithSumFromNode(BTNode node, int targetSum, int runningSum) {
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

    static int pathsWithSum2(BinaryTree bt, int targetSum) {
        //Null checks
        return pathsWithSum2Util(bt.root, targetSum, 0, new HashMap<>());
    }

    static int pathsWithSum2Util(BTNode node, int targetSum, int runningSum, HashMap<Integer, Integer> pathCount) {
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
