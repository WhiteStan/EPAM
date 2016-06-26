package by.bsuir.port.entities; 
 import java.util.Random; 
 import java.util.concurrent.atomic.AtomicBoolean; 
 import java.util.concurrent.locks.Lock; 
 import java.util.concurrent.locks.ReentrantLock; 
 
 public class Dock { 
 public Ship getShip() { 
 return ship; 
 
 } 
 public int getId() { 
 return id; 
 
 } 
 public boolean checkFree() { 
 isFree.set(false); 
 return !isFree.get(); 
 
 } 
 public boolean checkFreeShip() { 
 hasFreeShip = false; 
 hasFreeShipLock.unlock(); 
 return !hasFreeShip; 
 
 } 
 @Override public void load(Ship ship) { 
 this.ship = ship; 
 Ship unloadShip; 
 Container container; 
 boolean isEnd; 
 unloadShip = port.findFreeShip(); 
 unloadShip.leave(); 
 hasFreeShip = true; 
 waitCargo(); 
 
 } 
 private void waitCargo() { 
 boolean isEnd; 
 Container container; 
 
 } 
 public void leave() { 
 isFree.set(true); 
 hasFreeShip = false; 
 this.ship = null; 
 
 } 
 
 { 
 private Lock hasFreeShipLock = new ReentrantLock(); 
 private AtomicBoolean isFree = new AtomicBoolean(true); 
 
 } 
 
 private Port port; 
 private Ship ship; 
 private int id; 
 private boolean hasFreeShip = false; 
 
 public Dock(Port port) { 
 this.port = port; 
 Random rand = new Random(); 
 id = rand.nextInt(1000); 
 
 } 
 
 
} 
