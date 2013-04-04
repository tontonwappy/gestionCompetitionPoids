import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;





public class PanelAjoutCatPoids extends JPanel {

	private static final long serialVersionUID = 1L;
	JButton boutonReset= new JButton("Reset Poids");
	JButton boutonEnvoie= new JButton("OK");
	JButton boutonSupprimer= new JButton("Supprimer");
	JLabel titre = new JLabel("Gestion des categories de Poids");
	JLabel labelAjoutCategorie = new JLabel("Nom de la categorie à ajouter");
	JLabel labelPoidsMini = new JLabel("Poids minimum de la categorie");
	JLabel labelPoidsMaxi = new JLabel("Poids maximum de la categorie");
	JLabel labelGenre = new JLabel("Genre de la categorie");
	JLabel labelCategorie = new JLabel("Categorie");
	static DefaultListModel<String> listModel = new DefaultListModel<String>();
	JList<String> list = new JList<String>(listModel); 
	final JTextField nomCategorie = new JTextField();
	final JTextField poidsMiniField = new JTextField();
	final JTextField poidsMaxiField = new JTextField();
	static DefaultTableModel model = new DefaultTableModel(); //définir le tableau
	JTable tableau = new JTable(model);
	int ligneSelectionTableau;
	JLabel labelAlerteMasculin = new JLabel("");
	JLabel labelAlerteFeminin = new JLabel("");
	public static 	ArrayList<Integer> poidsManquantMasculin=new ArrayList<>();
	public static 	ArrayList<Integer> poidsManquantFeminin=new ArrayList<>();

	private static JComboBox<String> comboGenre = new JComboBox<String>(); //définir fille ou garçon
	private static JComboBox<String> comboCategorie = new JComboBox<String>(); //définir fille ou garçon

	HashMap <String,Integer>  parcourPoidsManquantMasculin= new HashMap <String,Integer> () ;
	HashMap <String,Integer>  parcourPoidsManquantFeminin=new 	HashMap <String,Integer> ();

	/* 
	 * 
	 * Gestion tableaux
	 * */

	
	public final static  JPanel contenuBas = new JPanel();
	PanelAjoutCatPoids(){

		/** Initialisation des panels **/   
		this.setLayout(new BorderLayout());
		JPanel contenuCentre = new JPanel();
		JPanel contenuDroite = new JPanel();
		final JPanel contenuHaut = new JPanel();



		contenuDroite.setBackground(Color.gray);
		contenuCentre.setBackground(new Color(193,205,205));

		this.add(contenuHaut,BorderLayout.NORTH);
		this.add(contenuCentre);	
		this.add(contenuDroite,BorderLayout.EAST);	
		this.add(contenuBas,BorderLayout.SOUTH);	
		final JPanel contenuTableau = new JPanel();
		contenuHaut.add(titre);
		contenuDroite.add(contenuTableau);
		contenuTableau .setLayout(new BorderLayout());
		contenuTableau.add(tableau.getTableHeader(),BorderLayout.NORTH);
		contenuTableau.add(tableau,BorderLayout.CENTER);
		//model.addColumn("Nom Categorie");
		model.addColumn("Poids Min");
		model.addColumn("Poids Max");
		model.addColumn("Genre");

		/****/
		//Ajout du genre
		comboGenre.addItem("H");
		comboGenre.addItem("F");

		tableau.getColumnModel().getColumn(0).setPreferredWidth(180);
		/** Placement des composants : titre et bouton **/

		//refresh les categories


		contenuCentre.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();


		//contenuCentre.add(labelAjoutCategorie);		
		//nomCategorie.setColumns(20);
		poidsMaxiField.setColumns(2);
		poidsMiniField.setColumns(2);
		
		//gbc.gridheight = 1.5;
		//gbc.weighty = 10;
		//gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.insets.top = 15;
		gbc.gridx = 0;
		gbc.gridy = 0;
		contenuCentre.add(labelGenre,gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		contenuCentre.add(comboGenre,gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		contenuCentre.add(labelCategorie,gbc);
		gbc.gridx = 3;
		gbc.gridy = 0;
		contenuCentre.add(comboCategorie,gbc);
		
		
		
		gbc.gridx = 0;
		gbc.gridy = 2;	
		contenuCentre.add(labelPoidsMini,gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		contenuCentre.add(poidsMiniField,gbc);
		gbc.gridx = 2;
		gbc.gridy = 2;
		contenuCentre.add(labelPoidsMaxi,gbc);
		gbc.gridx = 3;
		gbc.gridy = 2;
		contenuCentre.add(poidsMaxiField,gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		contenuCentre.add(boutonEnvoie,gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		contenuCentre.add(boutonSupprimer,gbc);
		gbc.gridx = 2;
		gbc.gridy = 4;
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.gridheight = 1;
//		gbc.gridwidth =1;
//		contenuCentre.add(labelPoidsMini,gbc);
//		gbc.gridx = 1;
//		gbc.gridy = 0;
//		contenuCentre.add(poidsMiniField,gbc);
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		contenuCentre.add(labelPoidsMaxi,gbc);
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//		contenuCentre.add(poidsMaxiField,gbc);
//		gbc.gridx = 0;
//		gbc.gridy = 2;
//		contenuCentre.add(labelGenre,gbc);
//		gbc.gridx = 1;
//		gbc.gridy = 2;
//		contenuCentre.add(comboGenre,gbc);
//		gbc.gridx = 0;
//		gbc.gridy = 3;
//		contenuCentre.add(labelCategorie,gbc);
//		gbc.gridx = 1;
//		gbc.gridy = 3;
//		contenuCentre.add(comboCategorie,gbc);
//		gbc.gridx = 0;
//		gbc.gridy = 4;
//		contenuCentre.add(boutonEnvoie,gbc);
//		gbc.gridx = 1;
//		gbc.gridy = 4;
//		contenuCentre.add(boutonSupprimer,gbc);
//		gbc.gridx = 2;
//		gbc.gridy = 4;

		contenuCentre.add(boutonReset,gbc);


		tableau.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				ligneSelectionTableau = tableau.getSelectedRow();
			

			}
		});


		comboCategorie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				//categorie=comboCategorie.getSelectedItem().toString();
				refreshTableau();
			}});

		comboGenre.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				//genre=comboGenre.getSelectedItem().toString();
				refreshTableau();
			}});



		boutonEnvoie.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{


				/** erreur, ne mets pas a jour le competiteur**/
				if(poidsManquantMasculin.size()>0){	

					for(int i=Integer.parseInt(poidsMiniField.getText());i<=Integer.parseInt(poidsMaxiField.getText());i++){
						int x=0;
						int indexAge=0;
						boolean ageTrouve=false;
						for(int age:poidsManquantMasculin){
							if(age==i && comboGenre.getSelectedItem().toString().equals("H")){
								ageTrouve=true;
								indexAge=x;
							}
							x=x+1;
						}

						if(ageTrouve)
							poidsManquantMasculin.remove(indexAge);

					}
				}

				/** erreur, ne mets pas a jour le competiteur**/
				if(poidsManquantFeminin.size()>0){	
					for(int i=Integer.parseInt(poidsMiniField.getText());i<=Integer.parseInt(poidsMaxiField.getText());i++){
						int x=0;
						int indexAge=0;
						boolean ageTrouve=false;
						for(int age:poidsManquantFeminin){
							if(age==i  && comboGenre.getSelectedItem().toString().equals("F") ){
								ageTrouve=true;
								indexAge=x;
							}
							x=x+1;
						}

						if(ageTrouve)
							poidsManquantFeminin.remove(indexAge);

					}
				}

				String msgalerte = "Veuillez recreer les categories Masculins pour les ages suivant :";
				for(int age :poidsManquantMasculin){
					msgalerte=msgalerte+Integer.toString( age)+" ;"; 
				}

				if(poidsManquantMasculin.size()>0)
					labelAlerteMasculin.setText(msgalerte);
				else{
					labelAlerteMasculin.setText("");
				}




				String msgalerteFeminin = "Veuillez recreer les categories Feminins pour les ages suivant :";
				System.out.println(poidsManquantFeminin);
				for(int age :poidsManquantFeminin){
					msgalerteFeminin=msgalerteFeminin+Integer.toString( age)+" ;"; 
				}

				if(poidsManquantFeminin.size()>0)
					labelAlerteFeminin.setText(msgalerteFeminin);
				else{
					labelAlerteFeminin.setText("");
				}


				if(!poidsMiniField.getText().equals("") && !poidsMaxiField.getText().equals("")){
					if(Integer.parseInt(poidsMiniField.getText())>Integer.parseInt(poidsMaxiField.getText()) ){
						JOptionPane.showMessageDialog(getParent(),
								"les categories sont incorrects");		
						return;
					}


					if(Controleur.VerifCreationCategoriePoids(Integer.parseInt(poidsMiniField.getText()), Integer.parseInt(poidsMaxiField.getText()),comboGenre.getSelectedItem().toString(),Controleur.retrouveCategorie(comboCategorie.getSelectedItem().toString()) )){
						System.out.println(Controleur.retrouveCategorie(comboCategorie.getSelectedItem().toString()));
						CategoriePoids nouvelleCategorie=new CategoriePoids( Integer.parseInt(poidsMiniField.getText()),Integer.parseInt(poidsMaxiField.getText()),poidsMiniField.getText()+poidsMaxiField.getText(),comboGenre.getSelectedItem().toString(),Controleur.retrouveCategorie(comboCategorie.getSelectedItem().toString()));
						Controleur.listeCategoriePoids.add(nouvelleCategorie);					
						model.addRow(new Object[]{Integer.parseInt(poidsMiniField.getText()),Integer.parseInt(poidsMaxiField.getText()),comboGenre.getSelectedItem().toString()});					
						poidsMaxiField.setText("");
						poidsMiniField.setText("");
						for(Club club : Controleur.listClub){
							for(Competiteur comp :club.getListCompetiteur()){

								System.out.println(comp.getCategorie());
								if(comp.getCategoriePoids()==null ){
									System.out.println(Controleur.verifPoidsDansCategorie(comp.getPoids(),nouvelleCategorie));
									/*26032013*/
									for(CategoriePoids cat :Controleur.listeCategoriePoids){
										if (Controleur.verifPoidsDansCategorie(comp.getPoids(),cat)){
											if(nouvelleCategorie.getGenre().equals(comp.getGenre())){
												comp.setCategoriePoids(cat);
												System.out.println(comp);
											}
										}
									}


								}
							}
						}
					}
					else{
						JOptionPane.showMessageDialog(getParent(),
								"La categorie est incorrecte");		
						return;
					}
				}
				else {
					JOptionPane.showMessageDialog(getParent(),
							"les champs ne sont pas tous remplis");	
				}

			}

		});

		/** Met l'attribut de la categorie de chaque compétiteurs à null quand on supprime une catégorie**/
		boutonSupprimer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				System.out.println(Integer.parseInt(tableau.getValueAt(tableau.getSelectedRow(), 0).toString()));
				System.out.println(Integer.parseInt(tableau.getValueAt(tableau.getSelectedRow(), 1).toString()));
				System.out.println(tableau.getValueAt(tableau.getSelectedRow(), 2).toString());
				
				CategoriePoids categoriePoidsSupprim=Controleur.retrouveCategoriePoids(Integer.parseInt(tableau.getValueAt(tableau.getSelectedRow(), 0).toString()), Integer.parseInt(tableau.getValueAt(tableau.getSelectedRow(), 1).toString()),
						tableau.getValueAt(tableau.getSelectedRow(), 2).toString());
				
				for(Club cl :Controleur.listClub){
					for(Competiteur comp : cl.getListCompetiteur()){					
						if(comp.getCategoriePoids()!=null){
							if(comp.getNom().equals("tata")){
								System.out.println("stop");
							}
							if ((comp.getPoids()>=  Integer.parseInt(tableau.getValueAt(tableau.getSelectedRow(), 0).toString())) && 
									(comp.getPoids()<=Integer.parseInt(tableau.getValueAt(tableau.getSelectedRow(), 1).toString()))
									&& comp.getGenre().equals(tableau.getValueAt(tableau.getSelectedRow(), 2).toString()) && categoriePoidsSupprim.equals(comp.getCategoriePoids())){

								comp.setCategoriePoids(null);
							}
						}
					}
				}
				
			

				if(categoriePoidsSupprim!=null){
					Controleur.listeCategoriePoids.remove(categoriePoidsSupprim);
				}

				for (Club cl:Controleur.listClub)
				{
					for(Competiteur comp : cl.getListCompetiteur()){
						if(!(comp==null)){
							if(comp.getCategoriePoids()==null){
								System.out.println("blablabla"+comp.getCategorie().getNom());
								if(!testValeurTableau(parcourPoidsManquantMasculin, comp.getPoids(),comp.getCategorie().getNom()) && comp.getGenre().equals(tableau.getValueAt(tableau.getSelectedRow(), 2).toString())&& comp.getGenre().equals("H")){
									parcourPoidsManquantMasculin.put(comp.getCategorie().toString(),comp.getPoids());
								}
							
								
								if(!testValeurTableau(parcourPoidsManquantFeminin, comp.getPoids(),comp.getCategorie().getNom()) && comp.getGenre().equals(tableau.getValueAt(tableau.getSelectedRow(), 2).toString())&& comp.getGenre().equals("F")){
									parcourPoidsManquantFeminin.put(comp.getCategorie().toString(),comp.getPoids());
								}
							}
						}
					}
				}


				/** Message d'alerte quand il manque des catégories associé aux attributs de chaques compétiteurs**/

				labelAlerteMasculin.setForeground(Color.RED);
				labelAlerteFeminin.setForeground(Color.BLUE);
				String msgalerteMasculin = "Veuillez recreer les categories Masculin pour les ages suivant :";
				String msgalerteFeminin = "Veuillez recreer les categories Feminin pour les ages suivant :";

			//	poidsManquantMasculin=triBulleCroissant(parcourPoidsManquantMasculin);
			//	poidsManquantFeminin=triBulleCroissant(parcourPoidsManquantFeminin);

			//	System.out.println(poidsManquantMasculin);
			//	System.out.println(poidsManquantFeminin);
				
			
				Iterator<String> it;
				it=parcourPoidsManquantMasculin.keySet().iterator(); // on cree un iterator sur les clés de ton hashmap
				 
				while(it.hasNext())
				{
				   Object key=it.next();
				   Object value=parcourPoidsManquantMasculin.get(key);
				   
				   
				   msgalerteMasculin=msgalerteMasculin+"H :"+key+value; 
				   //ton traitement
				}

				if(poidsManquantMasculin.size()>0)
					labelAlerteMasculin.setText(msgalerteMasculin);
//
//				for(int poids :poidsManquantMasculin){
//					msgalerteMasculin=msgalerteMasculin+Integer.toString( poids)+"M ;"; 
//				}
//				if(poidsManquantMasculin.size()>0)
//					labelAlerteMasculin.setText(msgalerteMasculin);
//
//				for(int poids :poidsManquantFeminin){
//					msgalerteFeminin=msgalerteFeminin+Integer.toString( poids)+"F ;"; 
//				}
//				if(poidsManquantFeminin.size()>0)
//					labelAlerteFeminin.setText(msgalerteFeminin);



				contenuHaut.add(labelAlerteMasculin);
				contenuHaut.add(labelAlerteFeminin);
				int numeroligne=ligneSelectionTableau;
				Controleur.supprimCategoriePoids(numeroligne);

				model.removeRow(ligneSelectionTableau);	

			}
		});

		boutonReset.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				for(CategoriePoids cat : Controleur.listeCategoriePoids){
					cat.setCategorie(Controleur.retrouveCategorie("minime"));
				}

				for(Club club : Controleur.listClub){
					for(Competiteur comp :club.getListCompetiteur()){
						for(CategoriePoids catpoids: Controleur.listeCategoriePoids){

							//							System.out.println(comp.getGenre());
							//							System.out.println(catpoids.getGenre());
							//							if(comp.getGenre().equals(catpoids.getGenre()))
							//									System.out.println("dac");
							//							if(comp.getGenre().equals(catpoids.getGenre()))
							//								System.out.println("oo");
							//							if(comp.getPoids()>=catpoids.getPoidsMin() && comp.getPoids()<=catpoids.getPoidsMax() && comp.getNom().equals("BOUDISSA") && catpoids.getGenre().equals("F") && comp.getGenre().equals(catpoids.getGenre())){
							//								System.out.println("OK");
							//							}
							if(comp.getPoids()>=catpoids.getPoidsMin() && comp.getPoids()<=catpoids.getPoidsMax()  && comp.getGenre().equals(catpoids.getGenre())){
								//	if(comp.getPoids()>=catpoids.getPoidsMin() && comp.getPoids()<=catpoids.getPoidsMax() && comp.getGenre()==catpoids.getGenre()){
								System.out.println(catpoids);
								comp.setCategoriePoids(catpoids);
							}
						}
					}
				}
			}

		});

	}

	public static void refreshCategorie(){
		comboCategorie.removeAllItems();

		for(Categorie cat:Controleur.listeCategorie){
			comboCategorie.addItem(cat.getNom());
		}

	}


		public static void refreshTableau(){
			//comboGenre.getSelectedItem().toString();
			model.getDataVector().removeAllElements();
			model.addRow(new Object[]{"","","","","","","",""});
			for(CategoriePoids catPoids: Controleur.listeCategoriePoids){
				if(catPoids.getGenre().equals(comboGenre.getSelectedItem().toString()) && catPoids.getCategorie().getNom().equals(comboCategorie.getSelectedItem().toString())){
					model.addRow(new Object[]{catPoids.getPoidsMin(),catPoids.getPoidsMax(),catPoids.getGenre()});					
				}
			}
		}




	private boolean testValeurTableau(HashMap <String,Integer> tableau, Integer i,String categorie){
//		for(Integer tab:tableau){
//			if(i==tab)
//				return true;
//		}
		Iterator<Entry<String, Integer>> entries = tableau.entrySet().iterator();
		while (entries.hasNext()) {
			Entry<String, Integer> entry = entries.next();
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
			return false;
		}
//		while(it.hasNext())
//		{
//		   Object key=it.next();
//		   //Object value=tableau.get(key);
//		   System.out.println("cat = "+categorie);
//		   System.out.println("clef donnée ="+i);
//		   System.out.println("key "+it.next());
//		   System.out.println("valeur associer clef "+tableau.get(key));
//		   
//		   if(key.equals(categorie) && tableau.get(key)==i){
//			   return true;
//		   }
//		   //ton traitement
//		}
//
//		return false;
//
//	}



	public static ArrayList<Integer> triBulleCroissant(ArrayList<Integer> tableau) {
		int longueur = tableau.size();
		int tampon = 0;
		boolean permut;

		do {
			// hypothèse : le tableau est trié
			permut = false;
			for (int i = 0; i < longueur - 1; i++) {
				// Teste si 2 éléments successifs sont dans le bon ordre ou non
				if (tableau.get(i) > tableau.get(i+1)) {
					// s'ils ne le sont pas, on échange leurs positions
					tampon = tableau.get(i);
					tableau.set(i, tableau.get(i+1));
					tableau.set(i+1, tampon) ;
					permut = true;
				}
			}
		} while (permut);
		return tableau;
	}

}

