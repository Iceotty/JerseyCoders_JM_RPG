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
    boolean x=true;
    public DoCombatAction(Delegator delegator,Character character, Character character1){
        this.character = character;
        this.character1 = character1;
        this.delegator = delegator;
        delegator.addActionhandler("attack",makeAttackAction(character,character1));
        delegator.addActionhandler("flee",makeFleeAction());
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        if (character.equals(NPC.class)){
            //Attack PC
            return delegator.delegate(new Action("attack",null));
        }else if (character.equals(PlayerCharacter.class)){
            //Take input from Display, and then either call an AttackAction, or a FleeAction
            while (x){
                if (actionType!=null) {//this is probs bad but i cant think of anything else I can do
                    delegator.delegate(new Action(actionType, null));
                    x=false;
                }
            }
        }
        return null;
    }
        ActionHandler makeAttackAction(Character char2, Character char1){return new AttackAction(char2, char1);}
        ActionHandler makeFleeAction(){return new FleeAction();}

}
