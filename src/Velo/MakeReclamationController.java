/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Velo;

import Entities.reclamation;
import Reparateur.ReponseRendezvousController;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import service.Rendezvous_service;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MakeReclamationController implements Initializable {

    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private TextArea id_txt;
    @FXML
    private TextArea message_txt;
    @FXML
    private Button btn_add;
    @FXML
    private Button btn_back;
    @FXML
    private TextArea about_txt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_valid(ActionEvent event) throws ParseException {
         String message = message_txt.getText().toString();
        String about = about_txt.getText().toString();
    
      reclamation s =new reclamation();
  
           s.setMessage(message);
            s.setSujet(about);
       
               Rendezvous_service cs = new Rendezvous_service() ;
                 cs.insertePStatement(s);
    }

    @FXML
    private void back(ActionEvent event) {
          Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("FXML_Product.fxml"));
                                            btn_back.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(MakeReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                  
    }
      public void initData(String text) {
about_txt.setText(text);
  
       
    

    }
    
}
