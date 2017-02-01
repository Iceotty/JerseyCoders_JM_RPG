package backend;

import java.util.ArrayList;

/**
 * Created by Joseph on 11/05/2016.
 */
public class Trap {
    String trapText;
    String killText;
    boolean hasSprung=false;
    public Trap(String trapText, String killText){
        this.trapText = trapText;
        this.killText = killText;
    }

    public ArrayList<Outcome> trigger(){
        Outcome outcome = new Outcome();
        ArrayList<Outcome>outcomes = new ArrayList<>();
        outcome.message = trapText;
        outcomes.add(outcome);
//        hasSprung = true;
        return outcomes;
    }
    public void makeHasSprungTrue(){
        hasSprung = true;
    }
    /**
     * need to make it so that hasSprung is made true at some point, otherwise it won't be possible to leave a room with a trap in it
     * WHICH I CAN'T DO WITHOUT THE TRAP
     * WHICH I ONLY HAVE IN MOVEACTION
     * WHICH DOESN'T KNOW WHEN I'VE ROLLED
     * make game-wide boolean which tells us whether the trap has sprung or not. but how do i tell which trap it is? rollaction still doesn't know!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * i feel rlly dumb how do i do this
     */
}