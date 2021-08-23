
package tetris_mvc;

/**
 *cette classe permet de definir la position de la piece
 * @author kaba
 */
public class Position {
    
    /**
     * l'abscisse de la piece
     */
    private int px;
    /**
     * l'oordonnee de la piece 
     */
    private int py;
    
    
    /**
     * constructeur de la classe position qui a pour parametre px et py
     * @param px
     * @param py 
     */
    public Position(int px , int py){
        
        this.px = px;
        this.py = py;
    }
    
    /**
     * methode retournant l'abscisse x
     * @return 
     */
    public int getPx() {
        return px;
    }

    /**
     * methode retournant l'ordonnée y
     * @return 
     */
    public int getPy() {
        return py;
    }
    
    /**
     * methode qui modifie l'abscisse x
     * @param px 
     */
    public void setPx(int px) {
        this.px = px;
    }

    /**
     * methode qui modifie l'ordonnee y
     * @param py 
     */
    public void setPy(int py) {
        this.py = py;
    }
     
    
}