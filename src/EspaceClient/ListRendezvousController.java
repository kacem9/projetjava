
package EspaceClient;
import controllers.*;



import Utils.*;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import Entities.rendezvous;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.cell.PropertyValueFactory;

import service.Rendezvous_service;
import service.Validrendezvous_service;
import java.sql.SQLException;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.util.Callback;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;

public class ListRendezvousController implements Initializable  {
     ObservableList<rendezvous> oblist = FXCollections.observableArrayList();


  @FXML
    private Button btn_back;
    @FXML
    private TableView<rendezvous> tableRendezvous;
    @FXML
    private TableColumn<rendezvous, String> col_nom;
    @FXML
    private TableColumn<rendezvous, String> col_prenom;
    @FXML
    private TableColumn<rendezvous, String> col_email;
    @FXML
    private TableColumn<rendezvous, String> col_adresse;
     @FXML
    private TableColumn<rendezvous, String> col_message;
    @FXML
    private TableColumn<rendezvous, String> col_typepanne;
   
    
     
    private TableColumn<rendezvous, String> action;
    @FXML
    private TextField txt_arch;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
         try {
              Rendezvous_service cr = new Rendezvous_service ();
             cr.afficherRendezvous(oblist);
         } catch (IOException ex) {
             Logger.getLogger(ListRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
         }
        TableColumn checkbox= new TableColumn("remove");

       
        col_nom.setCellValueFactory(new PropertyValueFactory<rendezvous ,String>("nom"));
        col_prenom.setCellValueFactory(new PropertyValueFactory<rendezvous ,String>("prenom"));
         col_email.setCellValueFactory(new PropertyValueFactory<rendezvous ,String>("email"));
          col_adresse.setCellValueFactory(new PropertyValueFactory<rendezvous ,String>("adresse"));
          col_message.setCellValueFactory(new PropertyValueFactory<rendezvous ,String>("message"));
        col_typepanne.setCellValueFactory(new PropertyValueFactory<rendezvous ,String>("typepanne"));
        checkbox.setCellFactory(
        CheckBoxTableCell.forTableColumn(checkbox)
    );
    checkbox.setCellValueFactory(
            new PropertyValueFactory<>("selected")
    );
   
        action= new TableColumn("DELETE");
       
 javafx.util.Callback<TableColumn<rendezvous, String>, TableCell<rendezvous, String>> cellFactory
                = 
                new Callback<TableColumn<rendezvous, String>, TableCell<rendezvous, String>>() {
                    @Override
                    public TableCell call(final TableColumn<rendezvous, String> param) {
                        final TableCell<rendezvous, String> cell = new TableCell<rendezvous, String>() {

                            final Button btn = new Button("supprimer");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    
                                    btn.setOnAction(event-> {
                                        rendezvous cn = getTableView().getItems().get(getIndex());
                                        System.out.println(cn.getCin()
                                                );
                                        Rendezvous_service cs = new Rendezvous_service();
                                        cs.delete(cn);

                                        Parent root;
                                        try {
                                            
                                            root= FXMLLoader.load(getClass().getResource("ListRendezvous.fxml"));
                                             
        
        
                                            btn.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ListRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
               

              
        action.setCellFactory(cellFactory);
//      
        tableRendezvous.getColumns().add(action);
        editableCols1();
         editableCols2();
                  tableRendezvous.setItems(oblist);

   ////
   
   
   FilteredList<rendezvous> filteredData = new FilteredList<>(oblist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txt_arch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(rendezvous -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (rendezvous.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (rendezvous.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<rendezvous> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableRendezvous.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableRendezvous.setItems(sortedData);
        
    }

        
    private void editableCols1(){
            col_typepanne.setCellFactory(TextFieldTableCell.forTableColumn());
              

            col_typepanne.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setTypepanne(e.getNewValue());
               
                final int row =  tableRendezvous.getEditingCell().getRow();
                System.out.println("_______________"+row);
   
                  System.out.println("Type panne __________ "+tableRendezvous.getItems().get(row).getTypepanne());
                 
                  System.out.println("Cin __________ "+tableRendezvous.getItems().get(row).getCin());
                Rendezvous_service cs = new Rendezvous_service();
                cs.updateTypepanne(  tableRendezvous.getItems().get(row));
               
            });   
              
          tableRendezvous.setEditable(true);
           btn_back.setOnAction(event -> {
                Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("Client.fxml"));
                                            btn_back.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
    } 
    
    
            
    private void editableCols2(){
           
              col_message.setCellFactory(TextFieldTableCell.forTableColumn());

           col_message.setOnEditCommit(e->{
                e.getTableView().getItems().get(e.getTablePosition().getRow()).setMessage(e.getNewValue());
               
                final int row =  tableRendezvous.getEditingCell().getRow();
                System.out.println("_______________"+row);
   
                  System.out.println("Message __________ "+tableRendezvous.getItems().get(row).getMessage());
                 
                  System.out.println("Cin __________ "+tableRendezvous.getItems().get(row).getCin());
                Rendezvous_service cs = new Rendezvous_service();
                cs.updateMessage(  tableRendezvous.getItems().get(row));
               
            });   
              
          tableRendezvous.setEditable(true);
             btn_back.setOnAction(event -> {
                Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("Client.fxml"));
                                            btn_back.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
    }
       
    }
 
  
    

