/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt.view;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import projekt.model.Aktywa;

/**
 *
 * @author Vieja
 */
public class Tab5Controller extends TabController{
    
    @FXML
    private TableView<Aktywa> TableAktywa;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaNazwa;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaTyp;
    @FXML
    private TableColumn<Aktywa, String> ColumnAktywaRynek;
    
    @FXML
    private LineChart ChartAktywo;
    
    ArrayList<Aktywa> serie = new ArrayList<>();
    
    public Tab5Controller() {
    }
    @FXML
    private void initialize() {
        ColumnAktywaNazwa.setCellValueFactory(cellData -> cellData.getValue().nazwaProperty());
        ColumnAktywaTyp.setCellValueFactory(cellData -> cellData.getValue().typProperty());
        ColumnAktywaRynek.setCellValueFactory(cellData -> cellData.getValue().rynekProperty());
        
        TableAktywa.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> sprawdzTyp(newValue));
        
    }   
    @Override
    public void setTable() {
        TableAktywa.setItems(this.getEkonomia().getlistaAktywa()); 
    }
    
    private void sprawdzTyp(Aktywa aktywa) {
        ChartAktywo.getData().clear();
        serie.add(aktywa);
        wykres();
      
    }
    
    private void wykres() {
        int temp = this.getEkonomia().getDay();
        for(int i=0;i<serie.size();i++) {
            XYChart.Series series = new XYChart.Series();
            for (int j = 9; j >= 0; j--) {
                if (temp-j <= 1)
                    series.getData().add(new XYChart.Data(Integer.toString(1), serie.get(i).getProcenty(j)));
                else 
                    series.getData().add(new XYChart.Data(Integer.toString(temp-j), serie.get(i).getProcenty(j)));
            }
            ChartAktywo.getData().add(series); 
        }
    }
    
    @FXML
    private void Reset() {
        serie.clear();
        ChartAktywo.getData().clear();
    }
    
    @FXML
    private void Aktualizuj() {
        ChartAktywo.getData().clear();
        wykres();
    }
}
