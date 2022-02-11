import java.util.Scanner;

public class Room
{
    private String name;
    private int dragons;
    private Player player;
    private int dragCount;

    public Room(String name, Player player)
    {
        this.name = name;
        this.player = player;
        dragons = (int) (Math.random() * (3) + 1);
        dragCount = dragons;
    }

    //creates the room that loops until all dragons are killed
    public void roomCreate()
    {
        Scanner scanner = new Scanner(System.in);
        for (int x = 0; x < dragons; x++)
        {

            if (player.getHealth() == 0)
            {
                break;
            }
            Dragon dragon = new Dragon(player);
            player.setDragon(dragon);
            player.setFirstRoomSearch(true);
            System.out.println("Welcome to the " + name + ", slayer.");
            System.out.println("Currently, " + dragCount + " dragon(s) reside here. Remember to slay all of them. \nAfter doing so you'll move onto the next room.");
            while (player.getCurrentDragonDead() == false)
            {
                if (player.getHealth() == 0)
                {
                    break;

                }

                System.out.println("(Press enter to progress)");
                String progress = scanner.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Current room: " + name + " |||||||| Dragons Left: " + dragCount);
                player.playerMenu();
            }
            dragCount--;
        }
    }

    //getter
    public String getCurrentRoom()
    {
        return name;
    }


}