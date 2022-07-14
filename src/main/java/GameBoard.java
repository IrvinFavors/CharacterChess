/**
 * The GameBoard class creates a game board which will hold a two-dimensional
 * array of BoardSquares, which hold Piece objects.
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 * */

public class GameBoard {
    private int numRows;
    private int numColumns;
    private BoardSquare[][] squares;
    private BonusSquare bonusSquare = null;

    /**
     * This constructor assigns the numRows and numColumns attributes and creates a two-dimensional array
     * of board squares which will be assigned to the squares attribute. The method then calls the method
     * setUpEmptyBoard.
     * @param numRows
     * @param numColumns
     */
    public GameBoard(int numRows, int numColumns){
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.squares = new BoardSquare[numRows][numColumns];
        setUpEmptyBoard();
    }

    /**
     * Getters, which can be used to obtain the attributes of a GameBoard.
     */
    public int getNumRows(){
        return this.numRows;
    }

    public int getNumColumns(){
        return this.numColumns;
    }

    public BoardSquare[][] getSquares(){
        return squares;
    }
    public BonusSquare getBonusSquare(){return this.bonusSquare;}

    /**
     * This method returns whether an ordered pair (row and column) are within the bounds of the GameBoard.
     * @param rowIndex
     * @param columnIndex
     * @return boolean
     */
    public boolean inBounds(int rowIndex, int columnIndex){
        return (rowIndex < numRows && rowIndex >= 0) && (columnIndex < numColumns && columnIndex >= 0);
    }

    /**
     * This method sets up and empty board by assigning each square in the two-dimensional array of BoardSquares
     * with a board square, while alternating between the black and white color for the BoardSquares to create
     * a checkered pattern.
     */
    private void setUpEmptyBoard(){
        for (int r = 0; r < numRows; ++r){
            for (int i = 0; i < numColumns; ++i){
                if (i == 0){
                    squares[r][i] = new BoardSquare("Black");
                }
                else if (i % 2 == 0){
                    squares[r][i] = new BoardSquare("Black");
                }
                else{
                    squares[r][i] = new BoardSquare("White");
                }
            }
        }
        //Adding random bonusSquare
        int row = (int)(Math.random() * (numRows));
        int column = (int)(Math.random()*(numColumns));
        squares[row][column] = new BonusSquare(squares[row][column].getSquareColor());
        this.bonusSquare = (BonusSquare) squares[row][column];
    }

    /**
     * This method returns the first random BoardSquare that is empty.
     * @return BoardSquare
     */
    public BoardSquare findRandomEmptySpace(){
        boolean stop = false;
        BoardSquare emptySquare = null;
        while (!stop){
            int r = (int)(Math.random() * (numRows));
            int c = (int)(Math.random() * (numColumns));
            if (squares[r][c].isEmpty()){
                emptySquare = squares[r][c];
                stop = true;
            }
        }
        return emptySquare;
    }

    /**
     * This toString method is an override of the default toString method.
     * This method will create a display of the game board.
     * @return String
     */
    public String toString(){
        StringBuilder boardString = new StringBuilder();
        boardString.append("Col :       ");

        for(int col = 0; col < squares[0].length; col++){
            boardString.append(col + "        ");
        }
        boardString.append("\n");
        for(int row = 0; row < squares.length; row++){
            boardString.append("Row : " + row + "   ");
            for(int col = 0; col < squares[row].length; col++){
                boardString.append(squares[row][col].toString() + "  ");
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }

}
