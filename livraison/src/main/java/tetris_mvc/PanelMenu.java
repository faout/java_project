
package tetris_mvc;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JButton;

import javax.swing.JPanel;


/**
 *Classe Panel qui présente le menu qui s'ouvre à l'ouverture de l'application
 * @author kaba
 */
public class PanelMenu extends JPanel {
    
    /**
     * définition des attribut de la classe
     */
    final JButton btn_quitte;
    final JButton btn_jouer;
    final JButton btn_aide;
    
    
    /**
     * Constructeur qui definit le panel
     * 
     */
    public PanelMenu(){
        
        String btn_j="JOUER";
        String btn_a="AIDE";
        String btn_q="QUITTER";
        
        this.setLayout(new GridLayout(3,2,50,50));
        
        
        /**
         * Bouton permettant  de jouer au jeu
         */
        btn_jouer = new JButton(btn_j);
        btn_jouer.setBackground(Color.gray);
        
        /**
         * bouton permettant de quitter le jeu
         */
        btn_quitte = new JButton(btn_q);
        btn_quitte.setBackground(Color.gray);
        
        /**
         * bouton permettant d'avoir des information sur le jeu
         */
        btn_aide = new JButton(btn_a);
        btn_aide.setBackground(Color.gray);
        
        /**
         * la couleur de fond du panel
         */
       
        setBackground(Color.blue);
        
        
         
	
		
	/**
         * ajout d'un composant en position (i, j), avec gridx=i et gridy=j 
         */
	
		
        
        
	add(btn_jouer);
        add(btn_aide);
	add(btn_quitte);
      
    }
    
    /**
     * 
     * @return le bouton jouer du menu de jeu
     */
    public JButton getb_jouer(){
        return btn_jouer;
    }
    
    /**
     * 
     * @return le bouton quitter du menu de jeu
     */
    public JButton getb_quitter(){
        return btn_quitte;
    }
    
    /**
     * 
     * @return le bouton aide du menu de jeu
     */
    public JButton getb_aide(){
        return btn_aide;
    }

}