import java.util.HashMap;

/**
 * Created by Joseph on 11/05/2016.
 */
public abstract class Character {
    int health;
    int armor;
    int initiative;
    int mana;
    int strength;
    int agility;
    int magic;
    boolean isDead;
    boolean isTurn;
    HashMap<String, Item> inventory;

    public abstract void combat(CombatState combatState);
}
