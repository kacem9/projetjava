/*
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Velo;
import Services.VeloService;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
 * @author HP
 */
public class AddBikeController implements Initializable {

    /**
     * Initializes the controller class.
     */
   ObservableList<String> options=FXCollections.observableArrayList("MountainsBike","hh");


  @FXML
  private AnchorPane anchorPane;
 
  @FXML
  private TextField TextP;
  @FXML
  private TextField TextL;
  @FXML
   private TextField TextQ;
  @FXML
  private TextArea TextD;    
   
 
     Velo v=new Velo();
        VeloService vs =new VeloService();

  @FXML
  private DatePicker dateP;
        @FXML
   private ImageView img;
        
       @FXML
    private Image image ;
     @FXML
   private FileInputStream fis;
     @FXML
             FileChooser Fc = new FileChooser();
     

 File selectedFile ;

 @Override
    public void initialize(URL url, ResourceBundle rb) {
  
     
    }
    @FXML
    private void choiceFileAction(ActionEvent event) throws IOException {

        
        Fc.setTitle("Open fil Dialog");
        Fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        Fc.setInitialDirectory(new File("C:\\wamp64\\www\\Velo"));
        selectedFile = Fc.showOpenDialog(null);
    
try {
               Image imge = new Image(selectedFile.toURI().toURL().toString());
               System.out.println(selectedFile.toURI().toURL().toString());
                this.img.setImage(imge);
           } catch (MalformedURLException ex) {
               Logger.getLogger(AddBikeController.class.getName()).log(Level.SEVERE, null, ex);
           }
      
    
        
}

    @FXML
   private void saveBikeAction(ActionEvent event) throws SQLException, IOException {

         
           if (TextQ.getText().isEmpty()
                || TextL.getText().isEmpty()
                || TextD.getText().isEmpty()
               ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }
           
           
        
        
           if (dateP.getValue()==null)
           {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               
               alert.setHeaderText("Alerte Date");
               alert.setContentText("Veuillez verifier la  date de circulation");
               alert.showAndWait();
               
           }else {
               LocalDate date_circulation=dateP.getValue();
               String 	localitsation_velo=TextL.getText();
               
               String quantity=TextQ.getText();
               
               
               LocalDate datePu=dateP.getValue();
               
               String Description=TextD.getText();
               String price_location=TextP.getText();
               String Quantity=TextQ.getText();
               
               String dateP=datePu.toString();
               
               vs.addBike(Quantity,date_circulation,localitsation_velo,Description,price_location,v,selectedFile);
               
               AnchorPane pane   = FXMLLoader.load(getClass().getResource("/GUI/EspaceLocation.fxml"));
               
               Stage stage = new Stage();
               stage.setScene(new Scene(pane));
               stage.show() ; } 
           }  
 
}