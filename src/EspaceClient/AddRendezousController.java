/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;
import EspaceClient.ListReparateurController;
import Entities.fos_user;
import Entities.reclamation;
import Entities.rendezvous;
import service.Rendezvous_service;
import service.fos_user_service;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddRendezousController implements Initializable {
 ObservableList<String> oblistType = FXCollections.observableArrayList("DIRECTION ET ROUES","FREINS","SUSPENSION");
 
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ChoiceBox<String> combo_type;
    @FXML
    private TextArea message_txt;
private fos_user selectedfos_user;

    @FXML
    private Button save;
    fos_user r = new   fos_user();
   
       Rendezvous_service cs = new Rendezvous_service ();
    /**
     * Initializes the controller class.
     */
           ObservableList<fos_user> oblist = FXCollections.observableArrayList();
    @FXML
    private TextArea id_txt;
    @FXML
    private Label lael_txt;
    @FXML
    private Button btn_back;
    @FXML
    private Label email_txt11;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       combo_type.setItems(oblistType);
    }    



    @FXML
    private void btn_addRendezvous(ActionEvent event) throws IOException, ParseException {
        
         String typepanne =combo_type.getValue().toString();
       // String adresse = adresse_txt.getText().toString();
      
         String message = message_txt.getText().toString();
         String user = id_txt.getText().toString();
          //String numtel = num_txt.getText().toString();
          
            if (
                message_txt.getText().isEmpty()
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Empty");
            alert.setContentText("message can't be empty !!!!");
            alert.showAndWait();
        }
          
           /*if (num_txt.getText().length()>8 || num_txt.getText().length()<8)
           {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               
               alert.setHeaderText("Phone number ");
               alert.setContentText("should be = 8");
               alert.showAndWait();
               
           }*/
         /*if (this.validateEmailAddress(email)==false){
          Alert alert = new Alert(Alert.AlertType.ERROR);
               
               alert.setHeaderText("erreur email ");
               alert.setContentText("erreur email");
               alert.showAndWait();
         
         }*/
          
          
    
    rendezvous s = new rendezvous();
     //s.setEmail(email);
       //s.setAdresse(adresse);
        s.setTypepanne(typepanne);
        
        
        
       
       s.setMessage(message);
         // s.setNumtel(Integer.parseInt(num_txt.getText()));
           s.setUser(Integer.parseInt(id_txt.getText()));
      
           Rendezvous_service cs = new Rendezvous_service() ;
              cs.insertPStatement(s);
       
    }
     public void initData(fos_user fos_user) {
 selectedfos_user = fos_user;
          
         id_txt.setText(String.valueOf(selectedfos_user.getId()));
          id_txt.setText(selectedfos_user.getId()+""); 
 
  
  
   
        
       
    

    }
      /*public static boolean validateEmailAddress(String email) { 
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; 
        Pattern pattern = Pattern.compile(regex); 
        return pattern.matcher(email).matches(); }*/

        @FXML
    private void back(ActionEvent event) {

                Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("ListReparateur.fxml"));
                                            btn_back.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                  
    }

 
    
}
