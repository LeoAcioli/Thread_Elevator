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
          Thread.sleep(100);
          Elevator.inElevator.add(this); //Add person to a list of people CURRENTLY in elevator
          Elevator.doorStatus.release();
          Elevator.onFirstFloor.release();
        }  catch (InterruptedException e) {e.printStackTrace();}
       full.release();//release each time a passanger gets here, 7 should get here. on the 7th, semaphore will be 1

      try{Elevator.done.acquire();} catch(InterruptedException e){e.printStackTrace();} //wait here until group is done
      Elevator.done.release();//Each thread in the group will acquire and release, to then release MaxCapacity
      Elevator.maxCapacity.release();//free max Capacity 7 times.
        
      }
}