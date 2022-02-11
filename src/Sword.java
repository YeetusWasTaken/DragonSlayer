public class Sword
{

    private int attackPower;
    private int dodgeRating;

    public Sword(int attackPower, int dodgeRating)
    {
        this.attackPower = attackPower;

        this.dodgeRating = dodgeRating;
    }

    //getters/setters
    public int getAttackPower()
    {
        return attackPower;
    }
    public int getDodgeRating()
    {
        return dodgeRating;
    }
    public void updateAttackPower(int newAttackPower)
    {
        attackPower = newAttackPower;
    }
    public void updateDodgeRating(int newDodgeRating)
    {
        dodgeRating = newDodgeRating;
    }


}


