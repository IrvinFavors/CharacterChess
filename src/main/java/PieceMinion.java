/**
 * The PieceMinion class is an extension of the Piece class and
 * creates a new Minion Object. It has the ability to recruit
 * new pieces up to three.
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 * */
public class PieceMinion extends Piece implements Recruiter {
    private int numRecruits;
    protected int numTimesSpawned;
    private boolean canRecruit;

    public static int MAX_NUM_SPAWNED = 3;

    public PieceMinion(char symbol, String teamColor,
                       int numRecruits, int numTimesSpawned,
                       boolean hidden, boolean original) {
        super(symbol, teamColor, hidden, original);
        this.numRecruits = numRecruits;
        this.numTimesSpawned = numTimesSpawned;
        this.canRecruit = true;
    }

    public PieceMinion(){
        this('M',"- -",
                0,0,
                false,true);
    }

    public int getNumRecruits() {
        return numRecruits;
    }


    @Override
    public boolean validRecruitPath(int fromSquareRow, int fromSquareCol,
                                    int toSquareRow, int toSquareCol) {
        if (fromSquareCol == toSquareCol) {
            return true;
        } else {
            return fromSquareRow == toSquareRow && Math.abs(toSquareCol - fromSquareCol) == 2;
        }
    }

    public int getNumTimesSpawned() {
        return numTimesSpawned;
    }


    public void setNumRecruits(int numRecruits) {
        this.numRecruits = numRecruits;
    }

    //BonusSquare Modification
    public boolean canRecruit(){return this.canRecruit;}
    //BonusSquare Modification
    public void setCanRecruit(boolean canRecruit){this.canRecruit = canRecruit;}

    /**
     * Prints tagline to the screen
     * @return void
     */
    public void speak(){
        System.out.println("Bello!");
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
        if (fromSquareCol == toSquareCol){return true;}
        else {return fromSquareRow == toSquareRow && Math.abs(toSquareCol - fromSquareCol) == 2;}
    }
    /**
     * Spawns in a new piece which is a copy of the current piece,
     * but sets original to false
     * @return minion
     */
    public PieceMinion spawn(){
        return new PieceMinion(Character.toUpperCase(this.symbol),
                this.teamColor,1,
                0,
                false,
                false);
    }
    /**
     * Checks if a new piece can spawn
     * @return if can spawn
     */
    public boolean canSpawn(){
        return original && numTimesSpawned < MAX_NUM_SPAWNED;
    }

    @Override
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol,
                                  int toSquareRow, int toSquareCol){
        if (fromSquareCol == toSquareCol){return true;}
        else {return fromSquareRow == toSquareRow && Math.abs(toSquareCol - fromSquareCol) == 2;}
    }
}