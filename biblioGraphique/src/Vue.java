import javax.swing.JFrame;
import java.util.Observable;
import java.util.Observer;
/**
 * Super classe des vues
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public abstract class Vue extends JFrame implements Observer{
	
	// énumération des états possibles pour une vue
	// cet état permet de contrôler l'enchaînement des dialogues possibles pour l'utilisateur
	// dans une vue
	private static final long serialVersionUID = 1L;
	final static int initiale = 0, inter1 = 1, inter2 = 2, inter3 = 3, inter4 = 4, finale = 5;
	
	// toute vue communique avec le controleur
	private Controleur _controleur;
	private int _etat;

	public Vue(Controleur controleur) {
		this.setControleur(controleur);
	}
	
	protected Controleur getControleur() {
		return _controleur;
	}
	
	
	protected void setControleur(Controleur cont) {
		_controleur = cont;
	}
	
	public int getEtat() {
		return _etat;
	}
	
	public void setEtat(int etat) {
		_etat = etat;
	}
	public void update(){
		this.repaint();
	}
}
