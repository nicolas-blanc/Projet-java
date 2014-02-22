import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.HashSet;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * Classe de gestion d'ouvrage.
 * 
 * @author IUT, refactoré par E. Ceret
 * @version 2.0
 */
public class Ouvrage extends Observable implements Serializable {

	// ************************************************************************************************************
	// Constantes
	// ************************************************************************************************************

	private static final long serialVersionUID = 1L;

	// ************************************************************************************************************
	// Attributs
	// ************************************************************************************************************

	private String _isbn;
	private String _titre;
	private Auteur _auteur;
	private String _editeur;
	private GregorianCalendar _dateEdition;
	private int _derNumExemplaire;

	// Attributs d'Association
	private HashMap<Integer, Exemplaire> _exemplaires;
	private HashSet<MotCle> _motscles; // check ant

	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************

	/**
	 * Constructeur.
	 * 
	 * @param isbn
	 *            numero ISBN de l'ouvrage
	 * @param titre
	 *            titre de l'ouvrage
	 * @param auteur
	 *            auteur de l'ouvrage
	 * @param editeur
	 *            editeur de l'ouvrage
	 * @param dateEdition
	 *            date d'edition
	 */
	public Ouvrage(String isbn, String titre, Auteur auteur, String editeur,
			GregorianCalendar dateEdition, HashSet<MotCle> mots) {

		this.setIsbn(isbn);
		this.setTitre(titre);
		this.setAuteur(auteur);
		this.setEditeur(editeur);
		this.setDateEdition(dateEdition);
		this.setExemplaires(new HashMap<Integer, Exemplaire>());
		this.setDerNumExemplaire(0);
		if (mots != null) {
			this.setMotsCles(mots);
			for (MotCle mC : mots) {
				_motscles.add(mC);
				mC.set_ouvrage(this);
			}
		}

	} // Fin Constructeur

	// ************************************************************************************************************
	// Méthodes privées
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Affecteurs

	/**
	 * setter de l'attribut auteur
	 * 
	 * @param auteur
	 *            valeur à affecter à l'auteur de l'ouvrage
	 */
	public void setAuteur(Auteur auteur) {// check nico & ant
		_auteur = auteur;
	}

	public void setExemp(HashSet<Exemplaire> e) {
		HashMap<Integer, Exemplaire> exe = null;
		for (Exemplaire ex : e) {
			exe.put(this.genererNumeroExemplaire(), ex);
		}
		_exemplaires = exe;
	}

	/**
	 * setter de l'attribut dateEdition
	 * 
	 * @param date
	 *            valeur à affecter à la date d'édition de l'ouvrage
	 */
	public void setDateEdition(GregorianCalendar date) {// check nico & ant
		_dateEdition = date;
	}

	/**
	 * setter de l'attribut editeur
	 * 
	 * @param editeur
	 *            valeur à affecter à l'éditeur de l'ouvrage
	 */
	public void setEditeur(String editeur) {// check nico & ant
		_editeur = editeur;
	}

	/**
	 * setter de l'attrribut exemplaires (ensemble des exemplaires de l'ouvrage)
	 * 
	 * @param exemplaires
	 *            hashmap d'exemplaires
	 */
	public void setExemplaires(HashMap<Integer, Exemplaire> exemplaires) {// check
																			// nico
																			// &
																			// ant
		_exemplaires = exemplaires;
	}

	/**
	 * setter de l'attribut num d'ISBN.
	 * 
	 * @param isbn
	 *            valeur à affecter à l'ISBN de l'ouvrage
	 */
	public void setIsbn(String isbn) {// check nico & ant
		this._isbn = isbn;
	}

	/**
	 * setter de l'attribut titre.
	 * 
	 * @param titre
	 *            valeur à affecter au titre de l'ouvrage
	 */
	public void setTitre(String titre) {// check nico & ant
		_titre = titre;
	}

	/**
	 * setter du num du dernier exemplaire.
	 * 
	 * @param derNumExemplaire
	 *            nombre à affecter au dernier numéro d'exemplaire
	 */
	private void setDerNumExemplaire(int derNumExemplaire) {// check nico & ant
		_derNumExemplaire = derNumExemplaire;
	}

	/**
	 * getter du dernier numéro d'exemplaire
	 * 
	 * @return un entier représentant le dernier numéro d'exemplaire de
	 *         l'ouvrage
	 */
	private int getDerNumExemplaire() {// check nico & ant
		return _derNumExemplaire;
	}

	private void setMotsCles(HashSet<MotCle> mots) {// check ant
		_motscles = mots;
	}

	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Lie un exemplaire à l'ouvrage. Insère un exemplaire dans l'ensemble des
	 * exemplaires de l'ouvrage, avec son numero.
	 * 
	 * @param numero
	 *            numero de l'exemplaire à insérer
	 * @param exemplaire
	 *            exemplaire à insérer
	 */
	private void setExemplaire(int numero, Exemplaire exemplaire) {
		_exemplaires.put(numero, exemplaire);
	} // Fin setExemplaire//check nico & ant

	/**
	 * Génère un numéro d'exemplaire.
	 * 
	 * @return un entier représentant le prochain numéro disponible pour un
	 *         exemplaire de l'ouvrage
	 */
	private int genererNumeroExemplaire() {
		this.setDerNumExemplaire(this.getDerNumExemplaire() + 1);
		return this.getDerNumExemplaire();
	} // Fin genererNumeroExemplaire//check nico & ant

	// ************************************************************************************************************
	// Méthodes publiques
	// ************************************************************************************************************

	// ------------------------------------------------------------------------------------------------------------
	// Accesseurs

	/**
	 * Getter de l'ISBN.
	 * 
	 * @return ISBN de l'ouvrage
	 */
	public String getIsbn() {
		return _isbn;// check nico & ant
	}

	/**
	 * Getter du titre.
	 * 
	 * @return titre de l'ouvrage
	 */
	public String getTitre() {
		return _titre;// check nico & ant
	}

	/**
	 * getter de l'auteur.
	 * 
	 * @return auteur de l'ouvrage
	 */
	public Auteur getAuteur() {
		return _auteur;// check nico & ant
	}

	/**
	 * getter de l'editeur.
	 * 
	 * @return editeur de l'ouvrage
	 */
	public String getEditeur() {
		return _editeur;// check nico & ant
	}

	/**
	 * getter de dateEdition.
	 * 
	 * @return date d'édition de l'ouvrage
	 */
	public GregorianCalendar getDateEdition() {
		return _dateEdition;// check nico & ant
	}

	/**
	 * getter d'un exemplaire de l'ouvrage.
	 * 
	 * @param numero
	 *            numero de l'exemplaire retourné
	 * @return l'exemplaire dont le numéro a été passé en paramètre, s'il existe
	 */
	public Exemplaire getExemplaire(int numero) {
		return (Exemplaire) _exemplaires.get(numero);
	} // Fin unExemplaire//check nico & ant

	/**
	 * getter de l'ensemble des exemplaires.
	 * 
	 * @return une collection d'exemplaires
	 */
	public Collection<Exemplaire> getExemplaires() {
		return _exemplaires.values();// check nico & ant
	}

	/**
	 * getter du nombre d'exemplaires de l'ouvrage.
	 * 
	 * @return un entier représentant le nombre d'exemplaires
	 */
	public int getNbExemplaires() {
		return _exemplaires.size();// check nico & ant
	}

	public HashSet<MotCle> getMotsCles() { // check ant
		return _motscles;
	}

	// ------------------------------------------------------------------------------------------------------------
	// Traitements

	/**
	 * Ajout d'un exemplaire à l'ouvrage. Le numéro de cet exemplaire est généré
	 * automatiquement pour l'ouvrage.
	 * 
	 * @param dateReception
	 *            date à laquelle l'exemplaire a été reçu.
	 * @return l'exemplaire ou null si la date de reception est antérieure à la
	 *         date d'édition de l'ouvrage
	 */
	public Exemplaire ajouterExemplaire(GregorianCalendar dateReception,
			int stat) {// check nico
		// Generation du numero chronologique de l'exemplaire
		int numero = this.genererNumeroExemplaire();
		if (this.verifDate(dateReception)) {
			// Creation de l'exemplaire
			Exemplaire exemplaire = new Exemplaire(numero, dateReception, stat,
					this);
			// liaison de l'ouvrage a l'exemplaire
			this.setExemplaire(numero, exemplaire);
			setChanged();
			notifyObservers();
			return exemplaire;
		} else {
			return null;
		}
	} // Fin ajouterExemplaire

	/**
	 * @param dateRecep
	 * @return vrai si date réception de l'exemplaire est postérieure à la date
	 *         édition de l'ouvrage
	 */
	private boolean verifDate(GregorianCalendar dateRecep) {// check nico & ant
		return (dateRecep.after(_dateEdition));
	}

	public int getNbExemplairesEnConsultation() {// check nico & ant
		int nb = 0;
		for (Exemplaire ex : this.getExemplaires()) {
			if (ex.estEnConsultation()) {
				nb = nb + 1;
			}
		}
		return nb;
	}

	public int getNbExemplairesEmpruntable() {// check nico & ant
		int nb = 0;
		for (Exemplaire ex : this.getExemplaires()) {
			if (ex.estEmpruntable()) {
				nb = nb + 1;
			}
		}
		return nb;
	}

	public synchronized Collection getInfosouv() {
		/*
		 * pour tous les exemplaires de cette ouvrage je prends leur numero et
		 * status et je les mets dans mytest , cette hashset je la rempli au fur
		 * et a meusure en avancant jusqu au dernier exemplaire , je revoi un
		 * ensemble d exemplaires de cette ouvrage
		 */

		//HashSet<Exemplaire> e;
		Collection e ;
		e =this.getExemplaires();
		Collection mytest = null;

		for (Iterator iterator = e.iterator(); iterator.hasNext();) {
			int num = ((Exemplaire) e).getNumero();
			int status = ((Exemplaire) e).getStatut();
			//int num = e.
			Exemplaire exe = new Exemplaire(num, null, status, null);
			mytest.add(exe);
		}
		return mytest;
	}

} // Fin Classe Ouvrage

