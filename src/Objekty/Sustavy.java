package Objekty;

import Exceptions.neexistujucaSustavaException;
import Vypocitavanie.Prevody;

public class Sustavy
{
    private long desiatkova;
    private int[] binarna;
    private int[] osmickova;
    private int[] sestnastkova;
    private Prevody prevody;

    public Sustavy(long dec)
    {
        prevody = new Prevody();

        this.desiatkova = dec;
        this.binarna = prevody.fromDecToOtherLong(dec, 2);
        this.osmickova = prevody.fromDecToOtherLong(dec, 8);
        this.sestnastkova = prevody.fromDecToOtherLong(dec, 16);
    }

    public Sustavy(String sustava, int start) throws neexistujucaSustavaException
    // start: 2-binarna 8-osmickova 16 sestnaskova
    {
        prevody = new Prevody();
        int[] s = new int[sustava.length()];

        //start = 2
        if(start == 2)
        {
            for(int a = 0; a < sustava.length();a++)
            {
                char n = sustava.charAt(a);
                int q = Integer.parseInt(Character.toString(n));
                if (q > 1)
                {
                    neexistujucaSustavaException e = new neexistujucaSustavaException();
                    throw e;
                }
                if (q < 0)
                {
                    neexistujucaSustavaException e = new neexistujucaSustavaException();
                    throw e;
                }
                if(n == ' ')
                {
                    neexistujucaSustavaException e = new neexistujucaSustavaException();
                    throw e;
                }
            }

            for(int i = 0; i < sustava.length(); i++)
            {
                s[i] = Integer.parseInt(Character.toString(sustava.charAt(i)));
            }
        }
        //start = 8
        if(start == 8)
        {
            for(int a = 0; a < sustava.length();a++)
            {
                char n = sustava.charAt(a);
                int q = Integer.parseInt(Character.toString(n));
                if (q > 7)
                {
                    neexistujucaSustavaException e = new neexistujucaSustavaException();
                    throw e;
                }
                if (q < 0)
                {
                    neexistujucaSustavaException e = new neexistujucaSustavaException();
                    throw e;
                }
            }

            for(int i = 0; i < sustava.length(); i++)
            {
                s[i] = Integer.parseInt(Character.toString(sustava.charAt(i)));
            }
        }



            for(int i = 0; i < sustava.length(); i++)
            {
                    if(sustava.charAt(i) == '0')
                        s[i] = 0;
                    if(sustava.charAt(i) == '1')
                        s[i] = 1;
                    if(sustava.charAt(i) == '2')
                        s[i] = 2;
                    if(sustava.charAt(i) == '3')
                        s[i] = 3;
                    if(sustava.charAt(i) == '4')
                        s[i] = 4;
                    if(sustava.charAt(i) == '5')
                        s[i] = 5;
                    if(sustava.charAt(i) == '6')
                        s[i] = 6;
                    if(sustava.charAt(i) == '7')
                        s[i] = 7;
                    if(sustava.charAt(i) == '8')
                        s[i] = 8;
                    if(sustava.charAt(i) == '9')
                        s[i] = 9;
                    if(sustava.charAt(i) == 'A')
                        s[i] = 10;
                    if(sustava.charAt(i) == 'B')
                        s[i] = 11;
                    if(sustava.charAt(i) == 'C')
                        s[i] = 12;
                    if(sustava.charAt(i) == 'D')
                        s[i] = 13;
                    if(sustava.charAt(i) == 'E')
                        s[i] = 14;
                    if(sustava.charAt(i) == 'F')
                        s[i] = 15;
                    if(sustava.charAt(i) == 'a')
                        s[i] = 10;
                    if(sustava.charAt(i) == 'b')
                        s[i] = 11;
                    if(sustava.charAt(i) == 'c')
                        s[i] = 12;
                    if(sustava.charAt(i) == 'd')
                        s[i] = 13;
                    if(sustava.charAt(i) == 'e')
                        s[i] = 14;
                    if(sustava.charAt(i) == 'f')
                        s[i] = 15;
            }

            //ak bude sestnastkova prilis velke cislo - v desiatkovej vecsie ako long
            if ((start == 16 && s.length > 15) || (start == 8 && s.length > 15) || (start == 2 &&  s.length > 60))
            {
                neexistujucaSustavaException e = new neexistujucaSustavaException();
                throw e;
            }



        this.desiatkova = prevody.fromOtherToDecLong(s, start);
//        this.binarna = prevody.fromDecToOtherLong(prevody.fromOtherToDec(s, start), 2);
//        this.osmickova = prevody.fromDecToOtherLong(prevody.fromOtherToDec(s, start), 8);
//        this.sestnastkova = prevody.fromDecToOtherLong(prevody.fromOtherToDec(s, start), 16);

        this.binarna = prevody.fromDecToOtherLong(desiatkova, 2);
        this.osmickova = prevody.fromDecToOtherLong(desiatkova, 8);
        this.sestnastkova = prevody.fromDecToOtherLong(desiatkova, 16);
    }

    /*
    Getre
     */

    public String getDesiatkova() {
        return "" + desiatkova;
    }

    public String getBinarna() {
        String sBinarna = "";
        for(int i = 0; i < binarna.length; i++)
        {
            sBinarna = sBinarna + this.binarna[i];

        }
        return sBinarna;
    }

    public String getOsmickova() {
        String sOsmickova = "";
        for(int i = 0; i < osmickova.length; i++)
        {
            sOsmickova = sOsmickova + this.osmickova[i];

        }
        return sOsmickova;
    }

    public String getSestnastkova() {
        String sSestnastkova = "";

        for (int i = 0; i < sestnastkova.length; i++)
        {
            if (sestnastkova[i] < 10)
                sSestnastkova = sSestnastkova + this.sestnastkova[i];
            if (sestnastkova[i] == 10)
                sSestnastkova = sSestnastkova + "A";
            if (sestnastkova[i] == 11)
                sSestnastkova =sSestnastkova + "B";
            if (sestnastkova[i] == 12)
                sSestnastkova = sSestnastkova + "C";
            if (sestnastkova[i] == 13)
                sSestnastkova = sSestnastkova + "D";
            if (sestnastkova[i] == 14)
                sSestnastkova = sSestnastkova + "E";
            if (sestnastkova[i] == 15)
                sSestnastkova = sSestnastkova + "F";
        }
        return sSestnastkova;
    }
}

