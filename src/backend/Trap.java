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
        return outcomes;
    }
}


