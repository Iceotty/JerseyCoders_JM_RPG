package backend;

import frontend.Action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joseph on 09/03/2016.
 */
public class Game {
    Delegator delegator;
    PlayerCharacter pc;
    String currentRoom;
    String previousRoom;
    List<NPC> deadNPCs;
    String[] args = new String[0];
    HashMap<String, Room> nodes;
    HashMap<String, NPC> NPCs;
    HashMap<String, Item> items;
    HashMap<String, Trap> trapsMap;
    ArrayList<Trap> traps;
    boolean running = false;
    boolean roll;
    boolean combat;
    boolean aBoolean = false;
    DoCombatAction doCombatAction;

    public Game(Delegator delegator) {
        this.delegator = delegator;
        running = true;
        nodes = new HashMap<>();
        NPCs = new HashMap<>();
        items = new HashMap<>();
        trapsMap = new HashMap<>();
        deadNPCs = new ArrayList<>();

        makeRoom("room.firstRoom", "You're stuck in a really awful dungeon", null).east("room.secondRoom").southEast("room.thirdRoom").south("room.fourthRoom");
        makeRoom("room.secondRoom", "Somewhat dank. Has rare pepes on the walls", null).west("room.firstRoom").south("room.fifthRoom").southEast("room.sixthRoom");
        makeRoom("room.thirdRoom", "rlly dank", null).northWest("room.firstRoom");
        makeRoom("room.fourthRoom", "Its okay I guess", null).north("room.firstRoom").east("room.fifthRoom").southEast("room.seventhRoom").south("room.eighthRoom");
        makeRoom("room.fifthRoom", "A plain, empty room with absolutely nothing in it", null).southWest("room.seventhRoom").south("room.ninthRoom").north("room.secondRoom");
        makeRoom("room.sixthRoom", "Dank. Really really dank", null).northWest("room.secondRoom");
        makeRoom("room.seventhRoom", "Has a warm pit of lava", null).northEast("room.fifthRoom").northWest("room.fourthRoom");
        makeRoom("room.eighthRoom", "Has octarine sparkles on the floor", null).north("room.fourthRoom").southWest("room.eleventhRoom").east("room.ninthRoom");
        makeRoom("room.ninthRoom", "3dank5me", null).north("room.fifthRoom").west("room.eighthRoom").south("room.tenthRoom");
        makeRoom("room.tenthRoom", "Has some cool loot in it", null).north("room.ninthRoom").south("room.twelfthRoom").isLocked = true;
        makeRoom("room.eleventhRoom", "Dead end, filled with monsters", null).northEast("room.eighthRoom");
        makeRoom("room.twelfthRoom", "Yay! You found the lift to the next floor of the dungeon!", null).north("room.tenthRoom").isEndRoom = true;

        makeItem("item.key", "room.sixthRoom", "you got a rusty key", "You found a key");
        makeItem("item.battleAxe", "room.fourthRoom", "You received a Shiny Battleaxe with which to smite your foes", "you found a battleaxe");
        makeItem("item.gloop", "room.firstRoom", "you picked up some lovely gloop off the floor", "there is some gloop on the floor");

        makeTrap("trap.arrowTrap", "room.fifthRoom", "An arrow trap fires at you", "An arrow trap shoots you in the balls");
        makeTrap("trap.chainsawTrap", "room.thirdRoom", "A chainsaw blade swings towards you from a wall", "You get sliced in half by a chainsaw blade");

        makeNPC(5, "npc.enemy", "room.ninthRoom", "You have awakened a slumbering pepe, it attacks you!", "You got dank'd by pepe", "Pepe", null, true);
        makeNPC(3, "npc.testEnemy", "room.secondRoom", "A slime attacks you", "The slime suffocated you", "Slime", null, true);
        makeNPC(3, "npc.slimeEnemy", "room.seventhRoom", "A pink slime flops towards you", "You were absorbed by the slime", "Pink Slime", null, true);
        makeNPC(10, "npc.niceGuy", "room.secondRoom", "A friendly man greets you in a friendly way", "A friendly man killed you", "Nice guy", null, false);
        makeNPC(20, "npc.givesItem", "room.fourthRoom", "There is a person in here, they offer you a battleaxe", "The person killed you.", "The person", "item.battleAxe", false);
        delegator.addActionhandler("move", makeMoveAction());
        delegator.addActionhandler("roll", makeRollAction());
        delegator.addActionhandler("take", makeItemAction());
        delegator.addActionhandler("playerAction", makePlayerAction());
        delegator.addActionhandler("beginCombat",makeBeginCombatAction());

        currentRoom = nodes.get("room.firstRoom").name;
        pc = new PlayerCharacter();
        System.out.println(getCurrentRoom().text);
    }

    public Room getRoom(String room) {
        return nodes.get(room);
    }

//    public void npcInteraction(ArrayList<NPC> npcs,boolean friendly){
//        if (friendly) {
//            for (NPC npc : npcs) {
////                npc.printText();
//                if (npc.item != null) {
//                    pc.inventory.put(npc.item.name, npc.item);
//                }
//            }
////            input = inputManager.read();
//            if (input == "attack") {//Doesn't do anything IS BROKEN HALP
////                getCurrentRoom().enemies = getCurrentRoom().friendlies;
////                getCurrentRoom().friendlies = null;
//                combat = true;
//                combat(npcs);
//            }
//        }
//        if (!friendly) {
//            for (NPC npc : npcs) {
////                npc.printText();
//            }
//            combat = true;
//            combat(npcs);
//        }
//    }

//    public Outcome lockedRoom(Room room){
//        Outcome outcome = new Outcome();
//        if (room.isLocked) {
////            System.out.println("The door is locked");
//            if (pc.inventory.containsKey("item.key")) {
//                outcome.message = "You unlock the door";
//                outcome.successful = true;
//                room.isLocked = false;
////                currentRoom = nextRoom;
//            } else {
//                if (inputManager.read().toLowerCase().equals("punch") || equals("break")) {
//                    System.out.println("You punch the door");
//                    if (rng.rollBoolean(20, 13, "You")) {
//                        outcome.message = "You broke down the door";
//                        outcome.successful = true;
//                        room.isLocked = false;
////                        currentRoom = nextRoom;
//                    } else {
//                        outcome.message = "You failed to punch down the door with your tiny baby arms, you now have splinters in your hands";
//                        outcome.successful = false;
//                        pc.health = pc.health - 1;
//                    }
//                    if (pc.isDead) {
//                        pcIsDead();
//                        System.out.println("You punched a door so many times you died. GG WP.");
//                    }
//                }
//            }
//        }
//        return outcome;
//    }

    public void gameLoop() {
        while (running) {
            if (getCurrentRoom().item != null) {
                getCurrentRoom().item = null;
            }
            if (nodes.get(currentRoom).isEndRoom == true) {
                running = false;
//                getCurrentRoom().print();
//                System.out.println("YOU WON ;_;");
            }
        }
    }

    public ArrayList<Outcome> combat(List<NPC> npcs) {
        ArrayList<Outcome> outcomes = new ArrayList<>();
        Outcome outcome = new Outcome();
        outcome.message = "combat broke in some way.";
        RandomNumberGenerator rng = new RandomNumberGenerator();

        List<Initiative> turnOrder = new ArrayList<>();
        turnOrder.add(new Initiative(pc, rng.rollInt(20, 0, null)));
        for (NPC npc : npcs) {
            turnOrder.add(new Initiative(npc, rng.rollInt(20, 0, null)));
            if (npc == null || npc.isDead) {
                outcome.message = "Combat is Over";
                outcomes.add(outcome);
                return outcomes;
            }
        }

        Collections.sort(turnOrder);
        if (pc.isDead || getCurrentRoom().enemies == null || getCurrentRoom().enemies.isEmpty()) {
            outcome.message = "Either the PC is dead, or the enemies are dead";
            outcomes.add(outcome);
            return outcomes;
        }
        CombatState combatState = new CombatState(NPCs.values(), turnOrder, getCurrentRoom());
        outcome.message = "Combat Starts!";
        if (turnOrder.get(0).character.equals(NPC.class)){
            makeDoCombatAction(delegator,turnOrder.get(0).character,pc);
            Initiative init = turnOrder.remove(0);
            turnOrder.add(init);
        }else if (turnOrder.get(0).character.equals(PlayerCharacter.class)){
            Character character=null;
            delegator.addActionhandler("doCombat",makeDoCombatAction(delegator,turnOrder.get(0).character,character));
            for (Initiative initiative : turnOrder) {
                if (initiative.character.equals(NPC.class)){
                    character = initiative.character;
                }
            }
            /**
             * This is where I need to get and use the information from DoCombat, for attackAction, I need to check if the target is dead, or how much hp it lost,
             * and then apply that to the NPC in the list or move it to the deadNPCs list.
             * For FleeAction, if it was successful then I need to call a MoveAction to move the PC back to the last room they were in.
             *
             * Wait no where does this get called?
             *
             * This doesn't make any sense to me, Combat starts when player enters a "combat room" with hostile creatures
             * then on player's turn they pick either fight or flight. Display then sends this information to this function, which then decides what to do with it.
             * Having a "doCombat" does make sense, but why is it being called immediately? It should be called in response to something.
             *
             * Surely PlayerAction should know what action the player has taken, and then delegate that itself? Although we still then have the problem of communicating with this function.
             * I might not even need this function. Something does need to do the turn order though. Ok I do need this function, but I don't need to delegate anything in here, I think.
             *
             * even if I did I'd want to delegate /if/ something happens, not just delegate immediately
             */
//            doCombatAction=makeDoCombatAction(delegator,turnOrder.get(0).character,character);
            Outcome outcomeX = delegator.delegate(new Action("doCombat",null)).get(0);
            if (outcomeX.successful && outcomeX.variables.contains("attack")){
            }
        }
//        while (combat) {
//            Character character;
//            Initiative init = turnOrder.remove(0);
//            character = init.character;
//            turnOrder.add(init);
//            if (character.equals(pc)) {
//                outcomes.addAll(character.combat(combatState));
//            }
//            for (NPC npc : npcs) {
//                if (npc.isDead) {
//                    deadNPCs.add(npc);
//                } else {
//                    if (character.equals(npc)) {
//                        outcome.message = (npc.name + " attacks you");
//                        if (rng.rollBoolean(20, pc.armor, npc.name)) {
//                            pc.health = pc.health - rng.rollInt(20, 0, npc.name);
//                            if (pc.health <= 0) {
//                                npc.printKillText();
//                                pc.isDead = true;
//                            }
//                        }
//                    }
//                }
//            }
//            if (pc.isDead) {
//                combat = false;
//                aBoolean = true;
//                outcome.message = "Combat Ends";
//                outcomes.add(outcome);
////                pcIsDead();
//                return outcomes;
//            }
//            npcs.removeAll(deadNPCs);
//            if (npcs.isEmpty()) {
//                combat = false;
//                outcome.message = "Combat Ends";
//            }
//        }
        outcomes.add(outcome);
        return outcomes;
    }

    public Room getCurrentRoom(){return getRoom(currentRoom);}

    private Room makeRoom(String roomName,String text,String trapKey){
        nodes.put(roomName,new Room(roomName,text,null,trapsMap.get(trapKey)));
        return nodes.get(roomName);
    }
    private void makeTrap(String name,String room, String text, String killText){
        trapsMap.put(name,new Trap(text,killText));
        nodes.get(room).trap = trapsMap.get(name);
        nodes.get(room).hasTrap = true;
    }
    private void makeItem(String name,String room, String pickupText,String findText){
        items.put(name, new Item(name, pickupText,findText));
        if (room!=null) {
            nodes.get(room).item = items.get(name);
        }
    }
    private void makeNPC(int health,String key,String room, String text, String killText, String name,String itemKey,boolean isAgressive){
        if (itemKey!=null) {
            NPCs.put(key, new NPC(health, nodes.get(room), text, killText, name, items.get(itemKey)));
        }else{
            NPCs.put(key, new NPC(health, nodes.get(room), text, killText, name,null));
        }
        if (isAgressive) {
            nodes.get(room).enemies.add(NPCs.get(key));
        }
        else{
            nodes.get(room).friendlies.add(NPCs.get(key));
        }
    }
    ActionHandler makeMoveAction(){
        return new MoveAction(this);
    }
    ActionHandler makeRollAction(){
        RollAction rollAction = new RollAction(this);
        return rollAction;
    }
    ActionHandler makeItemAction(){return  new ItemAction(this);}
    ActionHandler makeBeginCombatAction(){return new BeginCombatAction(this);}
    DoCombatAction makeDoCombatAction(Delegator delegator,Character character,Character character1){return  new DoCombatAction(delegator,character,character1,this);}
    ActionHandler makePlayerAction(){return new PlayerAction(this);}


//    ActionHandler makeBeginCombatAction(){return  new BeginCombatAction(this);}

//    public ActionHandler makeCombatButtonAction(){return new CombatButtonAction(this);}

//    private void pcIsDead(){
//        String input;
//        while (pc.isDead&&running){
//            System.out.println("YOU ARE DEAD");
//            System.out.println("type restart to begin again, or end to stop playing.");
//            input = inputManager.read();
//            if (input.toLowerCase().equals("restart")){
//                pc.isDead=false;
//                pc.health=10;
//                currentRoom="room.firstRoom";
//            }
//            if (input.toLowerCase().equals("end")) {
//                running = false;
//            }
//            if (pc.isDead&&running) {
//                System.out.println("Type in a proper response");
//            }
//        }
//    }
}

