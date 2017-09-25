package se.kth.ID1020;

public class Trie {
    public Trie root;
    public String key;
    public int value;
    public Trie[] branch;
    public Trie parent;

    public Trie() {
        this.key = "";
        this.branch = new Trie[256];
        int value = 0;
    }
    public Trie(Trie parent) {
        this.key = "";
        this.branch = new Trie[256];
        int value = 0;
        this.parent=parent;
    }

    public Trie(String key, int value) {
        this.key = key;
        this.branch = new Trie[256];
        this.value = value;
    }

    public void put(String key) {
        if ("".equals(key)) {
            this.value++;
            return;
        }
        int currentIndex = (int) key.charAt(0);

        if (this.branch[currentIndex] == null) {
            this.branch[currentIndex] = new Trie(this);
        }

        this.branch[currentIndex].put(key.substring(1));
    }

    public int get(String key) {
        if (isEndOf(key)) {
            return this.value;
        }
        int currentIndex = (int) key.charAt(0);

        if (this.branch[currentIndex] == null) {
            return 0;
        }
        return this.branch[currentIndex].get(key.substring(1));
    }

    public int count(String key) {
        int sumOfBranches = 0;
        if (isEndOf(key)) {
            sumOfBranches += this.value;
            for (int i = 255; i >=0; i--) {
                if (this.branch[i] != null) {
                    sumOfBranches += this.branch[i].count("");
                }
            }
            return sumOfBranches;
        }
        int currentIndex = (int) key.charAt(0);
        return this.branch[currentIndex].count(key.substring(1));
    }

    public int distinct(String key) {
        int numOfBranches = 0;
        if (isEndOf(key)) {
            for (int i = 0; i < 255; i++) {
                if (this.branch[i] == null) {
                } else
                    numOfBranches += 1 + this.branch[i].distinct("");
            }
            return numOfBranches;
        }
        int currentIndex = (int) key.charAt(0);
        return this.branch[currentIndex].distinct(key.substring(1));
    }

    private boolean isEndOf(String key) {
        return "".equals(key);
    }

}
