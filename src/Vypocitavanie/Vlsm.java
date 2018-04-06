package Vypocitavanie;

import Exceptions.*;
import Objekty.Siet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class Vlsm
{
    public static ObservableList<Siet> vlsm(String siet, int prefix, String[][] siete) throws zlyPrefixException, IOException, zlyOctetException, zlaDlzkaAMWException, MalaSietExcepiton, nieSietovaAdresaException {

        Prevody prevody = new Prevody();
        //rozdelene siete
        ObservableList<Siet> finallSiete = FXCollections.observableArrayList();

        //kontrola ci je zadana adresa sietova
        Siet kontrola = new Siet(siet,prefix);

        //zoradienie pozadovanych sieti od najvecej po najmensiu
        quicksort(siete,0, siete.length);


        /*
        kontrola spravnosti udajov - vyhadzovanie vinimiek
         */
        //ak na vstupe nebude sietova adresa
        if (!kontrola.getSietovaAdresa().equals(siet))
        {
            nieSietovaAdresaException e = new nieSietovaAdresaException();
            throw e;
        }

        // vypocitane prefixi ku danym sietam
        int[] prefixi = new int[siete.length];

        //celkovy pocet pozadovanych adries
        int celkovyPocetVsetkychAdries = 0;

        // pridanie sietovej a broadcastovej adresy a vypocitanie prefixu
        for (int i = 0; i < siete.length; i++)
        {
            siete[i][0] = "" + (Integer.parseInt(siete[i][0]) + 2);
            celkovyPocetVsetkychAdries += Integer.parseInt(siete[i][0]);
            prefixi[i] = 32 - prevody.najblizsiaOdmocnina2(Integer.parseInt(siete[i][0]));
        }

        //Ak zadana siet je mala a neda sa rozdelit
        if(celkovyPocetVsetkychAdries > prevody.exponentiation(2, 32 - prefix))
        {
            MalaSietExcepiton malaSietExcepiton = new MalaSietExcepiton();
            throw malaSietExcepiton;
        }


        for(String[] t : siete)
        {
            for(String r : t)
            {
                System.out.print(r + " ");
            }
            System.out.println();
        }
        //vypocitavanie sieti
        for(int o = 0; o < prefixi.length; o++)
        {
            if(o == 0)
            {
                finallSiete.add(new Siet(siet,prefixi[o],siete[o][1]));
            }
            else
            {
                String sietova = finallSiete.get(finallSiete.size() -1).getNasledujucaSietova();
                finallSiete.add(new Siet(sietova,prefixi[o],siete[o][1]));
            }
        }

        return finallSiete;


    }

    //zoradovanie 2rozmerneho pola - riadky podla velkosti prvkov v 1.stlpci
    public static String[][] quicksort(String[][] array, int left, int right)
    {
        if(left < right)
        {
            int boundary = left;
            for(int i = left + 1; i < right; i++){
                if(Integer.parseInt(array[i][0]) > Integer.parseInt(array[left][0])){
                    swap2(array, i, ++boundary);
                }
            }
            swap2(array, left, boundary);
            quicksort(array, left, boundary);
            quicksort(array, boundary + 1, right);
        }

        return array;
    }
    private static void swap2(String[][] array, int left, int right){
        String[] tmp = array[right];
        array[right] = array[left];
        array[left] = tmp;
    }

    private static String hladanieNazvu(String[][] a, int prefix)
    {
        Prevody prevody = new Prevody();
        int pocetZariadeni = (prevody.exponentiation(2,32 - prefix) - 2);
        for(int i = 0; i < a.length; i++)
        {
            if(Integer.parseInt(a[i][0]) == pocetZariadeni)
                return a[i][1];
        }
        return "";
    }
}
