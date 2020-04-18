/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import entities.Velo;

import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.net.MalformedURLException;
import java.net.URL;
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
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.VeloService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeBikeLocationController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
 
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

    
    
    Velo v=new Velo();
        VeloService sv =new VeloService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<Velo>data=FXCollections.observableArrayList();

        try {
            data.addAll(sv.readAll());
        } catch (SQLException ex) {
            Logger.getLogger(ListeBikeLocationController.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println(data.size());

            description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                            price_location.setCellValueFactory(new PropertyValueFactory<>("price_location"));

 
        localitsation_velo.setCellValueFactory(new PropertyValueFactory<>("localitsation_velo"));
        
        
      
        tabv.setItems(data);
        

  
    } 
    
    
    
    
    
    
    private void setCellTableNormale() throws SQLException {
            
    ObservableList<Velo>data=FXCollections.observableArrayList();

    data.addAll(sv.readAll());
            System.out.println(data.size());

            description.setCellValueFactory(new PropertyValueFactory<>("description"));
                    quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
                            price_location.setCellValueFactory(new PropertyValueFactory<>("price_location"));


        localitsation_velo.setCellValueFactory(new PropertyValueFactory<>("localitsation_velo"));
        
        
      
        tabv.setItems(data);
    }
    
    




    @FXML
    private void deleteVeloAction(ActionEvent event) throws SQLException, MalformedURLException 
    {
    VeloService vs = new VeloService();
        Velo v = (Velo) tabv.getSelectionModel().getSelectedItem();
        vs.delete(v);
   
                    tabv.getItems().clear();
vs.readAll();                    
setCellTableNormale();
        vs.readAll();
     try {
                Notification.sendNotification("Success", "Bike of location removed with successful",TrayIcon.MessageType.INFO);
            } catch (AWTException ex) {
                Logger.getLogger(VeloService.class.getName()).log(Level.SEVERE, null, ex);     
} 
    }

}
