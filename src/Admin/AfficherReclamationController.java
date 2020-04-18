/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;
import Entities.reclamation;
import Entities.rendezvous;
import service.reclamation_service;
import connection.ConnexionBD;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private TableView<reclamation> tabc;
    @FXML
    private TableColumn<reclamation, String> name_txt;
    @FXML
    private TableColumn<reclamation,String > prenom_txt;
    @FXML
    private TableColumn<reclamation, String> email_txt;
    @FXML
    private TableColumn<reclamation, String> about_txt;
    @FXML
    private TableColumn<reclamation, String> message_txt;
    @FXML
    private TableColumn<reclamation, Integer> num_txt;

 ObservableList<reclamation> oblist = FXCollections.observableArrayList();
    // ObservableList<reclamation> oblist ;
 Connection c= ConnexionBD.getInstance().getCnx();
  reclamation_service cs = new reclamation_service ();
    reclamation r=new reclamation();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         reclamation_service cr = new reclamation_service ();
         
       
        try {
            cr.afficherReclamation(oblist);
        } catch (IOException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
          name_txt.setCellValueFactory(new PropertyValueFactory<reclamation ,String>("nom"));
        prenom_txt.setCellValueFactory(new PropertyValueFactory<reclamation ,String>("prenom"));
        email_txt.setCellValueFactory(new PropertyValueFactory<reclamation ,String>("email"));
         about_txt.setCellValueFactory(new PropertyValueFactory<reclamation ,String>("sujet"));
          message_txt.setCellValueFactory(new PropertyValueFactory<reclamation ,String>("message"));
          num_txt.setCellValueFactory(new PropertyValueFactory<reclamation ,Integer>("numtel"));
      tabc.setItems(oblist);
    }    


    @FXML
    private void UpdateCategoriesAction(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("RepondreRec.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        RepondreRecController controller = loader.getController();
        controller.initData(tabc.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }

    @FXML
    public void delete(ActionEvent event) throws IOException{ 
            reclamation selected = tabc.getSelectionModel().getSelectedItem();
            if(selected==null)
            {
                Alert dialogW = new Alert(Alert.AlertType.WARNING);
                dialogW.setTitle("Fenêtre d'alerte !");
                dialogW.setContentText("selected reclamation");
                dialogW.showAndWait();
            }else{   
                Alert dialogC = new Alert(Alert.AlertType.CONFIRMATION);
                dialogC.setTitle("Confirm delete");
                dialogC.setHeaderText(null);
                dialogC.setContentText("you have to delete reclamation ?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) 
                {
                    int selectedReference = selected.getReference();
                    cs.deleteRec(selectedReference );
                    tabc.getItems().clear();
                  cs.afficherReclamation(oblist);
             
                    tabc.setItems(oblist); 
                    //System.out.println("User chose OK");
                }
                else {
                System.out.println("User chose Cancel or closed the dialog-box");
                }
            }
            
           
            //}
    }
    
}
