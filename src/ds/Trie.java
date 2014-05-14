
/*
Trie data structure
Add strings to trie, build structure letter by letter.
--------------------------------------------------------------------------------
*/

// (keymap-set-local "<f5>" (lambda () (interactive) (file-save) (shell-command "cd../.. && ant run")))
// (keymap-set-local "<f5>" (lambda () (interactive) (file-save) (shell-command "cd../.. && ant") (shell-command "cd../.. && java -cp build/classes ds.Trie")))
// (keymap-set-local "<f5>" (lambda () (interactive) (file-save) (shell-command "cd../.. && ant") (shell-command (concat "cd../.. && java -cp build/classes ds." (file-title))))


package ds;


import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

// import lib.Test;
// import static Test.test;
import static lib.Test.test;
import static lib.Test.testPermutations;


// * Trie
// Trie has two nested classes - Node and Nodes.
public class Trie {
    
    
    // * Node
    // A Node represents one character and its children.
    // The character can be the end of a word or not. 
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
    
        // get all words including and below this node, prefixed with given string.
        // adds them to the list, passed by reference.
        public void getWordsRecurse(String prefix, ArrayList<String> list) {
            for (Node n : children.values()) {
                String nodestring = prefix + n.key;
                if (n.endofword) list.add(nodestring);
                if (n.children.size()>0) n.getWordsRecurse(nodestring, list);
            }
        }

        // get all words starting with the given prefix.
        // find the node corresponding to the prefix, then gather up all the child words.
        public ArrayList<String> getWords(String prefix) {
            ArrayList<String> list = new ArrayList<String>();
            Node node = findNode(prefix);
            if (node!=null) {
                if (node.endofword) list.add(prefix);
                node.getWordsRecurse(prefix, list);
            }
            return list;
        }
    
        
        // public static void main(String[] args) {
        public static void runtests() {
            System.out.println("Hello node");
        
            Node root = new Node();
        
            Node n;
        
            // addChildNode
            n = root.addChildNode('i');
            test(n.key, 'i');
            root.addChildNode('r');
            root.addChildNode('r');
        
            // getChildNode
            n = root.getChildNode('r');
            test(n.key, 'r');
            n = root.getChildNode('z');
            test(n, null);
             
            // addWord
            root.addWord("i");
            n = root.getChildNode('i');
            test(n.key,'i');
            test(n.endofword);
        
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
            test(n.key,'e');
            test(n.endofword,false);
        
            n = root.findNode("zork");
            test(n,null);
        
        
            // getWords
            root.addWord("room");
            root.addWord("robot");
            
            List<String> list;
            list = root.getWords("red");
            test(list,"[red]");
            
            list = root.getWords("re");
            test(list,"[red]");
            
            list = root.getWords("r");
            test(list,"[red, robot, room]");
        
            
            System.out.println("done.");
        }

    }
    
    

    // * Nodes
    // an alias for HashMap Character->Node. 
    @SuppressWarnings("serial") // why?
    static class Nodes extends HashMap<Character, Node> {
        Node addNode(Character k, Node v) {
            return put(k,v);
        }
    }

    
    
    // Trie attributes and methods
    
    Node root = new Node();
    
    public void addWord(String word) {
        root.addWord(word);
    }
    
    public ArrayList<String> getWords(String startingWith) {
        // return root.getWords(startingWith);
        // sort
        ArrayList<String> words = root.getWords(startingWith);
        Collections.sort(words); // in place sort
        return words;
    }
        
    
    public static void runtests() {
        System.out.println("Hello trie");
        
        Trie t = new Trie();
        // System.out.println(t);
        
        t.addWord("i");
        t.addWord("if");
        t.addWord("in");
        t.addWord("info");
        t.addWord("into");
        t.addWord("red");
        t.addWord("robot");
        t.addWord("room");
        
        // results in no particular order, so test permutations
        // oh. could have sorted them. as we would want to. 
        // System.out.println(t.getWords(""));
        test(t.getWords(""), "[i, if, in, info, into, red, robot, room]"); // all words
        test(t.getWords("i"), "[i, if, in, info, into]");
        test(t.getWords("in"), "[in, info, into]");
        test(t.getWords("if"), "[if]");
        test(t.getWords("inz"), "[]");
        test(t.getWords("is"), "[]");
        test(t.getWords("r"), "[red, robot, room]");
        test(t.getWords("rooms"), "[]");
        test(t.getWords("ra"), "[]");
        test(t.getWords("z"), "[]");

        // testPermutations(t.getWords("in"), Arrays.asList("in","info","into"));
        // testPermutations(t.getWords("i"), Arrays.asList("i","if","in","info","into"));
        // testPermutations(t.getWords(""), Arrays.asList("i","if","in","info","into","red","robot","room")); // all words
        
    }
    
    public static void main(String[] args) {
        Trie.runtests();
        Node.runtests();
        // Nodes.runtests();
    }
}





