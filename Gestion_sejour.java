import java.io.*;
import java.util.*;
import java.io.BufferedReader;

class Gestion_sejour
{
    public static void main (String[]argu)throws IOException
    {
	Vector sejour=new Vector();

	while (true)
	    {
		System.out.println("Votre voyage est à la carte. Faites vos choix.");
		afficherMenu();
		int rep = saisieEntier();
		switch(rep)
		    {
		    case 1 : destination(sejour);break;
		    case 2 : prestation1(sejour);break;
		    case 3 : hotel(sejour);break;
		    case 4 : type_sejour(sejour);break;
		    case 5 : affichage(sejour);break;
		    case 6 : ecrire(sejour);break;
		    case 7 : creer_client(sejour);break;
		    case 0 : System.exit(0);
		    default : System.out.println("Votre choix est incorrect, saisissez une des valeurs indiquées");
		    }
	    }
    }
  
    public static void affichage (Vector sejour)
    {
	if (sejour.isEmpty())
	    {
		System.out.println("Vous n'avez ajouter aucun séjour dans votre fichier");
		return;
	    }
	for (Enumeration e = sejour.elements();e.hasMoreElements();)
	    {
		Sejour unsejour = (Sejour)e.nextElement();
		unsejour.affichage();
	    }
    }

   public static void destination(Vector sejour)
   {
       int prix1;
       double prix;
       Sejour unsejour = new Sejour();
       sejour.addElement(unsejour);
       Destination billet = new Destination();
       unsejour.setDestination(billet);
       billet.destination();

       prix = billet.rechercher_prix(billet.ville_depart, billet.ville_arrivee);
       unsejour.setPrix(prix);
       
   }


	    
    public static void prestation1(Vector sejour)
    {
	int prix4;
	Sejour unsejour = new Sejour();
	Prestation uneprestation = new Prestation();
	unsejour.setPrestation(uneprestation);
	uneprestation.prestation1();
	
	prix4 = uneprestation.location();
	System.out.println("La location d'une voiture vous coute : "+prix4+" euros.");
	unsejour.setPrix2(prix4);


    }

    public static void hotel (Vector sejour)
    {
	int prix7;
	double prix1;
	Sejour unsejour = new Sejour();
	Hotel unhotel = new Hotel();
	unsejour.setHotel(unhotel);
	unhotel.reservation();
        prix1 = unhotel.rechercher_prix(unhotel.ville_dortoir);

	unsejour.setPrix3(prix1);


    }

    public static void type_sejour(Vector sejour)
    {
	int prix6;
	Sejour unsejour = new Sejour();
	Type_sejour unitinerant= new Type_sejour();
	unsejour.setItinerant(unitinerant);
	unitinerant.sejour();
	prix6 = unitinerant.rechercher_prix(unitinerant.ville_itinerant,unitinerant.ville_arrivee);
	System.out.println("Le sejour itinérant entre : "+unitinerant.ville_itinerant+" et "+unitinerant.ville_arrivee+" coute "+prix6+" euros.");

	unsejour.setPrix4(prix6);	
    }

    static void afficherMenu()
    {
	System.out.println("Tapez 1 pour choisir un vol");
	System.out.println("Tapez 2 pour choisir une prestation");
	System.out.println("Tapez 3 pour choisir une offre d'hotel");
       	System.out.println("Tapez 4 pour choisir un type de séjour");
	System.out.println("Tapez 5 pour afficher les choix");
	System.out.println("Taper 6 pour ecrire les résultats dans un fichier");
	System.out.println("Taper 7 pour créer un sejour.");
	System.out.println("Tapez 0 pour quitter");
    }

    public static void creer_client(Vector sejour)
    {
	System.out.println("Combien de sejour voulez vous inscrire?");
	int NB= saisieEntier();
	System.out.println("Ajouter un sejour");
	for (int i = 0; i <NB ; i++)
	    {
		Sejour unsejour = null;
		System.out.println("Donnez le nom du client");
		String nom_client = saisieChaine();
	    
		if (unsejour != null)
		    {sejour.addElement(unsejour);}
	    }
    }

    public static String saisieChaine()
    {
	try{
	    BufferedReader buff = new BufferedReader
		(new InputStreamReader(System.in));
	    String chaine = buff.readLine();
	    return chaine;
	}
	catch(IOException e){
	    System.out.println("impossible de travailler" + e);
	    return null;
	}
    }

    public static int saisieEntier()
    {
	try{
	    BufferedReader buff = new BufferedReader (new InputStreamReader(System.in));
	    String chaine = buff.readLine();
	    int num = Integer.parseInt(chaine);
	    return num;
	}
	catch(IOException e){return 0;}
    }


    public static void ecrire (Vector sejour) throws IOException
    {
	BufferedWriter buff = new BufferedWriter (new FileWriter ("clientelle.txt"));
	for (Enumeration e = sejour.elements();e.hasMoreElements();)
		 {
		     Destination courant = (Destination)e.nextElement();
		     courant.save(buff);
		     Prestation courant1 = (Prestation)e.nextElement();
		     courant1.save(buff);
		     Hotel courant2 = (Hotel)e.nextElement();
		     courant2.save(buff);
		     Type_sejour courant3 = (Type_sejour)e.nextElement();
		     courant3.save(buff);
		 }
	     buff.flush();
	     buff.close();
    }
   
}

    
