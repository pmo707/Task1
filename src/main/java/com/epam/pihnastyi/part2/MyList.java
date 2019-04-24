package com.epam.pihnastyi.part2;



import com.epam.pihnastyi.part1.Product;

import java.util.*;

public class MyList<E extends Product> implements List<E> {

    private Product[] myElements;
    private int size;

    public MyList() {
        size = 0;
        myElements = new Product[0];
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
            for (int i = 0; i < myElements.length; i++) {
                if (o.equals(myElements[i])) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < myElements.length; i++) {
                if (o == myElements[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return new myIterator();
    }


    @Override
    public Object[] toArray() {
        Object[] newMyElements;
        newMyElements = java.util.Arrays.copyOf(myElements, myElements.length);
        return newMyElements;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E o) {
        myElements = java.util.Arrays.copyOf(myElements, myElements.length + 1);
        myElements[size] = o;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o != null) {
            for (int i = 0; i < myElements.length; i++) {
                if (o.equals(myElements[i])) {
                    remove(i);
                    return true;
                }
            }
        } else {
            for (int i = 0; i < myElements.length; i++) {
                if (o == myElements[i]) {
                    remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        for (Object cObj : c) {
            add((E) cObj);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        Collections.reverse((List) c);
        for (Object cObj : c) {
            add((E) cObj);
        }
        return true;
    }

    @Override
    public void clear() {
        size = 0;
        myElements = new Product[0];
    }

    @Override
    public E get(int index) {

        return (E) myElements[index];
    }

    @Override
    public E set(int index, E element) {
        Product tmp = myElements[index];
        myElements[index] = element;
        return (E) tmp;

    }


    @Override
    public void add(int index, E element) {
        myElements = Arrays.copyOf(myElements, myElements.length + 1);
        System.arraycopy(myElements, index, myElements, index + 1, size - index);
        myElements[index] = element;
        size++;
    }

    @Override
    public E remove(int index) {

        E removeElement;
        removeElement = (E) myElements[index];
        if (index == size - 1) {
            myElements = java.util.Arrays.copyOf(myElements, myElements.length - 1);
        } else {
            System.arraycopy(myElements, index + 1, myElements, index, size - index - 1);
            myElements = java.util.Arrays.copyOf(myElements, myElements.length - 1);
        }
        size--;
        return (E) removeElement;
    }


    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < myElements.length; i++) {
            if (o.equals(myElements[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {

        for (int i = myElements.length; i >= 0; i--) {
            if (o.equals(myElements[i])) {
                return i;
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
        //TODO check this method
        boolean result = false;
        for (int i = 0; i < myElements.length; i++) {
            for (Object cObj : c) {
                if (!cObj.equals(myElements[i])) {
                    remove(cObj);
                    result=true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection c) { //TODO check this method
        boolean result = false;
        for (int i = 0; i < myElements.length; i++) {
            for (Object cObj : c) {
                if (cObj.equals(myElements[i])) {
                    remove(cObj);
                    result=true;
                }
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


    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[");
        for (int i = 0; i < myElements.length; i++) {
            if (i == myElements.length - 1) {
                result.append(myElements[i].getName());
            } else {
                result.append(myElements[i].getName());
                result.append(", ");
            }
        }
        result.append("]");
        return result.toString();
    }

    class myIterator implements Iterator {
        protected int index;


        myIterator() {
            index = -1;
            wasCall = false;
        }

        protected boolean wasCall;

        @Override
        public boolean hasNext() {
            return index < myElements.length - 1;
        }

        @Override
        public Object next() {
            wasCall = false;
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            } else {
                index++;
                return myElements[index];
            }
        }

        @Override
        public void remove() {
            if (wasCall) {
                throw new IllegalStateException();
            } else {
                wasCall = true;
                MyList.this.remove(index);
                index--;
            }

        }
    }
}

