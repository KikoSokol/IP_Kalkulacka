package Vypocitavanie;

import Exceptions.*;
import Objekty.Siet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Vlsm
{
    public static ObservableList<Siet> vlsm(String siet, int prefix, String[][] siete) throws ZlyPrefixException, ZlyOctetException, ZlaDlzkaAMWException, MalaSietExcepiton, NieSietovaAdresaException, PismenoVOkteteAdresaException {

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
            NieSietovaAdresaException e = new NieSietovaAdresaException();
            throw e;
        }

        // vypocitane prefixi ku danym sietam
        int[] prefixi = new int[siete.length];

        //celkovy pocet pozadovanych adries
        long celkovyPocetVsetkychAdries = 0;

        // pridanie sietovej a broadcastovej adresy a vypocitanie prefixu
        for (int i = 0; i < siete.length; i++)
        {
            System.out.println("aa " + siete[i][0]);
            siete[i][0] = "" + (Long.parseLong(siete[i][0]) + 2);
//            try {
//                Integer.parseInt(siete[i][0]);
//                System.out.println("aaa " + siete[i][0]);
//            }
//            catch (NumberFormatException e)
//            {
//                MalaSietExcepiton malaSietExcepiton = new MalaSietExcepiton();
//                throw malaSietExcepiton;
//            }
            celkovyPocetVsetkychAdries +=  Long.parseLong(siete[i][0]);
            prefixi[i] = 32 - prevody.najblizsiaOdmocnina2(Integer.parseInt(siete[i][0]));
        }
        for (int u = 0; u < prefixi.length; u++)
        {
            System.out.print(prefixi[u] + "    ");
        }


        //Ak zadana siet je mala a neda sa rozdelit
        if(celkovyPocetVsetkychAdries >  prevody.exponentiationLong((long)2, (long)(32 - prefix)))
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
    private static String[][] quicksort(String[][] array, int left, int right)
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
}
