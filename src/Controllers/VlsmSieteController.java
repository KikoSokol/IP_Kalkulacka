package Controllers;

import Objekty.Siet;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class VlsmSieteController implements Initializable
{

    //tabulka
    @FXML
    private TableView<Siet> table;

    @FXML
    private TableColumn<Siet, String> rozsah;

    @FXML
    private TableColumn<Siet, String> bc;

    @FXML
    private TableColumn<Siet, String> prefix;

    @FXML
    private TableColumn<Siet, String> pocet;

    @FXML
    private TableColumn<Siet, String> sie;

    @FXML
    private TableColumn<Siet, String> nazov;

    @FXML
    private TableColumn<Siet, String> pocetP;

    @FXML
    private TableColumn<Siet, String> maska;

    private ObservableList<Siet> siete;


    //zatial nepotrebne veci
    @FXML
    private VBox menuVB;

    @FXML
    private VBox vyudajeVB;

    @FXML
    private HBox natovHB;

    @FXML
    private Label nazovL;

    public void setSieteDoTabulky(ObservableList<Siet> siete)
    {
        this.siete = siete;
        this.table.setItems(this.siete);

    }



    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

        //stlpec s nazvom
        nazov.setCellValueFactory(new PropertyValueFactory<>("NazovSiete"));
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
