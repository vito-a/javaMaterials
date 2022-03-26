/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.training.model;

import javax.persistence.Entity;

@Entity
public interface CustomMapInterface <K, V> {
    int size();
    Boolean isEmpty();

    V get(K key);
    void put(K key, V value);

    interface CustomEntryInterface <K, V> {
        int hashValue = 0;
        K getKey();
        V getValue();
    }
}
