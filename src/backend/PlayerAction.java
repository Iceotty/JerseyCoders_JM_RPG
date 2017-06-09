package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 30/05/2017.
 */
public class PlayerAction extends ActionHandler {
    String actionType;
    Game game;
    public PlayerAction(Game game){
        this.game = game;
    }

    @Override
    public ArrayList<Outcome> execute(Action action) {
        /**
         * Shouldn't need to return any outcomes, as this just gives information to DoCombatAction
         * Although this might not work at all.
         */
        actionType = action.getParameters().get(0);
        if (actionType!=null) {
            game.doCombatAction.actionType = actionType;
        }
        return null;
    }
}
