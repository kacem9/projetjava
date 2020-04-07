/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Velo;
import Services.CategoriesService;
import Services.VeloService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AddCategoriesController implements Initializable {

    @FXML
    private ImageView image;
 @FXML
  private TextField TextA;
  @FXML
   private TextField TextN;
    VeloService vs =new VeloService();
    CategoriesService cs=new CategoriesService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
  
    @FXML
   private void saveCategoriesAction(ActionEvent event) throws SQLException, IOException {

         
           if (TextN.getText().isEmpty()
                || TextA.getText().isEmpty()
               
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
           
           
        
        
        else {
              
               String 	Nom=TextN.getText();
               
                String Age=TextA.getText();
               
             
               
               cs.addCategorie(Nom,Age);
               
               AnchorPane pane   = FXMLLoader.load(getClass().getResource("/GUI/AfficherCategories.fxml"));
               
               Stage stage = new Stage();
               stage.setScene(new Scene(pane));
               stage.show() ; } 
           }  
 
} 

