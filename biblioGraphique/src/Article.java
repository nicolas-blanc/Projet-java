import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class Article implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private HashSet<MotCle> _motcles;
	private Auteur _auteur;
	private String _titre;
	// private int _pageDeb;
	private Parution _par;

	public Article(String titre, Auteur aut, Parution par, HashSet<MotCle> mots) {
		/*
		 * this._motCle = _motCle; this._auteur = _auteur; this._titre = _titre;
		 * this._pageDeb = _pageDeb; this.parution = parution;
		 */
		this.set_titre(titre);
		this.set_auteur(aut);
		this.set_parution(par);
		this.setMotCle(mots);
	}

	public Parution get_par() {
		return _par;
	}

	public void set_par(Parution _par) {
		this._par = _par;
	}

	public void set_titre(String titre) {
		this._titre = titre;
	}

	public void set_auteur(Auteur aut) {
		this._auteur = aut;
	}

	public void set_parution(Parution par) {
		this._par = par;
	}

	public String get_titre() {
		return this._titre;
	}

	public Auteur get_auteur() {
		return this._auteur;
	}

	public Parution get_parution() {
		return this._par;
	}

	public HashSet<MotCle> getMotsCles() { // check ant
		return _motcles;
	}

	public void setMotCle(HashSet<MotCle> _motCle) {
		this._motcles = _motCle;
		for (MotCle mC : _motCle) {
			_motcles.add(mC);
			mC.set_article(this);}
	}

	public Parution getInfosParution() {
		Article a = null; 
		if (this.get_par() != null) {
			Periodique perio = null;
			Parution p = this.get_par();
			if (this.get_par() != null) {
				perio = p.getInfosPeriodique();
			}
			a.set_par(p);
			return null;
		}
		return null;

	}
	/*
	 * private void setMotsCles (HashSet<MotCle> mots){//check ant _motscles =
	 * mots; }
	 */

	/*
	 * public void setPageDeb(int _pageDeb) { this._pageDeb = _pageDeb; }
	 */
	/*
	 * public void setMotCle(MotCle mC) { this._motCles.add(mC); }
	 */
}