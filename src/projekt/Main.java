/**
 * Głowna klasa odpowiadająca za odpalenie grafiki, wczytanie zakładek oraz ich kontrolerów.
 */

package projekt;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projekt.view.RootController;

public class Main extends Application{
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Ekonomia ekonomia = new Ekonomia(this);
    
    public Main() {
        
    }
     public Ekonomia getEkonomia() {
        return this.ekonomia;
    }
    
   @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Symulacja Rynku - Aplikacja");

        initRootLayout();
        ekonomia.zacznij();
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            RootController controller = loader.getController();
            controller.setMain(this);
            
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}