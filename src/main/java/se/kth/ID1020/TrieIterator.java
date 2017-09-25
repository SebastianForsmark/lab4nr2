package se.kth.ID1020;

import java.util.Iterator;
import java.util.Map;

public class TrieIterator implements Iterator<Map.Entry<String, Integer>> {
    public Trie root;
    public String prefix;
    public int numOfVisitedNodes = -1;
    public int nodesToVisit;

    public TrieIterator(Trie start, String prefix) {
        this.root = start;
        this.prefix = prefix;

    }

    public boolean hasNext() {
        Entry next = next();
        nodesToVisit--;
        return next != null;
    }

    public Entry next() {
        return next(root, prefix, 255);
    }

    //Could not get next() to iterate properly. Once it reached the end of the word Zealous it returned null the next time next() was called.
    //I attempted to remedy this by adding a parent reference and attempting to call that and make it resume the for loop where it left off but I am running out of time.
    //Forgive me coding gods, for I have failed thee.

    public Entry next(Trie current, String key, int var) {
        if (key.equals("")) {
            for (int i = var; i >= 0; i--) {
                if (current.branch[i] != null) {
                    numOfVisitedNodes++;
                    if (numOfVisitedNodes == nodesToVisit) {
                        nodesToVisit++;
                        numOfVisitedNodes = -1;
                        return new Entry(Character.toString((char) i), current.branch[i].value);
                    } else {
                        return next(current.branch[i], "", 255);
                    }
                }
            }
            return next(current.parent, "", var - 1); // Parent reference attempt
        }
        int currentLetter = (int) key.charAt(0);
        return next(current.branch[currentLetter], key.substring(1), 255);
    }


    public void remove() {
    }
}
