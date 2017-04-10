package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 05/04/2017.
 */
public class CombatButtonAction extends ActionHandler {
//    Object foo=new Object();
    Game game;
    public CombatButtonAction(Game game){
        this.game = game;
    }
    @Override
    public ArrayList<Outcome> execute(Action action) {
        synchronized(game.pc.foo) {
            game.pc.attackFoo = true;
            game.pc.foo.notify();
        }
        return null;
    }
}
