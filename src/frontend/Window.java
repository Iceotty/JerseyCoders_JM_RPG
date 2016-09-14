package frontend;

import backend.Game;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Joseph on 15/06/2016.
 */
public class Window extends Application {
    Stage window;
    Scene scene;
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
        window = primaryStage;
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
