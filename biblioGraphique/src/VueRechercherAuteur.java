import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JSeparator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.ScrollPaneConstants;

// nothing to be imported from nico in this
public class VueRechercherAuteur extends Vue {

	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private DefaultListModel model;

	private JComboBox lesAuteurs;
	JTextArea textAreaPeriodique ;

	private JTextArea textAreaOuv;

	public VueRechercherAuteur(Controleur controleur,
			HashMap<String, Auteur> auteurs) {

		super(controleur);
		setTitle("Recherche par auteur");

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				getControleur().fermerVue(VueRechercherAuteur.this);
			}
		});
		setBounds(100, 100, 542, 536);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setBounds(12, 130, 540, 15);
		contentPane.add(separator);

		JButton butConsulter = new JButton("Consulter");
		butConsulter.setBounds(274, 93, 117, 25);
		contentPane.add(butConsulter);

		JButton butAnnuler = new JButton("Annuler");
		butAnnuler.setBounds(117, 93, 117, 25);
		contentPane.add(butAnnuler);

		JLabel lblOuvrages = new JLabel("Ouvrages");
		lblOuvrages.setBounds(238, 139, 70, 15);
		contentPane.add(lblOuvrages);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(53, 166, 439, 83);
		contentPane.add(scrollPane);

		textAreaOuv = new JTextArea();
		textAreaOuv.setEditable(false);
		scrollPane.setColumnHeaderView(textAreaOuv);

		// *********************************
		model = new DefaultListModel();
		// ***********************************
		// JList<? extends E> listOuvrage = new JList();
		// scrollPane.setViewportView(list);

		JLabel lblPeriodique = new JLabel("Periodique");
		lblPeriodique.setBounds(231, 261, 95, 15);
		contentPane.add(lblPeriodique);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(53, 308, 439, 91);
		contentPane.add(scrollPane_1);

		textAreaPeriodique = new JTextArea();
		scrollPane_1.setColumnHeaderView(textAreaPeriodique);

		JButton butFermer = new JButton("Fermer");
		butFermer.setBounds(212, 436, 117, 25);
		contentPane.add(butFermer);

		JLabel lblAuteur = new JLabel("Auteur");
		lblAuteur.setBounds(12, 26, 70, 15);
		contentPane.add(lblAuteur);

		// comboBoxMotCle = new JComboBox<String>();
		// lesAuteurs = new ComboBox<String>();
		lesAuteurs = new JComboBox();
		lesAuteurs.setBounds(114, 21, 277, 25);
		contentPane.add(lesAuteurs);

		butAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueRechercherAuteur.this);
			}
		});
		butFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueRechercherAuteur.this);
			}
		});

		// afin d afficher la liste des auteurs
		for (Auteur au : getControleur().getAuteur()) {
			lesAuteurs.addItem(au.get_nom() + " " + au.get_prenom());
		}

		butConsulter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (lesAuteurs.getSelectedItem() != null) {
					String auteur = lesAuteurs.getSelectedItem().toString();
					auteur = auteur.replaceAll("\\s+", "");// pour enlever les
															// espaces
					Auteur aut;
					aut = getControleur().getAuteur(auteur);
					// System.out.println(aut.toString());
					if (aut != null) {
						alimenteOuvrage(aut);
						alimentePeriodique(aut);
					} else if (aut == null) {
						Message dialog2 = new Message("Auteur non existant");
					}

				} else {
					Message dialog3 = new Message(
							"Pas encore d'auteur dans la base");
				}
			}
		});

	}

	/*
	 * public void alimente(Ouvrage ouv) { textAreaInfosExemplaires.setText("");
	 * for (Exemplaire exemplaire : ouv.getExemplaires()) {
	 * textAreaInfosExemplaires.append(
	 * "numéro "+String.valueOf(exemplaire.getNumero()) + " : " +
	 * exemplaire.libStatut() + "\n"); } }
	 */
	public synchronized void alimenteOuvrage(Auteur a) {
		// tout cela , c est tous ce que j aurais voulu pouvoir faire !! mais
		// qui ne marchent pas encore
		/*
		 * HashSet<Ouvrage> ouv=null; ouv = a.getInfosOuv();
		 * textAreaOuv.setText(""); for (Ouvrage o : ouv) {
		 * textAreaOuv.append("Isbn " + o.getIsbn() + " - Titre " + o.getTitre()
		 * + "\n"); Collection ex = o.getExemplaires(); for (int
		 * i=0;i<ex.size();i++) { textAreaOuv.append("num " +
		 * ((Exemplaire)ex).getNumero() + " - status " +
		 * ((Exemplaire)ex).getStatut() + "\n"); } }
		 */
		
		// version temporaire 
		HashSet<Ouvrage> ouv = a.get_mesOuvrages();
		textAreaOuv.setText("");
		for (Ouvrage o : ouv) {
			textAreaOuv.append("Isbn : " + o.getIsbn() + " - Titre : " + o.getTitre() + "\n");
		}
		textAreaOuv.append("Le nombre d'exemplaire pour chaque ouvrage et d'autres\n fonctionnalité sont à venir !\n");
	}

	public void alimentePeriodique(Auteur a) {

		textAreaPeriodique.setText("");

		/*HashSet<Article> art = null;
		art = a.getInfos();
		*/
		
		//temporaire
		Parution pa = null;
		Periodique pe = null;
		HashSet<Article> arts = a.get_mesArticles();
		for (Article article : arts){
			pa = article.get_par();
			if (pa != null){
				pe = pa.get_periodique();
				textAreaPeriodique.append("Issn : " + pe.get_issn() + " nom : " + pe.get_nom() + " ID parution : " + pa.get_identParution() + " Titre : " + article.get_titre() + "\n");
			}
			
		}
		
		/*
		 * HashSet<Article> ar = a.get_mesArticles(); Parution par=null; for
		 * (Article art : ar){ par = art.getInfosParution(); }
		 */
	}

	public void alimenteOuvrages(HashSet<Ouvrage> ouv) {
		if (!(ouv.isEmpty())) {
			for (Ouvrage ouvrage : ouv) {
				model.addElement(ouv);
			}
		} else if (ouv.isEmpty()) {
			model.addElement("pas d ouvrage");

		}

	}

	public void update(Observable o, Object arg) {

	}
}
