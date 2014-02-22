import java.io.Serializable;
import java.util.HashSet;
import java.util.Observable;


public class Parution extends Observable implements Serializable {
	// ************************************************************************************************************
		// Attributs
		// ************************************************************************************************************
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		private String _identParution;
		private Periodique _periodique;
		
		private HashSet<Article> _articles; // un ensemble d article
		// ************************************************************************************************************
		// Constructeur
		// ************************************************************************************************************
		/**
		 * Constructeur. Cr�e un Parution d'ouvrage au statut NonDisponible.
		 * 
		 * @param numero		num�ro de l'Parution dans l'ouvrage.
		 * @param dateReception	date de la r�ception de cet Parution.
		 * @param ouvrage		ouvrage dont cet Parution est un repr�sentant.
		 */
		public Parution(String id,  Periodique periodique,HashSet<Article> article) {
			this.set_identParution(id);
			this.set_periodique(periodique);
			this.set_articles(article);
			
		} // Fin Constructeur
		
		public String get_identParution() {
			return _identParution;
		}
		public void set_identParution(String _identParution) {
			this._identParution = _identParution;
		}
		public Periodique get_periodique() {
			return _periodique;
		}
		public void set_periodique(Periodique _periodique) {
			this._periodique = _periodique;
		}
		public HashSet<Article> get_articles() {
			return _articles;
		}
		public void set_articles(HashSet<Article> _articles) {
			this._articles = _articles;
		}
		public void ajout_article (Article art){
			this._articles.add(art);
			setChanged();
			notifyObservers();
		}
		public Periodique getInfosPeriodique(){
			return this._periodique;
		}
}

