/**
 * The BoardSquare class creates a BoardSquare object which can hold a Piece object.
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 * */

public class BoardSquare {
    private boolean empty;
    private Piece piece;
    private String color;

    /**
     * This constructor creates a board square with a string parameter being used to set the BoardSquare color,
     * the default value for Piece is set to null because there isn't a piece present when the square is created
     * and empty is set to default true.
     * @param color
     */
    public BoardSquare(String color){
        this.color = color;
        this.piece = null;
        this.empty = true;
    }

    /**
     * Getters and Setters, which are used to set or obtain the values present in an object's attributes.
     */
    public Piece getPiece() {
        return this.piece;
    }

    public boolean isEmpty() {
        return this.empty;
    }

    public String getSquareColor(){
        return this.color;
    }

    public void setPiece(Piece piece){
        this.piece = piece;
        this.empty = false;
    }

    /**
     * This method removes the piece in the board square, sets the piece attribute to null, the empty attribute to true,
     * and then returns the Piece that was removed.
     * @return Piece
     */
    public Piece removePiece(){
        Piece temp = this.getPiece();
        this.piece = null;
        this.empty = true;
        return temp;
    }

    /**
     * This method is an override of the default toString method, which is used to display the toString version of
     * Piece objects. This method displays a default string if the BoardSquare is empty.
     * @return String
     */
    @Override
    public String toString(){
        if (this.empty){
            return "-------";
        }
        else{
            return "-" + piece.toString() + "-";
        }
    }
}
