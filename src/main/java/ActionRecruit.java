public class ActionRecruit extends Action{
    //Constructor
    public ActionRecruit(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn) {
        super(game, fromRow, fromColumn, toRow, toColumn);
    }

    /**
     * This method removes the piece on the toSquare, removes that same piece from the opponents team, and also
     * adds that same piece to the current team. Then changes the turn.
     */
    @Override
    public void performAction() {
        BoardSquare from = this.game.getBoardSquares()[fromRow][fromColumn];
        BoardSquare to = this.game.getBoardSquares()[toRow][toColumn];
        from.getPiece().speak();
        Piece temp = to.getPiece();
        this.game.getOpponentTeam().removePieceFromTeam(temp);
        this.game.getCurrentTeam().addPieceToTeam(temp);
        this.game.changeTurn();
    }
}
