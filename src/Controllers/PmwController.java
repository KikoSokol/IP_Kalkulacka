package Controllers;

import Exceptions.zlaDlzkaAMWException;
import Exceptions.zlaMaskaException;
import Exceptions.zlyPrefixException;
import Exceptions.zlyWildcardException;
import Objekty.Pmw;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PmwController implements Initializable
{

    //label
    @FXML
    private Label chybaL;

    //textfieldy pre vstupne udaje
    @FXML
    private TextField prefix;

    @FXML
    private TextField wildcard;

    @FXML
    private TextField maska;


    //zatial nepotrebne veci
    @FXML
    private Button zMaska;

    @FXML
    private Button vlsm;

    @FXML
    private Button zWildcard;

    @FXML
    private Button subnetting1;

    @FXML
    private Button pmw;

    @FXML
    private Button subnetting2;

    @FXML
    private Button ipinfo;

    @FXML
    private Button zPrefix;

    @FXML
    private Button sustavy;

    @FXML
    private VBox menuVB;

    @FXML
    private VBox vyudajeVB;

    @FXML
    private HBox natovHB;

    @FXML
    private HBox funkciaHB;

    @FXML
    private HBox tlacidlaHB;

    @FXML
    private Label funkciaL;

    @FXML
    private Label nazovL;






    @FXML
    void ipinfoAction(ActionEvent event) throws IOException {
        FXMLLoader ipInfoLoader = new FXMLLoader();
        ipInfoLoader.setLocation(getClass().getResource("../View/IPInfo.fxml"));

        Parent ipInfoParent = ipInfoLoader.load();

        Scene ipInfoScena = new Scene(ipInfoParent);

        Stage ipInfoStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        ipInfoStage.setScene(ipInfoScena);
        ipInfoStage.setTitle("IP Info");
        ipInfoStage.show();

    }

    @FXML
    void pmwAction(ActionEvent event) throws IOException {
        FXMLLoader pmwLoader = new FXMLLoader();
        pmwLoader.setLocation(getClass().getResource("../View/Pmw.fxml"));

        Parent pmwParent = pmwLoader.load();

        Scene pmwScena = new Scene(pmwParent);

        Stage pmwStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        pmwStage.setScene(pmwScena);
        pmwStage.setTitle("Prefix/Maska/Wildcard");
        pmwStage.show();

    }

    @FXML
    void subnetting1Action(ActionEvent event) throws IOException {
        FXMLLoader subnetting1Loader = new FXMLLoader();
        subnetting1Loader.setLocation(getClass().getResource("../View/Subnetting1.fxml"));

        Parent subnetting1Parent = subnetting1Loader.load();

        Scene subnetting1Scena = new Scene(subnetting1Parent);

        Stage subnetting1Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        subnetting1Stage.setScene(subnetting1Scena);
        subnetting1Stage.setTitle("Subnetting - rovnako veľke siete");
        subnetting1Stage.show();

    }

    @FXML
    void subneting2Action(ActionEvent event) throws IOException {
        FXMLLoader subnetting2Loader = new FXMLLoader();
        subnetting2Loader.setLocation(getClass().getResource("../View/Subnetting2.fxml"));

        Parent subnetting2Parent = subnetting2Loader.load();

        Scene subnetting2Scena = new Scene(subnetting2Parent);

        Stage subnetting2Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        subnetting2Stage.setScene(subnetting2Scena);
        subnetting2Stage.setTitle("Subnetting - podla najväčšej siete");
        subnetting2Stage.show();

    }

    @FXML
    void vlsmAction(ActionEvent event) throws IOException {
        FXMLLoader vlsmLoader = new FXMLLoader();
        vlsmLoader.setLocation(getClass().getResource("../View/Vlsm.fxml"));

        Parent vlsmParent = vlsmLoader.load();

        Scene vlsmScena = new Scene(vlsmParent);

        Stage vlsmStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        vlsmStage.setScene(vlsmScena);
        vlsmStage.setTitle("VLSM");
        vlsmStage.show();

    }

    @FXML
    void sustavyAction(ActionEvent event) throws IOException {
        FXMLLoader sustavyLoader = new FXMLLoader();
        sustavyLoader.setLocation(getClass().getResource("../View/Sustavy.fxml"));

        Parent sustavyParent = sustavyLoader.load();

        Scene sustavyScena = new Scene(sustavyParent);

        Stage sustavyStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sustavyStage.setScene(sustavyScena);
        sustavyStage.setTitle("Prevod číselných sústav");
        sustavyStage.show();

    }

    @FXML
    void vymazAction(ActionEvent event)
    {
        prefix.setText("");
        maska.setText("");
        wildcard.setText("");
        chybaL.setText("");
    }

    //prevod z prefixu
    @FXML
    void zPrefixAction(ActionEvent event)
    {
        chybaL.setText("");
        if(prefix.getText().equals(""))
            chybaL.setText("Nebol zadaný údaj!");
        else
        {
            try
            {
                Pmw pmw = new Pmw(Integer.parseInt(prefix.getText()));
                maska.setText(pmw.getMaska());
                wildcard.setText(pmw.getWildcard());
                chybaL.setText("");
            }
            catch (zlyPrefixException e)
            {
                System.out.println("zly prefix");
                chybaL.setText("Prefix neexistuje! Prefix môže byť <0;32>");
                maska.setText("");
                wildcard.setText("");
            }
        }


    }

    //prevod z masky
    @FXML
    void zMaskaAction(ActionEvent event)
    {
        chybaL.setText("");
        if(maska.getText().equals(""))
            chybaL.setText("Nebol zadaný údaj!");
        else
        {
            try
            {
                Pmw pmw = new Pmw(maska.getText());
                prefix.setText(pmw.getPrefix());
                wildcard.setText(pmw.getWildcard());
                chybaL.setText("");
            }
            catch (zlaMaskaException e)
            {
                System.out.println("Maska neexistuje");
                chybaL.setText("Maska Neexistuje!");
                prefix.setText("");
                wildcard.setText("");
            }
            catch (zlaDlzkaAMWException e)
            {
                System.out.println("zla dlzka masky");
                chybaL.setText("Maska neexistuje. Maska obsahuje 4 oktety.");
                prefix.setText("");
                wildcard.setText("");
            }
            catch (Exception e)
            {
                System.out.println("pismena");
                chybaL.setText("Maska neexistuje! Oktet masky môže obsahovať iba čísla.");
                prefix.setText("");
                wildcard.setText("");
            }
        }

    }

    //prevod z wildcardu
    @FXML
    void zWildcardAction(ActionEvent event) 
    {
        chybaL.setText("");
        if(wildcard.getText().equals(""))
            chybaL.setText("Nebol zadaný údaj!");
        else
        {
            try
            {
                Pmw pmw = new Pmw(wildcard.getText(),true);
                prefix.setText(pmw.getPrefix());
                maska.setText(pmw.getMaska());
                chybaL.setText("");

            }
            catch (zlyWildcardException e)
            {
                System.out.println("Wildcard neexistuje");
                chybaL.setText("Wildcard neexistuje!");
                prefix.setText("");
                maska.setText("");
            }
            catch (zlaDlzkaAMWException e)
            {
                System.out.println("zla dlzka wildcardu");
                chybaL.setText("Wildcard neexistuje! Wildcard obsahuje 4 oktety.");
                prefix.setText("");
                maska.setText("");
            }
            catch (Exception e)
            {
                System.out.println("pismena");
                chybaL.setText("Wildcard neexistuje! Oktet wildcardu môže obsahovať iba čísla.");
                prefix.setText("");
                maska.setText("");
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        
        //obmezenie znakov okrem cisel, vypocitavanie masky a wildcardu podla prefixu
        prefix.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    prefix.setText(newValue.replaceAll("[^\\d]", ""));

                }
                if(prefix.getText().equals(""))
                {
                    maska.setText("");
                    wildcard.setText("");
                }
                else
                {
                    try
                    {
                        Pmw pmw = new Pmw(Integer.parseInt(prefix.getText()));
                        maska.setText(pmw.getMaska());
                        wildcard.setText(pmw.getWildcard());
                        chybaL.setText("");
                    }
                    catch (zlyPrefixException e)
                    {
                        System.out.println("zly prefix");
                        chybaL.setText("Prefix neexistuje! Prefix môže byť <0;32>");
                        maska.setText("");
                        wildcard.setText("");
                    }
                }




            }
        });
        
    }
}
