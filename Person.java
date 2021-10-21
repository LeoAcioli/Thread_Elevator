import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.Random;


public class Person implements Runnable{

    public static Semaphore full = new Semaphore(-6, true); //When 7 person releases it, it will be 1. Elevator can then acquire.
    
    public int floor;
    public int personNumber;
    
    Person(int personNumber){
      this.personNumber = personNumber;
      Random r = new Random();
      int low = 2;
      int high = 10;
      this.floor = r.nextInt(high - low) + low;
    }
    
    @Override
    public void run() {
    
      try {//ENTER ELEVATOR
          Elevator.maxCapacity.acquire();
          Elevator.doorStatus.acquire();
          Elevator.onFirstFloor.acquire();
          System.out.println("Person " +this.personNumber+" enters elevator to go to floor "+this.floor);
          Thread.sleep(200);
          Elevator.inElevator.add(this);
          Elevator.doorStatus.release();
          Elevator.onFirstFloor.release();
        }  catch (InterruptedException e) {
         }
       full.release();//release each time a passanger gets here, 7 should get here. on the 7th, semaphore will be 1
       }
}