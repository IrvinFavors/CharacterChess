/**
 * The PieceBlueHen class creates a blue hen object which is
 * an extension of the Piece class. It has an attributes allowing
 * it to fly and recruit other pieces
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 * */
public class PieceBlueHen extends Piece implements Attacker, Recruiter{
    private int numAttacks;
    private int numRecruits;
    private boolean canFly;
    //BonusSquare Modification
    private boolean canRecruit;

    final public int MAX_NUM_ATTACKS = 3;

    public PieceBlueHen (char symbol,
                         String teamColor,
                         int numAttacks, int numRecruits,
                         boolean hidden, boolean original){
        super(symbol, teamColor, hidden, original);
        this.numAttacks = numAttacks;
        this.numRecruits = numRecruits;
        this.canRecruit = true;
        updateFly();
    }

    public PieceBlueHen ()  {
        this('H',"NON",
                0,0,
                false,true);

    }

    //BonusSquare Modification
    public boolean canRecruit(){return this.canRecruit;}
    //BonusSquare Modification
    public void setCanRecruit(boolean canRecruit){this.canRecruit = canRecruit;}


    public int getNumAttacks()    {
        return this.numAttacks;
    }
    public int getNumRecruits(){
        return this.numRecruits;
    }
    public boolean canFly()    {
        return this.canFly;
    }


    public void setNumAttacks(int numAttacks)    {
        this.numAttacks = numAttacks;
        updateFly();
    }

    @Override
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        if (this.canFly()){return true;}
        else {return fromSquareRow == toSquareRow && Math.abs(fromSquareCol - toSquareCol) == 1;}
    }

    public void setNumRecruits(int numRecruits)    {
        this.numRecruits = numRecruits;
    }

    @Override
    public boolean validRecruitPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        if (this.canFly()) {
            return true;
        } else {
            return fromSquareCol == toSquareCol && Math.abs(fromSquareRow - toSquareRow) == 1;
        }
    }

    /**
     * Updates the fly attribute.
     * @param
     * @return void
     */
    private void updateFly()    {
        if (this.numAttacks < MAX_NUM_ATTACKS){
            this.canFly = true;
        }
        else {
            this.canFly = false;
        }
    }
    /**
     * Prints tagline to the screen
     * @param
     * @return void
     */
    public void speak(){
        System.out.println("Go UD!");
    }

    @Override
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        if (this.canFly()){return true;}
        else {return Math.abs(toSquareCol - fromSquareCol) == 1 && Math.abs(toSquareRow - fromSquareRow) == 1;}
    }

    /**
     * Defines a valid move path for this specific character
     * @param fromSquareRow
     * @param fromSquareCol
     * @param toSquareRow
     * @param toSquareCol
     * @return true
     */
    public boolean validMovePath(int fromSquareRow, int fromSquareCol,
                                 int toSquareRow, int toSquareCol) {
        System.out.println(this.canFly());
        if (this.canFly()){return true;}
        else {return (Math.abs(fromSquareRow - toSquareRow) <= 1) &&
                     (Math.abs(fromSquareCol-toSquareCol) <= 1);}
        }

    /**
     * Spawns in a new piece whic is a copy of the current piece,
     * but sets original to false
     * @return copyHen
     */
    public PieceBlueHen spawn()    {
        PieceBlueHen copyHen =
                new PieceBlueHen(Character.toUpperCase(this.symbol),
                        this.teamColor,this.numAttacks,this.numRecruits,
                        false,false);
        return copyHen;
    }
    /**
     * Checks if a new piece can spawn
     * @return true
     */
    public boolean canSpawn(){
        return true;
    }
}