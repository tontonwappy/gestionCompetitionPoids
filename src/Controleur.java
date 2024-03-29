import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JComboBox;




public class Controleur {
	public static  ArrayList<Categorie> listeCategorie=new ArrayList<Categorie>();
	public static  ArrayList<CategoriePoids> listeCategoriePoids=new ArrayList<CategoriePoids>();
	public static ArrayList<Club> listClub=new ArrayList<Club>();
	public static ArrayList<Categorie> getListeCategorie() {
		return listeCategorie;
	}

	public static void listeCategorieToString(){
		for(Categorie cat :listeCategorie){
			System.out.println(cat.getNom());
		}
	}

	public static void setListeCategorie(ArrayList<Categorie> listeCategorie) {
		Controleur.listeCategorie = listeCategorie;
	}

	public static ArrayList<Club> getListClub() {
		return listClub;
	}

	public static void setListClub(ArrayList<Club> listClub) {
		Controleur.listClub = listClub;
	}

	public static ArrayList<ListeCombat> getListCategorieCombat() {
		return listCategorieCombat;
	}

	public static void setListCategorieCombat(
			ArrayList<ListeCombat> listCategorieCombat) {
		Controleur.listCategorieCombat = listCategorieCombat;
	}

	public static ArrayList<ListeCombat> listCategorieCombat = new ArrayList<ListeCombat>();


	public static Boolean inserCompetiteur(Competiteur competiteur,Club c){
		c.getListCompetiteur().add(competiteur);
		return true;
	}

	public static boolean verifInsertionDansCategorie(Competiteur competiteur){
		for(Categorie cat : listeCategorie){
			if(cat.getAgeMini()<=competiteur.getAge() && cat.getAgeMaxi()>=competiteur.getAge()){
				return true;
			}
		}
		return false;
	}

	public static Categorie inserCombattantCategorie(Competiteur competiteur){
		for(Categorie cat : listeCategorie){
			if(cat.getAgeMini()<=competiteur.getAge() && cat.getAgeMaxi()>=competiteur.getAge()){
				competiteur.setCategorie(cat);
				System.out.println("Nouveau competiteur ajout� dans :" + cat.getNom());
				return cat;
			}
		}
		return null;
	}
	/**************Verifie tout sur un competiteur avant de l'inserer dans une categorie de poids*********************/ 
	public static boolean verifInsertionPoids(int poids, String genre, int age){
		Categorie categorie=null;
		for(Categorie cat : listeCategorie){
			if((cat.getAgeMini()<=age) && (age<=cat.getAgeMaxi())){
				categorie=cat;
				break;
			}
		}
		
			for(CategoriePoids catpoids : listeCategoriePoids){
				if((catpoids.getPoidsMin()<=poids && catpoids.getPoidsMax()>=poids)&& catpoids.getCategorie().equals(categorie) && catpoids.getGenre().equals(genre)){
					System.out.println("Nouveau competiteur ajout� dans la categorie:" + catpoids.getPoidsMin()+"- "+catpoids.getPoidsMax());
					return true;
				}
			}
		
		return false;
	}



	public static CategoriePoids inserCategoriePoids(Competiteur competiteur, Categorie categorie){
		for(CategoriePoids catpoids : listeCategoriePoids){

			if((catpoids.getPoidsMin()<=competiteur.getPoids() && catpoids.getPoidsMax()>=competiteur.getPoids())&& catpoids.getCategorie().equals(categorie) && catpoids.getGenre().equals(competiteur.getGenre())){
				competiteur.setCategoriePoids(catpoids);
				System.out.println("Nouveau competiteur ajout� dans la categorie:" + catpoids.getPoidsMin()+"- "+catpoids.getPoidsMax());
				return catpoids;
			}
		}
		return null;
	}

	public static Boolean verifInsertionCategoriePoids(int poids,String genre){
		for(CategoriePoids cat : listeCategoriePoids){
			if(cat.getPoidsMin()<=poids && cat.getPoidsMax()>=poids && cat.getGenre().equals(genre)){
				return true;
			}
		}
		return false;

	}

	public static Boolean verifInsertionCategorie(int age){
		for(Categorie cat : listeCategorie){
			if(cat.getAgeMini()<=age && cat.getAgeMaxi()>=age){
				return true;
			}
		}
		return false;

	}



	public static Categorie retrouveCategorie(String categorieString){
		for(Categorie cat: listeCategorie){
			if (cat.getNom().equals(categorieString))
				return cat;
		}
		return null;
	}

	public static CategoriePoids retrouveCategoriePoids(String categorieString){
		for(CategoriePoids cat: listeCategoriePoids){
			String nomCat=cat.getPoidsMin()+"-"+cat.getPoidsMax();
			if (nomCat.equals(categorieString))
				return cat;
		}
		return null;
	}

	public static Boolean VerifCreationCategoriePoids(int poidsMin,int poidsMax, String genre, Categorie categorie){

		if(listeCategoriePoids==null)
			return true;
		for(CategoriePoids cat : listeCategoriePoids){
			if(cat.getPoidsMin()<=poidsMin && cat.getPoidsMax()> poidsMin && cat.getGenre().equals(genre) && categorie.equals(cat.getCategorie())){
				return false;
			}
			else if(cat.getPoidsMin()<=poidsMax && cat.getPoidsMax()> poidsMax && cat.getGenre().equals(genre)&& categorie.equals(cat.getCategorie())){
				return false;
			}

			else if(cat.getPoidsMin()<=poidsMin && cat.getPoidsMax()>=poidsMin && cat.getGenre().equals(genre)&& categorie.equals(cat.getCategorie())){
				return false;
			}

			else if(cat.getPoidsMin()>=poidsMin && cat.getPoidsMax()<=poidsMax && cat.getGenre().equals(genre)&& categorie.equals(cat.getCategorie())){
				return false;
			}

		}
		return true;
	}

	public static Boolean VerifCreationCategorie(int ageMini,int ageMaxi){
		for(Categorie cat : listeCategorie){
			if(cat.getAgeMini()<=ageMini && cat.getAgeMaxi()> ageMini ){
				return false;
			}
			else if(cat.getAgeMini()<=ageMaxi && cat.getAgeMaxi()> ageMaxi ){
				return false;
			}

			else if(cat.getAgeMini()<=ageMini && cat.getAgeMaxi()>=ageMaxi){
				return false;
			}

			else if(cat.getAgeMini()>=ageMini && cat.getAgeMaxi()<=ageMaxi){
				return false;
			}

		}
		return true;
	}

	public static Boolean  verifAgeDansCategorie(int age,Categorie cat){
		Boolean agebool=false;
		if((cat.getAgeMini()<=age) && (age<=cat.getAgeMaxi())){
			agebool=true;
		}	
		return agebool;	
	}

	public static Boolean  verifPoidsDansCategorie(int poids,CategoriePoids cat){
		Boolean agebool=false;
		if((cat.getPoidsMin()<=poids) && (poids<=cat.getPoidsMax())){
			agebool=true;
		}	
		return agebool;	
	}


	public static Club rechercheClub(String club){
		Club recupclub=new Club();
		for(Club cl : listClub){
			if(cl.getNom().equals(club)){
				recupclub=cl;
			}		
		}
		return recupclub;
	}

	public static boolean supprimClub(String club){
		boolean supprim = true;
		Club rechercheClub=rechercheClub(club);
		for(Competiteur comp : rechercheClub.getListCompetiteur()){
			if(comp.getClub().getNom().equals(club)){
				supprim=false;
			}
		}
		if(supprim){
			Club recupClub=rechercheClub(club);
			if(recupClub!=null){
				listClub.remove(recupClub);
			}
		}

		return supprim;
	}


	public static boolean supprimCompetiteurClub(Club club,int numeroDsArray){	
		int tailleAvant=club.getListCompetiteur().size();
		club.getListCompetiteur().remove(numeroDsArray);

		if(tailleAvant==club.getListCompetiteur().size()){
			return false;
		}
		else{
			return true;
		}
	}

	public static void supprimCategorie(int numeroDsArray){
		listeCategorie.remove(numeroDsArray);
	}

	public static void supprimCategoriePoids(int numeroDsArray){
		listeCategoriePoids.remove(numeroDsArray);
	}

	public static void afficheToutCompetiteur(){
		for(Club cl : listClub){
			for(Competiteur comp :cl.getListCompetiteur()){
				System.out.println("-----");			
				comp.toString();
				System.out.println("-----");
			}
		}
	}

	public static void generationListCombat(){
		listCategorieCombat.clear();
		for(CategoriePoids catpoids: Controleur.listeCategoriePoids){
			for(Categorie cat : listeCategorie){
				if(cat.isCategorieMixt()){
					/* */
					ListeCombat nvlListeCombatH=new ListeCombat(cat, "Mixte",catpoids);


					listCategorieCombat.add(nvlListeCombatH);
					for(Club cl : listClub){
						for(Competiteur comp : cl.getListCompetiteur()){
							System.out.println(comp.getNom());
							if(comp.getCategorie().getNom().equals(cat.getNom())){
								nvlListeCombatH.getListeCombattant().add(comp);

							}
						}
					}
				}

				else{

					if(catpoids.getGenre().equals("H")){
						ListeCombat nvlListeCombatH=new ListeCombat(cat, "H",catpoids);
						listCategorieCombat.add(nvlListeCombatH);
						for(Club cl : listClub){
							for(Competiteur comp : cl.getListCompetiteur()){
								System.out.println(comp.getNom());
								if(comp.getCategorie().getNom().equals(cat.getNom())){
									if(comp.getGenre().equals("H")){
										nvlListeCombatH.getListeCombattant().add(comp);
									}						
								}
							}
						}
					}
					else if(catpoids.getGenre().equals("F")){
						ListeCombat nvlListeCombatF=new ListeCombat(cat, "F",catpoids);
						listCategorieCombat.add(nvlListeCombatF);
						for(Club cl : listClub){
							for(Competiteur comp : cl.getListCompetiteur()){
								System.out.println(comp.getNom());
								if(comp.getCategorie().getNom().equals(cat.getNom())){
									if(comp.getGenre().equals("F")){
										nvlListeCombatF.getListeCombattant().add(comp);
									}						
								}
							}
						}
					}

				}
			}
		}
	}

	public static void insertionCombattantListCombat(){
		resetCombattant();
		for(ListeCombat li : listCategorieCombat){
			Collections.shuffle(li.getListeCombattant());	
			for(Competiteur comp1 :li.getListeCombattant()){
				if(!comp1.isDansListCombat() && comp1.getCategoriePoids().equals(li.getCategoriepoids())){
					for(Competiteur comp2 :li.getListeCombattant()){
						if(comp1.getClub()!=comp2.getClub() && !comp2.isDansListCombat()  && comp1!=comp2 &&   comp1.getCategoriePoids()==li.getCategoriepoids()  && comp2.getCategoriePoids()==li.getCategoriepoids()){
							Combat nouveauCombat =new Combat(li.getCategorie(),comp1,comp2,li.getGenre());				
							li.getListeCombat().add(nouveauCombat);
							comp1.setDansListCombat(true);
							comp2.setDansListCombat(true);
							break;
						}



					}
				}
			}
			for(Competiteur comp1 :li.getListeCombattant()){
				if(!comp1.isDansListCombat() && comp1.getCategoriePoids().equals(li.getCategoriepoids())){
					for(Competiteur comp2 :li.getListeCombattant()){
						if(!comp2.isDansListCombat() && comp1!=comp2 && comp1.getCategoriePoids()==li.getCategoriepoids()  && comp2.getCategoriePoids()==li.getCategoriepoids()){//faux categorie poids
							Combat nouveauCombat =new Combat(li.getCategorie(),comp1,comp2,li.getGenre());				
							li.getListeCombat().add(nouveauCombat);
							comp1.setDansListCombat(true);
							comp2.setDansListCombat(true);
							break;
						}



					}
				}

			}

			//if (li.getListeCombattant().size() % 2==1){

			for(Competiteur comp1 :li.getListeCombattant()){
				if(!comp1.isDansListCombat() && comp1.getCategoriePoids()==li.getCategoriepoids()){
					Combat nouveauCombat =new Combat(li.getCategorie(),comp1,null,li.getGenre());				
					li.getListeCombat().add(nouveauCombat);
					comp1.setDansListCombat(true);
				}
			}
			//	}

		}
		int i=0;
		//on le fait deux fois pour un meilleur resultat
		while(i<2){


			for(ListeCombat li : listCategorieCombat){
				for(Combat combat : li.getListeCombat()){
					if(combat.getDeuxiemeCombattant()!=null && combat.getPremierCombattant().getClub().getNom().equals(combat.getDeuxiemeCombattant().getClub().getNom())){			


						for(Combat listeCombattant2 : li.getListeCombat()){
							System.out.println("***");
							System.out.println(combat.getPremierCombattant());
							System.out.println(combat.getDeuxiemeCombattant());
							if(listeCombattant2.getDeuxiemeCombattant()!=null){
								if(!(combat.getPremierCombattant().getClub().getNom().equals(listeCombattant2.getPremierCombattant().getClub().getNom())) && !(combat.getPremierCombattant().getNom().equals(listeCombattant2.getDeuxiemeCombattant().getClub().getNom()))){


									System.out.println(listeCombattant2.getPremierCombattant());
									System.out.println(listeCombattant2.getDeuxiemeCombattant());
									Competiteur competetiteurSave=combat.getPremierCombattant();
									combat.setPremierCombattant(listeCombattant2.getPremierCombattant());															
									listeCombattant2.setPremierCombattant(competetiteurSave);

								}
							}
						}

					}
				}
			}
			i++;
		}

	}

	public static CategoriePoids retrouveCategoriePoids(int poidsMin, int poidsMax, String genre){
		
		for(CategoriePoids catPoids :listeCategoriePoids){
			if(catPoids.getPoidsMin()==poidsMin && catPoids.getPoidsMax()==poidsMax && catPoids.getGenre().equals(genre)){
				return catPoids;
			}
		}
		return null;
	}
	
	
	
	public static void chargementCategorie(){

		for(Categorie cat : Controleur.listeCategorie){
			PanelAjoutCategorie.listModel.addElement(cat.getNom());
			PanelAjoutCategorie.model.addRow(new Object[]{cat.getNom(),cat.getAgeMini(),cat.getAgeMaxi()});			
		}

	}

	public static void chargementCategoriePoids(){
		if(!(Controleur.listeCategoriePoids==null)){
			for(CategoriePoids cat : Controleur.listeCategoriePoids){
				PanelAjoutCatPoids.listModel.addElement(cat.getPoidsMin()+" - "+cat.getPoidsMax()+cat.getGenre());
				PanelAjoutCatPoids.model.addRow(new Object[]{cat.getPoidsMin(),cat.getPoidsMax(),cat.getGenre()});			
			}
		}
	}

	public static void resetCombattant(){
		for(Club cl : listClub){
			for(Competiteur comp : cl.getListCompetiteur()){
				comp.setDansListCombat(false);
			}
		}
	}

}