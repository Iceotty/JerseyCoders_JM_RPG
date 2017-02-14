package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Joseph on 15/06/2016.
 */
//public class Window extends Application {
//    Stage window;
//    Scene scene;
//    public VBox layout;
//    public Window(){
//    }
//
//    public static void main(String[] args){
//        launch(args);
////        Game game = new Game();
////        game.gameLoop();
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        window = primaryStage;
//    }
//    public void applyScene(Scene scene){
//        window.setScene(scene);
//        window.show();
//    }
////    public void makeScene(String text, String name){
////        Label sceneText = new Label(text);
////        Scene scene;
////        layout.getChildren().add(sceneText);
////        scene = new Scene(layout);
////        scenes.put(name,scene);
////        vBoxLayouts.put(name,layout);
////    }
public class Window{
    public JFrame frame = new JFrame("insert game title here");
    JPanel panel;
    Label textLabel = new Label();

    JButton bNorth;
    JButton bNorthEast;
    JButton bNorthWest;
    JButton bEast;
    JButton bSouth;
    JButton bSouthEast;
    JButton bSouthWest;
    JButton bWest;
    String text;
    ArrayList<String> textList;

    public Window(){
        textList = new ArrayList<>();
        bEast = new JButton("East");
        bEast.setBounds(100,200,80,25);
        bNorth = new JButton("North");
        bNorth.setBounds(200,100,80,25);
        bNorthEast = new JButton("North East");
        bNorthWest = new JButton("North West");
        bSouth = new JButton("South");
        bSouth.setBounds(200,300,80,25);
        bSouthEast = new JButton("South East");
        bSouthWest = new JButton("South West");
        bWest = new JButton("West");
        bWest.setBounds(300,200,80,25);
        panel = new JPanel();
        panel.setBounds(0,0,600,600);
        textLabel.setText(text);
        textLabel.setBounds(300,20,100,200);

        panel.setLayout(null);
        panel.add(bEast);
        panel.add(bNorth);
        panel.add(bSouth);
        panel.add(bWest);
        panel.add(textLabel,BorderLayout.CENTER);
        panel.validate();



        frame.add(panel, BorderLayout.CENTER);

        //Sets what happens when the frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create components and put them into the frame
//        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);

        // Size the frame. frame.setSize() HAS to be here otherwise it doesn't work.
        frame.pack();
        frame.setSize(500,500);

        // Show it.
        frame.setVisible(true);
    }
    public void setText(String words){
        text=words;
    }
}
/**
 * Window
 * I want to display the text of the room, and underneath it any additional text (traps, items)
 * also want buttons for each direction it is possible to move in, and none for the ones you can't move in.
 *
 * Need to actually make a window first though
 */
