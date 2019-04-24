package main.java.com.epam.pihnastyi.part1;

import java.util.Objects;

public class Pizza extends Food {

    private double size;
    private double weight;

    public Pizza() {
    }

    public Pizza(String name, String color, double price, boolean isHealthy,
                 String country, double size, double weight) {
        super(name, color, price, isHealthy, country);
        this.size = size;
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pizza pizza = (Pizza) o;
        return Double.compare(pizza.size, size) == 0 &&
                Double.compare(pizza.weight, weight) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), size, weight);
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "size=" + size +
                ", weight=" + weight +
                '}';
    }
}
