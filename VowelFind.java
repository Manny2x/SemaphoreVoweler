package semaphores.project1;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class VowelFind implements Runnable {
    String name;
    Semaphore semaphore;

    VowelFind(String name, Semaphore sempahore){
        this.name = name;
        this.semaphore = sempahore;
    }

    @Override
    public void run() {
        System.out.println("Starting " + name + " thread");
        String vowels = "AEIOUaeiou";
        String symbols = "@#}{()*&^%$£!?/><.,|\\¬`[]1234567890=-+_;:'~";

        try {
            System.out.println(name + " is waiting for a permit");
            semaphore.acquire();
            System.out.println(name + " has acquired a permit to " + SharedResource.class.getName());
            char[] chars = SharedResource.sharedFile.strip().toCharArray();

            ArrayList<Character> characterArrayList =
                    new ArrayList<>();

            for (char aChar : chars) {
                if(!Character.isDigit(aChar) ||
                    !symbols.contains(String.valueOf(aChar)))
                    characterArrayList.add(aChar);
            }

            for (Character character : characterArrayList) {
                if (vowels.contains(String.valueOf(character))) {
                    SharedResource.vowelCount++;
                    System.out.println(name + " found a vowel");

                    SharedResource.vowels.append(character).append(" ");

                } else {
                    System.out.println(name + " did not find a vowel");
                }
            }

            semaphore.release();


        } catch (InterruptedException exc){
            System.err.println("Threads where interrupted...");
            exc.printStackTrace();

            System.exit(1);
        }
    }
}
