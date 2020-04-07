/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author HP
 */
public class Pidev_javaController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private AnchorPane rootPane;
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private void LocationAction(ActionEvent event)  {
            
   try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("Pidev_java.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));  
stage.show();
    } catch(Exception e)
    {
    System.out.println("eer");
} 
    }
        @FXML
 private void CloseAction(ActionEvent event)  {
            
   try {   
       FXMLLoader pane   = FXMLLoader.load(getClass().getResource("Pidev_java.fxml"));
    Parent root =(Parent) pane.load();
    Stage stage = new Stage();
stage.setScene(new Scene(root));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }
    
      
    
}
