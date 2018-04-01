package Controllers;

import Exceptions.*;
import Objekty.Pmw;
import Objekty.Siet;
import Vypocitavanie.Vlsm;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VlsmController implements Initializable
{

    @FXML
    private TableColumn<Siet, String> rozsah;

    @FXML
    private TextField n1;

    @FXML
    private TextField n2;

    @FXML
    private HBox tlacidlaHB;

    @FXML
    private Label funkciaL;

    @FXML
    private TextField n3;

    @FXML
    private TableColumn<Siet, String> prefix;

    @FXML
    private TextField n4;

    @FXML
    private TextField n5;

    @FXML
    private VBox menuVB;

    @FXML
    private TextField n6;

    @FXML
    private TextField n7;

    @FXML
    private TextField n8;

    @FXML
    private TextField n9;

    @FXML
    private TextField n10;

    @FXML
    private TextField p10;

    @FXML
    private TextField n12;

    @FXML
    private TextField n11;

    @FXML
    private TextField p12;

    @FXML
    private TextField n14;

    @FXML
    private TextField n13;

    @FXML
    private TextField p11;

    @FXML
    private TextField n16;

    @FXML
    private TextField p14;

    @FXML
    private TextField p13;

    @FXML
    private TextField n15;

    @FXML
    private TextField p16;

    @FXML
    private TextField n18;

    @FXML
    private TextField p15;

    @FXML
    private TextField n17;

    @FXML
    private TextField p18;

    @FXML
    private TextField p17;

    @FXML
    private Button pmw;

    @FXML
    private Button enter;

    @FXML
    private Button vlsm;

    @FXML
    private TableColumn<Siet, String> pocet;

    @FXML
    private TextField prefixTF;

    @FXML
    private TableColumn<Siet, String> bc;

    @FXML
    private TextField p1;

    @FXML
    private TextField p2;

    @FXML
    private TextField p3;

    @FXML
    private TextField p4;

    @FXML
    private TextField p5;

    @FXML
    private TextField p6;

    @FXML
    private TextField p7;

    @FXML
    private HBox natovHB;

    @FXML
    private TextField p8;

    @FXML
    private TextField p9;

    @FXML
    private TableColumn<Siet, String> sie;

    @FXML
    private HBox enterHB;

    @FXML
    private HBox vsudajeHB;

    @FXML
    private TableColumn<Siet, String> pocetP;

    @FXML
    private TableColumn<Siet, String> maska;

    @FXML
    private TextField adresaTF;

    @FXML
    private Label chybaL;

    @FXML
    private Button subnetting1;

    @FXML
    private Button subnetting2;

    @FXML
    private Button ipinfo;

    @FXML
    private TableView<Siet> table;

    @FXML
    private Button sustavy;

    @FXML
    private VBox vyudajeVB;

    @FXML
    private HBox chybaHB;

    @FXML
    private Button x;

    @FXML
    private Label nazovL;

    @FXML
    private HBox funkciaHB;

    private TextField[] pocty;

    private TextField[] nazvy;

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
    void vlsmAction(ActionEvent event) throws IOException
    {
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
    void xAction(ActionEvent event)
    {
        adresaTF.setText("");
        prefixTF.setText("");
        chybaL.setText("");
        table.getItems().clear();
        table.setVisible(false);
        for (int a = 0; a < pocty.length; a++)
        {
            pocty[a].setText("");
        }
    }

    //vypocitanie sieti
    @FXML
    void enterAction(ActionEvent event)
    {
        if(adresaTF.getText().equals(""))
            chybaL.setText("Nebola zadaná sieťová adresa");
        if(prefix.getText().equals(""))
            chybaL.setText("Nebol zadaný prefix");

        //pole do ktoreho sa ulozia vsetky zadane pocty
        ArrayList<Integer> poctyArray = new ArrayList<>();
        //uloznie poctov do pola
        for (int i = 0; i < pocty.length; i++)
        {
            if(pocty[i].equals("") == false)
            {
                try
                {
                    if(Integer.parseInt(pocty[i].getText()) > 0)
                        poctyArray.add(Integer.parseInt(pocty[i].getText()));
                    else continue;
                }
                catch (NumberFormatException e)
                {
                    System.out.println(i);
                }
            }


        }
        //jednorozmerne pole kde sa ulozia finalne pocty
        int[] finallPocty = new int[poctyArray.size()];
        for(int u = 0; u < finallPocty.length; u++)
        {
            finallPocty[u] = poctyArray.get(u);
        }

        //ak bude zadanych viac ako 0 sieti tak vypocita
        if (finallPocty.length > 0)
        {
            try
            {
                ObservableList<Siet> siete;
                siete = Vlsm.vlsm(adresaTF.getText(), Integer.parseInt(prefixTF.getText()), finallPocty);
                table.setItems(siete);
                table.setVisible(true);
                chybaL.setText("");

            } catch (zlyPrefixException e)
            {
                chybaL.setText("Prefix neexistuje. Prefix môže byť <0;32>");
            }
            catch (zlyOctetException e)
            {
                chybaL.setText("Zle zadaný vstupný udaj. Oktet v IP adrese može byť <0;255>");
            }
            catch (zlaDlzkaAMWException e)
            {
                chybaL.setText("Zle zadaný vstupný údaj. IP adresa obsahuje 4 oktety");
            }
            catch (MalaSietExcepiton e)
            {
                chybaL.setText("Sieť sa nedá rozdeliť");
            }
            catch (nieSietovaAdresaException e)
            {
                chybaL.setText("Zla IP adresa. Zadaj sieťovú adresu!");
            }
            catch (IOException e)
            {
                chybaL.setText("Zle zadaný vstupný údaj. Oktet IP adresy alebo môže obsahovať iba čísla.");
            }
        }
        else
            chybaL.setText("Neboli zadane siete");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        pocty = new TextField[]{p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18};
        nazvy = new TextField[]{n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18};

        //nastavenie automatickeho prepočitavania masky z prefixu, nedovolim pisať do prefixu znaky okrem cisel
        for(int a = 0; a < pocty.length;a++)
        {
            int i = a;
            pocty[a].textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        pocty[i].setText(newValue.replaceAll("[^\\d]", ""));
                    }


                }
            });
        }

        prefixTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    prefixTF.setText(newValue.replaceAll("[^\\d]", ""));
                }


            }
        });

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

        //stlpes s rozsahom
        rozsah.setCellValueFactory(new PropertyValueFactory<>("Rozsah"));



    }
}
