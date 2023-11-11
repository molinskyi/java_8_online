package org.hw6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Dictionary<K, V> {
    private K key;
    private V value;
    private Dictionary<K, V> next;
    private int capacity = 16;

    private Dictionary<K, V>[] table;

    public Dictionary() {
        table = new Dictionary[capacity];
    }

    public Dictionary(int capacity) {
        this.capacity = capacity;
        table = new Dictionary[capacity];
    }

    public Dictionary(K key, V value, Dictionary<K, V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Dictionary getNext() {
        return next;
    }

    public void setNext(Dictionary<K, V> next) {
        this.next = next;
    }

    public boolean put(K key, V value) {
        int index = (int) key;
        Dictionary newEntry = new Dictionary(key, value, null);
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            Dictionary<K, V> previousNode = null;
            Dictionary<K, V> currentNode = table[index];
            while (currentNode != null) {
                if (currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    break;
                }
                previousNode = currentNode;
                currentNode = currentNode.getNext();
            }
            if (previousNode != null)
                previousNode.setNext(newEntry);
        }
        return true;
    }

    public V get(K key) {
        V value = null;
        int index = (int) key;
        Dictionary<K, V> entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }

    public boolean remove(K key) {
        int index = (int) key;
        Dictionary previous = null;
        Dictionary entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (previous == null) {
                    entry = entry.getNext();
                    table[index] = entry;
                    return true;
                } else {
                    previous.setNext(entry.getNext());
                    return true;
                }
            }
            previous = entry;
            entry = entry.getNext();
        }
        return true;
    }

    int size() {
        int counter = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                counter++;
            }
        }
        return counter;
    }

    boolean isEmpty() {
        boolean isEmpty = false;
        int counter = 0;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                counter++;
            }
        }
        if (counter == 0) {
            isEmpty = true;
        }
        return isEmpty;
    }

    boolean containsKey(K key) {
        boolean containsKey = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].key == key) {
                containsKey = true;
            }
        }
        return containsKey;
    }

    boolean containsValue(V value) {
        boolean containsValue = false;
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && table[i].value == value) {
                containsValue = true;
            }
        }
        return containsValue;
    }

    boolean putAll(Dictionary<K, V> dictionary) {
        for (int i = 0; i < dictionary.size(); i++) {
            if (dictionary.table[i].key != null) {
                put(dictionary.table[i].key, dictionary.table[i].value);
            }
        }
        return true;
    }

    boolean clear() {
        if (!isEmpty()) {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
        }
        return true;
    }

    Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                set.add(table[i].key);
            }
        }
        return set;
    }

    Collection<V> values() {
        Collection collection = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                collection.add(table[i].value);
            }
        }
        return collection;
    }

}
