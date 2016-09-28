package backend;

import frontend.Action;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joseph on 21/09/2016.
 */
public class Delegator {
    Map<String,ActionHandler> actionHandlers = new HashMap<>();
    public Delegator(){
        actionHandlers.put("move", new MoveAction());
    }

    public Outcome delegate(Action action){
         if (actionHandlers.containsKey(action.getAction())){
             return actionHandlers.get(action.getAction()).execute(action);
         }
        return null;
     }
}
