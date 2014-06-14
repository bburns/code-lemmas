
// Test of directed graph


package ds;

//import adt.Graph;
//import ds.GraphDirected; // in package


import org.junit.Test;
import static org.junit.Assert.*;


public class GraphDirectedTest {
    
    @Test public void test() throws Exception {

        // so... if you do this it will treat N as Object?
//        GraphDirected g = new GraphDirected(); // works
//        GraphDirected<Object> g = new GraphDirected<>(); // works
        GraphDirected<Integer> g = new GraphDirected<>(); // works
//        GraphDirected<Character> g = new GraphDirected<>(); // error

//        g.findNode(0);
        
        g.addNode(0);
        g.addNode(1);
        g.addNode(2);
        
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(0,2);
        
        System.out.println(g);
        
    }

}
