import java.io.*;
import java.util.*;

class Client implements Serializable
{
    private String nom;
    private Prestation prestation = new Prestation();
    private Vol billetAller = new Vol();
    private Vol billetRelais = new Vol();
    private Vol billetRetour = new Vol();
    private Hotel hotel1 = new Hotel();
    private Hotel hotel2 = new Hotel();
    private Voiture voiture1 = new Voiture();
    private Voiture voiture2 = new Voiture();
    private double cout = 0;

    String getNom(){return nom;}
    Prestation getPrestation(){return prestation;}
    Vol getVolAl(){return billetAller;}
    Vol getVolRel(){return billetRelais;}
    Vol getVolRet(){return billetRetour;}
    Hotel getHotel1(){return hotel1;}
    Hotel getHotel2(){return hotel2;}
    Voiture getVoiture1(){return voiture1;}
    Voiture getVoiture2(){return voiture2;}
    double getCout(){return cout;}

    void setNom(String ident){nom = ident;}
    void setPrestation(Prestation item){prestation = item;}
    void setVolAl(Vol item){billetAller = item;}
    void setVolRel(Vol item){billetRelais = item;}
    void setVolRet(Vol item){billetRetour = item;}
    void setHotel1(Hotel item){hotel1 = item;}
    void setHotel2(Hotel item){hotel2 = item;}
    void setVoiture1(Voiture item){voiture1 = item;}
    void setVoiture2(Voiture item){voiture2 = item;}
    void setCout(double num){cout = num;}

    public Client(String nom){this.nom = nom;}
    
    public double calculCoutFinal()
	{
	    double prixVolFinal = billetAller.getPrix() + billetRetour.getPrix() + billetRelais.getPrix();
	    double prixHotelFinal = hotel1.getPrix() + hotel2.getPrix();
	    double prixVoitureFinal = voiture1.getPrix() + voiture2.getPrix();
	    cout = prixVolFinal + prixHotelFinal + prixVoitureFinal;
	    return cout;
	}

    public void facture()
    {
        boolean test1 = prestation.getPrestplus();
        boolean test2 = prestation.getPrest();
        System.out.println("");
        if (test1)
        {
            billetAller.facture();
            System.out.println("");
            hotel1.facture(billetAller.getDest()); 
            System.out.println("");
            voiture1.facture(billetAller.getDest());
            System.out.println("");
            billetRelais.facture();
            System.out.println("");        
            hotel2.facture(billetRelais.getDest());
            System.out.println("");
            voiture2.facture(billetRelais.getDest());
            System.out.println("");
	    billetRetour.facture();
	    System.out.println("");
        }
        else
        {
            if(test2)
            {
                billetAller.facture();
                System.out.println("");
                hotel1.facture(billetAller.getDest()); 
                System.out.println("");
                voiture1.facture(billetAller.getDest());
                System.out.println("");
		billetRetour.facture();
		System.out.println("");
            }
            else
            {
                billetAller.facture();
                System.out.println("");
		billetRetour.facture();
                System.out.println("");
            }
        }
        calculCoutFinal();
        System.out.println("Votre sejour vous coutera donc " +cout);
        System.out.println("");
    }

}
