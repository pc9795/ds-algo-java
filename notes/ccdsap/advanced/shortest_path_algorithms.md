In worst case edges=V(V-1) so it is no benefit in using adjacency list representation.
 
In Dijkstra's algo, for negative weight edges, calculate the minimum weight value, add a positive value(equal to the 
absolute value of minimum weight value) to all weights and run the Dijksra's algo for the modified graph.
 