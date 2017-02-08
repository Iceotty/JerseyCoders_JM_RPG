package backend;

/**
 * Created by Joseph on 04/05/2016.
 */
public class Item {
    String name;
    String pickupText;
    String findText;
    String room;
    public Item(String name, String pickupText,String findText){
        this.name = name;
        this.pickupText = pickupText;
        this.findText = findText;
    }
}
