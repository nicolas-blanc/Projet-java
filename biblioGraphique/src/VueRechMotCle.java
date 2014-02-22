import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class VueRechMotCle extends Vue {

	private static final long serialVersionUID = 1L;
	private JButton btnRechercher;
	private JButton btnQuitter;
	private JScrollPane scrollPaneOuvrage;
	private JScrollPane scrollPaneArticle;
	private JScrollPane scrollPaneMotCle;
	private JTextArea textAreaOuvrage;
	private JTextArea textAreaArticle;
	private JList listMotCle;
	private DefaultListModel model;

	public VueRechMotCle (Controleur controleur, HashMap<String, MotCle> cle) {
		super(controleur);
		setTitle("Recherche des ouvrages et des pÃ©riodiques en fonction des mots-clÃÂ©");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mots-cles");
		lblNewLabel.setBounds(25, 32, 65, 15);
		getContentPane().add(lblNewLabel);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaOuvrage.setText("");
				textAreaArticle.setText(" ");
				ArrayList<String> mC = new ArrayList<String>();
				HashSet<MotCle> motscles = new HashSet<MotCle>();
				if (!(listMotCle.isSelectionEmpty())){
					mC = (ArrayList<String>)listMotCle.getSelectedValuesList();
				}
				for (int i = 0; i < mC.size(); i++){
					motscles.add(getControleur().getMot(mC.get(i)));
				}
				getControleur().rechercheParMotCle(motscles);
			}
		});
		btnRechercher.setBounds(382, 61, 117, 25);
		getContentPane().add(btnRechercher);
		
		JLabel lblOuvrages = new JLabel("Ouvrages");
		lblOuvrages.setBounds(25, 147, 65, 15);
		getContentPane().add(lblOuvrages);
		
		JLabel lblArticles = new JLabel("Articles");
		lblArticles.setBounds(38, 334, 52, 15);
		getContentPane().add(lblArticles);
		
		scrollPaneOuvrage = new JScrollPane();
		scrollPaneOuvrage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneOuvrage.setToolTipText("");
		scrollPaneOuvrage.setBounds(135, 142, 364, 174);
		getContentPane().add(scrollPaneOuvrage);
		
		textAreaOuvrage = new JTextArea();
		textAreaOuvrage.setEditable(false);
		scrollPaneOuvrage.setColumnHeaderView(textAreaOuvrage);
		
		scrollPaneArticle = new JScrollPane();
		scrollPaneArticle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneArticle.setBounds(135, 329, 363, 135);
		getContentPane().add(scrollPaneArticle);
		
		textAreaArticle = new JTextArea();
		textAreaArticle.setEnabled(false);
		textAreaArticle.setEditable(false);
		scrollPaneArticle.setColumnHeaderView(textAreaArticle);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueRechMotCle.this);}
		});
		btnQuitter.setBounds(247, 477, 117, 23);
		getContentPane().add(btnQuitter);
		
		scrollPaneMotCle = new JScrollPane();
		scrollPaneMotCle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneMotCle.setBounds(135, 28, 212, 101);
		getContentPane().add(scrollPaneMotCle);
		
		model = new DefaultListModel();
		listMotCle = new JList(model);
		scrollPaneMotCle.setViewportView(listMotCle);
				
		alimenteMotCle(cle);
		}
	
	private void alimenteMotCle(HashMap<String, MotCle> MotsCle ) {
		for (MotCle motcle : getControleur().getMotCle()) {
			if (!(motcle.get_ouvrages().isEmpty()))
				model.addElement(motcle.get_motCle());
		}
	}
	
	public void alimente(MotCle motCle) {
		alimenteOuvrage(motCle.get_ouvrages());
		alimenteArticle(motCle.get_articles());
	}
	
	private void alimenteOuvrage(HashSet<Ouvrage> ouv) {
		for (Ouvrage ouvrage : ouv) {
			textAreaOuvrage.append(ouvrage.getIsbn() + " : " + ouvrage.getTitre() +"\n");
			if (ouvrage.getNbExemplaires() != 0)
				alimenteExemplaire(ouvrage.getExemplaires());
		}
	}
	
	private void alimenteExemplaire(Collection<Exemplaire> exem) {
		for (Exemplaire exemplaire : exem) {
			textAreaOuvrage.append( "	numero " + String.valueOf(exemplaire.getNumero()) + " : " + exemplaire.libStatut() + "\n");
		}
	}
	
	private void alimenteArticle(HashSet<Article> art) {
		for (Article article : art) {
			textAreaArticle.append(article.get_par().get_periodique().get_nom() + " n°" + String.valueOf(article.get_par().get_identParution()) + " " + article.get_titre()/*+ " n° page : " + String.valueOf(article.getPageDeb())*/);
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
