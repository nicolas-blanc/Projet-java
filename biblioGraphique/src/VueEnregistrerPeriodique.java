import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.JComboBox;


public class VueEnregistrerPeriodique extends Vue {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField textFieldissn;
	private JTextField textFieldnom;


	/**
	 * Create the frame.
	 */
	public VueEnregistrerPeriodique(Controleur controleur) {
		super(controleur);	
		setBounds(100, 100, 450, 150);

		setTitle("Enregistrement d'un nouveau periodique");

		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
			getControleur().fermerVue(VueEnregistrerPeriodique.this);
			}
		});

		getContentPane().setLayout(null);
		
		textFieldissn = new JTextField();
		textFieldissn.setBounds(133, 12, 114, 19);
		getContentPane().add(textFieldissn);
		textFieldissn.setColumns(10);
		
		JLabel lblIssn = new JLabel("issn");
		lblIssn.setBounds(31, 14, 70, 15);
		getContentPane().add(lblIssn);
		
		JLabel lblNom = new JLabel("nom");
		lblNom.setBounds(31, 52, 70, 15);
		getContentPane().add(lblNom);
		
		textFieldnom = new JTextField();
		textFieldnom.setBounds(133, 50, 114, 19);
		getContentPane().add(textFieldnom);
		textFieldnom.setColumns(10);
		
		
		JButton butEnregistrer = new JButton("Enregistrer");
		
		butEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issn = textFieldissn.getText();
				String nom = textFieldnom.getText();
				getControleur().nouvPeriodique(issn, nom);
				}
		});
		
		butEnregistrer.setBounds(252, 97, 117, 25);
		getContentPane().add(butEnregistrer);
		
		JButton butQuitter = new JButton("Quitter");
		butQuitter.setBounds(98, 97, 117, 25);
		getContentPane().add(butQuitter);
		
		butQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueEnregistrerPeriodique.this);
				}
		});
		
	}
	
	public void update (Observable o , Object arg)
	{		
		
	}
}
