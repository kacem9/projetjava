/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author root
 */
public class AdminController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Button Admin;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void eventAcceuilAction(ActionEvent event)  {
            
    try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Admin/Event.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("erreur");
}
    }
    
    @FXML
    private void UserAcceuilAction(ActionEvent event)  {
            
    try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Admin/Administrator.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("erreur");
}
    }
    
     @FXML
    private void VeloLoactionAcceuilAction(ActionEvent event)  {
            
    try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Admin/ListeBikeLocation.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("erreur");
}
    }
    
      @FXML
    private void CategoriesAcceuilAction(ActionEvent event)  {
            
    try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Admin/AfficherCategories.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("erreur");
}
    }

      @FXML
    private void CommandesFxml(ActionEvent event) {
           try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Velo/FXMLCommande.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("erreur");
}
    }
    @FXML
    private void handleButton1Action(ActionEvent e) throws IOException {
               if(e.getSource()==Admin){
        Stage stage = (Stage) root.getScene().getWindow();

            System.out.println("Admin");  

            //get reference to the button's stage
            stage = (Stage) root.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("AfficherReclamation.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    }
    }

