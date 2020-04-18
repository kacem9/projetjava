/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceVendeur;

import View.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * FXML Controller class
 *
 * @author root
 */
public class HomeController implements Initializable {

    @FXML
    private Label label;
    
    @FXML
    private AnchorPane rootPane;
     
    @FXML
    private AnchorPane pane;
      @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

  @FXML
    private void LocationAcceuilAction(ActionEvent event)  {
            
    try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/EspaceVendeur/EspaceLocation.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("erreur");
}
    }
     @FXML
    public void Produit(ActionEvent event) throws Exception {

       try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Velo/FXML_Product.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("erreur");
}
        
}
}
