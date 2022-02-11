import java.util.Scanner;
public class Player
{
  
  //instance variables
  private String playerName;
  private int health;
  private int gold;
  private boolean healthPotStatus;
  private Sword sword;
  private Dragon currentDragon;
  private boolean dead; 
  private boolean firstRoomSearch;
  private final int PLAYER_HP_FINAL = 100;

  
  //Constructor
  public Player(String playerName, int health)
  {
    currentDragon = null;
    this.playerName = playerName;
    this.health = health;
    gold = 0; 
    healthPotStatus = false;
    sword = new Sword(2, 20);
    dead = false;
    firstRoomSearch = true;
  }
  
  //directly attacks current dragon 
  public void attack(Dragon currentDragon)
   {
 
     int attackDmg = sword.getAttackPower() * (int) (Math.random()*(10)+1);
     System.out.println("You attack the dragon for " + (attackDmg) + " health points.");
     
 
     currentDragon.takeDamage(attackDmg);
   }

  //taking damage
  public void takeDamage(int dmg)
   {
     int rand = 0;

     rand = (int) (Math.random() * (100) + 1);
     if (rand <= sword.getDodgeRating())
     {
       //dodge
       dmg = 0;
       System.out.println("With quick thinking on your side, you dodged the dragon's attack! You took no damage!");
     }
     else
     {
     health -= dmg;
    
     if (health > 0)
     {
     System.out.println("You took " + dmg + " damage and you now have " + health + " health.");
     }
 
     else if (health <= 0 && dead == false)
     {
       health = 0;
     System.out.println("You took " + dmg + " damage and you how have " + health + " health.");
 
     System.out.println("");
 
     dead = true;
     }
     }
   }

  //sword upgrade
   public void upgradeSword(int newAtt, int newDod)
   {
     sword.updateAttackPower(sword.getAttackPower() + newAtt);
     sword.updateDodgeRating(sword.getDodgeRating() + newDod);
   }

  //Menu where everything happens, displays basically everything the player sees
   public void playerMenu()
   {
     Scanner scanner = new Scanner(System.in);
    String choice = "";
    System.out.println("--------------------------------------------------------");
     System.out.println("Make a choice, your goal is to defeat all the dragons.");
     System.out.println("--------------------------------------------------------");
     System.out.println("(S)earch the room.");
     System.out.println("(A)ttack the dragon.");
     System.out.println("(U)se your health pot.");
     System.out.println("(O)bserve your sword stats.");
     System.out.println("(C)heck the dragon's status.");

    System.out.println("======================================================");      String hp = "Currently you have " + health + " health";
    if (healthPotStatus == true)
    {
      hp += " and one health pot to use!";
    }
    else
    {
      hp += " and no health pots to use.";
    }
    System.out.println(hp);
    System.out.println("======================================================");
    System.out.print("Make the fateful choice: ");
    choice = scanner.nextLine();
    System.out.print("\033[H\033[2J");
    System.out.flush();
    System.out.println("Your Turn: ");
    System.out.println("--------------------------------------------------------");
    processChoice(choice); 
   }

    //where choices are processed
   public void processChoice(String choice)
   {
     if (choice.equals("S") || choice.equals("s"))
    {
      //add a first search here 
      if ( ((int) (Math.random() * (2) + 1)) == 2 && healthPotStatus == false && firstRoomSearch == true)
      {
        System.out.println("You found a health pot! (TIP: Remember to use them in difficult situations.)");
        
        setHealthPot(true);
        setFirstRoomSearch(false);
      }
      else
      {
        System.out.println("You didn't find anything or you were already carring too much.");
        //remove to find infinite health pots lol
        setFirstRoomSearch(false);

      }
    }
    else if (choice.equals("A") || choice.equals("a"))
    {
     attack(currentDragon);
     System.out.println("======================================================");
     System.out.println("Dragon's Turn:");
     System.out.println("--------------------------------------------------------");
  
     takeDamage(currentDragon.attack());
    }
    else if (choice.equals("U") || choice.equals("u"))
    {
      if (healthPotStatus == true && health <= PLAYER_HP_FINAL)
      {
      System.out.println("You used your health pot and regained back all your hp!");
      setHealthPot(false);
      setHealth(PLAYER_HP_FINAL);
      }
      else if (healthPotStatus == true && health >= PLAYER_HP_FINAL)
      {
        System.out.println("Seems like the health pot had no effect...");
      }
      else
      {
        System.out.println("You don't have a health pot to use.");
        System.out.println("======================================================");
        System.out.println("Dragon's Turn:");
        System.out.println("--------------------------------------------------------");
        takeDamage(currentDragon.attack());
        
      }
    }
    else if (choice.equals("O") || choice.equals("o"))
    {
      System.out.println("Currently you have " + sword.getAttackPower() + " attack power and " + sword.getDodgeRating() + " dodge on your sword.");
    }
    else if (choice.equals("C") || choice.equals("c"))
    {
      System.out.println("You realized that is a level " + currentDragon.getLevel() + " dragon, with currently " + currentDragon.getHealth() + " health.");
      System.out.println("======================================================");
      System.out.println("Dragon's Turn:");
      System.out.println("--------------------------------------------------------");
      takeDamage(currentDragon.attack());
      
    }

    else
    {
      System.out.println("Yikes! That's an invalid option! Try again.");
    }
   }

  //getters/setters
   public void addGold(int newGold)
   {
     gold += newGold;
   }

   public void setHealth(int newHP)
   {
     health = newHP;
   }
   public int getHealth()
   {
     return health;
   }
   public void setHealthPot(boolean state)
   {
     healthPotStatus = state; 
   }

   public boolean getCurrentDragonDead()
   {
     return currentDragon.isDead();
   }

   public void setDragon(Dragon dragon)
   {
     currentDragon = dragon;
   }

   public void setFirstRoomSearch(boolean state)
   {
     firstRoomSearch = state;
   }

   public int getGold()
   {
     return gold;
   }

   public int getSwordAtt()
   {
     return sword.getAttackPower();
   }

  public int getSwordDodge()
  {
    return sword.getDodgeRating();
  }
}