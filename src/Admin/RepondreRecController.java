/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Entities.reclamation;
import EspaceClient.ClientController;
import java.io.IOException;
import service.reclamation_service;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class RepondreRecController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private TextField TextN;
      @FXML
    private TextField TextP;
    @FXML
    private TextField TextA;
  private reclamation selectedReclamation;
reclamation_service acr=new reclamation_service();
    @FXML
    private Button back;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveCategoriesAction(ActionEvent event) {
           try {
        
            String obj=TextA.getText();
            String objs=TextP.getText();
            String to = TextN.getText();
            acr.sendMail(to,obj,objs);
        } catch (Exception ex) {
            Logger.getLogger(RepondreRecController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public void initData(reclamation reclamation) {

  selectedReclamation = reclamation;
        TextN.setText(selectedReclamation.getEmail());
         TextP.setText(selectedReclamation.getSujet());
       
    

    }

    @FXML
    private void back(ActionEvent event) {
          Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("AfficherReclamation.fxml"));
                                            back.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
    }
    
}
