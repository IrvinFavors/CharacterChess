import java.util.Objects;

//public class ClassName implements InterfaceName1, InterfaceName2
public class Rules {

    public static boolean checkValidPieceUses(Game game, int fromRow, int fromColumn){
        if (!game.getBoardSquares()[fromRow][fromColumn].isEmpty()) {
            //System.out.println("\u001B[31m" + "From Square is not empty" + "\u001B[0m");
            Piece fromPiece = game.getBoardSquares()[fromRow][fromColumn].getPiece();
            //System.out.println("\u001B[31m" + "Created From Piece Variable" + "\u001B[0m");
            if (fromPiece == game.getCurrentTeam().getLastUsed()) {
                System.out.println("\u001B[31m" + "This Piece has been used " + game.getCurrentTeam().countLastUsed + " time(s) in a row" + "\u001B[0m");
                if (game.getCurrentTeam().getCountLastUsed() == 3) {
                    System.out.println("\u001B[31m" + "You have already used this Piece three times in a row!\n your turn has been skipped" + "\u001B[0m");
                    game.getCurrentTeam().setLastUsed(null);
                    game.getCurrentTeam().countLastUsed = 0;
                    return false;
                } else {
                    ++game.getCurrentTeam().countLastUsed;
                }
            } else {
                game.getCurrentTeam().setLastUsed(fromPiece);
                game.getCurrentTeam().countLastUsed = 1;
            }
        }
        return true;
    }
    /**
     * This method checks whether an action can be executed based on which char is given as an argument to
     * action. The general rule is that both the from and go squares should have indexes that are within the
     * bounds of the board, the from squares must not be empty, the to square must be not be empty with an
     * exception for the move action, the piece on the from square must belong to the team's who's turn it is,
     * and the action must take a valid path.
     * @param GameS22
     * @param r1
     * @param c1
     * @param r2
     * @param c2
     * @param action
     * @return
     */
    public static boolean checkValidAction(Game GameS22, int r1, int c1, int r2, int c2, char action){
        BoardSquare from = GameS22.getBoardSquares()[r1][c1];
        BoardSquare to = GameS22.getBoardSquares()[r2][c2];
        boolean b = false;
        if (Character.toUpperCase(action) == 'M'){
            b =     GameS22.getGameBoard().inBounds(r1, r2) &&
                    GameS22.getGameBoard().inBounds(r2, c2) &&
                    !from.isEmpty() &&
                    from.getPiece().getTeamColor().equals(GameS22.getCurrentTeam().getTeamColor()) &&
                    to.isEmpty() &&
                    from.getPiece().validMovePath(r1, c1, r2, c2) &&
                    from.getPiece() != GameS22.getFrozen();
        }
        if (Character.toUpperCase(action) == 'S'){
            b =     !((from.getPiece()) instanceof PieceBuzz) &&
                    GameS22.getGameBoard().inBounds(r1, r2) &&
                    GameS22.getGameBoard().inBounds(r2, c2) &&
                    !from.isEmpty() &&
                    from.getPiece().getTeamColor().equals(GameS22.getCurrentTeam().getTeamColor()) &&
                    to.isEmpty() &&
                    from.getPiece().validSpawnPath(r1, c1, r2, c2) &&
                    from.getPiece() != GameS22.getFrozen();
        }
        if (Character.toUpperCase(action) == 'R'){
            b =     !((from.getPiece()) instanceof PieceBuzz) &&
                    GameS22.getGameBoard().inBounds(r1, r2) &&
                    GameS22.getGameBoard().inBounds(r2, c2) &&
                    !from.isEmpty() &&
                    from.getPiece().getTeamColor().equals(GameS22.getCurrentTeam().getTeamColor()) &&
                    !to.isEmpty() &&
                    !to.getPiece().getTeamColor().equals(GameS22.getCurrentTeam().getTeamColor()) &&
                    ((Recruiter)from.getPiece()).validRecruitPath(r1, c1, r2, c2) &&
                    from.getPiece() != GameS22.getFrozen();
            //BonusSquare Modification
            if(from.getPiece() instanceof PieceBlueHen){
                b = b && ((PieceBlueHen) from.getPiece()).canRecruit();
            }
            if(from.getPiece() instanceof PieceMinion){
                b = b && ((PieceMinion) from.getPiece()).canRecruit();
            }
        }
        if (Character.toUpperCase(action) == 'A'){
            if (from.getPiece() instanceof PieceBuzz || from.getPiece() instanceof PieceBlueHen || from.getPiece() instanceof PieceGru){
                 b =
                        GameS22.getGameBoard().inBounds(r1, r2) &&
                        GameS22.getGameBoard().inBounds(r2, c2) &&
                        !from.isEmpty() &&
                        from.getPiece().getTeamColor().equals(GameS22.getCurrentTeam().getTeamColor()) &&
                        !to.isEmpty() &&
                        !to.getPiece().getTeamColor().equals(GameS22.getCurrentTeam().getTeamColor()) &&
                        ((Attacker)from.getPiece()).validAttackPath(r1, c1, r2, c2) &&
                                from.getPiece() != GameS22.getFrozen();
                //if the piece is an instance of PieceBuzz it should have a working laser
                if (from.getPiece() instanceof PieceBuzz){b = b && ((PieceBuzz) from.getPiece()).canAttack();}
            }
            if (from.getPiece() instanceof PieceEvilMinion){
                 b =
                        GameS22.getGameBoard().inBounds(r1, r2) &&
                        GameS22.getGameBoard().inBounds(r2, c2) &&
                        !from.isEmpty() &&
                        from.getPiece().getTeamColor().equals(GameS22.getCurrentTeam().getTeamColor()) &&
                        !to.isEmpty() &&
                        ((PieceEvilMinion)from.getPiece()).validAttackPath(r1, c1, r2, c2) &&
                                from.getPiece() != GameS22.getFrozen();
                //if the piece is an instance of PieceEvilMinion it should be hungry.
                b = b && ((PieceEvilMinion) from.getPiece()).canAttack();
            }
        }
        if (Character.toUpperCase(action) == 'F'){
            b = GameS22.getGameBoard().inBounds(r1, r2) &&
                    GameS22.getGameBoard().inBounds(r2, c2) &&
                    !from.isEmpty() &&
                    from.getPiece().getTeamColor().equals(GameS22.getCurrentTeam().getTeamColor()) &&
                    to.isEmpty() &&
                    from.getPiece().validMovePath(r1, c1, r2, c2) &&
                    from.getPiece() != GameS22.getFrozen();
        }
        //Prints and error message, indicating to the user that their action does not follow the rules of the game.
        if (from.getPiece() != null && from.getPiece().equals(GameS22.getFrozen())) {
            System.out.println("\u001B[36m" + "This Piece is Frozen! It cannot interact with the Game at this time." + "\u001B[0m");
        }
        if (!b){System.out.println("\u001B[31m" + "This action is not allowed!" + "\u001B[0m");};
        return b;
    }
}
