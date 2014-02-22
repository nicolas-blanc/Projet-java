import java.io.Serializable;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;

public class Periodique implements Serializable {
	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************
	private static final long serialVersionUID = 1L;
	// final static int EMPRUNTABLE = 0, EN_CONSULTATION = 1;
	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************
	private String _issn;
	private String _nom;
	// private HashSet<Parution> _mesParutions;
	private HashMap<String, Parution> _mesParutions;

	/*
	 * public Periodique(String issn, String nom , HashSet<Parution> paru) {
	 * 
	 * this.set_issn(issn); this.set_nom(nom); this.set_mesParutions(paru);
	 * 
	 * }
	 */
	public Periodique(String issn, String nom) {

		this.set_issn(issn);
		this.set_nom(nom);
		this.set_mesParutions(new HashMap<String, Parution>());

	}

	public void set_mesParutions(HashMap<String, Parution> _mesParutions) {
		this._mesParutions = _mesParutions;
	}

	public String get_issn() {
		return _issn;
	}

	public void set_issn(String _issn) {
		this._issn = _issn;
	}

	public String get_nom() {
		return _nom;
	}

	public void set_nom(String _nom) {
		this._nom = _nom;
	}

	/*
	 * public HashSet<Parution> get_mesParutions() { return _mesParutions; }
	 * public void set_mesParutions(HashSet<Parution> _mesParutions) {
	 * this._mesParutions = _mesParutions; }
	 */

	/*
	 * Periodique(String i , String n) { this.set_issn(i); this.set_nom(n);
	 * 
	 * }
	 */

	public int getNbParution() {
		return _mesParutions.size();
	}

	public boolean getParution(Parution par) {
		return _mesParutions.containsKey(par.get_identParution());
	}

	public void ajouterParution(Parution par) {
		_mesParutions.put(par.get_identParution(), par);
	}

	public Parution getParution(String id) {
		return _mesParutions.get(id);
	}

	public Collection<Parution> getParutions() {
		return _mesParutions.values();
	}
/*
	public Periodique getInfosPeriodique(Parution p) {
		Periodique perio = null;
		perio = p.get_periodique();
		return perio;

	}*/

}
