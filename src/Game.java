import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Joseph on 09/03/2016.
 */
public class Game {
    String currentRoom;
    HashMap<String,Room> nodes;
    Scanner scanner;
    boolean running = false;
    boolean isDead=false;
    public Game(){
        running = true;
        nodes=new HashMap<>();
        makeRoom("room.firstRoom","The dankest of dank rooms").east("room.secondRoom").southEast("room.thirdRoom").south("room.fourthRoom");
        makeRoom("room.secondRoom","Somewhat dank. Has rare pepes on the walls").west("room.firstRoom").south("room.fifthRoom").southEast("room.sixthRoom");
        makeRoom("room.thirdRoom","No dank memes in this one").northEast("room.firstRoom").isDeathRoom=true;
        makeRoom("room.fourthRoom","Its okay I guess").north("room.firstRoom").east("room.fifthRoom").southEast("room.seventhRoom").south("room.eighthRoom");
        makeRoom("room.fifthRoom","An arrow trap shoots you in the balls").southWest("room.seventhRoom").south("room.ninthRoom").north("room.secondRoom").isDeathRoom=true;
        makeRoom("room.sixthRoom","Dank. Really really dank.").northWest("room.secondRoom").isDeathRoom=true;
        makeRoom("room.seventhRoom","Has a warm pit of lava").northEast("room.fifthRoom").northWest("room.fourthRoom");
        makeRoom("room.eighthRoom","Has octarine sparkles on the floor.").north("room.fourthRoom").southWest("room.eleventhRoom");
        makeRoom("room.ninthRoom","Infested with demons").north("room.fifthRoom").west("room.eighthRoom").south("room.tenthRoom");
        makeRoom("room.tenthRoom","Has some cool loot in it").north("room.ninthRoom").south("room.twelfthRoom");
        makeRoom("room.eleventhRoom","Dead end, filled with monsters").northEast("room.eighthRoom");
        makeRoom("room.twelfthRoom","Yay! You found the lift to the next floor of the dungeon!").north("room.tenthRoom").isEndRoom=true;
        currentRoom = nodes.get("room.firstRoom").name;
//        nodes.get("node.eatNode").addPath("die","node.deadNode");
        scanner=new Scanner(System.in);
//        currentNode=firstNode.name;

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
        currentRoom=nextRoom;
    }
    public String read(){
        return scanner.nextLine();
    }
    public void gameLoop(){
        String input;
        while (running){
            processRoom(getCurrentRoom());
            if (nodes.get(currentRoom).isDeathRoom){
                isDead=true;
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
    public Room getCurrentRoom(){
        return getRoom(currentRoom);
    }

    private Room makeRoom(String roomName, String nodeText){
        nodes.put(roomName,new Room(roomName,nodeText));
        return nodes.get(roomName);
    }
}

