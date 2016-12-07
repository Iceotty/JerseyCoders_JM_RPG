package backend;

import frontend.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 21/09/2016.
 */
public class Delegator {
    Map<String,ActionHandler> actionHandlers = new HashMap<>();
    public Delegator(){
    }

    public ArrayList<Outcome> delegate(Action action){
         if (actionHandlers.containsKey(action.getAction())){
             return actionHandlers.get(action.getAction()).execute(action);
         }
         ArrayList<Outcome>outcomes = new ArrayList<>();
        Outcome outcome = new Outcome();
        outcome.message = "Harambe";
        outcomes.add(outcome);
        return outcomes;
     }
    public void addActionhandler(String name,ActionHandler actionHandler){
        actionHandlers.put(name, actionHandler);
    }
}