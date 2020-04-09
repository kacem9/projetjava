/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Entites.Event;
import Entites.Fos_User;
import Entites.Participation;
import Service.EventServices;
import Service.ParticipationServices;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
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
        List<Event> le = new ArrayList<>();
        EventServices es = new EventServices();

        try {
            le = (ArrayList<Event>) es.AfficherAcceptedEvents();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if ( !le.isEmpty()){

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

        }else
       {
           System.out.println("il n'y a pas des evenement valider !!!!");
       }
    } 
    
     @FXML
        private void TableEvent(ActionEvent event) {
        
        
        tvEvent.getSelectionModel().getSelectedItem();   
     }
        
             public void loadData() throws SQLException{
    ObservableList<Event> dataa = null;

    dataa = FXCollections.observableArrayList(new EventServices().AfficherEvents());
    }
    
     void refresh() throws SQLException {
         try {
           EventServices es = new EventServices();
        

           ArrayList<Event> le;
       
            le = (ArrayList<Event>) es.AfficherEvents();
            ObservableList<Event> data = FXCollections.observableArrayList(le);
            tcNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
            tcCategories.setCellValueFactory(new PropertyValueFactory<>("Categories"));
            tcDate_event.setCellValueFactory(new PropertyValueFactory<>("Date_event"));
            tcDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            tcNbr_participant.setCellValueFactory(new PropertyValueFactory<>("Nbr_participant"));
            tcLieu_event.setCellValueFactory(new PropertyValueFactory<>("Lieu_event"));
            tcPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));

            tvEvent.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(View.EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
        @FXML
        private void ParticiperEventAction(ActionEvent event) throws SQLException { 
             Event e=tvEvent.getSelectionModel().getSelectedItem();
             Participation p = new Participation();
             Fos_User u = new Fos_User(1, "kacem", "yedes", "yedes@esprit.tn", "yedes@esprit.tn", "azerty", "chef d'equipe", 1, "kacem", "yedes", "homme", 55845804, "korba", "korba", "korba", "korba", "korba", "8070");
        // ylzem kol admin andou l ha9 yparticipi ken mara f nafs el  event w andou lha9 yannuli ken mara event
        if(e==null){
            System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");
            alert.showAndWait();
        }
        else {
            ParticipationServices ps = new ParticipationServices();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Participer à l'Evenement");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir de participerà l'évenement " +" "+ e.getNom());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    ps.Participer(e, u);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.showAndWait();
                }
            } catch (Exception ex) {
            Logger.getLogger(View.EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
         loadData();
         refresh();
        }
    }

      @FXML
        private void AnnulerParticipation(ActionEvent event) throws SQLException {
            Event e=tvEvent.getSelectionModel().getSelectedItem();
        if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
            ParticipationServices ps = new ParticipationServices();
            Participation p = new Participation();
            Fos_User u = new Fos_User(1, "kacem", "yedes", "yedes@esprit.tn", "yedes@esprit.tn", "azerty", "chef d'equipe", 1, "kacem", "yedes", "homme", 55845804, "korba", "korba", "korba", "korba", "korba", "8070");
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Annuler la participation");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir Annuler la participation dans l'evenement " +" "+ e.getNom());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    ps.AnnulerParticipation(p, u);
                    p.toString();
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Anuulation done!");

                    alert1.showAndWait();
                }
            } catch (Exception ex) {
            Logger.getLogger(View.EventController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            }
        
         loadData();
         refresh();
        }
        }
}
