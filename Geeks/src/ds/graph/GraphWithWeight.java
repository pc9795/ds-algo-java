package ds.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class GraphWithWeight {

	int size;
	List<Node> vertices[];

	@SuppressWarnings("unchecked")
	public GraphWithWeight(int size) {
		vertices = new LinkedList[50];
		this.size = size;
		for (int i = 0; i < size; i++) {
			vertices[i] = new LinkedList<>();
		}
	}

	public void show() {
		for (int i = 0; i < this.size; i++) {
			System.out.print(i + ":");
			for (int j = 0; j < vertices[i].size(); j++) {
				System.out.print(vertices[i].get(j).vertex + "-");
			}
			System.out.println();
		}
	}

	public boolean isBipartite(int src) {
		int color[] = new int[size];
		for (int i = 0; i < size; i++) {
			color[i] = -1;
		}
		color[src] = 1;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		for (; !queue.isEmpty();) {
			int index = queue.poll();
			List<Node> neighbours = vertices[index];
			for (int i = 0; i < neighbours.size(); i++) {
				if (color[neighbours.get(i).vertex] == -1) {
					queue.add(neighbours.get(i).vertex);
					color[neighbours.get(i).vertex] = 1 - color[index];
				} else {
					if (color[neighbours.get(i).vertex] == color[index]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public void printArray(Object[] arr) {
		System.out.print("Array: ");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "|");
		}
		System.out.println();
	}

	public void longestPath(int src) {
		boolean visited[] = new boolean[size];
		Integer distance[] = new Integer[size];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < distance.length; i++) {
			if (i == src) {
				distance[i] = 0;
			} else {
				distance[i] = Integer.MIN_VALUE;
			}
		}
		// printArray(distance);
		for (int i = 0; i < size; i++) {
			if (!visited[i]) {
				topologicalSort(i, visited, stack);
			}
		}
		for (; !stack.isEmpty();) {
			int index = stack.pop();
			if (distance[index] != Integer.MIN_VALUE) {
				// System.out.println("index:" + index);
				List<Node> neighbours = vertices[index];
				for (int i = 0; i < neighbours.size(); i++) {
					int dist = distance[index] + neighbours.get(i).wieght;
					if (dist > distance[neighbours.get(i).vertex]) {
						distance[neighbours.get(i).vertex] = dist;
					}
				}
			}
		}
		printArray(distance);
	}

	public void topologicalSort(int index, boolean visited[],
			Stack<Integer> stack) {
		visited[index] = true;
		List<Node> neighbours = vertices[index];
		for (int i = 0; i < neighbours.size(); i++) {
			if (!visited[neighbours.get(i).vertex]) {
				topologicalSort(neighbours.get(i).vertex, visited, stack);
			}
		}
		stack.push(index);
	}

	public void addEdge(int src, int dest) {
		vertices[src].add(new Node(dest));
	}

	public void addEdge(int src, int dest, int weight) {
		vertices[src].add(new Node(dest, weight));
	}

	public void addEdgeU(int src, int dest) {
		vertices[src].add(new Node(dest));
		vertices[dest].add(new Node(src));
	}

	public void addEdgeU(int src, int dest, int weight) {
		vertices[src].add(new Node(dest, weight));
		vertices[dest].add(new Node(src, weight));
	}

	public class Node {
		int vertex;
		int wieght;

		public Node(int vertex, int wieght) {
			this.vertex = vertex;
			this.wieght = wieght;
		}

		public Node(int vertex) {
			this.vertex = vertex;
		}
	}
}
