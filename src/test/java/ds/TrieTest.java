
// Test Trie

package ds;

import static test.Test.*;

import org.junit.Test;
import static org.junit.Assert.*;


public class TrieTest {


    @Test public void testTrie() throws Exception {

        Trie t = new Trie();

        // nothing there yet
        assertString("[]", t.getWords(""));
        assertString("[]", t.getWords("hi"));

        // add some words
        t.addWord("i");
        t.addWord("if");
        t.addWord("in");
        t.addWord("info");
        t.addWord("into");
        t.addWord("red");
        t.addWord("robot");
        t.addWord("room");

        // words are returned in sorted order
        assertString("[red, robot, room]", t.getWords("r"));
        assertString("[]", t.getWords("ra"));
        assertString("[]", t.getWords("rooms"));

        assertString("[i, if, in, info, into]", t.getWords("i"));
        assertString("[in, info, into]", t.getWords("in"));
        assertString("[if]", t.getWords("if"));
        assertString("[]", t.getWords("inz"));
        assertString("[]", t.getWords("is"));

        assertString("[i, if, in, info, into, red, robot, room]", t.getWords("")); // all words

        assertString("[]", t.getWords("z"));

        // re-add a word
        t.addWord("room");
        assertString("[i, if, in, info, into, red, robot, room]", t.getWords("")); // all words

    }

}