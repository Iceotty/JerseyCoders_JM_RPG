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
    public DoCombatAction(Delegator delegator,Character character, Character character1, Game game){
        this.character = character;
        this.character1 = character1;
        this.delegator = delegator;
        delegator.addActionhandler("attack",makeAttackAction(character,character1));
        delegator.addActionhandler("flee",makeFleeAction());
        this.game = game;
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        ArrayList<Outcome> outcomes=new ArrayList<>();
        Outcome outcome=new Outcome();
        if (character.equals(NPC.class)){
            //Attack PC
            return delegator.delegate(new Action("attack",null));
        }else if (character.equals(PlayerCharacter.class)){
            //Take input from Display, and then either call an AttackAction, or a FleeAction
            while (x){
                if (actionType!=null) {//this is probs bad but i cant think of anything else I can do
                    if (actionType.equals("attack")){
                        outcome = delegator.delegate(new Action(actionType, null)).get(0);
                        outcomes.add(outcome);
                    }
                    if (actionType.equals("flee")){
                        outcome =delegator.delegate(new Action(actionType,null)).get(0);
                        if (outcome.successful){
                            ArrayList<String> parameters =new ArrayList<>();
                            parameters.add("back");
                            delegator.delegate(new Action("move",parameters));
                        }
                        outcomes.add(outcome);
                    }
                    x=false;
                }
            }
        }
        outcome.variables.add(actionType);
        return outcomes;
    }
        ActionHandler makeAttackAction(Character char2, Character char1){return new AttackAction(char2, char1);}
        ActionHandler makeFleeAction(){return new FleeAction();}

}
