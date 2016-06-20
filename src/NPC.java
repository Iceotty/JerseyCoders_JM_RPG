/**
 * Created by Joseph on 11/05/2016.
 */
public class NPC extends Character{
    Room room;
    String text;
    String killText;
    String name;
    public NPC(int health,Room room,String text,String killText,String name){
        this.room = room;
        this.text = text;
        this.name = name;
        this.killText = killText;
        this.health = health;
    }
    public void printText(){System.out.println(text);}
    public void printKillText(){System.out.println(killText);}

    @Override
    public void combat(CombatState combatState) {

    }
}
