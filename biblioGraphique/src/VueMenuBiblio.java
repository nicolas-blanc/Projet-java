import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.SwingConstants;
/**
 * Fenêtre principale de l'application Bibliothèque avec le menu 
 * @author IUT,  A.Culet 
 * @version 1.0
 */
public class VueMenuBiblio  extends Vue{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	// ************************************************************************************************************
	// Constructeur
	// ************************************************************************************************************
	public VueMenuBiblio(Controleur controleur) {
		super (controleur);
		setTitle("Gestion de bibliothèque");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		this.addWindowListener (new WindowAdapter() {
			public void windowClosing (WindowEvent e) {
			getControleur().fermerVue(VueMenuBiblio.this);
			}
		});
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(35, 29, 340, 21);
		getContentPane().add(menuBar);
		
		JMenu mnApplication = new JMenu("Application");
		mnApplication.setHorizontalAlignment(SwingConstants.LEFT);
		menuBar.add(mnApplication);
		
		JMenuItem menuItemQuitter = new JMenuItem("Quitter");
		menuItemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getControleur().fermerVue(VueMenuBiblio.this);
				}		
		});
		mnApplication.add(menuItemQuitter);
		
		JMenu mnOuvrage = new JMenu("Ouvrage");
		menuBar.add(mnOuvrage);
		
		JMenuItem MenuItemOuv = new JMenuItem("Nouvel ouvrage");
		
		MenuItemOuv.addActionListener(new ActionListener()
		 {
			@Override
			public void actionPerformed(ActionEvent event) {
				//  affichage de la fenetre de saisie d'un ouvrage
				getControleur(). saisirOuvrage() ;}
		});//check nico
		
		mnOuvrage.add(MenuItemOuv);

		JMenuItem MenuItemExemp = new JMenuItem("Nouvel exemplaire");
		MenuItemExemp.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				//  affichage de la fenetre de saisie d'un exemplaire
				getControleur().saisirExemplaire();}
		});
		
		mnOuvrage.add(MenuItemExemp);
		
		
		//********
		
		JMenuItem MenuItemConsOuv = new JMenuItem("Consulter Ouvrage");
		MenuItemConsOuv.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				//  affichage de la fenetre de saisie de consultation d'un ouvrage
				getControleur(). consulterOuvrage() ;}
		});
		mnOuvrage.add(MenuItemConsOuv);
		
		JMenu mnRecherche = new JMenu("Recherche");
		menuBar.add(mnRecherche);
		mnRecherche.setHorizontalAlignment(SwingConstants.LEFT);
		
		JMenuItem menuItemRechMotCle = new JMenuItem("Recherche par mot cle");
		menuItemRechMotCle.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				//  affichage de la fenetre de saisie de consultation d'un ouvrage
				getControleur().rechercheMotCle(); ;}
		});
		mnRecherche.add(menuItemRechMotCle);		//***********added from nico

		
		JMenuItem menuItemRechercherAuteur = new JMenuItem("Rechercher par Auteur");
		mnRecherche.add(menuItemRechercherAuteur);
		menuItemRechercherAuteur.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				getControleur().rechercherAuteur(); ;}
		});
		mnRecherche.add(menuItemRechercherAuteur);
		
		JMenu mnPeriodique = new JMenu("Periodique");
		menuBar.add(mnPeriodique);
		mnPeriodique.setHorizontalAlignment(SwingConstants.LEFT);
		
		JMenuItem MenuItemEnregistrerPeriodique = new JMenuItem("Enregistrer Periodique");
		MenuItemEnregistrerPeriodique.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				getControleur().enregPeriodique(); }
		});
		
		
		mnPeriodique.add(MenuItemEnregistrerPeriodique);

		JMenuItem MenuItemConsulterPeriodique = new JMenuItem("Consulter Periodique");
		MenuItemConsulterPeriodique.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				getControleur().consulterPeriodique(); }
		});
		mnPeriodique.add(MenuItemConsulterPeriodique);
	
		JMenuItem mntmNouvelleParution = new JMenuItem("Nouvelle parution");
		mntmNouvelleParution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					getControleur().nouvelleParution(); }
		});
		mnPeriodique.add(mntmNouvelleParution);
	}// Fin Constructeur
	
	/**
	 * méthode exécutée lorsque la croix de fermeture de la fenêtre a été cliquée
	 */
	public void windowClosing (WindowEvent e) {
		getControleur().fermerVue(VueMenuBiblio.this);
	}
	public void update (Observable o , Object arg)
	{
		//reafficher ce qui existait
		
		
	}

}
