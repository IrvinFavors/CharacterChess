public class PieceGru extends Piece implements Attacker, Recruiter {
    private int numAttacks;
    private int numRecruits;

    public PieceGru(char symbol,
                    String teamColor,
                    int numRecruits, int numAttacks,
                    boolean hidden, boolean original) {
        super(symbol, teamColor, hidden, original);
        this.numAttacks = numAttacks;
        this.numRecruits = numRecruits;
    }

    public PieceGru() {
        this('G', "NON", 0, 0, false, true);
    }


    @Override
    public int getNumAttacks() {
        return this.numAttacks;
    }

    @Override
    public void setNumAttacks(int numAttacks) {
        this.numAttacks = numAttacks;
    }

    @Override
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        return Math.abs(toSquareCol - fromSquareCol) == 3 && Math.abs(toSquareRow - fromSquareRow) == 3 ||
                Math.abs(toSquareCol - fromSquareCol) == 2 && Math.abs(toSquareRow - fromSquareRow) == 2 ||
                Math.abs(toSquareCol - fromSquareCol) == 1 && Math.abs(toSquareRow - fromSquareRow) == 1;
    }

    @Override
    public boolean validMovePath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        return (Math.abs(fromSquareCol - toSquareCol) == 3) && (Math.abs(fromSquareRow - toSquareRow) == 1) ||
                (Math.abs(fromSquareRow - toSquareRow) == 3) && (Math.abs(fromSquareCol - toSquareCol) == 1);
    }

    @Override
    public Piece spawn() {
        PieceGru copyGru =
                new PieceGru(Character.toUpperCase(this.symbol),
                        this.teamColor,this.numAttacks,this.numRecruits,
                        false,false);
        return copyGru;
    }

    @Override
    public void speak() {
        System.out.println("Freeze Ray!");
    }

    @Override
    public boolean validSpawnPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        return fromSquareCol == toSquareCol;
    }

    @Override
    public int getNumRecruits() {
        return this.numRecruits;
    }

    @Override
    public void setNumRecruits(int numRecruits) {
        this.numRecruits = numRecruits;
    }

    @Override
    public boolean validRecruitPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol) {
        return (Math.abs(fromSquareRow - toSquareRow) <= 3) &&
                (Math.abs(fromSquareCol-toSquareCol) <= 3) && (Math.abs(fromSquareCol - toSquareCol) > 0) &&
                (Math.abs(fromSquareRow - toSquareRow) > 0);
    }
}