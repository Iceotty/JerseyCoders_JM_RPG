package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 04/04/2017.
 */
public class BeginCombatAction extends ActionHandler {
    /**
     * This class is used to initiate combat,
     * for example this should be called when the player enters a room full of enemies, or attacks a previously friendly NPC.
     */
    Game game;
    ArrayList<Outcome> outcomes;
    public BeginCombatAction(Game game){
        this.game = game;
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        outcomes = new ArrayList<>();
        outcomes.addAll(game.combat(game.getCurrentRoom().enemies));
        return outcomes;
    }

}
