import java.io.*;
import java.util.*;

class Prestation extends Sejour implements Serializable
{

    private boolean typePrestation = false;
    private boolean typePrestationPlus = false;

    boolean getPrest(){return typePrestation;}
    boolean getPrestplus(){return typePrestationPlus;}

    public void prestation()
    {
    	typePrestation = true;
    }

    public void prestationPlus()
    {
    	typePrestation = true;
    	typePrestationPlus = true;
    }
}
