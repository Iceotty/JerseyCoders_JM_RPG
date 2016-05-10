import java.util.HashMap;
/**
 * Created by Joseph on 09/03/2016.
 */
public class Node {
    String name;
    String text;
    String trapText;
    HashMap<String, String> paths;

    public Node(String name, String text, String trapText) {
        paths = new HashMap<>();
        this.text = text;
        this.name = name;
        this.trapText = trapText;
    }

    public void print() {
        System.out.println(text);
    }
    public void printTrap(){System.out.println(trapText);}
    /*Decides the next node based on the input*/
    public String decide(String input) {
        String nextNode;
        nextNode = paths.get(input);
        return nextNode;
    }

    public void addPath(String key, String value) {
        paths.put(key,value);
    }
}