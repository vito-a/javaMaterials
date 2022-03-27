package ua.training.model;

import java.util.Arrays;

public class CustomMapClass <K,V> implements CustomMapInterface <K, V> {

    private final int DEFAULT_INITIAL_CAPACITY = 3;
    private final float DEFAULT_LOAD_FACTOR = 0.6f;
    private CustomEntryClass <K, V> [] table;
    private int size = 0;

    public static class CustomEntryClass<K, V> implements CustomEntryInterface <K,V> {
        K key;
        V value;
        int hashValue;
        CustomEntryClass<K, V> next;

        public CustomEntryClass(K key, V value, CustomEntryClass<K, V> next, int hashValue) {
            this.hashValue = hashValue;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public V getValue() {
            return this.value;
        }

        @Override
        public int getHashValue() {
            return hashValue;
        }

        @Override
        public CustomEntryInterface<K, V> getNext() {
            return this.next;
        }

        @Override
        public String toString() {
            return "{" + key + "=" + value + ",hash=" + hashValue + "}" + " ";
        }
    }

    public CustomMapClass() {
        this.table = new CustomEntryClass[DEFAULT_INITIAL_CAPACITY];
        size = DEFAULT_INITIAL_CAPACITY;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Boolean isEmpty() {
        return null;
    }

    @Override
    public V get(K key) {
        int hashValue = hash(key);
        if (table[hashValue] == null) {
            return null;
        } else {
            CustomEntryClass<K,V> currentEntry = table[hashValue];
            while (currentEntry != null) {
                if (currentEntry.key.equals(key)) {
                    return currentEntry.value;
                }
                currentEntry = currentEntry.next;
            }
            return null;
        }
    }

    public CustomEntryClass <K, V> [] getTable() {
        return table;
    }

    private int hash(K key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        int hashValue = hash(key);
        CustomEntryClass<K, V> newEntry = new CustomEntryClass<>(key, value, null, hashValue);

        int itemsNum = 0;
        CustomEntryClass<K,V> currentEntry = null;
        for (int i = 0; i < size; i++){
            if (table[i] != null) {
                currentEntry = table[i];
                while (currentEntry != null) {
                    currentEntry = currentEntry.next;
                    itemsNum++;
                }
            }
        }
        float currentLoad = (float) itemsNum / size;
        System.out.println("itemsNum = " + itemsNum + ", current load = " + currentLoad);
        if (currentLoad > DEFAULT_LOAD_FACTOR) {
            System.out.println(this);
            // System.out.println(Arrays.toString(table));
            System.out.print("Current load = " + currentLoad + ", increased the buckets table, old size = " + size);
            System.out.print(" new size = " + (size + DEFAULT_INITIAL_CAPACITY));
            System.out.println("\n");
            size = size + DEFAULT_INITIAL_CAPACITY;
            CustomEntryClass[] newTable = new CustomEntryClass[size];
            System.arraycopy(table,0, newTable,0, table.length);
            /*
            for (CustomEntryClass<K,V> tempEntry : table) {
                if (tempEntry != null) {
                    newTable[tempEntry.hashValue] = table[tempEntry.hashValue];
                }
            }
            */
            table = newTable;
        }

        if (table[hashValue] == null) {
            table[hashValue] = newEntry;
        } else {
            CustomEntryClass<K,V> previousEntry = null;
            currentEntry = table[hashValue];

            while (currentEntry != null) {
                if (currentEntry.key.equals(newEntry.key)) {
                    if (previousEntry == null) {
                        newEntry.next = currentEntry.next;
                        table[hashValue] = newEntry;
                        return;
                    } else {
                        newEntry.next = currentEntry.next;
                        table[hashValue] = newEntry;
                        return;
                    }
                }
                previousEntry = currentEntry;
                currentEntry = currentEntry.next;
            }
            previousEntry.next = newEntry;
        }
    }

    public boolean remove (K key) {
        int hashValue = hash(key);
        if (table[hashValue] == null){
            return false;
        } else {
            CustomEntryClass<K,V> previousEntry = null;
            CustomEntryClass<K,V> currentEntry = table[hashValue];

            while (currentEntry != null) {
                if (currentEntry.key.equals(key)) {
                    if (previousEntry == null) {
                        table[hashValue] = table[hashValue].next;
                        return true;
                    } else {
                        previousEntry.next = currentEntry.next;
                        return true;
                    }
                }
                previousEntry = currentEntry;
                currentEntry = currentEntry.next;
            }

        }

        return false;
    }

    @Override
    public String toString() {
        String tableString = "";
        for (int i = 0; i < size; i++){
            if (table[i] != null) {
                CustomEntryClass entry = table[i];
                while (entry != null) {
                    tableString += entry.toString();
                    entry = (CustomMapClass.CustomEntryClass) entry.getNext();
                }
            }
        }
        // Arrays.toString(table)
        return tableString;
    }
}
