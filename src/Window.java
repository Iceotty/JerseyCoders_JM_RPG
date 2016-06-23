import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Created by Joseph on 15/06/2016.
 */
public class Window extends Application {

    HashMap<String,Button> buttons;
    HashMap<String,VBox> vBoxLayouts;
    HashMap<String,Scene> scenes;
    Button button1;
    Button button2;
    Stage window;
    Scene scene1, scene2;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Label label1 = new Label("HI HOW ARE YOU");
        //Button 1
        button1 = new Button("Clicky button");
        button1.setOnAction(e -> applyScene(scene2));

        //Layout 1
        HBox layout = new HBox();
        layout.getChildren().addAll(label1,button1);
        Scene scene1 = new Scene(layout, 300, 300);

        //Button 2
        button2 = new Button("A different button");
        button2.setOnAction(e -> applyScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2,300,300);

        applyScene(scene1);
    }
    public void applyScene(Scene scene){
        window.setScene(scene);
        window.show();
    }
    public void makeScene(String text, String name){
        Label sceneText = new Label(text);
        VBox layout = new VBox();
        Scene scene;
        layout.getChildren().add(sceneText);
        scene = new Scene(layout);
        scenes.put(name,scene);
        vBoxLayouts.put(name,layout);
    }
    public void makeButton(String text, String name){
        Button button = new Button(text);
        buttons.put(name,button);
    }
}
