/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entites.Categories_event;
import Entites.Event;
import Entites.Pdf;
import Service.EventServices;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author root
 */
public class EventController implements Initializable {
    @FXML
    private Button add;
    @FXML
    private Button Modifiy;
    @FXML
    private Button remove;
    @FXML
    private Button Print;
    @FXML
     private TableColumn<Event, String> tcNom;
    @FXML
     private TableColumn<Event, Categories_event> tcCategories;
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
    private int as ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @FXML
    private void ajouterEventAction(ActionEvent event)  {
            
   FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/View/AddEvent.fxml"));
        try {
            Parent root = loader.load();
            add.getScene().setRoot(root);
                            
        } catch (IOException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
        private void TableEvent(ActionEvent event) {
        
        
        tvEvent.getSelectionModel().getSelectedItem();   
     }
        
  @FXML
    private void modifierEventAction(ActionEvent event) throws IOException  {
             Event e=tvEvent.getSelectionModel().getSelectedItem();
      System.out.println(e);
if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
          try {   
        FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("/View/ModifyEvent.fxml"));
        Scene scene=new Scene(loader.load());
        

        ModifyEventController mc= loader.getController();
        Stage stageAff=new Stage();
        stageAff.setScene(scene);
        stageAff.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
        as=tvEvent.getSelectionModel().getSelectedItem().getId();
        String Nom = tvEvent.getSelectionModel().getSelectedItem().getNom();
        Date Date_event = tvEvent.getSelectionModel().getSelectedItem().getDate_event();
        String Description = tvEvent.getSelectionModel().getSelectedItem().getDescription();
        String Lieu_event = tvEvent.getSelectionModel().getSelectedItem().getLieu_event();
        String Photo = tvEvent.getSelectionModel().getSelectedItem().getPhoto();
        Double Prix = tvEvent.getSelectionModel().getSelectedItem().getPrix();
        int Nbr_participant = tvEvent.getSelectionModel().getSelectedItem().getNbr_participant();
        
        mc.setData(
                 tvEvent.getSelectionModel().getSelectedItem().getId(),
                 tvEvent.getSelectionModel().getSelectedItem().getNom(),
                 tvEvent.getSelectionModel().getSelectedItem().getDate_event(),
                 tvEvent.getSelectionModel().getSelectedItem().getDescription(),
                 tvEvent.getSelectionModel().getSelectedItem().getLieu_event(),
                 tvEvent.getSelectionModel().getSelectedItem().getPhoto(),
                 tvEvent.getSelectionModel().getSelectedItem().getPrix(),
                 tvEvent.getSelectionModel().getSelectedItem().getNbr_participant()
        );
        } catch(Exception ex)
    {
     System.out.println("eer");
}
        }
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
            tcPrice.setCellValueFactory(new PropertyValueFactory<>("Prix"));

            tvEvent.setItems(data);
        } catch (SQLException ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
        private void SupprimerEventAction(ActionEvent event) throws SQLException {
            Event e=tvEvent.getSelectionModel().getSelectedItem();
        
        if(e==null){
        
           System.out.println("Aucun événement séléctionné");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Aucun événement séléctionné");

            alert.showAndWait();
     
        }else {
            EventServices es=new EventServices();
            String nom_P=e.getNom();
            try {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Supprimer Evenement");
                alert.setHeaderText(null);
                alert.setContentText("Etes-vous sur de vouloir supprimer l'évenement " +" "+ e.getNom());
                Optional<ButtonType> action = alert.showAndWait();
                if (action.get() == ButtonType.OK) {
                    // System.out.println("sup1");
                    es.SupprimerEvent(e);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Succés!");
                    alert1.setHeaderText(null);
                    alert1.setContentText("Evenement supprimé!");

                    alert1.showAndWait();
                }
            } catch (Exception ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
         loadData();
         refresh();
        }
        
        }
        
         @FXML
    private void generatepdf(ActionEvent event) throws DocumentException, BadElementException, IOException, FileNotFoundException, InterruptedException, SQLException {
        Pdf pd=new Pdf();
        try{
        pd.GeneratePdf("list of event");
            System.out.println("impression done");
        } catch (Exception ex) {
            Logger.getLogger(EventController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
