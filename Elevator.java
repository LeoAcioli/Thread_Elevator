import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.ArrayList;

public class Elevator implements Runnable{

    public static Semaphore maxCapacity = new Semaphore(7, true); //Can only fit 7 people inside elevator
    public static Semaphore doorStatus = new Semaphore(0,true); //Binary semaphore, 1 for open , 0 for closed
    public static Semaphore onFirstFloor = new Semaphore(0,true); //Binary semaphore, 0 for not on first floor, 1 if on first floor.
    public static Semaphore done = new Semaphore(-6,true);
    
    public static ArrayList<Person> inElevator = new ArrayList<>();

    
    
    @Override
    public void run() {
        try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}   
            
    for (int y=0;y<=6;y++){ //7 groups!
    for (int x=1;x<=10;x++){
    
        switch(x) {
        
          case 1: //First floor. Open door of elevator and let people in.
            onFirstFloor.release();//Elevator on first floor. Signal that
            System.out.println("Elevator door opens at floor 1");
            try {Thread.sleep(1);}  catch (InterruptedException e) {e.printStackTrace();} 
            doorStatus.release();//open elevator door
            try {
                Person.full.acquire(); //wait for everybody to get in before closing door //WORKS!
                doorStatus.acquire(); //close elevator door
                onFirstFloor.acquire();} //Not on first floor anymore
            catch (InterruptedException e){e.printStackTrace();}
            System.out.println("Elevator door closes"); //If you got here means that the door is closed.
            try {Thread.sleep(1);}  catch (InterruptedException e) {e.printStackTrace();}

            break;
          
          case 2:
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
            
          case 3:
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
            
          case 4:
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
            
          case 5:
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
            
          case 6:
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
            
          case 7:
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
            
          case 8: 
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
          
          case 9:    
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
          
          case 10:
            for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
              if (inElevator.get(i).floor ==x){
                getOffElevator(x);
              }
            }
            break;
                      
        }//end switch
    }//end inner for
    System.out.println("---------------- END "+(y+1)+" ROUND -------------------");
    for (int z=0;z<=6;z++){
      maxCapacity.release();
      Person.full.release();
    }
  }//end outer for
    }//end run
    
    public void getOffElevator(int floor){

      System.out.println("Elevator door opening at floor "+floor);
      try {Thread.sleep(1);}  catch (InterruptedException e) {e.printStackTrace();} 
      doorStatus.release();//open door

      for (int i=0;i<inElevator.size();i++){ //LOOP TO PRINT. 
        if (inElevator.get(i).floor == floor){
          System.out.println("Person "+inElevator.get(i).personNumber+" leaves elevator");
          try {Thread.sleep(1);}  catch (InterruptedException e) {e.printStackTrace();}
        }
      }

      for (int i=0;i<inElevator.size();i++){ //LOOP TO DELETE FROM LIST
        if (inElevator.get(i).floor == floor){
          System.out.println("Elevator before deletion: ");
          for (int a=0;a<inElevator.size();a++){
            System.out.print(inElevator.get(a).personNumber+ ", ");
          }
          System.out.println("");
          inElevator.remove(i);
          System.out.println("Elevator after deletion: ");
          for (int a=0;a<inElevator.size();a++){
            System.out.print(inElevator.get(a).personNumber+ ", ");
          }
          System.out.println("");
          i--; //If you remove it, you have to decrease counter by one because length decreased by 1. Would skip a person if not done. Took long time!
        }
      }

      try{doorStatus.acquire();} catch (InterruptedException e) {e.printStackTrace();}
      System.out.println("Elevator door closes");
      try {Thread.sleep(1);}  catch (InterruptedException e) {e.printStackTrace();}
    }
}//end elevator class