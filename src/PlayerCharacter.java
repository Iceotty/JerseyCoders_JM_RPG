import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joseph on 09/03/2016.
 */
public class PlayerCharacter extends Character {
    int level;
    public PlayerCharacter(){
        agility = 14;
        health = 10;
        armor=13;
        inventory = new HashMap<>();
    }

    @Override
    public void combat(CombatState combatState) {
        List<NPC> enemies;
        System.out.println("It is your turn");
        System.out.println("Choose an enemy to attack");
        enemies=combatState.getEnemies();
        for (NPC npc : enemies){
            System.out.println(npc.name);
        }
    }
}
