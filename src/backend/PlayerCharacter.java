package backend;

import frontend.InputManager;

import java.util.HashMap;

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
        System.out.println("It is your turn");
        System.out.println("Type in the number of the enemy you want to attack");
        NPC enemyToAttack=null;
        String input;
        for (NPC npc : combatState.currentRoom.enemies){
                System.out.println(combatState.currentRoom.enemies.indexOf(npc)+". "+npc.name);
        }
        while(enemyToAttack==null){
            input=inputManager.read();
            if (input!=null&&isInteger(input)) {
                if (Integer.parseInt(input)>=combatState.currentRoom.enemies.size()){
                    System.out.println("Type in a proper response");
                }else {
                    enemyToAttack = combatState.currentRoom.enemies.get(Integer.parseInt(input));
                }
            }else {
                System.out.println("Type in a proper response");
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
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
