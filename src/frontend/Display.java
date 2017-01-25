package frontend;

import backend.Delegator;
import backend.Outcome;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Joseph on 14/09/2016.
 */
public class Display {
    private Delegator delegator;
    private InputManager inputManager = new InputManager();
    public boolean areDead;

    public Display(Delegator delegator){setDelegator(delegator);}

    public void display(String input){
        System.out.println(input);
    }

    public String read(){
        String input;
        ArrayList<Outcome> outcomes =  new ArrayList<>();
        input = getInputManager().read();
        if(input.toLowerCase().equals("roll")){
            Action action2 = new Action("roll",null);
            outcomes.addAll(delegator.delegate(action2));
            for (Outcome outcome : outcomes){
                display(outcome.message);
                if (!outcome.successful) {
                    areDead = true;
                }
            }
            return input;
        }

        String[] words = input.split(" ");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
            String action = list.remove(0);
            Action action1 = new Action(action, list);

            outcomes = delegator.delegate(action1);
            for (Outcome outcome : outcomes){
                display(outcome.message);
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
