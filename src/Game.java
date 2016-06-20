import java.util.*;

/**
 * Created by Joseph on 09/03/2016.
 */
public class Game {
    PlayerCharacter pc;
    String currentRoom;
    String previousRoom;
    List<NPC> deadNPCs;
    RandomNumberGenerator rng;
    HashMap<String,Room> nodes;
    HashMap<String, NPC> NPCs;
    HashMap<String,Item> items;
    HashMap<String,Trap> traps;
    boolean running = false;
    boolean roll;
    boolean combat;
    boolean aBoolean = false;
    InputManager inputManager;
    public Game(){
        running = true;
        nodes=new HashMap<>();
        NPCs=new HashMap<>();
        items=new HashMap<>();
        traps=new HashMap<>();
        deadNPCs = new ArrayList<>();
        inputManager = new InputManager();
        makeRoom("room.firstRoom","WELCOME TO THE DUNGEON OF THE MEME").east("room.secondRoom").southEast("room.thirdRoom").south("room.fourthRoom");
        makeRoom("room.secondRoom","Somewhat dank. Has rare pepes on the walls").west("room.firstRoom").south("room.fifthRoom").southEast("room.sixthRoom");
        makeRoom("room.thirdRoom","Barry levels of Dank").northWest("room.firstRoom");
        makeRoom("room.fourthRoom","Its okay I guess").north("room.firstRoom").east("room.fifthRoom").southEast("room.seventhRoom").south("room.eighthRoom");
        makeRoom("room.fifthRoom","A plain, empty room with absolutely nothing in it").southWest("room.seventhRoom").south("room.ninthRoom").north("room.secondRoom");
        makeRoom("room.sixthRoom","Dank. Really really dank.").northWest("room.secondRoom");
        makeRoom("room.seventhRoom","Has a warm pit of lava").northEast("room.fifthRoom").northWest("room.fourthRoom");
        makeRoom("room.eighthRoom","Has octarine sparkles on the floor.").north("room.fourthRoom").southWest("room.eleventhRoom").east("room.ninthRoom");
        makeRoom("room.ninthRoom","Infested with demons").north("room.fifthRoom").west("room.eighthRoom").south("room.tenthRoom");
        makeRoom("room.tenthRoom","Has some cool loot in it").north("room.ninthRoom").south("room.twelfthRoom").isLocked=true;
        makeRoom("room.eleventhRoom","Dead end, filled with monsters").northEast("room.eighthRoom");
        makeRoom("room.twelfthRoom","Yay! You found the lift to the next floor of the dungeon!").north("room.tenthRoom").isEndRoom=true;
        makeItem("item.key","room.sixthRoom","you got a rusty key");
        makeTrap("trap.arrowTrap", "room.fifthRoom","An arrow trap fires at you","An arrow trap shoots you in the balls");
        makeTrap("trap.chainsawTrap","room.thirdRoom","A chainsaw blade swings towards you from a wall","You get sliced in half by a chainsaw blade");
        makeNPC(5,"npc.enemy","room.ninthRoom","You have awakened a slumbering pepe, it attacks you!","You got dank'd by pepe","Pepe",true);
        makeNPC(3,"npc.testEnemy","room.seventhRoom","A slime attacks you","The slime suffocated you","Slime",true);
        makeNPC(3,"npc.slimeEnemy","room.seventhRoom","A pink slime flops towards you","You were absorbed by the slime","Pink Slime",true);
        makeNPC(10,"npc.niceGuy","room.secondRoom","A friendly man greets you in a friendly way","A friendly man killed you","Nice guy",false);
        currentRoom = nodes.get("room.firstRoom").name;
        pc  =  new PlayerCharacter();
        rng = new RandomNumberGenerator();

    }
    public static void main(String ...args){
        Game game = new Game();
        game.gameLoop();
    }
    public Room getRoom(String room){
        return nodes.get(room);
    }
    public void processRoom(Room room){
        String nextRoom=null;
        if (room!=null&&room.allowPrint) {
            room.print();
        }
        previousRoom = currentRoom;
        String input;
        if (!getCurrentRoom().friendlies.isEmpty()){
            for (NPC npc:getCurrentRoom().friendlies){
                npc.printText();
            }

            input = inputManager.read();
            if (input=="attack"){
                getCurrentRoom().enemies = getCurrentRoom().friendlies;
            }
        }
        if (!getCurrentRoom().enemies.isEmpty()){
            for (NPC npc : getCurrentRoom().enemies){
                npc.printText();
            }
            combat = true;
            combat(getCurrentRoom().enemies);
        }
        if (pc.isDead){
            pcIsDead();
            return;
        }
        if (aBoolean){
            aBoolean = false;
            return;
        }
        while (nextRoom==null&&running){
            input=inputManager.read();
            nextRoom=room.decide(input);
            if (nextRoom==null){
                System.out.println("Type in a proper response");
            }
        }
        if (nextRoom != null && nodes.get(nextRoom).trap!=null){
            nodes.get(nextRoom).trap.printTrap();
            System.out.println("Type roll to roll the outcome");
            if (inputManager.read().toLowerCase().equals("roll")){
                roll=rng.rollBoolean(20,pc.agility,"You");
                if (roll){
                    System.out.println("You successfully dodged the trap");
                    currentRoom=nextRoom;
                    getCurrentRoom().trap.hasSprung=true;
                }else {
                    nodes.get(nextRoom).trap.printKillTrap();
                    pc.isDead = true;
                }
            }
        }
        if (pc.isDead){
            pcIsDead();
            return;
        }
        if (!nodes.get(nextRoom).isLocked){
            currentRoom=nextRoom;
        }
        if (getCurrentRoom().hasTrap && !getCurrentRoom().trap.hasSprung){
            currentRoom=previousRoom;
            getCurrentRoom().allowPrint=false;
            System.out.println("Type in a proper response");
        }
        if (nodes.get(nextRoom).isLocked){
            if (pc.inventory.containsKey("item.key")){
                System.out.println("You unlock the door");
                nodes.get(nextRoom).isLocked=false;
                currentRoom=nextRoom;
            }else {
                System.out.println("The door is locked");
                if (inputManager.read().toLowerCase().equals("punch")||equals("break")){
                    System.out.println("You punch the door");
                    if (rng.rollBoolean(20,13,"You")){
                        System.out.println("You broke down the door");
                        nodes.get(nextRoom).isLocked = false;
                        currentRoom = nextRoom;
                    }else{
                        System.out.println("You failed to punch down the door with your tiny baby arms, you now have splinters in your hands");
                        pc.health= pc.health- 1;
                    }
                    if (pc.isDead){
                        pcIsDead();
                        System.out.println("You punched a door so many times you died. GG WP.");
                        return;
                    }
                }
            }
        }
    }
    public void gameLoop(){
        while (running){
            processRoom(getCurrentRoom());
            if (getCurrentRoom().item!=null){
                pc.inventory.put(getCurrentRoom().item.name,getCurrentRoom().item);
                System.out.println(getCurrentRoom().item.text);
                getCurrentRoom().item=null;
            }
            if (nodes.get(currentRoom).isEndRoom==true){
                running=false;
                getCurrentRoom().print();
                System.out.println("YOU WON ;_;");
            }
        }
    }
    private void combat(List<NPC> npcs) {

        List<Initiative> turnOrder = new ArrayList<>();
        turnOrder.add(new Initiative(pc, rng.rollInt(20, 0, null)));
        for (NPC npc : npcs){
            turnOrder.add(new Initiative(npc, rng.rollInt(20, 0, null)));
            if (npc == null || npc.isDead){
                return;
            }
        }
        Collections.sort(turnOrder);
        if (pc.isDead || getCurrentRoom().enemies == null || getCurrentRoom().enemies.isEmpty()) {
            return;
        }
        CombatState combatState = new CombatState(NPCs.values(), turnOrder, getCurrentRoom());
        System.out.println("Combat Starts!");
        while (combat) {
            Character character;
            Initiative init = turnOrder.remove(0);
            character = init.character;
            turnOrder.add(init);
            if (character.equals(pc)) {
                character.combat(combatState);
            }
            for (NPC npc:npcs) {
                if (npc.isDead){
                    deadNPCs.add(npc);
                }else {
                    if (character.equals(npc)) {
                        System.out.println(npc.name + " attacks you");
                        if (rng.rollBoolean(20, pc.armor, npc.name)) {
                            pc.health=pc.health- rng.rollInt(20,0,npc.name);
                            if (pc.health<=0) {
                                npc.printKillText();
                                pc.isDead = true;
                            }
                        }
                    }
                }
            }
            if (pc.isDead){
                combat = false;
                aBoolean = true;
                System.out.println("Combat Ends");
                pcIsDead();
                return;
            }
            npcs.removeAll(deadNPCs);
            if (npcs.isEmpty()) {
                combat = false;
                System.out.println("Combat Ends");
            }

        }
    }

    public Room getCurrentRoom(){return getRoom(currentRoom);}

    private Room makeRoom(String roomName,String text){
        nodes.put(roomName,new Room(roomName,text,null,null));
        return nodes.get(roomName);
    }
    private void makeTrap(String name,String room, String text, String killText){
        traps.put(name,new Trap(text,killText));
        nodes.get(room).trap = traps.get(name);
        nodes.get(room).hasTrap = true;
    }
    private void makeItem(String name,String room, String text){
        items.put(name,new Item(name,text));
        nodes.get(room).item = items.get(name);
    }
    private void makeNPC(int health,String key,String room, String text, String killText, String name,boolean isAgressive){
        NPCs.put(key, new NPC(health,nodes.get(room),text,killText, name));
        if (isAgressive) {
            nodes.get(room).enemies.add(NPCs.get(key));
        }
        else{
            nodes.get(room).friendlies.add(NPCs.get(key));
        }
    }
    private void pcIsDead(){
        String input;
        while (pc.isDead&&running){
            System.out.println("YOU ARE DEAD");
            System.out.println("type restart to begin again, or end to stop playing.");
            input = inputManager.read();
            if (input.toLowerCase().equals("restart")){
                pc.isDead=false;
                pc.health=10;
                currentRoom="room.firstRoom";
            }
            if (input.toLowerCase().equals("end")) {
                running = false;
            }
            if (pc.isDead&&running) {
                System.out.println("Type in a proper response");
            }
        }
    }
}

