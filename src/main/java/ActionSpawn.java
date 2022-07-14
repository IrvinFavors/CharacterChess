public class ActionSpawn extends Action{
    //Constructor
    public ActionSpawn(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn) {
        super(game, fromRow, fromColumn, toRow, toColumn);
    }

    /**
     * This method calls the speak method of the piece on the fromSquare, calls the spawn method of that same
     * piece, adds that piece to the current team, and then places the same piece on the toSquare.
     */
    @Override
    public void performAction() {
        BoardSquare from = this.game.getBoardSquares()[fromRow][fromColumn];
        BoardSquare to = this.game.getBoardSquares()[toRow][toColumn];
        from.getPiece().speak();
        Piece temp  = from.getPiece().spawn();
        this.game.getCurrentTeam().addPieceToTeam(temp);
        to.setPiece(temp);
        this.game.changeTurn();
    }
}
