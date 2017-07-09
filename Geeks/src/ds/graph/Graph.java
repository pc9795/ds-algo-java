package ds.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Graph {

	List<Integer> vertices[];
	int size;

	@SuppressWarnings("unchecked")
	public Graph(int size) {
		this.size = size;
		vertices = new LinkedList[50];
		for (int i = 0; i < size; i++) {
			vertices[i] = new LinkedList<>();
		}
	}

	public void topologicalSort(int index, boolean[] visited,
			Stack<Integer> stack) {
		visited[index] = true;
		List<Integer> neighbours = vertices[index];
		for (int i = 0; i < neighbours.size(); i++) {
			if (!visited[neighbours.get(i)]) {
				topologicalSort(neighbours.get(i), visited, stack);
			}
		}
		stack.push(index);
	}

	public int parent(int parent[], int index) {
		if (parent[index] == -1) {
			return index;
		}
		return parent(parent, parent[index]);
	}

	public void union(int x, int y, int[] parent) {
		int px = parent(parent, x);
		int py = parent(parent, y);
		parent[px] = py;
	}

	public boolean isCyclicUndirected2(boolean[] visited, int index, int parent) {
		// System.out.println("vertex:" + index + ",parent:" + parent);
		visited[index] = true;
		List<Integer> neighbours = vertices[index];
		for (int i = 0; i < neighbours.size(); i++) {
			if (visited[neighbours.get(i)] && neighbours.get(i) != parent) {
				// System.out.println("neighbor:" + neighbours.get(i));
				// System.out.println("Cycle Found");
				return true;
			} else if (!visited[neighbours.get(i)]) {
				// System.out.println("neighbor:" + neighbours.get(i));
				if (isCyclicUndirected2(visited, neighbours.get(i), index)) {
					return true;
				}
			}
		}
		return false;
	}

	// union-find algorithm
	// 0-1 is taken one time not 1-0 event though it is undirected.
	public boolean isCyclicUndirected() {
		int parent[] = new int[size()];
		for (int i = 0; i < parent.length; parent[i] = -1, i++)
			;
		for (int i = 0; i < size(); i++) {
			for (int j = 0; j < vertices[i].size(); j++) {
				// System.out.println("Edge:" + i + "," + vertices[i].get(j));
				// for (int k = 0; k < parent.length; k++) {
				// System.out.print(parent[k] + ":");
				// }
				// System.out.println();
				int parentx = parent(parent, i);
				int parenty = parent(parent, vertices[i].get(j));
				if (parentx == parenty) {
					return true;
				}
				union(i, vertices[i].get(j), parent);
			}
		}
		return false;
	}

	public boolean isCyclicDirected(int index, boolean[] visited,
			boolean[] recStack) {
		visited[index] = true;
		recStack[index] = true;
		List<Integer> neighbours = vertices[index];
		for (int i = 0; i < neighbours.size(); i++) {
			if (recStack[neighbours.get(i)]) {
				return true;
			} else if (!visited[neighbours.get(i)]
					&& isCyclicDirected(neighbours.get(i), visited, recStack)) {
				return true;
			}

		}
		recStack[index] = false;
		return false;
	}

	public void show() {
		for (int i = 0; i < this.size; i++) {
			System.out.print(i + ":");
			for (int j = 0; j < vertices[i].size(); j++) {
				System.out.print(vertices[i].get(j) + "-");
			}
			System.out.println();
		}
	}

	public void dfs(int index, boolean[] visited) {
		visited[index] = true;
		System.out.println(index);
		List<Integer> neighbours = vertices[index];
		for (int i = 0; i < neighbours.size(); i++) {
			if (!visited[neighbours.get(i)]) {
				dfs(neighbours.get(i), visited);
			}
		}
	}

	public void bfs(int index, boolean[] visited) {

		Queue<Integer> queue = new LinkedList<>();
		queue.add(index);
		while (!queue.isEmpty()) {
			// System.out.println(queue);
			int vertex = queue.poll();
			if (!visited[vertex]) {
				System.out.println(vertex);
				visited[vertex] = true;
				List<Integer> neighbours = vertices[vertex];
				for (int i = 0; i < neighbours.size(); i++) {
					if (!visited[neighbours.get(i)]) {
						queue.add(neighbours.get(i));
					}
				}
			}
		}
	}

	public int size() {
		return this.size;
	}

	public void addEdge(int src, int dest) {
		vertices[src].add(dest);
	}

	public void addUEdge(int src, int dest) {
		vertices[src].add(dest);
		vertices[dest].add(src);
	}
}
