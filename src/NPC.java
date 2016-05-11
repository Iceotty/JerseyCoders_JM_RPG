/**
 * Created by Joseph on 11/05/2016.
 */
public class NPC extends Character{
    boolean isAggressive;
    Room room;
    String text;
    String killText;
    public NPC(Room room,String text,String killText){
        this.room = room;
        this.text = text;
        this.killText = killText;
        isAggressive = true;
    }
    public void printText(){System.out.println(text);}
    public void printKillText(){System.out.println(killText);}
}
