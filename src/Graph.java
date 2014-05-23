

//
////import java.util.*;
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.Stack;
//
//
//// this is the abstract Graph interface
//// just enough for dfs - could add more later
//
//interface Graph<Node, Edge> {
//
//    //    Collection<N> getNodes();
//    Collection<Edge> getEdges(Node node);
//
//    Node getDestination(Edge edge);
//
//}
//
//
//
//static Node dfs(Graph g, Node start, Node find) {
//    Stack<Node> tosee = new Stack<Node>();
//    Set<Node> seen = new HashSet<Node>();
//    tosee.push(start);
//    while (! tosee.empty()) {
//        Node n = tosee.pop();
//        if (n.equals(find)) { return n; }
//        seen.add(n);
//        // adding from left to right, so will be searched right to left - change if that's an issue
//        for (Edge e : g.getEdges(n)) {
//            Node dest = g.getDestination(e);
//            if (! seen.contains(dest)) { tosee.push(dest); }
//        }
//    }
//
//    // return NullObject. or return null? but would have to check return value etc
//    return new Node("No Match");
//}
//
//
//
//
