import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;
import java.util.Queue;

public class Main {

    public static ArrayList<Person> persons = new ArrayList<>();
    public static ArrayList<Thread> threads = new ArrayList<>();
    public static void main(String[] args) {
        for (int i=0;i<49;i++){ //Creates 49 people
          persons.add(new Person(i));//add person to array of persons
          Thread p = new Thread(persons.get(i));
          threads.add(p);//add thread to array of threads
          p.start();
        }

        
        Elevator lift = new Elevator(); //Creates elevator
        Thread l = new Thread(lift);
        l.start();
        
        //--------------------- NOW JOIN ALL THREADS-----------------------//
        for (int i=0;i<threads.size();i++){
          try{threads.get(i).join();} catch(InterruptedException e){e.printStackTrace();} //JOIN THREADS OF PEOPLE
          System.out.println("Joined Person "+i);
        }
        try{l.join();} catch(InterruptedException e){e.printStackTrace();} //JOIN THREAD OF ELEVATOR
        System.out.println("Joined Elevator");


    }
}