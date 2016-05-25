import java.util.ArrayList;
import java.util.Collection;
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
    public List getEnemies(){
        List<NPC> enemies = new ArrayList<>();
        for (Character character : characters){
            if (character instanceof NPC){
                enemies.add((NPC) character);
            }
        }
        return enemies;
    }
}
