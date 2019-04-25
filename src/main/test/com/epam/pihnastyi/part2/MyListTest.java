package com.epam.pihnastyi.part2;

import com.epam.pihnastyi.part1.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

public class MyListTest {

    private MyList <Product>list1;
    private MyList <Product>list2;
    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;
    private Product product6;

    @Before
    public void init() {
        list1 = new MyList();
        list1.add(product1);
        list1.add(product2);
        list1.add(product3);
        list1.add(product4);
        list2 = new MyList();
        list2.add(product1);
        list2.add(product2);
        list2.add(product3);
        list2.add(product4);


    }

    @Test
    public void testIterator() {
        Iterator<Product> itr = list1.iterator();
        int iterator = 0;

        while (itr.hasNext()) {
            itr.next();
            iterator++;
        }
        Assert.assertEquals(iterator, list1.size());
    }

    @Test
    public void testAdd() {
        list1.add(product1);
        Assert.assertEquals(5, list1.size());
    }

    @Test
    public void testRemove() {
        list1.remove(product1);
        Assert.assertEquals(3, list1.size());
    }

    @Test
    public void testGet() {

        Assert.assertEquals(product1, list1.get(0));

    }

    @Test
    public void testAddAll() {
        list1.addAll(list2);
        Assert.assertEquals(8, list1.size());
    }

    @Test
    public void testRemoveAll() {
        list1.removeAll(list1);
        Assert.assertEquals(0, list1.size());
    }
}