import java.util.Observable;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VueConsulterPeriodique extends Vue {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldIssn;
	private JTextField textFieldNom;
	private JTextField textFieldNbrPar;
	private JButton btnRechercher;
	private JButton btnQuitter;

	public VueConsulterPeriodique(Controleur controleur) {
		super(controleur);
		setTitle("Consulter un p\u00E9riodique");
		setBounds(100, 100, 450, 300);

		getContentPane().setLayout(null);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(-18, 74, 515, 9);
		getContentPane().add(separator_1);
		
		JLabel lblIssn = new JLabel("Issn ?");
		lblIssn.setBounds(64, 30, 56, 16);
		getContentPane().add(lblIssn);
		
		textFieldIssn = new JTextField();
		textFieldIssn.setBounds(143, 27, 116, 22);
		getContentPane().add(textFieldIssn);
		textFieldIssn.setColumns(10);
		
		btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Periodique per = getControleur().recherchePeriodique(textFieldIssn.getText());
			}
		});
		btnRechercher.setBounds(315, 26, 97, 25);
		getContentPane().add(btnRechercher);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(103, 99, 56, 16);
		getContentPane().add(lblNom);
		
		textFieldNom = new JTextField();
		textFieldNom.setEditable(false);
		textFieldNom.setBounds(239, 96, 116, 22);
		getContentPane().add(textFieldNom);
		textFieldNom.setColumns(10);
		
		JLabel lblNombreDeParutions = new JLabel("Nombre de parutions");
		lblNombreDeParutions.setBounds(33, 134, 126, 16);
		getContentPane().add(lblNombreDeParutions);
		
		textFieldNbrPar = new JTextField();
		textFieldNbrPar.setEditable(false);
		textFieldNbrPar.setColumns(10);
		textFieldNbrPar.setBounds(239, 131, 116, 22);
		getContentPane().add(textFieldNbrPar);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			getControleur().fermerVue(VueConsulterPeriodique.this);}
		});
		btnQuitter.setBounds(162, 187, 97, 25);
		getContentPane().add(btnQuitter);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	void alimentePeriodique(Periodique per) {
		textFieldNom.setText(per.get_nom());
		textFieldNbrPar.setText(String.valueOf(per.getNbParution())); 
	}
}