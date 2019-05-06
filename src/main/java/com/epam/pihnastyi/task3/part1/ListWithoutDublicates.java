package com.epam.pihnastyi.task3.part1;


import com.epam.pihnastyi.task1.part1.Product;

import java.util.*;

public class ListWithoutDublicates<E extends Product> extends ArrayList<E> {

    @Override
    public boolean add(E element) {
        if (contains(element)) {
            throw new IllegalArgumentException ("Element is already exists in list");
        }
        return super.add(element);
    }

    @Override
    public E set(int index, E element) {
        int indexInList = indexOf(element);
        if ((indexInList != -1) && (indexInList != index)) {
            throw new IllegalArgumentException ("Element is already existInList in this list");
        }
        return super.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        if (contains(element)) {
            throw new IllegalArgumentException ("Element is already exists in this list");
        }
        super.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (existDuplicates(collection)) {
            throw new IllegalArgumentException ("Some elements is already exist in this list");
        }
        return super.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        if (existDuplicates(collection)) {
            throw new IllegalArgumentException ("Some elements is already exist in this list");
        }
        return super.addAll(index, collection);
    }

    private boolean existDuplicates(Collection<? extends E> collection) {

       //TODO contains all collection
        return true;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException ();
    }
}