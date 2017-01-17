package backend;

import frontend.Action;

import java.util.ArrayList;

/**
 * Created by Joseph on 21/09/2016.
 */
public class MoveAction extends ActionHandler {
    public MoveAction(Game game){
        this.game = game;
    }
    @Override

    public ArrayList<Outcome> execute(Action action) {
        ArrayList<Outcome> outcomes = new ArrayList<>();

        Outcome outcome = new Outcome();
        outcome.message = "where?";
        if (action.getParameters().size() == 0) {
            outcomes.add(outcome);
            return outcomes;
        }
            String direction = action.getParameters().get(0);
            Room room;
            String nextRoom;
            game.previousRoom = game.currentRoom;
            room = game.getCurrentRoom();
            nextRoom = room.decide(direction);
            if (nextRoom == null) {
                outcome.successful = false;
                outcome.message = "You can't go that way";
            } else {

                if (!game.nodes.get(nextRoom).isLocked) {
                    if (game.nodes.get(nextRoom).whenEntered() != null) {
                        outcomes.addAll(game.nodes.get(nextRoom).whenEntered());
                    }
                    game.currentRoom = nextRoom;
                    outcome.successful = true;
                    String formattedString = game.nodes.get(nextRoom).paths.keySet().toString()
                            .replace("[", "")  //remove the right bracket
                            .replace("]", "")  //remove the left bracket3
                            .trim();
                    outcome.message = game.nodes.get(nextRoom).text + ". You can go: " + formattedString + ".";
                }else {
                    outcome.message = "The door is locked.";
                }
            outcomes.add(outcome);
            }
            return outcomes;


    }

}
