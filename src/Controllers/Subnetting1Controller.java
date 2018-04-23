package Controllers;

import Exceptions.*;
import Objekty.Siet;
import Vypocitavanie.Subneting;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Subnetting1Controller implements Initializable
{

    //tabulka a stlpce
    @FXML
    private TableView<Siet> table;

    @FXML
    private TableColumn<Siet, String> prefix;

    @FXML
    private TableColumn<Siet, String> sie;

    @FXML
    private TableColumn<Siet, String> pocetP;

    @FXML
    private TableColumn<Siet, String> maska;

    @FXML
    private TableColumn<Siet, String> pocet;

    @FXML
    private TableColumn<Siet, String> rozsah;

    @FXML
    private TableColumn<Siet, String> bc;

    @FXML
    private TableColumn<Siet, String> nazov;

    //textfieldy pre vstupne udaje
    @FXML
    private TextField adresaTF;

    @FXML
    private TextField pocetTF;

    @FXML
    private TextField prefixTF;

    //label
    @FXML
    private Label chybaL;



    //zatial nepotrebne veci
    @FXML
    private VBox vyudajeVB;

    @FXML
    private VBox menuVB;

    @FXML
    private HBox funkciaHB;

    @FXML
    private HBox natovHB;

    @FXML
    private HBox enterHB;

    @FXML
    private HBox vsudajeHB;

    @FXML
    private HBox tlacidlaHB;

    @FXML
    private HBox chybaHB;


    @FXML
    private Label nazovL;

    @FXML
    private Label funkciaL;

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
    private Button x;

    @FXML
    void ipinfoAction(ActionEvent event) throws IOException {
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
    void pmwAction(ActionEvent event) throws IOException {
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
    void subnetting1Action(ActionEvent event) throws IOException {
        FXMLLoader subnetting1Loader = new FXMLLoader();
        subnetting1Loader.setLocation(getClass().getResource("/View/Subnetting1.fxml"));

        Parent subnetting1Parent = subnetting1Loader.load();

        Scene subnetting1Scena = new Scene(subnetting1Parent);

        Stage subnetting1Stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        subnetting1Stage.setScene(subnetting1Scena);
        subnetting1Stage.setTitle("Subnetting - rovnaká veľkosť sietí");
        subnetting1Stage.show();

    }

    @FXML
    void subneting2Action(ActionEvent event) throws IOException {
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
    void vlsmAction(ActionEvent event) throws IOException {
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
    void sustavyAction(ActionEvent event) throws IOException {
        FXMLLoader sustavyLoader = new FXMLLoader();
        sustavyLoader.setLocation(getClass().getResource("/View/Sustavy.fxml"));

        Parent sustavyParent = sustavyLoader.load();

        Scene sustavyScena = new Scene(sustavyParent);

        Stage sustavyStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        sustavyStage.setScene(sustavyScena);
        sustavyStage.setTitle("Prevod číselných sústav");
        sustavyStage.show();

    }

    @FXML
    void xAction(ActionEvent event)
    {
        table.getItems().clear();
        table.setVisible(false);
        adresaTF.setText("");
        prefixTF.setText("");
        pocetTF.setText("");
        chybaL.setText("");
    }

    @FXML
    void enterAction(ActionEvent event)
    {
        //ak nebola zadana adresa
        if(adresaTF.getText().equals(""))
        {
            table.setVisible(false);
            chybaL.setText("Nebola zadaná sieťová adresa! Zadaj sieťovú adresu.");
        }
        // ak nebol zadany prefix
        else if(prefixTF.getText().equals(""))
        {
            table.setVisible(false);
            chybaL.setText("Nebol zadaný prefix! Zadaj prefix siete.");
        }
        //ak nebol zadany pocet potrebnych sieti
        else if(pocetTF.getText().equals(""))
        {
            table.setVisible(false);
            chybaL.setText("Nebol zadaný počet potrebných sieti!");
        }
        //ak vsetko je zadane spravne vypocita udaje
        else
        {
            try
            {
                ObservableList<Siet> siete;
                siete = Subneting.sub(adresaTF.getText(),Integer.parseInt(prefixTF.getText()), Integer.parseInt(pocetTF.getText()));
                table.setItems(siete);
                table.setVisible(true);
                chybaL.setText("");
            }
            catch (ZlaDlzkaAMWException e)
            {
                table.setVisible(false);
                System.out.println("Zla dlzka adresy");
                chybaL.setText("IP adresa neexistuje! IP adresa obsahuje 4 oktety.");
            }
            catch (ZlyOctetException e)
            {
                table.setVisible(false);
                System.out.println("Zly oktet");
                chybaL.setText("IP adresa neexistuje. Oktet IP adresy može byť <0;255>");

            }
            catch (ZlyPrefixException e)
            {
                table.setVisible(false);
                System.out.println("Zly prefix");
                chybaL.setText("Prefix neexistuje! Prefix môže byť <0;32>");
            }
            catch (SubnetingException e)
            {
                table.setVisible(false);
                chybaL.setText("Táto sieť sa nedá rozdeliť na toľko podsietí!");
            }
            catch (NullSubnetingException e)
            {
                table.setVisible(false);
                chybaL.setText("Bolo zadané počet sieti 0!");
            }
            catch (VelaRozdelenychSietiException e)
            {
                table.setVisible(false);
                chybaL.setText("Maximálny počet rozdelených sietí môže byť 1000!");
            }
            catch (NieSietovaAdresaException e)
            {
                table.setVisible(false);
                chybaL.setText("Zla IP adresa! Zadaj sieťovú adresu!");
            }
            catch (NumberFormatException e)
            {
                table.setVisible(false);
                chybaL.setText("IP adresa neexistuje! Oktet IP adresy môže obsahovať iba čísla.");
                System.out.println("Zly vstup");

            }
            catch (Exception e)
            {
                table.setVisible(false);
                chybaL.setText("Maximálny počet rozdelených sietí môže byť 1000!");
            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //nastavenie stlpcov v tabulke
        table.setVisible(false);
        //stlpec so sietovou adresou
        sie.setCellValueFactory(new PropertyValueFactory<>("SietovaAdresaForTable"));

        //stlpec s prefixom
        prefix.setCellValueFactory(new PropertyValueFactory<>("Prefix"));

        //stlpec s maskou
        maska.setCellValueFactory(new PropertyValueFactory<>("Maska"));

        //stlpec s broadcastovou adresou
        bc.setCellValueFactory(new PropertyValueFactory<>("BroadcastovaAdresaForTable"));

        //stlpec s poctom adries
        pocet.setCellValueFactory(new PropertyValueFactory<>("PocetAdries"));

        //stlpec s poctom pouzitelnych adries
        pocetP.setCellValueFactory(new PropertyValueFactory<>("PocetPouzitelnychAdries"));

        //stlpec s rozsahom
        rozsah.setCellValueFactory(new PropertyValueFactory<>("Rozsah"));

        //stlpec s nazvom
        nazov.setCellValueFactory(new PropertyValueFactory<>("NazovSiete"));

        //obmezenie znakov okrem cisel na textfieldy
        prefixTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    prefixTF.setText(newValue.replaceAll("[^\\d]", ""));

                }
            }
        });

        pocetTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    pocetTF.setText(newValue.replaceAll("[^\\d]", ""));

                }
            }
        });

    }
}
