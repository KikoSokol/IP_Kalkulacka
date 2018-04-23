package Vypocitavanie;

import Exceptions.*;
import Objekty.Siet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;

public class Subneting
{
    /*
       Metoda na klasicky subneting
       rozdeli adresu na rovnako velke siete
        */
    public static ObservableList<Siet> sub(String sietA, int prefix, long pocet) throws ZlaDlzkaAMWException, ZlyOctetException, ZlyPrefixException, SubnetingException, NullSubnetingException, VelaRozdelenychSietiException, IOException, NieSietovaAdresaException, PismenoVOkteteAdresaException {
        Prevody prevody = new Prevody();

        //arraylist kde sa ulozia vsetky adresy
        ObservableList<Siet> vysledneAdresy = FXCollections.observableArrayList();

        //prevedieme stringovu siet na intovu
        int[] siet = getIntAddress(sietA);

        Siet kontrola = new Siet(sietA,prefix);


        /*
        kontrola spravnosti udajov - vyhadzovanie vinimiek
         */
        //ak na vstupe nebude sietova adresa
        if (!kontrola.getSietovaAdresa().equals(sietA))
        {
            NieSietovaAdresaException e = new NieSietovaAdresaException();
            throw e;
        }
        //ak uzivatel zada ze chce nula sieti
        if(pocet == 0)
        {
            NullSubnetingException e = new NullSubnetingException();
            throw e;
        }

        //ak uzivatel bude chciet rozdelit siet na viac ako 524288 sieti
        if (pocet > 1000)
        {
            VelaRozdelenychSietiException e = new VelaRozdelenychSietiException();
            throw e;
        }

        /*
        subnetting
         */
        //ak sa ma siet rozdelit na jednu siet
        if(pocet == 1)
        {
            vysledneAdresy.add(new Siet(siet,prefix));
        }
        // ak sa ma siet rozdelit na viacej sieti
        else
        {
            if (prefix < 0 || prefix > 32)
            {
                ZlyPrefixException e = new ZlyPrefixException();
                throw e;
            }





            // vypocitame druhy novy prefix
            long s = 2;
            int plus = prevody.najblizsiaOdmocnina2(pocet);
            if((32 - prefix) < plus)
            {
                SubnetingException e = new SubnetingException();
                throw e;
            }
            int druhyPrefix = prefix + prevody.najblizsiaOdmocnina2(pocet);





            //urcime si kde sa nachadza prvy prefix
            int fromThisPrefix = prefix / 8 + 1; 						// oktet kde sa nachadza prvy prefix
            int border = prefix - (prefix / 8)*8;                       // hranica kde sa zacne menit 0/1
            System.out.println("from> " + fromThisPrefix);
            System.out.println("border> " + border);

            //urcime si kde sa nachadza druhy prefix
            int toThisPrefix = druhyPrefix / 8 + 1;                     // oktet kde sa nachadza druhy prefix
            int bordet2 = (druhyPrefix - (druhyPrefix/ 8) *8);          // hranica kde sa konci zmena 0/1
            System.out.println("to> " + toThisPrefix);
            System.out.println("border2> " + bordet2);
            System.out.println();
            System.out.println();

            //polia zo vsetkymi moznostiami v oktetoch
            int[] octet1 = moznosti(siet[0],fromThisPrefix,toThisPrefix,1,border,bordet2);  // vsetky moznosti v prvom oktete
            int[] octet2 = moznosti(siet[1],fromThisPrefix,toThisPrefix,2,border,bordet2);  // vsetky moznosti v druhom oktete
            int[] octet3 = moznosti(siet[2],fromThisPrefix,toThisPrefix,3,border,bordet2);  // vsetky moznosti v tretiom oktete
            int[] octet4 = moznosti(siet[3],fromThisPrefix,toThisPrefix,4,border,bordet2);  // vsetky moznosti v stvrtom oktete

            for (int a : octet1)
            {
                System.out.print(a + " ");
            }
            System.out.println();

            for (int b : octet2)
            {
                System.out.print(b + " ");
            }
            System.out.println();

            for (int c : octet3)
            {
                System.out.print(c + " ");
            }
            System.out.println();

            for (int d : octet4)
            {
                System.out.print(d + " ");
            }
            System.out.println();

            //kombynacia vsetkych moznych rieseni a pridavanie rozdelenej siete do arraylistu
            int stop = 0;
            for(int a = 0; a < octet1.length; a++)
            {
                for(int b = 0; b < octet2.length; b++)
                {
                    for(int c = 0; c < octet3.length; c++)
                    {
                        for(int d = 0; d < octet4.length; d++)
                        {
                            int[] adresa = new int[4];
                            adresa[0] = octet1[a];
                            adresa[1] = octet2[b];
                            adresa[2] = octet3[c];
                            adresa[3] = octet4[d];
                            Siet sietTemp = new Siet(adresa,druhyPrefix);
                            sietTemp.setNazovSiete("Sieť " + (stop + 1));
                            vysledneAdresy.add(sietTemp);
                            stop++;
                            if (stop == pocet)
                                break;
                        }
                        if (stop == pocet)
                            break;
                    }
                    if (stop == pocet)
                        break;
                }
                if (stop == pocet)
                    break;

            }
        }


        //vratime rozdelenu siet
        return vysledneAdresy;

    }
    public static ObservableList<Siet> sub2(String sietA, int prefix, String maxZariadeniS, int pocet) throws ZlaDlzkaAMWException, ZlyOctetException, IOException, ZlyPrefixException, NieSietovaAdresaException, MalaSietExcepiton, PismenoVOkteteAdresaException {
        Prevody prevody = new Prevody();

        int maxZariadeni;
        try
        {
            maxZariadeni = Integer.parseInt(maxZariadeniS);
        }
        //ak maximalny pocet zariadeni bude vecsi ako 2147483647
        catch (NumberFormatException e)
        {
            MalaSietExcepiton malaSietExcepiton = new MalaSietExcepiton();
            throw malaSietExcepiton;
        }

        //arraylist kde sa ulozia vsetky adresy
        ObservableList<Siet> vysledneAdresy = FXCollections.observableArrayList();

        //prevedieme stringovu siet na intovu
        int[] siet = getIntAddress(sietA);

        Siet kontrola = new Siet(sietA,prefix);

         /*
        kontrola spravnosti udajov - vyhadzovanie vinimiek
         */
        //ak na vstupe nebude sietova adresa
        if (!kontrola.getSietovaAdresa().equals(sietA))
        {
            NieSietovaAdresaException e = new NieSietovaAdresaException();
            throw e;
        }

        // urcime si druhy prefix
        int druhyPrefix = 32 - prevody.najblizsiaOdmocnina2(maxZariadeni);

        if (druhyPrefix <= prefix)
        {
            MalaSietExcepiton malaSietExcepiton = new MalaSietExcepiton();
            throw malaSietExcepiton;
        }

        //urcime si kde sa nachadza prvy prefix
        int fromThisPrefix = prefix / 8 + 1; 						// oktet kde sa nachadza prvy prefix
        int border = prefix - (prefix / 8)*8;                       // hranica kde sa zacne menit 0/1

        //urcime si kde sa nachadza druhy prefix
        int toThisPrefix = druhyPrefix / 8 + 1;                     // oktet kde sa nachadza druhy prefix
        int bordet2 = (druhyPrefix - (druhyPrefix/ 8) *8);          // hranica kde sa konci zmena 0/1

        //polia zo vsetkymi moznostiami v oktetoch
        int[] octet1 = moznosti(siet[0],fromThisPrefix,toThisPrefix,1,border,bordet2);  // vsetky moznosti v prvom oktete
        int[] octet2 = moznosti(siet[1],fromThisPrefix,toThisPrefix,2,border,bordet2);  // vsetky moznosti v druhom oktete
        int[] octet3 = moznosti(siet[2],fromThisPrefix,toThisPrefix,3,border,bordet2);  // vsetky moznosti v tretiom oktete
        int[] octet4 = moznosti(siet[3],fromThisPrefix,toThisPrefix,4,border,bordet2);  // vsetky moznosti v stvrtom oktete

        //kombynacia vsetkych moznych rieseni a pridavanie rozdelenej siete do arraylistu
        int stop = 0;
        for(int a = 0; a < octet1.length; a++)
        {
            for(int b = 0; b < octet2.length; b++)
            {
                for(int c = 0; c < octet3.length; c++)
                {
                    for(int d = 0; d < octet4.length; d++)
                    {
                        int[] adresa = new int[4];
                        adresa[0] = octet1[a];
                        adresa[1] = octet2[b];
                        adresa[2] = octet3[c];
                        adresa[3] = octet4[d];
                        Siet sietTemp = new Siet(adresa,druhyPrefix);
                        sietTemp.setNazovSiete("Sieť " + (stop + 1));
                        vysledneAdresy.add(sietTemp);
                        stop++;
                        if (stop == pocet)
                            break;
                    }
                    if (stop == pocet)
                        break;
                }
                if (stop == pocet)
                    break;
            }
            if (stop == pocet)
                break;

        }

        //vratime rozdelenu siet
        return vysledneAdresy;
    }

    // Metoda ktora vracia pole so vsetkymi moznostami pre dany oktet
    private static int[] moznosti(int octet,int fromThisPrefix,int toThisPrefix, int numOctet,int border,int border2)
    {
        Prevody prevody = new Prevody();
        int[] moznosti = null;                                              // premenna do ktorej sa budu ukladat vsetky moznosti oktetu
        if(fromThisPrefix <= numOctet && toThisPrefix >= numOctet)			// numOctet - cislo oktetu ktory sa bude menit
        {
            // ak obidva prefixi su v jednom a tom istom oktete
            if(toThisPrefix == fromThisPrefix)
            {
                return dajMoznosti(octet, prevody.exponentiation(2, border2 - border),border,border2,0, 8, true);

            }

            // ak prvy prefix sa nachadza v tomto oktete a druhy sa nachadza za tymto oktetom
            if (fromThisPrefix == numOctet && toThisPrefix > numOctet)
            {
                return dajMoznosti(octet,prevody.exponentiation(2,8 - border),border, border2, border, 8, false);

            }

            //ak druhy oktet sa nachadza v tomto oktete a prvy sa nachadza pred tymto oktetom
            if (toThisPrefix == numOctet && fromThisPrefix < numOctet)
            {
                if(border2 == 0)  //ak sa oktet nema ako zmenit a ma len jednu moznost 0
                {
                    moznosti = new int[1];
                    moznosti[0] = 0;
                    return moznosti;
                }
                return dajMoznosti(octet, prevody.exponentiation(2,border2),border, border2, 0, border2, false);

            }

            //ak obidva prefixi sa nenachadzaju v tomto oktete
            if(fromThisPrefix < numOctet && toThisPrefix > numOctet)
            {
                //celi oktet zmenim na same jednotky moznosti 0 - 255
                return dajMoznosti(octet,256,border,border2, 0,8, false);

            }

        }
        else                                   // ak v danom oktete sa nenachadza ziaden prefix
        {
            moznosti = new int[1];
            moznosti[0] = octet;
        }
        return moznosti;
    }

    /*
    Metoda ktora zistuje vsetky moznosti v oktete
    octet - oktet
    velkost - kolko moznosti bude mat tento oktet, podla toho sa vytvori velkost pola
    border - border
    border2 - border2
    od - znamena od akeho indexu sa zacinaju menit 1 v binarnom oktete
    do - znamena do akeho indexu sa menia 1 v binarnom oktete
    c - sposob menenia jednotiek
     */
    private static int[] dajMoznosti(int octet,int velkost, int border, int border2, int od, int doK, boolean c)
    {
        Prevody prevody = new Prevody();
        int[] moznosti = new int[velkost];              // ulozenie vsetkych moznosti
        int[] binOctet = prevody.fromDecToBin8(octet);
        for(int i = od; i < doK; i++)
        {
            if (c)
            {
                if(i >= border && i < border2)
                {
                    binOctet[i] = 1;
                }
            }
            else {
                binOctet[i] = 1;
            }

        }
        //postupnost zistim najmensi a najvecsi mozny oktet a vypocita kolko sa pripocitava k najmensiemu
        int minOctet = octet;
        int maxOctet = prevody.fromOtherToDec(binOctet,2);
        int n = moznosti.length;
        int d = (maxOctet - minOctet) / (n - 1);
        moznosti[0] = minOctet;
        for(int a = 1; a < velkost; a++)
        {
            moznosti[a] = moznosti[a - 1] + d;
        }
        //vrati vsetky moznosti
        return moznosti;
    }

    // metoda vracia intove pole s adresou
    private static int[] getIntAddress(String adresa) throws ZlaDlzkaAMWException, ZlyOctetException
    {
        String[] inout = adresa.split("\\.");
        int[] address = new int[4];

        // zisti ci adresa ma 4 octety
        if (inout.length != 4)
        {
            ZlaDlzkaAMWException a = new ZlaDlzkaAMWException();
            throw a;
        }

        //zisti ci octety obsahuju iba cisla
        for(int i = 0; i < inout.length; i++ )
        {
            try
            {
                address[i] = Integer.parseInt(inout[i]);
            }
            catch (NumberFormatException e)
            {
                throw e;
            }
        }

        // zisti ci oktet je cislo 0-255
        for(int q = 0; q < address.length; q++)
        {
            if(address[q] < 0 )
            {
                ZlyOctetException a = new ZlyOctetException();
                throw a;
            }
            if (address[q] > 255)
            {
                ZlyOctetException a = new ZlyOctetException();
                throw a;
            }
        }
        return address;
    }
}


