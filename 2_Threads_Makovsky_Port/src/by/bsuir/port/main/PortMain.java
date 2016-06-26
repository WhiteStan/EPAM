package by.bsuir.port.main;

import by.bsuir.port.entities.Port;
import by.bsuir.port.entities.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lenovo on 08.03.2016.
 */
public class PortMain {
    private final static Logger LOG = LogManager.getRootLogger();
    private final static int NUMBER_OF_SHIPS = 10;
    public static void main(String[] args) {
        Port port = Port.getInstance();
        List<Ship> threads = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_SHIPS; i++) {
            Ship ship = new Ship(port);
            ship.start();
            threads.add(ship);
        }
        try {
            for (Ship ship : threads) {
                ship.join();
            }
        } catch (InterruptedException e) {
            LOG.warn(e);
        }
        LOG.info("Storage " + port.getStorageSize());
    }
}
