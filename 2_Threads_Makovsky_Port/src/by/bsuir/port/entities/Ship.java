package by.bsuir.port.entities;

import by.bsuir.port.exception.CargoOverflowException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lenovo on 08.03.2016.
 */
public class Ship extends Thread{
    final static Logger LOG = LogManager.getRootLogger();

    private int shipNumber;
    private final int MAX_STORAGE_CAPACITY = 100;
    private ArrayList<Container> containerList = new ArrayList<>();
    private Port port;
    private Dock dock;
    public Ship(Port port){
        Random rand = new Random();
        shipNumber = rand.nextInt(1000);
        Container container;
        int containersNumber = rand.nextInt(100);
        for (int i=0; i<containersNumber; i++){
            container = new Container();
            containerList.add(container);
        }
        this.port = port;
        LOG.info("Ship number " + shipNumber + " Contains before unloading " + containerList.size());
    }

    public int getShipNumber() {
        return shipNumber;
    }
    public void run(){
        while(dock == null) {
            dock = port.requestDock();
        }
        dock.load(this);
        LOG.info("Ship number " + shipNumber + " Contains after unloading " + containerList.size());
    }
    public Container unload(){
        Container unloadedContainer = new Container();
        try{
            containerList.remove(containerList.size() - 1);
        }
        catch(ArrayIndexOutOfBoundsException e){
            unloadedContainer = null;
               LOG.warn(e);
        }
        return unloadedContainer;
    }
    public boolean load(Container loadingContainer){
        boolean result = true;
        try {
            if (containerList.size() < MAX_STORAGE_CAPACITY) {
                containerList.add(loadingContainer);
            } else {
                throw new CargoOverflowException();
            }
        } catch (CargoOverflowException e) {
            LOG.warn(e);
            result = false;
        }
        return result;
    }
    public void leave(){
        dock.leave();
    }
}
