package by.bsuir.taxi.utils;

import by.bsuir.taxi.app.cars.Car;

import java.util.Comparator;

/**
 * Created by Lenovo on 28.02.2016.
 */
public class TaxiComparator implements Comparator<Car> {
    @Override
    public int compare(Car a, Car b) {
        return a.getFuelConsumption() < b.getFuelConsumption() ? -1 : 1;
    }
}
