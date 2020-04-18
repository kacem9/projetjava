/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;

import entities.Velo;
import service.CategoriesService;
import service.PanierServices;
import service.ProductServices;
import service.VeloService;
import utils.Session;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShopController implements Initializable {

    @FXML
    private TableView<Velo> shoptable;
  
    @FXML
    private TableColumn<Velo, String>  name;
    @FXML
    private TableColumn<Velo, Integer> price_location;
     @FXML
    private TableColumn<Velo, String> description;
        @FXML
    private TableColumn<Velo, Integer> quantity;
    @FXML
    private TableColumn                   add;
    
    @FXML
    private Label total;
 ProductServices ps=new ProductServices();
 VeloService vs=new VeloService();
      PanierServices                        pas = new PanierServices();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ps = new ProductServices();
        VeloService sousCatService= new VeloService();
        ArrayList<Velo> lesSousCategories = new ArrayList<>();
        PanierServices      pas = new PanierServices();
        try {
            
            lesSousCategories = (ArrayList<Velo>) sousCatService.readAll();
            System.out.println(lesSousCategories);
            for(int i=0;i<lesSousCategories.size();i++){
                
                CategoriesService catService = new CategoriesService();
                lesSousCategories.get(i).setCategories_id(catService.getCategorieById(
                        lesSousCategories.get(i).getCategories_id().getId()));
                System.out.println(lesSousCategories);
            }
            
               
        ObservableList obs = FXCollections.observableArrayList(lesSousCategories);
       shoptable.setItems(obs);     
             
        
        } catch (SQLException ex) {
            Logger.getLogger(ShopController.class.getName()).log(Level.SEVERE, null, ex);
        }
        name.setCellValueFactory(new PropertyValueFactory<>("categories_id"));
        price_location.setCellValueFactory(new PropertyValueFactory<>("price_location"));
               description.setCellValueFactory(new PropertyValueFactory<>("description"));
                 quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        Callback<TableColumn<Velo, String>, TableCell<Velo, String>> cellFactory;
        cellFactory = (TableColumn<Velo, String> param) -> {
            final TableCell<Velo, String> cell = new TableCell<Velo, String>() {
                @Override
                public void updateItem(String Item, boolean empty) {
                    super.updateItem(Item, empty);
                    
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        
                        // action button here
                        
                        
                        
                        final Button addButton = new Button("add");
                        
                        
                        addButton.setOnAction(
                                event -> {
                                    Velo p = getTableView().getItems().get(getIndex());
                                    System.out.println(p.getPrice_location());
                                 Session.getInstance(Session.getUser()).addToPanier(p.getId(),1);
                                    total.setText(pas.getPrixPanier().toString());
                                });
                                  total.setText(pas.getPrixPanier().toString());

                        setGraphic(addButton);
                    }
                }
            };
            
            return cell;
        };
        add.setCellFactory(cellFactory);
    
    }
     
    
}
