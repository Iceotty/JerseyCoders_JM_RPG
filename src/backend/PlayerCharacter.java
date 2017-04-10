package backend;

import frontend.InputManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joseph on 09/03/2016.
 */
public class PlayerCharacter extends Character {
    int level;
    boolean attackFoo;
    Object foo=new Object();
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
    public ArrayList<Outcome> combat(CombatState combatState) {
        ArrayList<Outcome> outcomes = new ArrayList<>();
        Outcome outcome1 = new Outcome();
        outcome1.message = "It is your turn";
        outcomes.add(outcome1);

        Outcome outcome2 = new Outcome();
        outcome2.message = "What do you want to do?";
        outcomes.add(outcome2);

        Outcome outcome = new Outcome();

        NPC enemyToAttack = null;
        String input;
        for (NPC npc : combatState.currentRoom.enemies) {
            System.out.println(combatState.currentRoom.enemies.indexOf(npc) + ". " + npc.name);
        }
//        while(enemyToAttack==null){
//            input=inputManager.read();
//            if (input!=null&&isInteger(input)) {
//                if (Integer.parseInt(input)>=combatState.currentRoom.enemies.size()){
//                    System.out.println("Type in a proper response");
//                }else {
//                    enemyToAttack = combatState.currentRoom.enemies.get(Integer.parseInt(input));
//                }
//            }else {
//                System.out.println("Type in a proper response");
//            }
//        }
//        if (enemyToAttack!=null) {
        try {
            synchronized (foo) {
                while (!attackFoo) {
                    foo.wait();
                }
                if (rng.rollBoolean(20, 11, "You")) {
                    outcome.message = "You hit the " + enemyToAttack.name;
                    enemyToAttack.health = enemyToAttack.health - rng.rollInt(6, 0, "You");
                    if (enemyToAttack.health <= 0) {
                        enemyToAttack.isDead = true;
                        outcome.message = "You killed " + enemyToAttack.name;
                    }
                } else {
                    outcome.message = "You completely missed " + enemyToAttack.name;
                }
            }

        } catch (InterruptedException e) {
            return outcomes;
        }
        return outcomes;
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
