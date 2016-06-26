package by.bsuir.taxi.app.cars;

/**
 * Created by Lenovo on 24.02.2016.
 */
public class PassengerTaxi extends Car {
    private int seats = 0;
    public PassengerTaxi(int price, int fuelConsumption, int seats) {
        super(price, fuelConsumption);
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }
    @Override
    public String toString(){
        return super.toString() + "\nSeats " + seats;
    }
}
