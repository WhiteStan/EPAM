package by.bsuir.taxi.app.main;

import by.bsuir.taxi.app.station.TaxiStation;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created by Lenovo on 24.02.2016.
 */
public class FirstExercise {

    private final static Logger LOG = LogManager.getRootLogger();

    public static void main(String[] args) {
        TaxiStation taxiStation = new TaxiStation();

        LOG.info("Example1");
        taxiStation.readFile("recources/Example1.xml");
        LOG.info(taxiStation.toString());
        LOG.info("\nTotal auto price: " + taxiStation.calcAutoPrice());
        taxiStation.taxiSort();
        LOG.info("\nAfter sort: " + taxiStation.toString());
        LOG.info("\nFound car: " + taxiStation.findCarByLoad(1000, 5000));

        LOG.info("Example2");
        taxiStation.readFile("recources/Example2.xml");
        LOG.info(taxiStation.toString());

        LOG.info("Example3");
        taxiStation.readFile("recources/Example3.xml");
        LOG.info(taxiStation.toString());
    }
}
