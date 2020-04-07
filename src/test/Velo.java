
package test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Velo extends Application {
       private Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage=primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/GUI/Client.fxml"));
        
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
