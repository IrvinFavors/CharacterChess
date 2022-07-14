/**
 * The Team class creates a Team object which holds an ArrayList of Piece objects.
 * @author Irvin Favors, James Krienen
 * @since 2022-04
 * */

import java.util.ArrayList;

public class Team {

    private String teamColor;
    ArrayList<Piece> team;
    private Piece lastUsed;
    protected int countLastUsed;


    /**
     * This constructor sets the teamColor of the team and assigns an ArrayList of Pieces to the team attribute.
     * @param teamColor
     * @param Team
     */
    public Team(String teamColor, ArrayList<Piece> Team){
        this.teamColor = teamColor;
        this.team = Team;
    }

    /**
     * Getters, these methods can be used to obtain the attributes of a team.
     * @return String
     */
    public String getTeamColor() {
        return this.teamColor;
    }

    public ArrayList<Piece> getTeamPieces(){
        return this.team;
    }

    public Piece getLastUsed() {
        return this.lastUsed;
    }
    public int getCountLastUsed() {
        return this.countLastUsed;
    }
    public void setLastUsed(Piece lastUsed) {
        this.lastUsed = lastUsed;
    }

    /**
     * This method takes a given piece and removes the piece from the team by calling the
     * remove method for ArrayLists and using the given Piece as the argument.
     * @param piece
     */
    public void removePieceFromTeam(Piece piece){
        this.team.remove(piece);
    }

    /**
     * This method takes in a Piece object and then adds the Piece to the team, also setting the teamColor of the
     * piece to the color of the current team.
     * @param piece
     */
    public void addPieceToTeam(Piece piece){
        this.getTeamPieces().add(piece);
        piece.setTeamColor(this.getTeamColor());
    }

    /**
     * This method overrides the toString method to display the teams, as well as the teamColor and symbol of the pieces
     * present.
     * @return String
     */
    @Override
    public String toString(){
        String output = "Team " + this.teamColor + " Pieces :\n";
        for (int i = 0; i < team.size(); ++i){
            output+=team.get(i).toString() + "   ";
        }
        return output;
    }

}
