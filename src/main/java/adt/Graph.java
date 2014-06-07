
// A Graph interface and some search methods


package adt;


import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.Queue;
import java.util.ArrayDeque;



// N for node class, E for edge class
interface Graph<N, E> {

    // Methods that need to be implemented

    Collection<N> getNodes();
    Collection<E> getEdges(N node);
    N getDestination(E edge);


    // Static methods

    // dfs
    // Depth first search on given Graph object,
    // starting with node start and searching for node find.
    // Returns the found node or null if not found.
    //
    // Uses stack to store nodes to visit
    static <N, E> N dfs(Graph<N, E> g, N start, N find) {
        Stack<N> unseen = new Stack<>();
        Set<N> seen = new HashSet<>();
        unseen.push(start);
        while (!unseen.empty()) {
            N n = unseen.pop();
            System.out.println("visiting " + n);
            if (n.equals(find)) { return n; }
            seen.add(n);
            // add all unseen children from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) { unseen.push(dest); }
            }
        }
        // TODO would prefer to return a NullObject, or Optional
//        return new N("NullObject"); // can't do due to type erasure
        return null;
    }


    // bfs
    // Breadth first search on given Graph object,
    // starting with node start and searching for node find.
    // Returns the found node or null if not found.
    //
    // Same algorithm as dfs, but uses queue to store nodes to visit
    // TODO combine dfs and bfs to keep in synch
    static <N, E> N bfs(Graph<N, E> g, N start, N find) {
        Queue<N> unseen = new ArrayDeque<>();
        Set<N> seen = new HashSet<>();
        unseen.add(start);
        while (unseen.peek() != null) { // ie while unseen.hasElements
            N n = unseen.remove();
            System.out.println("visiting " + n);
            if (n.equals(find)) { return n; }
            seen.add(n);
            // add all unseen children from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) { unseen.add(dest); }
            }
        }
        // TODO would prefer to return a NullObject, or Optional
        return null;
    }


    // asString
    // Return a string representation of the given Graph object -
    // a list of nodes and where they exit to. eg
    // "a->[b,c]   b->[d,a]   c->[]   d->[b]"
    static <N, E> String asString(Graph<N, E> g) {
        StringBuilder sb = new StringBuilder();
        for (N n : g.getNodes()) {
            sb.append(n + "->" + g.getEdges(n) + "\n");
        }
        return sb.toString();
    }


    // asGraphviz
    // Return a representation of the graph as a Graphviz dot file, eg
    // "digraph g {a -> b; a -> c; b -> c;}"
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




