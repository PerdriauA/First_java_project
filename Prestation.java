import java.io.*;
public class Prestation implements Serializable{

    private static int prix4=10;
	
    public static void prestation1()
    {
	System.out.println("Le client choisi une offre avec ou sans prestation?");
	System.out.println("Tapez 1 pour sans et 2 pour avec");
	int rep = saisieEntier();
	
	if (rep == 1)
	    {
		System.out.println("Le client veut juste un A/R");
	    }
	
	if (rep == 2)
	    {
		System.out.println("Le client veut une prestation");
	    }
    }
    public static int location()
    {
	System.out.println("Le client veut-il louer une voiture?");
	System.out.println("Tapez 1 pour oui");
		
	int rep2=saisieEntier();
	if (rep2 == 1)
	    {
      		System.out.println("la location coute : "+prix4+" euros par jour.");
		System.out.println("Combien de jour de location?");
		int NB = saisieEntier();
		return(int)(prix4*NB);
	    }
	return 0;
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
	String prix = String.valueOf(prix4);
	buff.write(prix);
	buff.newLine();
    }
}
