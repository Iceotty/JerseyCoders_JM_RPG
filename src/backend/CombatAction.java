package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 04/04/2017.
 */
public class CombatAction extends ActionHandler {
    Game game;
    ArrayList<Outcome> outcomes;
    public CombatAction(Game game){
        this.game = game;
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        outcomes = new ArrayList<>();
        outcomes.addAll(game.combat(game.getCurrentRoom().enemies));
        return outcomes;
    }
}
