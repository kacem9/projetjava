/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entites.Event;
import Service.EventServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author root
 */
public class EventController implements Initializable
{
     @FXML
     private TableColumn<Event, String> tcNom;
    @FXML
     private TableColumn<Event, String> tcCategories;
    @FXML
     private TableColumn<Event, String> tcDate_event;
    @FXML
     private TableColumn<Event, String> tcDescription;
    @FXML
     private TableColumn<Event, String> tcNbr_participant;
    @FXML
     private TableColumn<Event, String> tcLieu_event;
    @FXML
     private TableColumn<Event, String> tcPrice;
    @FXML
    private TableView<Event> tvEvent;
    Event ev = new Event();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
       
       if ( ev.getEtat()== 1){
         EventServices es = new EventServices();
        
        List<Event> le = new ArrayList<>();
        try {
            le = (ArrayList<Event>) es.AfficherEvents();
            ObservableList<Event> data = FXCollections.observableArrayList(le);
            FilteredList<Event> fle = new FilteredList(data, e -> true);
            tcNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            tcCategories.setCellValueFactory(new PropertyValueFactory<>("Categories_id"));
            tcDate_event.setCellValueFactory(new PropertyValueFactory<>("Date_event"));
            tcDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            tcNbr_participant.setCellValueFactory(new PropertyValueFactory<>("Nbr_participant"));
            tcLieu_event.setCellValueFactory(new PropertyValueFactory<>("Lieu_event"));
            tcPrice.setCellValueFactory(new PropertyValueFactory<>("Prix"));
            
            tvEvent.setItems(fle);
            int nbe=tvEvent.getItems().size();
            
        }catch (SQLException ex) {
            Logger.getLogger(View.EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
       }else
       {
           System.out.println("il n'y a pas des evenement valider !!!!");
       }
    } 
    
     @FXML
        private void TableEvent(ActionEvent event) {
        
        
        tvEvent.getSelectionModel().getSelectedItem();   
     }
        
        @FXML
        private void ParticiperEventAction(ActionEvent event) throws SQLException { 
             Event e=tvEvent.getSelectionModel().getSelectedItem();
        // ylzem kol admin andou l ha9 yparticipi ken mara f nafs el  event w andou lha9 yannuli ken mara event
        if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }
        }
    
}
