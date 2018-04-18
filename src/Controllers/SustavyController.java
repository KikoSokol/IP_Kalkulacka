package Controllers;

import Exceptions.neexistujucaSustavaException;
import Objekty.Sustavy;
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

public class SustavyController implements Initializable
{

    //textfieldy
    @FXML
    private TextField binarna;

    @FXML
    private TextField sestnastkova;

    @FXML
    private TextField osmickova;

    @FXML
    private TextField desiatkova;


    //zatial nepotrebne
    @FXML
    private HBox tlacidlaHB;

    @FXML
    private HBox natovHB;

    @FXML
    private HBox funkciaHB;

    @FXML
    private Label funkciaL;

    @FXML
    private Label chybaL;

    @FXML
    private Label nazovL;

    @FXML
    private VBox menuVB;

    @FXML
    private VBox vyudajeVB;

    @FXML
    private Button vlsm;

    @FXML
    private Button subnetting1;

    @FXML
    private Button pmw;

    @FXML
    private Button subnetting2;

    @FXML
    private Button ipinfo;

    @FXML
    private Button sustavy;



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
        subnetting1Stage.setTitle("Subnetting - rovnako veľke siete");
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
    void vymazAction(ActionEvent event)
    {
        desiatkova.setText("");
        desiatkova.clear();
        binarna.setText("");
        osmickova.setText("");
        sestnastkova.setText("");
        chybaL.setText("");

    }
    private int prevod = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        //nastavenie prepocitavania, obmedzeni na textfieldy
        desiatkova.setOnMouseClicked(event -> prevod = 0);
        desiatkova.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    desiatkova.setText(newValue.replaceAll("[^\\d]", ""));
                }

                if(desiatkova.getText().equals(""))
                {
                    if (prevod == 0)
                    {
                        binarna.clear();
                        osmickova.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 1)
                    {
                        osmickova.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 2)
                    {
                        binarna.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 3)
                    {
                        binarna.clear();
                        osmickova.clear();
                    }
                }
                else
                {
                    if (prevod == 0)
                    {
                        Sustavy sustavy = null;
                        try
                        {
                            sustavy = new Sustavy(Long.parseLong(desiatkova.getText()));
                            binarna.setText(sustavy.getBinarna());
                            osmickova.setText(sustavy.getOsmickova());
                            sestnastkova.setText(sustavy.getSestnastkova());
                            System.out.println("a");
                            chybaL.setText("");
                        }
                        catch (NumberFormatException e)
                        {
                            System.out.println("NumberFormatException");
                            chybaL.setText("Príliš veľké číslo!");
                            binarna.setText("");
                            osmickova.setText("");
                            sestnastkova.setText("");

                        }
                        catch (ArithmeticException e)
                        {
                            System.out.println("aritmetik");
                            chybaL.setText("Príliš veľké číslo!");
                            binarna.clear();
                            osmickova.clear();
                            sestnastkova.clear();
                        }
                    }
                }

            }
        });

        //binarna
        binarna.setOnMouseClicked(event -> prevod = 1);
        binarna.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.equals("0") || !newValue.equals("1")) {
                    binarna.setText(newValue.replaceAll("[^\\\\0\\\\1]", ""));
                }
                if(binarna.getText().equals(""))
                {
                    if(prevod == 0)
                    {
                        osmickova.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 1)
                    {
                        desiatkova.clear();
                        osmickova.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 2)
                    {
                        desiatkova.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 3)
                    {
                        desiatkova.clear();
                        osmickova.clear();
                    }
                }
                else
                {
                    if (prevod == 1)
                    {
                        Sustavy sustavy = null;
                        try {
                            sustavy = new Sustavy(binarna.getText(), 2);
                            desiatkova.setText(sustavy.getDesiatkova());
                            osmickova.setText(sustavy.getOsmickova());
                            sestnastkova.setText(sustavy.getSestnastkova());
                            System.out.println("a");
                            chybaL.setText("");
                        }
                        catch (neexistujucaSustavaException e)
                        {
                            System.out.println("binarna");
                            chybaL.setText("Príliš veľké číslo!");
                            desiatkova.clear();
                            osmickova.clear();
                            sestnastkova.clear();
                        }

                    }
                }


            }
        });


        //osmickova
        osmickova.setOnMouseClicked(event -> prevod = 2);
        osmickova.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.equals("0")|| !newValue.equals("1") || !newValue.equals("2") || !newValue.equals("3") || !newValue.equals("4") || !newValue.equals("5") || !newValue.equals("6") || !newValue.equals("7")) {
                    osmickova.setText(newValue.replaceAll("[^\\\\0\\\\1\\\\2\\\\3\\\\4\\\\5\\\\6\\\\7]", ""));
                }

                if(osmickova.getText().equals(""))
                {
                    if(prevod == 0)
                    {
                        binarna.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 1)
                    {
                        desiatkova.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 2)
                    {
                        desiatkova.clear();
                        binarna.clear();
                        sestnastkova.clear();
                    }
                    if(prevod == 3)
                    {
                        binarna.clear();
                        desiatkova.clear();
                    }

                }
                else
                {
                    if (prevod == 2)
                    {
                        Sustavy sustavy = null;
                        try
                        {
                            sustavy = new Sustavy(osmickova.getText(), 8);
                            desiatkova.setText(sustavy.getDesiatkova());
                            binarna.setText(sustavy.getBinarna());
                            sestnastkova.setText(sustavy.getSestnastkova());
                            System.out.println("a");
                            chybaL.setText("");
                        }
                        catch (neexistujucaSustavaException e)
                        {
                            System.out.println("osem");
                            chybaL.setText("Príliš veľké číslo!");
                            desiatkova.clear();
                            binarna.clear();
                            sestnastkova.clear();
                        }
                        catch (ArithmeticException e)
                        {
                            System.out.println("aritmetik");
                            chybaL.setText("Príliš veľké číslo!");
                            binarna.clear();
                            desiatkova.clear();
                            sestnastkova.clear();
                        }

                    }
                }


            }
        });


        //sestnastkova
        sestnastkova.setOnMouseClicked(event -> prevod = 3);
        sestnastkova.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.equals("0")|| !newValue.equals("1") || !newValue.equals("2") || !newValue.equals("3") ||
                        !newValue.equals("4") || !newValue.equals("5") || !newValue.equals("6") || !newValue.equals("7")
                        || !newValue.equals("8") || !newValue.equals("9") || !newValue.equals("A") || !newValue.equals("a")
                        || !newValue.equals("B") || !newValue.equals("b") || !newValue.equals("C") || !newValue.equals("c")
                        || !newValue.equals("D") || !newValue.equals("d") || !newValue.equals("E") || !newValue.equals("e")
                        || !newValue.equals("F") || !newValue.equals("f")) {
                    sestnastkova.setText(newValue.replaceAll("[^\\\\0\\\\1\\\\2\\\\3\\\\4\\\\5\\\\6\\\\7\\\\8\\\\9\\\\A\\\\a\\\\B\\\\b\\\\C\\\\c\\\\D\\\\d\\\\E\\\\e\\\\F\\\\f]", ""));
                }

                if(sestnastkova.getText().equals(""))
                {
                    if(prevod == 0)
                    {
                        binarna.clear();
                        osmickova.clear();
                    }
                    if(prevod == 1)
                    {
                        osmickova.clear();
                        desiatkova.clear();
                    }
                    if(prevod == 2)
                    {
                        binarna.clear();
                        desiatkova.clear();
                    }
                    if(prevod == 3)
                    {
                        desiatkova.clear();
                        binarna.clear();
                        osmickova.clear();
                    }

                }
                else
                {
                    if (prevod == 3)
                    {
                        Sustavy sustavy = null;
                        try
                        {
                            sustavy = new Sustavy(sestnastkova.getText(), 16);
                            desiatkova.setText(sustavy.getDesiatkova());
                            binarna.setText(sustavy.getBinarna());
                            osmickova.setText(sustavy.getOsmickova());
                            System.out.println("a");
                            chybaL.setText("");
                        }
                        catch (neexistujucaSustavaException e)
                        {
                            System.out.println("sestnast");
                            chybaL.setText("Príliš veľké číslo!");
                            binarna.clear();
                            desiatkova.clear();
                            osmickova.clear();
                        }
                        catch (ArithmeticException e)
                        {
                            chybaL.setText("Príliš veľké číslo!");
                            System.out.println("aritmetik");
                            binarna.clear();
                            osmickova.clear();
                            desiatkova.clear();
                        }
                    }
                }


            }
        });
    }

}
