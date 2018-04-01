package Objekty;

import Exceptions.*;
import Vypocitavanie.Prevody;

import java.io.IOException;

public class Siet
{
    private String typZadanejAdresy;
    private int[] sietovaAdresa;
    private int[] broadcastovaAdresa;
    private int prefix;
    private int[] maska;
    private int[] wildcard;
    private String trieda;
    private long poradie;
    private int[] prvaPouzitelnaAdresa;
    private int[] poslednaPouzitelnaAdresa;
    private long pocetAdries;
    private long pocetPouzitelnychAdries;
    private static Prevody prevody;
    private boolean specialnaHostova = false;

    //konstruktor1
    public Siet(String siet, int prefix) throws zlaDlzkaAMWException, zlyOctetException, IOException, zlyPrefixException
    {
        prevody = new Prevody();
        int[] zadanaAdresa = getIntAddress(siet);
        this.prefix = getPrefix(prefix);
        this.sietovaAdresa = getNetOrBroAddress(zadanaAdresa,this.prefix,true);
        this.broadcastovaAdresa = getNetOrBroAddress(zadanaAdresa, this.prefix, false);
        this.maska = fromPrefixToMask(this.prefix);
        this.wildcard = fromMaskToWildcard(this.maska);
        this.trieda = setTrieda(zadanaAdresa);
        this.poradie = poradie(zadanaAdresa, prefix);
        this.prvaPouzitelnaAdresa = getFirstOrLastAddress(zadanaAdresa, this.prefix,true);
        this.poslednaPouzitelnaAdresa = getFirstOrLastAddress(zadanaAdresa, this.prefix, false);
        this.pocetAdries = getSpaceAddress(this.prefix);
        this.pocetPouzitelnychAdries = this.pocetAdries - 2;

        if(zadanaAdresa[0] == sietovaAdresa[0] && zadanaAdresa[1] == sietovaAdresa[1] && zadanaAdresa[2] == sietovaAdresa[2] && zadanaAdresa[3] == sietovaAdresa[3] )
            typZadanejAdresy = "Sieťová adresa";
        else if (zadanaAdresa[0] == broadcastovaAdresa[0] && zadanaAdresa[1] == broadcastovaAdresa[1] && zadanaAdresa[2] == broadcastovaAdresa[2] && zadanaAdresa[3] == broadcastovaAdresa[3] )
            typZadanejAdresy = "Broadcastová adresa";
        else
            typZadanejAdresy = "Hostová adresa";

        if(this.prefix == 32)
        {
            this.specialnaHostova = true;
            this.poradie = 1;
            this.pocetAdries = 1;
            this.pocetPouzitelnychAdries = 1;
            typZadanejAdresy = "Hostová adresa";
        }

    }

    public Siet(String siet, String maska) throws zlaDlzkaAMWException, zlaDlzkaMasky, zlyOctetException, IOException, zlaMaskaException
    {
        prevody = new Prevody();
        int[] zadanaAdresa = getIntAddress(siet);
        this.maska = getIntMaska(maska);
        this.prefix = fromMaskToPrefix(this.maska);
        this.sietovaAdresa = getNetOrBroAddress(zadanaAdresa,this.prefix,true);
        this.broadcastovaAdresa = getNetOrBroAddress(zadanaAdresa, this.prefix, false);
        this.wildcard = fromMaskToWildcard(this.maska);
        this.trieda = setTrieda(zadanaAdresa);
        this.poradie = poradie(zadanaAdresa, prefix);
        this.prvaPouzitelnaAdresa = getFirstOrLastAddress(zadanaAdresa, this.prefix,true);
        this.poslednaPouzitelnaAdresa = getFirstOrLastAddress(zadanaAdresa, this.prefix, false);
        this.pocetAdries = getSpaceAddress(this.prefix);
        this.pocetPouzitelnychAdries = this.pocetAdries - 2;
        if(zadanaAdresa[0] == sietovaAdresa[0] && zadanaAdresa[1] == sietovaAdresa[1] && zadanaAdresa[2] == sietovaAdresa[2] && zadanaAdresa[3] == sietovaAdresa[3] )
            typZadanejAdresy = "Sieťová adresa";
        else if (zadanaAdresa[0] == broadcastovaAdresa[0] && zadanaAdresa[1] == broadcastovaAdresa[1] && zadanaAdresa[2] == broadcastovaAdresa[2] && zadanaAdresa[3] == broadcastovaAdresa[3] )
            typZadanejAdresy = "Broadcastová adresa";
        else
            typZadanejAdresy = "Hostová adresa";

        if(this.prefix == 32)
        {
            this.specialnaHostova = true;
            this.poradie = 1;
            this.pocetAdries = 1;
            this.pocetPouzitelnychAdries = 1;
            typZadanejAdresy = "Hostová adresa";
        }

    }

    public Siet(int[] siet, int prefix) throws zlaDlzkaAMWException, zlyOctetException, zlyPrefixException
    {
        prevody = new Prevody();
        int[] zadanaAdresa = siet;
        this.prefix = getPrefix(prefix);
        this.sietovaAdresa = getNetOrBroAddress(zadanaAdresa,this.prefix,true);
        this.broadcastovaAdresa = getNetOrBroAddress(zadanaAdresa, this.prefix, false);
        this.maska = fromPrefixToMask(this.prefix);
        this.wildcard = fromMaskToWildcard(this.maska);
        this.trieda = setTrieda(zadanaAdresa);
        this.poradie = poradie(zadanaAdresa, prefix);
        this.prvaPouzitelnaAdresa = getFirstOrLastAddress(zadanaAdresa, this.prefix,true);
        this.poslednaPouzitelnaAdresa = getFirstOrLastAddress(zadanaAdresa, this.prefix, false);
        this.pocetAdries = getSpaceAddress(this.prefix);
        this.pocetPouzitelnychAdries = this.pocetAdries - 2;

        if(zadanaAdresa[0] == sietovaAdresa[0] && zadanaAdresa[1] == sietovaAdresa[1] && zadanaAdresa[2] == sietovaAdresa[2] && zadanaAdresa[3] == sietovaAdresa[3] )
            typZadanejAdresy = "Sieťová adresa";
        else if (zadanaAdresa[0] == broadcastovaAdresa[0] && zadanaAdresa[1] == broadcastovaAdresa[1] && zadanaAdresa[2] == broadcastovaAdresa[2] && zadanaAdresa[3] == broadcastovaAdresa[3] )
            typZadanejAdresy = "Broadcastová adresa";
        else
            typZadanejAdresy = "Hostová adresa";

        if(this.prefix == 32)
        {
            this.specialnaHostova = true;
            this.poradie = 1;
            this.pocetAdries = 1;
            this.pocetPouzitelnychAdries = 1;
            typZadanejAdresy = "Hostová adresa";
        }

    }

    /*
    GETERY
     */
    public String getTypZadanejAdresy()
    {
        return typZadanejAdresy;
    }

    public String getSietovaAdresa() {
        String ssietovaAdresa = "";
        if(!this.specialnaHostova)
        {
            for(int i = 0; i < 4; i++)
            {
                ssietovaAdresa = ssietovaAdresa + this.sietovaAdresa[i];
                if (i != 3)
                    ssietovaAdresa = ssietovaAdresa + ".";
            }
        }
        return ssietovaAdresa;
    }

    public String getSietovaAdresaForTable() {
        String ssietovaAdresa = "";
            for(int i = 0; i < 4; i++)
            {
                ssietovaAdresa = ssietovaAdresa + this.sietovaAdresa[i];
                if (i != 3)
                    ssietovaAdresa = ssietovaAdresa + ".";
            }
        return ssietovaAdresa;
    }

    public String getBroadcastovaAdresa() {
        String sBroadcastovaAdresa = "";
        if(!this.specialnaHostova)
        {
            for(int i = 0; i < 4; i++)
            {
                sBroadcastovaAdresa = sBroadcastovaAdresa + this.broadcastovaAdresa[i];
                if (i != 3)
                    sBroadcastovaAdresa = sBroadcastovaAdresa + ".";
            }
        }
        return sBroadcastovaAdresa;
    }

    public String getBroadcastovaAdresaForTable() {
        String sBroadcastovaAdresa = "";
            for(int i = 0; i < 4; i++)
            {
                sBroadcastovaAdresa = sBroadcastovaAdresa + this.broadcastovaAdresa[i];
                if (i != 3)
                    sBroadcastovaAdresa = sBroadcastovaAdresa + ".";
            }
        return sBroadcastovaAdresa;
    }

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

    public String getTrieda() {
        return trieda;
    }

    public String getPoradie() {
        return "" + poradie;
    }

    public String getPrvaPouzitelnaAdresa() {
        String sPrvaPouzitelnaAdresa = "";
        for(int i = 0; i < 4; i++)
        {
            sPrvaPouzitelnaAdresa = sPrvaPouzitelnaAdresa + this.prvaPouzitelnaAdresa[i];
            if (i != 3)
                sPrvaPouzitelnaAdresa = sPrvaPouzitelnaAdresa + ".";
        }
        return sPrvaPouzitelnaAdresa;
    }

    public String getPoslednaPouzitelnaAdresa() {
        String sPoslednaPouzitelnaAdresa = "";
        for(int i = 0; i < 4; i++)
        {
            sPoslednaPouzitelnaAdresa = sPoslednaPouzitelnaAdresa + this.poslednaPouzitelnaAdresa[i];
            if (i != 3)
                sPoslednaPouzitelnaAdresa = sPoslednaPouzitelnaAdresa + ".";
        }
        return sPoslednaPouzitelnaAdresa;
    }

    public String getRozsah()
    {
        if(this.pocetPouzitelnychAdries == 0)
            return "-";
        else
        {
            return getPrvaPouzitelnaAdresa().concat(" - ").concat(getPoslednaPouzitelnaAdresa());
        }


    }

    public String getPocetAdries() {
        return "" + pocetAdries;
    }

    public String getPocetPouzitelnychAdries() {
        return "" + pocetPouzitelnychAdries;
    }


    //metoda na vratenie stringovej adresy
    /*
    co:
    0 - sietova adresa
    1 - broadcastova adresa
    2 - maska
    3 - wildcard
     */
    public String getBinarySieBcPMWAdress(int co)
    {
        // konecna binarna adresa
        String binaryAddress;

        // binarne octety
        int[] octet1 = null;
        int[] octet2 = null;
        int[] octet3 = null;
        int[] octet4 = null;

        if (co == 0)
        {
             octet1 = prevody.fromDecToBin8(this.sietovaAdresa[0]);
             octet2 = prevody.fromDecToBin8(this.sietovaAdresa[1]);
             octet3 = prevody.fromDecToBin8(this.sietovaAdresa[2]);
             octet4 = prevody.fromDecToBin8(this.sietovaAdresa[3]);
        }

        if (co == 1)
        {
            octet1 = prevody.fromDecToBin8(this.broadcastovaAdresa[0]);
            octet2 = prevody.fromDecToBin8(this.broadcastovaAdresa[1]);
            octet3 = prevody.fromDecToBin8(this.broadcastovaAdresa[2]);
            octet4 = prevody.fromDecToBin8(this.broadcastovaAdresa[3]);
        }

        if (co == 2)
        {
            octet1 = prevody.fromDecToBin8(this.maska[0]);
            octet2 = prevody.fromDecToBin8(this.maska[1]);
            octet3 = prevody.fromDecToBin8(this.maska[2]);
            octet4 = prevody.fromDecToBin8(this.maska[3]);
        }

        if (co == 3)
        {
            octet1 = prevody.fromDecToBin8(this.wildcard[0]);
            octet2 = prevody.fromDecToBin8(this.wildcard[1]);
            octet3 = prevody.fromDecToBin8(this.wildcard[2]);
            octet4 = prevody.fromDecToBin8(this.wildcard[3]);
        }


        String o1 = "";     // binarny stringovy prvy octet
        String o2 = "";     // binarny stringovy druhy octet
        String o3 = "";     // binarny stringovy treti octet
        String o4 = "";     // binarny stringovy stvrty octet

        // binarny prvy octet
        for (int i = 0; i < octet1.length; i++)
        {
            o1 = o1.concat("" + octet1[i]);
        }

        // binarny druhy octet
        for (int i = 0; i < octet2.length; i++)
        {
            o2 = o2.concat("" + octet2[i]);
        }

        // binarny treti octet
        for (int i = 0; i < octet3.length; i++)
        {
            o3 = o3.concat("" + octet3[i]);
        }

        // binarny stvrty octet
        for (int i = 0; i < octet4.length; i++)
        {
            o4 = o4.concat("" + octet4[i]);
        }

        binaryAddress = o1.concat("." + o2).concat("." + o3).concat("." + o4);

        if(this.specialnaHostova && co != 3)
            return "";

        return binaryAddress;

    }

    /*
    Metody na vypocitanie vlastnosti siete
     */

    // metoda ktora vrati binarnu alebo broadcastovu adresu
    private static int[] getNetOrBroAddress(int[] address,int prefix,boolean net)	// net == true -> getNet  if net == false -> getBro
    {
        if(prefix == 32)											// ak prefix = 32 metoda vrati tu istu adresu pretoze nenastanu ziadne zmeny
            return address;

        int[] octet1 = prevody.fromDecToBin8(address[0]);
        int[] octet2 = prevody.fromDecToBin8(address[1]);
        int[] octet3 = prevody.fromDecToBin8(address[2]);
        int[] octet4 = prevody.fromDecToBin8(address[3]);
        int fromThisPrefix = prefix / 8 + 1; 						// zisti v ktorom oktete sa zacinaju menit 0/1
        int border = prefix - (prefix / 8)*8;						// hranica kde sa zacne menit 0/1

        changeOctet(octet1, fromThisPrefix, 1, border, net);		// zmeni octet1
        changeOctet(octet2, fromThisPrefix, 2, border, net);		// zmeni octet2
        changeOctet(octet3, fromThisPrefix, 3, border, net);		// zmeni octet3
        changeOctet(octet4, fromThisPrefix, 4, border, net);		// zmeni octet4

        int[] outAddress = new int[4];								// pole kde sa uklada vysledna adresa
        outAddress[0] = prevody.fromOtherToDec(octet1,2);
        outAddress[1] = prevody.fromOtherToDec(octet2,2);
        outAddress[2] = prevody.fromOtherToDec(octet3,2);
        outAddress[3] = prevody.fromOtherToDec(octet4,2);
        return outAddress;
    }


    // metoda meni oktet v metode getNetOrBroAddress
    private static void changeOctet(int[] octet,int fromThisPrefix,int numOctet,int border,boolean net)	//octet,fromThisPrefix,number octet,border,net
    {
        if(fromThisPrefix <= numOctet)			// numOctet - cislo oktetu ktory sa bude menit
        {
            border = (fromThisPrefix == numOctet) ? border : 0;
            for(int i = border;i < 8;i++)
            {
                octet[i] = (net) ? 0 : 1;		// change 1/0    - if true cisla sa zmenia na 0  if false cisla sa zmenia na 1
            }
        }
    }


    // metoda na prevod prefixu do masky
    public static int[] fromPrefixToMask(int prefix)
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
    public static int fromMaskToPrefix(int[] mask)
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


    // metoda na predod masky na wildcard
    public static int[] fromMaskToWildcard(int[] mask)
    {
        int[] Wildcard = new int[4];      				// pole kde bude ulozeny wildcard
        for(int index = 0; index < Wildcard.length;index++)
        {
            Wildcard[index] = 255 - mask[index];
        }
        return Wildcard;
    }


    // metoda ktora zisti velkost siete / pocet adries
    public static long getSpaceAddress(int prefix)
    {
        long spaceAddress;
        spaceAddress = prevody.exponentiationLong((long)2,(long)32 - prefix);
        return spaceAddress;
    }


    // metoda ktora najde prvu alebo poslednu pouzitelnu adresu v sieti
    public static int[] getFirstOrLastAddress(int[] address,int prefix,boolean or)	//ak or == true -> getFirst ak or == false -> getLast
    {
        int[] net = getNetOrBroAddress(address,prefix,true);     	// pole pre sietovu adresu
        int[] bro = getNetOrBroAddress(address,prefix,false);		// pole pre broadcastovu adresu

        net[3]++;
        bro[3]--;

        return (or) ? net : bro;
    }


    // metoda ktora vrati int pole s adresou
    public static int[] getIntAddress(String adresa) throws zlaDlzkaAMWException, zlyOctetException, IOException {
        String[] inout = adresa.split("\\.");
        int[] address = new int[4];

        // zisti ci adresa ma 4 octety
        if (inout.length != 4)
        {
            zlaDlzkaAMWException a = new zlaDlzkaAMWException();
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
                zlyOctetException a = new zlyOctetException();
                throw a;
            }
            if (address[q] > 255)
            {
                zlyOctetException a = new zlyOctetException();
                throw a;
            }
        }
        return address;
    }

    // Metoda vrati int pole s maskou
    public int[] getIntMaska(String maska) throws zlaDlzkaMasky, zlaMaskaException {
        String[] inout = maska.split("\\.");

        int[] intMaska = new int[4];

        //zisti ci ma maska 4 oktety
        if (inout.length != 4)
        {
            zlaDlzkaMasky a = new zlaDlzkaMasky();
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

                zlaMaskaException b = new zlaMaskaException();
                throw b;
            }
        }

        //zisti ci existuje taka maska
        if(intMaska[tmp] != 0 && intMaska[tmp] != 128 && intMaska[tmp] != 192 && intMaska[tmp] != 224 && intMaska[tmp] != 240 && intMaska[tmp] != 248 && intMaska[tmp] != 252 && intMaska[tmp] != 254 && intMaska[tmp] != 255)
        {
            zlaMaskaException c = new zlaMaskaException();
            throw c;
        }

        return intMaska;
    }


    //metoda ktora zistuje ci existuje taka adresa
    public static boolean isThisIPv4Address(int[] address)
    {
        for (int i : address)
        {
            if (i < 0 || i > 255)
                return false;
        }
        return true;
    }


    // metoda ktora konvertuje stringove pole na intove pole
    public static int[] stringArrayToIntArray(String[] array)
    {
        int[] arrayInt = new int[array.length];
        for (int i = 0; i < array.length; i++)
        {
            arrayInt[i] = Integer.parseInt(array[i]);
        }
        return arrayInt;
    }


    // metoda ktora urci triedu sietovaj adresy
    private String setTrieda(int[] adresa)
    {
        if(adresa[0] < 128)
            return "A";
        if(adresa[0] >= 128 && adresa[0] < 192)
            return "B";
        if(adresa[0] >= 192 && adresa[0] < 224)
            return "C";
        if(adresa[0] >= 224 && adresa[0] < 240)
            return "D";

        return "E";
    }


    // metoda ktora vypocita poradie adresy
    private static long poradie(int[] address,int prefix)	// net == true -> getNet  ak net == false -> getBro
    {
        long poradie = 0;

        int[] octet1 = prevody.fromDecToBin8(address[0]);
        int[] octet2 = prevody.fromDecToBin8(address[1]);
        int[] octet3 = prevody.fromDecToBin8(address[2]);
        int[] octet4 = prevody.fromDecToBin8(address[3]);
        int fromThisPrefix = prefix / 8 + 1; 						// zisti v ktorom oktete sa zacinaju 0/1
        int border = prefix - (prefix / 8)*8;						// hranica kde sa menia 0/1

        poradie = calcOctet(octet1, poradie, fromThisPrefix, 1, border,24);	// octet1
        poradie = calcOctet(octet2, poradie, fromThisPrefix, 2, border,16);    // octet2
        poradie = calcOctet(octet3, poradie, fromThisPrefix, 3, border,8);		// octet3
        poradie = calcOctet(octet4, poradie, fromThisPrefix, 4, border,0);		// octet4

        return poradie;


    }


    // metoda pocita jednotky v oktete
    private static long calcOctet(int[] octet,long poradie, int fromThisPrefix, int numOctet, int border, int exponent)	//octet,fromThisPrefix,number octet,border,net
    {
        if(fromThisPrefix <= numOctet)			// numOctet - number octets which is changing
        {
            border = (fromThisPrefix == numOctet) ? border : 0;
            for(int i = 7;i >= border;i--)
            {
                if(octet[i] == 1)
                    poradie += prevody.exponentiationLong((long)2,(long)exponent);
                exponent++;
            }
        }
        return poradie;
    }

    // metoda ktora vracia prefix
    private int getPrefix(int prefix) throws zlyPrefixException
    {
        if (prefix < 0 || prefix > 32)
        {
            zlyPrefixException e = new zlyPrefixException();
            throw e;
        }
        return prefix;
    }

    //metoda ktora vrati nasledujucu sietovu adresu ktora nasleduje po broadcastovej
    public String getNasledujucaSietova()
    {
        int[] bc = new int[this.broadcastovaAdresa.length];
        for (int o = 0; o < bc.length; o++)
        {
            bc[o] = this.broadcastovaAdresa[o];
        }
        for (int i = bc.length - 1; i >= 0;i--)
        {
            if (bc[i] < 255)
            {
                bc[i] = bc[i] + 1;
                break;
            }
            else
                bc[i] = 0;
        }

        return getNasledujucaSietovaS(bc);
    }

    public String getNasledujucaSietovaS(int[] adresa) {
        String sBroadcastovaAdresa = "";
        for(int i = 0; i < 4; i++)
        {
            sBroadcastovaAdresa = sBroadcastovaAdresa + adresa[i];
            if (i != 3)
                sBroadcastovaAdresa = sBroadcastovaAdresa + ".";
        }
        return sBroadcastovaAdresa;
    }






}
