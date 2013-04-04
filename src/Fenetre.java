

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;


public class Fenetre extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuAccueil,menuItem,club,competiteur,categorie,rechercheCompetiteur,genererList,sauvegarde,chargement,poids;
	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	JMenu accueil,gestion,afficher,generer,gestionSauvegarde;
	JPanel currentPanel;

	public Fenetre(){
		this.setTitle("Gestionnaire de  comp�titions");
		this.setSize(1100, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		this.setVisible(true);	 
		menuBar = new JMenuBar();
		
		accueil = new JMenu("Accueil ");
		menuBar.add(accueil);
		menuAccueil = new JMenuItem("Accueil");
		accueil.add(menuAccueil);
		
		gestion = new JMenu("Gestion ");
		menuBar.add(gestion);
		generer = new JMenu("G�nerer ");
		menuBar.add(generer);
		gestionSauvegarde = new JMenu("Sauvegarde ");
		menuBar.add(gestionSauvegarde);
		club = new JMenuItem("Clubs");
		competiteur = new JMenuItem("Comp�titeurs");
		categorie = new JMenuItem("Cat�gories");
		poids = new JMenuItem("Cat�gorie de Poids");
		genererList = new JMenuItem("generer la liste des combats / clubs");
		gestion.add(club);
		gestion.add(competiteur);
		gestion.add(categorie); 
		gestion.add(poids);
		generer.add(genererList);		
		afficher = new JMenu("Afficher");
		menuBar.add(afficher);
		rechercheCompetiteur = new JMenuItem("Afficher tous les comp�titeurs par cat�gories");
		afficher.add(rechercheCompetiteur);	     
		sauvegarde = new JMenuItem("Sauvegarder");
		gestionSauvegarde.add(sauvegarde);
		this.setJMenuBar(menuBar);
		final PanelAjoutClub panelAjoutClub = new PanelAjoutClub();
		final PanelAccueil panelAccueil = new PanelAccueil();
		final PanelAjoutCatPoids panelPoids = new PanelAjoutCatPoids();
		final PanelAjoutCategorie panelAjoutCategorie = new PanelAjoutCategorie();
		final PanelAjoutCompetiteur panelAjoutCompetiteur = new PanelAjoutCompetiteur();
		final PanelGenererList  panelGenererList= new PanelGenererList();
		final PanelAfficherCategorieDetail panelAfficherCompetiteur = new PanelAfficherCategorieDetail();

		setPanel(panelAccueil);
		currentPanel=panelAccueil;
		
		menuAccueil.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setPanel(panelAccueil);
				currentPanel=panelAccueil;
			}
		});
		
		
		club.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setPanel(panelAjoutClub);
				currentPanel=panelAjoutClub;
			}
		});
		poids.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setPanel(panelPoids);
				currentPanel=panelPoids;
			}
		});

		competiteur.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setPanel(panelAjoutCompetiteur);
				panelAjoutCompetiteur.refreshComboClub();
				currentPanel=panelAjoutCompetiteur;
			}
		});

		categorie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setPanel(panelAjoutCategorie);
				currentPanel=panelAjoutCategorie;
			}
		});
		
		genererList.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				setPanel(panelGenererList);
				currentPanel=panelGenererList;
			}
		});
		
		sauvegarde.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				Sauvegarde nvlSauvegarde=new Sauvegarde();
				nvlSauvegarde.sauvegarder();
				System.out.println(nvlSauvegarde.listeCategorie);
				Serialization.serialise(nvlSauvegarde);
			}
		});

		rechercheCompetiteur.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				
				PanelAfficherCategorieDetail.refresh();
				
			    if(PanelAjoutCategorie.ageManquant.size()==0  && PanelAjoutCatPoids.poidsManquantMasculin.size()==0) {
				setPanel(panelAfficherCompetiteur);
				currentPanel=panelAfficherCompetiteur;
			    }
			
			    else{
			    	JOptionPane.showMessageDialog(panelAfficherCompetiteur,"Avant de pouvoir afficher les categories veuillez remplir els categories manquante : ","Titre : exception",JOptionPane.ERROR_MESSAGE);
			    }
			}
		});
		
		this.setVisible(true);
		

	}
	
	private void setPanel(JPanel panel){
		if(currentPanel!=null){
			this.remove(currentPanel);
		
		}
		this.add(panel);
		this.revalidate();
		this.repaint();
		
	}

}
