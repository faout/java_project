package tetris_mvc;



import tetris_mvc.Createur.TypePieces;



/**
 *
 * @author kaba
 */

/**
 * La Classe principale
 */
public class TetrisJeu 
{

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args)
    {
        // definition d'un niveau de difficulte 
        
        int difficult = 3;
        
        // Instanciation de Createur des Pieces
        
        Createur usinePiece = new CreateurPieces();
        
        // Création des pièces
        Pieces L1 = usinePiece.formerPiece(TypePieces.PieceL);
        Pieces U1 = usinePiece.formerPiece(TypePieces.PieceU);
        Pieces T1 = usinePiece.formerPiece(TypePieces.PieceT);
        Pieces L2 = usinePiece.formerPiece(TypePieces.PieceL);
        Pieces T2 = usinePiece.formerPiece(TypePieces.PieceT); 
        Pieces U2 = usinePiece.formerPiece(TypePieces.PieceU);
        Pieces T3 = usinePiece.formerPiece(TypePieces.PieceT);
        Pieces U3 = usinePiece.formerPiece(TypePieces.PieceU);
        Pieces L3 = usinePiece.formerPiece(TypePieces.PieceL);
        
        
        // Creation du plateau de jeu sur lequel on joue
        PlateauJeu plateau = new PlateauJeu(25,25);
        
        //Récupération des positions aléatoires suivant les 3 pièces 
        Position positionT1 = plateau.PositionAleatoire(T1);
        Position positionL1 = plateau.PositionAleatoire(L1);
        Position positionU1 = plateau.PositionAleatoire(U1);
        
        //Ajout minimum des 3 pieces pour pouvoir jouer        
        plateau.ajoutPiece(T1, positionT1);
        plateau.ajoutPiece(L1, positionL1);
        plateau.ajoutPiece(U1, positionU1);       
        
        // on ajoute plus ou moins de piece
        
        if(difficult == 2 || difficult == 3)
        {        
            Position positionT2 = plateau.PositionAleatoire(T2);
            Position positionL2 = plateau.PositionAleatoire(L2);
            Position positionU2 = plateau.PositionAleatoire(U2);
                
            plateau.ajoutPiece(T2, positionT2);
            plateau.ajoutPiece(L2, positionL2);
            plateau.ajoutPiece(U2, positionU2);
        }
            
        if(difficult == 3)
        {   
            Position positionT3 = plateau.PositionAleatoire(T3);
            Position positionL3 = plateau.PositionAleatoire(L3);
            Position positionU3 = plateau.PositionAleatoire(U3);
                
            plateau.ajoutPiece(T3, positionT3);
            plateau.ajoutPiece(L3, positionL3);
            plateau.ajoutPiece(U3, positionU3);                 
        }
        
        // On lance le jeu en graphique
        new EspaceJeu(plateau);
    }
    
}