package com.epam.pihnastyi.part1;


import java.util.Objects;

public class Drink extends Product {

    private boolean withSugar;
    private boolean withGas;
    private String PackagingType;

    public Drink() {
    }

    public Drink(String name, String color, double price, boolean withSugar, boolean withGas) {
        super(name, color, price);
        this.withSugar = withSugar;
        this.withGas = withGas;
    }

    public boolean isWithSugar() {
        return withSugar;
    }

    public void setWithSugar(boolean withSugar) {
        this.withSugar = withSugar;
    }

    public boolean isWithGas() {
        return withGas;
    }

    public void setWithGas(boolean withGas) {
        this.withGas = withGas;
    }

    public String getPackagingType() {
        return PackagingType;
    }

    public void setPackagingType(String packagingType) {
        PackagingType = packagingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Drink drink = (Drink) o;
        return withSugar == drink.withSugar &&
                withGas == drink.withGas &&
                Objects.equals(PackagingType, drink.PackagingType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), withSugar, withGas, PackagingType);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "withSugar=" + withSugar +
                ", withGas=" + withGas +
                ", PackagingType='" + PackagingType + '\'' +
                '}';
    }
}
