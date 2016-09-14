package backend;

/**
 * Created by Joseph on 11/05/2016.
 */
public class Trap {
    String trapText;
    String killText;
    boolean hasSprung=false;
    public Trap(String trapText, String killText){
        this.trapText = trapText;
        this.killText = killText;

    }
    public void printTrap(){System.out.println(trapText);}
    public void printKillTrap(){System.out.println(killText);}
}
