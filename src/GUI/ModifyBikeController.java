/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Velo;
import Services.VeloService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
public class ModifyBikeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ObservableList<String> options=FXCollections.observableArrayList("eya","hh");


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
     
Velo v1=new Velo();
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
   private void updateVeloAction(ActionEvent event) throws SQLException, IOException {
       
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
       VeloService vs=new VeloService();
       
        v1.setDescription(TextD.getText());
            v1.setQuantity(Integer.parseInt(TextQ.getText()));

          v1.setLocalitsation_velo(TextL.getText());
          
        String datePu=dateP.getValue().toString();

   v1.setPrice_location(Float.parseFloat(TextP.getText()));

    v1.setId(v1.getId());
    v1.setDate_circulation(java.sql.Date.valueOf(datePu));
        System.out.println(v1.getId());       

        System.out.println(this.v1.getId());
   vs.updateBike(v1,selectedFile);
        
      AnchorPane pane   = FXMLLoader.load(getClass().getResource("/GUI/EspaceLocation.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();     
}  
   }
    void setData(String description, String localitsation_velo, int quantity,Float price_location, int id,String date_circulation) {
     v1.setId(id);
       TextD.setText(description);
       TextL.setText(localitsation_velo);
       TextQ.setText(String.valueOf(quantity));
        TextP.setText(String.valueOf(price_location));
        dateP.setValue(LocalDate.parse(date_circulation));
    }

  
 

   
   
    
}
