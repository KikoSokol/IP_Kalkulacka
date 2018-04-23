package Objekty;

import Exceptions.ZlaDlzkaAMWException;
import Exceptions.ZlaMaskaException;
import Exceptions.ZlyPrefixException;
import Exceptions.ZlyWildcardException;
import Vypocitavanie.Prevody;

public class Pmw
{
    private int prefix;
    private int[] maska;
    private int[] wildcard;
    private static Prevody prevody;

    public Pmw(int prefix) throws ZlyPrefixException
    {
        prevody = new Prevody();
        this.prefix = getPrefix(prefix);
        this.maska = fromPrefixToMask(prefix);
        this.wildcard = fromMaskToWildcard(this.maska);
    }

    public Pmw(String maska) throws ZlaMaskaException, ZlaDlzkaAMWException {
        this.maska = getIntMaska(maska);
        this.wildcard = fromMaskToWildcard(this.maska);
        this.prefix = fromMaskToPrefix(this.maska);
    }

    public Pmw(String wildcard, boolean a) throws ZlaDlzkaAMWException, ZlyWildcardException // a = true > wildcard   a = false > maska
    {
        this.wildcard = getIntWildcard(wildcard);
        this.maska = fromWildcardToMask(this.wildcard);
        this.prefix = fromWildcardToPrefix(this.wildcard);
    }

    /*
    GETRE
     */

    public String getPrefix() {
        return "" + this.prefix;
    }

    public String getMaska() {
        String sMaska = "";
        for(int i = 0; i < 4; i++)
        {
            sMaska = sMaska + this.maska[i];
            if (i != 3)
                sMaska = sMaska + ".";
        }
        return sMaska;
    }

    public String getWildcard() {
        String sWildcard = "";
        for(int i = 0; i < 4; i++)
        {
            sWildcard = sWildcard + this.wildcard[i];
            if (i != 3)
                sWildcard = sWildcard + ".";
        }
        return sWildcard;
    }

    /*
        Metody na pocitanie prefixu, masky, wildcardu
    */

    // metoda na prevod prefixu do masky
    private static int[] fromPrefixToMask(int prefix)
    {
        int[] mask = new int[4];  			// pole do ktorej sa uklada vysledna maska
        int full = prefix / 8;				// zisťuje kolko oktetov bude 255
        for(int i = 0; i < full;i++)
        {
            mask[i] = 255;
        }
        if(full < 4)
        {
            prefix = prefix - (full*8);		// zvisny prefix
            int[] activeOctet = new int[8];	// pole do ktoreho sa ukladaju zvisne jednotky prefixu
            for(int i = 0; i < prefix;i++)
            {
                activeOctet[i] = 1;
            }
            mask[full] = prevody.fromOtherToDec(activeOctet,2);
        }

        return mask;
    }
    // metoda ne prevod masky na prefix
    private static int fromMaskToPrefix(int[] mask)
    {
        int prefix = 0;                     			// vysledny prefix
        for(int i = 0; i < mask.length;i++)				// prehladava oktety masky
        {
            int[] pole = prevody.fromDecToOther(mask[i],2);			// oktet v maske sa prevedie do binarnej sustavy
            for(int a = 0; a < pole.length;a++)			// cyklus zisťuje kolko jednotiek je v oktete
            {
                if(pole[a] == 1)
                    prefix++;
                else									// ak sa zisti prva nula tak metoda vrati vysledný prefix
                    return prefix;
            }
        }
        return prefix;
    }

    //VYMAZ
//    // metoda na prevod prefixu na wildcard
//    public static int[] fromPrefixToWildcard(int prefix)
//    {
//        int[] mask = fromPrefixToMask(prefix);         // prefix prevedie na masku
//        int[] Wildcard = fromMaskToWildcard(mask);     // maska sa prevedie ma wildcard
//        return Wildcard;
//    }


    // metoda na predod masky na wildcard
    private static int[] fromMaskToWildcard(int[] mask)
    {
        int[] Wildcard = new int[4];      				// pole kde bude ulozeny wildcard
        for(int index = 0; index < Wildcard.length;index++)
        {
            Wildcard[index] = 255 - mask[index];
        }
        return Wildcard;
    }
    // metoda na prevod wildcardu na masku
    private static int[] fromWildcardToMask(int[] wildcard)
    {
        int[] mask = new int[4];            			// pole kde bude ulozena maska

        for(int i = 0; i < mask.length;i++)
        {
            mask[i] = 255 - wildcard[i];
        }
        return mask;
    }
    // metoda na prevod wildcardu na prefix
    private static int fromWildcardToPrefix(int[] wildcard)
    {
        int prefix = fromMaskToPrefix(fromWildcardToMask(wildcard)); // wildcard bude prevedeny na masku a nasledne sa maska prevedie na prefix
        return prefix;
    }



    // Metoda vrati int pole s maskou
    private int[] getIntMaska(String maska) throws ZlaDlzkaAMWException, ZlaMaskaException {
        String[] inout = maska.split("\\.");

        int[] intMaska = new int[4];

        //zisti ci ma maska 4 oktety
        if (inout.length != 4)
        {
            ZlaDlzkaAMWException a = new ZlaDlzkaAMWException();
            throw a;
        }

        // zisti ci ma maska iba cisla
        for(int i = 0; i < inout.length; i++ )
        {
            try
            {
                intMaska[i] = Integer.parseInt(inout[i]);
            }
            catch (NumberFormatException e)
            {
                throw e;
            }
        }
        if(intMaska[0] == 255 && intMaska[1] == 255 && intMaska[2] == 255 && intMaska[3] == 255)
            return intMaska;

        //zisti v ktory oktet je mensi ako 255
        int tmp = 0;
        for(;tmp < intMaska.length; tmp++)
        {

            if(intMaska[tmp] == 255)
            {
                continue;
            }
            else if (intMaska[tmp] < 255 || intMaska[tmp] > 0)
            {
                break;
            }

        }



        //zisti ci za mensim oktetom ako 255 sa nachadzaju uz iba same 0
        int q = tmp+1;
        for(; q < intMaska.length; q++)
        {

            if(intMaska[q] != 0)
            {
                ZlaMaskaException b = new ZlaMaskaException();
                throw b;
            }
        }

        //zisti ci existuje taka maska
        if(intMaska[tmp] != 0 && intMaska[tmp] != 128 && intMaska[tmp] != 192 && intMaska[tmp] != 224 && intMaska[tmp] != 240 && intMaska[tmp] != 248 && intMaska[tmp] != 252 && intMaska[tmp] != 254 && intMaska[tmp] != 255)
        {
            ZlaMaskaException c = new ZlaMaskaException();
            throw c;
        }

        return intMaska;
    }


    // Metoda vrati int pole s maskou
    private int[] getIntWildcard(String wildcard) throws ZlaDlzkaAMWException, ZlyWildcardException {
        String[] inout = wildcard.split("\\.");

        int[] intWildcard = new int[4];

        //zisti ci ma wildcard 4 oktety
        if (inout.length != 4)
        {
            ZlaDlzkaAMWException a = new ZlaDlzkaAMWException();
            throw a;
        }

        // zisti ci ma wildcard iba cisla
        for(int i = 0; i < inout.length; i++ )
        {
            try
            {
                intWildcard[i] = Integer.parseInt(inout[i]);
            }
            catch (NumberFormatException e)
            {
                throw e;
            }
        }

        //zisti v ktory oktet je mensi ako 255
        int tmp = intWildcard.length - 1;
        for(; tmp >= 0 ; tmp--)
        {

            if(intWildcard[tmp] == 255)
            {
                continue;
            }
            else if (intWildcard[tmp] < 255 || intWildcard[tmp] > 0)
            {
                break;
            }

        }



        //zisti ci za mensim oktetom ako 255 sa nachadzaju uz iba same 0
        int q = tmp-1;
        for(; q >= 0; q--)
        {

            if(intWildcard[q] != 0)
            {
                ZlyWildcardException b = new ZlyWildcardException();
                throw b;
            }
        }
        //zisti ci existuje taka maska
        if(intWildcard[tmp] != 0 && intWildcard[tmp] != 1 && intWildcard[tmp] != 3 && intWildcard[tmp] != 7 && intWildcard[tmp] != 15 && intWildcard[tmp] != 31 && intWildcard[tmp] != 63 && intWildcard[tmp] != 127 && intWildcard[tmp] != 255)
        {
            ZlyWildcardException c = new ZlyWildcardException();
            throw c;
        }

        return intWildcard;
    }

    //metoda ktora vrati prefix a zisti ci taky prefix existuje
    private int getPrefix(int prefix) throws ZlyPrefixException
    {
        if (prefix < 0 || prefix > 32)
        {
            ZlyPrefixException e = new ZlyPrefixException();
            throw e;
        }
        return prefix;
    }
}
