import java.util.Scanner;

public class Dragon
{


    private int health;
    private int level;
    private boolean dead;
    private Player player;
    private final int PLAYER_HP_FINAL = 100;

    public Dragon(Player currentPlayer)
    {
        player = currentPlayer;
        health = 100;
        level =  (int) (Math.random() * (3) + 1);
        dead = false;

    }

    //Calculations for taking damage
    public void takeDamage(int dmg)
    {
        health -= dmg;

        if (health > 0)
        {
            System.out.println("The dragon takes " + dmg + " damage and how has " + health + " health.");
        }

        else if (health <= 0 && dead == false)
        {
            health = 0;
            System.out.println("The dragon takes " + dmg + " damage and how has " + health + " health.");

            System.out.println("");

            System.out.println("The dragon has been slayed!");

            dead = true;

            dropDead();
        }

    }

    //returns attack damage to be applied to player
    public int attack()
    {

        int attackDmg =  ((int) (Math.random() * (5) + 1)) * level;
        System.out.println("The dragon attacks for " + (attackDmg) + " health points.");


        return attackDmg;
    }


    //the drop chances and what they apply after slaying dragon
    public void dropDead()
    {
        int chance = (int) (Math.random()*(4)+1);
        if (chance == 1)
        {
            int gold = (int) (Math.random() * (100) + 1);
            System.out.println("The dragon dropped a pouch of " + gold + " gold and you recieved it.");
            player.addGold(gold);
        }
        else if (chance == 2)
        {
            Scanner scanner = new Scanner(System.in);

            System.out.println("You found an upgrade for your sword!");
            System.out.println("Do you want to upgrade your (A)ttack power or your (D)odge speed?");
            String upgrade = scanner.nextLine();
            if (upgrade.equals("A") || upgrade.equals("a"))
            {
                System.out.println("You upgraded your sword attack power!");
                player.upgradeSword(10, 0);
            }
            if (upgrade.equals("d") || upgrade.equals("D"))
            {
                System.out.println("You upgraded your sword dodge speed!");
                player.upgradeSword(0, 10);
            }
        }
        else if (chance == 3)
        {
            System.out.println("You regained some of your health back!");
            player.setHealth((PLAYER_HP_FINAL - player.getHealth() * 3/4) + player.getHealth());
        }
        else
        {
            System.out.println("You found a health pot! Only one can be carried at a time. (TIP: Remember to use them in difficult situations.)");
            player.setHealthPot(true);
        }

        System.out.println("But, with it's final breath...the dragon launches a final attack!");
    }

    //getter methods


    public int getHealth()
    {
        return health;
    }

    public int getLevel()
    {
        return level;
    }

    public boolean isDead()
    {
        return dead;
    }
}
