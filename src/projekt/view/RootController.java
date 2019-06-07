/*
 * Kontroler wczytujący zakładki w odpowiednie miejsca
 */
package projekt.view;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import projekt.Main;

/**
 *
 * @author Vieja
 */
public class RootController {
@FXML
private AnchorPane id_tab1;
@FXML
private AnchorPane id_tab2;
@FXML
private AnchorPane id_tab3;
@FXML
private AnchorPane id_tab4;
@FXML
private AnchorPane id_tab5;
        
Main main;

@FXML
private void initialize() {
        
}

 private void initMyAnchors(AnchorPane pane, String loc){

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(RootController.class.getResource(loc));
        try {
            pane.getChildren().setAll((Node) loader.load());
        }
        catch(IOException e){
            System.out.println("Nie udało się załadować zawartści zakładek");
        }
        TabController cont=loader.getController();
        cont.setEkonomia(main.getEkonomia());
        cont.setTable();
 }
public void initTabs(){
    initMyAnchors(id_tab1,"Tab1.fxml");
    initMyAnchors(id_tab2,"Tab2.fxml");
    initMyAnchors(id_tab3,"Tab3.fxml");
    initMyAnchors(id_tab4,"Tab4.fxml");
    initMyAnchors(id_tab5,"Tab5.fxml");
}
    public void setMain(Main main) {
        this.main=main;
        initTabs();
    }


}