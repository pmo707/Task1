package com.epam.pihnastyi.task2.part2;


import com.epam.pihnastyi.task1.part1.Product;

import java.util.*;

public class MixedList<T extends Product> implements List<T> {
    private List<T> modifiableList;
    private List<T> unmodifiableList;


    public MixedList(List<T> modifiableList, List<T> unmodifiableList) {
        this.modifiableList = modifiableList;
        this.unmodifiableList = unmodifiableList;
    }

    @Override
    public int size() {
        return modifiableList.size() + unmodifiableList.size();
    }

    @Override
    public boolean isEmpty() {
        return modifiableList.isEmpty() && unmodifiableList.isEmpty();
    }

    @Override
    public boolean contains(Object element) {
        return modifiableList.contains(element) || unmodifiableList.contains(element);
    }

    @Override
    public Iterator<T> iterator() {
        return new MixedListIterator<>();
    }

    @Override
    public Object[] toArray() {
        Object[] products = new Object[size()];
        System.arraycopy(modifiableList.toArray(), 0, products, 0, modifiableList.size());
        System.arraycopy(unmodifiableList.toArray(), 0, products, modifiableList.size(), unmodifiableList.size());
        return products;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Object[] products = new Object[size()];
        System.arraycopy(modifiableList.toArray(), 0, products, 0, modifiableList.size());
        System.arraycopy(unmodifiableList.toArray(), 0, products, modifiableList.size(), unmodifiableList.size());
        return (T[]) products;
    }

    @Override
    public boolean add(T element) {
        return modifiableList.add(element);
    }

    @Override
    public boolean remove(Object element) {
        if (unmodifiableList.contains(element) && !modifiableList.contains(element)) {
            throw new UnsupportedOperationException("Method remove() is not supported for unmodifiable list!");
        }
        return modifiableList.remove(element);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!this.contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return modifiableList.addAll(collection);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        if (index > modifiableList.size()) {
            throw new UnsupportedOperationException("Method addAll() is not supported for unmodifiable list!");
        }
        return modifiableList.addAll(index, collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (unmodifiableList.containsAll(collection) && !modifiableList.containsAll(collection)) {
            throw new UnsupportedOperationException("Method removeAll() is not supported for unmodifiable list!");
        }
        return modifiableList.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (unmodifiableList.containsAll(collection) && !modifiableList.containsAll(collection)) {
            throw new UnsupportedOperationException("Method retainAll() is not supported for unmodifiable list!");
        }
        return modifiableList.retainAll(collection);
    }

    @Override
    public void clear() {
        if (unmodifiableList.size() > 0) {
            throw new UnsupportedOperationException("Method clear() is not supported for unmodifiable list while it's size is greater that zero!");
        }
        modifiableList.clear();
    }

    @Override
    public T get(int index) {
        if (index < modifiableList.size()) {
            return modifiableList.get(index);
        }
        return unmodifiableList.get(index - modifiableList.size());
    }

    @Override
    public T set(int index, T element) {
        if (index < modifiableList.size()) {
            return modifiableList.set(index, element);
        }
        throw new UnsupportedOperationException("Method set() is not supported for unmodifiable list!");
    }

    @Override
    public void add(int index, T element) {
        if (index <= modifiableList.size()) {
            modifiableList.add(index, element);
            return;
        }
        throw new UnsupportedOperationException("Method add() is not supported for unmodifiable list!");
    }

    @Override
    public T remove(int index) {
        if (index < modifiableList.size()) {
            return modifiableList.remove(index);
        }
        throw new UnsupportedOperationException("Method remove() is not supported for unmodifiable list!");
    }

    @Override
    public int indexOf(Object element) {
        if (modifiableList.contains(element)) {
            return modifiableList.indexOf(element);
        }
        return modifiableList.size() + unmodifiableList.indexOf(element);
    }

    @Override
    public int lastIndexOf(Object element) {
        if (unmodifiableList.contains(element)) {
            return modifiableList.size() + unmodifiableList.lastIndexOf(element);
        }
        return modifiableList.lastIndexOf(element);
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Method listIterator isn't supported");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Method listIterator isn't supported");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Method listIterator isn't supported");
    }

    private class MixedListIterator<E> implements Iterator<E> {
        private int index;

        MixedListIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size();
        }

        @Override
        public E next() {
            if (index >= size()) {
                throw new NoSuchElementException("There is no more elements in collection!");
            }
            return (E) get(index++);
        }
    }

}
