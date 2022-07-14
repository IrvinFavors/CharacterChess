public interface Attacker {
    public int getNumAttacks();
    public void setNumAttacks(int numAttacks);
    public boolean validAttackPath(int fromSquareRow, int fromSquareCol, int toSquareRow, int toSquareCol);
}
