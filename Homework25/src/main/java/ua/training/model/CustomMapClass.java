package ua.training.model;

public class CustomMapClass <K,V> implements CustomMapInterface <K, V> {

    private final int DEFAULT_INITIAL_CAPACITY = 3;
    private final float DEFAULT_LOAD_FACTOR = 0.6f;
    private CustomEntryClass <K, V> [] table;
    private int size = 0;

    static class CustomEntryClass<K, V> implements CustomEntryInterface <K,V> {
        K key;
        V value;
        int hashValue;
        CustomEntryClass<K,V> next;

        public CustomEntryClass(K key, V value, CustomEntryClass<K,V> next, int hashValue) {
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
            for (CustomEntryClass<K,V> currentEntry : table) {
                if (currentEntry.key.equals(key) && (hashValue == currentEntry.hashValue)) {
                    return currentEntry.value;
                }
            }
            return null;
        }
    }

    public CustomEntryClass <K, V> [] getTable() {
        return table;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        int hashValue = hash(key);
        CustomEntryClass<K, V> newEntry = new CustomEntryClass<K, V>(key, value, null, hashValue);

        if (table[hashValue] == null) {
            int itemsNum = 0;
            for (CustomEntryClass<K,V> currentEntry : table) {
                if (currentEntry != null) {
                    itemsNum++;
                }
                if ((float) (itemsNum / DEFAULT_INITIAL_CAPACITY) > DEFAULT_LOAD_FACTOR) {
                    size = size + DEFAULT_INITIAL_CAPACITY;
                    CustomEntryClass[] newTable = new CustomEntryClass[size];
                    System.arraycopy(table,0, newTable,0, table.length);
                }
            }
            table[hashValue] = newEntry;
        } else {
            CustomEntryClass<K,V> previousEntry = null;
            CustomEntryClass<K,V> currentEntry = table[hashValue];

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
}
