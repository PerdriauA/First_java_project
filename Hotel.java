import java.io.*;

public class Hotel implements Serializable{
    
    private static String ville_sejour[]= {"Pointe a pitre","Fort de France","Tombouctou","Dakar","Casablanca"};
    
    public static String ville_dortoir;

    private static int prix7[] ={100, 110, 70, 60, 50};

    public static void hotel1()
    {
	ville_sejour = new String [5];
	prix7 = new int[5];
    }

    public double reservation()
    {
	double prix = 0;

	System.out.println("Voici les villes de séjour disponible : ");
	for (int i = 0; i < ville_sejour.length; i ++)
	    {
		System.out.println(ville_sejour[i]);
	    }
	while(true)
	    {
		System.out.println("Saisissez la ville de sejour.");
		ville_dortoir = saisieChaine();
		if (controle(ville_sejour, ville_dortoir)==true)
		    {
			System.out.println("Hotel commandé");
			break;
		    }
	    }
	return 0;
    }
    
    public int rechercher_prix(String ville_dortoir)
    {
	int i=0;
	
	for (i=0; i< ville_sejour.length; i++)
	    {
		if(ville_dortoir.equals(ville_sejour[i]))
		    {
			System.out.println("Combien de jour souhaité vous rester?");
			int NB = saisieEntier();
			System.out.println("Il existe une prestation de luxe. Le client la veut-il?1 pour oui ou 2 pour non");
			int luxe = saisieEntier();
			
			if (luxe == 1)
			    {
				double prixh = (prix7[i] + (prix7[i]*0.30)*NB);
				System.out.println("La suite de luxe coûte : "+prixh+" euros.");
				break;
			    }
			if (luxe == 2)
			    {
				double prixh2=(prix7[i]*NB);
				System.out.println("La suite coute :"+prixh2+" euros.");				
      			    }
		    }
	    }
	
	return 0;
    }
    
    private boolean controle(String [] villes, String ville)
    {
	int  i = 0;
	for (i = 0;i<villes.length; i++)
	    {
		if (ville.equals(villes[i]))
		    {
			return true;
		    }
	    }
	if (i>= villes.length)
	    {
		System.out.println("La ville : "+ville+ ", ne fait pas partie de la liste");
		return false;
	    }
	return false;
    }
    private String saisieChaine ()
    {
	try
	    {
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		String chaine=buff.readLine();
		
		return chaine;
	    }
	catch (IOException e) 
	    {
		System.out.println("impossible de travailler" +e);
		return null;
	    }
    }
    
    public static int saisieEntier()
    {
	try
	    {
		BufferedReader buff = new BufferedReader (new InputStreamReader(System.in));
		String chaine = buff.readLine();
		int num = Integer.parseInt(chaine);
		return num;
	    }
	catch(IOException e){return 0;}
    }
    
    void save (BufferedWriter buff)throws IOException
    {
	buff.write(ville_dortoir);
	buff.newLine();
	String prix = String.valueOf(prix7);
	buff.write(prix);
	buff.newLine();
    }
}


		
		
