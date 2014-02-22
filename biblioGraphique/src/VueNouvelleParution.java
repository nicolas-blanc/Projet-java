import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JTextArea;


public class VueNouvelleParution extends Vue {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textIssn;
	private JTextField textFieldTitreArticle;
	private JTextField textFieldNomPerio;
	private JList listMotsCles;
	private JButton btnEnregistrerArticle;
	private JComboBox comboBox;
	private JButton btnEnregSelecParution;
	private JButton btnRechercher;
	private JComboBox comboBoxIdentifiant;
	private JTextArea textArea;
	
	// à ajouter car la vue est observatrice d'un ouvrage
	private Parution _parution ;
		
	Periodique per;
	
	/**
	 * Create the frame.
	 */
	public VueNouvelleParution(Controleur controleur) {
		super(controleur);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIssn = new JLabel("Issn");
		lblIssn.setBounds(54, 12, 70, 15);
		contentPane.add(lblIssn);
		
		textIssn = new JTextField();
		textIssn.setBounds(169, 10, 114, 19);
		contentPane.add(textIssn);
		textIssn.setColumns(10);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				per = getControleur().rechPeriodique(textIssn.getText());
				if (per != null) {
					alimenteIdentifiant(per);
				}
			}
		});
		btnRechercher.setBounds(302, 7, 117, 25);
		contentPane.add(btnRechercher);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueNouvelleParution.this);}
		});
		btnAnnuler.setBounds(195, 486, 117, 25);
		contentPane.add(btnAnnuler);
		
		JLabel lblTitreArticle = new JLabel("Titre Article");
		lblTitreArticle.setBounds(54, 133, 93, 15);
		contentPane.add(lblTitreArticle);
		
		textFieldTitreArticle = new JTextField();
		textFieldTitreArticle.setBounds(172, 131, 114, 19);
		contentPane.add(textFieldTitreArticle);
		textFieldTitreArticle.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		alimenteComboAuteur();
		comboBox.setBounds(169, 162, 219, 25);
		contentPane.add(comboBox);
		
		JLabel lblAuteur = new JLabel("Auteur");
		lblAuteur.setBounds(54, 167, 70, 15);
		contentPane.add(lblAuteur);
		
		btnEnregistrerArticle = new JButton("Enregistrer");
		btnEnregistrerArticle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String titre = textFieldTitreArticle.getText();
				String aut = (String)comboBox.getSelectedItem();
				String prenomauteur;
				String nomauteur;
				StringTokenizer parser = new StringTokenizer(aut);
						nomauteur = (parser.nextToken());
						prenomauteur = (parser.nextToken());
			    HashSet<MotCle> motscles = new HashSet<MotCle>();
			    ArrayList<String> mots = new ArrayList<String>();
				if (!(listMotsCles.isSelectionEmpty())){
					mots = (ArrayList<String>)listMotsCles.getSelectedValuesList();
				}			
				for (int i = 0; i < mots.size(); i++){
					//getControleur().getMot(mots.get(i));
					motscles.add(getControleur().getMot(mots.get(i)));
				}
				String id = (String)comboBoxIdentifiant.getSelectedItem();
				getControleur().nouvelArticle(titre,prenomauteur,nomauteur,per,id,motscles);
				setParution(getControleur().rechParution(id,per));
			}
		});
		btnEnregistrerArticle.setBounds(302, 337, 117, 25);
		contentPane.add(btnEnregistrerArticle);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(126, 212, 293, 101);
		contentPane.add(scrollPane);

		DefaultListModel model = new DefaultListModel();
		listMotsCles = new JList(model);
		scrollPane.setViewportView(listMotsCles);
		alimenteMotsCles(model);
		
		JLabel lblMotsCls = new JLabel("Mot(s) Clé(s)");
		lblMotsCls.setBounds(29, 245, 93, 15);
		contentPane.add(lblMotsCls);
		
		JLabel lblNomPeriodique = new JLabel("Nom Periodique");
		lblNomPeriodique.setBounds(29, 39, 118, 15);
		contentPane.add(lblNomPeriodique);
		
		textFieldNomPerio = new JTextField();
		textFieldNomPerio.setBounds(169, 41, 114, 19);
		contentPane.add(textFieldNomPerio);
		textFieldNomPerio.setColumns(10);
		
		JLabel lblIdentifiant = new JLabel("Identifiant");
		lblIdentifiant.setBounds(39, 66, 85, 15);
		contentPane.add(lblIdentifiant);
		
		btnEnregSelecParution = new JButton("Enregister/Sélectionner");
		btnEnregSelecParution.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (String)comboBoxIdentifiant.getSelectedItem();
				if (per.getParution(id) != null){
			    alimenteArticle(per.getParution(id));}
				else {
					HashSet<Article> art = new HashSet<Article>();
					getControleur().nouvelleParution(id,per,art);
					setParution(getControleur().rechParution(id,per));
				}
			}
		});
		btnEnregSelecParution.setBounds(297, 106, 202, 25);
		contentPane.add(btnEnregSelecParution);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(94, 378, 378, 96);
		contentPane.add(scrollPane_1);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane_1.setColumnHeaderView(textArea);
		
		JLabel lblArticles = new JLabel("Article(s) :");
		lblArticles.setBounds(12, 390, 79, 15);
		contentPane.add(lblArticles);
		
		comboBoxIdentifiant = new JComboBox();
		comboBoxIdentifiant.setEditable(true);
		comboBoxIdentifiant.setBounds(169, 72, 219, 25);
		contentPane.add(comboBoxIdentifiant);
	}


	
	public void alimenteComboAuteur(){
		if (getControleur().getAuteur() == null){
		}
		else {
			for (Auteur auteur : getControleur().getAuteur()) {
			  comboBox.addItem(auteur.get_nom()+" "+auteur.get_prenom());
		}
		}
	}
	
	
	void alimentePeriodique(Periodique per) {
		textFieldNomPerio.setText(per.get_nom());
		//textFieldNbrPar.setText(String.valueOf(per.getNbParution())); 
	}
	
	public void alimenteMotsCles(DefaultListModel model){
		if (getControleur().getMotCle() == null) {
		}
		else {
			for (MotCle motcle : getControleur().getMotCle()) {
				model.addElement(motcle.get_motCle());
		}
		}
	}
	
	public void alimenteIdentifiant( Periodique per) {
		for (Parution par : per.getParutions()) {
			comboBoxIdentifiant.addItem( par.get_identParution());
		}
	}
	
	public void  alimente (Parution par){
		comboBox.removeAllItems();
		textArea.setText(null);
		alimenteComboAuteur();
		alimenteArticle(par);
	}
	public void alimenteArticle (Parution par){
		Iterator<Article> iter;
		iter= (par.get_articles()).iterator();
		while (iter.hasNext()){
			Article art = iter.next();
			textArea.append(art.get_titre()+"   "+art.get_auteur().get_nom()+" "+art.get_auteur().get_prenom()+"\n");
		}
		
	}
	
	private Parution getParution() {
		return _parution;
	}
	private void setParution(Parution parution) {
		 _parution = parution;
	}
	
	public void update (Observable o , Object arg)
	{
		//reafficher ce qui existait
		this.alimenteArticle(this.getParution());
		// liaison de la vue avec l'objet observé
		super.update();			
	}
}