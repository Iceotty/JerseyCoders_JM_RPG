import java.util.HashMap;
import java.util.List;

/**
 * Created by Joseph on 09/03/2016.
 */
public class PlayerCharacter extends Character {
    int level;
    RandomNumberGenerator rng;
    InputManager inputManager;
    public PlayerCharacter(){
        agility = 14;
        health = 10;
        armor=13;
        inventory = new HashMap<>();
        rng = new RandomNumberGenerator();
    }

    @Override
    public void combat(CombatState combatState) {
        HashMap<String,NPC> enemyHash;
        List<NPC> enemyList;
        System.out.println("It is your turn");
        System.out.println("Choose an enemy to attack");
        NPC enemyToAttack=null;
        String input;
        enemyHash =combatState.getEnemiesHash();
        enemyList = combatState.getEnemiesList();
        for (NPC npc : enemyList){
            if (npc==combatState.currentRoom.npc) {
                System.out.println(npc.name);
            }
            if (inputManager.read() == npc.name){
                enemyToAttack = npc;
            }
        }
        while(enemyToAttack==null){
            input=inputManager.read();
            enemyToAttack=enemyHash.get(input);
            if (enemyToAttack==null){
                System.out.println("Type in a proper response");
            }
        }
        if (rng.rollBoolean(20, 11, "You")) {
            System.out.println("You hit the " + combatState.currentRoom.name);
            combatState.currentRoom.npc.health=combatState.currentRoom.npc.health-rng.rollInt(6,0,"You");
            if(combatState.currentRoom.npc.health<=0){
                combatState.currentRoom.npc.isDead=true;
                System.out.println("You killed "+combatState.currentRoom.npc.name);
            }
        } else {
            System.out.println("You completely missed " + combatState.currentRoom.npc.name);
        }

    }
}
