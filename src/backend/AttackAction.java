package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 11/05/2017.
 */
public class AttackAction extends ActionHandler {
    Character attacker;
    Character target;
    RandomNumberGenerator rng;
    public AttackAction(Character attacker,Character target){this.attacker = attacker; this.target = target;}

    @Override
    public ArrayList<Outcome> execute(Action action) {
        Outcome outcome = new Outcome();
        if (rng.rollBoolean(20, target.armor, attacker.name)) {
            target.health = target.health - rng.rollInt(20, 0, attacker.name);
            if (target.health <= 0) {
                outcome.message = attacker.name+" killed "+target.name;
                target.isDead = true;
            }
        }
        ArrayList<Outcome>outcomes=new ArrayList<>();
        outcomes.add(outcome);
        return outcomes;
    }
}
