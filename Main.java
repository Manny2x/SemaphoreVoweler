package semaphores.project1;


import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(1);

        Thread a1 =
                new Thread(
                        new VowelFind("A1",
                                semaphore));
        Thread b1 =
                new Thread(
                        new ReadString("B1"
                                ,semaphore));

        a1.start();
        b1.start();
    }
}
