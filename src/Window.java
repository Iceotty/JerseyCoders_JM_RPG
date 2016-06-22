import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by Joseph on 15/06/2016.
 */
public class Window extends Application {

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
        button1.setOnAction(e -> window.setScene(scene2));

        //Layout 1
        StackPane layout = new StackPane();
        layout.getChildren().add(button1);
        Scene scene1 = new Scene(layout, 300, 300);

        //Button 2
        button2 = new Button("A different button");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        scene2 = new Scene(layout2,300,300);

        window.setScene(scene1);
        window.setTitle("WINDOW_TITLE");
        window.show();
    }


}
