/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;

import Entities.rendezvous;
import controllers.*;


import Utils.*;

import Entities.validrendezvous;
import service.Rendezvous_service;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Validrendezvous_service;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author HP
 */
public class ValidrendezvousController implements Initializable {
     ObservableList<validrendezvous> oblist = FXCollections.observableArrayList();
  @FXML
    private Button btn_back;

    @FXML
    private TableView<validrendezvous> tableValidRendezvous;
    @FXML
    private TableColumn<validrendezvous, String> col_message;
    @FXML
    private TableColumn<validrendezvous, String> col_etat;
    @FXML
    private TableColumn<validrendezvous, Date> col_date;
    @FXML
    private TableColumn<validrendezvous, String> col_prix;
    @FXML
    private TableColumn<validrendezvous, String> col_promo;
     private TableColumn<validrendezvous, String> action;
    @FXML
    private TextField txt_arch;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          LocalDateTime myDateObj = LocalDateTime.now();

  
   
              Rendezvous_service cr = new Rendezvous_service ();
             cr.afficherValidRendezvous(oblist);
        
 
      
     
       
        
        col_message.setCellValueFactory(new PropertyValueFactory<>("message"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("dateheure"));
         col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
          col_promo.setCellValueFactory(new PropertyValueFactory<>("promo"));
           action= new TableColumn("DELETE");
     
        javafx.util.Callback<TableColumn<validrendezvous, String>, TableCell<validrendezvous, String>> cellFactory
                = 
                new Callback<TableColumn<validrendezvous, String>, TableCell<validrendezvous, String>>() {
                    @Override
                    public TableCell call(final TableColumn<validrendezvous, String> param) {
                        final TableCell<validrendezvous, String> cell = new TableCell<validrendezvous, String>() {

                            final Button btn = new Button("supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    
                                    btn.setOnAction(event-> {
                                        validrendezvous cn = getTableView().getItems().get(getIndex());
                                        System.out.println(cn.getEmailR()
                                                );
                                        Validrendezvous_service cs = new Validrendezvous_service();
                                        cs.delete(cn);

                                        Parent root;
                                        try {
                                            
                                            root= FXMLLoader.load(getClass().getResource("Validrendezvous.fxml"));
                                             
        
        
                                            btn.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ValidrendezvousController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                
                            tableValidRendezvous.setEditable(true);         
              
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
         btn_back.setOnAction(event -> {
                Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("Client.fxml"));
                                            btn_back.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                 action.setCellFactory(cellFactory);
//      
        tableValidRendezvous.getColumns().add(action);
         tableValidRendezvous.setItems(oblist);
         ///
         FilteredList<validrendezvous> filteredData = new FilteredList<>(oblist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txt_arch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(validrendezvous -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (validrendezvous.getEmailR().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (validrendezvous.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<validrendezvous> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableValidRendezvous.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableValidRendezvous.setItems(sortedData);
         
         
         
         
         
    }    
  
    
}
