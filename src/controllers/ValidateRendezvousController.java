package controllers;




import Utils.ConnexionBD;
import Service.ValidateRendezvous_service;

import entities.rendezvous;
import entities.validrendezvous;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
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
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.DatePicker;
import Service.Validrendezvous_service;
import Service.ValidateRendezvous_service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ValidateRendezvousController implements Initializable {
       Connection c= ConnexionBD.getInstance().getCnx();
        ObservableList<String> list = FXCollections.observableArrayList();
            ObservableList<String> oblistType = FXCollections.observableArrayList("10%","20%","30%","40%","50%","no promotion");
                ObservableList<String> oblistTypes = FXCollections.observableArrayList("At home","at etablissement");


   ObservableList<rendezvous> oblist = FXCollections.observableArrayList();
    Validrendezvous_service cr =new Validrendezvous_service();
    rendezvous r=new rendezvous();
   
    @FXML
    private AnchorPane anchorpane_parent;
    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_left;
    @FXML
    private TableView<rendezvous> tab_view;
    @FXML
    private TableColumn<rendezvous, String> clnm2;
    @FXML
    private TableColumn<rendezvous, String> clnm3;
    @FXML
    private TableColumn<rendezvous, String> clnm4;
    @FXML
    private TableColumn<rendezvous, String> clnm5;
    @FXML
    private TableColumn<rendezvous, String> clnm6;
    @FXML
    private TableColumn<rendezvous, String> clnm7;
    @FXML
    private TableColumn<rendezvous, Integer> clnm8;
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private Button btn_add;
    @FXML
    private TextArea id_txt;
    @FXML
    private Button btn_edit;
    @FXML
    private ComboBox<String> combo_type;
    @FXML
    private TextArea email_txt;
    private TextArea adresse_txt;
    private TextArea num_txt;
    private TextArea prenom_txt;
    private TextArea nom_txt;
    @FXML
    private Pane pane_top;
    @FXML
    private Label lael_txt;
    @FXML
    private TextArea price_txt;
    @FXML
    private TextArea message_txt;
    @FXML
    private ComboBox<String> combo_type1;
    @FXML
    private DatePicker date_txt;

    
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
           try {
                
               Validrendezvous_service cs =new Validrendezvous_service();
               oblist = cs.getAlls();
               this.tab_view.setEditable(true);
               
               cr.afficherRendezvous(oblist);
               tab_view.setItems(oblist);
               setCellTable();
               tab_view.setItems(oblist);
               oblist = cs.getAlls();
               setCellTable();
                              combo_type.setItems(oblistType);
                                             combo_type1.setItems(oblistTypes);
           } catch (IOException ex) {
               Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
           }
      
    }    

    @FXML
    private void btn_valid(ActionEvent event) throws ParseException {
        
         String promo =combo_type.getValue().toString();
          String etat =combo_type1.getValue().toString();
  
       String emailR = email_txt.getText().toString();
        String prix = price_txt.getText().toString();
       String message = message_txt.getText().toString();
            
String dateheure = date_txt.getValue().toString();
    validrendezvous s = new validrendezvous();
    s.setDateheure(dateheure);
            s.setEmailR(emailR);
       s.setPrix(prix);
       s.setPromo(promo);
        s.setEtat(etat);
        s.setMessage(message);
      
          s.setMessage(message);
           
   
 s.setUser(Integer.parseInt(id_txt.getText()));
      
           ValidateRendezvous_service cs = new ValidateRendezvous_service() ;
             cs.insertPStatement(s);
    
        
        
        
        
    }

    @FXML
    private void editRendezvous(ActionEvent event) {
          rendezvous selected = tab_view.getSelectionModel().getSelectedItem();
         id_txt.setText(String.valueOf(selected.getUser()));
          id_txt.setText(selected.getUser()+"");
    }
    
    
    
    
    
       private void setCellTable(){
        
    
       
     
        clnm2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        clnm3.setCellValueFactory(new PropertyValueFactory<>("prenom"));
           
        clnm4.setCellValueFactory(new PropertyValueFactory<>("email"));
      clnm5.setCellValueFactory(new PropertyValueFactory<>("adresse"));
          clnm6.setCellValueFactory(new PropertyValueFactory<>("message"));
           
        clnm7.setCellValueFactory(new PropertyValueFactory<>("typepanne"));
      clnm8.setCellValueFactory(new PropertyValueFactory<>("numtel"));
         tab_view.setItems(oblist);
    }

    @FXML
    private void testEtat(MouseEvent event) {
    }
    
    

 
    
    
    
    
}
