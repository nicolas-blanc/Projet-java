/*
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JList;

import java.awt.Scrollbar;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Fenêtre de saisie d'un ouvrage 
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */

/*
public class VueSaisieOuvrage extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIsbn ;
	private JTextField textFieldTitre;
	private JTextField textFieldDate;
	private JButton buttonEnreg;
	private JButton buttonAnnuler;
	private JTextField textFieldEditeur;
	private JComboBox comboBoxAuteur;
	private JList listMotsCles;
	
	/**
	 * Create the frame.
	 */
/*
	public VueSaisieOuvrage(Controleur controleur) {
		super(controleur);
		setTitle("Enregistrement d'un nouvel ouvrage");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 463, 466);
		contentPane = new JPanel();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
			getControleur().fermerVue(VueSaisieOuvrage.this);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(99, 32, 61, 15);
		contentPane.add(lblNewLabel);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(170, 32, 141, 19);
		contentPane.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(99, 66, 61, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(170, 65, 225, 19);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HashSet<MotCle> motscles = new HashSet<MotCle>();
				String isbn = textFieldIsbn.getText();
				if (getControleur().rechOuv(isbn) == null){
				String titre = textFieldTitre.getText();
				String editeur = textFieldEditeur.getText();
				String dateEdition = textFieldDate.getText();
				
				ArrayList<String> mots = new ArrayList<String>();
				if (!(listMotsCles.isSelectionEmpty())){
					mots = (ArrayList<String>)listMotsCles.getSelectedValuesList();
				}			
				for (int i = 0; i < mots.size(); i++){
					//getControleur().getMot(mots.get(i));
					motscles.add(getControleur().getMot(mots.get(i)));
				}
				String aut = (String)comboBoxAuteur.getSelectedItem();
				String prenomauteur;
				String nomauteur;
				StringTokenizer parser = new StringTokenizer(aut);
						prenomauteur = (parser.nextToken());
						nomauteur = (parser.nextToken());

				getControleur().nouvOuvrage(isbn, titre, nomauteur, prenomauteur, editeur, dateEdition,motscles);
				}
			else {
				Message dialog = new Message("Isbn déjà existant");
			}
		}});
		buttonEnreg.setBounds(302, 376, 107, 25);
		contentPane.add(buttonEnreg);
		
		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueSaisieOuvrage.this);}
		});
		buttonAnnuler.setBounds(302, 402, 107, 25);
		contentPane.add(buttonAnnuler);
		
		JLabel lblNewLabel_2 = new JLabel("Date édition (MM/AAAA)");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(12, 200, 154, 33);
		contentPane.add(lblNewLabel_2);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(170, 205, 114, 19);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblEditeur = new JLabel("Editeur");
		lblEditeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditeur.setBounds(79, 180, 61, 15);
		contentPane.add(lblEditeur);
		
		textFieldEditeur = new JTextField();
		textFieldEditeur.setColumns(10);
		textFieldEditeur.setBounds(170, 175, 114, 19);
		contentPane.add(textFieldEditeur);
		
		ButtonGroup bg = new ButtonGroup();
		
		comboBoxAuteur = new JComboBox();
		comboBoxAuteur.setEditable(true);
		comboBoxAuteur.setBounds(170, 111, 255, 19);
		contentPane.add(comboBoxAuteur);
		if (getControleur().getAuteur() == null){
		}
		else {
			for (Auteur auteur : getControleur().getAuteur()) {
			  comboBoxAuteur.addItem(auteur.get_nom()+" "+auteur.get_prenom());
		}
		}
		JLabel lblMotsCl = new JLabel("Mots clés");
		lblMotsCl.setBounds(40, 250, 70, 15);
		contentPane.add(lblMotsCl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(154, 250, 293, 101);
		contentPane.add(scrollPane);
		
		DefaultListModel model = new DefaultListModel();
		listMotsCles = new JList(model);
		scrollPane.setViewportView(listMotsCles);
		
		JLabel lblAuteur = new JLabel("Auteur");
		lblAuteur.setBounds(79, 113, 70, 15);
		contentPane.add(lblAuteur);
		if (getControleur().getMotCle() == null) {
		}
		else {
			for (MotCle motcle : getControleur().getMotCle()) {
				model.addElement(motcle.get_motCle());
		}
		}
		
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale: {
			buttonEnreg.setEnabled(true);
			buttonAnnuler.setEnabled(true);
			break;
			}
		}
	}
	public void update (Observable o , Object arg)
	{
		//reafficher ce qui existait
		
		
	}
}*/

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JList;

import java.awt.Scrollbar;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

/**
 * Fenêtre de saisie d'un ouvrage 
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */


public class VueSaisieOuvrage extends Vue {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIsbn ;
	private JTextField textFieldTitre;
	private JTextField textFieldDate;
	private JButton buttonEnreg;
	private JButton buttonAnnuler;
	private JTextField textFieldEditeur;
	private JComboBox comboBoxAuteur;
	private JList listMotsCles;
	
	/**
	 * Create the frame.
	 */

	public VueSaisieOuvrage(Controleur controleur) {
		super(controleur);
		setTitle("Enregistrement d'un nouvel ouvrage");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 463, 466);
		contentPane = new JPanel();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
			getControleur().fermerVue(VueSaisieOuvrage.this);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(99, 32, 61, 15);
		contentPane.add(lblNewLabel);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(170, 32, 141, 19);
		contentPane.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(99, 66, 61, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setBounds(170, 65, 225, 19);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				HashSet<MotCle> motscles = new HashSet<MotCle>();
				String isbn = textFieldIsbn.getText();
				if (getControleur().rechOuv(isbn) == null){
				String titre = textFieldTitre.getText();
				String editeur = textFieldEditeur.getText();
				String dateEdition = textFieldDate.getText();
				
				ArrayList<String> mots = new ArrayList<String>();
				if (!(listMotsCles.isSelectionEmpty())){
					mots = (ArrayList<String>)listMotsCles.getSelectedValuesList();
				}			
				for (int i = 0; i < mots.size(); i++){
					//getControleur().getMot(mots.get(i));
					motscles.add(getControleur().getMot(mots.get(i)));
				}
				String aut = (String)comboBoxAuteur.getSelectedItem();
				String prenomauteur;
				String nomauteur;
				StringTokenizer parser = new StringTokenizer(aut);
						nomauteur = (parser.nextToken());
						prenomauteur = (parser.nextToken());

				getControleur().nouvOuvrage(isbn, titre, nomauteur, prenomauteur, editeur, dateEdition,motscles);
				}
			else {
				Message dialog = new Message("Isbn déjà existant");
			}
		}});
		buttonEnreg.setBounds(302, 376, 107, 25);
		contentPane.add(buttonEnreg);
		
		buttonAnnuler = new JButton("Annuler");
		buttonAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueSaisieOuvrage.this);}
		});
		buttonAnnuler.setBounds(302, 402, 107, 25);
		contentPane.add(buttonAnnuler);
		
		JLabel lblNewLabel_2 = new JLabel("Date édition (MM/AAAA)");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_2.setBounds(12, 200, 154, 33);
		contentPane.add(lblNewLabel_2);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(170, 205, 114, 19);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		JLabel lblEditeur = new JLabel("Editeur");
		lblEditeur.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEditeur.setBounds(79, 180, 61, 15);
		contentPane.add(lblEditeur);
		
		textFieldEditeur = new JTextField();
		textFieldEditeur.setColumns(10);
		textFieldEditeur.setBounds(170, 175, 114, 19);
		contentPane.add(textFieldEditeur);
		
		ButtonGroup bg = new ButtonGroup();
		
		comboBoxAuteur = new JComboBox();
		comboBoxAuteur.setEditable(true);
		comboBoxAuteur.setBounds(170, 111, 255, 19);
		contentPane.add(comboBoxAuteur);
		alimenteComboAuteur();//Affiche les auteurs déja présents
		
		JLabel lblMotsCl = new JLabel("Mots clés");
		lblMotsCl.setBounds(40, 250, 70, 15);
		contentPane.add(lblMotsCl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(154, 250, 293, 101);
		contentPane.add(scrollPane);
		
		DefaultListModel model = new DefaultListModel();
		listMotsCles = new JList(model);
		scrollPane.setViewportView(listMotsCles);
		
		JLabel lblAuteur = new JLabel("Auteur");
		lblAuteur.setBounds(79, 113, 70, 15);
		contentPane.add(lblAuteur);
		alimenteMotsCles(model);
		
	}
	
	public void setEtat (int etat){
		switch (etat) {
		case initiale: {
			buttonEnreg.setEnabled(true);
			buttonAnnuler.setEnabled(true);
			break;
			}
		}
	}
	public void update (Observable o , Object arg)
	{
		//reafficher ce qui existait
	}
	
	public void alimenteComboAuteur(){
		if (getControleur().getAuteur() == null){
		}
		else {
			for (Auteur auteur : getControleur().getAuteur()) {
			  comboBoxAuteur.addItem(auteur.get_nom()+" "+auteur.get_prenom());
		}
		}
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
}