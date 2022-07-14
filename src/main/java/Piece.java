/**
 * <h1>Lab5</h1>
 * <h2>CISC 181-022L Spring 2022</h2>
 * <h3>University of Delaware</h3>
 * <p>
 * The Piece class is an abstract class made so that each class extended from it will create
 * objects that are also Piece objects. Instances of the Piece object itself cannot be created due to
 * the class being abstract.
 *
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 */
import java.lang.Math.*;
public abstract class Piece {
    protected char symbol;
    protected String teamColor;
    protected boolean hidden;
    protected boolean original;
    //New Game Rule
    protected int numActions;

    /**
     * This constructor is used to create a Piece objects, the below parameters allow for the attributes
     * of the piece to be set when the object is being created.
     * @param symbol
     * @param teamColor
     * @param hidden
     * @param original
     */
    public Piece(char symbol, String teamColor, boolean hidden, boolean original){
        this.symbol = symbol;
        this.teamColor = teamColor;
        this.hidden = hidden;
        this.original = original;
    }

    /**
     * Getters and Setters, these methods can be used to obtain or modify the attributes
     * of any instance of a Piece object.
     */
    public char getSymbol() {
        return symbol;
    }
    public String getTeamColor() {
        return teamColor;
    }
    public boolean isHidden() { return hidden; }
    public boolean isOriginal() {
        return original;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
    public void setTeamColor(String teamColor) {
        this.teamColor = teamColor;
    }
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    public void setOriginal(boolean original) {
        this.original = original;
    }

    /**
     * This method is an override of the default toString method. The teamColor and symbol of the object will
     * be return in a string.
     * @return String
     */
    @Override
    public String toString(){
        if (this == Game.frozen){return "\u001B[36m" + this.teamColor + " " + this.symbol + "\u001B[0m";}
        else {return this.teamColor + " " + this.symbol;}
    }

    /**
     * This abstract method makes it so that any class extended from the Piece class must obtain
     * a validMovePath method. Which should return whether a piece can make a specific move.
     * @param fromSquareRow
     * @param fromSquareCol
     * @param toSquareRow
     * @param toSquareCol
     * @return boolean
     */
    public abstract boolean validMovePath(int fromSquareRow, int fromSquareCol,
                                          int toSquareRow, int toSquareCol);

    /**
     * This abstract method makes it so that any extension of the Piece class must include a spawn method,
     * which will spawn the piece.
     * @return Piece
     */
    public abstract Piece spawn();

    /**
     * This abstract method makes it so that any extension of the Piece class must include a speak method,
     * which will give the Piece its own unique quip.
     * @return none
     */
    public abstract void speak();

    public abstract boolean validSpawnPath(int fromSquareRow, int fromSquareCol,
                                           int toSquareRow, int toSquareCol);

}
