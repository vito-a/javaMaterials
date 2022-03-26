package ua.training.model;

public class CustomMapClass <K,V> implements CustomMapInterface <K, V> {

    private final int DEFAULT_INITIAL_CAPACITY = 32;
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
            return null;
        }

        @Override
        public V getValue() {
            return null;
        }
    }

    public CustomMapClass() {
        this.table = new CustomEntryClass[DEFAULT_INITIAL_CAPACITY];
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

    private int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }
        int hashValue = hash(key);
        CustomEntryClass<K, V> newEntry = new CustomEntryClass<K, V>(key, value, null, hashValue);

        if(table[hashValue] == null){
            table[hashValue] = newEntry;
        } else {
            CustomEntryClass<K,V> previousEntry = null;
            CustomEntryClass<K,V> current = table[hashValue];
        }
    }
}
