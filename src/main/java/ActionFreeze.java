import java.util.Scanner;

public class ActionFreeze extends Action{
    public ActionFreeze(GameS22 game, int fromRow, int fromColumn, int toRow, int toColumn) {
        super(game, fromRow, fromColumn, toRow, toColumn);
    }
    @Override
    public void performAction() {
        if (!this.game.hasFrozenPiece()){
            BoardSquare from = this.game.getBoardSquares()[fromRow][fromColumn];
            BoardSquare to = this.game.getBoardSquares()[toRow][toColumn];
            int freezeRow, freezeColumn;
            if (from.getPiece() instanceof PieceBlueHen || from.getPiece() instanceof PieceMinion &&
                    !(from.getPiece() instanceof PieceEvilMinion)) {
                if (to.isEmpty()) {
                    Scanner scr = new Scanner(System.in);
                    while(true) {
                        System.out.println("Enter a row index for the Piece you wish to freeze: ");
                        freezeRow = TextView.getValidInt(toRow - 1, toRow + 2, scr);
                        System.out.println("Enter a column index for the Piece you wish to freeze: ");
                        freezeColumn = TextView.getValidInt(toColumn - 1, toColumn + 2, scr);
                        if (this.game.getGameBoard().inBounds(freezeRow, freezeColumn) && !this.game.getBoardSquares()[freezeRow][freezeColumn].isEmpty()){break;}
                        if (!this.game.getGameBoard().inBounds(freezeRow, freezeColumn)){
                            System.out.println("\u001B[31m" + "This square chosen is out of bounds!" + "\u001B[0m");
                        }
                        if (this.game.getBoardSquares()[freezeRow][freezeColumn].isEmpty()){
                            System.out.println("\u001B[31m" + "The chosen square does not contain a piece!" + "\u001B[0m");
                        }

                    }
                    if (!this.game.getBoardSquares()[freezeRow][freezeColumn].isEmpty() &&
                            this.game.getBoardSquares()[freezeRow][freezeColumn].getPiece().getTeamColor().equals(this.game.getCurrentTeam().getTeamColor())){
                        System.out.println("\u001B[31m" + "You cannot freeze your own piece. Try Again!" + "\u001B[0m");
                    }
                    else if (!this.game.getBoardSquares()[freezeRow][freezeColumn].isEmpty()) {
                        this.game.setFrozen(this.game.getBoardSquares()[freezeRow][freezeColumn].getPiece());
                        this.game.changeTurn();
                    }
                }
            }
            else{
                System.out.println("\u001B[31m" + "This piece cannot perform this action!" + "\u001B[0m");
            }
        }
        else{
            System.out.println("\u001B[31m" + "A piece on the board has already been frozen. Choose another action." + "\u001B[0m");
        }
    }
}
