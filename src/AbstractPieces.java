public abstract class AbstractPieces {
   public abstract boolean isValidMovement(int col, int row);

   public boolean moveCollidesWithPiece(int col, int row){
      return false;
   }
}
