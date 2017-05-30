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
    Character character1;
    Delegator delegator;
    Game game;
    String actionType;
    public DoCombatAction(Delegator delegator,Character character, Character character1){
        this.character = character;
        this.character1 = character1;
        this.delegator = delegator;
        delegator.addActionhandler("attack",makeAttackAction(character,character1));
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        if (character.equals(NPC.class)){
            //Attack PC
            return delegator.delegate(new Action("attack",null));
        }else if (character.equals(PlayerCharacter.class)&&(actionType!=null)){
            //Take input from Display, and then either call an AttackAction, or a FleeAction
            delegator.delegate(new Action(actionType,null));//This likely wont work, as Im pretty sure the player wont have pressed a button at this point. might have to have a loop
        }
        return null;
    }
        ActionHandler makeAttackAction(Character char2, Character char1){return new AttackAction(char2, char1);}

}
