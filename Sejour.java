import java.io.*;

public class Sejour implements Serializable{
    
    private  String nom_client;
    
    private Destination destination;
    double prix = 0;
    
    private Prestation prestation;
    int prix4 = 0;

    private Hotel reservation;
    double prix1 = 0;

    private Type_sejour itinerant;
    int prix6 = 0;
    
    public Destination getDestination()
    {return destination;}
    public void setDestination(Destination dest)
    {destination= dest;}

    public double getPrix()
    {return prix;}
    public void setPrix(double prixd)
    {prix = prixd;}

    public int getPrix2()
    {return prix4;}
    public void setPrix2(int prixp)
    {prix4  = prixp;}

    public double getPrix3()
    {return prix1;}
    public void setPrix3(double prixh)
    {prix1  = prixh;}

    public int getPrix4()
    {return prix6;}
    public void setPrix4(int prixi)
    {prix6  = prixi;}

    public Prestation getPrestation()
    {return prestation;}
    public void setPrestation(Prestation prest)
    {prestation = prest;}

    public Hotel getHotel()
    {return reservation;}
    public void setHotel(Hotel host)
    {reservation = host;}

    public  Type_sejour getItinerant()
    {return itinerant;}
    public void setItinerant(Type_sejour itin)
    {itinerant = itin;}
    
    public String getNom_client()
    {return nom_client;}
    
    public void setNom_client (String nom)
    {nom_client = nom;}
    
    void affichage()
    {
	System.out.println("Le client : ");
	System.out.println(nom_client);
	System.out.println("Fait le voyage : ");
	System.out.println(destination);
	System.out.println("ce qui lui coûte : ");
	System.out.println(prix4);
	System.out.println("La location coûte : ");
	System.out.println(prix);
	System.out.println("La nuit d'hôtel coûte : ");
	System.out.println(prix1);
	System.out.println("Le changement en itinérant coûte : ");
	System.out.println(prix6);
	
    }

	void save (BufferedWriter buff)throws IOException
	{
	    buff.write(nom_client);
	    buff.newLine();
	}
    
}




	
	
	
