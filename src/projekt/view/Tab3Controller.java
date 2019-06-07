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
import javafx.scene.layout.GridPane;
import projekt.model.Aktywa;
import projekt.model.Fundusz;
import projekt.model.Inwestor;
import projekt.model.InwestorIndywidualny;

/**
 *
 * @author Vieja
 */
public class Tab3Controller extends TabController{
    
    @FXML
    private TableView<Inwestor> TableInwestor;
    @FXML
    private TableView<Aktywa> TableAktywa;
    @FXML
    private TableView<Fundusz> TableJednostki;
    
    @FXML
    private TableColumn<Inwestor, String> ColumnInwestorNazwa;
    @FXML
    private TableColumn<Inwestor, String> ColumnInwestorTyp;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaNazwa;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaTyp;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaGielda;
    @FXML
    private TableColumn<Fundusz, String> ColumnJednostki;
    
    @FXML
    private GridPane GridInwestor;
    @FXML
    private GridPane GridFundusz;
    
    @FXML
    private Label LabelInwestorImie;
    @FXML
    private Label LabelInwestorNazwisko;
    @FXML
    private Label LabelInwestorPesel;
    @FXML
    private Label LabelInwestorBudzet;
    @FXML
    private Label LabelFunduszNazwa;
    @FXML
    private Label LabelFunduszBudzet;
    @FXML
    private Label LabelFunduszImie;
    @FXML
    private Label LabelFunduszNazwisko;

    
    public Tab3Controller() {
    }
    
    @FXML
    private void initialize() {
        ColumnInwestorNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnInwestorTyp.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        
        GridInwestor.setVisible(false);
        GridFundusz.setVisible(false);
        
        TableInwestor.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> sprawdzTyp(newValue));
    }
    
        private void sprawdzTyp(Inwestor inwestor) {
        if (inwestor.getClass() == InwestorIndywidualny.class) {
            showDetailsInwestorIndywidualny((InwestorIndywidualny) inwestor);            
        } else
        {
            showDetailsFundusz((Fundusz) inwestor);
        }
        System.out.println(inwestor.getlistaJednostek().size());
        this.getEkonomia().getListaAktywInwestora(inwestor);
        this.getEkonomia().getListaJednostekInwestora(inwestor);
    }
    
    private void showDetailsInwestorIndywidualny(InwestorIndywidualny inwestor) {
        if (inwestor != null) {
            GridFundusz.setVisible(false);
            GridInwestor.setVisible(true);
            LabelInwestorImie.setText(inwestor.getImie());
            LabelInwestorNazwisko.setText(inwestor.getNazwa());
            LabelInwestorPesel.setText(Integer.toString(inwestor.getPesel()));
            LabelInwestorBudzet.setText(Double.toString(inwestor.getBudzet())+" zł");
            } else {
                GridInwestor.setVisible(false);
            }
        
    }
    
    private void showDetailsFundusz(Fundusz inwestor) {
        if (inwestor != null) {
            GridInwestor.setVisible(false);
            GridFundusz.setVisible(true);
            LabelFunduszNazwa.setText(inwestor.getNazwa());
            LabelFunduszBudzet.setText(Double.toString(inwestor.getBudzet())+" zł");
            LabelFunduszImie.setText(inwestor.getImie());
            LabelFunduszNazwisko.setText(inwestor.getNazwisko());
            } else {
                GridFundusz.setVisible(false);
            }
        
    }
    
    @FXML
    private void DeleteInwestor() {
        int selectedIndex = TableInwestor.getSelectionModel().getSelectedIndex();
        TableInwestor.getItems().remove(selectedIndex);
    }
    
    @FXML
    private void AddInwestor() {
        TableInwestor.setItems(this.getEkonomia().dodajInwestor(0));
    }
    
    @FXML
    private void AddFundusz() {
        TableInwestor.setItems(this.getEkonomia().dodajInwestor(1));
    }
        
    @Override
    public void setTable() {
        TableInwestor.setItems(this.getEkonomia().getlistaInwestor());
        TableAktywa.setItems(this.getEkonomia().getlistaAktywInwestora());
        TableJednostki.setItems(this.getEkonomia().getlistaJednostekInwestora());
        ColumnAktywaNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnAktywaTyp.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        ColumnAktywaGielda.setCellValueFactory(cellData -> cellData.getValue().rynekProperty());
        ColumnJednostki.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        
    }
    
}
