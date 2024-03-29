import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class PanelAfficherCategorieDetail extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel labelTitre=new JLabel("Affichage des categories");

	static JLabel labelInfo=new JLabel();
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	static DefaultListModel<String> listModelPoids = new DefaultListModel<String>();
	static DefaultListModel<String> listModelGenreCat = new DefaultListModel<String>();
	static JList<String> list = new JList<String>(listModel); 
	JList<String> listPoids = new JList<String>(listModelPoids); 
	static JList<String> listGenreCat = new JList<String>(listModelGenreCat); 
	static DefaultTableModel model = new DefaultTableModel(); //d�finir le tableau
	JTable tableau = new JTable(model);
	JButton boutontest=new JButton("Ok");
	private  static JScrollPane areaScrollPane=new JScrollPane();
	JButton boutonRefresh=new JButton("Refraichir la liste");

	private static Categorie categorie;
	private static boolean genreChange ;
	
	private static CategoriePoids categoriePoids;

	PanelAfficherCategorieDetail(){

		// Ajouter des colonnes au tableau
		model.addColumn("Club");
		model.addColumn("Nom");
		model.addColumn("Prenom");
		model.addColumn("Age");
		model.addColumn("Ann�e de naissance");
		model.addColumn("Genre");
		model.addColumn("Categorie");
		model.addColumn("Poids");


		this.setLayout(new BorderLayout());
		JPanel panelEst=new JPanel();
		JPanel panelCentre=new JPanel();
		JPanel panelHaut=new JPanel();
		JPanel panelBas=new JPanel();

		panelEst.setBackground(Color.gray);
		panelCentre.setBackground(new Color(193,205,205));


		this.add(panelEst,BorderLayout.WEST);
		this.add(panelCentre,BorderLayout.CENTER);
		this.add(panelHaut,BorderLayout.NORTH);
		this.add(panelBas,BorderLayout.SOUTH);

		//panelBas.add(boutonRefresh);


		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(-1);
		list.setLayoutOrientation(JList.VERTICAL); 
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(200,250));
		panelEst.add(listScroller);

		listPoids.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listPoids.setVisibleRowCount(-1);
		listPoids.setLayoutOrientation(JList.VERTICAL); 

		listGenreCat.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		listGenreCat.setVisibleRowCount(-1);
		listGenreCat.setLayoutOrientation(JList.VERTICAL); 
		
		JScrollPane listGenreCatScroller = new JScrollPane(listGenreCat);
		listGenreCatScroller.setPreferredSize(new Dimension(70,250));
		panelEst.add(listGenreCatScroller);

		JScrollPane listPoidsScroller = new JScrollPane(listPoids);
		listPoidsScroller.setPreferredSize(new Dimension(70,250));
		panelEst.add(listPoidsScroller);

		

	

		areaScrollPane = new JScrollPane(tableau);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		areaScrollPane.setPreferredSize(new Dimension(550, 550));


		panelCentre.add(tableau.getTableHeader());
		panelCentre.add(areaScrollPane);

		panelBas.add(labelInfo);

		remplirListClub();

		System.out.println(list.getSelectedValue());



		panelHaut.add(labelTitre);



		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				areaScrollPane.repaint();
				CategoriePoids selectioncategoriePoids=Controleur.retrouveCategoriePoids(listPoids.getSelectedValue());	
				categoriePoids=selectioncategoriePoids;
				Categorie selectioncategorie=Controleur.retrouveCategorie(list.getSelectedValue());
				categorie=selectioncategorie;
				efface();
			}});

		listPoids.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub
				areaScrollPane.repaint();
				/* erreur retrouveCategoriePoids ne compare pas les bonnes valeurs --> regex */
				System.out.println(listPoids.getSelectedValue());
				CategoriePoids selectioncategoriePoids=Controleur.retrouveCategoriePoids(listPoids.getSelectedValue());	
				categoriePoids=selectioncategoriePoids;
				Categorie selectioncategorie=Controleur.retrouveCategorie(list.getSelectedValue());
				categorie=selectioncategorie;
				remplirCombattantPoidsCat();

			}
			
		});



		listGenreCat.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub

				areaScrollPane.repaint();
				/* erreur retrouveCategoriePoids ne compare pas les bonnes valeurs --> regex */
				System.out.println(listGenreCat.getSelectedValue());
				CategoriePoids selectioncategoriePoids=Controleur.retrouveCategoriePoids(listPoids.getSelectedValue());	
				categoriePoids=selectioncategoriePoids;
				Categorie selectioncategorie=Controleur.retrouveCategorie(list.getSelectedValue());
				categorie=selectioncategorie;
				remplirListCategoriePoids();
				remplirTableauxChangementGenre();
			}});
	}


	private void remplirListClub(){
		for(Club cl : Controleur.listClub){
			listModel.addElement(cl.getNom());
		}

	}
	private static void remplirListCategorie(){
		listModel.addElement(" ");
		for(Categorie cat : Controleur.listeCategorie){
			listModel.addElement(cat.getNom());
		}

	}

	private static void remplirListCategorieGenre(){
		listModelGenreCat.addElement("H");
		listModelGenreCat.addElement("F");


	}


	private static void remplirListCategoriePoids(){
		listModelPoids.addElement(" ");
		listModelPoids.removeAllElements();	
		for(CategoriePoids cat : Controleur.listeCategoriePoids){
			if(listGenreCat.getSelectedValue()==null){
			}
			else if((listGenreCat.getSelectedValue().equals(cat.getGenre()) && (list.getSelectedValue().equals(cat.getCategorie().getNom())))){
				listModelPoids.addElement(cat.getPoidsMin()+"-"+ cat.getPoidsMax());
			}
		
		}
	}

	private static int remplirCombattantPoidsCat(){
		listModel.addElement("");
		model.getDataVector().removeAllElements();
		int i=0;
		String genreSelection=listGenreCat.getSelectedValue();
		if(genreSelection==(null)){
			genreSelection="H";
		}	
		if(!(categorie==null)&&!(categoriePoids==null)){
			for(Club cl : Controleur.listClub){
				for(Competiteur comp : cl.getListCompetiteur()){		
					if (comp.getCategorie().getNom().equals(categorie.getNom()) && comp.getGenre().equals(genreSelection) && comp.getCategoriePoids().getNom().equals(categoriePoids.getNom())){
						i=i+1;
						model.addRow(new Object[]{cl.getNom(),comp.getNom(),comp.getPrenom(),comp.getAge(),calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),comp.getPoids()});
					}
				}
			}	
		}
		/* si on selectionne que le poids*/
		if (!(categorie==null)&&(categoriePoids==null) ){
			for(Club cl : Controleur.listClub){
				for(Competiteur comp : cl.getListCompetiteur()){
					if (comp.getCategorie().getNom().equals(categorie.getNom())  && comp.getGenre().equals(genreSelection)){
						model.addRow(new Object[]{cl.getNom(),comp.getNom(),comp.getPrenom(),comp.getAge(),calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),comp.getPoids()});
					}					
				}
			}
			return i;
		}
		/* si on selectionne que la categorie*/
		if(((categorie==null)&&!(categoriePoids==null)) ){
			for(Club cl : Controleur.listClub){
				for(Competiteur comp : cl.getListCompetiteur()){
					if (comp.getCategoriePoids().getNom().equals(categoriePoids.getNom())  && comp.getGenre().equals(genreSelection)){
						model.addRow(new Object[]{cl.getNom(),comp.getNom(),comp.getPrenom(),comp.getAge(),calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),comp.getPoids()});
					}		
				}
			}
			return i;
		}
		/* si on ne selectionne rien */
		if((categorie==null)&&(categoriePoids==null)){
			areaScrollPane.repaint();
			model.addRow(new Object[]{});
		}
		labelInfo.setText("");
		return 0;
	}
	
	private static void remplirTableauxChangementGenre(){
		listModel.addElement("");
		model.getDataVector().removeAllElements();
		/* si on selectionne que la categorie*/		
			int i =0;
			for(Club cl : Controleur.listClub){
				for(Competiteur comp : cl.getListCompetiteur()){

					if ( (comp.getGenre().equals(listGenreCat.getSelectedValue()) &&( comp.getCategorie().getNom().equals(list.getSelectedValue())))){
						model.addRow(new Object[]{cl.getNom(),comp.getNom(),comp.getPrenom(),comp.getAge(),calculAnnee(comp.getAge()),comp.getGenre(),comp.getCategorie().getNom(),comp.getPoids()});
					i++;
					}				
				}		 
			}		
		labelInfo.setText("Nombre de comp�titeurs :"+i);		
	}
	
	
	
	private static int calculAnnee(int age){
		Calendar c = Calendar.getInstance();
		int annee = c.get(Calendar.YEAR);
		return annee-age;
	}
	
	/* efface la  les categories de poids et les combattants dans la liste*/
	public static void efface(){
		listModelPoids.removeAllElements();
		listGenreCat.clearSelection();
		model.getDataVector().removeAllElements();
		model.addRow(new Object[]{"","","","","","","",""});
	}



	public static void refresh(){
		listModel.removeAllElements();
		listModelPoids.removeAllElements();
		listModelGenreCat.removeAllElements();
		remplirListCategorie();		
		remplirListCategoriePoids();
		remplirCombattantPoidsCat();
		remplirListCategorieGenre();
	}



	public Categorie getCategorie() {
		return categorie;
	}



	public void setCategorie(Categorie categorie) {
		PanelAfficherCategorieDetail.categorie = categorie;
	}



	public CategoriePoids getCategoriePoids() {
		return categoriePoids;
	}



	public void setCategoriePoids(CategoriePoids categoriePoids) {
		PanelAfficherCategorieDetail.categoriePoids = categoriePoids;
	}


}
