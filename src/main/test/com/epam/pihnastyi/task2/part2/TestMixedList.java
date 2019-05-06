package com.epam.pihnastyi.task2.part2;

import com.epam.pihnastyi.task1.part1.Product;
import com.epam.pihnastyi.task1.part2.ProductList;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class TestMixedList {
    private MixedList<Product> mixed;
    private Product product;
    private Product product1;
    private Product product2;
    private Product product3;
    List<Product> unmodify;
    List<Product> modify;
    List<Product> collection;

    @Before
    public void initialize() {
        product = new Product("1", "2", 1.1);
        product1 = new Product("2", "2", 1.1);
        product2 = new Product("3", "2", 1.1);
        product3 = new Product("4", "2", 1.1);
        modify = new ProductList<>();
        modify.add(product);
        modify.add(product1);
        unmodify = new ProductList<>();
        unmodify.add(product2);
        unmodify.add(product3);
        collection = new ProductList<>();
        collection.add(product);
        collection.add(product3);
        mixed = new MixedList<>(modify, unmodify);
    }

    @Test
    public void shouldReturnSize() {
        assertEquals(4, mixed.size());
    }

    @Test
    public void shouldReturnFalseIfCollectionNotIsEmpty() {
        assertFalse(mixed.isEmpty());
    }

    @Test
    public void shouldReturnTrueIfCollectionContainsObject() {
        assertTrue(mixed.contains(product));
    }

    @Test
    public void shouldRemoveObjectFromCollection() {
        mixed.remove(product1);
        assertEquals(3, mixed.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionIfYouTryRemoveObjectFromUnmodCollection() {
        mixed.remove(product3);
    }

    @Test
    public void shouldAddObjectAtTheEndOfTheList() {
        mixed.add(product3);
        assertTrue(mixed.contains(product3));
    }

    @Test
    public void shouldAddObjectByIndex() {
        mixed.add(2, product3);
        assertTrue(mixed.contains(product3));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void throwExceptionIfYouTryAddObjectInUnmodCollection() {
        mixed.add(3, product3);
    }

    @Test
    public void shouldIterator() {
        Iterator iter = mixed.iterator();
        assertTrue(iter.hasNext());
        assertEquals(product, iter.next());
        assertTrue(iter.hasNext());
        assertEquals(product1, iter.next());
    }

    @Test
    public void shouldRemoveByIndex() {
        mixed.remove(1);
        assertEquals(3, mixed.size());
    }

    @Test
    public void shouldGetObjectByIndex() {
        assertEquals(product1, mixed.get(1));
    }

    @Test
    public void shouldRemoveByObject() {
        mixed.remove(product1);
        assertEquals(3, mixed.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldThrowExceptionIfYouTryRemoveByObjectFromUnmodCollection() {
        mixed.remove(product3);
    }

    @Test
    public void shouldSetTheObjectByIndex() {
        mixed.set(1, product3);
        assertTrue(mixed.contains(product3));
        assertEquals(1, mixed.indexOf(product3));
    }

    @Test
    public void shouldReturnIndexObject() {
        assertEquals(2, mixed.indexOf(product2));
        assertEquals(0, mixed.indexOf(product));
    }

    @Test
    public void shouldReturnLastIndexObject() {
        assertEquals(2, mixed.lastIndexOf(product2));
        assertEquals(0, mixed.lastIndexOf(product));
    }

    @Test
    public void shouldReturnNegativeNumberIfObjectNotContainsInCollection() {
        Product productNotExist = new Product("11", "21", 11.1);
        assertEquals(-1, mixed.indexOf(productNotExist));
        assertEquals(-1, mixed.indexOf(new Product()));
    }

    @Test
    public void shouldAddCollectionAtTheEndOfTheList() {
        mixed.addAll(collection);
        assertEquals(6, mixed.size());
    }

    @Test
    public void shouldRemoveAllWhatHaveIncomingCollectionFromCollection() {
        collection.remove(product3);
        mixed.removeAll(collection);
        assertEquals(3, mixed.size());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void shouldNotRemoveAllWhatHaveIncomingCollectionFromCollection() {
        collection.remove(product);
        mixed.removeAll(collection);
    }
}