package JavaCollections.Map;

import java.util.Arrays;
import java.util.Objects;

public class myHashMap<K, V> {
    private int THRESHOLD = 16;
    private Object[] array = new Object[THRESHOLD];

    private static class Node<K, V> {
        int hash;
        K key;
        V value;
        Node<K, V> next;

        private Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (Object o : array) {
            if (o != null) {
                stringBuilder.append(o).append(", ");
            }
        }
        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public V get(K key) {
        int index = hash(key);
        Node<K, V> temp;
        if ((temp = (Node<K, V>) array[index]) != null) {
            while (temp.hash != key.hashCode()) {
                temp = temp.next;
            }
            if (temp.hash == key.hashCode()) {
                if (temp.key.equals(key)) {
                    return temp.value;
                }
            }
        }
        return null;

    }

    public void put(K key, V value) {
        boolean notExistKey = true;
        int hash = key.hashCode();
        Node<K, V> node = new Node<>(hash, key, value);
        int index = hash(key);
        if (array[index] == null) {
            array[index] = node;
        } else {
            Node<K, V> temp = (Node<K, V>) array[index];
            while (temp.next != null) {
                if (temp.hash == node.hash) {
                    notExistKey = false;
                    if (!temp.key.equals(key)) {
                        node.next = (Node<K, V>) array[index];
                        array[index] = node;
                    } else {
                        temp.value = node.value;
                    }
                    break;
                }
                temp = temp.next;
            }
            if (notExistKey) {
                temp.next = node;
            }
        }
    }

    private void grow() {
        THRESHOLD = (int) (THRESHOLD * 1.5);
        array = Arrays.copyOf(array, THRESHOLD);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        myHashMap<?, ?> myHashMap = (myHashMap<?, ?>) o;
        return THRESHOLD == myHashMap.THRESHOLD && Objects.deepEquals(array, myHashMap.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(THRESHOLD, Arrays.hashCode(array));
    }

    private int hash(Object key) {
        return key.hashCode() & (THRESHOLD - 1);
    }
}
