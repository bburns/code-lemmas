
// A Graph interface
// getEdges and getDestination are sufficient to run a depth-first-search.


//import java.util.*;

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
    // starting with given node and searching for other.
    static <N, E> N dfs(Graph<N, E> g, N start, N find) {
        Stack<N> unseen = new Stack<N>();
        Set<N> seen = new HashSet<N>();
        unseen.push(start);
        while (!unseen.empty()) {
            N n = unseen.pop();
            System.out.println("visiting " + n);
            if (n.equals(find)) return n;
            seen.add(n);
            // adding from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) {
                    unseen.push(dest);
                }
            }
        }
//        return new N("NullObject"); // can't do due to type erasure
        return null;
    }


    // bfs
    // Breadth first search
    static <N, E> N bfs(Graph<N, E> g, N start, N find) {
        Queue<N> unseen = new ArrayDeque<N>();
        Set<N> seen = new HashSet<N>();
//        unseen.push(start);
        unseen.add(start);
//        while (!unseen.empty()) {
        while (unseen.peek() != null) { // ie while unseen.hasElements
//            N n = unseen.pop();
            N n = unseen.remove();
            System.out.println("visiting " + n);
            if (n.equals(find)) return n;
            seen.add(n);
            // adding from left to right, so will be searched right to left - change if that's an issue
            for (E e : g.getEdges(n)) {
                N dest = g.getDestination(e);
                if (!seen.contains(dest)) {
//                    unseen.push(dest);
                    unseen.add(dest);
                }
            }
        }
        return null;
    }


    // asString
    // Return a string representation of the given graph -
    // a list of nodes and where they exit to
    static <N, E> String asString(Graph<N, E> g) {
        StringBuilder sb = new StringBuilder();
        for (N n : g.getNodes()) {
            sb.append(n + "->" + g.getEdges(n) + "\n");
        }
        return sb.toString();
    }


    // asGraphviz
    // Return a representation of the graph as a Graphviz dot file, eg
    // digraph g {
    //     a -> b;
    //     a -> c;
    //     b -> c;
    // }
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




