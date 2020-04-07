/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AdminController implements Initializable {


  @FXML
  private AnchorPane anchorPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     @FXML
    private void BikeLocationAction(ActionEvent event) {
        try {   
      AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/GUI/ListeBikeLocation.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(anchorPane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }
    
    
    
    
    
    
      @FXML
    private void  CategoriesVeloAction(ActionEvent event) {
        try {   
      AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/GUI/AfficherCategories.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(anchorPane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    } 
    
    
    
    
    
    
    
    
    
    
    
    
}
