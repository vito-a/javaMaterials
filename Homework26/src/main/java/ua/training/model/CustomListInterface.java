/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.model;

import javax.persistence.Entity;

@Entity
public interface CustomListInterface<E> extends Iterable<E> {
    boolean isEmpty();

    int getSize();

    boolean add(E element);

    boolean add(int index, E element);

    int remove(E element);

    E get(int index);

    int indexOf(E element);

    void set(int index, E element);

    boolean contains(E element);

    void clear();

    E[] toArray();
}
