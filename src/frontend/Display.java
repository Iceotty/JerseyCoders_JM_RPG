package frontend;

import backend.Delegator;
import backend.Outcome;
import backend.Trap;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Joseph on 14/09/2016.
 */
public class Display {
    private Delegator delegator;
    private InputManager inputManager = new InputManager();
    ArrayList<Trap> traps = new ArrayList<>();

    public Display(Delegator delegator){setDelegator(delegator);}

    public void display(String input){
        System.out.println(input);
    }
    public void recieveTraps(ArrayList<Trap> traps){this.traps = traps;}

    public String read(){
        String input;
        ArrayList<Outcome> outcomes =  new ArrayList<>();
        input = getInputManager().read();
        if(input.toLowerCase().equals("roll")){
            Action action2 = new Action("roll",null);
            outcomes.addAll(delegator.delegate(action2));
//            for (Trap trap : traps){
//                if (trap.getHasSprung()){
//                    outcomes.add(delegator.delegate(new RollAction(10,20,"You","You successfully dodged the trap",trap.getKillText())));
//                }
//            }
        }

        String[] words = input.split(" ");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
            String action = list.remove(0);
            Action action1 = new Action(action, list);

            outcomes = delegator.delegate(action1);
            for (Outcome outcome : outcomes) {
                System.out.println(outcome.message);
            }
        return input;
    }

    public Delegator getDelegator() {
        return delegator;
    }

    public void setDelegator(Delegator delegator) {
        this.delegator = delegator;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}
