
// A Graph interface with nodes and edges, and some
// static methods - dfs, bfs, asString, asGraphviz.
//
// For example,
// static class Places implements Graph<String, Places.Road> {
//    @Override public Collection<Room> getNodes() { return getRooms(); }



// ---------------------------------------------------

package adt;


import static org.junit.Assert.*;


import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;



// ------------------------------------------------------------
// Graph interface with some static methods
// ------------------------------------------------------------
// N for node class, E for edge class
interface Graph<N, E> {

    // ------------------------------------------------------------
    // Methods to be implemented
    // ------------------------------------------------------------
    Collection<N> getNodes();
    Collection<E> getEdges(N node);
    N getDestination(E edge);
    double getCost(E edge);


    // ------------------------------------------------------------
    // Depth first search
    // ------------------------------------------------------------
    // For a given Graph object and starting node, search for
    // a particular node. 
    // Returns the found node or null if not found.
    //
    // Implementation
    // Works iteratively, uses stack to store nodes to visit
    static <N, E> N dfs(Graph<N, E> g, N start, N find) {
        Stack<N> unseen = new Stack<>();
        Set<N> seen = new HashSet<>();
        unseen.push(start);
        while (!unseen.empty()) {
            N n = unseen.pop();
            System.out.println("visiting " + n); // TODO use logging
            if (n.equals(find)) { return n; }
            seen.add(n);
            // add all unseen children from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) { unseen.push(dest); }
            }
        }
        // return new N("NullObject"); // can't do due to type erasure
        return null; // TODO would prefer to return a NullObject, or Optional
    }


    // ------------------------------------------------------------
    // Breadth first search
    // ------------------------------------------------------------
    // For a given Graph object and starting node, search for
    // a particular node. 
    // Returns the found node or null if not found.
    //
    // Implementation
    // The same algorithm as dfs, but uses queue instead of stack
    // to store nodes to visit.
    // TODO could combine dfs and bfs code to keep code in synch
    static <N, E> N bfs(Graph<N, E> g, N start, N find) {
        Queue<N> unseen = new ArrayDeque<>();
        Set<N> seen = new HashSet<>();
        unseen.add(start);
        while (unseen.peek() != null) { // ie while unseen.hasElements
            N n = unseen.remove();
            System.out.println("visiting " + n); // TODO use logging
            if (n.equals(find)) { return n; }
            seen.add(n);
            // add all unseen children from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) { unseen.add(dest); }
            }
        }
        return null; // TODO would prefer to return a NullObject, or Optional
    }


    // ------------------------------------------------------------
    // Dijkstra's algorithm - find shortest distance between two nodes
    // ------------------------------------------------------------
    // Given a Graph g, find the shortest distance from start to target.
    // Returns distance, or infinity if no path to target exists.
    static <N, E> double dijkstra(Graph<N, E> g, N startNode, N targetNode) {

        // list of all nodes
        Collection<N> nodes = g.getNodes();

        // make sure nodes exist
//        assert nodes.contains(startNode);
//        assert nodes.contains(targetNode);
        assertTrue(nodes.contains(startNode));
        assertTrue(nodes.contains(targetNode));

        // init distances from start to node
        Map<N, Double> dStartToNodeMap = new HashMap<>();
        for (N node : nodes) { dStartToNodeMap.put(node, Double.POSITIVE_INFINITY); }
        dStartToNodeMap.put(startNode, 0.0); // start to start is 0

        // init list of unvisited nodes
        List<N> unvisited = new ArrayList<>(nodes); // make copy

        // init start node
        double dStartToTarget = Double.POSITIVE_INFINITY;
        double dStartToCurrent = 0.0;
        N currentNode = startNode;

        // search graph until target found or all nodes visited
        while (true) {

            // update neighbor distances based on distance to current node
            dijkstraUpdateNeighbors(g, currentNode, unvisited, dStartToCurrent, dStartToNodeMap);

            // mark current node as visited
            unvisited.remove(currentNode);

            // if target has been visited, return its distance value
            if (! unvisited.contains(targetNode)) { dStartToTarget = dStartToNodeMap.get(targetNode); break; }

            // find unvisited node with lowest distance from start
            N nodeLowest = null;
            double dLowest = Double.POSITIVE_INFINITY;
            for (N node : nodes) {
                if (unvisited.contains(node)) {
                    double dStartToNode = dStartToNodeMap.get(node);
                    if (dStartToNode < dLowest) { dLowest = dStartToNode; nodeLowest = node; }
                }
            }
            
            // if there are no more unvisited nodes or all are at infinity, target is unreachable, so return infinity
            if (nodeLowest == null) { dStartToTarget = Double.POSITIVE_INFINITY; break; }

            // go to best unvisited node
            currentNode = nodeLowest;
            dStartToCurrent = dStartToNodeMap.get(currentNode);

        } // while

        return dStartToTarget; 
    }


    // Helper method for dijkstra - update distances from start node to unvisited neighbors.
    // Returns nothing, but modifies contents of dStartToNodeMap.
    static <N, E> void dijkstraUpdateNeighbors(Graph<N, E> g, N currentNode,
            List<N> unvisited, double dStartToCurrent, Map<N, Double> dStartToNodeMap) {

        // iterate over current node's unvisited neighbors
        Collection<E> edges = g.getEdges(currentNode);
        for (E edge : edges) {
            N neighborNode = g.getDestination(edge);
            if (unvisited.contains(neighborNode)) {

                // calculate possible new distance from start to neighbor
                double dStartToNeighbor = dStartToNodeMap.get(neighborNode);
                double dCurrentToNeighbor = g.getCost(edge);
                double dStartToCurrentToNeighbor = dStartToCurrent + dCurrentToNeighbor;
//                System.out.println(neighborNode + " " + dStartToNeighbor + " " + dCurrentToNeighbor + " " + dStartToCurrentToNeighbor);

                // if found better path to neighbor, update its distance from start node
                if (dStartToCurrentToNeighbor < dStartToNeighbor) {
                    dStartToNeighbor = dStartToCurrentToNeighbor;
                    dStartToNodeMap.put(neighborNode, dStartToNeighbor);
                }
            }
        }
    }



    // ------------------------------------------------------------
    // asString
    // ------------------------------------------------------------
    // Return a string representation of the given Graph object -
    // a list of nodes and where they exit to.
    // eg
    // Graph.asString(g) => "a->[b,c]   b->[d,a]   c->[]   d->[b]"
    static <N, E> String asString(Graph<N, E> g) {
        StringBuilder sb = new StringBuilder();
        for (N n : g.getNodes()) {
            sb.append(n + "->" + g.getEdges(n) + "\n");
        }
        return sb.toString();
    }


    // ------------------------------------------------------------
    // asGraphviz
    // ------------------------------------------------------------
    // Return a representation of the graph as a Graphviz dot file.
    // eg
    // Graph.asGraphviz(g) => "digraph g {a -> b; a -> c; b -> c;}"
    static <N, E> String asGraphviz(Graph<N, E> g) {
        StringBuilder sb = new StringBuilder();
        sb.append("digraph g {\n");
        for (N n : g.getNodes()) {
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                sb.append("    \"" + n + "\" -> \"" + dest + "\";\n");
            }
        }
        sb.append("}\n");
        return sb.toString();
    }

}


