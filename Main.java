import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;
import java.util.Queue;

public class Main {

    public static ArrayList<Person> persons = new ArrayList<>();
    public static void main(String[] args) {
        for (int i=0;i<49;i++){ //Creates 49 people
          persons.add(new Person(i));
          Thread t = new Thread(persons.get(i));
          t.start();
        }
        
        Elevator lift = new Elevator(); //Creates elevator
        Thread t = new Thread(lift);
        t.start();
        
    }
}