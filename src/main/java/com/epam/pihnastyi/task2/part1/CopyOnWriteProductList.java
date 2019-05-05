package com.epam.pihnastyi.task2.part1;


import com.epam.pihnastyi.task1.part1.Product;

import java.util.*;
import java.util.function.Predicate;

public class CopyOnWriteProductList<T extends Product> implements List<T> {

    private static final int DEFAULT_ENSURE_CAPACITY = 10;

    private boolean isIteratorCreated;
    private Object[] products;
    private int currentSize;

    public CopyOnWriteProductList(int initialCapacity) {
        this.products = new Object[initialCapacity];
        this.isIteratorCreated = false;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(products, currentSize);
    }

    private Object[] getNewArray() {
        return Arrays.copyOf(products, products.length);
    }

    private void resetIsIteratorCreated() {
        if (isIteratorCreated) {
            products = getNewArray();
            isIteratorCreated = false;
        }
    }

    @Override
    public boolean add(T element) {
        add(currentSize, element);
        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        ensureCapacityOrResetIterator(currentSize);
        shiftElementsToTheRight(index, 1);
        products[index] = element;
        currentSize++;
    }

    private void ensureCapacityOrResetIterator(int checkSize) {
        if (checkSize >= products.length) {
            ensureCapacity(DEFAULT_ENSURE_CAPACITY);
            isIteratorCreated = false;
        } else {
            resetIsIteratorCreated();
        }
    }

    private void ensureCapacity(int ensureIndex) {
        int arrayNewSize = products.length + ensureIndex;
        products = Arrays.copyOf(products, arrayNewSize);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        resetIsIteratorCreated();
        T oldValue = (T) products[index];
        shiftElementsToTheLeft(index);
        return oldValue;
    }

    @Override
    public boolean remove(Object obj) {
        int indexToRemove = indexOf(obj);
        if (indexToRemove >= 0) {
            remove(indexToRemove);
            return true;
        }
        return false;
    }

    private void shiftElementsToTheRight(int index, int numToShift) {
        int numToMove = currentSize - index;
        if (numToMove > 0) {
            System.arraycopy(products, index, products, index + numToShift, currentSize - index);
        }
    }

    private void shiftElementsToTheLeft(int index) {
        int numToRemove = currentSize - index - 1;
        if (numToRemove > 0) {
            System.arraycopy(products, index + 1, products, index, numToRemove);
        }
        products[--currentSize] = null;
    }

    @Override
    public boolean addAll(Collection collection) {
        return addAll(currentSize, collection);
    }

    @Override
    public boolean addAll(int index, Collection collection) {
        checkIndexForAdd(index);
        Object[] tempArray = collection.toArray();
        int tempLength = tempArray.length;
        int checkSize = tempLength + currentSize;
        ensureCapacityOrResetIterator(checkSize);
        shiftElementsToTheRight(index, tempLength);
        System.arraycopy(tempArray, 0, products, index, tempLength);
        currentSize += tempLength;
        return collection.size()!=0;
    }


    @Override
    public void clear() {
        if (isIteratorCreated) {
            isIteratorCreated = false;
        }
        products = Arrays.copyOf(products, DEFAULT_ENSURE_CAPACITY);
        Arrays.fill(products, null);
        currentSize = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) products[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        resetIsIteratorCreated();
        T oldElement = (T) products[index];
        products[index] = element;
        return oldElement;
    }

    @Override
    public int indexOf(Object obj) {
        for (int i = 0; i < currentSize; i++) {
            if (Objects.equals(obj, products[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object obj) {
        for (int i = currentSize - 1; i >= 0; i--) {
            if (Objects.equals(obj, products[i])) {
                return i;
            }
        }
        return -1;
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
        throw new UnsupportedOperationException("Method subList isn't supported");
    }

    @Override
    public boolean retainAll(Collection collection) {
        boolean isRemoved = false;
        resetIsIteratorCreated();
        for (int i = currentSize - 1; i >= 0; i--) {
            if (!collection.contains(products[i])) {
                this.remove(products[i]);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean removeAll(Collection collection) {
        boolean isRemoved = false;
        resetIsIteratorCreated();
        for (int i = currentSize - 1; i >= 0; i--) {
            if (collection.contains(products[i])) {
                this.remove(products[i]);
                isRemoved = true;
            }
        }
        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection collection) {
        Object[] array = collection.toArray();
        for (Object object : array) {
            if (!this.contains(object)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public <E> E[] toArray(E[] array) {
        return (E[]) Arrays.copyOf(products, currentSize, array.getClass());
    }

    private void checkIndexForAdd(int index) {
        if (index > currentSize || index < 0) {
            throw new IndexOutOfBoundsException("Index= " + index + ", list currentSize= " + currentSize);
        }
    }

    private void checkIndex(int index) {
        if (index >= currentSize || index < 0) {
            throw new IndexOutOfBoundsException("Index= " + index + ", list currentSize= " + currentSize);
        }
    }

    @Override
    public ProductIterator<T> iterator() {
        this.isIteratorCreated = true;
        return new ProductIterator<>();
    }

    public ProductIterator<T> iterator(Predicate<T> predicate) {
        this.isIteratorCreated = true;
        return new ProductIterator<>(predicate, getNewArray(), 0);
    }

    private class ProductIterator<E extends T> implements Iterator<E> {
        private final Predicate<E> DEFAULT_PREDICATE = transport -> true;

        private Predicate<E> predicate;
        private Object[] snapshot;
        private int currentIndex;
        private int currentCopySize;

        ProductIterator() {
            this.predicate = DEFAULT_PREDICATE;
            this.snapshot = getNewArray();
            this.currentIndex = 0;
            this.currentCopySize = currentSize;

        }

        ProductIterator(Predicate<E> predicate, Object[] snapshot, int currentIndex) {
            this.predicate = predicate;
            this.snapshot = snapshot;
            this.currentIndex = currentIndex;
            this.currentCopySize = currentSize;
        }

        @Override
        public boolean hasNext() {
            for (int i = currentIndex; i < currentCopySize; i++) {
                if (predicate.test((E) snapshot[i])) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public E next() {
            for (int i = currentIndex; i < currentCopySize; i++) {
                if (predicate.test((E) snapshot[i])) {
                    currentIndex = i + 1;
                    return (E) snapshot[i];
                }
            }
            throw new NoSuchElementException("Iterator has not next element");
        }
    }
}