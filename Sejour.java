import java.util.*;
import java.io.*;

abstract class Sejour implements Serializable
{
    protected String depart [] = {"Paris","Bordeaux","Marseille","Lille","Lyon" };
    protected String destination []= {"Pointe a Pitre","Fort de France","Tombouctou","Dakar","Casablanca"};

    protected boolean disponibilite[][] = 
       {{ true , true,  true,  true,  true  },
        { true , false, false, true,  true  },
        { true , false, true,  true,  false },
        { false, true,  true,  false, false },
        { true , false, true,  true,  false }};

    protected int prixVol [][] =
       {{ 1000     ,     1100,       700,       600,       500 },
        { 1010     , 1010+150,   610+250,       610,       510 },
        { 1020     , 1020+150,       720,       620,       520 },
        { 1130+150 ,     1130,       730,   730+250,   730+300 },
        { 1040     , 1040+150,       740,       640,   640+200 }};
    
    protected int prixRelais[][] =
       {{    0 ,   150,    0,   0,   0 },
        {  150 ,     0,    0,   0,   0 },
        {    0 ,     0,    0, 250, 300 },
        {    0 ,     0,  250,   0, 200 },
        {    0 ,     0,  300, 200,   0 }};

    
    protected int reservoir[] = {10, 25, 20, 12, 15};
    
    protected int prixHotel [] = {250, 150, 300, 452, 320};

    protected int prixVoiture [] = {35, 50, 55, 22, 34};

}
