
package tetris_mvc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


/**
 *classe qui herite de la classe JFrame et implemente ActionListener et KeyListener
 * @author kaba
 */

public class EspaceJeu  implements ActionListener, KeyListener{
    
    /**
     * les differents boutons de notre espace de jeu
     * 
     */
    private JFrame frame;
    JButton left = new JButton("LEFT");
    JButton right= new JButton("RIGHT");
    JButton up= new JButton("UP");
    JButton down = new JButton("DOWN");
    JButton R_rotation= new JButton("R");
    JButton L_rotation= new JButton("L");
    JButton Result = new JButton("SCORE");
     JButton back = new JButton("BACK") ;  
    PanelMenu menu = new PanelMenu();
    JPanel panelB= new JPanel();
    
    Dimension dimension = new Dimension(80,80);
    
    VueJeu vueJeu;

    /**
     * Constructeur qui permet d'ecouter les boutons, le clavier et aussi de definir les caracteristiques graphique de la vue du jeu.
     * 
     * @param p 
     */
    public EspaceJeu(PlateauJeu p)
    {  
        frame= new JFrame();

        Container cp = frame.getContentPane();
        cp.setLayout(new BorderLayout());
        
        vueJeu = new VueJeu(p);
        vueJeu.setSize(new Dimension(p.getLargeur()*p.getLargeur(), p.getLongueur()*p.getLongueur()));
        
       
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.addKeyListener(this);        

        /**
         * on rend ecoutable les button de la classe PanelMenu
         */
        menu.setSize(dimension);
        menu.getb_jouer().addActionListener(this);
        menu.getb_quitter().addActionListener(this);
        menu.getb_aide().addActionListener(this);
        
        
        

        
         /**
          * On ajoute sur le panel tous les boutons.
          */
        panelB.setPreferredSize(dimension);  
        panelB.setBackground(Color.black);
        panelB.setLayout(new GridLayout(8,1));
        panelB.add(left);   
        panelB.add(right);
        panelB.add(up );
        panelB.add(down);
        panelB.add(R_rotation);
        panelB.add(L_rotation);
        panelB.add(Result);
        panelB.add(back);
        frame.setFocusable(true);// Le frame est capable de prendre le focus
        frame.setTitle("MENU");
        frame.setResizable(false);
        
        /**
         * On rend ecoutable les boutons 
         */
        Result.addActionListener(this);
        Result.setBackground(Color.BLACK);
        Result.setForeground(Color.white);
        
        left.addActionListener(this);
        left.setBackground(Color.BLACK);
        left.setForeground(Color.white);
        
        right.addActionListener(this);
        right.setBackground(Color.BLACK);
        right.setForeground(Color.white);
        
        up.addActionListener(this);
        up.setBackground(Color.BLACK);
        up.setForeground(Color.white);
        
        down.addActionListener(this);
        down.setBackground(Color.BLACK);
        down.setForeground(Color.white);
        
        R_rotation.addActionListener(this);
        R_rotation.setBackground(Color.BLACK);
        R_rotation.setForeground(Color.white);
        
        L_rotation.addActionListener(this);
        L_rotation.setBackground(Color.BLACK);
        L_rotation.setForeground(Color.white);
        
        back.addActionListener(this);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.white);
        
        
        /**
         *On ajoute le panel au container, c'est lui qui contient tous les boutons  
         */
        cp.add(panelB,BorderLayout.WEST);
        cp.add(menu);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(650, 540);
        
        menu.setVisible(true);
        panelB.setVisible(false); // On cache les boutons de deplacement au debut du lancement
        

    }
    
    
    /**
     * Methode qui est appelee lors du clic de la souris. 
     * Elle permet de definir chaque action suivant l'endroit du clic sur plateau de jeu.
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e){
        Pieces p = vueJeu.getPieceClicked();
        
        if(e.getSource()==menu.getb_jouer())
        {
            menu.setVisible(false);
            panelB.setVisible(true);
            vueJeu.setVisible(true);
            frame.setTitle("TETRIS");
            
            frame.getContentPane().add(vueJeu);            
         
            }
            
        if(e.getSource() == menu.getb_aide())
        {
            JOptionPane.showMessageDialog(null,"L'idée du jeu s'inspire du fameux Tétris , l'objectif du jeu consiste à: \n"
                                                + " --Faire en sorte que les pièces occupent le moins de place possible sur le plateau de jeu. \n\n"
                                                + "Pour que votre objectif soit atteint, Deux solutions s'offrent à vous : \n\n"
                                                + "     1- Déplacer les Tétriminos en utlisant les boutons du plateau de jeu à l'aide de la souris, aussi Il est possible de faire un retour en arrière en cliquant sur BACK\"\n"
                                                + "     2- Déplacer les pièces via le clavier avec les flèches pour les différents déplacement, la touche ' - ' ou la touche ' + ' pour la rotation de la pièce dans un sens. \n\n"
                                                + "À la fin d'une partie, vous pouvez voir votre taux d'occupation de la grille en cliquant sur ' SCORE'. \n\n"
                                                + "Aussi,il est important de savoir que plus le pourcentage d'occupation est faible plus vous vous rapprocher de l'objectif"
                                                + "\n\n Bon courage à vous!!!!");
        }
        
        if(e.getSource()==menu.getb_quitter())
        {
            frame.dispose();
        }
        
        if(e.getSource() == Result)
        {
           PlateauJeu plateau = vueJeu.getPlateauDeJeu();
           float value = plateau.getPourcentageOccuppe();
           JOptionPane.showMessageDialog(null, "Occupation des pieces sur le plateau : \n " + value + "%\n");    
           
        }
        
        if(e.getSource()== L_rotation&& p!=null)
        { 
              p.dirigeable(1);
        }
        
        if(e.getSource()==R_rotation && p!=null)
        {
              p.dirigeable(2);
        }
        
        if(e.getSource()==up && p!=null)
        {
                p.deplace(1);
        }
        
        if(e.getSource()==left&& p!=null)
        {
                p.deplace(4);
        }
        
        if(e.getSource()==right&& p!=null)
        {
                p.deplace(3);
        }
        
        if(e.getSource()==down && p!=null)
        {
                p.deplace(2);
        }
        if(e.getSource()==back){
            menu.setVisible(true);
            panelB.setVisible(false);
            frame.setTitle("MENU");
            
        }
        frame.requestFocusInWindow(); // Methode qui renvoie le focus sur le frame  
      
    }
        
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    
    
    /**
     * 
     * Methode qui est appelee lors d'un appuie sur une touche du clavier.
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {
        
        Pieces p = vueJeu.getPieceClicked();
        
        if(p!=null && e.getKeyCode()==KeyEvent.VK_ADD)
        {
            p.dirigeable(1);
        }
        
         if( e.getKeyCode()==KeyEvent.VK_SUBTRACT)
        {
            p.dirigeable(2);
        }
         
               
         if(e.getKeyCode()==KeyEvent.VK_UP)
        {
                p.deplace(1);
        }
        
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
                p.deplace(4);
        }
        
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
                p.deplace(3);
        }
        
        if(e.getKeyCode()==KeyEvent.VK_DOWN)
        {
                p.deplace(2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}