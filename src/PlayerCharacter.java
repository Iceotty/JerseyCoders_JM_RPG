import java.util.HashMap;

/**
 * Created by Joseph on 09/03/2016.
 */
public class PlayerCharacter {
    int level;
    int health;
    int mana;
    int strength;
    int agility;
    int magic;
    HashMap<String, Item> inventory;
    public PlayerCharacter(){
        inventory = new HashMap<>();
    }

}
