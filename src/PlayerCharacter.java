import java.util.HashMap;
import java.util.List;

/**
 * Created by Joseph on 09/03/2016.
 */
public class PlayerCharacter extends Character {
    int level;
    RandomNumberGenerator rng;
    InputManager inputManager=new InputManager();
    public PlayerCharacter(){
        agility = 14;
        health = 10;
        armor=13;
        inventory = new HashMap<>();
        rng = new RandomNumberGenerator();
    }

    @Override
    public void combat(CombatState combatState) {
        List<NPC> enemyList;
        System.out.println("It is your turn");
        System.out.println("Type in the number of the enemy you want to attack");
        NPC enemyToAttack=null;
        String input;
        enemyList = combatState.getEnemiesList();
        for (NPC npc : enemyList){
            if (npc==combatState.currentRoom.npc) {
                System.out.println(enemyList.indexOf(npc)+". "+npc.name);
            }
        }
        while(enemyToAttack==null){
            input=inputManager.read();
            if (input!=null) {
                enemyToAttack = enemyList.get(Integer.parseInt(input));
                if (enemyToAttack == null) {
                    System.out.println("Type in a proper response");
                }
            }
        }
        if (enemyToAttack!=null) {
            if (rng.rollBoolean(20, 11, "You")) {
                System.out.println("You hit the " + enemyToAttack.name);
                enemyToAttack.health = enemyToAttack.health - rng.rollInt(6, 0, "You");
                if (enemyToAttack.health <= 0) {
                    enemyToAttack.isDead = true;
                    System.out.println("You killed " + enemyToAttack.name);
                }
            } else {
                System.out.println("You completely missed " + enemyToAttack.name);
            }
        }

    }
}
