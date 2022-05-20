package ua.training.model;

import java.util.Arrays;
import java.util.Iterator;

public class CustomArrayListClass<E> implements CustomListInterface <E> {

    private static final int INITIAL_CAPACITY = 10;
    private int size;
    private E[] elements;

    public CustomArrayListClass() {
        this(INITIAL_CAPACITY);
    }

    public CustomArrayListClass(int initSize) {
        if (initSize < 0) {
            throw new IllegalArgumentException("illegal size:" + initSize);
        }
        this.elements = (E[]) new Object[initSize];
    }

    @Override
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    private void ensureCapacity(int needCapacity) {
        if (needCapacity > elements.length) {
            Object[] oldElements = this.elements;
            int newSize = this.size + INITIAL_CAPACITY;
            this.elements = (E[]) new Object[newSize];
            this.elements = (E[]) Arrays.copyOf(oldElements, newSize);
        }
    }

    @Override
    public boolean add(E element) {
        if (size >= elements.length) {
            ensureCapacity(this.size + INITIAL_CAPACITY); // increase current capacity of the list
        }
        elements[this.size++] = element;
        return true;
    }

    private void checkRange(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("illegal index:" + index);
        } else if (index >= size) {
            size = index + 1;
        }
        if (index >= elements.length) {
            ensureCapacity(index + INITIAL_CAPACITY); // increase current capacity of the list
        }
    }

    @Override
    public boolean add(int index, E element) {
        checkRange(index);
        elements[index] = element;
        return true;
    }

    @Override
    public int remove(E element) {
        int removedElementIndex = -1;

        if (element == null) {
            for (int i = 0; i < this.size; i++) {
                if (this.elements[i] == null) {
                    removedElementIndex = i;
                    removeAtIndex(i);
                }
            }
        } else {
            for (int i = 0; i < this.size; i++) {
                if (element.equals(this.elements[i])) {
                    removedElementIndex = i;
                    removeAtIndex(i);
                }
            }
        }

        return removedElementIndex;
    }

    public E removeAtIndex(int index) {
        E removedElement = this.elements[index];
        int movedNumber = this.size - index - 1;
        if (movedNumber > 0) {
            System.arraycopy(this.elements, index + 1, this.elements, index, movedNumber);
        }
        this.elements[--this.size] = null;

        return removedElement;
    }

    @Override
    public E get(int index) {
        E e = this.elements[index];
        return e;
    }

    @Override
    public int indexOf(E element) {

        if (element == null) {
            for (int i = 0; i < this.elements.length; i++) {
                if (this.elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < this.elements.length; i++) {
                if (element.equals(this.elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void set(int index, E element) {
        checkRange(index);
        this.elements[index] = element;
    }

    @Override
    public boolean contains(E element) {
        if (element == null) {
            for (E e : this.elements) {
                if (e == null) {
                    return true;
                }
            }
        } else {
            for (E e : this.elements) {
                if (element.equals(e)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.elements[i] = null;
        }
        this.size = 0;
    }

    @Override
    public E[] toArray() {
        return this.elements;
    }

    @Override
    public Iterator<E> iterator() {
        return new CustomIterator<>();
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "elements=" + Arrays.toString(elements) +
                ", size=" + size +
                '}';
    }

    private class CustomIterator<E> implements Iterator<E> {

        private int current = 0;

        @Override
        public boolean hasNext() {
            return this.current < getSize();
        }

        @Override
        public E next() {
            E value = (E) elements[current++];
            return value;
        }
    }

}
