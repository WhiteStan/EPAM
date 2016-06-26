by.bsuir.port.entities; package 
 java.util.Random; import 
 java.util.concurrent.atomic.AtomicBoolean; import 
 java.util.concurrent.locks.Lock; import 
 java.util.concurrent.locks.ReentrantLock; import 
 
 class Dock { 
 Ship getShip() { 
 ship; return 
 
 } 
 int getId() { 
 id; return 
 
 } 
 boolean checkFree() { 
 isFree.set(false); 
 !isFree.get(); return 
 
 } 
 boolean checkFreeShip() { 
 false; = hasFreeShip 
 hasFreeShipLock.unlock(); 
 !hasFreeShip; return 
 
 } 
 @Override void load(Ship ship) { 
 ship; = this.ship 
 unloadShip; Ship 
 container; Container 
 isEnd; boolean 
 port.findFreeShip(); = unloadShip 
 unloadShip.leave(); 
 true; = hasFreeShip 
 waitCargo(); 
 
 } 
 private void waitCargo() { 
 isEnd; boolean 
 container; Container 
 
 } 
 void leave() { 
 isFree.set(true); 
 false; = hasFreeShip 
 null; = this.ship 
 
 } 
 
 { 
 ReentrantLock(); Lock hasFreeShipLock = new private 
 AtomicBoolean(true); AtomicBoolean isFree = new private 
 
 } 
 
 port; Port private 
 ship; Ship private 
 id; int private 
 false; boolean hasFreeShip = private 
 
 Dock(Port port) { 
 port; = this.port 
 Random(); rand = new Random 
 rand.nextInt(1000); = id 
 
 } 
 
 
} 
