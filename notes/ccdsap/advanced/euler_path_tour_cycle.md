**Euler Path**
* Path that uses every edge of a geeks_for_geeks.graph exactly once.
* If a geeks_for_geeks.graph G has an Euler path, then it must have exactly two odd vertices. It must be connected.
* In a directed geeks_for_geeks.graph, must have exactly one vertex with inDegree-outDegree=1 and one with outDegree-inDegree=1. All other
 vertices must have inDegree==outDegree.

**Euler Circuit**
* Circuit that uses every edge of a geeks_for_geeks.graph exactly once.
* If a geeks_for_geeks.graph G has an Euler circuit, then all of its vertices must be even vertices. It must be connected.
* In a directed geeks_for_geeks.graph all vertices must have inDegree==outDegree

Every geeks_for_geeks.graph has an even number of odd vertices.

Removing a single edge from a connected geeks_for_geeks.graph can make it disconnected. Such an edge is called a bridge.

**Fluery's Algorithm**
1. Make sure the geeks_for_geeks.graph has either 0 or 2 odd vertices.
2. If there are 0 odd vertices, start anywhere. If there are 2 odd vertices, start at one of them.
3. Follow edges one at a time. If you have a choice between a bridge and a no-bridge, always choose the non-bridge.
4. Stop when you run out of edges.