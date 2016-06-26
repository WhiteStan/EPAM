package by.bsuir.taxi.utils;

import by.bsuir.taxi.app.cars.Car;
import by.bsuir.taxi.app.cars.CargoTaxi;
import by.bsuir.taxi.app.cars.Convertible;
import by.bsuir.taxi.app.cars.PassengerTaxi;

/**
 * Created by Lenovo on 28.02.2016.
 */
public class TaxiFactory {
    private Car car = null;

    public Car makeTaxi(String type, int args[]) {
        switch (type) {
            case "PASSENGERTAXI":
                return car = new PassengerTaxi(args[0], args[1], args[2]);
            case "CARGOTAXI":
                return car = new CargoTaxi(args[0], args[1], args[2]);
            case "CONVERTIBLE":
                return car = new Convertible(args[0], args[1], args[2]);
            default:
                return car;
        }
    }
}
