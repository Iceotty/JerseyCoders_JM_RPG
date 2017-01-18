package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 14/12/2016.
 */

public class RollAction extends ActionHandler {

    boolean roll;
    int min;
    int max;
    String roller;
    String successText;
    String failureText;
    RandomNumberGenerator rng;

    public RollAction(){
        rng = new RandomNumberGenerator();
        min=10;
        max = 20;
        roller = "You";
        successText = "You successfully rolled";
        failureText = "You failed";
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        ArrayList<Outcome> outcomes = new ArrayList<>();
        Outcome outcome = new Outcome();
        outcome.message = "rollAction input failure";

        roll = rng.rollBoolean(max, min, roller);
        //For some reason, it rolls twice. It is only called once.
        outcome.successful = roll;
        if (roll){
            outcome.message = successText;
        }else{
            outcome.message = failureText;
            //Need to kill the player somehow
        }
        outcomes.add(outcome);
        return outcomes;
    }
}
/**
 * when I get input that says roll, I call a RollAction, which makes a true/false roll
 * I then need to output a message, depending if the roll was successful or not.
 * Could give the RollAction the trap that triggered it, but then RollAction would still need to get the success text and call printKillTrap().
 *
 * Don't want to be able to roll at any point in the game, would be confusing. Although it might just be easier to make it so nothing happens if you roll for no reason.
 * I need to be able to tell why the player is rolling. Combat, traps, etc.
 * HOW!?
 * So in Display(), when it gets input I want to check if the input == "roll". If so, I need some way to check if a trap has just been triggered. The traps do have a hasSprung boolean.
 * If a trap HAS been triggered, then set the RollAction's success text to "You successfully dodged the trap", and give the trap's killText as the failure text.
 * So therefore Display needs to have an arrayList of traps, which it can loop through and find the one that hasSprung (will need to reset hasSprung afterwards).
 * Can't just give the ArrayList to Display when it is created, because then it wouldn't update.
 *
 */