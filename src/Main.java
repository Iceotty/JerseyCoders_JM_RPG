import backend.Game;
import frontend.Display;

/**
 * Created by Joseph on 21/09/2016.
 */
public class Main {
    public static void main(String ...args){
        Display display = new Display();
        Game game = new Game(display.getDelegator());
        game.gameLoop();
    }
}
