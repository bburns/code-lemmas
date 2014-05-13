
/*
Trie data structure
--------------------------------------------------------------------------------

want to add strings to trie.
it builds structure letter by letter.

*/

// (keymap-describe (keymap-local))
// (keymap-set-local "<f5>" nil)
// (shell-command "java Trie" "trie")
// (shell-command "go" "trie")
// (keymap-set-local "<f5>" (lambda () (interactive) (file-save) (shell-command (concat "go " Trie "trie")))
// (keymap-set-local "<f5>" (lambda () (interactive) (file-save) (shell-command (concat "go " (filename-title)) (concat (filename-title) " output"))))
// (file-name-base) ;=> "Trie"
// (file-name-title)

(keymap-set-local "<f5>" (lambda () (interactive) (file-save) (shell-command (concat "ant run " (filename-title)) (concat (filename-title) " output"))))



package ds;


// import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

// import static Test.test;
// import static lib.Test.test;



// * Trie


/* Trie
 */
public class Trie {
    
    
    
// * Nodes

/* Nodes
 */
@SuppressWarnings("serial") // why?
static class Nodes extends HashMap<Character, Node> {
    Node addNode(Character k, Node v) {
        return put(k,v);
    }
}



// * Node

/* Node
  a node represents a letter. following letters in a word are children.
  say there's a top node representing the bol.
  so it can have multiple following letters.
  in a binary tree would just store tree of nodes with names
  and a map would be clearer, codewise, and could abstract it to an array if necess
*/
static class Node {
    
    char key;
    boolean endofword;
    Nodes children = new Nodes();
        
    
    // constructors
    public Node() {
        this.key = '/';
    }
    
    public Node(char key) {
        this.key = key;
    }
    
    
    public String toString() {
        return Character.toString(key);
        // return children.toString();
        // return Character.toString(key) + children.toString();
    }
        
    
    public Node addChildNode(char c) {
        //. what if exists?
        Node node = getChildNode(c);
        if (node==null) {
            node = new Node(c);
            children.addNode(c, node);
        }
        return node;
    }
    
    public void addWord(String s) {
        Node n = this;
        int nchars = s.length();
        for (int i=0; i < nchars; i++) {
            char c = s.charAt(i);
            n = n.addChildNode(c);
            if (i==nchars-1) n.endofword = true;
        }
    }
    
    public Node getChildNode(char c) {
        //. what if not found? throw error?
        // map will return null
        return children.get(c);
    }

    // find node following characters in string
    public Node findNode(String s) {
        Node n = this;
        int nchars = s.length();
        for (int i=0; i < nchars; i++) {
            char c = s.charAt(i);
            n = n.getChildNode(c);
            if (n==null) break;
        }
        return n;
    }
    
    // get words below this node, prefixed with given string
    public ArrayList<String> getWords(String prefix) {
        ArrayList<String> list = new ArrayList<String>();
        for (Node n : children.values()) {
            String nodestring = prefix + n.key;
            if (n.endofword) list.add(nodestring);
            else list.addAll(n.getWords(nodestring));
        }
        return list;
    }

    // node = root.findNode("re");
    // words = node.getWords("re");
    // words = root.getWordsStartingWith("re");
    
    // public List<String> getWords(String prefix) {
    public List<String> getWordsStartingWith(String prefix) {
        Node node = findNode(prefix);
        // ArrayList<String> list = new ArrayList<String>();
        // if (node.endofword) list.add(prefix);
        // list.append(node.getWords(prefix));
        ArrayList<String> list = node.getWords(prefix);
        if (node.endofword) list.add(0, prefix);
        return list;
    }
    
        
    // public static void main(String[] args) {
    public static void testclass() {
        System.out.println("hi node!");
        
        Node root = new Node();
        
        Node n;
        
        // addChildNode
        n = root.addChildNode('i');
        assert (n.key=='i');
        root.addChildNode('r');
        root.addChildNode('r');
        
        // getChildNode
        n = root.getChildNode('r');
        assert (n.key=='r');
        n = root.getChildNode('z');
        assert (n==null);
             
        // addWord
        root.addWord("i");
        n = root.getChildNode('i');
        assert (n.key=='i');
        assert (n.endofword==true);
        
        root.addWord("red");
        
        // getChildren
        // // for (Node child : n.children())
        // Nodes children = root.getChildren();
        // // assert (children.size() == 2);
        // for (n : children.values()) {
        //     System.out.println(n);
        // }
        
        // findNode
        n = root.findNode("re");
        assert (n.key=='e');
        assert (n.endofword==false);
        
        n = root.findNode("zork");
        assert (n==null);
        
        
        // getWords starting with
        // List<String> list = root.getWords("r");
        root.addWord("room");
        root.addWord("robot");
        List<String> list;
        list = root.getWordsStartingWith("red");
        assert (list.contains("red"));
        System.out.println(list);
        list = root.getWordsStartingWith("re");
        assert (list.contains("red"));
        System.out.println(list);
        list = root.getWordsStartingWith("r");
        assert (list.contains("red"));
        System.out.println(list);
        
        
        System.out.println("done.");
    }

}
    
    

    
    
    
    Node root = new Node();
    
    public void addWord(String word) {
        root.addWord(word);
    }
    
    // public ArrayList<String> getWords(String startingWith) {
    // }
        
    public static void testclass() {
        System.out.println("Hello trie");
        
        Trie t = new Trie();
        System.out.println(t);
        
        // t.addWord("i");
        t.addWord("if");
        // t.addWord("in");
        // t.addWord("info");
        // t.addWord("into");
        // t.addWord("red");
        // t.addWord("robot");
        // t.addWord("room");

        // test(t.getWords(), "[i, if, in, info, into, red, robot, room]"); // all words
        // test(t.getWords("i"), "[i, if, in, info, into]");
     // test(t.getWords(in) -> in,info,into
        
        // test(t.getWords("if"), "[if]");
        
     // test(t.getWords(is) -> []
     // test(t.getWords(r) -> red,robot,room
     // test(t.getWords(ra) -> []
     // test(t.getWords(z) -> []


    }
    
    public static void main(String[] args) {
        Trie.testclass();
        // Node.testclass();
        // Nodes.testclass();
    }
}





