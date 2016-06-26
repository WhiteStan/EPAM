package by.bsuir.port.entities;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lenovo on 08.03.2016.
 */
public class Dock {
    private Port port;
    private Ship ship;
    private int id;
    private AtomicBoolean isFree = new AtomicBoolean(true);
    private boolean hasFreeShip = false;
    private Lock hasFreeShipLock = new ReentrantLock();

    public Dock(Port port) {
        this.port = port;
        Random rand = new Random();
        id = rand.nextInt(1000);
    }

    public Ship getShip() {
        return ship;
    }

    public int getId() {
        return id;
    }

    public boolean checkFree() {
        if (isFree.get()) {
            isFree.set(false);
            return !isFree.get();
        } else {
            return isFree.get();
        }
    }

    public boolean checkFreeShip() {
        if (!hasFreeShipLock.tryLock()) {
            return false;
        }
        if (hasFreeShip) {
            hasFreeShip = false;
            hasFreeShipLock.unlock();
            return !hasFreeShip;
        } else {
            hasFreeShipLock.unlock();
            return hasFreeShip;
        }
    }

    public void load(Ship ship) {
        this.ship = ship;
        Ship unloadShip;
        Container container;
        boolean isEnd;
        unloadShip = port.findFreeShip();
        if (unloadShip != null) {
            do {
                container = ship.unload();
                if (container != null) {
                    isEnd = unloadShip.load(container);
                    if(!isEnd){
                        ship.load(container);
                    }
                } else {
                    isEnd = false;
                }
            } while (isEnd);
            unloadShip.leave();
        } else {
            do {
                container = ship.unload();
                if (container != null) {
                    isEnd = port.load(container);
                    if(!isEnd){
                        ship.load(container);
                    }
                } else {
                    isEnd = false;
                }
            } while (isEnd);
        }
        hasFreeShip = true;
        waitCargo();
    }

    private void waitCargo() {
        boolean isEnd;
        Container container;
        while (hasFreeShip) {
            hasFreeShipLock.lock();
            if (port.isStorageFull()) {
                hasFreeShip = false;
                do {
                    container = port.unload();
                    if (container != null) {
                        isEnd = ship.load(container);
                        if(!isEnd){
                            ship.load(container);
                        }
                    } else {
                        isEnd = false;
                    }
                } while (isEnd);
                leave();
            }
            hasFreeShipLock.unlock();
        }
    }

    public void leave() {
        isFree.set(true);
        hasFreeShip = false;
        this.ship = null;
    }
}

