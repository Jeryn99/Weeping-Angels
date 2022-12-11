package mc.craig.software.angels.utils;

public class Pair<U, V> {

    private U first;

    private V second;

    public Pair(U first, V second) {

        this.first = first;
        this.second = second;
    }

    public U getFirst() {
        return first;
    }

    public void setFirst(U first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }
}