import java.util.Objects;

public class BonusSquareAction extends Action{

    public BonusSquareAction(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn) {
        super(game, fromRow, fromColumn, toRow, toColumn);
    }

    /**
     * If the piece argument is an instance of the Attacker interface, this method will remove its
     * ability to attack.
     * @param piece
     * @return
     */
    public static Piece removeAttacking(Piece piece){
        if (piece instanceof Attacker) {
            if (piece instanceof PieceBuzz) {
                ((PieceBuzz) piece).setWorkingLaser(false);
            } else if (piece instanceof PieceEvilMinion) {
                ((PieceEvilMinion) piece).hungry = false;
            } else if (piece instanceof PieceBlueHen) {
                ((PieceBlueHen) piece).setNumAttacks(((PieceBlueHen) piece).MAX_NUM_ATTACKS);
            }
        }
        return piece;
    }

    /**
     * If the piece argument is an instance of the Recruiter Class, this method will remove its
     * ability to recruit.
     * @param piece
     * @return
     */
    public static Piece removeRecruiting(Piece piece){
        if (piece instanceof Recruiter){
            if (piece instanceof PieceBlueHen){
                System.out.println("MMMMMMMMMMMMM");
                System.out.println(((PieceBlueHen)piece).canRecruit());
                ((PieceBlueHen) piece).setCanRecruit(false);
                System.out.println("MMMMMMMMMMMMM");
                System.out.println(((PieceBlueHen) piece).canRecruit());
            }
            else if (piece instanceof PieceMinion){
                ((PieceMinion) piece).setCanRecruit(false);
            }
        }
        return piece;
    }

    @Override
    public void performAction() {
        //Bonus BoardSquare Modification
        for (int r = this.toRow-1; r < this.toRow + 2; ++r) {
            for (int c = this.toColumn - 1; c < this.toColumn + 2; ++c) {
                if (this.game.getGameBoard().inBounds(r, c)) {
                    if (this.game.getBoardSquares()[r][c] != this.game.getBoardSquares()[toRow][toColumn]) {
                        //System.out.println("In bounds" + r + c);
                        if (!this.game.getBoardSquares()[r][c].isEmpty()) {
                            //System.out.println("Square is not empty" + r + c);
                            //if (!this.game.getBoardSquares()[r][c].getPiece().getTeamColor().equals(this.game.getBoardSquares()[toRow][toColumn].getPiece().getTeamColor())) {
                            if(this.game.getBoardSquares()[r][c].getPiece().getTeamColor().equals(this.game.getCurrentTeam().getTeamColor())){
                                //System.out.println("Opponent contains piece" + r + c);
                                if ((this.game.getBoardSquares()[r][c].getPiece()) instanceof Attacker) {
                                    //System.out.println("Should remove attacking" + r + c);
                                    removeAttacking(this.game.getBoardSquares()[r][c].getPiece());
                                }
                                if ((this.game.getBoardSquares()[r][c].getPiece()) instanceof Recruiter) {
                                    //System.out.println("Should remove recruiting");
                                    removeRecruiting(this.game.getBoardSquares()[r][c].getPiece());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
