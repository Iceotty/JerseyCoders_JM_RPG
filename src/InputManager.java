import java.util.Scanner;

/**
 * Created by Joseph on 25/05/2016.
 */
public class InputManager {

    Scanner scanner;
    public InputManager(){
        scanner=new Scanner(System.in);
    }
    public String read(){
        return scanner.nextLine();
    }
}
