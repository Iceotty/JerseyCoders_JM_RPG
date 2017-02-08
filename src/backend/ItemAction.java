package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 01/02/2017.
 */
public class ItemAction extends ActionHandler {
    Character pc = new PlayerCharacter();
    Game game;
    public ItemAction(Game game){
        this.game = game;
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        ArrayList<Outcome> outcomes = new ArrayList<>();
        Outcome outcome = new Outcome();
        outcome.message = "what?";
        if (game.getCurrentRoom().item==null) {
            outcomes.add(outcome);
            return outcomes;
        }
        pc.inventory.put(game.getCurrentRoom().item.name,game.getCurrentRoom().item);
        outcome.message = game.getCurrentRoom().item.text;
        outcomes.add(outcome);
        return outcomes;
    }
    /**
     * NOTES:
     * don't think I actually need to use parameters for this
     * it'll be triggered by the string "take"
     * Item.text is what it says when you are automatically given an item, e.g. "a friendly man gives you an axe" so if you have to say you take the item I might have to change the text
     *
     * Do I even need ItemActions?
     * Probably, we'll see.
     */
}
