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
    Delegator delegator = new Delegator();
    Game game;
    public DoCombatAction(Character character, Character character1){
        this.character = character;
        this.character1 = character1;
        delegator.addActionhandler("attack",makeAttackAction(character,character1));
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        if (character.equals(NPC.class)){
            //Attack PC
            return delegator.delegate(new Action("attack",null));
        }else if (character.equals(PlayerCharacter.class)){
            //Take input from Display, and then either call an AttackAction, or a FleeAction
            /**
             * How do I do this? This is where I ran into problems before.
             * I can't run this from Display, and already know what the choice is, because Display doesn't know when it is the player's turn.
             * So maybe I need to tell Display that it is. Previously I was only sending Actions from Display to Game, but I should be able to do it the other way.
             * Display can't delegate it, it has to be delegated in here, but how will the Action get Display?
             * Actually no that's not true, I can get Game to delegate it, and then give this the result, but that doesn't solve the problem.
             * how does display send stuff to game? ive completely forgotten
             *
             * Plan:
             * new ActionHandler PlayerInputAction, asks Display for input from one of two buttons, then sends it to here, then stuff happens
             *
             */
        }
        return null;
    }
        ActionHandler makeAttackAction(Character char2, Character char1){return new AttackAction(char2, char1);}

}
