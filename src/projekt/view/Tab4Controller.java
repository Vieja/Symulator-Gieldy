/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.view;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import projekt.model.Akcja;
import projekt.model.Aktywa;
import projekt.model.Surowiec;
import projekt.model.Waluta;

/**
 *
 * @author Vieja
 */
public class Tab4Controller extends TabController{
    
    @FXML
    private TableView<Aktywa> TableAktywa;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaNazwa;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaTyp;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaRynek;
    
    @FXML
    private GridPane GridAkcja;
    @FXML
    private GridPane GridSurowiec;
    @FXML
    private GridPane GridWaluta;
    
    @FXML
    private LineChart ChartAktywo;
    
    @FXML
    private Label LabelAkcjaNazwa;
    @FXML
    private Label LabelAkcjaGielda;
    @FXML
    private Label LabelAkcjaData;
    @FXML
    private Label LabelAkcjaKursOtwarcia;
    @FXML
    private Label LabelAkcjaKurs;
    @FXML
    private Label LabelAkcjaKursMin;
    @FXML
    private Label LabelAkcjaKursMax;
    
    @FXML
    private Label LabelSurowiecNazwa;
    @FXML
    private Label LabelSurowiecRynek;
    @FXML
    private Label LabelSurowiecJednostka;
    @FXML
    private Label LabelSurowiecWaluta;
    @FXML
    private Label LabelSurowiecWartosc;
    @FXML
    private Label LabelSurowiecWartoscMin;
    @FXML
    private Label LabelSurowiecWartoscMax;
    
    @FXML
    private Label LabelWalutaNazwa;
    @FXML
    private Label LabelWalutaRynek;
    @FXML
    private Label LabelWalutaKurs;
    
    
    public Tab4Controller() {
    }
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        ColumnAktywaNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnAktywaTyp.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        ColumnAktywaRynek.setCellValueFactory(cellData -> cellData.getValue().rynekProperty());
        
        GridAkcja.setVisible(false);
        GridWaluta.setVisible(false);
        GridSurowiec.setVisible(false);
        
        TableAktywa.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> sprawdzTyp(newValue));
        
    }   
    @Override
    public void setTable() {
        TableAktywa.setItems(this.getEkonomia().getlistaAktywa()); 
    }
    
    private void sprawdzTyp(Aktywa aktywa) {
        ChartAktywo.getData().clear();
        if (aktywa.getClass() == Akcja.class) {
            showDetailsAkcja((Akcja) aktywa);
        }
        if (aktywa.getClass() == Surowiec.class) {
            showDetailsSurowiec((Surowiec) aktywa);
        }
        if (aktywa.getClass() == Waluta.class) {
            showDetailsWaluta((Waluta) aktywa);
        }
        XYChart.Series series = new XYChart.Series();
        int temp = this.getEkonomia().getDay();
        for (int i = 9; i >= 0; i--) {
            if (temp-i <= 1)
                series.getData().add(new XYChart.Data(Integer.toString(1), aktywa.getHistoria(i)));
             else 
                series.getData().add(new XYChart.Data(Integer.toString(temp-i), aktywa.getHistoria(i)));
        }
        ChartAktywo.getData().add(series); 
      
    }
    
    private void showDetailsAkcja(Akcja aktywa) {
    if (aktywa != null) {
        GridSurowiec.setVisible(false);
        GridWaluta.setVisible(false);
        GridAkcja.setVisible(true);
        LabelAkcjaNazwa.setText(aktywa.getNazwa());
        LabelAkcjaGielda.setText(aktywa.getNazwaRynek());
        LabelAkcjaData.setText(aktywa.getData());
        LabelAkcjaKursOtwarcia.setText(Double.toString(aktywa.getKursOtwarcia())+" zł");      
        LabelAkcjaKurs.setText(Double.toString(aktywa.getKurs())+" zł");
        LabelAkcjaKursMin.setText(Double.toString(aktywa.getMin())+" zł");
        LabelAkcjaKursMax.setText(Double.toString(aktywa.getMax())+" zł");
    } else {
        GridAkcja.setVisible(false);
    }
    }
    
    private void showDetailsSurowiec(Surowiec aktywa) {
    if (aktywa != null) {
        GridAkcja.setVisible(false);
        GridWaluta.setVisible(false);
        GridSurowiec.setVisible(true);
        LabelSurowiecNazwa.setText(aktywa.getNazwa());
        LabelSurowiecRynek.setText(aktywa.getNazwaRynek());
        LabelSurowiecJednostka.setText(aktywa.getJednostka());
        LabelSurowiecWaluta.setText(aktywa.getWaluta());
        LabelSurowiecWartosc.setText(Double.toString(aktywa.getKurs())+" zł");
        LabelSurowiecWartoscMin.setText(Double.toString(aktywa.getMin())+" zł");
        LabelSurowiecWartoscMax.setText(Double.toString(aktywa.getMax())+" zł");
    } else {
        GridSurowiec.setVisible(false);
    }
    }
    private void showDetailsWaluta(Waluta aktywa) {
    if (aktywa != null) {
        GridSurowiec.setVisible(false);
        GridAkcja.setVisible(false);
        GridWaluta.setVisible(true);
        LabelWalutaNazwa.setText(aktywa.getNazwa());
        LabelWalutaRynek.setText(aktywa.getNazwaRynek());
        LabelWalutaKurs.setText(Double.toString(aktywa.getKurs())+" zł");
    } else {
        GridWaluta.setVisible(false);
      }
    }
    @FXML
    private void DeleteAktywa() {
        int selectedIndex = TableAktywa.getSelectionModel().getSelectedIndex();
        TableAktywa.getItems().remove(selectedIndex);
    }

    @FXML
    private void AddAkcja() {
        TableAktywa.setItems(this.getEkonomia().dodajAktywo("giełda papierów wartościowych"));
    }
    
    @FXML
    private void AddSurowiec() {
        TableAktywa.setItems(this.getEkonomia().dodajAktywo("rynek surowców"));
    }
    
    @FXML
    private void AddWaluta() {
        TableAktywa.setItems(this.getEkonomia().dodajAktywo("rynek walut"));
    }
    
}
