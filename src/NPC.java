/**
 * Created by Joseph on 11/05/2016.
 */
public class NPC extends Character{
    boolean isAggressive;
    Room room;
    public NPC(Room room){
        this.room = room;
        isAggressive = true;
    }
}
