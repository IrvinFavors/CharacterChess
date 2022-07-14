public class ActionMove extends Action {
    //Constructor
    public ActionMove(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn) {
        super(game, fromRow, fromColumn, toRow, toColumn);
    }

    /**
     * This method moves call the speak method of the piece on the fromSquare, then moves the piece on the
     * fromSquare to the toSquare. Then changes the turn.
     */
    @Override
    public void performAction() {
        BoardSquare from = this.game.getBoardSquares()[fromRow][fromColumn];
        BoardSquare to = this.game.getBoardSquares()[toRow][toColumn];
        from.getPiece().speak();
        to.setPiece(from.removePiece());
        this.game.changeTurn();
    }
}
