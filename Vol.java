import java.io.*;

class Vol extends Sejour implements Serializable
{
    private boolean type = false;
    private boolean reduit = false;
    private double prix = 0;
    private String dep;
    private String dest;

    String [] getdepart(){return depart;}
    String [] getdestination(){return destination;}

    String getDep(){return dep;}
    String getDest(){return dest;}
    boolean getReduit(){return reduit;}
    boolean getType(){return type;}
    double getPrix(){return prix;}
    
    void setDep(String nom){dep = nom;}
    void setDest(String nom){dest = nom;}
    void setPrix(double num){prix = num;}

    public boolean rechDispo()
    {
        for (int i=0; i< depart.length; i++)
        {
            if(dep.equals(depart[i]))
            { 
                for (int j=0; j< destination.length; j++)
                {
                    if(dest.equals(destination[j]))
                    return disponibilite[i][j];
                }
            }
        }
        return false;
    }

    public double rechPrix()
    {
	
        for (int i=0; i< depart.length; i++)
        {
            if(dep.equals(depart[i]))
            { 
                for (int j=0; j< destination.length; j++)
                {
                    if(dest.equals(destination[j]))
			{
			    prix = prixVol[i][j];
			    return prixVol[i][j];
			}
                }
            }
	    if(dep.equals(destination[i]))
		{ 
		    for (int j=0; j< destination.length; j++)
			{
			    if(dest.equals(depart[j]))
				{
				    prix = prixVol[j][i];
				    return prixVol[j][i];
				}
			    if (dest.equals(destination[j]))
				{
				    prix = prixRelais[j][i];
				    return prixRelais[j][i];
				}
			}
		}
	}
    	return 0;
    }

    public boolean testRed()
    {
        for (int i=0; i< destination.length; i++)
        {
            if(dest.equals(destination[i]))
            {
                if (reservoir[i] > 0)
		    return true;
		else
		    return false;
	    }
        }
	return false;
    }

    public void volClass()
    {
	type = true;
        prix = prix + prix * 0.3;
    }

    public void diminutionReserv()
    {
	for (int i=0; i< destination.length; i++)
	    {
		if(dest.equals(destination[i]))
		    reservoir[i] = reservoir[i]-1;
	    }
    }
    
    public void volReduit()
    {
	diminutionReserv();
        reduit = true;
        prix = prix - prix * 0.2;
    }

    public void facture()
    {
        System.out.println("Votre vol de "+ dep +" a "+ dest+ " ------------ "+ rechPrix());
        if (reduit)
	    {
	    volReduit();
            System.out.println("Reduction de 20 % --------------------------------- " +prix );
	    }
        if (type)
	    {
	    volClass();
            System.out.println("Vol en 1er Classe --------------------------------- " +prix );
	    }
	
    }

}
