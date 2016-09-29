package backend;

import frontend.Action;

/**
 * Created by Joseph on 21/09/2016.
 */
public class MoveAction extends ActionHandler {
    public MoveAction(Game game){
        this.game = game;
    }
    @Override

    public Outcome execute(Action action) {
        // takes in (String direction)
        String direction = action.getParameters().get(0);
        Room room;
        String nextRoom;
        Outcome outcome = new Outcome();
        game.previousRoom = game.currentRoom;
        room = game.getCurrentRoom();
        nextRoom=room.decide(direction);
        if (nextRoom==null){
            outcome.successful = false;
            outcome.message = "Type in a proper response";
        }else{
            if (!game.nodes.get(nextRoom).isLocked){
                game.currentRoom=nextRoom;
            }
            outcome.successful = true;
            outcome.message = room.text;
        }
        return outcome;
    }
}
