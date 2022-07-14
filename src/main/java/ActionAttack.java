public class ActionAttack extends Action{
    //Constructor
    public ActionAttack(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn) {
        super(game, fromRow, fromColumn, toRow, toColumn);
    }

    /**
     * This method performs an attack action, it calls the speak method of the piece on the from square,
     * removes the piece on the to square, while removing that same piece from the opponent's team. Then moves the
     * piece on the from square to the to square. If the fromSquare piece is an instance of PieceEvilMinion and the
     * toSquare piece is an instance of a PieceMinion belonging to the current team, the user has the ability to
     * attack their own piece and turn it into an instance of PieceEvilMinion. After which, the turn changes.
     */
    @Override
    public void performAction() {
        BoardSquare from = this.game.getBoardSquares()[fromRow][fromColumn];
        BoardSquare to = this.game.getBoardSquares()[toRow][toColumn];
        if (from.getPiece() instanceof PieceEvilMinion && to.getPiece() instanceof PieceMinion &&
            to.getPiece().getTeamColor().equals(from.getPiece().getTeamColor())){
            from.getPiece().speak();
            PieceMinion temp = (PieceMinion) to.removePiece();
            this.game.getCurrentTeam().removePieceFromTeam(temp);

            /*PieceEvilMinion newPiece1 = new PieceEvilMinion(temp.getSymbol(), from.getPiece().getTeamColor(),
                                                   temp.getNumRecruits(), 0, temp.getNumTimesSpawned(),
                                                   temp.isHidden(), temp.isOriginal());

             */
            PieceEvilMinion newPiece = new PieceEvilMinion();
            this.game.getCurrentTeam().addPieceToTeam(newPiece);
            from.setPiece(newPiece);
            this.game.changeTurn();
        }
        if (!(to.getPiece().getTeamColor().equals(this.game.getCurrentTeam().getTeamColor()))){
            from.getPiece().speak();
            Piece temp = to.removePiece();
            this.game.getOpponentTeam().removePieceFromTeam(temp);
            Piece moved = from.removePiece();
            to.setPiece(moved);
            this.game.changeTurn();
        }

    }
}
