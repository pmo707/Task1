package com.epam.pihnastyi.part2;


import com.epam.pihnastyi.part1.Product;

import java.util.*;
import java.util.function.Predicate;

import static java.util.Arrays.copyOf;

public class ProductList<E extends Product> implements List<E> {

    private Product[] productsArray;
    private int size;

    public ProductList() {
        size = 0;
        productsArray = new Product[0];
    }

    public ProductList(int size) {
        this.size = size;
        productsArray = new Product[size];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int index = -1;
            boolean wasCall = false;

            @Override
            public boolean hasNext() {
                return index < productsArray.length - 1;
            }

            @Override
            public Object next() {
                wasCall = false;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                } else {
                    index++;
                    return (E) productsArray[index];
                }
            }
            @Override
            public void remove() {
                if (wasCall) {
                    throw new IllegalStateException();
                } else {
                    wasCall = true;
                    ProductList.this.remove(index);
                    index--;
                }
            }
        };
    }

    public Iterator iterator(Predicate predicate) {
        return new ProductIterator(predicate);
    }

    @Override
    public Object[] toArray() {
        Object[] newProductsArray;
        newProductsArray = copyOf(productsArray, productsArray.length);
        return newProductsArray;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            return (T[]) copyOf(productsArray, size, array.getClass());
        }
        System.arraycopy(productsArray, 0, array, 0, size);
        if (array.length > size) {
            array[size] = null;
        }
        return array;
    }

    @Override
    public boolean add(E element) {
        productsArray = copyOf(productsArray, productsArray.length + 1);
        productsArray[size] = element;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        productsArray = copyOf(productsArray, productsArray.length + 1);
        System.arraycopy(productsArray, index, productsArray, index + 1, size - index);
        productsArray[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection collection) {
        int previousSize = size;
        for (Object cObj : collection) {
            add((E) cObj);
        }
        return collection.size() == productsArray.length - previousSize;
    }

    @Override
    public boolean addAll(int index, Collection collection) {

        int newSize = size + collection.size();

        if (size < newSize) {
            productsArray = copyOf(productsArray, newSize);
        }
        if (index != size) {
            System.arraycopy(productsArray, index, productsArray, index + collection.size(), size - index);
        }
        System.arraycopy(collection.toArray(), 0, productsArray, index, collection.size());
        size = newSize;
        return true;


    }

    @Override
    public boolean remove(Object element) {
        int indexElementForDelete = indexOf(element);
        if (indexElementForDelete == -1) {
            return false;
        }
        remove(indexElementForDelete);
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E removeElement = (E) productsArray[index];
        System.arraycopy(productsArray, index + 1, productsArray, index, size - index - 1);
        productsArray = copyOf(productsArray, productsArray.length - 1);
        size--;
        return (E) removeElement;
    }

    @Override
    public void clear() {
        size = 0;
        productsArray = new Product[0];
    }

    @Override
    public E get(int index) {
        return (E) productsArray[index];
    }

    @Override
    public E set(int index, E element) {
        Product tmp = productsArray[index];
        productsArray[index] = element;
        return (E) tmp;
    }

    @Override
    public int indexOf(Object element) {
        if (element != null) {
            for (int i = 0; i < productsArray.length; i++) {
                if (element.equals(productsArray[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < productsArray.length; i++) {
                if (element == productsArray[i]) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        if (element != null) {
            for (int i = productsArray.length - 1; i >= 0; i--) {
                if (element.equals(productsArray[i])) {
                    return i;
                }
            }
        } else {
            for (int i = productsArray.length - 1; i >= 0; i--) {
                if (element == productsArray[i]) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean retainAll(Collection collection) {
        boolean result = false;

        for (Object element : productsArray) {
            if (!collection.contains(element)) {
                remove(element);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection collection) {
        boolean result = false;

        for (Object element : productsArray) {
            if (collection.contains(element)) {
                remove(element);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean containsAll(Collection collection) {
        for (Object cObj : collection) {
            if (!this.contains(cObj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ListIterator listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    private void checkIndex(int index) {
        if ((index > size) || (index < 0)) {
            throw new IndexOutOfBoundsException("Index is " + index + ", but size is " + size);
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < productsArray.length; i++) {
            if (i == productsArray.length - 1) {
                result.append(productsArray[i].getName());
            } else {
                result.append(productsArray[i].getName());
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    class ProductIterator<E> implements Iterator<E> {
        Predicate<E> predicate;
        int index;
        int checker;
        boolean wasCall;

        ProductIterator(Predicate<E> predicate) {
            this.predicate = predicate;
        }

        @Override
        public boolean hasNext() {
            if (checker < productsArray.length) {
                while (!predicate.test((E) productsArray[checker])) {
                    checker++;
                }
            }
            return index < productsArray.length;
        }

        @Override
        public E next() {
            wasCall = false;
            if (!hasNext()) {
                throw new NoSuchElementException();
            } else {
                index = checker;
                checker++;
                return (E) productsArray[index];
            }
        }

        @Override
        public void remove() {
            if (wasCall) {
                throw new IllegalStateException();
            } else {
                wasCall = true;
                ProductList.this.remove(index);
                index--;
            }
        }
    }
}

