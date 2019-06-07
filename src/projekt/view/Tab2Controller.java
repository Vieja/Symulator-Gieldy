/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import projekt.model.Akcja;
import projekt.model.Gielda;
import projekt.model.Indeks;
import projekt.model.Rynek;

/**
 *
 * @author Vieja
 */

public class Tab2Controller extends TabController{
    
    @FXML
    private TableView<Gielda> TableGielda;
    @FXML
    private TableView<Akcja> TableSpolka;
    @FXML
    private TableView<Indeks> TableIndeks;
    @FXML
    private TableView<Akcja> TableIndeks_Spolka;
    @FXML
    private TableColumn<Rynek, String> ColumnGieldaNazwa;
    @FXML
    private TableColumn<Rynek, String> ColumnGieldaTyp;
    @FXML
    private TableColumn<Akcja, String> ColumnSpolkaNazwa;
    @FXML
    private TableColumn<Indeks, String> ColumnIndeksNazwa;
    @FXML
    private TableColumn<Indeks, String> ColumnIndeksGielda;
    @FXML
    private TableColumn<Indeks, String> ColumnIndeksTyp;
    @FXML
    private TableColumn<Akcja, String> ColumnIndeks_SpolkaNazwa;
    @FXML
    private TableColumn<Akcja, String> ColumnIndeks_SpolkaObroty;
    
    
    @FXML
    private GridPane GridSpolka;
    
    @FXML
    private TextField TextFieldWig;

    @FXML
    private Label LabelSpolkaNazwa;
    @FXML
    private Label LabelSpolkaData;
    @FXML
    private Label LabelSpolkaKurs_Otwarcia;
    @FXML
    private Label LabelSpolkaKurs;
    @FXML
    private Label LabelSpolkaMinKurs;
    @FXML
    private Label LabelSpolkaMaxKurs;
    @FXML
    private Label LabelSpolkaZysk;
    @FXML
    private Label LabelSpolkaPrzychod;
    @FXML
    private Label LabelSpolkaKapitalWlasny;
    @FXML
    private Label LabelSpolkaKapitalZakladowy;
    @FXML
    private Label LabelSpolkaWolumen;
    @FXML
    private Label LabelSpolkaObroty ;
    
    public Tab2Controller() {
    }
    
    @FXML
    private void initialize() {
        
        ColumnGieldaNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnGieldaTyp.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        ColumnIndeksNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnIndeksGielda.setCellValueFactory(cellData -> cellData.getValue().nazwaGieldaProperty());
        ColumnIndeksTyp.setCellValueFactory(cellData -> cellData.getValue().typGieldaProperty());
        
        GridSpolka.setVisible(false);
        
        TableGielda.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showSpolki(newValue));
        TableSpolka.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showDetailsSpolka(newValue));
        TableIndeks.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showSpolkiIndeksu(newValue));
        TableIndeks_Spolka.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showDetailsSpolka(newValue));
    }
    public void showSpolki(Gielda gielda) {
        this.getEkonomia().getSpolki(gielda,1);
    }
    
    private void showSpolkiIndeksu(Indeks indeks) {
        this.getEkonomia().getSpolkiIndeksu(indeks);
    }
    
    private void showDetailsSpolka(Akcja aktywa) {
    if (aktywa != null) {
        GridSpolka.setVisible(true);
        LabelSpolkaNazwa.setText(aktywa.getNazwa());
        LabelSpolkaData.setText(aktywa.getData()+" dzień symulacji");
        LabelSpolkaObroty.setText(aktywa.getNazwaRynek());
        LabelSpolkaKurs.setText(Double.toString(aktywa.getKurs()));
        
        LabelSpolkaKurs_Otwarcia.setText(Double.toString(aktywa.getKursOtwarcia())+" zł");      
        LabelSpolkaKurs.setText(Double.toString(aktywa.getKurs())+" zł");
        LabelSpolkaMinKurs.setText(Double.toString(aktywa.getMin())+" zł");
        LabelSpolkaMaxKurs.setText(Double.toString(aktywa.getMax())+" zł");
        LabelSpolkaZysk.setText(Double.toString(aktywa.getZysk())+" zł");
        LabelSpolkaPrzychod.setText(Double.toString(aktywa.getPrzychod())+" zł");
        LabelSpolkaKapitalWlasny.setText(Double.toString(aktywa.getKapitalWlasny())+" zł");
        LabelSpolkaKapitalZakladowy.setText(Double.toString(aktywa.getKapitalZakladowy())+" zł");
        LabelSpolkaWolumen.setText(Integer.toString(aktywa.getWolumen()));
        LabelSpolkaObroty.setText(Double.toString(aktywa.getObroty())+" zł"); 
        } else {
            GridSpolka.setVisible(false);
        }
    }
    
    @Override
    public void setTable() {
        TableGielda.setItems(this.getEkonomia().getlistaGielda()); 
        TableSpolka.setItems(this.getEkonomia().getlistaSpolki());
        TableIndeks.setItems(this.getEkonomia().getlistaIndeks());
        TableIndeks_Spolka.setItems(this.getEkonomia().getlistaIndeks_Spolka());
        ColumnSpolkaNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnIndeks_SpolkaNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnIndeks_SpolkaObroty.setCellValueFactory(cellData -> cellData.getValue().obrotyProperty());
    }

    @FXML
    private void DeleteIndeks() {
        int selectedIndex = TableIndeks.getSelectionModel().getSelectedIndex();
        TableIndeks.getItems().remove(selectedIndex);
    }
    
    @FXML
    private void AddIndeksLos() {
        TableIndeks.setItems(this.getEkonomia().stworzIndeks(null));
    }
    
    @FXML
    private void AddIndeksWig() {
        String typ = TextFieldWig.getText();
        int liczba;
        try {
            liczba = Integer.parseInt(typ);
            TableIndeks.setItems(this.getEkonomia().stworzIndeks(liczba));
        } catch (NumberFormatException e) {
        }   
        
    }
}
