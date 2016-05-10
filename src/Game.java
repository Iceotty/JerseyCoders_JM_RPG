import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Joseph on 09/03/2016.
 */
public class Game {
    PlayerCharacter pc;
    String currentRoom;
    Random random;
    HashMap<String,Room> nodes;
    Scanner scanner;
    boolean running = false;
    boolean isDead=false;
    boolean roll;
    public Game(){
        running = true;
        nodes=new HashMap<>();
        makeRoom("room.firstRoom","The dankest of dank rooms",null,null,null).east("room.secondRoom").southEast("room.thirdRoom").south("room.fourthRoom");
        makeRoom("room.secondRoom","Somewhat dank. Has rare pepes on the walls",null,null,null).west("room.firstRoom").south("room.fifthRoom").southEast("room.sixthRoom");
        makeRoom("room.thirdRoom","Barry levels of Dank","You get sliced in half by a chainsaw blade","A chainsaw blade swings towards you from a wall",null).northWest("room.firstRoom").isDeathRoom=true;
        makeRoom("room.fourthRoom","Its okay I guess",null,null,null).north("room.firstRoom").east("room.fifthRoom").southEast("room.seventhRoom").south("room.eighthRoom");
        makeRoom("room.fifthRoom","A plain, empty room with absolutely nothing in it","An arrow trap shoots you in the balls","An arrow trap fires at you",null).southWest("room.seventhRoom").south("room.ninthRoom").north("room.secondRoom").isDeathRoom=true;
        makeRoom("room.sixthRoom","Dank. Really really dank.",null,null,null).northWest("room.secondRoom");
        makeRoom("room.seventhRoom","Has a warm pit of lava",null,null,null).northEast("room.fifthRoom").northWest("room.fourthRoom");
        makeRoom("room.eighthRoom","Has octarine sparkles on the floor.",null,null,null).north("room.fourthRoom").southWest("room.eleventhRoom").east("room.ninthRoom");
        makeRoom("room.ninthRoom","Infested with demons",null,null,null).north("room.fifthRoom").west("room.eighthRoom").south("room.tenthRoom");
        makeRoom("room.tenthRoom","Has some cool loot in it",null,null,null).north("room.ninthRoom").south("room.twelfthRoom").isLocked=true;
        makeRoom("room.eleventhRoom","Dead end, filled with monsters",null,null,null).northEast("room.eighthRoom");
        makeRoom("room.twelfthRoom","Yay! You found the lift to the next floor of the dungeon!",null,null,null).north("room.tenthRoom").isEndRoom=true;
        makeItem("item.key","you got a rusty key","room.sixthRoom");
        makeItem("item.loot","You got some cool loot","room.tenthRoom");
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
        String input;
        while (nextRoom==null&&running){
            input=read();
            nextRoom=room.decide(input);
            if (nextRoom==null){
                System.out.println("Type in a proper response");
            }

        }
        if (nodes.get(nextRoom).isDeathRoom){
            nodes.get(nextRoom).printTrap();
            System.out.println("type roll to roll the outcome");
            if (read().toLowerCase().equals("roll")){
                roll=roll(20,pc.agility);
                if (roll){
                    System.out.println("You successfully dodged the trap");
                    currentRoom=nextRoom;
                }else {
                    isDead = true;
                    System.out.println("YOU ARE DEAD");
                    System.out.println("type restart to begin again, or end to stop playing.");
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
            if (getCurrentRoom().item!=null){
                pc.inventory.put(getCurrentRoom().item.name,getCurrentRoom().item);
                System.out.println(getCurrentRoom().item.text);
            }
            while (isDead&&running){
                input = read();
                if (input.toLowerCase().equals("restart")){
                    isDead=false;
                    currentRoom="room.firstRoom";

                }
                if (input.toLowerCase().equals("end")) {
                    running = false;
                }
                if (isDead&&running) {
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

    private Room makeRoom(String roomName,String text,String killText,String trapText, Item item){
        nodes.put(roomName,new Room(roomName,text,killText,trapText, item));
        return nodes.get(roomName);
    }
    private Item makeItem(String name, String text, String room){
        nodes.get(room).item= new Item(name, text, room);
        return nodes.get(room).item;
    }
    private boolean roll(int max, int min){
        random = new Random();
        int randInt = random.nextInt(max)+1;
        System.out.println("You rolled a "+randInt);
        if (randInt>=min){
            return true;
        }else{
            return false;
        }
    }

}

