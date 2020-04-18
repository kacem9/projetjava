/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;

import service.PanierServices;
import utils.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.MalformedURLException;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author HP
 */
public class CheckoutController implements Initializable {

  @FXML
    private AnchorPane pane; 
    @FXML
    private RequiredFieldValidator validator;
    @FXML
    private JFXTextField addresse;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField tel;
       @FXML
    private JFXTextField ville;
    @FXML
    private Button Confirm;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        name.setText( Session.getInstance().getUser().getUsername());
        
        name.setDisable(true);
addresse.getValidators().add(validator);
        tel.getValidators().add(validator);
           ville.getValidators().add(validator);
       
      
        addresse.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                addresse.validate();
            }
            
        });
        tel.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                tel.validate();     
            }
        });     
 ville.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                ville.validate();     
            }
        });     

       
  
    }  
  @FXML
     private void confirmAction(ActionEvent event) throws SQLException, MalformedURLException {
              if(addresse.validate()&&tel.validate()&&ville.validate()&&addresse.getText().matches("[a-z A-Z]+")&& tel.getText().matches("[0-9]*")&&tel.getText().length()==8  )
        
               
              {
         
            PanierServices pas = new PanierServices();
            System.out.println(addresse.getText()); 
            System.out.println(ville.getText()); 
            System.out.println(tel.getText()); 
           pas.validerPanier(addresse.getText(),ville.getText(),tel.getText());
            Alert dialogW = new Alert(Alert.AlertType.INFORMATION);
                dialogW.setTitle("Fenêtre d'alerte !");
                dialogW.setContentText("Succeful, order added ");
                dialogW.showAndWait();
       
                }  
           
           else{
           Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
            alert.close();        }}
           
    }



