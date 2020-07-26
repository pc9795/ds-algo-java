package gfg.ds.tree;

import gfg.ds.graph.adj_list.GraphBase;
import gfg.ds.graph.adj_list.UndirectedGraph;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 23-07-2020 01:09
 **/
public class Applications {

    /**
     * Can't use NryTree as while creating centroid tree sometimes the parents become children and using a graph instead
     * of updating this relationship is easy.
     */
    public static int centroidDecomposition(UndirectedGraph nryTree) {
        assert nryTree != null && !nryTree.isEmpty();
        assert !nryTree.isCyclic() : "Centroid decomposition happens on a NryTree or acyclic Undirected graph";

        Map<Integer, Integer> countMap = new HashMap<>();
        int totalCount = getCount(nryTree, 0, countMap, new HashSet<>());

        return createCentroidTreeUtil(nryTree, 0, countMap, totalCount, new HashSet<>());
    }

    /**
     * t=O(n*log n)
     */
    private static int createCentroidTreeUtil(UndirectedGraph nryTree, int vertex, Map<Integer, Integer> countMap, int totalCount, Set<Integer> centroids) {
        Optional<Integer> maybeCentroid = getCentroid(nryTree, vertex, countMap, totalCount, new HashSet<>(centroids));
        if (!maybeCentroid.isPresent()) {
            throw new RuntimeException(String.format("Centroid found for the tree rooted at %s", vertex));
        }

        int centroid = maybeCentroid.get();
        centroids.add(centroid);

        //Creating new list to avoid concurrent modification exception
        List<GraphBase.GraphNode> neighbours = new ArrayList<>(nryTree.getNeighbours(centroid));
        if (neighbours.isEmpty()) {
            return maybeCentroid.get();
        }

        List<Integer> neighborCentroids = new ArrayList<>();
        for (GraphBase.GraphNode neighbor : neighbours) {
            if (centroids.contains(neighbor.vertex)) {
                continue;
            }
            Map<Integer, Integer> neighbourRootedTreeCountMap = new HashMap<>();
            int neighbourRootedTreeTotalCount = getCount(nryTree, neighbor.vertex, neighbourRootedTreeCountMap, new HashSet<>(centroids));
            neighborCentroids.add(createCentroidTreeUtil(nryTree, neighbor.vertex, neighbourRootedTreeCountMap, neighbourRootedTreeTotalCount, centroids));
        }

        //Replace children links with the centroids of trees rooted by the children.
        for (GraphBase.GraphNode neighbour : neighbours) {
            nryTree.removeEdge(centroid, neighbour.vertex);
        }
        for (int neighbourCentroid : neighborCentroids) {
            nryTree.addEdge(centroid, neighbourCentroid);
        }

        return centroid;
    }

    /**
     * t=O(n)
     */
    private static Optional<Integer> getCentroid(UndirectedGraph nryTree, int vertex, Map<Integer, Integer> countMap, int totalCount, Set<Integer> visitedOrCentroids) {
        visitedOrCentroids.add(vertex);

        List<GraphBase.GraphNode> neighbours = nryTree.getNeighbours(vertex);
        int heaviestNeighbourVertex = -1;
        boolean childWithMoreThanHalfNodes = false;
        for (GraphBase.GraphNode neighbour : neighbours) {
            if (visitedOrCentroids.contains(neighbour.vertex)) {
                continue;
            }
            if (countMap.get(neighbour.vertex) > totalCount / 2) {
                childWithMoreThanHalfNodes = true;
            }
            if (heaviestNeighbourVertex == -1 || countMap.get(neighbour.vertex) > countMap.get(heaviestNeighbourVertex)) {
                heaviestNeighbourVertex = neighbour.vertex;
            }
        }
        if (!childWithMoreThanHalfNodes && (totalCount - countMap.get(vertex)) <= totalCount / 2) {
            return Optional.of(vertex);
        }
        return heaviestNeighbourVertex == -1 ? Optional.empty() : getCentroid(nryTree, heaviestNeighbourVertex, countMap, totalCount, visitedOrCentroids);
    }

    private static int getCount(UndirectedGraph nryTree, int vertex, Map<Integer, Integer> countMap, Set<Integer> visitedOrCentroids) {
        visitedOrCentroids.add(vertex);

        int count = 1;
        for (GraphBase.GraphNode neighbor : nryTree.getNeighbours(vertex)) {
            if (visitedOrCentroids.contains(neighbor.vertex)) {
                continue;
            }
            count += getCount(nryTree, neighbor.vertex, countMap, visitedOrCentroids);
        }
        countMap.put(vertex, count);

        return count;
    }
}
