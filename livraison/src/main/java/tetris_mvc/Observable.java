
package tetris_mvc;

/**
 *Interface qui permet de redefinir respectivement la methode d'ajout des observateurs 
 * et aussi la façon de les informés 
 * @author kaba
 */

public interface Observable {
    
    
    public void ajouterObservateur(Observateur obs);
    
    public void notifierObservateurs();
    
}
