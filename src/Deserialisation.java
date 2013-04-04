import java.io.FileInputStream;
import java.io.ObjectInputStream;



public class Deserialisation {

	@SuppressWarnings({ "resource" })
	public static void deserialise(){
		try {
			FileInputStream fichier = new FileInputStream("gestionCompetitionCombat.ser");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			
			Sauvegarde  restauration = (Sauvegarde) ois.readObject();
			System.out.println("Deserialisation"+restauration.listClub);
			Controleur.listClub=restauration.listClub;
			Controleur.listeCategorie=restauration.listeCategorie;
			Controleur.listCategorieCombat=restauration.listCategorieCombat;
			Controleur.listeCategoriePoids=restauration.listeCategoriePoids;
			Controleur.chargementCategorie();
			Controleur.chargementCategoriePoids();
			
			PanelAjoutCatPoids.refreshCategorie();
		
			PanelAjoutClub.remplirListClub();
		//	PanelAjoutCategorie.refreshCheckBox(PanelAjoutCategorie.contenuBas);
			for(Club cl : restauration.listClub){
				System.out.println("*********");
				System.out.println(cl.getNom());
			}
			for(Categorie  cat : restauration.listeCategorie){
				System.out.println("*********");
				System.out.println(cat.getNom());
			}
			
		}
		catch (java.io.IOException e) {
			//e.printStackTrace();
			System.out.println("le fichier n'a pas était trouvé");
		}
		catch (ClassNotFoundException e) {
			//e.printStackTrace();
			System.out.println("le fichier n'a pas était trouvé");
		}
	}
}
