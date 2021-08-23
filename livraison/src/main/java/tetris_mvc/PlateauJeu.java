
package tetris_mvc;


import java.util.ArrayList;
/**
 *class de creation d'un plateau de jeu
 * @author kaba
 */
public class PlateauJeu implements Observateur ,Observable {
    /**
     * la longueur du plateau
     */
    private int longueur;
    /**
     * la largeur du plateau
     */
    private int largeur;
    /**
     * grille du jeu du plateau
     */
    protected boolean grilleJeuPlateau[][];
    /**
     * la liste des pieces sur le plateau
     */
    public ArrayList<Pieces> listePiece;
    
    Observateur obs;
    
    
    /**
     * constructeur du plateau de jeu
     * @param largeur
     * @param longueur 
     */
    public PlateauJeu(int largeur,int longueur){
        this.longueur= longueur;
        this.largeur = largeur;
        
        this.grilleJeuPlateau = new boolean [this.longueur][this.largeur];
        this.listePiece = new ArrayList();
    }
    
    /**
     * methode de modification de la largeur
     * @param larg 
     */
    public void setLargeur(int larg){
        
        this.largeur = larg;
    }
    
    /**
     * methode de modification de la longueur
     * @param longue
     */
    public void setLongeur(int longue){
        
        this.longueur = longue;
    }
    
    /**
     * methode de retour de la longueur
     * @return 
     */
    public int getLongueur(){
        
        return this.longueur;
    }
    
    /**
     * methode de retour de la largeur
     * @return 
     */
    public int getLargeur(){
        
        return this.largeur;
    }
    
    /**
     * methode de retour de la liste des pieces
     * @return 
     */
    public ArrayList<Pieces> getListePiece(){
  
        return this.listePiece;
    
    }
    
    /**
     * methode qui affirme si une position est occupée par la piece ou non
     * @param p
     * @return 
     */
    public boolean estOccupe(Position p){
        
        return this.grilleJeuPlateau[p.getPx()][p.getPy()];
        
    }
   
    /**
     * methode d'ajout d'un observateur
     * @param obs 
     */
    @Override
    public void ajouterObservateur(Observateur obs){
        
        this.obs= obs;
    
    }
    /**
     * methode d'information d'un observateur
     */
    @Override
    public void notifierObservateurs(){
        
        this.obs.actualiser(this);
    
    }
    
    /**
     * methode qui permet d'ajouter dans la grille de jeu toute les pieces qui sont dans l'attribut la listepiece
     * @param obs 
     */
    @Override
    public void actualiser (Observable obs){
        
         this.grilleJeuPlateau = new boolean[this.longueur][this.largeur];
        
        for(int i = 0; i<listePiece.size(); i++)
        {
           
          this.ajoutPiece(listePiece.get(i), listePiece.get(i).getPlacePiece());
            
            
        }            
        this.notifierObservateurs();  
    
    
    }
    
    
    /**
     * methode confirmant s'il est possible de mettre une piece a une position donné de la grille de jeu
     * @param p
     * @param piece
     * @return 
     */
    public boolean estPositionnable(Position p,Pieces piece){
        
        boolean cp = true;
        
        int x = p.getPx()-piece.getPositionMilieu().getPx();
        int y = p.getPy()-piece.getPositionMilieu().getPy();
        
        //Vérifie que la pièce est toujour dans l'espace du jeu(dans le Tableau)
        if(x<0 | y<0 | y>this.getLargeur() | x>this.getLongueur()){
            cp = false;
        }
        else
        {
            for(int i = x; i <x+piece.getLargeur();i++)
                
                for(int j= y; j<y+piece.getLongueur();j++)
                    
                    if(estOccupe(new Position(i, j))){
                        
                        if(piece.getGrille(i-x,j-y))
                        
                        cp=false;
                    
                    }
                    
                    else{
                        
                        if(estOccupe(new Position(i, j)))
                            cp=false;
                    
                    }
           
        }
         return cp;      
    }
    
    
    /**
     * methode qui permet d'ajouter une piece sur la grille de jeu selon sa position
     * @param piece
     * @param position 
     */
    
    public void ajoutPiece(Pieces piece, Position position){
        
        if(estPositionnable(position, piece)){
            
            int x = position.getPx()-piece.getPositionMilieu().getPx();
            int y = position.getPy()-piece.getPositionMilieu().getPy();
            
            for(int i = x; i<x+piece.getLargeur();i++)
                
                for(int  j = y; j<y+piece.getLongueur(); j++){
                    
                    if(piece.getGrille(i-x, j-y)){
                        this.grilleJeuPlateau[i][j]=piece.getGrille(i-x,j-y);
                    }
                    
                }
                
                piece.setPlacePiece(position);
                listePiece.add(piece);
                piece.ajouterObservateur(this);
             
        }
    
    }

    /**
     * methode qui permet de supprimée la piece dans la grille de jeu
     * @param p 
     */
    public void supprimerPieces(Pieces p){
        
        int x = p.getPlacePiece().getPx()-p.getPositionMilieu().getPx();
        int y = p.getPlacePiece().getPy()-p.getPositionMilieu().getPy();
        
        for(int i = x;i<x+p.getLongueur();i++)
            for(int j = y; j<y+p.getLargeur();j++)
            {
                if(p.getGrille(i-x, j-y))
                {   
                    if(this.estOccupe(new Position(i, j)))
                    {
                        this.grilleJeuPlateau[i][j]=false;
                    }
                }    
            }
       
    }
    
    /**
     * methode qui permet de trouver une position disponible sur la grille du plateau 
     * @param p
     * @return 
     */
    public Position PositionAleatoire(Pieces pa)
    {  
        // Recupere les coordonnees du centre de la piece
        Position positionCentre = pa.getPositionMilieu();
        
        //  valeurs minimales de l'abscisse et l'ordonnee du centre de la piece pour qu'elle rentre dans la grille
        int xMin = positionCentre.getPx();
        int yMin = positionCentre.getPy();
        
        // pareil pour la valeur en  maximale
        int xMax =  this.largeur - (pa.getLargeur() - positionCentre.getPx());
        int yMax =  this.longueur - (pa.getLongueur() - positionCentre.getPy());
        
        Position positionPiece = new Position(-1,-1);
        
        boolean possible = false;  
        
        while(!possible)
        {
            // choisit aleatoirement un endroit pour le centre de la piece dans le plateau de jeu
            int xCentre = xMin + (int)(Math.random() * (xMax - xMin));
            int yCentre = yMin + (int)(Math.random() * (yMax - yMin));
            
            positionPiece = new Position(xCentre, yCentre);
          
            possible = this.estPositionnable(positionPiece, pa);  // Appel de la methode qui verifie si il est possible d'ajouter la piece sur la plateau
        }
        
        return positionPiece;
    }
    
    
    /**
     * pourcentage d'occupation des pieces presente sur le plateau
     * @return 
     */
     public float getPourcentageOccuppe()
    {
        int xMinGauche = 5000;
        int yMinGauche = 5000;
        
        int xMaxDroit = -5000;
        int yMaxDroit = -5000;
        
        int valeurSurface = 0;
        
        int totalSurfaceJeu = this.getLongueur() * this.getLargeur();
        
        for(Pieces p : this.listePiece)
        {
            int x = p.getPlacePiece().getPy() - p.getPositionMilieu().getPy();
            int y = p.getPlacePiece().getPx() - p.getPositionMilieu().getPx();
            
            int xmax = p.getPlacePiece().getPy() + p.getPositionMilieu().getPy();
            int ymax = p.getPlacePiece().getPx() + p.getPositionMilieu().getPx();
            
            if(x<xMinGauche)
                xMinGauche = x;
            
            if(y<yMinGauche)
                yMinGauche = y;
            
            if(xmax>xMaxDroit)
                xMaxDroit = xmax;
            
            if(ymax>yMaxDroit)
                yMaxDroit = ymax;   
        }
        
        //Nombre de cases occupees par l'ensemble des pieces du plateau
        valeurSurface = ((xMaxDroit-xMinGauche)+1)*((yMaxDroit-yMinGauche)+1);
        
        float surfaceOccupe =  ((float)valeurSurface / totalSurfaceJeu)*100 ;
        
        return surfaceOccupe;
    }
    
     
    /**
     * methode qui retourne la piece occupant la position donnée en parametre dans  grille de jeu
     * @param p
     * @return 
     */
    public Pieces TrouvePositionPiece(Position p)
    {
        Pieces pi = null;
         
        if(this.grilleJeuPlateau[p.getPx()][p.getPy()])
        {  
            for(Pieces piece : this.listePiece)
            { 
               int x = piece.getPlacePiece().getPx()-(piece.getPositionMilieu().getPx());
               int y = piece.getPlacePiece().getPy()-piece.getPositionMilieu().getPy();
               
               for (int i =x; i<x+piece.getLongueur(); i++)
               {
                    for(int j =y; j<y+piece.getLargeur();j++)
                    {
                        if(p.getPx()==i && p.getPy()==j)
                        {  
                            pi=piece;
                        } 
                    }
               }
             }
         } 
        return pi;
    }    
    

    
    /**
     * methode de presentation et affichage de la grille de jeu
     * @return 
     */
    @Override
    public String toString()
    {
        String str  = "";
        for(int i = 0; i < this.grilleJeuPlateau.length; i++)
        {
            for(int j = 0; j < this.grilleJeuPlateau[i].length; j++)
            {
                str += this.grilleJeuPlateau[i][j] ? "O" : " ";
                str += " ";
            }
            str += "\n";
        }
        return str;
    }

}
