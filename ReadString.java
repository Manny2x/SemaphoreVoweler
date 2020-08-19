package semaphores.project1;

import java.util.concurrent.Semaphore;

public class ReadString implements Runnable{
    String name;
    Semaphore semaphore;

    ReadString(String name, Semaphore semaphore){
        this.name = name;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {

        try {
            System.out.println(name + " is waiting for a permit");
            semaphore.acquire();
            System.out.println(name + " has acquired a permit to " + SharedResource.class.getName());


            System.out.println("The count of vowels is: " + SharedResource.vowelCount);
            System.out.println("The vowels found were: " + SharedResource.vowels);
        } catch (InterruptedException exc){
            System.err.println(name + " thread Interrupted");
            exc.printStackTrace();

            System.exit(1);
        }
    }
}
