/**
 * Created by Joseph on 20/04/2016.
 */
public class Room extends Node {
    boolean isEndRoom=false;
    boolean isDeathRoom=false;
    public Room(String name, String text) {
        super(name, text);
    }
    public Room north(String room){
        addPath("north",room);
        return this;
    }
    public Room east(String room){
        addPath("east",room);
        return this;
    }
    public Room south(String room){
        addPath("south",room);
        return this;
    }
    public Room west(String room){
        addPath("west",room);
        return this;
    }
    public Room northEast(String room){
        addPath("northEast",room);
        return this;
    }
    public Room northWest(String room){
        addPath("northWest",room);
        return this;
    }
    public Room southEast(String room){
        addPath("southEast",room);
        return this;
    }
    public Room southWest(String room){
        addPath("southWest",room);
        return this;
    }
}
