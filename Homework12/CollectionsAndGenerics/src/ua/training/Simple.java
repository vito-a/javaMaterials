package ua.training;

import java.util.Collection;

public interface Simple<E> extends Iterable<E> {
    boolean add(E e);
    void addAll(Collection<E> collection);
    E get(int index);
    int size();
    void update(int index, E e);
}
