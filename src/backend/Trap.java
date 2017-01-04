package backend;

/**
 * Created by Joseph on 11/05/2016.
 */
public class Trap {
    String trapText;
    String killText;
    boolean roll;
    RandomNumberGenerator rng;
    boolean hasSprung=false;
    public Trap(String trapText, String killText){
        this.trapText = trapText;
        this.killText = killText;
        rng = new RandomNumberGenerator();

    }
    public Outcome trigger(){
        Outcome outcome = new Outcome();
        this.printTrap();
//        System.out.println("Type roll to roll the outcome");
//            if (inputManager.read().toLowerCase().equals("roll")){
                roll = rng.rollBoolean(20,10,"You");
                if (roll){
                    this.hasSprung = true;
                    outcome.successful = true;
                    outcome.message = "You successfully dodged the trap";
                }else {
//                    pc.isDead = true;
                    outcome.successful = false;
                    outcome.message = this.killText;
                }
//            }

            return outcome;
    }
    public void printTrap(){System.out.println(trapText);}
    public void printKillTrap(){System.out.println(killText);}
}


