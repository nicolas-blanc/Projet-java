package biblioGraphique;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Observable;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class VueRechMotCle extends Vue {

	private static final long serialVersionUID = 1L;
	private JButton btnRechercher;
	private JButton btnQuitter;
	private JComboBox<String> comboBoxMotCle;
	private JScrollPane scrollPaneOuvrage;
	private JScrollPane scrollPaneArticle;
	private JTextArea textAreaOuvrage;
	private JTextArea textAreaArticle;

	public VueRechMotCle (Controleur controleur, HashMap<String, MotCle> cle) {
		super(controleur);
		setTitle("Recherche des ouvrages et des périodiques en fonction des mots-clÃ©");
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mots-clé");
		lblNewLabel.setBounds(25, 32, 51, 15);
		getContentPane().add(lblNewLabel);
		
		comboBoxMotCle = new JComboBox<String>();
		comboBoxMotCle.setBounds(135, 27, 169, 24);
		getContentPane().add(comboBoxMotCle);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				textAreaOuvrage.setText("");
				String mC = (String) comboBoxMotCle.getSelectedItem();
				getControleur().rechercheParMotCle(mC);
			}
		});
		btnRechercher.setBounds(381, 27, 117, 25);
		getContentPane().add(btnRechercher);
		
		JLabel lblOuvrages = new JLabel("Ouvrages");
		lblOuvrages.setBounds(25, 122, 65, 15);
		getContentPane().add(lblOuvrages);
		
		JLabel lblArticles = new JLabel("Articles");
		lblArticles.setBounds(25, 306, 42, 15);
		getContentPane().add(lblArticles);
		
		scrollPaneOuvrage = new JScrollPane();
		scrollPaneOuvrage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneOuvrage.setToolTipText("");
		scrollPaneOuvrage.setBounds(134, 116, 364, 174);
		getContentPane().add(scrollPaneOuvrage);
		
		textAreaOuvrage = new JTextArea();
		textAreaOuvrage.setEditable(false);
		scrollPaneOuvrage.setColumnHeaderView(textAreaOuvrage);
		
		scrollPaneArticle = new JScrollPane();
		scrollPaneArticle.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneArticle.setBounds(135, 301, 363, 107);
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
		btnQuitter.setBounds(220, 417, 117, 23);
		getContentPane().add(btnQuitter);
				
		alimenteMotCle(cle);
		}
	
	private void alimenteMotCle(HashMap<String, MotCle> MotsCle ) {
		for (MotCle motcle : getControleur().getMotCle()) {
			if (!(motcle.get_ouvrages().isEmpty()))
				comboBoxMotCle.addItem(motcle.get_motCle());
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
			textAreaOuvrage.append("	" + String.valueOf(exemplaire.getNumero()) + " " + String.valueOf(exemplaire.getStatut()) + "\n");
		}
	}
	
	private void alimenteArticle(HashSet<Article> art) {
		for (Article article : art) {
			textAreaArticle.append(article.getParution().get_periodique().get_nom() + " n�" + String.valueOf(article.getParution().get_identParution()) + " " + article.getTitre() + " n� page : " + String.valueOf(article.getPageDeb()));
		}
	}
	
	private void rechercheParMotCle(MotCle mC) {
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
