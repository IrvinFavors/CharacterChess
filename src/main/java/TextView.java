import java.util.Scanner;

public class TextView {
    private int fromSquareRow;
    private int fromSquareColumn;
    private int toSquareRow;
    private int toSquareColumn;
    private char action;

    public int getFromSquareRow() {
        return this.fromSquareRow;
    }

    public int getFromSquareColumn() {
        return this.fromSquareColumn;
    }

    public int getToSquareRow() {
        return this.toSquareRow;
    }

    public int getToSquareColumn() {
        return this.toSquareColumn;
    }

    public char getAction() {
        return this.action;
    }

    /**
     * This method takes input from the user and returns the first character to match
     * an action. Either 'A', 'M', 'R', or 'S'.
     * @param scr
     * @return
     */
    public static char getUsersNextActionType(Scanner scr){
        String s;
        char output = 0;
        boolean done = false;
        while(!done){
            System.out.println("Enter a character representing an action ('A', 'R', 'S', 'M' or 'F'): ");
            s = scr.nextLine();
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (String.valueOf(c).toUpperCase().equals("A")) {
                    //System.out.println("Attack");
                    output = c;
                    done = true;
                }
                else if (String.valueOf(c).toUpperCase().equals("M")) {
                    //System.out.println("Move");
                    output = c;
                    done = true;
                }
                else if (String.valueOf(c).toUpperCase().equals("R")) {
                    //System.out.println("Recruit");
                    output = c;
                    done = true;
                }
                else if (String.valueOf(c).toUpperCase().equals("S")) {
                    //System.out.println("Spawn");
                    output = c;
                    done = true;
                }
                else if (String.valueOf(c).toUpperCase().equals("F")){
                    output = c;
                    done = true;
                }
            }
        }
        return output;
    }

    /**
     * This method takes input from the user and checks if the input is an integer and
     * within the range of the arguments for min and max.
     * @param min
     * @param max
     * @param scr
     * @return
     */
    public static int getValidInt(int min, int max, Scanner scr){
        boolean valid = false;
        int userInput = 0;
        while (!valid){
            System.out.println("Enter a positive integer between " + min + " and " + String.valueOf(max-1) + " : ");
            if (scr.hasNextInt()){
                userInput = scr.nextInt();
                if (userInput >= min && userInput <= max){
                    valid = true;
                    System.out.println("Your input, " + userInput + ", is valid.");
                }
                else {
                    System.out.println("Your input is not a valid positive integer.");
                }
            }
            else {
                scr.next();
                System.out.println("Try Again! You must enter an integer.");
            }

        }
        return userInput;
    }

    /**
     * This method used the getUsersNextActionType method to assign a valid character
     * to the action field and uses the getValidInt to assign values for the indexes of the
     * from and to squares, which will be used to execute an action.
     * @param game
     */
    public void getNextPlayersAction(GameS22 game){
        Scanner scr = new Scanner(System.in);
        this.action = getUsersNextActionType(scr);
        System.out.println("Enter a fromSquare row index: ");
        this.fromSquareRow = getValidInt(0, game.getGameBoard().getNumRows(), scr);
        System.out.println("Enter a fromSquare column index: ");
        this.fromSquareColumn = getValidInt(0, game.getGameBoard().getNumColumns(), scr);
        System.out.println("Enter a toSquare row index: ");
        this.toSquareRow = getValidInt(0, game.getGameBoard().getNumRows(), scr);
        System.out.println("Enter a toSquare column index: ");
        this.toSquareColumn = getValidInt(0, game.getGameBoard().getNumColumns(), scr);
    }

    /**
     * This method prints the game, allowing for the user to see any changes caused by an action.
     * @param game
     */
    public void updateView(Game game){
        System.out.println(game);
    }

    /**
     * This method displays that the game has ended and which team won if
     * there is a winner.
     * @param game
     */
    public void printEndOfGameMessage(Game game){
        System.out.println("The game has ended!");
        System.out.println(game.getWinner().getTeamColor() + " Team Won!");
    }
}
