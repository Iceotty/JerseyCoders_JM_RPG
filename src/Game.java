import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Joseph on 09/03/2016.
 */
public class Game {
    String currentNode;
    String nextNode;
    HashMap<String,Node> nodes;
    Scanner scanner;
    boolean running = false;
    public Game(){
        running = true;
        nodes=new HashMap<>();
        Node firstNode = new Node("node.firstNode","Eat, Sleep or Die (no caps pls)");
        nodes.put(firstNode.name,firstNode);

        Node eatNode = new Node("node.eatNode","Nom nom nom");
        nodes.put(eatNode.name,eatNode);
        firstNode.addPath("eat","node.eatNode");

        Node sleepNode = new Node("node.sleepNode","ZzzzzZzzz");
        nodes.put(sleepNode.name,sleepNode);
        firstNode.addPath("sleep","node.sleepNode");

        Node deadNode = new Node("node.deadNode","You Died");
        nodes.put(deadNode.name,deadNode);
        firstNode.addPath("die","node.deadNode");

        scanner=new Scanner(System.in);
        currentNode=firstNode.name;
    }
    public static void main(String ...args){
        Game game = new Game();
        game.gameLoop();
    }
    public Node getNode(String node){
        return nodes.get(node);
    }
    public void processNode(Node node){
        if (node!=null) {
            node.print();
            currentNode=node.decide(read());
        }
        if (currentNode!=null) {
            nodes.get(currentNode).print();
        }if (node!=null&&node.decide(read())==null){
            System.out.println("Type in a proper response");
        }
    }
    public String read(){
        return scanner.nextLine();
    }
    public void gameLoop(){
        while (running){
            processNode(getCurrentNode());
        }
    }
    public Node getCurrentNode(){
        return getNode(currentNode);
    }
}
