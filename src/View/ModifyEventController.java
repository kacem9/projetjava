/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entites.Categories_event;
import Entites.Event;
import Service.CategoriesServices;
import Service.EventServices;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author root
 */
public class ModifyEventController implements Initializable {
 ObservableList<String> options=FXCollections.observableArrayList();
    @FXML
     Button btnModifiyE;
    @FXML
     Button btnPhoto;
    @FXML
     DatePicker dpnDate_event;
    @FXML
     TextField tfnPrice;
    @FXML
     TextField tfnNom;
    @FXML
     TextField tfnLieu_event;
    @FXML
     TextField tfnNbr_participant;
    @FXML
     TextArea tanDescription;
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
    private ImageView tf_image_view;
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
     private ComboBox<Categories_event> cbnCategories;
    Event e;
    Event ev = new Event();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //cbnCategories.setItems(options);
        Platform.runLater(()->{
            System.out.println(e);
             dpnDate_event.getEditor().setText(String.valueOf(e.getDate_event()));
               tfnPrice.setText(String.valueOf(e.getPrix()));
               tfnNom.setText(e.getNom());
               tfnLieu_event.setText(e.getLieu_event());
               tfnNbr_participant.setText(String.valueOf(e.getNbr_participant()));
               tanDescription.setText(e.getDescription());
               CategoriesServices cs = new CategoriesServices();
            try {
                cbnCategories.setValue(cs.getCategorieById(e.getCategories_id()));
            } catch (SQLException ex) {
                Logger.getLogger(ModifyEventController.class.getName()).log(Level.SEVERE, null, ex);
            }
               btnPhoto.setText(e.getPhoto());
               
               FileInputStream input;
            try {
                input = new FileInputStream("C:\\\\wamp64\\\\www\\\\" + e.getPhoto() + "");
                Image image = new Image(input);
                tf_image_view.setImage(image);
            } catch (FileNotFoundException ex) {
                System.out.println("error");
            }
    });    
    }
    
    @FXML
    private void Modifiy(ActionEvent event) throws SQLException, IOException {
        
        if (ev == null) {

            System.out.println("choisir un evenement");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Modify event");
            alert.setHeaderText(null);
            alert.setContentText("the event is not modified !");

            alert.showAndWait();
        }else {
            ev.setPrix(Double.valueOf(tfnPrice.getText()));
            ev.setNom(tfnNom.getText());
            ev.setLieu_event(tfnLieu_event.getText());
            ev.setNbr_participant(Integer.valueOf(tfnNbr_participant.getText()));
            ev.setDescription(tanDescription.getText());
            ev.setPhoto(btnPhoto.getText());
            //a mmodifier !!!!
            ev.setCategories_id(cbnCategories.getValue().getId());
            try {
               EventServices es = new EventServices();
                System.out.println("azaz.............");

                System.out.println(ev.toString());
               es.ModifierEvent(ev);

        } catch (Exception ex) {
                System.out.println("ex.............");
                ex.printStackTrace();
        }
            System.out.println("Modification terminé");
           
           String title = ev.getPhoto();
           System.out.println(title);
           FileInputStream input = new FileInputStream("C:\\wamp64\\www\\" + title + "");
         //  Image image = new Image(input);
        //ImageView view = new ImageView();
        //tf_image_view.setImage(image);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Evenement enregistré avec succès.");
        alert.setHeaderText(null);
//        alert.setContentText("L'evenement " + e.getNom() + " has been modified.");
        alert.showAndWait();
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
        btnPhoto.setText(file.getName());
        if (file != null) {
            try {
                Files.copy(file.toPath(), new File(path + "\\" + file.getName()).toPath());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }
    }
    
    void setData(int id, String Nom, Date Date_event, String Description, String Lieu_event, String Photo, Double Prix, int Nbr_participant) {
       ev.setId(id);
       tfnNom.setText(Nom);
       dpnDate_event.getEditor().setText(String.valueOf(Date_event));
       tanDescription.setText(Description);
       tfnLieu_event.setText(Lieu_event);
       btnPhoto.setText(Photo);
       tfnPrice.setText(String.valueOf(Prix));
       tfnNbr_participant.setText(String.valueOf(Nbr_participant));
    }
}
