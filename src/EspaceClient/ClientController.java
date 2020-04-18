/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;

import service.userService;
import entities.user;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ClientController implements Initializable {

    @FXML
    private MenuItem login;
    @FXML
    private MenuItem register;
    @FXML
    private BorderPane mainPane;
    @FXML
    private MenuButton bike;
    @FXML
    private Button bikeParts;
    @FXML
    private Button events;
    @FXML
    private MenuButton repaires;
    @FXML
    private MenuItem listRepaires;
    @FXML
    private MenuItem createMenuClicked;
    @FXML
    private MenuItem valid;
    @FXML
    private MenuButton anOrder;
    @FXML
    private MenuButton reclamation;
    @FXML
    private BorderPane mainPane1;
         userService us=new userService();
    @FXML
    private HBox main;
    @FXML
    private AnchorPane root;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

  

    }    

    @FXML
    private void locationClient(ActionEvent event) {
        try {   
   AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/EspaceClient/EspaceLocationClient.fxml"));
     // AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/GUI/Shop.fxml"));

Stage stage = new Stage();
stage.setScene(new Scene(anchorPane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }

    @FXML
    private void CartClient(ActionEvent event) {
        try {   
    //  AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/GUI/EspaceLocationClient.fxml"));
      AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/EspaceClient/Cart.fxml"));

Stage stage = new Stage();
stage.setScene(new Scene(anchorPane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }
    @FXML
    private void orderLocation(ActionEvent event) {
        try {   
   //  AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/GUI/EspaceLocationClient.fxml"));
    AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/EspaceClient/OrderClientLocation.fxml"));

Stage stage = new Stage();
stage.setScene(new Scene(anchorPane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    } 
     @FXML
    private void eventAcceuilAction(ActionEvent event)  {
            
    try {   
       AnchorPane pane   = FXMLLoader.load(getClass().getResource("/EspaceClient/Event.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("erreur");
}
    }

     @FXML
    private void produit(ActionEvent event) {
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
    @FXML
    private void handleButton1Action(ActionEvent event) throws IOException {
         if(event.getSource()==createMenuClicked){
        Stage stage = (Stage) root.getScene().getWindow();

            System.out.println("this is list appointement");  

            //get reference to the button's stage
            stage = (Stage) root.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("ListRendezvous.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    else if(event.getSource()==valid){
    
        Stage stage = (Stage) root.getScene().getWindow();

            System.out.println("this is valid list");  

            //get reference to the button's stage
            stage = (Stage) root.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("Validrendezvous.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
       else {
    
        Stage stage = (Stage) root.getScene().getWindow();

            System.out.println("this is list repaires");  

            //get reference to the button's stage
            stage = (Stage) root.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("ListReparateur.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    
    }
}
