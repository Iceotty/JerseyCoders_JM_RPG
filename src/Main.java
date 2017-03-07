import backend.Delegator;
import backend.Game;
import frontend.Display;

import java.awt.*;

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
            if(display.isDead){
//                display.display("You're Ded");
                display.setText("You're Dead",0,new Label());
                return;
            }
        }
    }
}
