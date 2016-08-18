import java.io.*;

public class Hotel extends Sejour
{
    private boolean type = false;
    private double prix = 0;
    
    boolean getType(){return type;}
    double getPrix(){return prix;}

    void setType(boolean type){type = type;}
    void setPrix(double num){prix= num;}

    public double rechPrix(String des)
    {
	for (int i=0; i< destination.length; i++)
	    {
		if(des.equals(destination[i]))
		    return prixHotel[i];
	    }
	return 0;
    }

    public void ChambLuxe()
    {
	type = true;
	prix = prix + prix*0.2;
    }

    public void facture(String dest)
    {
        System.out.println("Votre hotel a "+dest+ " ---------------------- " +rechPrix(dest));
        if (type)
        {
                System.out.println("Chambre de luxe ----------------------------------- " );
                System.out.println("L'hotel ------------------------------------------- "+ prix);
        }
    }

}
