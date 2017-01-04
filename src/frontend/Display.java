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

    public Display(Delegator delegator){setDelegator(delegator);}

    public void display(String input){
        System.out.println(input);
    }

    public String read(){
        String input;
        input = getInputManager().read();
        if(input.toLowerCase().equals("roll")){

        }

        String[] words = input.split(" ");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
            String action = list.remove(0);
            Action action1 = new Action(action, list);

            ArrayList<Outcome> outcomes = delegator.delegate(action1);
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
