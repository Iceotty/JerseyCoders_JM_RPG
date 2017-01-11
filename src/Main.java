import backend.Delegator;
import backend.Game;
import frontend.Display;

/**
 * Created by Joseph on 21/09/2016.
 */
public class Main {
    public static void main(String ...args){
        Delegator delegator = new Delegator();
        Display display = new Display(delegator);
        Game game = new Game(display.getDelegator());
        display.recieveTraps(game.getTraps());
//        game.gameLoop();
        while (true){
            display.read();
        }
    }
}
