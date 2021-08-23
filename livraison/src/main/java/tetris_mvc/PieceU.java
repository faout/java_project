
package tetris_mvc;

import java.awt.Color;

/**
 *Classe qui represente la piece en forme U
 * @author kaba
 */
public class PieceU extends Pieces{
    
    
    /**
     * Constructeur de la piece U
     */
    public PieceU()
    
    { 
       this.setLargeur(3);
       this.setLongueur(3);
       this.setOrientation(0);
       this.grille = new boolean [this.getLongueur()][this.getLargeur()];
       this.setCouleur(Color.GREEN);
    }
    
    /**
     * cette methode permet de creer la grille de la piece U
     */
    @Override
     public void creeGrille()
      
    {
          for(int i = 0 ; i< this.grille.length; i++)
          {
             for(int j = 0 ; j< this.grille[i].length; j++)
             {
                if((i==(this.getLongueur())-1) || ((j== 0)||(j==(this.getLargeur())-1)))
                {
                    this.grille[i][j] = true ;
                }
           }
       }     
    }
    
    /**
     * methode qui retourne une piece de la forme U
     * @return 
     */
     
    @Override
    public Pieces duplicationPiece()
    {
       
       Pieces pU = new PieceU();
       
       pU.setLongueur(this.getLongueur());
       pU.setLargeur(this.getLargeur());
   
       
       for(int i = 0; i < this.getLongueur(); i++)
       {
           
           for(int j = 0; j< this.getLargeur(); j++)
           {
               
               pU.setGrille(i, j, this.getGrille(i, j));
           }
           
       
       }
   
       return pU;
   } 
}