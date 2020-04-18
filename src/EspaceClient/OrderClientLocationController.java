/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;

import EspaceVendeur.EspaceLocationController;
import com.jfoenix.controls.JFXButton;
import entities.Commande;
import entities.Velo;
import entities.ligne_de_commande;

import entities.user;
import service.CommandeService;
import service.userService;
import utils.Session;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import service.CategoriesService;
import service.LigneCommandeServices;
import service.VeloService;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class OrderClientLocationController implements Initializable {

    private CommandeService cs;
    @FXML
    private AnchorPane orders;
    @FXML
    private TableView<Commande> orderstable;
    @FXML
    private TableColumn<Commande, Date> dateCommande;
 Button bt1=new Button();
    @FXML
    private TableColumn<Commande, Date> dateMax;
        @FXML
    private TableColumn                      actions;
    @FXML
    private Label label;
  Date date=  new java.sql.Date(System.currentTimeMillis());

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    String Nom=Session.getUser().getUsername();
        
        label.setText(Nom);
        ObservableList<String> s = FXCollections.observableArrayList();
                s.addAll(Session.getUser().getUsername());

        cs= new CommandeService();
        cs.findAll();

        dateCommande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        dateMax.setCellValueFactory(new PropertyValueFactory<>("dateMax"));
      
        

        Callback<TableColumn<Commande, String>, TableCell<Commande, String>> cellFactory;

        cellFactory = (TableColumn<Commande, String> param) -> {
                          final TableCell<Commande, String> cell = new TableCell<Commande, String>() {
                              @Override
                              public void updateItem(String Item, boolean empty) {
                                  super.updateItem(Item, empty);

                                  if (empty) {
                                      setGraphic(null);
                                      setText(null);
                                  } else {
                                      Commande c = getTableView().getItems().get(getIndex());

                                      // action button here
                                      final JFXButton setDeliveredButton = new JFXButton("Remove");
                                      if (c.getDateMax().after(date)) {
                                          setDeliveredButton.setDisable(false);
                                      }

                                      setDeliveredButton.getStyleClass().add("custom-button");
                                      setDeliveredButton.getStyleClass().add(".jfx-button");
                                      setDeliveredButton.setOnAction(
                                          event -> {
                                              int ct = getTableView().getItems().get(getIndex()).getId();
Commande ctt = getTableView().getItems().get(getIndex());
                                              setDeliveredButton.setDisable(true);
                                             
                                            
                                       LigneCommandeServices lc=new LigneCommandeServices();
                                                                 ArrayList<ligne_de_commande> obs= (ArrayList<ligne_de_commande>) lc.findAll(ct);
                                           
                                        for (ligne_de_commande css : obs)
                   cs.supp(ct);                          {
                  cs.delete(ct);    
                          cs.findAll();

                                        
                                          try {
                                              setCellTableNormale();
                                          } catch (SQLException ex) {
                                              Logger.getLogger(OrderClientLocationController.class.getName()).log(Level.SEVERE, null, ex);
                                          }
                                          }
                                          });
                                      setGraphic(setDeliveredButton);
                                  }
                              }
                          };

                          return cell;
                      };
        actions.setCellFactory(cellFactory);
        orderstable.setItems(cs.findAll());
    }
 private void setCellTableNormale() throws SQLException {

              String Nom=Session.getUser().getUsername();
        
        label.setText(Nom);
        ObservableList<String> s = FXCollections.observableArrayList();
                s.addAll(Session.getUser().getUsername());

        cs= new CommandeService();
        cs.findAll();

        dateCommande.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        dateMax.setCellValueFactory(new PropertyValueFactory<>("dateMax"));
      
            
            
          
     
        
      
    }


}

   


