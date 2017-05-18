package backend;

import java.lang.*;
import java.util.ArrayList;

/**
 * Created by Joseph on 11/05/2016.
 */
public class NPC extends Character {
    Room room;
    String text;
    String killText;
    Item item;
    public NPC(int health,Room room,String text,String killText,String name,Item item){
        this.room = room;
        this.text = text;
        this.name = name;
        this.killText = killText;
        this.health = health;
        this.item = item;
    }
    public void printText(){System.out.println(text);}
    public void printKillText(){System.out.println(killText);}

    @Override
    public ArrayList<Outcome> combat(CombatState combatState) {
        return null;
    }
}
