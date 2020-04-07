/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Entity.Commande;
import Services.ServiceCommande;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class FXMLCommandeController implements Initializable {
      @FXML
    private TableColumn<Commande, Integer> Id;

    @FXML
    private TableView<Commande> TableCommande;

    @FXML
    private TableColumn<Commande, Integer> id_user;
    @FXML
    private TableColumn<Commande, Date> dateCommande;

    @FXML
    private TableColumn<Commande, Date> dateMax;

    @FXML
    private TableColumn<Commande, Integer> etat_vendu;

    @FXML
    private TableColumn<Commande, Integer> etatLocation;
    
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private StackPane rootPane;

  
    private int CurrentSelected;
    @FXML
    private TextField rech;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       buildTableviewCommandeData();
      

    
}
 
   
   
    private void buildTableviewCommandeData() {
       
         ObservableList <Commande> P;
 ServiceCommande cs = new ServiceCommande();
        P =  cs.FindAll();
     Id.setCellValueFactory(new PropertyValueFactory<>("id"));
       dateCommande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        dateMax.setCellValueFactory(new PropertyValueFactory<>("dateMax"));
         id_user.setCellValueFactory(new PropertyValueFactory<>("id_user"));
        etat_vendu.setCellValueFactory(new PropertyValueFactory<>("etat_vendu"));
       
        etatLocation.setCellValueFactory(new PropertyValueFactory<>("etatLocation"));

  

        TableCommande.setItems(P);

    }
     public void buildTableviewRecherche() {

        if (rech.getText().equals(" ")) {
            buildTableviewCommandeData();
            System.out.println("null");
        } else {
            ServiceCommande sc = new ServiceCommande();

            ObservableList<Commande> data = sc.listerRecherche(rech.getText());

            TableCommande.setItems(data);

        }
    }
     
    @FXML
    void RechercheCommande(KeyEvent event) {
buildTableviewRecherche();
    }

    
      @FXML
    void SupprimerCommande(ActionEvent event) {
ServiceCommande sa= new ServiceCommande();
 Commande A = TableCommande.getSelectionModel().getSelectedItem();
          System.out.println(A);
     sa.remove(A);
       buildTableviewCommandeData();
      
    }
    
}
