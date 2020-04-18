/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import entities.Categories;
import service.CategoriesService;
import service.VeloService;
import java.io.IOException;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ModifierCatgeoriesController implements Initializable {

    @FXML
    private ImageView image;
 @FXML
  private TextField TextA;
  @FXML
   private TextField TextN;
  Categories c=new Categories();

    CategoriesService cs=new CategoriesService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
   private void saveCategoriesUpdatedAction(ActionEvent event) throws SQLException, IOException {

         
 
           if (TextN.getText().isEmpty()
                || TextA.getText().isEmpty()
               
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
           
          if(!TextN.getText().matches("[a-z A-Z]+")
                && (!TextA.getText().matches("[a-z A-Z]+")))
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez respecter format");
            alert.setContentText("respecter format");
            alert.showAndWait();
        }
           
        
        
        else {
              
                   
        c.setNom(TextN.getText());
            c.setAge(TextA.getText());

       

    c.setId(c.getId());
               cs.UpdateCategorie(c);
               
               AnchorPane pane   = FXMLLoader.load(getClass().getResource("/Admin/AfficherCategories.fxml"));
               
               Stage stage = new Stage();
               stage.setScene(new Scene(pane));
               stage.show() ; } 
           }  


    void setData(String nom, String age, int id) {
    
     c.setId(id);
       TextN.setText(nom);
       TextA.setText(age);
}
}