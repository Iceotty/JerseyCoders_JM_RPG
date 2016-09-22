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
        actionHandlers.put("north",MoveAction);
        actionHandlers.put("northEast",MoveAction);
    }

    public void delegate(Action action){
         if (actionHandlers.containsKey(action)){
             actionHandlers.get(action.getAction()).execute(action);
         }
     }
}
