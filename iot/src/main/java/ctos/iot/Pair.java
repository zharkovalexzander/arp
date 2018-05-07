package ctos.iot;

public class Pair<V, K> {
    private V key;
    private K value;

    public K getValue() {
        return value;
    }

    public V getKey() {
        return key;
    }

    public void setKey(V key) {
        this.key = key;
    }

    public void setValue(K value) {
        this.value = value;
    }

    private Pair(V key, K value) {
        this.key = key;
        this.value = value;
    }

    public static <V, K> Pair of(V key, K value) {
        return new Pair<>(key, value);
    }
}
