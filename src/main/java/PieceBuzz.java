/**
 * The PieceBuzz class creates a game piece modeled after Buzz Lightyear
 * It has basic attributes like the number of times attacked and
 * if its laser is working.
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 * */
public class PieceBuzz extends Piece implements Attacker{
    private int numAttacks;
    private int numTimesBeenAttacked;
    private boolean workingLaser;

    public PieceBuzz(char symbol,
                     String teamColor,
                     int numAttacks,
                     int numTimesBeenAttacked,
                     boolean workingLaser,
                     boolean hidden,
                     boolean original) {
        super(symbol, teamColor, hidden, original);
        this.numAttacks = numAttacks;
        this.numTimesBeenAttacked = numTimesBeenAttacked;
        this.workingLaser = workingLaser;
    }

    public PieceBuzz(){
        this('B',"- -",
                0,0,
                true, false, true);
    }

    public int getNumAttacks() {
        return numAttacks;
    }
    public int getNumTimesBeenAttacked() {
        return numTimesBeenAttacked;
    }
    public boolean canAttack(){
        return workingLaser;
    }

    public void setWorkingLaser(boolean workingLaser) {
        this.workingLaser = workingLaser;
    }

    public void setNumAttacks(int numAttacks)  {
        this.numAttacks = numAttacks;
    }

    @Override
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        if (toSquareRow == fromSquareRow){return true;}
        else {return fromSquareCol == toSquareCol && Math.abs(fromSquareRow-toSquareRow) == 2;}
    }

    public void updateNumTimesBeenAttacked(){
        this.numTimesBeenAttacked += 1;
        this.workingLaser = false;
    }

    /**
     * Prints tagline to the screen
     * @param
     * @return void
     */
    public void speak(){
        System.out.println("To Infinity and Beyond!");
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
        //PieceBuzz can move anywhere on the board.
        return true;
    }

    /**
     * Spawns in a new piece
     * @return null
     */
    public Piece spawn(){
        return null;
    }

    /**
     * Checks if a new piece can spawn
     * @return false
     */
    public boolean canSpawn(){
        return false;
    }

    @Override
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol,
                                  int toSquareRow, int toSquareCol) {
        return false;
    }

}