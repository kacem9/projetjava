
package controllers;
import controllers.*;


import Utils.*;

import test.Velo;
import entities.rendezvous;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Service.Rendezvous_service;
import controllers.ListRendezvousController;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;

        

public class ClientController implements Initializable {
          
            
   
    
    ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
    "First", "Second", "Third")
);
    @FXML
    private MenuButton bike;
    @FXML
    private Button bikeParts;
    @FXML
    private Button location;
    @FXML
    private Button events;
    @FXML
    private MenuButton repaires;
    @FXML
    private MenuButton anOrder;
    @FXML
    private MenuButton reclamation;
    @FXML
    private MenuItem login;
    @FXML
    private MenuItem register;
   
    @FXML
    private MenuItem createMenuClicked;
    private TextArea textArea;
    ObservableList<rendezvous> oblist ; 
    @FXML
    private BorderPane mainPane;
    @FXML
    private MenuItem valid;
    @FXML
    private AnchorPane root;
    @FXML
    private MenuItem listRepaires;
   

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 


    }    
    
    @FXML
    private void handleButton1Action(ActionEvent e) throws IOException {
    
    if(e.getSource()==createMenuClicked){
        Stage stage = (Stage) root.getScene().getWindow();

            System.out.println("this is list appointement");  

            //get reference to the button's stage
            stage = (Stage) root.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/GUI/ListRendezvous.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        }
    else if(e.getSource()==valid){
    
        Stage stage = (Stage) root.getScene().getWindow();

            System.out.println("this is valid list");  

            //get reference to the button's stage
            stage = (Stage) root.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("/GUI/Validrendezvous.fxml"));

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
            root = FXMLLoader.load(getClass().getResource("/GUI/ListReparateur.fxml"));

        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    
    }
    
    
    
}



  
    
}
