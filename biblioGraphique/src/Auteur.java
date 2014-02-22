import java.io.Serializable;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;

// nothing to be imported from ant

public class Auteur implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	final static int EMPRUNTABLE = 0, EN_CONSULTATION = 1;
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************
	private String _nom;
	private String _prenom;
	private HashSet<Ouvrage> _mesOuvrages;
	private HashSet<Article> _mesArticles;

	Auteur(String n, String p) {
		this.set_nom(n);
		this.set_prenom(p);
		_mesOuvrages = new HashSet<Ouvrage>();
		_mesArticles = new HashSet<Article>();
	}

	public String get_nom() {
		return _nom;
	}

	public void set_nom(String _nom) {
		this._nom = _nom;
	}

	public String get_prenom() {
		return _prenom;
	}

	public void set_prenom(String _prenom) {
		this._prenom = _prenom;
	}

	public HashSet<Ouvrage> get_mesOuvrages() {
		return _mesOuvrages;
	}

	public void set_mesOuvrages(HashSet<Ouvrage> _mesOuvrages) {
		this._mesOuvrages = _mesOuvrages;
	}

	public void ajout_ouvrage(Ouvrage ouv) {
		_mesOuvrages.add(ouv);
	}

	public void ajout_article(Article art) {
		_mesArticles.add(art);
	}

	public synchronized HashSet<Ouvrage> getInfosOuv() {
		/*
		 * je prends toutes les ouvrages de cette auteur et pour chaque ouvrage
		 * je demande des infos sur ses exemplaires , je renvoi un ensemble d
		 * ouvrages qui ont des infos sur chacun de leur exempl
		 */
		HashSet<Ouvrage> ouv;
		HashSet<Exemplaire> exe = null;
		ouv = this.get_mesOuvrages();
		// synchronized (ouv){
		
// nouvelle methode !!!
		for (Iterator iterator = ouv.iterator(); iterator.hasNext();) {		
			exe = (HashSet<Exemplaire>) ((Ouvrage)iterator).getInfosouv();
			Ouvrage ouv1 = new Ouvrage(((Ouvrage)iterator).getIsbn(), ((Ouvrage)iterator).getTitre(), null, null,
					null, null);
			// ouv1.setExemp((HashSet<Exemplaire>) exe);
			ouv.add(ouv1);
		}
		/*for (Ouvrage o : ouv) { //marchait pas 

			exe = (HashSet<Exemplaire>) o.getInfosouv();
			Ouvrage ouv1 = new Ouvrage(o.getIsbn(), o.getTitre(), null, null,
					null, null);
			// ouv1.setExemp((HashSet<Exemplaire>) exe);
			ouv.add(ouv1);
		}*/
		return ouv;
		// }
	}

	// checked avec nico

	public HashSet<Article> get_mesArticles() {
		return _mesArticles;
	}

	public void set_mesArticles(HashSet<Article> _mesArticles) {
		this._mesArticles = _mesArticles;
	}

	public HashSet<Article> getInfos() {
		HashSet<Article> art = this.get_mesArticles();
		for (Article ar : art) {
			ar.getInfosParution();
		}
		return null;
	}

}
