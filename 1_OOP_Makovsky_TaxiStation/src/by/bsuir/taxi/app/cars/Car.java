package by.bsuir.taxi.app.cars;

/**
 * Created by Lenovo on 24.02.2016.
 */
public abstract class Car {
    private int price = 0;
    private int fuelConsumption = 0;

    public Car(int price, int fuelConsumption) {
        this.price = price;
        this.fuelConsumption = fuelConsumption;
    }

    public int getFuelConsumption() {
        return fuelConsumption;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  "\nName: " + getClass().getSimpleName()
                + "\nPrice: " + price
                + "\nFuel consumption: " + fuelConsumption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (price != car.price) return false;
        return fuelConsumption == car.fuelConsumption;

    }

    @Override
    public int hashCode() {
        return (this.price + this.fuelConsumption) * 23 / 11;
    }
}
