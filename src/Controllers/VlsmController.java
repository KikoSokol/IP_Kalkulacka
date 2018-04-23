package Controllers;

import Exceptions.*;
import Objekty.Siet;
import Okna.VlsmSieteOkno;
import Vypocitavanie.Vlsm;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VlsmController implements Initializable
{

    @FXML
    private Button pridajSiet;

    @FXML
    private HBox tlacidlaHB;

    @FXML
    private Label funkciaL;

    @FXML
    private ListView<String> siete;

    @FXML
    private Button vymazVS;

    @FXML
    private VBox menuVB;

    @FXML
    private HBox natovHB;

    @FXML
    private HBox enterHB;

    @FXML
    private HBox vsudajeHB;

    @FXML
    private TextField adresaTF;

    @FXML
    private Label chybaL;

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
    private HBox sieteHB;

    @FXML
    private Button vlsm;

    @FXML
    private TextField pocetZariadeni;

    @FXML
    private TextField nazovSiete;

    @FXML
    private HBox chybaHB;

    @FXML
    private TextField prefixTF;

    @FXML
    private Button x;

    @FXML
    private Label nazovL;

    @FXML
    private HBox funkciaHB;

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
        adresaTF.setText("");
        prefixTF.setText("");
        chybaL.setText("");
    }

    public void vymazVS(ActionEvent event)
    {
        cisloSiete = 1;
        nazovSiete.setText(pNazovSiete.concat(" " + cisloSiete));
        pocetZariadeni.setText("");
        siete.getItems().clear();
        poctyArray.clear();
        nazvySietiArray.clear();

    }

    private ArrayList<String> poctyArray = new ArrayList<>();
    private ArrayList<String> nazvySietiArray = new ArrayList<>();
    private ObservableList<String> sieteString = FXCollections.observableArrayList();
    private int cisloSiete = 1;
    private String pNazovSiete = "Sieť";

    @FXML
    void pridajSiet(ActionEvent event)
    {
        if(pocetZariadeni.getText().equals(""))
            chybaL.setText("Nebol zadaný počet zariadení!");
        else
        {
            boolean rovnakyNazovSiete = false;
            for(int i = 0; i < nazvySietiArray.size(); i++)
            {
                if (nazvySietiArray.get(i).equals(nazovSiete.getText()))
                    rovnakyNazovSiete = true;
            }
            if (rovnakyNazovSiete)
                chybaL.setText("Sieť s týmto názvom už existuje.");
            else
            {
                nazvySietiArray.add(nazovSiete.getText());
                poctyArray.add(pocetZariadeni.getText());
                sieteString.add(nazvySietiArray.get(nazvySietiArray.size() - 1).concat(": ").concat(poctyArray.get(poctyArray.size() - 1)).concat(" zariadení"));
                siete.setItems(sieteString);
                cisloSiete++;
                nazovSiete.setText(pNazovSiete.concat(" " + cisloSiete));
                pocetZariadeni.clear();
                chybaL.setText("");
            }

        }
    }

    //vypocitanie sieti
    @FXML
    void enterAction(ActionEvent event)
    {
        for(int y = 0; y < poctyArray.size(); y++)
        {
            System.out.println(poctyArray.get(y));
        }
        if(adresaTF.getText().equals(""))
            chybaL.setText("Nebola zadaná sieťová adresa!");
        if(prefixTF.getText().equals(""))
            chybaL.setText("Nebol zadaný prefix!");


        //finalne dvojrozmerne pole kde sa ulozia finalne pocty a nazvy
        String[][] finallSiete = new String[poctyArray.size()][2];
        for(int u = 0; u < finallSiete.length; u++)
        {
            finallSiete[u][0] = poctyArray.get(u);
            finallSiete[u][1] = nazvySietiArray.get(u);
        }

        //ak bude zadanych viac ako 0 sieti tak vypocita
        if (finallSiete.length > 0)
        {
            try
            {
                ObservableList<Siet> siete;
                siete = Vlsm.vlsm(adresaTF.getText(), Integer.parseInt(prefixTF.getText()), finallSiete);
                VlsmSieteOkno vlsmSiete = new VlsmSieteOkno(siete);
                chybaL.setText("");

            } catch (ZlyPrefixException e)
            {
                chybaL.setText("Prefix neexistuje! Prefix môže byť <0;32>");
            }
            catch (ZlyOctetException e)
            {
                chybaL.setText("IP adresa neexistuje! Oktet IP adresy može byť <0;255>");
            }
            catch (ZlaDlzkaAMWException e)
            {
                chybaL.setText("IP adresa neexistuje! IP adresa obsahuje 4 oktety");
            }
            catch (MalaSietExcepiton e)
            {
                chybaL.setText("Sieť sa nedá rozdeliť!");
            }
            catch (NieSietovaAdresaException e)
            {
                chybaL.setText("Zla IP adresa! Zadaj sieťovú adresu!");
            }
            catch (IOException e)
            {
                chybaL.setText("IP adresa neexistuje. Oktet IP adresy môže obsahovať iba čísla.");
            }
            catch (NumberFormatException e)
            {
                chybaL.setText("Je požadovaných príliš veľa zariadení v sieti!");
            }
            catch(Exception e)
            {
                chybaL.setText("Nebola zadaná sieťová adresa");
            }
        }
        else
            chybaL.setText("Neboli zadane všetky potrebné údaje");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {


        prefixTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    prefixTF.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        pocetZariadeni.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    pocetZariadeni.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        //vymazavanie sieti
        siete.setOnMouseClicked(event ->
        {
            if(event.getButton().equals(MouseButton.PRIMARY))
            {
                if(event.getClickCount() == 2)
                {
                    String[] rozdelenyString = siete.getSelectionModel().getSelectedItem().split("");
                    String nazov = rozdelenyString[0].substring(rozdelenyString[0].length() - 1);
                    String pocet = rozdelenyString[1].trim();
                    for(int i = 0; i < sieteString.size(); i++)
                    {
                        if (sieteString.get(i).equals(siete.getSelectionModel().getSelectedItem()))
                        {
                            sieteString.remove(i);
                            System.out.println(poctyArray.get(i));
                            poctyArray.remove(i);
                            System.out.println(nazvySietiArray.get(i));
                            nazvySietiArray.remove(i);
                        }
                    }
                   // System.out.println(siete.getSelectionModel().getSelectedItem());
                }
            }
        });


    }


}
