
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JSeparator;
import java.util.Observable;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
/**
 * Fenêtre de saisie d'un exemplaire d'un ouvrage avec la vue des exemplaires de l'ouvrage
 * Code du JFrame généré par Window Builder/Swing Designer.
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueSaisieExemplaire extends Vue  {
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIsbn;
	private JTextField textFieldTitre;
	private JTextField textFieldDateReception;
	private JTextArea textAreaInfosExemplaires;
	
	// pour que les boutons soient des attributs, il faut faire "convert local to field"
	private JButton buttonRech;
	private JButton buttonEnreg;
	private JButton buttonAnnulerEnreg;
	private JButton buttonFermer;
	
	// à ajouter car la vue est observatrice d'un ouvrage
	private Ouvrage _ouvrage ;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton RadioButtonConsultable;
	private JButton buttonAnnulerRech;
	
	/**
	 * Create the frame.
	 */
	public VueSaisieExemplaire(Controleur controleur) {
		super (controleur);
		setTitle("Enregistrement d'un nouvel exemplaire d'ouvrage");
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
		setBounds(100, 100, 540, 461);
		contentPane = new JPanel();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
			getControleur().fermerVue(VueSaisieExemplaire.this);
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Isbn");
		lblNewLabel.setBounds(145, 31, 43, 17);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date réception (MM/AAAA)");
		lblNewLabel_1.setBounds(12, 115, 168, 15);
		contentPane.add(lblNewLabel_1);
		
		textFieldIsbn = new JTextField();
		textFieldIsbn.setBounds(190, 30, 159, 19);
		contentPane.add(textFieldIsbn);
		textFieldIsbn.setColumns(10);
		
		textFieldDateReception = new JTextField();
		textFieldDateReception.setEditable(false);
		textFieldDateReception.setText("");
		textFieldDateReception.setBounds(189, 114, 114, 19);
		contentPane.add(textFieldDateReception);
		textFieldDateReception.setColumns(10);
		
		buttonRech = new JButton("Rechercher");
		buttonRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String isbn = textFieldIsbn.getText();
				// liaison de la vue avec l'objet observé
				setOuvrage (getControleur().rechOuvrage(isbn));
		}});
		buttonRech.setBounds(357, 12, 107, 25);
		contentPane.add(buttonRech);
		
		buttonAnnulerEnreg = new JButton("Annuler");
		buttonAnnulerEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieExemplaire.this);}
		});
		buttonAnnulerEnreg.setBounds(357, 167, 107, 25);
		contentPane.add(buttonAnnulerEnreg);
		
		buttonEnreg = new JButton("Enregistrer");
		buttonEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateReception = textFieldDateReception.getText();
				String statut;
				if (RadioButtonConsultable.isSelected()) {
					statut = "consultable";}
				else {
					statut = "empruntable";}
				getControleur().nouvExemplaire(getOuvrage(), dateReception, statut);
				}
		});
		buttonEnreg.setBounds(357, 130, 107, 25);
		contentPane.add(buttonEnreg);
		
		JLabel lblNewLabel_2 = new JLabel("Titre ouvrage");
		lblNewLabel_2.setBounds(91, 85, 97, 15);
		contentPane.add(lblNewLabel_2);
		
		textFieldTitre = new JTextField();
		textFieldTitre.setEditable(false);
		textFieldTitre.setBounds(189, 83, 247, 19);
		contentPane.add(textFieldTitre);
		textFieldTitre.setColumns(10);
		
		JLabel lblExemplaires = new JLabel("Exemplaires");
		lblExemplaires.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
		lblExemplaires.setBounds(206, 237, 97, 15);
		contentPane.add(lblExemplaires);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(74, 223, 300, 2);
		contentPane.add(separator);
		
		buttonFermer = new JButton("Fermer");
		buttonFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueSaisieExemplaire.this);
			}
		});
		buttonFermer.setBounds(196, 401, 107, 25);
		contentPane.add(buttonFermer);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 276, 399, 96);
		contentPane.add(scrollPane);
		
		textAreaInfosExemplaires = new JTextArea();
		textAreaInfosExemplaires.setEditable(false);
		scrollPane.setColumnHeaderView(textAreaInfosExemplaires);
		
		JLabel lblNewLabel_4 = new JLabel("Statut");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(117, 172, 61, 15);
		contentPane.add(lblNewLabel_4);
		
		RadioButtonConsultable = new JRadioButton("Consultable");
		buttonGroup.add(RadioButtonConsultable);
		RadioButtonConsultable.setBounds(190, 164, 134, 23);
		contentPane.add(RadioButtonConsultable);
		
		JRadioButton RadioButtonEmpruntable = new JRadioButton("Empruntable");
		buttonGroup.add(RadioButtonEmpruntable);
		RadioButtonEmpruntable.setSelected(true);
		RadioButtonEmpruntable.setBounds(190, 191, 134, 23);
		contentPane.add(RadioButtonEmpruntable);
		
		buttonAnnulerRech = new JButton("Annuler");
		buttonAnnulerRech.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueSaisieExemplaire.this);}
		});
		buttonAnnulerRech.setBounds(357, 46, 107, 25);
		contentPane.add(buttonAnnulerRech);
	}
	
		private Ouvrage getOuvrage() {
			return _ouvrage;
		}
		private void setOuvrage(Ouvrage ouvrage) {
			 _ouvrage = ouvrage;
		}
		
		/**
		 * alimentation des composants d'affichage en consultant directement l'ouvrage
		 */
		public void alimente(Ouvrage ouv) {
			textFieldTitre.setText(ouv.getTitre());
			textAreaInfosExemplaires.setText("");
			for (Exemplaire exemplaire : ouv.getExemplaires()) {
				  textAreaInfosExemplaires.append( "numéro " + String.valueOf(exemplaire.getNumero()) 
				 + " : " + exemplaire.libStatut() + "\n");
			}
		}
		/**
		 * définition des états de la fenêtre
		 */
		public void setEtat (int etat){
			switch (etat) {
			case initiale: {
				buttonRech.setEnabled(true);
				buttonEnreg.setEnabled(false);
				buttonAnnulerEnreg.setEnabled(false);
				buttonFermer.setEnabled(false);
				break;
				}
			case inter1: {
				buttonRech.setEnabled(false);
				buttonAnnulerRech.setEnabled(false);
				buttonEnreg.setEnabled(true);
				buttonAnnulerEnreg.setEnabled(true);
				buttonFermer.setEnabled(false);
				textFieldIsbn.setEditable(false);
				textFieldDateReception.setEditable(true);
				break;
				}
			case finale: {
				buttonRech.setEnabled(false);
				buttonEnreg.setEnabled(false);
				buttonAnnulerEnreg.setEnabled(false);
				buttonFermer.setEnabled(true);
				break;
				}
			}
		}
		public void update (Observable o , Object arg)
		{
			//reafficher ce qui existait
			this.alimente(this.getOuvrage());
			// liaison de la vue avec l'objet observé
			super.update();			
		}
		
}
