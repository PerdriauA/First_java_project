import java.io.*;
import java.util.*;

class Gestion 
{
    public static void main(String[] args)
    {
	Vector lesClients = lire();
	
	System.out.println("Bienvenue sur 'Des vacances à la carte pour tous les goûts et les budgets'.");

	while(true)
	    {
		System.out.println("Que voulez-vous faire?");
		int rep = menuPrincipal();
		switch(rep)
		    {
		    case 1: creaReserv(lesClients); break;
		    case 2: modifReserv(lesClients);break;
		    case 3: factureClient(lesClients); break;
		    case 0: sauvegarder(lesClients); System.exit(0);
		    default: System.out.println("Choix incorrecte, Recommencez ! ");
		    }
	    }
    } 
    
    public static void creaReserv(Vector lesClients)
    {
	Client unClient = creationClient(lesClients);
	reservation(unClient);
	}

    public static Client creationClient (Vector lesClients)
    {
	System.out.println("Quel est le nom du client ?");
	String nom = saisieChaine();

	Client unClient = new Client(nom);
	lesClients.addElement(unClient);

	return unClient;
    } 

    public static void modifReserv(Vector lesClients)
    {
    	Client unClient = find(lesClients);
    	reservation(unClient);
    }

    public static void factureClient(Vector lesClients)
    {
	Client unClient = find(lesClients);
	unClient.facture();
    }

    public static Client find(Vector lesClients)
    {
    	System.out.println("Quel est le nom du client recherché?");
    	String nom = saisieChaine();
    	for(Enumeration e=lesClients.elements(); e.hasMoreElements();)
    	{
    		Client item =(Client) e.nextElement();
    		if(item.getNom().equals(nom))
    			return item;
    	}

    	System.out.println("Ce client n'existe pas ! ");
    	return null;
    }

	public static void reservation(Client unClient)
	{
		while(true)
	    {
			System.out.println("Que voulez-vous faire?");
			System.out.println("Il faut rentrer la prestation du client et sa destination avant de pouvoir interagir sur d'autres élements de la réservation");
			int rep = menuClient();
 			switch(rep)
		    {
		    	case 1: choixPrestation(unClient); break;
		    	case 2: choixDestination(unClient); break;
		    	case 3: choixLuxe(unClient); break;
		    	case 4: choixDestination2(unClient); break;
		    	case 5: choixLuxe2(unClient); break;
		    	case 6: unClient.facture(); break;
		    	case 0: return;
		    	default: System.out.println("Choix incorrecte, Recommencez ! ");
		    }
	    } 
    }
		
    public static void choixPrestation(Client unClient)
    {
	Prestation prest = unClient.getPrestation();
	int rep = menuPrestation();
	switch(rep)
	    {
	    case 1: break;
	    case 2: prest.prestation(); break;
	    case 3: prest.prestationPlus(); break;
	    default: System.out.println("Choix incorrecte, Recommencez ! ");
	    }
    }

    public static void choixDestination(Client unClient)
    {
    	Vol volA = unClient.getVolAl();
	Vol volR = unClient.getVolRet();
	String [] depart = volA.getdepart();
	String [] destination = volA.getdestination();
	
	int repDepart = menuDepart();
	String dep = depart[repDepart-1];
	volA.setDep(dep);
	volR.setDest(dep);
	
	int repDestination  = menuDestination();
	String dest = destination[repDestination-1];
	volA.setDest(dest);
	volR.setDep(dest);
	boolean test = volA.rechDispo();
	if(!(test))
	    {	
		System.out.println("Cette destination n'est pas accessible directement. Vous prendrez donc un relai par la ville la plus proche");
		System.out.println("");
	    }	
	double prixAct = volA.rechPrix();
	System.out.println("");
	System.out.println("Le prix de votre billet d'avion est actuellement de "+prixAct);
	if (volA.testRed())
	    {
		System.out.println("Le client peut bénéficier du tarif réduit ou prendre une place en 1ere classe");
		System.out.println("1-Beneficier du tarif reduit? (minoration de 20%)");
		System.out.println("2-Place en première classe? (majoration de 30%)");
		int repType = saisieEntree();
		switch(repType)
		    {
		    case 1: volA.volReduit(); break;
		    case 2: volA.volClass(); volR.volClass(); break;
		    default: System.out.println("Choix incorrecte, Recommencez ! ");
		    }
	    }
	else 
	    {
		System.out.println("Voulez-vous une place en première place pour une majoration de 30% (O/N)");
		String repType = saisieChaine();
		if(repType.equals("o"))
		    {
		    volA.volClass();
		    volR.volClass();
		    }
	    }		
		double prixFinal = volA.getPrix();
		System.out.println("");
		System.out.println("Le prix de votre vol sera donc de " +prixFinal);
    }
    
    public static void choixLuxe(Client unClient)
    {
    	if (!((unClient.getPrestation()).getPrest()))
    	{
	    System.out.println("Votre client a choisi un séjour sans prestation.");
	    return;
    	}
    	
 	Hotel hotel = unClient.getHotel1();
	String dest = (unClient.getVolAl()).getDest();
	double prixAct = hotel.rechPrix(dest);
	hotel.setPrix(prixAct);
	System.out.println("");
	System.out.println("Le prix de votre hotel à " +dest+ " est actuellement de "+prixAct);
	System.out.println("Voulez-vous une chambre de luxe pour 20% de majoration?");
	System.out.println("Tapez 1 pour oui et 0 pour non");
	int rep = saisieEntree();
	if(rep == 1)
	    hotel.ChambLuxe();
	double prixFinal = hotel.getPrix();
	System.out.println("Le prix de votre hotel sera donc de " +prixFinal );
	System.out.println("");

	Voiture voiture = unClient.getVoiture1();
	System.out.println("Le client souhaite-il une voiture a " +dest);
	System.out.println("Tapez 1 pour oui et 0 pour non");
	rep = saisieEntree();
	if(rep == 1)
	    voiture.setPrix(voiture.rechPrix(dest));
	prixFinal = voiture.getPrix();
	System.out.println("Le prix de votre voiture sera de " +prixFinal );
    }

    public static void choixDestination2(Client unClient)
    {
    	if (!((unClient.getPrestation()).getPrestplus()))
	    {
    		System.out.println("Votre client a choisi un séjour simple.");
    		return ;
	    }
	Vol volA = unClient.getVolAl();
	Vol volRe = unClient.getVolRel();
	Vol volR = unClient.getVolRet();
	String dest1 = volA.getDest();
	String dest2 = "";
	volRe.setDep(dest1);
	if(dest1.equals("Pointe a Pitre"))
	    {
		System.out.println("Votre deuxième destination est Fort de France");
		dest2 = "Fort de France";
	    }
	if (dest1.equals("Fort de France"))
	    {
		System.out.println("Votre deuxième destination est Pointe a Pitre");
		dest2 = "Pointe a Pitre";
	    }
	if (dest1.equals("Tombouctou"))
	    {
		System.out.println("Votre destination peut etre Dakar ou Casablanca");
		System.out.println("Tapez 1 pour Dakar et 2 pour Casablanca");
		int rep = saisieEntree();
		switch(rep)
		    {
		    case 1: dest2 = "Dakar"; break;
		    case 2: dest2 = "Casablanca"; break;
		    default: System.out.println("Choix incorrecte, Recommencez");
		    }
		}
	if (dest1.equals("Dakar"))
	    {
		System.out.println("Votre destination peut etre Tombouctou ou Casablanca");
		System.out.println("Tapez 1 pour Tombouctou et 2 pour Casablanca");
		int rep = saisieEntree();
		switch(rep)
		    {
		    case 1: dest2 = "Tombouctou"; break;
		    case 2: dest2 = "Casablanca"; break;
		    default: System.out.println("Choix incorrecte, Recommencez !");
		    }
		}
	if (dest1.equals("Casablanca"))
	    {
		System.out.println("Votre destination peut etre Tombouctou ou Dakar");
		System.out.println("Tapez 1 pour Tombouctou et 2 pour Dakar");
		int rep = saisieEntree();
		switch(rep)
		    {
		    case 1: dest2 = "Tombouctou"; break;
		    case 2: dest2 = "Dakar"; break;
		    default: System.out.println("Choix incorrecte, Recommencez !");
		    }
		}

	volRe.setDest(dest2);
	volR.setDep(dest2);
	double prixAct = volRe.rechPrix();
	volRe.setPrix(prixAct);
	System.out.println("");
	System.out.println("Le prix de votre billet d'avion est actuellement de "+prixAct);
	System.out.println("Voulez-vous une place en première place pour une majoration de 30%");
	System.out.println("Tapez 1 pour oui et 0 pour non");
	int rep = saisieEntree();
	if(rep == 1)
	    volRe.volClass();	
	double prixFinal = volRe.getPrix();
	System.out.println("");
	System.out.println("Le prix de votre vol sera donc de " +prixFinal );
    }

    public static void choixLuxe2(Client unClient)
    {
    	if (!((unClient.getPrestation()).getPrestplus()))
    	{
    		System.out.println("Votre client a choisi un séjour simple.");
    		return ;
    	}

	Hotel hotel = unClient.getHotel2();
	String dest = (unClient.getVolRel()).getDest();
	double prixAct = hotel.rechPrix(dest);
	hotel.setPrix(prixAct);
       	System.out.println("Le prix de votre hotel à "+ dest +" est actuellement de "+prixAct);
	System.out.println("Voulez-vous une chambre de luxe pour 20% de majoration?");
	System.out.println("Tapez 1 pour oui et 0 pour non");
	int rep = saisieEntree();
	if(rep == 1)
	    hotel.ChambLuxe();
	double prixFinal = hotel.getPrix();
	System.out.println("");
	System.out.println("Le prix de votre hotel sera donc de " +prixFinal );

	Voiture voiture = unClient.getVoiture2();
	System.out.println("Le client souhaite-il une voiture a " + dest);
	System.out.println("Tapez 1 pour oui et 0 pour non");
	rep = saisieEntree();
	if(rep == 1)
	    voiture.setPrix(voiture.rechPrix(dest));
	prixFinal = voiture.getPrix();
	System.out.println("");
	System.out.println("Le prix de votre voiture sera de " +prixFinal );
    }

 public static int menuPrincipal()
    {
    	System.out.println("");
    	System.out.println("================================================");
	System.out.println("1- Nouvelle reservation");
	System.out.println("2- Modification d'une reservation");
	System.out.println("3- Afficher la facture d'un client");
	System.out.println("0- Quitter");
	System.out.println("================================================");
	System.out.println("");
	int rep = saisieEntree();
	return rep;
    }

    public static int menuClient()
    {
    	System.out.println("");
    	System.out.println("================================================");
		System.out.println("1- Choisir le type de prestation voulu?");
		System.out.println("2- Choisir votre voyage");
		System.out.println("3- Hotel et voiture");
		System.out.println("4- Deuxième destination (pour un sejour itinerant)");
		System.out.println("5- Deuxième hotel et voiture (pour un sejour itinerant)");
		System.out.println("6- Afficher la facture");
		System.out.println("0- Fin de la reservation");
		System.out.println("================================================");
		System.out.println("");
		int rep = saisieEntree();
		return rep;
    }
	
	public static int menuPrestation()
    {
	System.out.println("Quel type de sejour voulez vous?");
	System.out.println("1-Un sejour sans prestation avec seulement un billet d'avion A/R.");
	System.out.println("2-Un sejour avec une reservation  dans un hotel et la possibilité de louer une voiture?");
	int rep = saisieEntree();
	if (rep == 2)
	    {
		System.out.println("2-Un sejour simple : chambre d'hotel et voiture");
		System.out.println("3-Un sejour itinerant avec changement de lieu de vacance pendant le sejour");
		rep = saisieEntree();
		return rep;
	    }
	
	return rep;
    }

        public static int menuDepart()
    {
        System.out.println("");
        System.out.println("Quelle serait la ville de depart?");
	System.out.println("1- Paris");
	System.out.println("2- Bordeaux");
	System.out.println("3- Marseille");
	System.out.println("4- Lille");
	System.out.println("5- Lyon");
	System.out.println("");
	int rep = saisieEntree();

   		return rep;
    }

    public static int menuDestination()
    {
        System.out.println("");
        System.out.println("Quelle serait la destination?");
        System.out.println("1- Pointe a Pitre");
        System.out.println("2- Fort de France");
        System.out.println("3- Tombouctou");
        System.out.println("4- Dakar");
        System.out.println("5- Casablanca");
        System.out.println("");
        int rep = saisieEntree();

   		return rep;
    }

    public static void sauvegarder(Vector lesClients)
    {
	try
	    {
		ObjectOutputStream flotEcriture = new ObjectOutputStream(new FileOutputStream("Reservation"));
		flotEcriture.writeObject(lesClients);
        	flotEcriture.flush();
        	flotEcriture.close();
	    }
        catch(IOException e){System.out.println("Erreur");}
    }

    public static Vector lire()
    {
	File fichier  = new File("Reservation");
	if( !fichier.exists() )
	    {
		Vector lesClients = new Vector();
		return lesClients;
	    }
	try
	    {
		ObjectInputStream flotLecture = new ObjectInputStream(new FileInputStream(fichier));
		Vector lesClients = (Vector) flotLecture.readObject();

		for(Enumeration e = lesClients.elements();e.hasMoreElements();)
		    {
			Client unClient = (Client) e.nextElement();
			Vol item = unClient.getVolAl();
			String dest = item.getDest();
			if (item.getReduit())
			    item.diminutionReserv();
		    }
		
		return lesClients;
	    }
	catch(IOException e){System.out.println("Erreur");}
	catch(ClassNotFoundException c){System.out.println("Erreur de chargement");}
	System.out.println("Probleme lors de la lecture");
	return null;
    }
    
    public static int saisieEntree()
    {
        try
            {
                BufferedReader buff = new BufferedReader (new InputStreamReader(System.in));
                String chaine=buff.readLine();
                int num = Integer.parseInt(chaine);	
                return (num);
            }
        catch(IOException e)
            {
                return 0;
            }
    }

    public static String saisieChaine()
    {
        try
            {
                BufferedReader buff = new BufferedReader (new InputStreamReader(System.in));
                String chaine=buff.readLine();
                return chaine;
            }
     catch(IOException e)
            {
                System.out.println("Impossible de travailler" +e);
                return null;
            }
    }

}
