import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Joseph on 09/03/2016.
 */
public class Game {
    PlayerCharacter pc =  new PlayerCharacter();
    String currentRoom;
    HashMap<String,Room> nodes;
    HashMap<String,Item> items;
    Scanner scanner;
    boolean running = false;
    boolean isDead=false;
    public Game(){
        running = true;
        nodes=new HashMap<>();
        makeRoom("room.firstRoom","The dankest of dank rooms",null).east("room.secondRoom").southEast("room.thirdRoom").south("room.fourthRoom");
        makeRoom("room.secondRoom","Somewhat dank. Has rare pepes on the walls",null).west("room.firstRoom").south("room.fifthRoom").southEast("room.sixthRoom");
        makeRoom("room.thirdRoom","You get sliced in half by a chainsaw blade",null).northEast("room.firstRoom").isDeathRoom=true;
        makeRoom("room.fourthRoom","Its okay I guess",null).north("room.firstRoom").east("room.fifthRoom").southEast("room.seventhRoom").south("room.eighthRoom");
        makeRoom("room.fifthRoom","An arrow trap shoots you in the balls",null).southWest("room.seventhRoom").south("room.ninthRoom").north("room.secondRoom").isDeathRoom=true;
        makeRoom("room.sixthRoom","Dank. Really really dank.",null).northWest("room.secondRoom").isDeathRoom=true;
        makeRoom("room.seventhRoom","Has a warm pit of lava",null).northEast("room.fifthRoom").northWest("room.fourthRoom");
        makeRoom("room.eighthRoom","Has octarine sparkles on the floor.",null).north("room.fourthRoom").southWest("room.eleventhRoom").east("room.ninthRoom");
        makeRoom("room.ninthRoom","Infested with demons",null).north("room.fifthRoom").west("room.eighthRoom").south("room.tenthRoom");
        makeRoom("room.tenthRoom","Has some cool loot in it",null).north("room.ninthRoom").south("room.twelfthRoom").isLocked=true;
        makeRoom("room.eleventhRoom","Dead end, filled with monsters",null).northEast("room.eighthRoom");
        makeRoom("room.twelfthRoom","Yay! You found the lift to the next floor of the dungeon!",null).north("room.tenthRoom").isEndRoom=true;
        makeItem("item.key","you got a rusty key","room.sixthRoom");
        currentRoom = nodes.get("room.firstRoom").name;
        scanner=new Scanner(System.in);

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
        if (!nodes.get(nextRoom).isLocked){
            currentRoom=nextRoom;
        }
        if (nodes.get(nextRoom).isLocked){
            if (pc.inventory.containsKey("item.key")){
                System.out.println("You unlock the door");
                nodes.get(nextRoom).isLocked=false;
            }else {
                System.out.println("The door is locked, you need a key");
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
            if (nodes.get(currentRoom).isDeathRoom){
                isDead=true;
                getCurrentRoom().print();
                System.out.println("YOU ARE DEAD");
                System.out.println("type restart to begin again, or end to stop playing.");
            }
            while (isDead&&running){
                input = read();
                if (input.toLowerCase().equals("restart")){
                    isDead=false;
                    currentRoom="room.firstRoom";

                }
                if (input.toLowerCase().equals("end")){
                    running=false;
                }
                if (isDead&&running){
                    System.out.println("Type in a proper response");
                }
            }
            if (nodes.get(currentRoom).isEndRoom==true){
                running=false;
                System.out.println("YOU WON ;_;");
            }
        }
    }
    public Room getCurrentRoom(){return getRoom(currentRoom);}

    private Room makeRoom(String roomName, String nodeText, Item item){
        nodes.put(roomName,new Room(roomName,nodeText, item));
        return nodes.get(roomName);
    }
    private Item makeItem(String name, String text, String room){
        nodes.get(room).item= new Item(name, text, room);
        return nodes.get(room).item;
    }
}

