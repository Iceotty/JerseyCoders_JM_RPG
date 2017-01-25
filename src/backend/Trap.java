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
    /**
     * need to make it so that you can only roll if a trap has sprung, and that you have to roll if one is sprung. or at least make it so that you can't leave the room.
     */
}