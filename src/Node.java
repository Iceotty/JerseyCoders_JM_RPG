import java.util.HashMap;
/**
 * Created by Joseph on 09/03/2016.
 */
public class Node {
    String name;
    String text;
    HashMap<String, String> paths;

    public Node(String name, String text) {
        paths = new HashMap<>();
        this.text = text;
        this.name = name;
    }

    public void print() {
        System.out.println(text);
    }
    /*Decides the next node based on the input*/
    public String decide(String input) {
        String nextNode;
        nextNode = paths.get(input.toLowerCase());
        return nextNode;
    }

    public void addPath(String key, String value) {
        paths.put(key,value);
    }
}