/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;

import entities.Velo;
import service.PanierServices;
import service.ProductServices;
import utils.Session;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CartController implements Initializable {

    @FXML
  Integer                                                  prix = 0;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> items;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> quantity;
    @FXML
    private TableColumn<Map.Entry<Integer, Integer>, String> price;
    @FXML
    private Label                                            total;
    @FXML
    private TableView<Map.Entry<Integer, Integer>>           table;
    ProductServices                                          ps;
    @FXML
    private Button                                        valider;
        @FXML
    private Button                                        remove;
    @FXML
    private AnchorPane                                       cart;
    private HBox                                             main;
    PanierServices                                           pas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         Map m = Session.getInstance().getPanier();

        if (m.isEmpty()) {
            valider.setDisable(true);
             remove.setDisable(true);
        }

      ProductServices  ps   = new ProductServices();
     
      PanierServices  pas  = new PanierServices();
    int    prix = pas.getPrixPanier();
        items.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>,
                                               ObservableValue<String>>() {
                                      @Override
                                      public ObservableValue<String> call(
                                              TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> p) {

                                          // this callback returns property for just one cell, you can't use a loop here
                                          // for first column we use key
                                          Velo prod = ps.getP(p.getValue().getKey());
                                          System.out.println(prod); 
                                         return new SimpleStringProperty(prod.getDescription());
                                      }
                                  });
        quantity.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>,
                                                  ObservableValue<String>>() {
                                         @Override
                                         public ObservableValue<String> call(
                                                 TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> p) {

                                             // this callback returns property for just one cell, you can't use a loop here
                                             // for first column we use key
                                             return new SimpleStringProperty(p.getValue().getValue().toString());
                                         }
                                     });
        price.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String>,
                                               ObservableValue<String>>() {
                                      @Override
                                      public ObservableValue<String> call(
                                              TableColumn.CellDataFeatures<Map.Entry<Integer, Integer>, String> p) {
                                          Velo prod = ps.getP(p.getValue().getKey());
                                          Integer t    = prod.getPrice_location()* p.getValue().getValue();

                                          return new SimpleStringProperty(t.toString());
                                      }
                                  });

        ObservableList<Map.Entry<Integer, Integer>> oblist = FXCollections.observableArrayList(m.entrySet());

        System.out.println(prix);
        total.setText(price.toString());
        table.setItems(oblist);
    }
    
  
    @FXML
    private void buyAction(ActionEvent event) throws IOException {

 try {
     AnchorPane pane = FXMLLoader.load(getClass().getResource("Checkout.fxml"));
 
Stage stage = new Stage();
stage.setScene(new Scene(pane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    } 
        @FXML
    private void RemoveAction(ActionEvent event) throws IOException {

 try {
        Map m = Session.getInstance().getPanier();
int id=table.getSelectionModel().getSelectedItem().getKey();


       
          Map p    = Session.getInstance(Session.getUser()).getPanier();
           p.keySet().clear();
                   } catch(Exception e)
    {
     System.out.println("eer");
}
    
      
    
    }}