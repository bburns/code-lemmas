//
//
//package lemmas.ds;
//
//
//import lemmas.algorithms.*;
//
//import java.util.*;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//
//public class DirectedGraph2Test {
//
//    class Room { String s; Room(String s) { this.s = s; } public String toString() { return s; }}
//
//
//    @Test public void test() throws Exception {
//
//        DirectedGraph2<Room> g = new DirectedGraph2<>();
//        Room r1 = new Room("a");
//        Room r2 = new Room("b");
//        Room r3 = new Room("c");
//        g.addEdge(r1,r2);
//        g.addEdge(r1,r3);
//        g.addEdge(r2,r3);
//        System.out.println(g); // {a=[b, c], b=[c]}
//        List<Room> rooms = g.getDestinations(r2);
//        System.out.println(rooms); // [c]
//
//        Search.dfs(g, r1, r3);
//        Search.bfs(g, r1, r3);
//
//    }
//
//}