package backend;

import java.util.Random;

/**
 * Created by Joseph on 25/05/2016.
 */
public class RandomNumberGenerator {
    Random random;
    public boolean rollBoolean(int max, int min, String roller){
        random = new Random();
        int randInt = random.nextInt(max)+1;
        if (roller!=null) {
            System.out.println(roller + " rolled a " + randInt);
        }
        if (randInt>=min){
            return true;
        }else{
            return false;
        }
    }
    public int rollInt(int max, int min, String roller){
        random = new Random();
        int randInt = random.nextInt(max)+1;
        if(roller!=null){
            System.out.println(roller+" rolled a "+randInt);
            //Needs to be sent to Display to be printed in future
        }
        return randInt;
    }
}
