package by.bsuir.port.entities;

import by.bsuir.port.exception.CargoOverflowException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lenovo on 08.03.2016.
 */
public class Port {
    private final static Logger LOG = LogManager.getRootLogger();

    private final int MAX_STORAGE_CAPACITY = 200;
    private final int NUMBER_OF_DOCKS = 5;

    private static ArrayList<Container> storage = new ArrayList<>();
    private static ArrayList<Dock> docks = new ArrayList<>();
    private static Lock singletonLock = new ReentrantLock();
    private Lock docksLock = new ReentrantLock();
    private Lock storageLock = new ReentrantLock();
    private static Port instance = null;
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    private Port() {
        Dock dock;
        for (int i = 0; i < NUMBER_OF_DOCKS; i++) {
            dock = new Dock(this);
            docks.add(dock);
        }
    }

    public static Port getInstance() {
        try {
            if (!isCreated.get()) {
                singletonLock.lock();
                if (!isCreated.get()) {
                    instance = new Port();
                    isCreated.set(true);
                }
            }
        }
        finally {
            singletonLock.unlock();
        }
        return instance;
    }

    public Dock requestDock() {
        Dock receivingDock = null;
        boolean isReceived = false;
        docksLock.lock();
        for (Dock dock : docks) {
            if (dock.checkFree() && !isReceived) {
                receivingDock = dock;
                isReceived = true;
                LOG.info("Received dock " + dock.getId());
            }
        }
        docksLock.unlock();
        return receivingDock;
    }

    public Ship findFreeShip() {
        Ship ship = null;
        boolean isFree = false;
        docksLock.lock();
        for (Dock dock : docks) {
            if (dock.checkFreeShip() && !isFree) {
                ship = dock.getShip();
                isFree = true;
            }
        }
        docksLock.unlock();
        return ship;
    }

    public boolean load(Container loadingContainer) {
        storageLock.lock();
        boolean result = true;
        try {
            if (storage.size() < MAX_STORAGE_CAPACITY) {
                storage.add(loadingContainer);
            } else {
                throw new CargoOverflowException();
            }
        } catch (CargoOverflowException e) {
            LOG.warn(e);
            result = false;
        } finally {
            storageLock.unlock();
        }
        return result;
    }

    public Container unload() {
        storageLock.lock();
        Container unloadedContainer = new Container();
        try {
            storage.remove(storage.size() - 1);
        } catch (ArrayIndexOutOfBoundsException e) {
            unloadedContainer = null;
            LOG.warn(e);
        } finally {
            storageLock.unlock();
        }
        return unloadedContainer;
    }

    public boolean isStorageFull() {
        boolean isStorageFull;
        storageLock.lock();
        isStorageFull = !storage.isEmpty();
        storageLock.unlock();
        return isStorageFull;
    }

    public int getStorageSize() {
        return storage.size();
    }
}
