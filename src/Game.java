import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Joseph on 09/03/2016.
 */
public class Game {
    PlayerCharacter pc;
    String currentRoom;
    String previousRoom;
    Random random;
    HashMap<String,Room> nodes;
    HashMap<String, NPC> NPCs;
    HashMap<String,Item> items;
    HashMap<String,Trap> traps;
    Scanner scanner;
    boolean running = false;
    boolean roll;
    boolean combat;
    public Game(){
        running = true;
        nodes=new HashMap<>();
        NPCs=new HashMap<>();
        items=new HashMap<>();
        traps=new HashMap<>();
        makeRoom("room.firstRoom","The dankest of dank rooms").east("room.secondRoom").southEast("room.thirdRoom").south("room.fourthRoom");
        makeRoom("room.secondRoom","Somewhat dank. Has rare pepes on the walls").west("room.firstRoom").south("room.fifthRoom").southEast("room.sixthRoom");
        makeRoom("room.thirdRoom","Barry levels of Dank").northWest("room.firstRoom").isDeathRoom=true;
        makeRoom("room.fourthRoom","Its okay I guess").north("room.firstRoom").east("room.fifthRoom").southEast("room.seventhRoom").south("room.eighthRoom");
        makeRoom("room.fifthRoom","A plain, empty room with absolutely nothing in it").southWest("room.seventhRoom").south("room.ninthRoom").north("room.secondRoom").isDeathRoom=true;
        makeRoom("room.sixthRoom","Dank. Really really dank.").northWest("room.secondRoom");
        makeRoom("room.seventhRoom","Has a warm pit of lava").northEast("room.fifthRoom").northWest("room.fourthRoom");
        makeRoom("room.eighthRoom","Has octarine sparkles on the floor.").north("room.fourthRoom").southWest("room.eleventhRoom").east("room.ninthRoom");
        makeRoom("room.ninthRoom","Infested with demons").north("room.fifthRoom").west("room.eighthRoom").south("room.tenthRoom");
        makeRoom("room.tenthRoom","Has some cool loot in it").north("room.ninthRoom").south("room.twelfthRoom").isLocked=true;
        makeRoom("room.eleventhRoom","Dead end, filled with monsters").northEast("room.eighthRoom");
        makeRoom("room.twelfthRoom","Yay! You found the lift to the next floor of the dungeon!").north("room.tenthRoom").isEndRoom=true;
        makeItem("item.key","you got a rusty key");
        addItem("item.key","room.sixthRoom");
        makeTrap("trap.arrowTrap","An arrow trap shoots you in the balls","An arrow trap fires at you");
        makeTrap("trap.chainsawTrap","You get sliced in half by a chainsaw blade","A chainsaw blade swings towards you from a wall");
        addTrap("trap.chainsawTrap","room.thirdRoom");
        addTrap("trap.arrowTrap", "room.fifthRoom");
        makeNPC("npc.enemy","room.ninthRoom","You have awakened a slumbering pepe, it attacks you!","You got dank'd by pepe","Pepe");
        currentRoom = nodes.get("room.firstRoom").name;
        scanner=new Scanner(System.in);
        pc  =  new PlayerCharacter();

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
        if (room!=null) {
            room.print();
        }
        previousRoom = currentRoom;
        String input;
        while (nextRoom==null&&running){
            input=read();
            nextRoom=room.decide(input);
            if (nextRoom==null){
                System.out.println("Type in a proper response");
            }

        }
        if (nodes.get(currentRoom).npc!=null&&nodes.get(currentRoom).npc.isAggressive){
            combat=true;
            nodes.get(currentRoom).npc.printText();

        }
        if (nodes.get(nextRoom).trap!=null){
            traps.get(nodes.get(nextRoom).trap).printTrap();
            System.out.println("Type roll to roll the outcome");
            if (read().toLowerCase().equals("roll")){
                roll=rollBoolean(20,pc.agility,"You");
                if (roll){
                    System.out.println("You successfully dodged the trap");
                    currentRoom=nextRoom;
                }else {
                    pc.isDead = true;
                }
            }
        }
        if (!nodes.get(nextRoom).isLocked){
            currentRoom=nextRoom;
        }
        if (nodes.get(nextRoom).isLocked){
            if (pc.inventory.containsKey("item.key")){
                System.out.println("You unlock the door");
                nodes.get(nextRoom).isLocked=false;
                currentRoom=nextRoom;
            }else {
                System.out.println("The door is locked");
            }
        }
    }
    public String read(){
        return scanner.nextLine();
    }
    public void gameLoop(){
        String input;
        while (running){
            processRoom(getCurrentRoom());
            if (combat){
                if (nodes.get(currentRoom).npc==null||pc.isDead){
                    combat=false;
                }
                System.out.println("Type roll to roll for initiative");
                if (read().toLowerCase().equals("roll")){
                    if (rollInt(20,0,"You")>rollInt(20,0,null)){
                        pc.isTurn=true;
                    }
                    else {
                        nodes.get(currentRoom).npc.isTurn=true;
                    }
                }
                if (pc.isTurn){
                    System.out.println("It is your turn, type attack to attack, or run to flee");
                    if (read().toLowerCase().equals("run")){
                        System.out.println("You run to the previous room");
                        currentRoom = previousRoom;
                    }
                    if (!pc.isDead&&read().toLowerCase().equals("attack")){
                        System.out.println("You attack "+nodes.get(currentRoom).npc.name);
                        if(rollBoolean(20,11,"You")){
                            System.out.println("You killed "+nodes.get(currentRoom).npc.name);
                            nodes.get(currentRoom).npc=null;
                        }else {
                            System.out.println("You completely missed "+nodes.get(currentRoom).npc.name);
                        }
                    }
                    pc.isTurn=false;
                    nodes.get(currentRoom).npc.isTurn=true;
                }
                if (nodes.get(currentRoom).npc.isTurn=true){
                    System.out.println(nodes.get(currentRoom).npc.name+" attacks you");
                    if (rollBoolean(20,pc.armor,nodes.get(currentRoom).npc.name)){
                        nodes.get(currentRoom).npc.printKillText();
                        pc.isDead=true;
                    }
                }

            }
            if (getCurrentRoom().item!=null){
                pc.inventory.put(getCurrentRoom().item.name,getCurrentRoom().item);
                System.out.println(getCurrentRoom().item.text);
                getCurrentRoom().item=null;
            }
            while (pc.isDead&&running){
                System.out.println("YOU ARE DEAD");
                System.out.println("type restart to begin again, or end to stop playing.");
                input = read();
                if (input.toLowerCase().equals("restart")){
                    pc.isDead=false;
                    currentRoom="room.firstRoom";
                }
                if (input.toLowerCase().equals("end")) {
                    running = false;
                }
                if (pc.isDead&&running) {
                    System.out.println("Type in a proper response");
                }
            }
            if (nodes.get(currentRoom).isEndRoom==true){
                running=false;
                getCurrentRoom().print();
                System.out.println("YOU WON ;_;");
            }
        }
    }
    public Room getCurrentRoom(){return getRoom(currentRoom);}

    private Room makeRoom(String roomName,String text){
        nodes.put(roomName,new Room(roomName,text,null,null,null));
        return nodes.get(roomName);
    }
    private void addTrap(String trap, String room){
        nodes.get(room).trap = traps.get(trap);
    }
    private Trap makeTrap(String name, String text, String killText ){
        traps.put(name,new Trap(text,killText));
        return traps.get(name);
    }
    private Item makeItem(String name, String text){
        items.put(name,new Item(name,text));
        return items.get(name);
    }
    private NPC makeNPC(String key,String room, String text, String killText, String name){
        NPCs.put(key, new NPC(nodes.get(room),text,killText, name));
        nodes.get(room).npc = NPCs.get(key);
        return NPCs.get(key);

    }
    private void addItem(String item, String room){
        nodes.get(room).item = items.get(item);
    }
    private boolean rollBoolean(int max, int min,String roller){
        random = new Random();
        int randInt = random.nextInt(max)+1;
        System.out.println(roller+" rolled a "+randInt);
        if (randInt>=min){
            return true;
        }else{
            return false;
        }
    }
    private int rollInt(int max, int min, String roller){
        random = new Random();
        int randInt = random.nextInt(max)+1;
        if(roller!=null){
            System.out.println(roller+" rolled a "+randInt);
        }
        return randInt;

    }

}

