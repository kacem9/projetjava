/*
* To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceVendeur;

import entities.Categories;
import entities.Velo;
import service.CategoriesService;
import service.VeloService;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;

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
  private JFXTextField TextP;
  @FXML
  private JFXTextField TextL;
  @FXML
   private TextField TextQ;
  @FXML
  private JFXTextArea TextD;    
   
 
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
   private JFXComboBox<Categories>cat;
     @FXML
             FileChooser Fc = new FileChooser();
     

 File selectedFile ;

 @Override
    public void initialize(URL url, ResourceBundle rb) {
   ObservableList<Categories>data=FXCollections.observableArrayList();
   
     CategoriesService cs=new CategoriesService();
   data.addAll(cs.readNom());
   data.toString();
       cat.setItems(data);
     
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
   private void saveBikeAction(ActionEvent event) throws SQLException, IOException, AWTException {

         LocalDate date_circulation=dateP.getValue();
               String 	localitsation_velo=TextL.getText();
               
               String quantity=TextQ.getText();
               
                Categories  combo =cat.getValue();
               System.out.println(combo);
               LocalDate datePu=dateP.getValue();
               
               String Description=TextD.getText();
               String price_location=TextP.getText();
               String Quantity=TextQ.getText();
               
            
               
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
               
           }
             
               if(!TextL.getText().matches("[a-z A-Z]+")
                && (!TextD.getText().matches("[a-z A-Z]+")))
                
                {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez respecter le format");
            alert.setContentText("Veuillez remplir tous les champs");
       
            
            
            alert.showAndWait();
            
            
                  }
        
           
        else  if (Integer.parseInt(TextQ.getText())<0 || (Integer.parseInt(TextP.getText()) < 0))
         {
             Alert alert3 = new Alert(Alert.AlertType.WARNING);
            alert3.setTitle(null);
            alert3.setHeaderText("WARNING !");
            alert3.setContentText("veuillez saisir un valeur positif !!");
            alert3.showAndWait();  
            } 
               
        
       
       
           
           
                 
       else {
                String dateP=datePu.toString();
               vs.addBike(Quantity,date_circulation,localitsation_velo,Description,price_location,v,selectedFile,combo);
               
               AnchorPane pane   = FXMLLoader.load(getClass().getResource("EspaceLocation.fxml"));
               
               Stage stage = new Stage();
               stage.setScene(new Scene(pane));
               stage.show() ; } 
           }  
 
}