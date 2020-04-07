/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Velo;
import Services.VeloService;
import Utils.ConnectionDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class EspaceLocationController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML 
   private TableView<Velo> tabv;
      

        
               @FXML 
   private TableColumn<Velo,String> description;
                    @FXML 
   private TableColumn<Velo,String> localitsation_velo;
             @FXML
   private TableColumn<Velo,Integer> quantity;
             @FXML
    private TableColumn<Velo,Float> price_location;
                        @FXML
    private TableColumn<Velo,LocalDate> date_circulation;
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
     
private int as;
 File selectedFile ;
              
 private Connection connexion;
VeloService sv=new VeloService();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    ObservableList<Velo>data=FXCollections.observableArrayList();
       
data.addAll(sv.readAll());
            System.out.println(data.size());

        
       
       
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        localitsation_velo.setCellValueFactory(new PropertyValueFactory<>("localitsation_velo"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        price_location.setCellValueFactory(new PropertyValueFactory<>("price_location"));
        
        date_circulation.setCellValueFactory(new PropertyValueFactory<>("date_circulation"));
        
        tabv.setItems(data);
        

    }    

    @FXML
    private void addBikeAction(ActionEvent event) {
        try {   
      AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("/GUI/AddBike.fxml"));
  
Stage stage = new Stage();
stage.setScene(new Scene(anchorPane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }
        
        @FXML
    private void ModifierAction(ActionEvent event)  {
   VeloService sp = new VeloService();
        Velo v = (Velo) tabv.getSelectionModel().getSelectedItem();
           
        try {   
       FXMLLoader pane = new FXMLLoader
                        (getClass()
                         .getResource("ModifyBike.fxml"));
                                         Stage primaryStage=new Stage();
                Parent root = pane.load();
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                
                ModifyBikeController test = pane.getController();
          as=tabv.getSelectionModel().getSelectedItem().getId();

        String  description=tabv.getSelectionModel().getSelectedItem().getDescription();
         String  localitsation_velo=tabv.getSelectionModel().getSelectedItem().getLocalitsation_velo();
       int quantity=tabv.getSelectionModel().getSelectedItem().getQuantity();
        
               String  date_circulation=tabv.getSelectionModel().getSelectedItem().getDate_circulation().toString();


    Float price_location=tabv.getSelectionModel().getSelectedItem().getPrice_location();
       
             test.setData(
                                         
                 

              tabv.getSelectionModel().getSelectedItem().getDescription(),
              tabv.getSelectionModel().getSelectedItem().getLocalitsation_velo(),
               tabv.getSelectionModel().getSelectedItem().getQuantity(),
                tabv.getSelectionModel().getSelectedItem().getPrice_location(),

                  tabv.getSelectionModel().getSelectedItem().getId(),
             
                                           tabv.getSelectionModel().getSelectedItem().getDate_circulation().toString()
             );
        
    } catch(Exception e)
    {
     System.out.println("eer");
}
            
          
    }
        
        
    @FXML
    private void deleteAction(ActionEvent event) 
    {
    VeloService vs = new VeloService();
        Velo v = (Velo) tabv.getSelectionModel().getSelectedItem();
        vs.delete(v);
   
                    tabv.getItems().clear();
vs.readAll();                    
setCellTableNormale();
        vs.readAll();
    
    }


    private void setCellTableNormale() {
            
    ObservableList<Velo>data=FXCollections.observableArrayList();
       
data.addAll(sv.readAll());
            System.out.println(data.size());

        
    
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        localitsation_velo.setCellValueFactory(new PropertyValueFactory<>("localitsation_velo"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price_location.setCellValueFactory(new PropertyValueFactory<>("price_location"));
        
        description.setCellValueFactory(new PropertyValueFactory<>("date_circulation"));
        tabv.setItems(data);
    }
   
    public Velo findVelo()
    {
      
       Velo selected = tabv.getSelectionModel().getSelectedItem();   
  return selected;
    }
}

