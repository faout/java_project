
package tetris_mvc;

import java.awt.Color;

/**
 *Classe abstraite dont toutes les pièces du jeu hériteront
 * @author kaba
 */
public abstract class Pieces implements InterfacePieces, Observable {
    
    /**
     * largeur de la pièce
     */
    private int largeur;
    /**
     * longueur de la pièce
     */
    private int longueur;
    /**
     * grille de jeu
     */
    protected boolean grille[][];
    /**
     * l'orientation de la piece
     */
    private int orientation;
    /**
     * la position de la piece
     */
    private Position placePiece;
    /**
     * l'observateur
     */
    protected Observateur observer;
    /**
     * la couleur de la piece
     */
    private Color couleur;

    
    /**
     * methode de retour de la largeur
     * @return 
     */
    @Override
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * methode de retour de la longueur
     * @return 
     */
    @Override
    public int getLongueur() {
        return this.longueur;
    }

    /**
     * methoe de retour de la grille
     * @param x
     * @param y
     * @return 
     */
    @Override
    public boolean getGrille(int x , int y) {
        return this.grille [x][y];
    }

    /**
     * methode de retour de l'orientation
     * @return 
     */
     @Override
    public int getOrientation() {
        return this.orientation;
    }

    /**
     * methode de retour de la Position
     * @return 
     */
   
    public Position getPlacePiece() {
        return this.placePiece;
    }
    
    /**
     * methode de retour du centre de la piece
     * @return 
     */
    @Override
    public Position getPositionMilieu(){
        
        return new Position(longueur/2,largeur/2);
    
    }
    
    /**
     * methode de retour de la couleur
     * @return 
     */
    @Override
    public Color getCouleur(){
        
        return this.couleur;
        
    }
    
    /**
     * methode de modification de la longueur
     * @param longueur 
     */
    @Override
    public void setLongueur(int longueur){
    
        this.longueur=longueur;
    }
    
    /**
     * methode de modification de la largeur
     * @param large 
     */
    @Override
    public void setLargeur(int large){
    
        this.largeur=large;
    }
    
    /**
     * methode de modification de la couleur
     * @param couleur 
    */
    @Override
    public void setCouleur(Color couleur){
    
        this.couleur=couleur;
    }
    
    /**
     * methode de modification de l'orientation
     * @param orient 
     */
    @Override
    public void setOrientation(int orient){
    
        this.orientation=orient;
    }
    
    
    /**
     * methode de modification de la position
     * @param placePiece 
     */
    public void setPlacePiece(Position placePiece) {
        
        this.placePiece = placePiece;
    }
    
  
    
    /**
     * methode de modification de la grille
     * @param x
     * @param y
     * @param z 
     */
    public void setGrille(int x, int y , boolean z) {
        
        this.grille[x][y] = z;
    }
    
    
    public abstract Pieces duplicationPiece();
    
    
    public abstract void creeGrille();

    
    /**
     * methode d'ajout d'un observateur de la piece
     * @param obs 
     */
    @Override
    public void ajouterObservateur(Observateur obs){
        
        this.observer = obs;
    
    }
 
    /**
     * methode qui informe les observateurs d'un changement d'état
     */
    @Override
    public void notifierObservateurs(){
        
        this.observer.actualiser(this);
    
    }
    
 
    
    /**
     * methode de rotation de la piece selon le sens
     * @param rotatsens 
     */
    
    public void rotation(int rotatsens){
        
        boolean tp[][]=new boolean [largeur][longueur];
        
        if(rotatsens==1){
            
            for(int i=0;i<this.longueur;i++){
                
                for(int j=0;j<this.largeur;j++)
                    
                    tp[this.largeur-1-j][i]=this.grille[i][j];
                
                }
                
            if(this.orientation != 3)
                this.orientation ++;
            else
                this.orientation = 0;
           
        }else{
            
            for(int i=0;i<this.longueur;i++){
                
                for(int j=0;j<this.largeur;j++)
                    
                    tp[j][this.largeur-1-i]=this.grille[i][j];
                 
        
        }
            if(this.orientation != 0)
                this.orientation --;
            else
                this.orientation=3;
        
    }
        
        int temp = this.longueur;
        this.setLongueur(this.largeur);
        this.setLargeur(temp);
        this.grille=tp;
}
    
    /**
     * methode qui informe s'il est possible de faire la rotation de la piece sur un plateau de jeu
     * @param pj
     * @param sens
     * @return 
     */
    public boolean peuTourner(PlateauJeu pj, int sens){
        
        pj.supprimerPieces(this);
        Pieces piece = this.duplicationPiece();
        piece.rotation(sens);
        return pj.estPositionnable(this.getPlacePiece(), piece);
        
    
    }
    
    /**
     * methode qui permet de faire une rotation de la piece sur le platau de jeu
     * @param sens 
     */
    public void dirigeable(int sens){
        
        if(this.peuTourner((PlateauJeu)observer, sens)){
            
            this.rotation(sens);
            this.notifierObservateurs();
        
        }
    
    }
    
    /**
     * methode qui applique les modifications aux coordonnées d'une pièce lors du deplacement de celle-ci
     * @param d 
     */
    public void deplacement(int d){
        
       switch (d)
        {
            case 1 : 
                this.placePiece.setPx(this.placePiece.getPx()-1);
                break;
            
            case 2 :
                this.placePiece.setPx(this.placePiece.getPx()+1);
                break;
            
            case 3 :
                this.placePiece.setPy(this.placePiece.getPy()+1);    
                break;
            
            case 4 :
                this.placePiece.setPy(this.placePiece.getPy()-1);
                break;
            
            default: 
                break;
        }
    
    }
    
    /**
     * methode qui confirme s'il est possible de faire un deplacement de la piece selon les parametres données 
     * @param pj
     * @param i
     * @return 
     */
    public boolean deplaçable(PlateauJeu pj, int i){
        
       // efface la piece du plateau 
       pj.supprimerPieces(this);
       // duplication de la piece
       Pieces piece = this.duplicationPiece();
       int x = this.getPlacePiece().getPx();
       int y = this.getPlacePiece().getPy();
       // On donne la position au clone de l'ancienne piece
       piece.setPlacePiece(new Position(x, y));
       // On fait la translation du clone en "arriere plan" pour ensuite verifier si on peut faire la translation de la piece d'origine
       piece.deplacement(i);
       //verifie s'il est possible de mettre le clone dans la grille donnee
       return pj.estPositionnable(piece.getPlacePiece(), piece);
    }
    
    /**
     * methode qui deplace d'une piece donnée et informe les observateurs du changement d'etat
     * @param i 
     */
    public void deplace(int i){
        
        if(deplaçable((PlateauJeu)observer, i )){
            
            this.deplacement(i);
            this.notifierObservateurs();  
    }
    
    
    }
    
    
    
   /**
    * methode de la presentation et de l'afficahge d'une piece donnée
    * @return 
    */ 
    public String toString(){
        
        String str = "Ma largeur est: " + this.largeur + "\n";
        str += "Ma longueur est : " + this.longueur + " \n";
        str += " ma forme ressemble à : \n\n";
        for(int i = 0; i< this.grille.length; i++){
            for(int j = 0; j< this.grille[i].length; j++){
                
                str += this.getGrille(i, j);
                str +=("");
            }
            str +=("\n");
            
        }
        str += ("\n");
        str +=("mon centre est : x : " + this.getPositionMilieu().getPx() +" "+ " y :" + this.getPositionMilieu().getPy());
        str += ("\nvoici mon orientation : "+this.getOrientation());
        str += ("\n");
        return str;
            
    
    }


}
