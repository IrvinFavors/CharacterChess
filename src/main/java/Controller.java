import java.util.ArrayList;
import java.util.Objects;

public class Controller {
    private GameS22 game;
    private TextView view;

    public Controller(){
        this.game = setUpGameModel();
        this.view = new TextView();
        this.view.updateView(game);
    }

    public GameS22 setUpGameModel(){
        // Create 4 pieces for team A
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamA = new ArrayList<>();
        piecesTeamA.add(new PieceMinion('M',"Blu",
                0,0,false,true));
        piecesTeamA.add(new PieceBuzz('B',"Blu",2,1,
                true,false,true));
        piecesTeamA.add(new PieceBlueHen('H',"Blu",0,
                9,false,true));
        piecesTeamA.add(new PieceEvilMinion('E',"Blu",1,
                1,4,false, true));
        //New Piece
        piecesTeamA.add(new PieceGru('G', "Blu", 1, 1, false, true));
        // Create a team object
        Team teamA = new Team("Blu",piecesTeamA);
        // Create 4 pieces for team B
        // Load the pieces in an ArrayList
        ArrayList<Piece> piecesTeamB = new ArrayList<>();
        piecesTeamB.add(new PieceMinion('M',"Red",
                0,0,false,true));
        piecesTeamB.add(new PieceBlueHen('H',"Red",0,
                9,false,true));
        piecesTeamB.add(new PieceBuzz('B',"Red",2,1,
                true,false,true));
        piecesTeamB.add(new PieceEvilMinion('E',"Red",1,
                1,4,false, true));
        //New Piece
        piecesTeamB.add(new PieceGru('G', "Red", 1, 1, false, true));
        // Create a team object
        Team teamB = new Team("Red",piecesTeamB);
        // Create an instance of the game
        return new GameS22(8, 8,teamA, teamB);
    }

    /**
     * This method performs an action based, with the indexes of the to and from squares, based
     * on the argument passed to the action parameter.
     *
     * @param fromRow
     * @param fromColumn
     * @param toRow
     * @param toColumn
     * @param action
     */
    public void carryOutAction(int fromRow, int fromColumn, int toRow, int toColumn, char action){
        if (Character.toUpperCase(action) == 'A'){
            new ActionAttack(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
            //BonusSquare Modification
            if (this.game.getBoardSquares()[toRow][toColumn] instanceof BonusSquare){
                if (((BonusSquare) this.game.getBoardSquares()[toRow][toColumn]).isActivated()){
                    new BonusSquareAction(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
                }
            }
        }
        if (Character.toUpperCase(action) == 'M'){
            new ActionMove(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
            //BonusSquare Modification
            if (this.game.getBoardSquares()[toRow][toColumn] instanceof BonusSquare){
                System.out.println("Found Bonus Square");
                if (((BonusSquare) this.game.getBoardSquares()[toRow][toColumn]).isActivated()){
                    System.out.println("Bonus Square is activated");
                    new BonusSquareAction(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
                }
            }
        }
        if (Character.toUpperCase(action) == 'R'){
            new ActionRecruit(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
            //BonusSquare Modification
            if (this.game.getBoardSquares()[toRow][toColumn] instanceof BonusSquare){
                if (((BonusSquare) this.game.getBoardSquares()[toRow][toColumn]).isActivated()){
                    new BonusSquareAction(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
                }
            }
        }
        if (Character.toUpperCase(action) == 'S') {
            new ActionSpawn(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
            //BonusSquare Modification
            if (this.game.getBoardSquares()[toRow][toColumn] instanceof BonusSquare){
                if (((BonusSquare) this.game.getBoardSquares()[toRow][toColumn]).isActivated()){
                    new BonusSquareAction(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
                }
            }
        }
        //New Action Modification
        if (Character.toUpperCase(action) == 'F'){
            new ActionFreeze(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
            //BonusSquare Modification
            if (this.game.getBoardSquares()[toRow][toColumn] instanceof BonusSquare){
                if (((BonusSquare) this.game.getBoardSquares()[toRow][toColumn]).isActivated()){
                    new BonusSquareAction(this.game, fromRow, fromColumn, toRow, toColumn).performAction();
                }
            }
        }
    }

    /**
     * This method continues to ask for an action from the user, then checks if the action is valid, if so
     * the action is carried out, and then the game is printed. This continues until the game has ended.
     */
    public void playGame(){
        while (!this.game.isGameEnded()){
            boolean end = false;
            boolean checkUses = true;
            while (!end){
                this.view.getNextPlayersAction(this.game);
                //New Rule Modification
                checkUses = Rules.checkValidPieceUses(this.game, this.view.getFromSquareRow(), this.view.getFromSquareColumn());
                if (!checkUses){break;}
                end = Rules.checkValidAction(this.game, this.view.getFromSquareRow(), this.view.getFromSquareColumn(),
                                    this.view.getToSquareRow(), this.view.getToSquareColumn(), this.view.getAction());
            }
            //New Rule Modification
            if (!end && !checkUses){this.game.changeTurn();}
            else {carryOutAction(view.getFromSquareRow(), view.getFromSquareColumn(), view.getToSquareRow(), view.getToSquareColumn(), view.getAction());}

            //BonusSquare modification
            //if (this.game.getCurrentTeam().team.size() <= ((this.game.getOpponentTeam().team.size())/2) ||
            //        this.game.getOpponentTeam().team.size() <= ((this.game.getCurrentTeam().team.size())/2)){
            //    this.game.getGameBoard().getBonusSquare().activate();
            //}
            this.game.getGameBoard().getBonusSquare().activate();
            System.out.println(this.game);
        }

        if (this.game.isAWinner()){this.view.printEndOfGameMessage(this.game);}
    }

    public static void main(String[] args) {
        Controller control = new Controller();
        control.playGame();
    }
}
