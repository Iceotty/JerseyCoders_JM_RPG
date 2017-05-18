package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 05/04/2017.
 */
public class DoCombatAction extends ActionHandler {
    /**
     * This gets called after combat has begun, and replaces looping through the initiative List
     */
    Character character;
    public DoCombatAction(Character character){
        this.character = character;
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        if (character.equals(NPC.class)){
            //Attack PC
        }else if (character.equals(PlayerCharacter.class)){
            //Take input from Display, and then either call an AttackAction, or a FleeAction
        }
        return null;
    }
}
