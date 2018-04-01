package Vypocitavanie;

import Exceptions.*;
import Objekty.Siet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class Vlsm
{
    public static ObservableList<Siet> vlsm(String siet, int prefix, int[] siete) throws zlyPrefixException, IOException, zlyOctetException, zlaDlzkaAMWException, MalaSietExcepiton, nieSietovaAdresaException {

        Prevody prevody = new Prevody();
        //rozdelene siete
        ObservableList<Siet> finallSiete = FXCollections.observableArrayList();

        //kontrola ci je zadana adresa sietova
        Siet kontrola = new Siet(siet,prefix);


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
            siete[i] = siete[i] +2;
            celkovyPocetVsetkychAdries += siete[i];
            prefixi[i] = 32 - prevody.najblizsiaOdmocnina2(siete[i]);
        }

        //Ak zadana siet je mala a neda sa rozdelit
        if(celkovyPocetVsetkychAdries > prevody.exponentiation(2, 32 - prefix))
        {
            MalaSietExcepiton malaSietExcepiton = new MalaSietExcepiton();
            throw malaSietExcepiton;
        }

        //zoradenie prefixov od najmensieho po najvecsi
        quicksort(prefixi, 0, prefixi.length);

        //vypocitavanie sieti
        for(int o = 0; o < prefixi.length; o++)
        {
            if(o == 0)
            {
                finallSiete.add(new Siet(siet,prefixi[o]));
            }
            else
            {
                String sietova = finallSiete.get(finallSiete.size() -1).getNasledujucaSietova();
                finallSiete.add(new Siet(sietova,prefixi[o]));
            }
        }

        return finallSiete;


    }

    public static void quicksort(int[] array, int left, int right){

        if(left < right)
        {
            int boundary = left;
            for(int i = left + 1; i < right; i++){
                if(array[i] < array[left]){
                    swap(array, i, ++boundary);
                }
            }
            swap(array, left, boundary);
            quicksort(array, left, boundary);
            quicksort(array, boundary + 1, right);
        }

    }
    private static void swap(int[] array, int left, int right){
        int tmp = array[right];
        array[right] = array[left];
        array[left] = tmp;
    }
}
