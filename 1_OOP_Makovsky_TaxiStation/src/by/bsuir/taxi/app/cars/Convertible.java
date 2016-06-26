package by.bsuir.taxi.app.cars;

/**
 * Created by Lenovo on 24.02.2016.
 */
public class Convertible extends PassengerTaxi {
    private final boolean OPENED = true;
    private final boolean CLOSED = false;

    private boolean state = CLOSED;

    public Convertible(int price, int fuelConsumption, int seats) {
        super(price, fuelConsumption, seats);
    }

    public void changeMode() {
        state = !state;
    }

    @Override
    public String toString() {
        return super.toString() + "\nState: " + state;
    }
}
