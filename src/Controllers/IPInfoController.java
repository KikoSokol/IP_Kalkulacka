package Controllers;
import Exceptions.*;
import Objekty.Pmw;
import Objekty.Siet;
import Vypocitavanie.KontrolaPM;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class IPInfoController implements Initializable
{

    //textfieldy pre vstupne udaje
    @FXML
    private TextField prefixTF;

    @FXML
    private TextField maskaTF;

    @FXML
    private TextField adresaTF;


    //lable
    @FXML
    private Label rozsah;

    @FXML
    private Label prefix;

    @FXML
    private Label typ;

    @FXML
    private Label sie;

    @FXML
    private Label pocetP;

    @FXML
    private Label bc;

    @FXML
    private Label poradie;

    @FXML
    private Label wildcard;

    @FXML
    private Label maska;

    @FXML
    private Label bcB;

    @FXML
    private Label maskaB;

    @FXML
    private Label sieB;

    @FXML
    private Label wildcardB;

    @FXML
    private Label trieda;

    @FXML
    private Label pocet;

    @FXML
    private Label chybaL;


    //zatial nepotrebne veci
    @FXML
    private HBox tlacidlaHB;

    @FXML
    private HBox natovHB;

    @FXML
    private HBox enterHB;

    @FXML
    private HBox vsudajeHB;

    @FXML
    private HBox funkciaHB;

    @FXML
    private HBox chybaHB;

    @FXML
    private VBox menuVB;

    @FXML
    private VBox vyudajeVB;

    @FXML
    private GridPane vyudajeTab;

    @FXML
    private Button subnetting1;

    @FXML
    private Button pmw;

    @FXML
    private Button subnetting2;

    @FXML
    private Button enter;

    @FXML
    private Button ipinfo;

    @FXML
    private Button sustavy;

    @FXML
    private Button vlsm;

    @FXML
    private Label posledna;

    @FXML
    private Label nText;

    @FXML
    private Label prva;

    @FXML
    private Label nazovTFL;

    @FXML
    private Label funkciaL;

    @FXML
    private Label nazovL;


    @FXML
    void ipinfoAction(ActionEvent event) throws IOException
    {
        FXMLLoader ipInfoLoader = new FXMLLoader();
        ipInfoLoader.setLocation(getClass().getResource("/View/IPInfo.fxml"));

        Parent ipInfoParent = ipInfoLoader.load();

        Scene ipInfoScena = new Scene(ipInfoParent);

        Stage ipInfoStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        ipInfoStage.setScene(ipInfoScena);
        ipInfoStage.setTitle("IP Info");
        ipInfoStage.show();

    }

    @FXML
    void pmwAction(ActionEvent event) throws IOException
    {
        FXMLLoader pmwLoader = new FXMLLoader();
        pmwLoader.setLocation(getClass().getResource("/View/Pmw.fxml"));

        Parent pmwParent = pmwLoader.load();

        Scene pmwScena = new Scene(pmwParent);

        Stage pmwStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        pmwStage.setScene(pmwScena);
        pmwStage.setTitle("Prefix/Maska/Wildcard");
        pmwStage.show();
    }

    @FXML
    void subnetting1Action(ActionEvent event) throws IOException
    {
        FXMLLoader subnetting1Loader = new FXMLLoader();
        subnetting1Loader.setLocation(getClass().getResource("/View/Subnetting1.fxml"));

        Parent subnetting1Parent = subnetting1Loader.load();

        Scene subnetting1Scena = new Scene(subnetting1Parent);

        Stage subnetting1Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        subnetting1Stage.setScene(subnetting1Scena);
        subnetting1Stage.setTitle("Subnetting - rovnako veľke siete");
        subnetting1Stage.show();
    }

    @FXML
    void subneting2Action(ActionEvent event) throws IOException
    {
        FXMLLoader subnetting2Loader = new FXMLLoader();
        subnetting2Loader.setLocation(getClass().getResource("/View/Subnetting2.fxml"));

        Parent subnetting2Parent = subnetting2Loader.load();

        Scene subnetting2Scena = new Scene(subnetting2Parent);

        Stage subnetting2Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        subnetting2Stage.setScene(subnetting2Scena);
        subnetting2Stage.setTitle("Subnetting - podla najväčšej siete");
        subnetting2Stage.show();
    }

    @FXML
    void vlsmAction(ActionEvent event) throws IOException
    {
        FXMLLoader vlsmLoader = new FXMLLoader();
        vlsmLoader.setLocation(getClass().getResource("/View/Vlsm.fxml"));

        Parent vlsmParent = vlsmLoader.load();

        Scene vlsmScena = new Scene(vlsmParent);

        Stage vlsmStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        vlsmStage.setScene(vlsmScena);
        vlsmStage.setTitle("VLSM");
        vlsmStage.show();
    }

    @FXML
    void sustavyAction(ActionEvent event) throws IOException
    {
        FXMLLoader sustavyLoader = new FXMLLoader();
        sustavyLoader.setLocation(getClass().getResource("/View/Sustavy.fxml"));

        Parent sustavyParent = sustavyLoader.load();

        Scene sustavyScena = new Scene(sustavyParent);

        Stage sustavyStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sustavyStage.setScene(sustavyScena);
        sustavyStage.setTitle("Prevod číselných sústav");
        sustavyStage.show();

    }

    //vymazat vsetky udaje
    @FXML
    void xAction(ActionEvent event)
    {
        chybaL.setText("");
        adresaTF.setText("");
        prefixTF.setText("");
        maskaTF.setText("");
        sie.setText("");
        sieB.setText("");
        bc.setText("");
        bcB.setText("");
        maska.setText("");
        maskaB.setText("");
        wildcard.setText("");
        wildcardB.setText("");
        prefix.setText("");
        typ.setText("");
        trieda.setText("");
        poradie.setText("");
        pocet.setText("");
        pocetP.setText("");
        rozsah.setText("");
    }

    //tlacidlo enter pre vypocitanie parametrov
    @FXML
    void enterAction(ActionEvent event)
    {
        //ak nebude zadana adresa
        if(adresaTF.getText().equals(""))
        {
            chybaL.setText("Nebola zadaná IP adresa! Zadajte IP adresu");
        }
        else if(maskaTF.getText().equals("") == false && prefixTF.getText().equals("") == false)
        {
            KontrolaPM kontrola = new KontrolaPM(maskaTF.getText(),Integer.parseInt(prefixTF.getText()));
            if(kontrola.zistiRovnake())
            {
                try
                {
                    Siet siet = new Siet(adresaTF.getText(), Integer.parseInt(prefixTF.getText()));
                    typ.setText(siet.getTypZadanejAdresy());
                    sie.setText(siet.getSietovaAdresa());
                    sieB.setText(siet.getBinarySieBcPMWAdress(0));
                    bc.setText(siet.getBroadcastovaAdresa());
                    bcB.setText(siet.getBinarySieBcPMWAdress(1));
                    maska.setText(siet.getMaska());
                    maskaB.setText(siet.getBinarySieBcPMWAdress(2));
                    prefix.setText(siet.getPrefix());
                    wildcard.setText(siet.getWildcard());
                    wildcardB.setText(siet.getBinarySieBcPMWAdress(3));
                    trieda.setText(siet.getTrieda());
                    poradie.setText(siet.getPoradie());
                    rozsah.setText(siet.getRozsah());
                    pocet.setText(siet.getPocetAdries());
                    pocetP.setText(siet.getPocetPouzitelnychAdries());
                    chybaL.setText("");
                }
                catch (ZlyOctetException e)
                {
                    System.out.println("octet");

                    chybaL.setText("IP adresa neexistuje! Oktet IP adresy môže byť <0;255>");
                    typ.setText("");
                    sie.setText("");
                    sieB.setText("");
                    bc.setText("");
                    bcB.setText("");
                    maska.setText("");
                    maskaB.setText("");
                    prefix.setText("");
                    wildcard.setText("");
                    wildcardB.setText("");
                    trieda.setText("");
                    poradie.setText("");
                    rozsah.setText("");
                    pocet.setText("");
                    pocetP.setText("");
                }
                catch (ZlaDlzkaAMWException e)
                {
                    System.out.println("dlzka");
                    chybaL.setText("IP adresa neexistuje! IP adresa obsahuje 4 oktety.");
                    typ.setText("");
                    sie.setText("");
                    sieB.setText("");
                    bc.setText("");
                    bcB.setText("");
                    maska.setText("");
                    maskaB.setText("");
                    prefix.setText("");
                    wildcard.setText("");
                    wildcardB.setText("");
                    trieda.setText("");
                    poradie.setText("");
                    rozsah.setText("");
                    pocet.setText("");
                    pocetP.setText("");
                }
                catch (PismenoVOkteteAdresaException e)
                {
                    System.out.println("Zle zadaný vstupný údaj. Oktet IP adresy môže obsahovať iba čísla.");
                    chybaL.setText("IP adresa neexistuje! Oktet IP adresy môže obsahovať iba čísla.");
                    typ.setText("");
                    sie.setText("");
                    sieB.setText("");
                    bc.setText("");
                    bcB.setText("");
                    maska.setText("");
                    maskaB.setText("");
                    prefix.setText("");
                    wildcard.setText("");
                    wildcardB.setText("");
                    trieda.setText("");
                    poradie.setText("");
                    rozsah.setText("");
                    pocet.setText("");
                    pocetP.setText("");
                }
                catch (Exception e)
                {

                }
            }
            else
                chybaL.setText("Prefix a maska sa nezhoduje!");

            try
            {
                Siet siet = new Siet(adresaTF.getText(), maskaTF.getText());
            }
            catch (ZlyOctetException e)
            {
                System.out.println("octet");

                chybaL.setText("IP adresa neexistuje! Oktet IP adresy može byť <0;255>");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (ZlaDlzkaAMWException e)
            {
                System.out.println("dlzka");
                chybaL.setText("IP adresa neexistuje! IP adresa obsahuje 4 oktety.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (ZlaDlzkaMasky e)
            {
                System.out.println("dlzka masky");
                chybaL.setText("Maska neexistuje! Maska obsahuje 4 oktety.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (ZlaMaskaException e)
            {
                System.out.println("Zle zadaná maska.");
                chybaL.setText("Maska neexistuje!");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (PismenoVOkteteAdresaException e)
            {
                System.out.println("Zle zadaný vstupný údaj. Oktet IP adresy môže obsahovať iba čísla.");
                chybaL.setText("IP adresa neexistuje! Oktet IP adresy môže obsahovať iba čísla.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (PismenoVOkteteMaskaException e)
            {
                System.out.println("Zle zadaný vstupný údaj. Oktet masky môže obsahovať iba čísla.");
                chybaL.setText("Maska neexistuje! Oktet masky môže obsahovať iba čísla.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (Exception e)
            {

            }

        }
        //ak nebude zadana maska
        else if(maskaTF.getText().equals("") == true && prefixTF.getText().equals("") == true)
        {
            chybaL.setText("Nebol zadaný prefix alebo maska!");
        }
        //ak bude zadany prefix a maska nie tak vypocita parametre podla prefixu
        else if(maskaTF.getText().equals("") == true && prefixTF.getText().equals("") == false)
        {
            try
            {
                Siet siet = new Siet(adresaTF.getText(), Integer.parseInt(prefixTF.getText()));
                typ.setText(siet.getTypZadanejAdresy());
                sie.setText(siet.getSietovaAdresa());
                sieB.setText(siet.getBinarySieBcPMWAdress(0));
                bc.setText(siet.getBroadcastovaAdresa());
                bcB.setText(siet.getBinarySieBcPMWAdress(1));
                maska.setText(siet.getMaska());
                maskaB.setText(siet.getBinarySieBcPMWAdress(2));
                prefix.setText(siet.getPrefix());
                wildcard.setText(siet.getWildcard());
                wildcardB.setText(siet.getBinarySieBcPMWAdress(3));
                trieda.setText(siet.getTrieda());
                poradie.setText(siet.getPoradie());
                rozsah.setText(siet.getRozsah());
                pocet.setText(siet.getPocetAdries());
                pocetP.setText(siet.getPocetPouzitelnychAdries());
                chybaL.setText("");
            }
            catch (ZlyOctetException e)
            {
                System.out.println("octet");

                chybaL.setText("IP adresa neexistuje! Oktet IP adresy može byť <0;255>");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (ZlaDlzkaAMWException e)
            {
                System.out.println("dlzka");
                chybaL.setText("IP adresa neexistuje! IP adresa obsahuje 4 oktety.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (PismenoVOkteteAdresaException e)
            {
                System.out.println("Zle zadaný vstupný údaj. Oktet IP adresy môže obsahovať iba čísla.");
                chybaL.setText("IP adresa neexistuje! Oktet IP adresy môže obsahovať iba čísla.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (Exception e)
            {

            }
        }
        //ak nebude zadany prefix a maska bude vypocita parametre podla masky
        else
        {
            try
            {
                Siet siet = new Siet(adresaTF.getText(), maskaTF.getText());
                typ.setText(siet.getTypZadanejAdresy());
                sie.setText(siet.getSietovaAdresa());
                sieB.setText(siet.getBinarySieBcPMWAdress(0));
                bc.setText(siet.getBroadcastovaAdresa());
                bcB.setText(siet.getBinarySieBcPMWAdress(1));
                maska.setText(siet.getMaska());
                maskaB.setText(siet.getBinarySieBcPMWAdress(2));
                prefix.setText(siet.getPrefix());
                wildcard.setText(siet.getWildcard());
                wildcardB.setText(siet.getBinarySieBcPMWAdress(3));
                trieda.setText(siet.getTrieda());
                poradie.setText(siet.getPoradie());
                rozsah.setText(siet.getRozsah());
                pocet.setText(siet.getPocetAdries());
                pocetP.setText(siet.getPocetPouzitelnychAdries());
                chybaL.setText("");
            }
            catch (ZlyOctetException e)
            {
                System.out.println("octet");

                chybaL.setText("IP adresa neexistuje! Oktet v IP adrese može byť <0;255>");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (ZlaDlzkaAMWException e)
            {
                System.out.println("dlzka");
                chybaL.setText("IP adresa neexistuje! IP adresa obsahuje 4 oktety.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (ZlaDlzkaMasky e)
            {
                System.out.println("dlzka masky");
                chybaL.setText("Maska neexistuje! Maska obsahuje 4 oktety.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (ZlaMaskaException e)
            {
                System.out.println("Zle zadaná maska.");
                chybaL.setText("Maska neexistuje!");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (PismenoVOkteteAdresaException e)
            {
                System.out.println("Zle zadaný vstupný údaj. Oktet IP adresy môže obsahovať iba čísla.");
                chybaL.setText("IP adresa neexistuje! Oktet IP adresy môže obsahovať iba čísla.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (PismenoVOkteteMaskaException e)
            {
                System.out.println("Zle zadaný vstupný údaj. Oktet masky môže obsahovať iba čísla.");
                chybaL.setText("Maska neexistuje! Oktet masky môže obsahovať iba čísla.");
                typ.setText("");
                sie.setText("");
                sieB.setText("");
                bc.setText("");
                bcB.setText("");
                maska.setText("");
                maskaB.setText("");
                prefix.setText("");
                wildcard.setText("");
                wildcardB.setText("");
                trieda.setText("");
                poradie.setText("");
                rozsah.setText("");
                pocet.setText("");
                pocetP.setText("");
            }
            catch (Exception e)
            {

            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        //nastavenie automatickeho prepočitavania masky z prefixu, nedovolim pisať do prefixu znaky okrem cisel
        prefixTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    prefixTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
                if(prefixTF.getText().equals(""))
                {
                    maskaTF.setText("");
                }
                //ak bude prefix vecsi ako 32 vyhodi chybu
                else if (Integer.parseInt(prefixTF.getText()) > 32)
                {
                    chybaL.setText("Zle zadaný vstupný údaj! Prefix môže byť <0;32>");
                    maskaTF.setText("");
                }
                //ak bude vsetko v poriadku vypocita masku
                else
                {
                    try
                    {
                        Pmw pmw = new Pmw(Integer.parseInt(prefixTF.getText()));
                        maskaTF.setText(pmw.getMaska());
                        chybaL.setText("");
                    }
                    catch (ZlyPrefixException e)
                    {
                        System.out.println("zly prefix");
                    }
                }

            }
        });
    }
}
