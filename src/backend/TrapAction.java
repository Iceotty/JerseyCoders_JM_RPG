package backend;

import frontend.Action;

/**
 * Created by Joseph on 23/11/2016.
 */
public class TrapAction extends ActionHandler{
    String nextRoom;
    RandomNumberGenerator rng;
    boolean roll;
    Trap trap;
    public TrapAction(Game game){
        this.game = game;
        this.trap = trap;
        rng = new RandomNumberGenerator();
        this.nextRoom = nextRoom;
    }
    @Override
    public Outcome execute(Action action) {
        Outcome outcome = new Outcome();
        if (nextRoom != null){
//        trap.printTrap();
//        System.out.println("Type roll to roll the outcome");
//            if (inputManager.read().toLowerCase().equals("roll")){
                roll=rng.rollBoolean(20,game.pc.agility,"You");
                if (roll){
                    game.currentRoom=nextRoom;
                    trap.hasSprung=true;
                    outcome.successful = true;
                    outcome.message = "You successfully dodged the trap";
                }else {
                    //                trap.printKillTrap();
                    game.pc.isDead = true;
                    outcome.successful = false;
                    outcome.message = trap.killText;
                }
            }
//        }

        return outcome;
    }
}
