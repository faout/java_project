

package tetris_mvc;

import java.awt.Color;

/**
 *Classe qui represente la piece en forme T
 * @author kaba
 */
public class PieceT extends Pieces{
    
    
   /**
    * Constructeur de la piece T
    */ 
   public PieceT(){
       
       this.setLongueur(3);
       this.setLargeur(3);
       this.setOrientation(0);
       this.grille = new boolean [this.getLongueur()][this.getLargeur()];
       this.setCouleur(Color.RED);
   }
   
    
   /**
    *  cette methode permet de creer la grille de la piece T
    */
   @Override
   public void creeGrille()
   {
       
       for(int i = 0 ; i< this.grille.length; i++)
       {
       
            for(int j = 0 ; j< this.grille[i].length; j++)
            {
                
                if((i==0) ||(j== (this.getLargeur()/2) ))
                {
                 this.grille[i][j] = true;
                }
            }
   
        }
   
   
   }
   
   
   /**
    * methode qui retourne une piece de la forme T
    * @return 
    */
   @Override
   public Pieces duplicationPiece()
   {
       
       Pieces pT = new PieceT();
       
       pT.setLongueur(this.getLongueur());
       pT.setLargeur(this.getLargeur());
   
       
       for(int i = 0; i < this.getLongueur(); i++)
       {
           
           for(int j = 0; j< this.getLargeur(); j++)
           {
               
               pT.setGrille(i, j, this.getGrille(i, j));
           }
           
       
       }
   
   return pT;
   
   }    
}

