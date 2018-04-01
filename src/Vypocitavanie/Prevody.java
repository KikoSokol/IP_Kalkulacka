package Vypocitavanie;

public class Prevody
{
    /*
    Mentoda na prevod z iných sústavo do desiatkovej sústavy
    endnumerals: 2 - z binarnej
                 8 - z osmickovej
                 16 - zo sestnastkovej
     */
    public static int fromOtherToDec(int[] numerals,int startnumerals)
    {
        int dec = 0;                                // vysledok v desiatkovej sústave
        int index = 0;								// prvok v poli
        for(int i = numerals.length - 1; i >= 0; i--)
        {
            if(numerals[i] != 0)
            {
                dec += exponentiation(startnumerals,index) * numerals[i];
                index++;
            }
            else
                index++;
        }
        return dec;
    }

    /*
    Mentoda na prevod z iných sústavo do desiatkovej sústavy
    endnumerals: 2 - z binarnej
                 8 - z osmickovej
                 16 - zo sestnastkovej
    metoda sa pouziva v sustavach a vracia long
     */
    public static long fromOtherToDecLong(int[] numerals,int startnumerals)
    {
        long dec = 0;                                // vysledok v desiatkovej sústave
        int index = 0;								// prvok v poli
        for(int i = numerals.length - 1; i >= 0; i--)
        {
            if(numerals[i] != 0)
            {
                dec += exponentiationLong((long)startnumerals,(long) index) *(long) numerals[i];
                index++;
            }
            else
                index++;
        }
        return dec;
    }


    /*
    Mentoda na prevod z desiatkovej sústavy do iných sústav
    endnumerals: 2 - do binarnej
                 8 - do osmickovej
                 16 - do sestnastkovej
     */
    public static int[] fromDecToOther(int dec,int endnumerals)
    {
        int bits = bits(dec,endnumerals);       //premenná ktorá uklada počet bitov danej spústavy
        int[] finall = new int[bits];           // pole do ktorého sa ukladá sústava
        int rest = dec;
        for(int index = bits - 1;index >= 0;index--)	// delenia číslom endnumerals
        {
            finall[index] = rest %endnumerals;
            rest = rest / endnumerals;
        }
        return finall;
    }

    /*
   Mentoda na prevod z desiatkovej sústavy do iných sústav
   endnumerals: 2 - do binarnej
                8 - do osmickovej
                16 - do sestnastkovej
   metoda sa pouziva v sustavach na vstupe je long
    */
    public static int[] fromDecToOtherLong(long dec,int endnumerals)
    {
        int bits = bitsLong(dec,(long) endnumerals);       //premenná ktorá uklada počet bitov danej spústavy
        int[] finall = new int[bits];           // pole do ktorého sa ukladá sústava
        long rest = dec;
        for(int index = bits - 1;index >= 0;index--)	// delenia číslom endnumerals
        {
            finall[index] =(int) (rest %endnumerals);
            rest = rest / endnumerals;
        }
        return finall;
    }
    // metoda ktorá zisťuje aké veľké pole sa ma vytvoriť pre danu cislnu sustavu na vstupe je long
    public static int bitsLong(long number,long numerals)
    {
        if (number == 0)		// if dec = 0 binary is had 1 number-> 0
            return 1;
        for(int index = 0;;index++)
        {
            long f = exponentiationLong(numerals,(long)index);
            if(number /  f == 0)
            {
                return index;
            }
        }
    }
    // metoda na umocnovanie vracia long
    public static long exponentiationLong(long base,long exponent)
    {
        long out = 0;
        for(int i = 0; i <= exponent; i++)
        {
            if( i == 0)
                out = 1;
            else
                out = out * base;
        }
        return out;
    }


    /*
    *  specialna metoda na predov z desiatkovej sústaavy do binarnej
    *  metoda vracia binarne pole o velkosti 8 prvkov
    */
    public static int[] fromDecToBin8(int dec)
    {
        int[] bin = fromDecToOther(dec,2);
        int[] out = new int[8];
        int read = 0;
        for(int i = 8 - bin.length; i < out.length; i++)
        {
            out[i] = bin[read];
            read++;
        }
        return out;
    }


    // metoda ktorá zisťuje aké veľké pole sa ma vytvoriť pre danu cislnu sustavu
    public static int bits(int number,int numerals)
    {
        if (number == 0)		// if dec = 0 binary is had 1 number-> 0
            return 1;
        for(int index = 0;;index++)
        {
            int f = exponentiation(numerals,index);
            if(number / f == 0)
            {
                return index;
            }
        }
    }


    // metoda na umocnovanie
    public static int exponentiation(int base, int exponent)
    {
        int out = 0;
        for(int i = 0; i <= exponent; i++)
        {
            if( i == 0)
                out = 1;
            else
                out = out * base;
        }
        return out;
    }

    //Metoda na hladanie najblizsej mocniny cisla 2
    public static int najblizsiaOdmocnina2(long cislo)
    {
        int a = 0;
        while(true)
        {
            if(Math.pow(2,a) >= cislo)
                return a;
            else
                a++;
        }
    }
}
