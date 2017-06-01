package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 01/06/2017.
 */
public class FleeAction extends ActionHandler {
    RandomNumberGenerator rng;
    @Override
    public ArrayList<Outcome> execute(Action action) {
        Outcome outcome = new Outcome();
        if(rng.rollBoolean(20,8,"You")){
            outcome.message = "You run away, what a wuss";
        }
        ArrayList<Outcome>outcomes=new ArrayList<>();
        outcomes.add(outcome);
        return outcomes;
    }
}
