import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Created by Joseph on 15/06/2016.
 */
public class Window extends Application {
    HashMap<String,VBox> vBoxLayouts;
    Stage window;
    Scene currentScene;
    public VBox layout;

    public Window(){
    }

    public static void main(String[] args){
        launch(args);
        Game game = new Game();
        game.gameLoop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
//        currentScene = scenes.get("scene.firstScene");
        window = primaryStage;
        applyScene(currentScene);
    }
    public void applyScene(Scene scene){
        window.setScene(scene);
        window.show();
    }
//    public void makeScene(String text, String name){
//        Label sceneText = new Label(text);
//        Scene scene;
//        layout.getChildren().add(sceneText);
//        scene = new Scene(layout);
//        scenes.put(name,scene);
//        vBoxLayouts.put(name,layout);
//    }

}
