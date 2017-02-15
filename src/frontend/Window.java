package frontend;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

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

    String text;
    ArrayList<String> directions;
    HashMap<String,JButton> buttons;

    public Window(){
        buttons = new HashMap<>();
        makeButton("north","North",200,100,80,25);
        makeButton("south","South",200,300,80,25);
        makeButton("west", "West",100,200,80,25);
        makeButton("east","East",300,200,80,25);
        makeButton("northEast","Northeast",260,150,100,25);
        makeButton("northWest","Northwest",120,150,100,25);
        makeButton("southEast","Southeast",260,250,100,25);
        makeButton("southWest","Southwest",120,250,100,25);

        buttons.get("south").setEnabled(true);
        buttons.get("east").setEnabled(true);
        buttons.get("southEast").setEnabled(true);
        panel = new JPanel();
        panel.setBounds(0,0,600,600);
        textLabel.setText(text);
        textLabel.setBounds(300,20,100,200);

        panel.setLayout(null);
        panel.add(buttons.get("east"));
        panel.add(buttons.get("north"));
        panel.add(buttons.get("south"));
        panel.add(buttons.get("west"));
        panel.add(buttons.get("northEast"));
        panel.add(buttons.get("northWest"));
        panel.add(buttons.get("southEast"));
        panel.add(buttons.get("southWest"));


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
    public void update(){
        if (directions !=null && directions.size()>0){
            for (String key : directions){
                if(buttons.get(key)!=null){
                    buttons.get(key).setEnabled(true);
                }else{
                    buttons.get(key).setEnabled(false);
                }
            }
        }
    }
    public void setText(String words){
        text=words;
    }
    public void makeButton(String key, String text,int x, int y, int width, int height){
        buttons.put(key, new JButton(text));
        buttons.get(key).setEnabled(false);
        buttons.get(key).setBounds(x,y,width,height);
    }
}
/**
 * Window
 * I want to display the text of the room, and underneath it any additional text (traps, items)
 * also want buttons for each direction it is possible to move in, and none for the ones you can't move in.
 *
 * Need to actually make a window first though
 */
