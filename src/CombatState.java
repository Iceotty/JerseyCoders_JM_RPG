import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joseph on 18/05/2016.
 */
public class CombatState {
    Collection<NPC> characters;
    List<Initiative> turnOrder;
    Room currentRoom;
    public CombatState(Collection<NPC> characters, List<Initiative> turnOrder, Room currentRoom){
        this.characters = characters;
        this.turnOrder = turnOrder;
        this.currentRoom = currentRoom;
    }
    public List<NPC> getEnemiesList(){
        List<NPC> enemyList = new ArrayList<>();
        for (Character character : characters){
            if (character instanceof NPC){
                enemyList.add((NPC) character);
            }
        }
        return enemyList;
    }
}
