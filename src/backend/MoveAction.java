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
            Room nextRoomRoom = game.nodes.get(nextRoom);

        if (nextRoom == null) {
                outcome.successful = false;
                outcome.message = "You can't go that way";
            } else {

                if (game.getCurrentRoom().hasTrap&&!game.getCurrentRoom().trap.hasSprung){
                    outcome.variables.add("RoomNotLeaveable");
                }
                if (!nextRoomRoom.isLocked) {
                    /**Combat/Enemy checks*/
                    if (!game.getCurrentRoom().enemies.isEmpty()){
                        outcome.variables.add("RoomNotLeaveable");
                    }
                    if (!nextRoomRoom.enemies.isEmpty()){
                        outcome.variables.add("combat");
                    }

                    if (nextRoomRoom.item!=null){
                        outcome.variables.add("isItem");
                    }
                    if (nextRoomRoom.hasTrap){
                        //&&!nextRoomRoom.trap.hasSprung
                        outcome.variables.add("isTrap");
                    }
                    outcome.directions.addAll(nextRoomRoom.paths.keySet());
                    if (game.getCurrentRoom().hasTrap) {
                        if (!game.getCurrentRoom().trap.hasSprung) {
                            outcome.message = "you can't leave the room before the trap is resolved";
                            outcomes.add(outcome);
                            return outcomes;
                            //prevents the player from leaving the room before the trap has been resolved/sprung
                        }
                    }
                    game.currentRoom = nextRoom;
                    outcome.successful = true;
//                    String formattedString = game.nodes.get(nextRoom).paths.keySet().toString()
//                            .replace("[", "")  //remove the right bracket
//                            .replace("]", "")  //remove the left bracket
//                            .trim();
                    outcome.message = game.nodes.get(nextRoom).text; // + ". You can go: " + formattedString + ".";
                    if (nextRoomRoom.whenEntered() != null) {
                        outcomes.addAll(nextRoomRoom.whenEntered());
                    }
                }else{
                    outcome.message = "The door is locked.";
                }
            outcomes.add(outcome);
            }
            return outcomes;
    }

}
