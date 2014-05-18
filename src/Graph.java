

import java.util.*;


public class Graph {

    static class Node {
        public String key;
        public List<Edge> edges = new ArrayList<Edge>();

        Node(String key) { this.key = key; }
        // just for convenience. could make user create an edge and call here.
        public void addEdge(Node dest, int cost) {
            Edge e = new Edge(dest, cost);
            edges.add(e);
        }
        @Override public String toString() { return key; }
        @Override public boolean equals(Object other) {
            return other instanceof Node && key.equals(((Node) other).key);
        }
        @Override public int hashCode() { return key.hashCode(); }
    }

    static class Edge {
//        public char dest;
        public Node dest;
        public int cost;

        Edge(Node dest, int cost) { this.dest = dest; this.cost = cost; }
        @Override public String toString() {
//            return dest.toString();
            return dest.toString() + String.valueOf(cost);
        }
    }


    // a graph is just a list of nodes -
    // the nodes contain edges which point to other nodes
    List<Node> nodes = new ArrayList<Node>();

    // represent graph as a string, like a->[b,c] b->[d], etc
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node node : nodes) {
            sb.append(node + "->" + node.edges + "\n");
        }
        return sb.toString();
    }



    static Node dfs(Graph g, Node start, Node find) {
        Stack<Node> tosee = new Stack<Node>();
        Set<Node> seen = new HashSet<Node>();
        tosee.push(start);
        while (! tosee.empty()) {
            Node n = tosee.pop();
            if (n.equals(find)) { return n; }
            seen.add(n);
            // adding from left to right, so will be searched right to left - change if that's an issue
            for (Edge e : n.edges) {
                Node dest = e.dest;
                if (! seen.contains(dest)) { tosee.push(dest); }
            }
        }
//        return null; // or null object? but would still have to check?
        return NoMatch;
    }

    // a NullObject
    static Node NoMatch = new Node("No Match");



    public static void main(String[] args) {

        Graph g = new Graph();

        Node root = new Node("root");
        g.nodes.add(root);

//        System.out.println(root);

        Node a = new Node("a");
        g.nodes.add(a);
        root.addEdge(a, 5);

        Node b = new Node("b");
        g.nodes.add(b);
        root.addEdge(b, 4);
        b.addEdge(a, 2);

        System.out.println(g);


        // could have arbitrary search conditions, so use a comparator?
        String s = "b";
        Node find = new Node(s);
        System.out.println("searching for " + s);
        Node n = dfs(g, root, find);
        System.out.println("found " + n);

    }
}
