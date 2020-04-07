/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import connection.ConnectionDB;
import entities.user;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.userService;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdministratorController implements Initializable {

    @FXML
    private ImageView image;

 Connection c = ConnectionDB.getinstance().getCnx();
 userService srv = new userService();
    @FXML
    private TableView<user> tab_view;

       @FXML
    private Button btn_del;
    ObservableList<user> oblist = FXCollections.observableArrayList();
    @FXML
    private Button btn_edit;
    @FXML
    private TableColumn<user, String> roles;
    @FXML
    private TableColumn<user, String> username;
    @FXML
    private TableColumn<user, String> nom;
    @FXML
    private TableColumn<user, String> prenom;
    @FXML
    private TableColumn<user, String> email;

    user u=new user();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
 ObservableList<user>data=FXCollections.observableArrayList();

    data.addAll(srv.readAll());
            System.out.println(data.size());

          nom .setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
      roles .setCellValueFactory(new PropertyValueFactory<>("roles"));
           
      
        tab_view.setItems(data);
        
  }
    /**
     * Initializes the controller class.
     */

   

private void setCellTable(){
        
         ObservableList<user>data=FXCollections.observableArrayList();

    data.addAll(srv.readAll());
            System.out.println(data.size());

          nom .setCellValueFactory(new PropertyValueFactory<>("Nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
      roles .setCellValueFactory(new PropertyValueFactory<>("roles"));
           
      
        tab_view.setItems(data);

    }

    
 

      @FXML
    public void btn_deleteUserAction(ActionEvent event){ 
         
           userService us = new userService();
       int u =  tab_view.getSelectionModel().getSelectedItem().getId();
     us.delete(u);
   
        tab_view.getItems().clear();
        us.readAll();
        setCellTable();
        us.readAll();
            
           
        
    }
  /*@FXML
    private void ModifierAction(ActionEvent event)  {
   userService sp = new userService();
        user u = (user) tab_view.getSelectionModel().getSelectedItem();
           
        try {   
       FXMLLoader pane = new FXMLLoader
                        (getClass()
                         .getResource("ModifyBike.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = pane.load();
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                ModifyBikeController test = pane.getController();
          as=tab_view.getSelectionModel().getSelectedItem().getId();

        String  description=tab_view.getSelectionModel().getSelectedItem().getDescription();
         String  localitsation_velo=tab_view.getSelectionModel().getSelectedItem().getLocalitsation_velo();
       int quantity=tab_view.getSelectionModel().getSelectedItem().getQuantity();
        
        

    Float price_location=tab_view.getSelectionModel().getSelectedItem().getPrice_location();
       
             test.setData(
                                         
                 

              tab_view.getSelectionModel().getSelectedItem().getDescription(),
              tab_view.getSelectionModel().getSelectedItem().getLocalitsation_velo(),
               tab_view.getSelectionModel().getSelectedItem().getQuantity(),
                tab_view.getSelectionModel().getSelectedItem().getPrice_location(),
                  tab_view.getSelectionModel().getSelectedItem().getId());
        
    } catch(Exception e)
    {
     System.out.println("eer");
}
            
          
    }*/

}