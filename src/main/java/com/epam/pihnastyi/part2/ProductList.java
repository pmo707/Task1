package com.epam.pihnastyi.part2;


import com.epam.pihnastyi.part1.Product;

import java.util.*;
import java.util.function.Predicate;

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
    public boolean contains(Object o) {
        if (o != null) {
            for (int i = 0; i < productsArray.length; i++) {
                if (o.equals(productsArray[i])) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < productsArray.length; i++) {
                if (o == productsArray[i]) {
                    return true;
                }
            }
        }
        return false;
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
        };
    }


    public Iterator iterator(Predicate predicate) {
        return new ProductIterator(predicate);
    }


    @Override
    public Object[] toArray() {
        Object[] newProductsArray;
        newProductsArray = java.util.Arrays.copyOf(productsArray, productsArray.length);
        return newProductsArray;
    }


    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size)
            return (T[]) Arrays.copyOf(productsArray, size, array.getClass());
        System.arraycopy(productsArray, 0, array, 0, size);
        if (array.length > size)
            array[size] = null;
        return array;
    }


    @Override
    public boolean add(E o) {
        productsArray = java.util.Arrays.copyOf(productsArray, productsArray.length + 1);
        productsArray[size] = o;
        size++;
        return true;
    }

    @Override
    public void add(int index, E element) {
        productsArray = Arrays.copyOf(productsArray, productsArray.length + 1);
        System.arraycopy(productsArray, index, productsArray, index + 1, size - index);
        productsArray[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection c) {
        int previousSize = size;
        for (Object cObj : c) {
            add((E) cObj);
        }
        return c.size() == productsArray.length - previousSize;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        if ((index > size) || (index < 0)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", but size is " + size);
        }
        if ((c == null) || c.size() == 0) {
            return false;
        }
        int newSize = size + c.size();
        if (size < newSize) {
            productsArray = Arrays.copyOf(productsArray, newSize);
        }
        if (index != size) {
            System.arraycopy(productsArray, index, productsArray, index + c.size(), size - index);
        }
        System.arraycopy(c.toArray(), 0, productsArray, index, c.size());
        size = size() + c.size();
        size = newSize;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o != null) {
            for (int i = 0; i < productsArray.length; i++) {
                if (o.equals(productsArray[i])) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < productsArray.length; i++) {
                if (o == productsArray[i]) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {

        E removeElement;
        removeElement = (E) productsArray[index];
        if (index == size - 1) {
            productsArray = java.util.Arrays.copyOf(productsArray, productsArray.length - 1);
        } else {
            System.arraycopy(productsArray, index + 1, productsArray, index, size - index - 1);
            productsArray = java.util.Arrays.copyOf(productsArray, productsArray.length - 1);
        }
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
    public int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < productsArray.length; i++) {
                if (o.equals(productsArray[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < productsArray.length; i++) {
                if (o == productsArray[i]) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        if (o != null) {
            for (int i = productsArray.length; i >= 0; i--) {
                if (o.equals(productsArray[i])) {
                    return i;
                }
            }
        } else {
            for (int i = productsArray.length; i >= 0; i--) {
                if (o == productsArray[i]) {
                    return i;
                }
            }
        }

        return -1;
    }


    @Override
    public boolean retainAll(Collection c) {
        boolean result = false;

        for (Object myObj : productsArray) {
            if (!c.contains(myObj)) {
                remove(myObj);
                result = true;
            }

        }
        return result;
    }


    @Override
    public boolean removeAll(Collection c) {
        boolean result = false;

        for (Object myObj : productsArray) {
            if (c.contains(myObj)) {
                remove(myObj);
                result = true;
            }
        }

        return result;
    }


    @Override
    public boolean containsAll(Collection c) {
        for (Object cObj : c) {
            if (!this.contains(cObj)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
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
        protected int index;
        protected int checker;
        protected boolean wasCall;


        public ProductIterator() {
        }

        ProductIterator(Predicate<E> predicate) {
            index = 0;
            checker = 0;
            wasCall = false;
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

