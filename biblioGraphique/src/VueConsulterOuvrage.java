import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// all imported from ant

public class VueConsulterOuvrage extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField Isbn;
	private JTextField Titre;
	private JTextField NomAuteur;
	private JTextField PrenomAuteur;
	private JTextField Editeur;
	private JButton btnRechercher;
	private JButton btnQuitter; 
	private JScrollPane MotCle;
	private JScrollPane Exemplaires;
	private JTextArea InfosEx;
	private JTextArea Mot;
	/**
	 * Create the frame.
	 */
	public VueConsulterOuvrage(Controleur controleur) {
		super(controleur);
		setAlwaysOnTop(true);
		setTitle("Consultation des ouvrages");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIsbn = new JLabel("Isbn ?");
		lblIsbn.setBounds(60, 15, 70, 15);
		contentPane.add(lblIsbn);
		Isbn = new JTextField();
		Isbn.setBounds(187, 10, 114, 19);
		contentPane.add(Isbn);
		Isbn.setColumns(10);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ouvrage ouv;
			ouv = getControleur().rechercherOuvrage(Isbn.getText());}
		});
		btnRechercher.setBounds(305, 33, 117, 25);
		contentPane.add(btnRechercher);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 70, 446, 9);
		contentPane.add(separator);
		
		JLabel lblTitre = new JLabel("Titre :");
		lblTitre.setBounds(15, 90, 70, 19);
		contentPane.add(lblTitre);
		
		Titre = new JTextField();
		Titre.setEditable(false);
		Titre.setBounds(60, 90, 110, 18);
		contentPane.add(Titre);
		Titre.setColumns(10);
		
		JLabel lblNomAuteur = new JLabel("Nom Auteur :");
		lblNomAuteur.setBounds(15, 130, 100, 15);
		contentPane.add(lblNomAuteur);
		
		NomAuteur = new JTextField();
		NomAuteur.setEditable(false);
		NomAuteur.setBounds(110, 130, 100, 18);
		contentPane.add(NomAuteur);
		NomAuteur.setColumns(10);
		
		JLabel lblPrnomAuteur = new JLabel("Prénom Auteur :");
		lblPrnomAuteur.setBounds(226, 128, 127, 15);
		contentPane.add(lblPrnomAuteur);
		
		PrenomAuteur = new JTextField();
		PrenomAuteur.setEditable(false);
		PrenomAuteur.setBounds(344, 126, 90, 19);
		contentPane.add(PrenomAuteur);
		PrenomAuteur.setColumns(10);
		
		JLabel lblDateDdition = new JLabel("Editeur :");
		lblDateDdition.setBounds(187, 90, 120, 18);
		contentPane.add(lblDateDdition);
		
		Editeur = new JTextField();
		Editeur.setEditable(false);
		Editeur.setBounds(305, 91, 114, 19);
		contentPane.add(Editeur);
		Editeur.setColumns(10);
		
		JLabel lblExemplaires = new JLabel("Exemplaire(s):");
		lblExemplaires.setBounds(10, 170, 100, 15);
		contentPane.add(lblExemplaires);
		
		JLabel lblMotsCls = new JLabel("Mot(s) Clés:");
		lblMotsCls.setBounds(242, 170, 90, 15);
		contentPane.add(lblMotsCls);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueConsulterOuvrage.this);}
		});
		btnQuitter.setBounds(160, 245, 117, 25);
		contentPane.add(btnQuitter);
		
		Exemplaires = new JScrollPane();
		Exemplaires.setBounds(110, 179, 100, 67);
		contentPane.add(Exemplaires);
		
		InfosEx = new JTextArea();
		Exemplaires.setColumnHeaderView(InfosEx);
		
		MotCle = new JScrollPane();
		MotCle.setBounds(322, 179, 100, 67);
		contentPane.add(MotCle);
		
		Mot = new JTextArea();
		MotCle.setColumnHeaderView(Mot);
		
	}
	
	public void update(Observable o , Object arg)
	{
		
	}
	
	public void alimente(Ouvrage ouv) {
		Titre.setText(ouv.getTitre());
		Editeur.setText(ouv.getEditeur());
		Auteur aut = ouv.getAuteur();
		NomAuteur.setText(aut.get_nom());
		PrenomAuteur.setText(aut.get_prenom());
		
		InfosEx.setText(null);
		for (Exemplaire exemplaire : ouv.getExemplaires()) {
			InfosEx.append( "numéro " + String.valueOf(exemplaire.getNumero()) 
					 + " : " + exemplaire.libStatut() + "\n");
		}
		Mot.setText(null);
		for (MotCle motcle : ouv.getMotsCles()) {
			Mot.append(motcle.get_motCle()+ "\n");
		}
	}
}