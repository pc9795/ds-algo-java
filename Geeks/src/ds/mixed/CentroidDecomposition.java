package ds.mixed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CentroidDecomposition {

	List<Integer> tree[];
	List<Integer> centroidTree[];
	boolean centroidMarked[];
	int size;

	@SuppressWarnings("unchecked")
	public CentroidDecomposition(int size) {

		tree = new ArrayList[size];
		centroidTree = new ArrayList[size];
		centroidMarked = new boolean[size];
		for (int i = 0; i < size; i++) {
			tree[i] = new ArrayList<>();
			centroidTree[i] = new ArrayList<>();
		}
		this.size = size;
	}

	public static void main(String[] args) {

		CentroidDecomposition tree = new CentroidDecomposition(17);
		tree.addEdgeU(1, 4);
		tree.addEdgeU(2, 4);
		tree.addEdgeU(3, 4);
		tree.addEdgeU(4, 5);
		tree.addEdgeU(5, 6);
		tree.addEdgeU(6, 7);
		tree.addEdgeU(7, 8);
		tree.addEdgeU(7, 9);
		tree.addEdgeU(6, 10);
		tree.addEdgeU(10, 11);
		tree.addEdgeU(11, 12);
		tree.addEdgeU(11, 13);
		tree.addEdgeU(12, 14);
		tree.addEdgeU(13, 15);
		tree.addEdgeU(13, 16);
		tree.printTree();
		tree.decomposeTree(1);
		tree.printCentroidTree();
		System.out.println(Arrays.toString(tree.centroidMarked));

	}

	public void DFS(int src, boolean[] visited, int[] subTreeSize) {
		visited[src] = true;
		subTreeSize[src] = 1;
		List<Integer> children = tree[src];
		for (Integer child : children) {
			if (!visited[child] && !centroidMarked[child]) {
				DFS(child, visited, subTreeSize);
				subTreeSize[src] += subTreeSize[child];
			}
		}
	}

	public int getCentroid(int root, boolean[] visited, int[] subTreeSize, int n) {
		boolean isCentroid = true;
		visited[root] = true;
		int heaviestChild = 0;
		List<Integer> children = tree[root];
		// System.out.println("for root:" + root);
		for (Integer child : children) {
			if (!visited[child] && !centroidMarked[child]) {
				if (subTreeSize[child] > n / 2) {
					isCentroid = false;
				}
				if (heaviestChild == 0 || subTreeSize[child] > subTreeSize[heaviestChild]) {
					heaviestChild = child;
				}
				// System.out.println(heaviestChil d);
			}
		}
		if (isCentroid && n - subTreeSize[root] <= n / 2) {
			return root;
		}
		return getCentroid(heaviestChild, visited, subTreeSize, n);
	}

	public int getCentroid(int root) {
		boolean[] visited = new boolean[size];
		int[] subTreeSize = new int[size];
		DFS(root, visited, subTreeSize);
		for (int i = 0; i < visited.length; i++) {
			visited[i] = false;
		}
		System.out.println(Arrays.toString(subTreeSize));
		int centroid = getCentroid(root, visited, subTreeSize, subTreeSize[root]);
		centroidMarked[centroid] = true;
		return centroid;
	}

	public int decomposeTree(int root) {
		int centTree = getCentroid(root);
		System.out.println("root:" + centTree);
		System.out.println();
		List<Integer> children = tree[centTree];
		for (Integer child : children) {
			if (!centroidMarked[child]) {
				int centroid = decomposeTree(child);
				centroidTree[centTree].add(centroid);
				centroidTree[centroid].add(centTree);
			}
		}
		return centTree;
	}

	public void addEdgeU(int src, int dest) {
		tree[src].add(dest);
		tree[dest].add(src);
	}

	public void printTree() {
		for (int i = 0; i < tree.length; i++) {
			System.out.print(i + "->");
			for (Integer v : tree[i]) {
				System.out.print(v + ":");
			}
			System.out.println();
		}
		System.out.println("_____");
	}

	public void printCentroidTree() {
		for (int i = 0; i < centroidTree.length; i++) {
			System.out.print(i + "->");
			for (Integer v : centroidTree[i]) {
				System.out.print(v + ":");
			}
			System.out.println();
		}
		System.out.println("_____");
	}

}
