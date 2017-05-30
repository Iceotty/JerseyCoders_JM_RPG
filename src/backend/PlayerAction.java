package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 30/05/2017.
 */
public class PlayerAction extends ActionHandler {
    String actionType;
    public PlayerAction(String actionType, Game game){
        this.actionType = actionType;
    }

    @Override
    public ArrayList<Outcome> execute(Action action) {
        if (actionType!=null) {
            game.doCombatAction.actionType = actionType;
        }
        return null;
    }
}
