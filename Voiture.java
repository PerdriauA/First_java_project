import java.io.*;
import java.util.*;

class Voiture extends Sejour implements Serializable
{
    private boolean pris = false;
    private double prix = 0;

    double getPrix(){return prix;}
    void setPrix(double num){prix= num;}

    public double rechPrix(String dest)
    {
        pris = true;
	    for (int i=0; i< destination.length; i++)
	    {
		    if(dest.equals(destination[i]))
		    return prixVoiture[i];
	    }
	return 0;
    }

    public void facture(String dest)
    {
        if(!pris)
            return;
        System.out.println("Votre voiture a "+dest+ " ---------------------- " +prix);
    }
}
