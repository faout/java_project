
package tetris_mvc;

/**
 *Classe permertant de fabriquer une piece
 * @author kaba
 */
public class CreateurPieces extends Createur{
    
    
    /**
     * methode de fabrication de la piece en fonction de son type passé en paramètre
     * @param type
     * @return 
     */
    public Pieces creationPieces(TypePieces type){
        
        Pieces piece = null;
        switch(type){
            
            case PieceT:piece = new PieceT();break;
            case PieceL:piece = new PieceL();break;
            case PieceU:piece = new PieceU();break;
        }
        return piece;
        
    }
    
}
