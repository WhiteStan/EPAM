package by.bsuir.taxi.app.cars;

/**
 * Created by Lenovo on 24.02.2016.
 */
public class CargoTaxi extends Car {
    private int load = 0;

    public CargoTaxi(int price, int fuelConsumption, int load) {
        super(price, fuelConsumption);
        this.load = load;
    }

    public int getLoad() {
        return load;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLoad: " + load;
    }
}
