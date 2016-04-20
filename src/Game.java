import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Joseph on 09/03/2016.
 */
public class Game {
    String currentNode;
    HashMap<String,Room> nodes;
    Scanner scanner;
    boolean running = false;
    public Game(){
        running = true;
        nodes=new HashMap<>();
        //1,2,3,4,5,6
        makeRoom("room.firstRoom","The dankest of dank rooms").east("room.secondRoom").southEast("room.thirdRoom").south("room.fourthRoom");
        makeRoom("room.secondRoom","Somewhat dank. Has rare pepes on the walls").west("room.firstRoom").south("room.fifthRoom").southEast("room.sixthRoom");
        makeRoom("room.thirdRoom","No dank memes in this one :(").northEast("room.firstRoom");
        makeRoom("room.fourthRoom","Its okay I guess").north("room.firstRoom").east("room.fifthRoom").southEast("room.seventhRoom").south("room.eighthRoom");
        makeRoom("room.fifthRoom","Not very dank tbh").southWest("room.seventhRoom").south("room.ninthRoom").north("room.secondRoom");
        makeRoom("room.sixthRoom","Dank. Really really dank.").northWest("room.secondRoom");
        makeRoom("room.seventhRoom","Has a warm pit of lava").northEast("room.fifthRoom").northWest("room.fourthRoom");

//        Node firstNode = new Node("node.firstNode","Eat, Sleep or Die (no caps pls)");
//        nodes.put(firstNode.name,firstNode);
//
//        makeNode("node.eatNode","Nom Nom");
//        firstNode.addPath("eat","node.eatNode");
//
//        makeNode("node.sleepNode","ZzzZzzz");
//        firstNode.addPath("sleep","node.sleepNode");
//
//        makeNode("node.deadNode","Yu ded");
//        firstNode.addPath("die","node.deadNode");
//        nodes.get("node.eatNode").addPath("die","node.deadNode");
//        scanner=new Scanner(System.in);
//        currentNode=firstNode.name;

    }
    public static void main(String ...args){
        Game game = new Game();
        game.gameLoop();
    }
    public Node getNode(String node){
        return nodes.get(node);
    }
    public void processNode(Node node){
        String nextNode=null;
        if (node!=null) {
            node.print();
        }
        String input;
        while (nextNode==null&&running){
            input=read();
            nextNode=node.decide(input);
            if (nextNode==null){
                System.out.println("Type in a proper response");
            }
        }
        currentNode=nextNode;
    }
    public String read(){
        return scanner.nextLine();
    }
    public void gameLoop(){
        while (running){
            if (nodes.get(currentNode).paths.isEmpty()){
                running=false;
            }
            processNode(getCurrentNode());
        }
    }
    public Node getCurrentNode(){
        return getNode(currentNode);
    }

    private Room makeRoom(String roomName, String nodeText){
        nodes.put(roomName,new Room(roomName,nodeText));
        return nodes.get(roomName);
    }
}

