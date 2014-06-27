
// simple implementation of a directed graph, using the Graph interface,
// for use in testing search/sort routines.


package lemmas.ds;


import lemmas.adt.Graph;

import java.util.*;

//
//@SuppressWarnings("serial")
//class DirectedGraph2<N> extends HashMap<N, List<N>> implements Graph<N,DirectedGraph2.Edge<N>> {
//
//
//    public List<N> getDestinations(N n) { return this.get(n); }
//
//
//    // ------------------------------------------------------------
//    // Graph interface
//    // ------------------------------------------------------------
//
////    @Override public boolean addNode(N n) { return nodes.add(n); }
//    @Override public boolean addNode(N n) { return true; } // TODO store nodes
//    @Override public boolean addEdge(N src, N dst) { return addEdge(src, dst, 1); }
//    public boolean addEdge(N src, N dst, double cost) {
//        // uh, cost
//        List<N> elist = this.get(src);
//        if (elist == null) {elist = new ArrayList<>();}
//        elist.add(dst);
//        this.put(src, elist);
//        return true;
//    }
//
//    @Override public List<N> getNodes() { return new ArrayList<N>(); }
//    @Override public List<Edge<N>> getEdges() {
//        List<Edge<N>> edges = new ArrayList<>();
//        // TODO walk over nodes, add all edges
////        for (N node : nodes) {
////            for (Edge<N> edge : getEdges(node)) { edges.add(edge); }
////        }
//        return edges;
//    }
//    @Override public List<Edge<N>> getEdges(N node) {
//        // each node stores a list of destination nodes, while an Edge class
//        // stores a source node, a target node, and a cost, so need to create a new Edge
//        // for each target
//        // inefficient, but okay for small graphs
//        // TODO use index or multimap for speed
//        List<N> nlist = getDestinations(node);
//        List<Edge<N>> elist = new ArrayList<>();
//        for (N n : nlist) { Edge<N> e = new Edge<>(node, n); elist.add(e); }
//        return elist;
//    }
//
//    @Override public N getSource(Edge<N> edge) { return edge.src; }
//    @Override public N getDestination(Edge<N> edge) { return edge.dst; }
//    @Override public double getCost(Edge<N> edge) { return edge.cost; }
//
//
//
//
//
//    // ------------------------------------------------------------
//    // Edge
//    // ------------------------------------------------------------
//    static class Edge<N> {
//
//        // an edge is a 3-tuple: source, destination, cost
//        N src;
//        N dst;
//        double cost;
//
//        // constructors
//        Edge(N src, N dst) { this.src = src; this.dst = dst; this.cost = 1; }
//        Edge(N src, N dst, double cost) { this.src = src; this.dst = dst; this.cost = cost; }
//
//        // representation - used by Graph.asString
//        @Override public String toString () { return dst.toString(); }
//
//        @Override public boolean equals (Object other) {
//            Edge<?> e = (Edge<?>) other; // warning if use Edge<N> - unchecked method invocation
//            if (other == null) {return false;}
//            if (this.getClass() != other.getClass()) {return false;}
//            if (!this.src.equals(e.src)) {return false;}
//            if (!this.dst.equals(e.dst)) {return false;}
//            if (this.cost != e.cost) {return false;}
//            return true;
//        }
//
//        @Override public int hashCode(){
//            int result = 1;
//            result = 31*result + (src !=null ? src.hashCode() : 0);
//            result = 31*result + (dst !=null ? dst.hashCode() : 0);
//            result = 31*result + (int) cost*1000;
//            return result;
//        }
//    }
//
//}
//
//
//
//
//
//
