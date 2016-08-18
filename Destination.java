import java.io.*;
public class Destination implements Serializable{

	  private static String depart[] = {"Paris","Bordeaux","Marseille","Lille","Lyon" };
	  private static String destination[]= {"Pointe a pitre","Fort de France","Tombouctou","Dakar","Casablanca"};
	  public String ville_depart;
	  public String ville_arrivee;
          public double prix2;
          public double prix3;
          static int reservoir = 3;
          int numero;
       
	  public static boolean disponibilite [] [] = {
		  { true,  true,  true,  true,  true},
		  { true, false, false,  true,  true},
		  { true, false,  true,  true, false},
		  {false,  true,  true, false, false},
		  { true, false,  true,  true, false} 
	  };
	  
	  public static int prix1 [][]={
		  {1000, 1100, 700, 600, 500},
		  {1010,    0,   0, 610, 510},
		  {1020,    0, 720, 620, 520},
		  {   0, 1130, 730,   0,   0},
		  {1040,    0, 740, 640,   0} 
          };
		  
	  public static void Vol()
	  {
		  depart = new String [5];
		  destination = new String [5];
		  disponibilite = new boolean [5] [5];
		  prix1 = new int [5][5];
	  }
	  
	  public void destination()
	  {
	      System.out.println("Voici les villes de départ disponible : ");
	      for (int i = 0; i < depart.length; i ++)
		  {
		      System.out.println(depart[i]);
		  }
	      while (true)
		  {
		      System.out.println("Saisissez la ville de départ.");
		      ville_depart = saisieChaine();
		      if (controle(depart, ville_depart) == true)
			  {
			      break;
			  }
		  }
		  
	      System.out.println("Depuis "+ville_depart+" plusieurs destinations sont possibles.");
	      for (int i = 0; i<destination.length; i++)
		  {
		      if (verif(ville_depart, destination [i])==true)
			  {
			      System.out.println(destination[i]);
			  }
		  }
		  
	      while (true)
		  { 
		      System.out.println("Saisissez la destination souhaitée.");
		      ville_arrivee = saisieChaine();
		      if (controle(destination, ville_arrivee)==true)
			  {
			      break;
			  }
		  }
	      if (verif(ville_depart, ville_arrivee)==true)
		  {
		      System.out.println("Le client voyagera entre : " +ville_depart+ " et " +ville_arrivee);
			  
		  }
	      else
		  {
		      System.out.println("Il n'y a pas de vol disponible pour la destination suivante : "+ville_arrivee);
		  }
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
	  
	  public boolean verif(String ville_depart, String ville_arrivee)
	  {
	      int ligne = 0;
	      int colonne = 0;
	      for (ligne = 0; ligne<depart.length; ligne++)
		  {
		      if (ville_depart.equals (depart[ligne]))
			  {
			      for (colonne = 0; colonne<destination.length; colonne++)
				  {
				      if (ville_arrivee.equals(destination[colonne]))
					  {
					      return disponibilite [ligne][colonne];
					  }
				  }
			  }
		  }
	      return false;
	  }
	  
	  public int rechercher_prix (String ville_depart, String ville_arrivee)
	  {
	      int ligne = 0;
	      int colonne = 0;
	 
  	  
	      for (ligne=0; ligne<depart.length; ligne++)
		  {
		      if (ville_depart.equals(depart[ligne]))
			  {
			      for (colonne=0; colonne<destination.length; colonne++)
				  {
				      if (ville_arrivee.equals(destination[colonne]))
					  {
					      System.out.println("Il y a des  places à prix réduit");
					      System.out.println("Le client en veut-il une? 1 pour oui ou 2 pour non");
					      int reduit = saisieEntier();
					      
					     /* System.out.println("Le voyage a prix reduit coute: " +prix3);*/
					      		   	
					         if (reduit == 1)
						  {
 
						      reservoir --;
						      numero = reservoir;
						      System.out.println("Il reste "+numero+" places à prix réduit.");
						      
						      if (numero == 0)
							  {
							      System.out.println("Il n'y a plus de places à prix réduit.");
							  }
						 
						      double prix2 = prix1[ligne][colonne]-(prix1[ligne][colonne]*0.20);

						      System.out.println("Le voyage a tarif réduit coute : "+prix2);
						      break;
						     
						  }
						 if (reduit == 2) 
						     {
							System.out.println("Le voyage se fait-il en première classe? 1 pour oui ou 2 pour non");
							int classe = saisieEntier();
							if (classe == 1)
							    {
								 double prix3 = prix1[ligne][colonne]+(prix1[ligne][colonne]*0.30);
								 System.out.println("Le voyage en première classe coute : "+prix3);
								 break;
							    }
							if (classe ==2)
							    {
								int prixs= prix1 [ligne][colonne];
								System.out.println("Le prix du billet est de :"+prixs);
							    }
						     }
						
						
					  }
				  }
			  }
		  }
	    return 0;
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
	      buff.write(ville_depart);
	      buff.newLine();
	      buff.write(ville_arrivee);
	      buff.newLine();
	      
	       for(int ligne = 0; ligne<prix1.length; ligne ++)
		  {
		      for(int colonne = 0; colonne<prix1[ligne].length; colonne ++)
			  {
			      buff.write((new Integer(prix1 [ligne][colonne])).toString());
			    
			  }
		      buff.newLine();
		  }
	       buff.write((new Double(prix2)).toString());
	       buff.newLine();

	       buff.write((new Double(prix3)).toString());
	       buff.newLine();
	  }
}
