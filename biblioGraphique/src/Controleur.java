
import java.io.Serializable;
import java.util.Observer;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.io.BufferedReader;
import java.util.StringTokenizer;
/**
 * Classe controleur et application (système)
 * @author IUT,   A. Culet
 * @version 1.0 
 */


public class Controleur implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	
	/**
	 * La classe Controleur est unique pour tous les cas d'utilisation
	 * Elle est également la classe "application" qui gère l'ensemble des objets de l'appli
	 */	
	// ************************************************************************************************************
		// Attributs
		// ************************************************************************************************************
	
		// Attributs d'Association
		// Ensemble des ouvrages de la bibliothèque
		private HashMap<String, Ouvrage> _ouvrages; //check nico
		
		// Ensemble des Auteurs de la bibliothèque
		private HashMap<String, Auteur> _auteurs; //check nico
		
		// Ensemble des Auteurs de la bibliothèque
		private HashMap<String, MotCle> _motCle; //check nico

		private HashMap<String, Periodique> _periodique; 

		// la liste des vues. La 1ere est toujours la vue Menu Principal. La dernière est la vue active.
		private LinkedList<Vue> _vues;//check nico
		
		
		
		// ************************************************************************************************************
		// Constructeur
		// ************************************************************************************************************

		public Controleur() {
			this.setOuvrages(new HashMap<String, Ouvrage>());
			
			//ajoute par nico
			this.setAuteurs(new HashMap<String, Auteur>());
			
			// ajoute par ant
			this.setMotsCle(new HashMap<String, MotCle>());
			this.setPeriodiquep(new HashMap<String,Periodique>());
			this.lectureLignesFichier();
			//******************
			
			_vues = new LinkedList<Vue>();
		} // Fin Controleur //check nico

		public void lectureLignesFichier(){ // check ant

		try  {
			BufferedReader in = new BufferedReader(
						new FileReader("ListeAutorite.txt"));
			String ligne;
			while ((ligne= in.readLine()) != null)  {
				/*** traitement de la chaine ch ***/
				MotCle motcle = new MotCle(ligne);
				this.setMotCle(motcle, motcle.get_motCle());
			}
			in.close();
		}

		catch (IOException e) {
		 	System.out.println("$$$$$ PB de Lecture dans le fichier ListeAutorité ");
			System.out.println();
		}


		} //  lectureLignesFichier;
		
		// ************************************************************************************************************
		// Méthodes privées
		// ************************************************************************************************************

		// ------------------------------------------------------------------------------------------------------------
		// Affecteurs
		
		/**
		 * Ajoute un ouvrage à l'ensemble des ouvrages de la bibliothèque.
		 * @param ouvrage 	Ouvrage à ajouter
		 * @param isbn 	code ISBN de cet ouvrage
		 */
		private void setOuvrage(Ouvrage ouvrage, String isbn) {
			this.getOuvrages().put(isbn, ouvrage);
		} // Fin setOuvrage//check nico & ant
		
		private void setPeriodique(Periodique perio, String issn) {
			this.get_Periodique().put(issn, perio);
		} // Fin setOuvrage

		/**
		 * @param ouvrages hashtable d'ouvrages à affecter
		 */
		private void setOuvrages(HashMap<String, Ouvrage> ouvrages) {
			_ouvrages = ouvrages;
		}// Fin setOuvrages//check nico & ant
		
		public void setAuteur(Auteur auteur, String nomprenom){
			this.getAuteurs().put(nomprenom, auteur);//check nico & ant
		}
		
		private void setMotCle(MotCle motCle, String mc) {
			this.getMotCles().put(mc, motCle);//check nico & ant
		}
		
		private void setAuteurs(HashMap<String, Auteur> auteurs) {
			_auteurs = auteurs;//check nico & ant
		}
		
		private void setMotsCle(HashMap<String, MotCle> motCle) {
			_motCle = motCle;//check nico
		}
		
		private void setPeriodiquep(HashMap<String, Periodique> perio) {
			_periodique = perio;//check nico
		}
		
		/**
		 * ajoute ou enlève la vue active courante de la liste des vues
		 * @param vue  la vue à affecter
		 */
		 private void setVue(Vue vue) {
				_vues.addLast(vue);//check nico
		 }
		 private void removeVue() {
				_vues.removeLast();//check nico
		 }
		
		/*
		// ------------------------------------------------------------------------------------------------------------
		// Accesseurs
		
		/**
		 * @return ensemble des ouvrages de la bibliothèque
		 */

			public HashMap<String, Auteur> get_auteurs() { // check ant
				return _auteurs;
			}

			public void set_auteurs(HashMap<String, Auteur> _auteurs) {
				this._auteurs = _auteurs;
			}

			public HashMap<String, MotCle> getMotCles() {
				return _motCle;//check nico & ant
			}
			
			public MotCle getMot(String mot){
				return _motCle.get(mot);
			}
			
			private HashMap<String, Auteur> getAuteurs(){
				return _auteurs;//check nico
			}
			
			public HashMap<String, Periodique> get_Periodique() {
				return _periodique;
			}
			
			public Collection<Auteur> getAuteur() {
				return _auteurs.values();//check nico & ant
			}
			
			public Collection<MotCle> getMotCle() {
				return _motCle.values();//added by ant
			}
			
			public void set_Periodique(HashMap<String, Periodique> periodique) {
				this._periodique= periodique;
			}
			
			public void set_motCles(HashMap<String, MotCle> _motCles) {
				this._motCle = _motCles;
			}
		 
		private HashMap<String, Ouvrage> getOuvrages() {//check nico & ant
			return _ouvrages;
		}// Fin getOuvrages
		
		private HashMap<String, Periodique> getPeriodiques() {//check nico & ant
			return _periodique;
		}// Fin getOuvrages
		 
		Auteur getAuteur(String nomprenom){//check nico & ant
			return this.getAuteurs().get(nomprenom);
		}
		
		private MotCle getMotCle(String motCle) {//check nico
			return this.getMotCles().get(motCle);
		}

		/**
		 * Accès à un ouvrage par son numéro ISBN
		 * @param isbn 	le code ISBN de l'ouvrage cherché
		 * @return l'ouvrage qui a l'ISBN indiqué
		 */
		private Ouvrage getOuvrage(String isbn) {//check nico & ant
			return this.getOuvrages().get(isbn);
		} // Fin getOuvrage
		
		private Periodique getPeriodique(String issn) {//check nico & ant
			return this.getPeriodiques().get(issn);
		}
		/**
		 * @return la vue active courante 
		 */
		private Vue getVue() {
			return (Vue)_vues.getLast() ;
		}
		/**
		 * @return la vue Menu Principal 
		 */
		private VueMenuBiblio getVueMenu() {
			return (VueMenuBiblio)_vues.getFirst() ;
		}
		
		
		/*
		// ************************************************************************************************************
		// Méthodes publiques de création et affichage des fenêtres de l'application et fermeture
		// ************************************************************************************************************
		/**
		 * Création et affichage de la fenêtre principale de l'application. 
		 * Elle propose le menu de l'appli
		 */
		
		public void menuBiblio() {
			try {this.setVue(new VueMenuBiblio(this));
				this.getVueMenu().setVisible(true); 	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		/**
		 * Cas d'utilisation : saisie d'un exemplaire d'ouvrage
		 * Création et affichage de la fenêtre de saisie d'un exemplaire d'ouvrage
		 */
		public void saisirExemplaire() {
			try {this.setVue(new VueSaisieExemplaire(this));
			// le Menu est caché
				this.getVueMenu().setVisible(false); 
			// la vue courante est VueSaisieExemplaire
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		/**
		 * cas d'utilisation : saisie d'un ouvrage
		 * Création et affichage de la fenêtre de saisie d'un ouvrage
		 */
		public void saisirOuvrage() {
			try {this.setVue(new VueSaisieOuvrage(this));
			// le Menu est caché
				this.getVueMenu().setVisible(false); 
			// la vue courante est VueSaisieOuvrage
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		public void consulterOuvrage() {//check nico
			try {this.setVue(new VueConsulterOuvrage(this));
			// la vue courante est VueConsulterOuvrage
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		/**
		 * cas d'utilisation : recherche par mot cle
		 * Creation et affichage de la fenetre de la recherche par mot cle
		 */
		public void rechercheMotCle() {//check nico
			try {this.setVue(new VueRechMotCle(this, _motCle));
			// la vue courante est VueConsulterOuvrage
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		public void rechercheParMotCle(String mC) {//check nico
			MotCle motCle = getMotCle(mC);
			((VueRechMotCle) getVue()).alimente(motCle);
		}
		
		public void enregPeriodique() {
			try {this.setVue(new VueEnregistrerPeriodique(this));
			// le Menu est caché
				this.getVueMenu().setVisible(false); 
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void rechercherAuteur(){
			try {this.setVue(new VueRechercherAuteur(this,this._auteurs));
			// le Menu est caché
				this.getVueMenu().setVisible(false); 
				this.getVue().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void consulterPeriodique() {//check nico
			try {this.setVue(new VueConsulterPeriodique(this));
				this.getVue().setEtat(Vue.initiale);
				this.getVue().setVisible(true);
			} catch (Exception e) {
			e.printStackTrace();
			}
		}
		
		public void nouvelleParution(){
			try {this.setVue(new VueNouvelleParution(this));
			this.getVue().setEtat(Vue.initiale);
			this.getVue().setVisible(true);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}
		
		/**
		 * fermeture de la fenêtre vue
		 * lors de la fermeture de la fenêtre principale de l'application sauvegarde des objets sérialisés 
		 */
		public void fermerVue (Vue vue) {
			//la vue est détruite et n'est plus la vue active courante	
			
			if (vue instanceof VueMenuBiblio ) {
			// Quitte l'aplication. Sauvegarde les objets du modèle
				vue.dispose();
				this.removeVue();this.sauve();
				System.exit(0);
				}
			else {
			// le Menu est rendu de nouveau visible
				vue.dispose();
				this.removeVue();
				this.getVueMenu().setVisible(true); 
			}
		}
		
		/*
		// ************************************************************************************************************
		// Opérations liées à la sérialisation des objets de l'application
		// ************************************************************************************************************
		/**
		 *  restauration des objets de l'application
		 */
		public Controleur restaure() {
			try {
				FileInputStream fichier = new FileInputStream("Fsauv.ser");
				ObjectInputStream in = new ObjectInputStream(fichier);
				return((Controleur) in.readObject());
			} catch (Exception e) {
				Message dialog = new Message("Pbs de Restauration ou fichier non encore créé");
				return this;
			} 
		}
		/**
		 *  sauvegarde des objets de l'application
		 */
		private void sauve() {
			try {
				FileOutputStream f = new FileOutputStream("Fsauv.ser");
				ObjectOutputStream out = new ObjectOutputStream(f);
				out.writeObject(this);
			} catch (Exception e) {
				Message dialog = new Message("Pb de Sauvegarde dans le fichier");
			}
		}
		// ************************************************************************************************************
		// Opérations liées à l'application en réponse à une action de l'utilisateur dans une vue
		// ************************************************************************************************************

		/**
		 * Accès à un ouvrage par son numéro ISBN
		 * Invoqué VueSaisieExemplaire
		 * @param isbn 	le code ISBN de l'ouvrage cherché
		 * @return l'ouvrage qui a l'ISBN indiqué ou null
		 * affiche un message d'erreur si l'ouvrage n'est pas trouvé
		 */
		public Ouvrage rechOuvrage(String isbn) {
			Ouvrage ouv = this.getOuvrage(isbn);
			if (ouv == null) {
				Message dialog = new Message("Ouvrage inconnu");
			}
			else {
				// le contrôleur modifie l'état de la vue
					this.getVue().setEtat(Vue.inter1);	
				    ouv.addObserver(this.getVue());
				// le controleur demande à la vue d'afficher les infos de l'ouvrage
					((VueSaisieExemplaire)this.getVue()).alimente(ouv);
					}	
			return ouv;
		} // Fin rechOuvrage
		
		public Ouvrage rechercherOuvrage(String isbn){ // check nico & ant 
			Ouvrage ouv = this.getOuvrage(isbn);
			if (ouv == null){
				Message dialog = new Message("Ouvrage introuvable");
			}
			else {
				((VueConsulterOuvrage)this.getVue()).alimente(ouv);
			}
			return ouv;
		}
		
		public Ouvrage rechOuv(String isbn){// check nico & ant
			return this.getOuvrage(isbn);
		}
		//**************************************************************************
		// pas besoin pour l instant !!
		//public void rechAuteur(String aut){
			/*StringTokenizer parser = new StringTokenizer(aut);
			String nomAut=(parser.nextToken());;
			String prenomAut=(parser.nextToken());;
			if(_auteurs.get(aut)!=null){
				Message dialog = new Message("l auteur existe");
			}
			else 
			{
				Message dialog = new Message("l auteur non existant");
			}*///test
			

						
			
		//}
		
		//**************************************************************************

		public Parution rechParution(String id,Periodique per) {
			Parution paru = per.getParution(id);
			if (paru == null) {
				Message dialog = new Message("Parution inconnue");
			}
			else {
				// le contrôleur modifie l'état de la vue
					this.getVue().setEtat(Vue.inter1);	
				    paru.addObserver(this.getVue());
				// le controleur demande à la vue d'afficher les infos de l'ouvrage
					((VueNouvelleParution)this.getVue()).alimente(paru);
					}	
			return paru;
		} // Fin rechParution
		/**
		 * Création d'un exemplaire d'ouvrage 
		 * Invoqué dans VueSaisieExemplaire
		 * @param ouv l'ouvrage  dateRecep la date de réception de l'exemplaire	
		 * affiche un message de confirmation après l'enregistrement ou un message d'erreur 
		 */
		public void nouvExemplaire(Ouvrage ouv, String dateReception, String statut) {
			// vérification de la présence de la date et de son format
			if (dateReception.length() == 0 ){
					Message dialog = new Message("La date de réception est obligatoire");
					}
			else {
				GregorianCalendar date = ESDate.lireDate (dateReception);
				if (date == null) {
					Message dialog = new Message("Le format de la date est incorrect");
					}
				else {
					int statutEx;
					if (statut == "empruntable") {
						statutEx = Exemplaire.EMPRUNTABLE ; }
					else {
						statutEx = Exemplaire.EN_CONSULTATION ; }
			// demande d'ajout de l'exemplaire
					Exemplaire exemplaire = ouv.ajouterExemplaire(date, statutEx);
			// l'opération s'est bien passée
					//ouv.addObserver((VueSaisieExemplaire)this.getVue());
					
					if (exemplaire != null) {
			// le contrôleur modifie l'état de la vue
						this.getVue().setEtat(Vue.finale);
			// affichage d'un message de confirmation
						Message dialog = new Message("Exemplaire enregistré");

					}
					else {
						Message dialog = new Message("Date de Reception incorrecte / à la date d'Edition.");
					}
				}
			}
		} // Fin nouvExemplaire
		
		/**
		 * Création d'un  d'ouvrage 
		 * Invoqué dans VueSaisieOuvrage
		 * @param  dateEdition la date d'édition de l'ouvrage
		 * affiche un message de confirmation après l'enregistrement ou un message d'erreur 
		 */
		/*
		public void nouvOuvrage(String isbn, String titre, String nomauteur,String prenomauteur, String editeur, String dateEdition,HashSet<MotCle> mots) {
			// vérification de la présence des infos obligatoires et du format de la date
			if ((isbn.length() == 0) || (titre.length() == 0) || (editeur.length() == 0 )|| (dateEdition.length() == 0 || prenomauteur.length()==0)|| (nomauteur.length()==0)){
					Message dialog = new Message("Tous les champs sont obligatoires");
					}
			else {
				GregorianCalendar date = ESDate.lireDate (dateEdition);
				if (date == null) {
					Message dialog = new Message("Le format de la date est incorrect");
					}

				else if (this.getOuvrage(isbn )== null) {
					//Auteur auteur =new Auteur(auteur,auteur) ; 
					Auteur auteur;
					if(_auteurs.get(nomauteur+prenomauteur)==null){
					auteur = new Auteur(nomauteur,prenomauteur);
					
					this.setAuteur(auteur, nomauteur+prenomauteur);}
					
					else{
					auteur = _auteurs.get(nomauteur+prenomauteur);}
					
				// Instanciation de l'ouvrage
					Ouvrage ouvrage = new Ouvrage(isbn, titre, auteur, editeur, date , mots);
				// Ajout de l'ouvrage dans l'ensemble des ouvrages de la bibliothèque
					this.setOuvrage(ouvrage, isbn);
					
					Message dialog = new Message("Ouvrage enregistré");
					this.fermerVue (this.getVue());
					} 
					else {
						Message dialog = new Message("Ouvrage déjà présent");
					}
				}
			}*/
		public void nouvOuvrage(String isbn, String titre, String nomauteur,String prenomauteur, String editeur, String dateEdition,HashSet<MotCle> mots) {
			Auteur aut = new Auteur ("","");
			// vérification de la présence des infos obligatoires et du format de la date
			GregorianCalendar date = ESDate.lireDate (dateEdition);
			if ((isbn.length() == 0) || (titre.length() == 0) || (editeur.length() == 0 )|| (dateEdition.length() == 0 || prenomauteur.length()==0)|| (nomauteur.length()==0)){
					Message dialog = new Message("Tous les champs sont obligatoires");
					}

			else if (date == null) {
				Message dialog = new Message("Le format de la date est incorrect");
				}
				
			else {	
				if (this.getOuvrage(isbn )== null) {
					
					if (_auteurs.get(nomauteur+prenomauteur)==null){
						aut = new Auteur(nomauteur,prenomauteur);
						this.setAuteur(aut, nomauteur+prenomauteur);
					}
					else {
						aut = _auteurs.get(nomauteur+prenomauteur);
					}
					// Instanciation de l'ouvrage
					Ouvrage ouvrage = new Ouvrage(isbn, titre, aut, editeur, date , mots);
					// Ajout de l'ouvrage dans l'ensemble des ouvrages de la bibliothèque
					this.setOuvrage(ouvrage, isbn);
					//Ajout de l'ouvrage nouvellement cr�e dans la liste des ouvrages de l'auteur
					aut.ajout_ouvrage(ouvrage);
					Message dialog = new Message("Ouvrage enregistré");
					this.fermerVue (this.getVue());
					} 
					else {
						Message dialog = new Message("Ouvrage déjà présent");
					}
				}
			}
		 // Fin nouvOuvrage
		
		public void nouvPeriodique(String issn , String nom){
			
		if (this.getPeriodique(issn)!=null){
			Message dialog = new Message("Le periodique existe déjà.");
		}
		else
		{/*
			Periodique perio = new Periodique(issn , nom ,null);
			HashSet<Parution> paru = new HashSet<Parution>();
			perio.set_mesParutions(paru);
			
		if (this.getPeriodique(issn)!=null){
			Message dialog = new Message("La periodique existe déjà.");
		}
		else
		{
			this.setPeriodique(perio, issn );
			Message dialog = new Message("La periodique a été enregistrée avec succès!");
		}		*/

			Periodique perio = new Periodique(issn , nom);
			HashMap<String,Parution> paru = new HashMap<String,Parution>();
			perio.set_mesParutions(paru);
			this.setPeriodique(perio, issn );
			Message dialog = new Message("Le periodique a été enregistrée avec succès!");
			//this.fermerVue (this.getVue());
		}	
		
		}
		public void nouvelleParution(String id, Periodique per, HashSet<Article> art) {
			Parution par = new Parution(id,per,art);
			per.ajouterParution(par);
			Message dialog = new Message("La parution a bien �t� enregistr�e!");
		}
		//Fin nouvellePArution
		public void nouvelArticle(String titre, String prenomauteur, String nomauteur,Periodique per,String id, HashSet<MotCle> mots){
			Auteur aut = new Auteur("","");
			if ((titre.length() == 0) || (prenomauteur.length() == 0) || (nomauteur.length() == 0)){
				Message dialog = new Message("Tous les champs sont obligatoires");
				}
			else {
				if (_auteurs.get(nomauteur+prenomauteur)==null){
					//aut = new Auteur(nomauteur,prenomauteur);
					aut.set_nom(nomauteur);
					aut.set_prenom(prenomauteur);
					this.setAuteur(aut, nomauteur+prenomauteur);
				}
				else {
					Auteur aut2 = _auteurs.get(nomauteur+prenomauteur);
					aut.set_nom(aut2.get_nom());
					aut.set_prenom(aut2.get_prenom());
				}

			Parution par = per.getParution(id);
			Article art = new Article(titre, aut, par, mots);
			par.ajout_article(art);
			aut.ajout_article(art);
			Message dialog = new Message("Article enregistré");
		}
		}

		//FinnouvelArticle
		/////////////////////////////////////////////////
		public void rechAuteurRech(String aut){
			StringTokenizer parser = new StringTokenizer(aut);
			String nomAut=(parser.nextToken());
			String prenomAut=(parser.nextToken());
			//((VueRechercherAuteur)this.getVue()).alimenteAuteur( _auteurs.get(nomAut+prenomAut)); // on donne a la vue l auteur 
			 //_auteurs.get(nomAut+prenomAut);
			
		}
		public void rechAuteur(String aut){
			//Auteur aut = new Auteur(nom,prenom);
			/*
			if (this._auteurs.get(aut)!=null)
			{
			this._auteurs.get(aut);
			((VueRechercherAuteur)this.getVue()).alimenteAuteur(aut);

			}
			else
			{
				Message dialog = new Message("L'auteur non existant");
			}	*/
		}
		/////////////////////////////////////////////////
		public void rechOuvrageAut(String Auteur){
			HashSet<Ouvrage> ouven =new HashSet<Ouvrage>();
			
			StringTokenizer parser = new StringTokenizer(Auteur);
			String nomAut=(parser.nextToken());
			String prenomAut=(parser.nextToken());
			
			Auteur aut = new Auteur(nomAut,prenomAut);
			ouven = aut.get_mesOuvrages();
			((VueRechercherAuteur)this.getVue()).alimenteOuvrages(ouven); 
		}
		
		/*public HashSet<Ouvrage> rechOuvrageAut(String Auteur){
			
			HashSet<Ouvrage> ouven =new HashSet<Ouvrage>();
			
			StringTokenizer parser = new StringTokenizer(Auteur);
			String nomAut=(parser.nextToken());
			String prenomAut=(parser.nextToken());
			
			Auteur aut = new Auteur(nomAut,prenomAut);
			aut = _auteurs.get(aut); // on cherche l auteur parmi tous nos auteurs 
			ouven = aut.get_mesOuvrages();
			
					return ouven; // on renvoie toutes les ouvrages de cette auteur
			
			//((VueRechercherAuteur)this.getVue()).alimenteOuvrages(ouven); 
		}*/
		
		
		public void nouvAuteur(String nom, String prenom){ // check nico & ant
			if (this.getAuteur(nom+prenom) == null){
				Auteur auteur = new Auteur(nom,prenom);
				this.setAuteur(auteur, nom+prenom);
			}
			else {
				Message dialog = new Message ("L'auteur existe dÃ©jÃ .");		
			}
		}
		
		public Periodique rechPeriodique(String issn) {
			Periodique per = this.getPeriodique(issn);
			if (per == null){
				Message dialog = new Message("Perdioque introuvable");
			}
			else {
				((VueNouvelleParution)this.getVue()).alimentePeriodique(per);
			}
			return per;
		}
		
		public Periodique recherchePeriodique(String issn) {
			Periodique per = this.getPeriodique(issn);
			if (per == null){
				Message dialog = new Message("Perdioque introuvable");
			}
			else {
				((VueConsulterPeriodique)this.getVue()).alimentePeriodique(per);
			}
			return per;
		}
		
		public void rechercheParMotCle(HashSet<MotCle> mC) {
			for (MotCle motcle : mC)
				((VueRechMotCle) getVue()).alimente(motcle);
		}
		
		
}

