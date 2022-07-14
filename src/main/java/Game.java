/**
 * The Game class creates a game object which will hold the key parts to creating the game
 * notably including a GameBoard object and two Team objects.
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 * */

import java.util.ArrayList;
import java.util.Collections;

public abstract class Game {

    protected GameBoard board;
    protected static Team team1;
    protected static Team team2;
    private String turn;
    //New Action Modification
    protected static Piece frozen;
    private int frozenTurnPass;

    /**
     * This method call the GameBoard constructor and uses the parameter arguments to create a GameBoard
     * using the number of rows and columns given.
     * @param row
     * @param columns
     */
    private void initializeGameBoard(int row, int columns){
        this.board = new GameBoard(row, columns);
        for (int i = 0; i < team1.getTeamPieces().size(); ++i){
            board.findRandomEmptySpace().setPiece(team1.team.get(i));
        }
        for (int i = 0; i < team2.getTeamPieces().size(); ++i){
            board.findRandomEmptySpace().setPiece(team2.team.get(i));
        }
    }

    /**
     * This constructor creates a new Game, calling the initializeGameBoard method with the arguments of rows and columns,
     * and adds a Team to the Team1 and Team2 attributes.
     * @param rows
     * @param columns
     * @param team1
     * @param team2
     */
    public Game(int rows, int columns, Team team1, Team team2){
        this.team1 = team1;
        this.team2 = team2;
        this.turn = team1.getTeamColor();
        initializeGameBoard(rows, columns);
    }

    /**
     * Getters, which enable the attributes of the game to be obtained.
     */
    public GameBoard getGameBoard() {
        return this.board;
    }

    public Team getCurrentTeam() {
        if (team1.getTeamColor() == this.turn){
            return team1;
        }
        else{
            return team2;
        }
    }

    public Team getOpponentTeam() {
        if (team1.getTeamColor() != this.turn){
            return team1;
        }
        else{
            return team2;
        }
    }

    public boolean isTurn(Team team){
        return team.getTeamColor() == this.turn;
    }

    /**
     * This method returns the two-dimensional array of BoardSquares that the GameBoard holds.
     * @return BoardSquare[][]
     */
    public BoardSquare[][] getBoardSquares(){
        return getGameBoard().getSquares();
    }

    //New Action Modifications
    public Piece getFrozen(){return this.frozen;}
    public void setFrozen(Piece frozen){this.frozen = frozen;}
    public boolean hasFrozenPiece(){return this.frozen != null;}
    public void removeFrozenPiece(){this.frozen = null;}
    public String getTurn(){return this.turn;}

    /**
     * This method sets the attribute turn to the opposite teamColor of the current team whos turn it is.
     */
    public void changeTurn(){
        if (team1.getTeamColor() == this.turn){
            this.turn = team2.getTeamColor();
            //New Action Modification
            if (this.hasFrozenPiece()) {
                if (frozenTurnPass < 5) {
                    ++frozenTurnPass;
                }
                if (frozenTurnPass >= 5) {
                    frozenTurnPass = 0;
                    removeFrozenPiece();
                }
            }
        }
        else{
            this.turn = team1.getTeamColor();
            //New Action Modification
            if (this.hasFrozenPiece()) {
                if (frozenTurnPass < 5) {
                    ++frozenTurnPass;
                }
                if (frozenTurnPass >= 5) {
                    frozenTurnPass = 0;
                    removeFrozenPiece();
                }
            }
        }
    }

    /**
     * This method is an override of the default toString method, while displays the components and information
     * of the game by calling the toString methods of each class on their respective instances.
     * @return String
     */
    public String toString(){
        StringBuilder retString = new StringBuilder();
        retString.append("Game Board:\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getGameBoard().toString())
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getCurrentTeam().toString() + "\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\n" + getOpponentTeam().toString() + "\n")
                .append(String.join("", Collections.nCopies(10 + board.getNumColumns()*8, "*")))
                .append("\nIt is Team " + getCurrentTeam().getTeamColor() + "'s turn\n");
        return retString.toString();
    }

    public abstract boolean isAWinner();
    public abstract Team getWinner();
    public abstract boolean isGameEnded();

}
