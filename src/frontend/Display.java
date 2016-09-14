package frontend;

/**
 * Created by Joseph on 14/09/2016.
 */
public class Display {
    InputManager inputManager = new InputManager();

    public void display(String input){
        System.out.println(input);
    }

    public String read(){
        String input;
        input = inputManager.read();
        return input;
    }
}
