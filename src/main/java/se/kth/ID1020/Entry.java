package se.kth.ID1020;

import java.util.Map;

public class Entry implements Map.Entry<String, Integer>, Comparable<Entry> {
    public String key = "";
    public int value = 0;

    public Entry(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int compareTo(Entry o) {
        if (this.value > o.value) {
            return 1;
        }
        if (this.value == o.value) {
            return 0;
        } else
            return -1;
    }

    public String getKey() {
        return this.key;
    }

    public Integer getValue() {
        return this.value;
    }

    public Integer setValue(Integer value) {
        this.value = value;
        return null;
    }
}
