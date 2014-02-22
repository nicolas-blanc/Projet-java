import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Observable;

public class MotCle implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	final static int EMPRUNTABLE = 0, EN_CONSULTATION = 1;
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************
	private String _motCle;
	private HashSet<Ouvrage> _ouvrages;
	private HashSet<Article> _articles;
	
	public HashSet<Article> get_articles() {
		return _articles;
	}

	public void set_articles(HashSet<Article> _articles) {
		this._articles = _articles;
	}

	MotCle(String m){//check nico
		this.set_motCle(m);
		this.set_ouvrages(new HashSet<Ouvrage>());
		this.set_articles(new HashSet<Article>());
	}

	public String get_motCle() {//check nico
		return _motCle;
	}

	public void set_motCle(String _motCle) {//check nico
		this._motCle = _motCle;
	}

	public HashSet<Ouvrage> get_ouvrages() {//check nico
		return _ouvrages;
	}

	public void set_ouvrages(HashSet<Ouvrage> _ouvrages) {//check nico
		this._ouvrages = _ouvrages;
	}
	
	public void set_ouvrage(Ouvrage ouvrage) { // check ant
		_ouvrages.add(ouvrage);
	}
	
	public void set_article(Article art){
		_articles.add(art);
	}
	
}
