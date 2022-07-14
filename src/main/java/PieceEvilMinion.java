/**
 * The PieceEvilMinion class creates a PieceEvilMinion object which is
 * an extension of the PieceMinion class.
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 * */

public class PieceEvilMinion extends PieceMinion implements Attacker {
    private int numAttacks;
    protected boolean hungry;

    public static int MAX_NUM_ATTACKS = 4;

    /**
     * This constructor creates a new PieceEvilMinion, setting the attributes as the arguments given
     * in the below parameters.
     * @param symbol
     * @param teamColor
     * @param numRecruits
     * @param numAttacks
     * @param numTimesSpawned
     * @param hidden
     * @param original
     */
    public PieceEvilMinion(char symbol, String teamColor, int numRecruits, int numAttacks,
                           int numTimesSpawned, boolean hidden, boolean original){
        super(symbol, teamColor, numRecruits, numTimesSpawned, hidden, original);
        this.numAttacks = numAttacks;
        updateHungry();
    }

    /**
     * This constructor has no parameters and can be used to create a default PieceEvilMinion.
     */
    public PieceEvilMinion(){
        super('E', "NON", 0, 0, false, true);
        this.numAttacks = 0;
    }

    /**
     * Getter and Setters, which can be used to modify the attributes of a PieceEvilMinion.
     *
     */
    public int getNumAttacks() {
        return numAttacks;
    }

    public boolean canAttack(){
        return this.hungry;
    }

    public void setNumAttacks(int numAttacks){
        this.numAttacks = numAttacks;
    }

    @Override
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        if (fromSquareCol == toSquareCol){return true;}
        else {return fromSquareRow == toSquareRow && Math.abs(toSquareCol - fromSquareCol) == 2;}
    }

    public void updateHungry(){
        this.hungry = numAttacks < MAX_NUM_ATTACKS;
    }

    public void speak(){System.out.println("Roar!");};

    /**
     * This method overrides the spawn method in PieceMinion to create a specialized spawn method for an
     * "evil minion." This method calls the updateHungry methods, increments the number of times the piece has spawned,
     * and then returns the PieceEvilMinion.
     * @return PieceEvilMinion
     */
    public PieceEvilMinion spawn(){
        updateHungry();
        ++this.numTimesSpawned;
        return new PieceEvilMinion(Character.toUpperCase(this.symbol), this.teamColor, 1,
                0, 0, false, false);
    }
}
