package com.epam.pihnastyi.task2.part1;

import com.epam.pihnastyi.part1.Product;


import java.util.*;

import static java.util.Arrays.copyOf;

public class CopyOnWriteProductList<E extends Product> implements List<E> {

    private Product[] productsArray;
    private int size;

    public CopyOnWriteProductList() {
        size = 0;
        productsArray = new Product[0];
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

    public Iterator<E> iterator() {
        return new COWPLIterator(this.toArray(productsArray));
    }

    @Override
    public Object[] toArray() {
        Object[] newProductsArray;
        newProductsArray = copyOf(productsArray, productsArray.length);
        return newProductsArray;
    }


    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size)
            return (T[]) copyOf(productsArray, size, array.getClass());
        System.arraycopy(productsArray, 0, array, 0, size);
        if (array.length > size)
            array[size] = null;
        return array;
    }

    @Override
    public boolean add(E o) {
        productsArray = copyOf(productsArray, productsArray.length + 1);
        productsArray[size] = o;
        size++;
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
    public boolean addAll(Collection c) {
        int previousSize = size;
        for (Object cObj : c) {
            add((E) cObj);
        }
        return c.size() == productsArray.length - previousSize;
    }

    @Override
    public boolean addAll(int index, Collection collection) {
//        if (index > size || index < 0) {
//            throw new IndexOutOfBoundsException("Index: " + index + ", but size is " + size);
//        }
//        if ((collection == null) || collection.size() == 0) {
//            return false;
//        }
//        int newSize = size + collection.size();
//        if (size < newSize) {
//            productsArray = copyOf(productsArray, newSize);
//        }
//        if (index != size) {
//            System.arraycopy(productsArray, index, productsArray, index + collection.size(), size - index);
//        }
//        System.arraycopy(collection.toArray(), 0, productsArray, index, collection.size());
//        size = size() + collection.size();
//        size = newSize;
//        return true;

        Product [] tmp=new Product[size-index];
        System.arraycopy(productsArray,index,tmp,0,size-index);
        System.arraycopy(collection,0,productsArray,index,collection.size());
        System.arraycopy(tmp,0,productsArray,index+collection.size(),tmp.length);

return true;


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
    public void add(int index, E element) {
        productsArray = copyOf(productsArray, productsArray.length + 1);
        System.arraycopy(productsArray, index, productsArray, index + 1, size - index);
        productsArray[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {

        E removeElement;
        removeElement = (E) productsArray[index];
        if (index == size - 1) {
            productsArray = copyOf(productsArray, productsArray.length - 1);
        } else {
            System.arraycopy(productsArray, index + 1, productsArray, index, size - index - 1);
            productsArray = copyOf(productsArray, productsArray.length - 1);
        }
        size--;
        return (E) removeElement;
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


    private class COWPLIterator implements Iterator {

        Product[] copyProducts;
        int index = -1;
        boolean wasCall = false;

        public COWPLIterator(Product[] copyProducts) {
            this.copyProducts = copyProducts;
        }

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
    }
}
