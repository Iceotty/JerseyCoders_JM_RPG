package frontend;

import backend.Delegator;

/**
 * Created by Joseph on 14/09/2016.
 */
public class Display {
    private Delegator delegator;
    private InputManager inputManager = new InputManager();

    public void display(String input){
        System.out.println(input);
    }

    public String read(){
        String input;
        input = getInputManager().read();

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
