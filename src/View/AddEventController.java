/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entites.Event;
import Service.EventServices;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author root
 */
public class AddEventController implements Initializable {

    ObservableList<String> options=FXCollections.observableArrayList("Evénement sportif","Bourse aux velos","Balade avec les velos");

    @FXML
     Button btSaveE;
    @FXML
     Button btPhoto;
    @FXML
     DatePicker dpDate_event;
    @FXML
     TextField tfPrice;
    @FXML
     TextField tfNom;
    @FXML
     TextField tfLieu_event;
    @FXML
     TextField tfNbr_participant;
    @FXML
     TextArea taDescription;
    @FXML
    private AnchorPane anchorPane;
    @FXML
     Label Nbr_participant;
    @FXML
     Label Categories;
    @FXML
     Label Photo;
    @FXML
     Label Lieu_event;
    @FXML
     Label Description;
    @FXML
     Label Nom;
    @FXML
     Label Price;
    @FXML
     Label Date_event; 
    @FXML
     private ChoiceBox cbCategories;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbCategories.setItems(options);
    }    
         
    @FXML
    private void Save(ActionEvent event) throws SQLException {
        if (dpDate_event.getEditor().getText().length() == 0 || tfPrice.getText().length() == 0 || tfNom.getText().length() == 0 || tfLieu_event.getText().length() == 0 || tfNbr_participant.getText().length() == 0 || taDescription.getText().length() == 0)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("veuillez remplir!!");
            alert.setHeaderText("WARNING !");
            alert.setContentText("some field are empty !!");
            alert.showAndWait();
        }
        if (Float.parseFloat(tfPrice.getText())<0){
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez saisir un valeur positif !!");
            alert3.showAndWait();  
            }
        else{
            
            Event ev= new Event(1, 2, tfNom.getText(), Date.valueOf(dpDate_event.getValue()), taDescription.getText(), tfLieu_event.getText(),btPhoto.getText(), Double.valueOf(tfPrice.getText()),Integer.parseInt(tfNbr_participant.getText()), 0);
           
               try {
                      EventServices   es= new EventServices();
                      es.AjouterEvent(ev);
                      Alert alert =new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("Ajout terminé");
                      alert.setHeaderText(null);
                      alert.setContentText("L'evenement : " +ev.getNom()+"  est ajoutée avec succès ");
                      alert.showAndWait();
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println("ajout terminé");   
        }
    }
    
     @FXML
    private void choiceFileAction(ActionEvent event) throws IOException {

        Stage primary = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selectionner une image");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(primary);
        String path = "C:\\wamp64\\www";
        btPhoto.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    }
}
