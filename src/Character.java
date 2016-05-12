import java.util.HashMap;

/**
 * Created by Joseph on 11/05/2016.
 */
public class Character {
    int health;
    int initiative;
    int mana;
    int strength;
    int agility;
    int magic;
    boolean isDead;
    boolean isTurn;
    HashMap<String, Item> inventory;
}
