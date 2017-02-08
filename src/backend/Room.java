package backend;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joseph on 20/04/2016.
 */
public class Room extends Node {
    boolean isEndRoom=false;
    boolean isLocked=false;
    boolean hasTrap = false;
    Trap trap;
    Item item;
    List<NPC> enemies;
    List<NPC> friendlies;
    public Room(String name,String text,Item item, Trap trap) {
        super(name, text);
        this.item = item;
        this.trap = trap;
        enemies = new ArrayList<>();
        friendlies = new ArrayList<>();
    }
    public  ArrayList<Outcome> whenEntered(){
        ArrayList<Outcome> outcomes = new ArrayList<>();
        if (item!=null){
            Outcome outcome1 = new Outcome();
            outcome1.message = item.findText;
            outcomes.add(outcome1);
        }
        if (hasTrap&&!trap.hasSprung){
            outcomes.addAll(trap.trigger());
        }
        return outcomes;
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
        addPath("northeast",room);
        return this;
    }
    public Room northWest(String room){
        addPath("northwest",room);
        return this;
    }
    public Room southEast(String room){
        addPath("southeast",room);
        return this;
    }
    public Room southWest(String room){
        addPath("southwest",room);
        return this;
    }
}
