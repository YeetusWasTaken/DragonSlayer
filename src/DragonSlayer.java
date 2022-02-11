import java.util.Scanner;
public class DragonSlayer
{

    private Player currentPlayer;
    private Room room;
    private static int score = 0;
    public DragonSlayer()
    {
        currentPlayer = null;
        room = null;
    }


    //play loop
    public void play()
    {
        Menu();
        setUp();

    }

    //starting menu where every option is seen
    public void Menu()
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Dragon Slayer! (Make a choice!)");

        System.out.println("Do you want to start a (N)ew game?");
        System.out.println("Do you want to view the (H)ighest score?");
        System.out.println("Do you want to (Q)uit the game?");
        String choice = scanner.nextLine();

        while  (!(choice.equals("N") || choice.equals("n")))
        {
            if (choice.equals("H") || choice.equals("h"))
            {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("The highest score currently is: "  + score);
                String progress = scanner.nextLine();
                choice = "";
            }


            else if (choice.equals("Q") || choice.equals("q"))
            {
                System.exit(0);

            }
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("Do you want to start a (N)ew game?");
            System.out.println("Do you want to view the (H)ighest score?");
            System.out.println("Do you want to (Q)uit the game?");
            choice = scanner.nextLine();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("What's your name, dragon slayer?");
        String name = scanner.nextLine();
        currentPlayer = new Player(name, 100);

    }

    //creates room and the array loop for it, it loops for the number of rooms in the array until they are all gone through or the player is dead
    private void setUp()
    {
        String[] roomArr = {"Dark Den", "Dungeon", "Hatchery", "Dragon Hideout", "Reckoning Room"};

        for (String room : roomArr)
        {
            if (currentPlayer.getHealth() ==0)
            {

                gameOver();
            }
            else
            {
                Room room_place = new Room(room, currentPlayer);
                room_place.roomCreate();
                System.out.println("You've cleared out the " + room + "! Onto the next room...");
            }
        }
        winGame();



    }

    //losing game method/restarts loop
    private void gameOver()
    {
        System.out.println("Or so you thought...but it all was just for naught.");
        System.out.println("Game over.");
        System.out.println("You died to a dragon so you didn't get a score.");
        play();
    }

    //Winning game method/restarts loop
    private void winGame()
    {
        System.out.println("You won the game!");
        int endScore = currentPlayer.getGold() * currentPlayer.getSwordAtt() * currentPlayer.getSwordDodge();
        System.out.println("Your score was: " + endScore);
        if (endScore > score)
        {
            score = endScore;
        }
        play();
    }




}
