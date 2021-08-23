
package tetris_mvc;

/**
 *Classe abstraite qui sert de base aux Createur de Piece
 * @author kaba
 */
 
public abstract class Createur {
 
    /**
     * methode qui permert de former les pieces
     * @param type
     * @return 
     */
    public Pieces formerPiece(TypePieces type){
        Pieces piece = this.creationPieces(type);
        piece.creeGrille();
        return piece;
        
        
        
    }
    
   /**
    * methode abstraite de la fabrication d'une piece souhaités
    * @param type
    * @return 
    */
    public abstract Pieces creationPieces(TypePieces type);
   
    
    /**
     * methode d'enumération des types de pieces que l'on peut fabriquer
     */
    public enum TypePieces{
        PieceT,
        PieceL,
        PieceU
    }
    
}
