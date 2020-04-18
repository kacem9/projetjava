/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceVendeur;

import entities.Categories;
import entities.Velo;
import entities.user;
import service.CategoriesService;
import service.VeloService;
import service.userService;
import connection.ConnectionDB;
import utils.Session;
import com.jfoenix.controls.JFXTextField;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    private TableColumn<Velo,Integer> price_location;
                        @FXML
    private TableColumn<Velo,LocalDate> date_circulation;
                              @FXML
    private TableColumn<Velo,Categories> categories;
                              @FXML
                                      private JFXTextField filterField;
              ObservableList<String> options=FXCollections.observableArrayList("eya","hh");
userService us=new userService();

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
                 ObservableList obs = FXCollections.observableArrayList();
     
 private Connection connexion;
VeloService sv=new VeloService();
int id=Session.getUser().getId();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      


        VeloService sousCatService= new VeloService();
        ArrayList<Velo> lesSousCategories = new ArrayList<>();
        try {
              
            lesSousCategories = (ArrayList<Velo>) sousCatService.readAl();
            System.out.println(lesSousCategories);
            for(int i=0;i<lesSousCategories.size();i++){
                
                CategoriesService catService = new CategoriesService();
                lesSousCategories.get(i).setCategories_id(catService.getCategorieById(
                        lesSousCategories.get(i).getCategories_id().getId()));
                System.out.println(lesSousCategories);
            }
            
            
          
        } catch (SQLException ex) {
            Logger.getLogger(EspaceLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList obs = FXCollections.observableArrayList(lesSousCategories);
        tabv.setItems(obs);     
            
            
        System.out.println(obs);
                       categories.setCellValueFactory(new PropertyValueFactory<>("categories_id"));
description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                            price_location.setCellValueFactory(new PropertyValueFactory<>("price_location"));

date_circulation.setCellValueFactory(new PropertyValueFactory<>("date_circulation"));
        localitsation_velo.setCellValueFactory(new PropertyValueFactory<>("localitsation_velo"));
      FilteredList<Velo> filteredData = new FilteredList<>(obs, b -> true);
     filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getCategories_id().getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				
                                } else if (employee.getLocalitsation_velo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                  else if (String.valueOf(employee.getQuantity()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                      else if (String.valueOf(employee.getPrice_location()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                            else if (String.valueOf(employee.getDate_circulation()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
        
        	});
        SortedList<Velo> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabv.setItems(sortedData);   
    }    
    public void populate() {
        VeloService sousCatService= new VeloService();
        ArrayList<Velo> lesSousCategories = new ArrayList<>();
        try {
              
            lesSousCategories = (ArrayList<Velo>) sousCatService.readAll();
            System.out.println(lesSousCategories);
            for(int i=0;i<lesSousCategories.size();i++){
                
                CategoriesService catService = new CategoriesService();
                lesSousCategories.get(i).setCategories_id(catService.getCategorieById(
                        lesSousCategories.get(i).getCategories_id().getId()));
                System.out.println(lesSousCategories);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EspaceLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList obs = FXCollections.observableArrayList(lesSousCategories);
        tabv.setItems(obs);
 
   }
    @FXML
    private void addBikeAction(ActionEvent event) {
        try {   
      AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("AddBike.fxml"));
  
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


    Integer price_location=tabv.getSelectionModel().getSelectedItem().getPrice_location();
       
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
    private void deleteAction(ActionEvent event) throws SQLException, MalformedURLException 
    {
    VeloService vs = new VeloService();
        Velo v = (Velo) tabv.getSelectionModel().getSelectedItem();
        vs.delete(v);
   
      //tabv.getItems().clear();
vs.readAll();                    
setCellTableNormale();
        vs.readAll();
          try {
                Notification.sendNotification("Success", "Bike of location removed with successful",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);     
}
    
    }


    private void setCellTableNormale() throws SQLException {
 VeloService sousCatService= new VeloService();
        ArrayList<Velo> lesSousCategories = new ArrayList<>();
        try {
              
            lesSousCategories = (ArrayList<Velo>) sousCatService.readAl();
            System.out.println(lesSousCategories);
            for(int i=0;i<lesSousCategories.size();i++){
                
                CategoriesService catService = new CategoriesService();
                lesSousCategories.get(i).setCategories_id(catService.getCategorieById(
                        lesSousCategories.get(i).getCategories_id().getId()));
                System.out.println(lesSousCategories);
            }
            
            
          
        } catch (SQLException ex) {
            Logger.getLogger(EspaceLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObservableList obs = FXCollections.observableArrayList(lesSousCategories);
        tabv.setItems(obs);     
            
            
        System.out.println(obs);
                       categories.setCellValueFactory(new PropertyValueFactory<>("categories_id"));
description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                            price_location.setCellValueFactory(new PropertyValueFactory<>("price_location"));

date_circulation.setCellValueFactory(new PropertyValueFactory<>("date_circulation"));
        localitsation_velo.setCellValueFactory(new PropertyValueFactory<>("localitsation_velo"));
      FilteredList<Velo> filteredData = new FilteredList<>(obs, b -> true);
     filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(employee -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (employee.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (employee.getCategories_id().getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				
                                } else if (employee.getLocalitsation_velo().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                  else if (String.valueOf(employee.getQuantity()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                      else if (String.valueOf(employee.getPrice_location()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                            else if (String.valueOf(employee.getDate_circulation()).indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
        
        	});
        SortedList<Velo> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabv.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabv.setItems(sortedData);   
        
      
    }
   
    public Velo findVelo()
    {
      
       Velo selected = tabv.getSelectionModel().getSelectedItem();   
  return selected;
    }
}

