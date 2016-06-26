package by.bsuir.taxi.app.station;

import by.bsuir.taxi.app.cars.Car;
import by.bsuir.taxi.app.cars.CargoTaxi;
import by.bsuir.taxi.app.cars.PassengerTaxi;
import by.bsuir.taxi.utils.TaxiComparator;
import by.bsuir.taxi.utils.TaxiParser;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Lenovo on 24.02.2016.
 */
public class TaxiStation {
    private ArrayList<Car> carsList = new ArrayList<Car>();

    public void readFile(String fileName) {
        TaxiParser taxiParser = new TaxiParser();
        carsList = taxiParser.parseFile(fileName);
    }

    public int calcAutoPrice() {
        int sum = 0;
        for (Car car : carsList) {
            sum += car.getPrice();
        }
        return sum;
    }

    public void taxiSort() {
        Collections.sort(carsList, new TaxiComparator());
    }

    public Car findCarByLoad(int lowLoad, int highLoad) {
        for (Car car : carsList) {
            if (car instanceof CargoTaxi)
                if (((CargoTaxi) car).getLoad() <= highLoad && ((CargoTaxi) car).getLoad() >= lowLoad) {
                    return car;
                }
        }
        return null;
    }

    public Car findCarBySeats(int lowSeats, int highSeats) {
        for (Car car : carsList) {
            if (car instanceof PassengerTaxi)
                if (((PassengerTaxi) car).getSeats() <= highSeats && ((PassengerTaxi) car).getSeats() >= lowSeats) {
                    return car;
                }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = new String();
        for (Car car : carsList) {
            result += car.toString();
        }
        return result;
    }
}
