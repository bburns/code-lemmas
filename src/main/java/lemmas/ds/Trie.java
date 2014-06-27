// ----------------------------------------------------------------------------
// Trie
// ----------------------------------------------------------------------------
// Add words to Trie object, retrieve list of words matching
// a given prefix.
// ----------------------------------------------------------------------------


package lemmas.ds;


import java.util.*;


// Trie class
// Trie has two nested classes - Node and Nodes
public class Trie {

    // Node class
    // A Node represents one character and its children.
    // The character can be the end of a word or not. 
    private static class Node {

        // -----------------
        // Attributes
        // -----------------
        private char key;
        private boolean endofword;
        private Nodes children = new Nodes();


        // -----------------
        // Constructors
        // -----------------
        public Node() {
            this.key = '/';
        }
        public Node(char key) {
            this.key = key;
        }


        // -----------------
        // Methods
        // -----------------

        // Add a child node containing the given character
        private Node addChildNode(char c) {
            Node node = getChildNode(c);
            if (node==null) {
                node = new Node(c);
                children.addNode(c, node);
            }
            return node;
        }

        // Add a word starting at this node by iterating through child nodes
        private void addWord(String s) {
            Node n = this;
            int nchars = s.length();
            for (int i=0; i < nchars; i++) {
                char c = s.charAt(i);
                n = n.addChildNode(c);
                if (i==nchars-1) n.endofword = true;
            }
        }

        // Get the child node associated with the given character.
        // Map returns null if not found, so we'll follow that convention.
        private Node getChildNode(char c) {
            return children.get(c);
        }

        // Find node by following characters in string.
        // If passed empty string, will return this node.
        private Node findNode(String s) {
            Node n = this;
            int nchars = s.length();
            for (int i=0; i < nchars; i++) {
                char c = s.charAt(i);
                n = n.getChildNode(c);
                if (n==null) break;
            }
            return n;
        }

        // Get all words including and below this node, prefixed with given string,
        // and add them to the given list.
        private void getWordsRecurse(String prefix, List<String> words) {
            for (Node n : children.values()) {
                String nodestring = prefix + n.key;
                if (n.endofword) words.add(nodestring);
                if (n.children.size()>0) n.getWordsRecurse(nodestring, words);
            }
        }

        // Get all words starting with the given prefix.
        // Find the node corresponding to the prefix, then gather up all the child words.
        private List<String> getWords(String prefix) {
            List<String> words = new ArrayList<>();
            Node node = findNode(prefix);
            if (node!=null) {
                if (node.endofword) words.add(prefix);
                node.getWordsRecurse(prefix, words);
            }
            return words;
        }

        public String toString() {
            return Character.toString(key);
        }


        // DON'T REMOVE
        // Test the Node class
        // This class was developed first, but is now an inner class,
        // so it's less necessary to test, plus it's harder to do with JUnit.
        // So just uncomment this and run when needed, eg if change anything in class.
//        public static void testNode() {
//
//            Trie.Node root = new Trie.Node();
//            Trie.Node n;
//
//            // addChildNode
//            n = root.addChildNode('i');
//            test(n.key, 'i');
//            root.addChildNode('r');
//            root.addChildNode('r');
//
//            // getChildNode
//            n = root.getChildNode('r');
//            test(n.key, 'r');
//            n = root.getChildNode('z');
//            test(n, null);
//
//            // addWord
//            root.addWord("i");
//            n = root.getChildNode('i');
//            test(n.key,'i');
//            test(n.endofword);
//
//            // findNode
//            root.addWord("red");
//            n = root.findNode("re");
//            test(n.key,'e');
//            test(n.endofword,false);
//        }

    }



    //-------------------------------------------------------------
    // Nodes class
    //-------------------------------------------------------------

    // Nodes is just a map from Character to Node
    @SuppressWarnings("serial") //. why?
    private static class Nodes extends HashMap<Character, Node> {
        Node addNode(Character k, Node v) {
            return put(k,v);
        }
    }



    //-------------------------------------------------------------
    // Trie class
    //-------------------------------------------------------------

    //---------------------------
    // Attributes
    //---------------------------

    // A trie always has a root node
    Node root = new Node();

    //---------------------------
    // Methods
    //---------------------------

    // Add a word to the trie
    public void addWord(String word) {
        root.addWord(word);
    }

    // Get a sorted list of words starting with the given prefix string
    public List<String> getWords(String startingWith) {
        List<String> words = root.getWords(startingWith);
        Collections.sort(words); // in place sort
        return words;
    }

    // Represent trie as an array, eg "[red, root, beer]". Includes all words, sorted.
    public String toString() {
        List<String> words = root.getWords("");
        return words.toString();
    }

    // to test Node class (DON'T REMOVE)
//    public static void main(String[] args) { Node.testNode(); }

}

