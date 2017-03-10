package frontend;

import backend.Delegator;
import backend.Outcome;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Joseph on 14/09/2016.
 */
public class Display implements ActionListener {
    private Delegator delegator;
    private InputManager inputManager = new InputManager();
    public boolean isDead;
    public JFrame frame = new JFrame("insert game title here");
    JPanel panel;
    Label textLabel1 = new Label();
    ArrayList<Label> labels;
    ArrayList<String> directions;
    ArrayList<JButton> buttonList;
    HashMap<String,JButton> buttons;
    ArrayList<String> textList;

    public Display(Delegator delegator){
//        window = new Window();
        setDelegator(delegator);textList = new ArrayList<>();
        labels = new ArrayList<>();
        buttons = new HashMap<>();
        buttonList = new ArrayList<>();
        makeButton("north","North",200,100,80,25,true);
        makeButton("south","South",200,300,80,25,true);
        makeButton("west", "West",100,200,80,25,true);
        makeButton("east","East",300,200,80,25,true);
        makeButton("northeast","Northeast",260,150,100,25,true);
        makeButton("northwest","Northwest",120,150,100,25,true);
        makeButton("southeast","Southeast",260,250,100,25,true);
        makeButton("southwest","Southwest",120,250,100,25,true);
        makeButton("roll","Roll",300,400,80,25,false);
        makeButton("take","Take Item",80,400,100,25,false);

        buttons.get("south").setEnabled(true);
        buttons.get("east").setEnabled(true);
        buttons.get("southeast").setEnabled(true);
        panel = new JPanel();
        panel.setBounds(0,0,500,500);

        panel.setLayout(null);
        panel.add(buttons.get("east"));
        panel.add(buttons.get("north"));
        panel.add(buttons.get("south"));
        panel.add(buttons.get("west"));
        panel.add(buttons.get("northeast"));
        panel.add(buttons.get("northwest"));
        panel.add(buttons.get("southeast"));
        panel.add(buttons.get("southwest"));
        panel.add(buttons.get("roll"));
        panel.add(buttons.get("take"));

        panel.add(textLabel1,BorderLayout.CENTER);
        setText("You're stuck in a really awful dungeon",0,textLabel1);
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

    public void display(ArrayList<Outcome> outcomes){
        ArrayList<String> input = new ArrayList<>();
        for (Outcome outcome : outcomes){
            input.add(outcome.message);
        }
        System.out.println(input);
        if (input!=null){
            int y =0;
            for (String text:input){
                setText(text,y,new Label());
                y+=26;
            }
        }
    }
    public void update(){
        for (Label label:labels){
            panel.remove(label);
        }

        if (isDead){
            System.out.println("Yo ded");
            for (JButton button:buttonList){
                button.setEnabled(false);
            }
            return;
        }
        if (directions !=null && directions.size()>0){
            for (JButton button:buttonList){
                button.setEnabled(false);
            }
            for (String key : directions){
                if(buttons.get(key)!=null){
                    buttons.get(key).setEnabled(true);
                }
            }
        }
    }

    public String read(){
        String input;
        ArrayList<Outcome> outcomes =  new ArrayList<>();
        input = getInputManager().read();
        if(input.toLowerCase().equals("roll")){
            Action action2 = new Action("roll",null);
            outcomes.addAll(delegator.delegate(action2));
            display(outcomes);
            for (Outcome outcome : outcomes){
                if (!outcome.successful) {
                    isDead = true;
                    update();
                }
            }
            return input;
        }

        String[] words = input.split(" ");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(words));
            String action = list.remove(0);
            Action action1 = new Action(action, list);

            outcomes = delegator.delegate(action1);
            for (Outcome outcome : outcomes){
                if (outcome.directions !=null){
                    directions = outcome.directions;
                }
            }
//            for (Outcome outcome : outcomes){
//                display(outcome.message);
//            }
        display(outcomes);
        return input;
    }

    public Delegator getDelegator() {
        return delegator;
    }

    public void setDelegator(Delegator delegator) {
        this.delegator = delegator;
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public void setText(String words,int y,Label textLabel){
        String text;
        text=words;
        textLabel.setText(text);
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform,true,true);
        Font font = new Font("Tahoma", Font.PLAIN, 12);
        int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
        textLabel.setBounds(250-textwidth/2,20+y,textwidth+15,25);
        panel.add(textLabel,BorderLayout.CENTER);
        labels.add(textLabel);
    }
    public void makeButton(String key, String text,int x, int y, int width, int height,boolean addToList){
        buttons.put(key, new JButton(text));
        if (addToList) {
            buttonList.add(buttons.get(key));
        }
        buttons.get(key).setEnabled(false);
        buttons.get(key).setActionCommand(key);
        buttons.get(key).addActionListener(this);
        buttons.get(key).setBounds(x,y,width,height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Outcome> outcomes;

        if ("take".equals(e.getActionCommand())){
            outcomes = delegator.delegate(new Action("take",null));
            display(outcomes);
        }
        if ("roll".equals(e.getActionCommand())){
            outcomes = delegator.delegate(new Action("roll",null));
            for (Outcome outcome: outcomes){
                if (!outcome.successful){
                    isDead=true;
                    return;
                }
            }
            return;
        }

        String direction = e.getActionCommand();
        ArrayList<String> parameters = new ArrayList<>();
        parameters.add(direction);
        outcomes = delegator.delegate(new Action("move",parameters));
        for (Outcome outcome:outcomes){
            if (!outcome.isRoomLeaveable){
                return;
            }
            buttons.get("take").setEnabled(outcome.isItem);
            if(outcome.isTrap){
                for (JButton button:buttonList){
                    button.setEnabled(false);
                }
            }
            buttons.get("roll").setEnabled(outcome.isTrap);
            if (outcome.directions!=null){
                directions = outcome.directions;
                update();
            }
        }
        update();
        display(outcomes);
    }
}
