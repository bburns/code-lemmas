
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


// package ds;

import java.util.*;

// * Nodes

// /* Nodes
//  */
// class Nodes {
//     // HashMap<char, Node> children = new HashMap<char, Node>();
//     // need to be able to iterate over children - can do in hashmap? don't think so.
//     // unless kept separate array
//     // could do analysis of data types
    
//     public Nodes() {
//     }
    
//     // public Node addNode(char c, Node n) {
//     // }
    
//     public static void test() {
//         System.out.println("hi nodes!");
        
//         Nodes nodes = new Nodes();
//         // System.out.println(nodes);
        
//         // Node n = new Node();
//         // nodes.addNode('c', n);
        
// //        Hash h = new Hash();
// //        h.add('a', "lij");
// //        assert h.get('a')=="lij"; // t??
// //        // assert(h.get('a')=="lij");
// //        System.out.println(h.get('a'));
        
        
//     }
// }






// class Nodes {
    // Map<Character, Node> children;
// }
@SuppressWarnings("serial")
class Nodes extends HashMap<Character, Node> {
    // public Nodes() { }
    Node addNode(Character k, Node v) {
        return put(k,v);
    }
}


// * Node

/*
  Node
  a node represents a letter. following letters in a word are children.
  say there's a top node representing the bol.
  so it can have multiple following letters.
  in a binary tree would just store tree of nodes with names
  and a map would be clearer, codewise, and could abstract it to an array if necess
*/
class Node {
    
    char key;
    boolean endofword;
    // Nodes children;
    // Map<Character, Node> children;
    Nodes children = new Nodes();
        
    
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
        // if ("".equals(s)) return;
        // char c = s.charAt(0);
        // Node n = this.addChildNode(c);
        // if (s.length()==1) n.endofword = true;
        // n.addWord(s.substring(1)); // recurse
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
        // if ("".equals(keys)) return null;
        // char c = keys.charAt(0);
        // Node n = this.getChildNode(c);
        // if (n!=null) return n.findNode(keys.substring(1)); // recurse
        // return null;
        
        Node n = this;
        int nchars = s.length();
        for (int i=0; i < nchars; i++) {
            char c = s.charAt(i);
            n = n.getChildNode(c);
            if (n==null) break;
        }
        return n;
    }
    
    
    public List<String> getWords(String prefix) {
        ArrayList<String> list = new ArrayList<String>();
        list.add("house");
        
        return list;
    }
    
    // public getWords(prefix="") {
    //     list = [];
    //     for (node : childnodes) {
    //         nodestring = prefix + node.key;
    //         if endofword, list+=nodestring;
    //         else list.append(node.getwords(nodestring));
    //     }
    //     return list;
    // }
        
        
    // public static void main(String[] args) {
    public static void test() {
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
        n = root.findNode("zork");
        assert (n==null);
        
        
        List<String> list = root.getWords("r");
        assert (list.contains("red"));
        System.out.println(list);
        
        
        System.out.println("done.");
    }

}
    
    

// * Trie


/* Trie
 */
class Trie {
    
    // Node n = new Node();
    // public Trie() {
    // }
    
    public static void test() {
        System.out.println("Hello trie");
        // Trie t = new Trie();
        // System.out.println(t);
        // t.addstring("cat");
        // t.addstring("cattle");
        // t.addstring("cart");
        
        // cat, cattle, cart
        // t.getstrings("ca");
    }
    
    public static void main(String[] args) {
        Node.test();
        // Nodes.test();
        // Trie.test();        
    }
}




/*

so each level would have 26 slots. 
if made explicit, 26^n total.
say n = 10
10^15ish


first letter
put it in 
  
  
  
*/



