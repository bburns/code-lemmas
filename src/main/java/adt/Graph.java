
// A Graph interface with nodes and edges, and some
// static methods - dfs, bfs, asString, asGraphviz.
//
// For example,
// static class Places implements Graph<String, Places.Road> {
//    @Override public Collection<Room> getNodes() { return getRooms(); }



// ---------------------------------------------------

package adt;


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
    // Dijkstra's algorithm for shortest path
    // ------------------------------------------------------------
    static <N, E> double dijkstra(Graph<N, E> g, N startNode, N targetNode) {

        // initialize distances from start to all other nodes.
        // dFromStart ? dFromStartTo ?
        Map<N, Double> m = new HashMap<>();
        Collection<N> nodes = g.getNodes();
        for (N node : nodes) { m.put(node, Double.POSITIVE_INFINITY); }
        m.put(startNode, 0.0); // start to start is 0

        // initialize list of unvisited nodes
        List<N> unvisited = new ArrayList<>(nodes); // copy

        N currentNode = startNode;
        double dStartToCurrent = 0.0;


        // getStartToTarget()
        double dStartToTarget = Double.POSITIVE_INFINITY;

        // search graph until can't reach any more nodes
        // search graph until current node has already been visited
        while (unvisited.contains(currentNode)) { // ie while current node is unvisited. ie exit when current node has already been visited

            // found target
            if (currentNode.equals(targetNode)) {dStartToTarget = dStartToCurrent; return dStartToTarget; }
            
            // getBestNeighbor()
            // find unvisited neighbor node with lowest distance from start
            // (bestNeighborNode, dBestStartToNeighbor)
            // Node bestNeighborNode = dijkstraGetBestNeighbor(nodes, currentNode, m, );
            
            N bestNeighborNode = null;
            double dBestStartToNeighbor = Double.POSITIVE_INFINITY;
            // iterate over unvisited neighbors
            Collection<E> edges = g.getEdges(currentNode);
            for (E edge : edges) {
                N neighborNode = g.getDestination(edge);
                if (unvisited.contains(neighborNode)) {
                    
                    double dStartToNeighbor = m.get(neighborNode);
                    double dCurrentToNeighbor = g.getCost(edge);
                    double dStartToCurrentToNeighbor = dStartToCurrent + dCurrentToNeighbor;
                    
                    // System.out.println(neighborNode + " " + dStartToNeighbor + " " + dCurrentToNeighbor + " " + dStartToCurrentToNeighbor);

                    // if found better path to neighbor, update its total distance
                    if (dStartToCurrentToNeighbor < dStartToNeighbor) {
                        dStartToNeighbor = dStartToCurrentToNeighbor;
                        m.put(neighborNode, dStartToNeighbor);
                    }

                    // remember closest neighbor
                    if (dStartToNeighbor < dBestStartToNeighbor) {
                        dBestStartToNeighbor = dStartToNeighbor;
                        bestNeighborNode = neighborNode;
                    }
                }
            }

            // mark current node as visited
            unvisited.remove(currentNode);

            // no neighbors left, so exit
            if (bestNeighborNode == null) {dStartToTarget = Double.POSITIVE_INFINITY; return dStartToTarget; }
            
            // go to best neighbor
            currentNode = bestNeighborNode;
            dStartToCurrent = dBestStartToNeighbor;
            
        } // while 
       
        return dStartToTarget;
    }

    
    // static <N, E> double dijkstraGetBestNeighbor(Graph<N, E> g, N startNode, N targetNode) {
    // }



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


