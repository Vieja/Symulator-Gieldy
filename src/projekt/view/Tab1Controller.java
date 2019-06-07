/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.view;

import projekt.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import projekt.model.Rynek;

/**
 *
 * @author Vieja
 */
public class Tab1Controller extends TabController {

    @FXML
    private TableView<Rynek> TableRynek;
    @FXML
    private TableColumn<Rynek, String> ColumnRynekNazwa;
    @FXML
    private TableColumn<Rynek, String> ColumnRynekTyp;
    
    @FXML
    private GridPane GridRynek;
    
    @FXML
    private Label LabelRynekNazwa;
    @FXML
    private Label LabelRynekTyp;
    @FXML
    private Label LabelRynekKraj;
    @FXML
    private Label LabelRynekWaluta;
    @FXML
    private Label LabelRynekMiasto;
    @FXML
    private Label LabelRynekAdres;
    @FXML
    private Label LabelRynekMarza;
    
    private Main main;
    
    public Tab1Controller() {
}
    @FXML
    private void initialize() {
        
        ColumnRynekNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnRynekTyp.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        
        showDetails(null);

        TableRynek.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> showDetails(newValue));
        
    }   
    @Override
    public void setTable() {
        TableRynek.setItems(this.getEkonomia().getlistaRynek()); 
    }
    
    private void showDetails(Rynek rynek) {
    if (rynek != null) {
        
        GridRynek.setVisible(true);
        LabelRynekNazwa.setText(rynek.getNazwa());
        LabelRynekTyp.setText(rynek.getTyp());
        LabelRynekKraj.setText(rynek.getKraj());
        LabelRynekWaluta.setText(rynek.getWaluta());
        LabelRynekMiasto.setText(rynek.getMiasto());
        LabelRynekAdres.setText(rynek.getAdres());
        LabelRynekMarza.setText(Double.toString(rynek.getMarza())+" %");

        } else {
        GridRynek.setVisible(false);
        }
    }

    @FXML
    private void DeleteRynek() {
        int selectedIndex = TableRynek.getSelectionModel().getSelectedIndex();
        this.getEkonomia().usunAktywaUsuwanegoRynku(selectedIndex);
        TableRynek.getItems().remove(selectedIndex);
    }
    
    @FXML
    private void AddRynek() {
        TableRynek.setItems(this.getEkonomia().dodajRynek(null));
    }

}
