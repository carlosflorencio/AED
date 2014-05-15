package problemaSerie2.map;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class HashTable<K, V> {
    private static class HNode<K,V> {
        public V value;
        public K key;
        public HNode<K, V> next;

        public HNode(K key, V value) { this.value = value; this.key = key; }
        public String toString() { return this.value.toString(); }
    }

    private int size;
    private HNode<K,V>[] table;
    private float loadFactor = 0.75f;
    private int count = 0;
    private IHashFunction<Object> hf = HashDefaultFunction.getDefaultInstance();

    @SuppressWarnings("unchecked")
    public HashTable(int size, float loadFactor) {
        this.size = size;
        this.loadFactor = loadFactor;
        this.table = new HNode[size];
    }

    @SuppressWarnings("unchecked")
    public HashTable() {
        this.size = 11; //default size is 11
        this.table = new HNode[size];
    }

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        this.table = new HNode[size];
    }

    public V put(K key, V value) {
        if(key == null || value == null) return null;

        int index = this.hash(key);
        HNode<K, V> toInsert = new HNode<K,V>(key, value);

        if(table[index] == null) {
            table[index] = toInsert;
        } else {
            for(HNode<K,V> node = table[index]; node != null; node = node.next) {
                if(this.hf.equals(node.key, key)) { //find duplicate and replace
                    V tmp = node.value;
                    node.value = toInsert.value;
                    return tmp;
                }
            }
            toInsert.next = table[index]; //insert if does not exist
            table[index] = toInsert;
        }
        this.count++;
        this.rehashIfNeeded();

        return null; //if we insert a new value we should return null
    }

    public V get(K key) {
        if(key == null) return null;

        int index = this.hash(key);
        if(table[index] == null) return null;

        for(HNode<K,V> node = table[index]; node != null; node = node.next) {
            if(this.hf.equals(node.key, key))
                return node.value;
        }
        return null;
    }

    public V remove(K key) {
        if(key == null) return null;

        int index = this.hash(key);
        if(table[index] == null) return null;

        HNode<K,V> toRemove;

        if(table[index].next == null) { //only one chaining element? cool
            toRemove = table[index];
            table[index] = null;
            count--;
            return toRemove.value;
        } else { //should we use a double linked list?
            if(this.hf.equals(table[index].key, key)) { //first element in chaining
                count--;
                toRemove = table[index];
                table[index] = toRemove.next;
                return toRemove.value;
            }

            HNode<K,V> node, prev;
            for(node = table[index].next, prev = table[index]; node != null; prev = node, node = node.next) {
                if(this.hf.equals(node.key, key)) {
                    count--;
                    prev.next = node.next;
                    return node.value;
                }
            }
        }

        return null;
    }

    private int hash(K key) {
        return Math.abs(this.hf.hashCode(key) % this.size);
    }

    /*
    |--------------------------------------------------------------------------
    | Rehash functions
    |--------------------------------------------------------------------------
    */
    private void rehashIfNeeded() {
        if(this.count / (float)size >= loadFactor)
            rehash();
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        int oldSize = this.size;
        this.size *= 2;
        HNode<K,V>[] newArray = new HNode[size];

        for(int i=0; i< oldSize; ++i) {
            if(table[i] == null) continue;
            int index = this.hash(table[i].key);
            newArray[index] = table[i];
        }
        this.table = newArray;
    }

    /*
    |--------------------------------------------------------------------------
    | Iterator
    |--------------------------------------------------------------------------
    */
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            HNode<K, V> current = null;
            HNode<K, V> previous = null;
            int index = 0;

            @Override
            public boolean hasNext() {
                if(current != null) return true;

                if(previous != null && previous.next != null) {
                    current = previous.next;
                    return true;
                }

                while(index < size) {
                    if(table[index] != null) {
                        current = table[index];
                        index++;
                        return true;
                    }
                    index++;
                }
                return false;
            }

            @Override
            public V next() {
                if(hasNext()) {
                    V aux = current.value;
                    previous = current;
                    current = null;
                    return aux;
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
                if(current == null && previous != null) {
                    HashTable.this.remove(previous.key); //same method name, so we need so specify
                    previous = previous.next;
                    return;
                }
                throw new IllegalStateException();
            }
        };
    }

    /*
    |--------------------------------------------------------------------------
    | Getters
    |--------------------------------------------------------------------------
    */
    public int getSize() {
        return this.count;
    }

    public boolean isEmpty() {
        return this.count == 0;
    }

    public float getFactor() {
        return this.loadFactor;
    }

    public float getCurrentFactor() {
        return count / (float)size;
    }
}
