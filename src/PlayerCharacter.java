import java.util.HashMap;

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
        System.out.println("It is your turn");
        System.out.println("Do you want to attack "+combatState);
    }
}
