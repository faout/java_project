
package tetris_mvc;

import java.awt.Color;

/**
 *Interface qui contient des methodes qu'on peut implementer pour les pièces
 * @author kaba
 */
public interface InterfacePieces {
    
    public int getLargeur();
    
    public int getLongueur();
    
    public boolean getGrille(int x , int y);
    
    public Position getPositionMilieu();
    
    public int getOrientation();
    
    public Color getCouleur();
    
    public void setOrientation(int o);
    
    public void setLargeur(int largeur);
    
    public void setLongueur(int longueur);
    
    public void  rotation (int orientation);
    
    public void setCouleur (Color c);
    
}
