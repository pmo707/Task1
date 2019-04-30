package com.epam.pihnastyi.task2.part1;

import com.epam.pihnastyi.part1.Product;
import com.epam.pihnastyi.part2.ProductList;

import java.util.Iterator;

public class CopyOnWriteProductList  extends ProductList {

    public Iterator iterator() {
        return new COWPLIterator(this.toArray());
    }

    private class COWPLIterator implements Iterator {

        Product [] copyProducts;
        int insex;

        public COWPLIterator(Object[] toArray) {

        }

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}
