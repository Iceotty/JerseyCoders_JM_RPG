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
//        game.gameLoop();
        while (true){
            display.read();
            display.update();
            if(display.areDead){
//                display.display("You're Ded");
//                display.window.setText("You're Dead",0,new Label());
                return;
            }
        }
    }
}
