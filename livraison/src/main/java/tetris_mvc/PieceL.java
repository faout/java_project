

package tetris_mvc;

import java.awt.Color;

/**
 *Classse qui represente la piece en forme L
 * @author kaba
 */
public class PieceL extends Pieces{
    
    /**
     *constructeur de la piece L
     */
    public PieceL()
    
    { 
       this.setLargeur(3);
       this.setLongueur(3);
       this.setOrientation(0);
       this.grille = new boolean [this.getLongueur()][this.getLargeur()];
       this.setCouleur(Color.BLUE);
    
    }
    
    /**
     * cette methode permet de creer la grille de la piece L
     */
    @Override
     public void creeGrille()
      {
          for(int i = 0 ; i< this.grille.length; i++)
          {
             for(int j = 0 ; j< this.grille[i].length; j++)
             {
                if((i==(this.getLongueur())-1) || (j== 0))
                {
                    this.grille[i][j] = true ;
                }
           }
       }     
    }
     
     
    /**
     * methode qui retourne une piece de la forme L
     * @return 
     */
     @Override
     public Pieces duplicationPiece(){
       
       Pieces pL = new PieceL();
       
       pL.setLongueur(this.getLongueur());
       pL.setLargeur(this.getLargeur());
   
       
       for(int i = 0; i < this.getLongueur(); i++)
       {
           
           for(int j = 0; j< this.getLargeur(); j++) 
           {
               
               pL.setGrille(i, j, this.getGrille(i, j));
           }
           
       
       }
   
       return pL;
   } 

}