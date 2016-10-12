package backend;

import frontend.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 21/09/2016.
 */
public class Delegator {
    Game game;
    Map<String,ActionHandler> actionHandlers = new HashMap<>();
    public Delegator(){
    }

    public Outcome delegate(Action action){
         if (actionHandlers.containsKey(action.getAction())){
             return actionHandlers.get(action.getAction()).execute(action);
         }
        return null;
     }
    public void addActionhandler(String name,ActionHandler actionHandler){
        actionHandlers.put(name, actionHandler);
    }
}
