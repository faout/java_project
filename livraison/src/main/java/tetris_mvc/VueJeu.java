

package tetris_mvc;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;


/**
 * class VueJeu définit ce que verra l'utilisateur.
 * @author kaba
 */
public class VueJeu extends JPanel implements Observateur, MouseListener {
    
    /** 
     * La dimension des cases de la grille de jeu (la dimension d'une case affichée et non pas le nombre de case dans la grille)
     */
    
    private final static int DIM = 20;
    
    /**
    * Le plateau de jeu qui sera "écouté"
    */
    
    private PlateauJeu plateau;
    
    /**
     * La piece qui sera cliquée par l'utilisateur
     */
    
    private Pieces pieceCliquee;
    
    
    /**
     * Constructeur de la classe
     * @param plateau 
     */
    
    public VueJeu(PlateauJeu plateau)
    {
        this.plateau = plateau;
        this.setSize(plateau.grilleJeuPlateau.length*plateau.grilleJeuPlateau.length,plateau.grilleJeuPlateau.length*plateau.grilleJeuPlateau.length);
        this.addMouseListener(this);
        this.plateau.ajouterObservateur(this);
    }
    
    
    /**
     * Méthode qui redéfinit une méthode présente dans le JPanel qui permet de "peindre" notre écran.
     * 
     * 
     * @param g 
     *       Le "graphics" sur lequel on dessinera nos objets.
     */
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g); // Permet de "partir d'une feuille blanche" à chaque "repaint"
        //System.out.println(this.plateau);
        
        int dimCase=DIM; //Taille des cases
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for(Pieces p : plateau.getListePiece())
        {
            //Boucle sur les pieces du tableau pour les afficher
            
            int x = p.getPlacePiece().getPx() - p.getPositionMilieu().getPx();
            int y = p.getPlacePiece().getPy() - p.getPositionMilieu().getPx();
            
            for(int i = x; i<x+p.getLargeur(); i++)
                for(int j = y; j<y+p.getLongueur(); j++)
                {
                    // Pour chaque coordonnees de chaque piece, on regarde si on doit l'afficher ou non
                    
                    if(p.getGrille(i-x,j-y))
                    {
                        // On regarde ensuite si la piece sur laquelle on boucle est la meme que la piece cliquée
                        // Suivant le cas, on peint d'une certaine couleur la piece cliquée
                        if(this.pieceCliquee == p)
                        {
                            g.setColor(Color.white);
                            g.fillRect(j*dimCase, i*dimCase, dimCase, dimCase);
                        }
                        else
                        {
                            g.setColor(p.getCouleur());
                            g.fillRect(j*dimCase, i*dimCase, dimCase, dimCase);
                        }
                    }   
                }
        }
        
        
        /**
         * Affiche les colonnes de la grille
         */
        
        for(int j = -1; j < plateau.grilleJeuPlateau.length+1; j++)     
        {
            g.drawLine(j*dimCase,0, j*dimCase, dimCase*plateau.grilleJeuPlateau.length);
            g.setColor(Color.white);
        }
        
        
        /**
         * Affiche les lignes de la grille
         */
        for(int i = 0; i < plateau.grilleJeuPlateau.length+1; i++)
        {
            g.drawLine(0,i*dimCase,dimCase*plateau.grilleJeuPlateau.length, i*dimCase);
            g.setColor(Color.white);
        }
        
    }
    
    /**
     * Retourne la piece cliquée.
     * 
     * @return 
     *      La piece cliquée par l'utilisateur.
     */
    public Pieces getPieceClicked()
    {
        return this.pieceCliquee;
    }
    
    /**
     * Retourne le plateau de jeu utilisé par cette vue.
     * 
     * @return Le plateau de jeu utilisé par cette vue du jeu.
     */
    
    public PlateauJeu getPlateauDeJeu()
    {
        return this.plateau;
    }
    
    
    /**
     *  Fait un repaint de son paintComponent, ce qui a pour effet de rafraichir la vue du jeu.
     * 
     * @param plateau  
     *      Le plateau de jeu qui est de type Observable
     */
    
    
    @Override
    public void actualiser(Observable plateau)
    {
        this.repaint();
    }
    
    
    /**
     * Effectue une action au clic de la souris.
     * En particulier ici, cela permet de tester si l'on a cliqué sur une piece.
     * Et aussi de mettre a jour le plateau de jeu.
     * 
     * @param e 
     *      Les evenements liés à la souris
     */
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        Position positionPieceClicked = new Position(e.getY()/DIM, e.getX()/DIM);
      
        if(this.plateau.estOccupe(positionPieceClicked))
            this.pieceCliquee = plateau.TrouvePositionPiece(positionPieceClicked);
        
        else
            // On remet la valeur de la piece cliquee a 'null' lorsque aucune piece ne match avec le clic (pour que l'ancienne piece cliquee reprenne sa couleur d'origine)
            this.pieceCliquee = null; 
        
        this.actualiser(this.plateau);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}