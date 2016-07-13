import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.HashMap;

/**
 * Created by Joseph on 13/07/2016.
 */
public class GameWindow {
    HashMap<String,Button> buttons;
    VBox layout;
    Scene scene;
    Game game;
    Window window;
    //when a new room is entered in game, add/remove buttons accordingly
    //create layout for room buttons, add layout to scene.
    public GameWindow(){
        makeButton("north","button.north");
        makeButton("south","button.south");
        makeButton("east","button.east");
        makeButton("west","button.west");
        makeButton("northWest","button.northWest");
        makeButton("northEast","button.northEast");
        makeButton("southWest","button.southWest");
        makeButton("southEast","button.southEast");

        window = new Window();
        scene = new Scene(layout);
    }
    public void makeButton(String text, String name){
        Button button = new Button(text);
        buttons.put(name,button);
    }
}
