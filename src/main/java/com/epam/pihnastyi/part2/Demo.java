package com.epam.pihnastyi.part2;

import com.epam.pihnastyi.part1.Product;


public class Demo {
    public static void main(String[] args) {
        //created product

        Product product0 = new Product("0", "0", 0);
        Product product1 = new Product("1", "1", 1);
        Product product2 = new Product("2", "2", 2);
        Product product3 = new Product("3", "3", 3);
        Product product4 = new Product("4", "4", 4);
        Product product5 = new Product("5", "5", 5);
        Product productColor1 = new Product("White", "5", 5);
        Product productColor2 = new Product("Black", "5", 5);
        Product productColor3 = new Product("Red", "5", 5);
        Product productColor4 = new Product("Green", "5", 5);


        MyList<Product> myList = new MyList<>();
        MyList<Product> myList2 = new MyList<>();
        MyList<Product> listFirst = new MyList<>();
        MyList<Product> listSecond = new MyList<>();


        listFirst.add(productColor1);
        listFirst.add(productColor2);
        listFirst.add(productColor3);

        listSecond.add(productColor4);
        listSecond.add(productColor3);
        listSecond.add(productColor1);
        //MyList add
        myList.add(product0);
        myList.add(product1);
        myList.add(product2);

        //MyList2 add
        myList2.add(product3);
        myList2.add(product4);
        myList2.add(product5);
        System.out.println("----------------------------------------------------------");
        System.out.println("MyList");
        System.out.println(myList);
        System.out.println("MyList2");
        System.out.println(myList2);
        System.out.println("----------------------------------------------------------");
        myList.remove(0);
        System.out.println("--Removed MyList[0]");
        System.out.println(myList);
        myList.remove(1);
        System.out.println("--Removed MyList[1]");
        System.out.println(myList);
        System.out.println("----------------------------------------------------------");
        System.out.println("Added to myList 2");
        myList.add(product2);
        System.out.println(myList);
        System.out.println("----------------------------------------------------------");
        myList2.addAll(myList);
        System.out.println("--addAll to myList2");
        System.out.println(myList2);


        System.out.println("----------------------------------------------------------");
        System.out.println("MyList");
        System.out.println(myList);
        System.out.println("MyList2");
        System.out.println(myList2);
        System.out.println("----------------------------------------------------------");

        myList2.addAll(0, myList);
        System.out.println(myList2);
        System.out.println("----------------------------------------------------------");

//        listFirst.retainAll(listSecond);
//        System.out.println(listFirst);
        System.out.println("----------------------------------------------------------");
        listFirst.removeAll(listSecond);
        System.out.println(listFirst);
        System.out.println("----------------------------------------------------------");

    }
}
