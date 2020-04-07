
package controllers;
import controllers.*;


import Utils.*;


import test.Velo;
import entities.fos_user;
import entities.rendezvous;
import entities.validrendezvous;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import Service.Validrendezvous_service;
import Service.fos_user_service;
import entities.rendezvous;
import java.io.IOException;
import java.text.ParseException;
import javafx.scene.control.Alert;
import Service.Rendezvous_service;


public class ListReparateurController implements Initializable {
       ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<fos_user> oblist = FXCollections.observableArrayList();
    ObservableList<String> oblistType = FXCollections.observableArrayList("DIRECTION ET ROUES","FREINS","SUSPENSION");
    Connection c= ConnexionBD.getInstance().getCnx();
    fos_user_service cr = new  fos_user_service();
     Rendezvous_service br = new  Rendezvous_service();
      fos_user r = new   fos_user();
       rendezvous t = new   rendezvous();

    @FXML
    private AnchorPane anchorpane_parent;
    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_left;
    @FXML
    private TableView<fos_user> tab_view;
   
    @FXML
    private TableColumn<fos_user, String> clnm1;
    @FXML
    private TableColumn<fos_user, String> clnm2;
    @FXML
    private TableColumn<fos_user, String> clnm3;
    @FXML
    private TableColumn<fos_user, String> clnm4;
   
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_edit;
  
 
    @FXML
    private ComboBox<String> combo_type;
    @FXML
    private Pane pane_top;
    @FXML
    private Label lael_txt;
    @FXML
    private TextArea email_txt;
    @FXML
    private TextArea id_txt;
    @FXML
    private TextArea adresse_txt;
    @FXML
    private TextArea num_txt;
    @FXML
    private TextArea prenom_txt;
    @FXML
    private TextArea nom_txt;
    @FXML
    private TextArea message_txt;

    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb  ) {
     
           try {
               fos_user_service cs = new fos_user_service ();
               oblist = cs.getAll();
               this.tab_view.setEditable(true);
               cr.afficherReparateur(oblist);
               setCellTable();
               tab_view.setItems(oblist);
               
        
               
               
               combo_type.setItems(oblistType);
               oblist = cs.getAll();
               
               
               
               
               setCellTable();
           } catch (IOException ex) {
               Logger.getLogger(ListReparateurController.class.getName()).log(Level.SEVERE, null, ex);
           }
     
 
	}
      private void setCellTable(){
        
    
       
        clnm1.setCellValueFactory(new PropertyValueFactory<>("email"));
        clnm2.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        clnm3.setCellValueFactory(new PropertyValueFactory<>("Num_tel"));
           
        clnm4.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
   
         tab_view.setItems(oblist);
    }
  
 

   

    
     @FXML
    private void btn_addRendezvous(ActionEvent event)throws ParseException {
    
    
       String typepanne =combo_type.getValue().toString();
  
       String email = email_txt.getText().toString();
        String adresse = adresse_txt.getText().toString();
       String nom = nom_txt.getText().toString();
        String prenom = prenom_txt.getText().toString();
         String message = message_txt.getText().toString();
         String user = id_txt.getText().toString();
          String numtel = num_txt.getText().toString();
    
    rendezvous s = new rendezvous();
     s.setEmail(email);
       s.setAdresse(adresse);
        s.setTypepanne(typepanne);
        
        
         s.setNom(nom);
       s.setPrenom(prenom);
       
       s.setMessage(message);
          s.setNumtel(Integer.parseInt(num_txt.getText()));
           s.setUser(Integer.parseInt(id_txt.getText()));
      
           Rendezvous_service cs = new Rendezvous_service() ;
              cs.insertPStatement(s);
    
    
    
    
    }
    
    
    
    
    

    @FXML
    private void editRendezvous(ActionEvent event) {
          fos_user selected = tab_view.getSelectionModel().getSelectedItem();
         id_txt.setText(String.valueOf(selected.getId()));
          id_txt.setText(selected.getId()+""); 
           
    
        
    }

    @FXML
    private void testEtat(MouseEvent event) {
    }




    }
      


