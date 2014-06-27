// ----------------------------------------------------------------------------
// Search algorithms
// ----------------------------------------------------------------------------


package lemmas.algorithms;


import lemmas.adt.Graph;

import java.util.*;
import static org.junit.Assert.*;


public class Search {

    // ------------------------------------------------------------
    // Depth first search
    // ------------------------------------------------------------
    // For a given Graph object and starting node, search for
    // a particular node.
    // Returns the found node or null if not found.
    //
    // Implementation
    // Works iteratively, uses stack to store nodes to visit
//    public static <N, E> N dfs(Graph<N, E> g, N start, N find, N notfound) {
    public static <N, E> N dfs(Graph<N, E> g, N start, N find) {
        System.out.println("running dfs...");
        Stack<N> unseen = new Stack<>();
        Set<N> seen = new HashSet<>();
        unseen.push(start);
        while (!unseen.empty()) {
            N n = unseen.pop();
            System.out.println("visiting " + n); // TODO use logging
            if (n.equals(find)) {
                System.out.println("-- found " + n + " --");
                return n;
            }
            seen.add(n);
            // add all unseen children from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) { unseen.push(dest); }
            }
        }
        System.out.println("-- couldn't find " + find + " --");
        // return new N("NullObject"); // can't do due to type erasure
        // return notfound; // nullobject or null
        return null; // TODO would prefer to return a NullObject, or Optional
    }


//    public static <N, E> N dfs(Graph<N, E> g, N start, N find) {
//        return dfs(g, start, find, null);
//    }

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
    public static <N, E> N bfs(Graph<N, E> g, N start, N find) {
        System.out.println("running bfs...");
        Queue<N> unseen = new ArrayDeque<>();
        Set<N> seen = new HashSet<>();
        unseen.add(start);
        while (unseen.peek() != null) { // ie while unseen.hasElements
            N n = unseen.remove();
            System.out.println("visiting " + n); // TODO use logging
            if (n.equals(find)) {
                System.out.println("-- found " + n + " --");
                return n;
            }
            seen.add(n);
            // add all unseen children from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) { unseen.add(dest); }
            }
        }
        System.out.println("-- couldn't find " + find + " --");
        return null; // TODO would prefer to return a NullObject, or Optional
    }


    // ------------------------------------------------------------
    // Dijkstra's algorithm
    // find shortest distance between two nodes
    // ------------------------------------------------------------
    // Given a Graph g, find the shortest distance from start to target.
    // Returns distance, or infinity if no path to target exists.
    public static <N, E> double dijkstra(Graph<N, E> g, N startNode, N targetNode) {

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


    // Dijkstra helper method - update distances from start node to unvisited neighbors.
    // Modifies contents of dStartToNodeMap. Returns nothing.
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

}


