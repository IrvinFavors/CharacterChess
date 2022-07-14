public class GameS22 extends Game{

    public GameS22(int rows, int columns, Team team1, Team team2){
        super(rows, columns, team1, team2);
    }

    @Override
    public boolean isAWinner() {
        //New win condition modification
        if(!board.getSquares()[0][0].isEmpty() && board.getSquares()[0][0].getPiece().getTeamColor() == team1.getTeamColor() &&
                !board.getSquares()[board.getNumRows()-1][0].isEmpty() && board.getSquares()[board.getNumRows()-1][0].getPiece().getTeamColor() == team1.getTeamColor() &&
                !board.getSquares()[0][board.getNumColumns()-1].isEmpty() && board.getSquares()[0][board.getNumColumns()-1].getPiece().getTeamColor() == team1.getTeamColor() &&
                !board.getSquares()[board.getNumRows()-1][board.getNumColumns()-1].isEmpty() && board.getSquares()[board.getNumRows()-1][board.getNumColumns()-1].getPiece().getTeamColor() == team1.getTeamColor()
        ){return true;}
        if(!board.getSquares()[0][0].isEmpty() && board.getSquares()[0][0].getPiece().getTeamColor() == team2.getTeamColor() &&
                !board.getSquares()[board.getNumRows()-1][0].isEmpty() && board.getSquares()[board.getNumRows()-1][0].getPiece().getTeamColor() == team2.getTeamColor() &&
                !board.getSquares()[0][board.getNumColumns()-1].isEmpty() && board.getSquares()[0][board.getNumColumns()-1].getPiece().getTeamColor() == team2.getTeamColor() &&
                !board.getSquares()[board.getNumRows()-1][board.getNumColumns()-1].isEmpty() && board.getSquares()[board.getNumRows()-1][board.getNumColumns()-1].getPiece().getTeamColor() == team2.getTeamColor()
        ){return true;}
        else {
            return false;
        }
    }

    /**
     * This method returns who won that game, by return which team is not empty while the other is.
     * @return
     */
    @Override
    public Team getWinner() {
        //New win condition modification
        if(!board.getSquares()[0][0].isEmpty() && board.getSquares()[0][0].getPiece().getTeamColor() == team1.getTeamColor() &&
                !board.getSquares()[board.getNumRows()-1][0].isEmpty() && board.getSquares()[board.getNumRows()-1][0].getPiece().getTeamColor() == team1.getTeamColor() &&
                !board.getSquares()[0][board.getNumColumns()-1].isEmpty() && board.getSquares()[0][board.getNumColumns()-1].getPiece().getTeamColor() == team1.getTeamColor() &&
                !board.getSquares()[board.getNumRows()-1][board.getNumColumns()-1].isEmpty() && board.getSquares()[board.getNumRows()-1][board.getNumColumns()-1].getPiece().getTeamColor() == team1.getTeamColor()
        ){return team1;}
        if(!board.getSquares()[0][0].isEmpty() && board.getSquares()[0][0].getPiece().getTeamColor() == team2.getTeamColor() &&
                !board.getSquares()[board.getNumRows()-1][0].isEmpty() && board.getSquares()[board.getNumRows()-1][0].getPiece().getTeamColor() == team2.getTeamColor() &&
                !board.getSquares()[0][board.getNumColumns()-1].isEmpty() && board.getSquares()[0][board.getNumColumns()-1].getPiece().getTeamColor() == team2.getTeamColor() &&
                !board.getSquares()[board.getNumRows()-1][board.getNumColumns()-1].isEmpty() && board.getSquares()[board.getNumRows()-1][board.getNumColumns()-1].getPiece().getTeamColor() == team2.getTeamColor()
        ){return team2;}
        else {
            return null;
        }
    }

    /**
     * This method check whether the game has a winner.
     * @return
     */
    @Override
    public boolean isGameEnded() {
        return this.isAWinner();
    }

}
