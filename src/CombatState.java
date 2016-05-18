import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joseph on 18/05/2016.
 */
public class CombatState {
    List<Character> characters;
    List<Initiative> turnOrder;
    public CombatState(List<Character> characters, List<Initiative> turnOrder){
        this.characters = characters;
        this.turnOrder = turnOrder;
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
