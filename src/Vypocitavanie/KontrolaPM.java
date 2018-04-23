package Vypocitavanie;

public class KontrolaPM
{
    private Prevody prevody;
    //uzivatelom zadana maska
    private String maska;
    //uzivatelom zadany prefix
    private int prefix;


    public KontrolaPM(String maska, int prefix)
    {
        this.maska = maska;
        this.prefix = prefix;
        prevody = new Prevody();
    }

    public boolean zistiRovnake()
    {
        String tempMaska = getMaska(fromPrefixToMask(this.prefix));

        if (this.maska.equals(tempMaska))
            return true;
        return false;
    }

    // metoda na prevod prefixu do masky
    private int[] fromPrefixToMask(int prefix)
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

    private String getMaska(int[] maska) {
        String sMaska = "";
        for(int i = 0; i < 4; i++)
        {
            sMaska = sMaska + maska[i];
            if (i != 3)
                sMaska = sMaska + ".";
        }
        return sMaska;
    }
}
