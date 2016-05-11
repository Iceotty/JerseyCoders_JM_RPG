import java.util.HashMap;

/**
 * Created by Joseph on 09/03/2016.
 */
public class PlayerCharacter extends Character {
    int level;
    public PlayerCharacter(){
        agility = 14;
        health = 10;
        inventory = new HashMap<>();
    }

}
