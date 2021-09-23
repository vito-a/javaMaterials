package ua.training;

import java.util.Collection;
import java.util.Iterator;

public class SimpleArray<E> implements Simple<E> {
    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public void addAll(Collection<E> collection) {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void update(int index, E e) {

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }
}
