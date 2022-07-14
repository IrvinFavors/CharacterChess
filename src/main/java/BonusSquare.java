public class BonusSquare extends BoardSquare{
    boolean activated;
    public BonusSquare(String color) {
        super(color);
        this.activated = false;
    }
    public void activate(){
        this.activated = true;
    }
    public boolean isActivated(){return this.activated;}

    @Override
    public String toString(){
        if (this.isActivated()){

            /*
            if (this.isEmpty()){
                return "\u001B[43m" + "-Bonus-" + "\u001B[0m";
            }
            else{
                return "\u001B[43m" + "B" + getPiece().toString() + "B" + "\u001B[0m";
            }

             */

            if (this.isEmpty()){
                return "-------";
            }
            else{
                return "-" + getPiece().toString() + "-";
            }

        }
        else {
/*
            if (this.isEmpty()) {
                return "-Bonus-";
            } else {
                return "B" + getPiece().toString() + "B";
            }

 */
            if (this.isEmpty()) {
                return "-------";
            } else {
                return "-" + getPiece().toString() + "-";
            }

        }
    }



}
