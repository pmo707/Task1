package com.epam.pihnastyi.part2;


import com.epam.pihnastyi.task1.part1.Product;
import com.epam.pihnastyi.task1.part2.ProductList;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;


public class ProductListTest {


    List<Product> list = new ProductList<>();
    List<Product> initList = new ArrayList<>(asList(new Product("product2", "color2", 2),
            new Product("product1", "color1", 1), new Product("product3",
                    "color3", 3)));
    List<Product> initListWithNull = new ArrayList<>(asList(null,
            new Product("product1", "color1", 1), null));

    @Before
    public void setUp() {
        list = new ProductList<>();
    }

    @Test
    public void returnElementsPositionInThisList() {
        fillInList(list);
        assertEquals(new Product("product1", "color1", 1), list.get(0));
    }


    @Test
    public void appendsSpecifiedElementToEndOfThisList() {
        Product product4 = new Product();
        fillInList(list);
        list.add(product4);
        assertEquals(product4, list.get(list.size() - 1));
        assertEquals(list.size(), 4);
    }

    @Test
    public void addItemsInCorrectOrderAtTheEnd() {
        assertTrue(list.add(new Product("product2", "color2", 2)));
        assertTrue(list.add(new Product("product1", "color1", 1)));
        assertTrue(list.add(new Product("product3", "color3", 3)));
        assertArrayEquals(initList.toArray(), list.toArray());
        assertEquals(list.size(), 3);
    }


    @Test
    public void removeFirstSpecifiedElementFromThisList() {
        fillInList(list);
        assertTrue(list.remove(new Product("product1", "color1", 1)));
        assertArrayEquals(asList(new Product("product2", "color2", 2), new Product("product3",
                "color3", 3)).toArray(), list.toArray());
        assertEquals(list.size(), 2);
    }

    @Test
    public void removeFirstNullFromThisList() {
        initListWithNull.remove(null);
        assertArrayEquals(asList(new Product("product1", "color1", 1),
                null).toArray(), initListWithNull.toArray());
        assertEquals(initListWithNull.size(), 2);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void removeSpecifiedElementByIWrongIndex() {
        list.remove(41);
    }

    @Test
    public void removesElementInThisListByIndex() {
        final int INDEX_FOR_DELETE = 1;
        fillInList(list);
        int previousSize = list.size();
        list.remove(INDEX_FOR_DELETE);
        assertArrayEquals(asList(new Product("product1", "color1", 1), new Product("product3",
                "color3", 3)).toArray(), list.toArray());
        assertEquals(previousSize - 1, list.size());
    }

    @Test
    public void removesElementInThisListByLastIndex() {
        final int INDEX_FOR_DELETE;
        fillInList(list);
        INDEX_FOR_DELETE = list.size() - 1;
        int previousSize = list.size();
        list.remove(INDEX_FOR_DELETE);
        assertArrayEquals(asList(new Product("product1", "color1", 1), new Product("product2",
                "color2", 2)).toArray(), list.toArray());
        assertEquals(previousSize - 1, list.size());
    }

    @Test
    public void doNotRemoveElementWhenNotExistElement() {
        fillInList(list);
        assertFalse(list.remove(new Product("productNotExist", "color1", 1)));
        assertArrayEquals(asList(new Product("product1", "color1", 1),
                new Product("product2", "color2", 2),
                new Product("product3", "color3", 3)).toArray(), list.toArray());
        assertEquals(list.size(), 3);
    }


    @Test
    public void appendsSpecifiedElementByIndex() {
        final int INDEX_FOR_ADD = 2;
        Product product5 = new Product("product5", "color5", 5);
        fillInList(list);
        int previousSize = list.size();
        list.add(INDEX_FOR_ADD, product5);
        assertEquals(product5, list.get(INDEX_FOR_ADD));
        assertEquals(previousSize + 1, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void appendsSpecifiedElementByIWrongIndex() {
        list.add(4, new Product("product2", "color2", 2));
    }


    @Test(expected = NoSuchElementException.class)
    public void runThroughCollectionWithNextWrong() {
        fillInList(list);
        Iterator<Product> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.next();
    }

    @Test
    public void runThroughCollectionByIterator() {
        Iterator<Product> itr = list.iterator();
        int iterator = 0;

        while (itr.hasNext()) {
            itr.next();
            iterator++;
        }
        assertEquals(iterator, list.size());
    }


    private void fillInList(List<Product> list) {
        list.add(new Product("product1", "color1", 1));
        list.add(new Product("product2", "color2", 2));
        list.add(new Product("product3", "color3", 3));

    }

}