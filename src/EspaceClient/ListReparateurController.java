
package EspaceClient;
import controllers.*;


import Utils.*;


import Entities.fos_user;
import Entities.rendezvous;
import Entities.validrendezvous;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.DefaultProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import service.Validrendezvous_service;
import service.fos_user_service;
import Entities.rendezvous;
import connection.ConnexionBD;
import java.io.IOException;
import java.text.ParseException;
import javafx.scene.control.Alert;
import service.Rendezvous_service;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ListReparateurController implements Initializable {
       ObservableList<String> list = FXCollections.observableArrayList();
    ObservableList<fos_user> oblist = FXCollections.observableArrayList();
    ObservableList<String> oblistType = FXCollections.observableArrayList("DIRECTION ET ROUES","FREINS","SUSPENSION");
    Connection c= ConnexionBD.getInstance().getCnx();
    fos_user_service cr = new  fos_user_service();
     Rendezvous_service br = new  Rendezvous_service();
      fos_user r = new   fos_user();
       rendezvous t = new   rendezvous();

    @FXML
    private AnchorPane anchorpane_parent;
    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_left;
    @FXML
    private TableView<fos_user> tab_view;
   
    @FXML
    private TableColumn<fos_user, String> clnm1;
    @FXML
    private TableColumn<fos_user, String> clnm2;
    @FXML
    private TableColumn<fos_user, String> clnm3;
    @FXML
    private TableColumn<fos_user, String> clnm4;
   
  
 
   
    @FXML
    private Pane pane_top;
    @FXML
    private Label lael_txt;
    private TextArea id_txt;
    @FXML
    private Button btn_back;
    @FXML
    private Button btn_add;
    @FXML
    private TextField txt_arch;

    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb  ) {
     
           try {
               fos_user_service cs = new fos_user_service ();
              // oblist = cs.getAll();
               this.tab_view.setEditable(true);
               cr.afficherReparateur(oblist);
               setCellTable();
               tab_view.setItems(oblist);
               
        
               
               
             
              // oblist = cs.getAll();
               
               
               
               
               setCellTable();
           } catch (IOException ex) {
               Logger.getLogger(ListReparateurController.class.getName()).log(Level.SEVERE, null, ex);
           }
           
           ////
           FilteredList<fos_user> filteredData = new FilteredList<>(oblist, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		txt_arch.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(fos_user -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (fos_user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (fos_user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<fos_user> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tab_view.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tab_view.setItems(sortedData);
     
 
	}
      private void setCellTable(){
        
    
       
        clnm1.setCellValueFactory(new PropertyValueFactory<>("email"));
        clnm2.setCellValueFactory(new PropertyValueFactory<>("Prenom"));
        clnm3.setCellValueFactory(new PropertyValueFactory<>("Num_tel"));
           
        clnm4.setCellValueFactory(new PropertyValueFactory<>("Adresse"));
   
         tab_view.setItems(oblist);
    }
  
 

   

    
    @FXML
    private void btn_addRendezvous(ActionEvent event)throws ParseException, IOException {
    
    
      /* String typepanne =combo_type.getValue().toString();
  
       String email = email_txt.getText().toString();
        String adresse = adresse_txt.getText().toString();
       String nom = nom_txt.getText().toString();
        String prenom = prenom_txt.getText().toString();
         String message = message_txt.getText().toString();
         String user = id_txt.getText().toString();
          String numtel = num_txt.getText().toString();
    
    rendezvous s = new rendezvous();
     s.setEmail(email);
       s.setAdresse(adresse);
        s.setTypepanne(typepanne);
        
        
         s.setNom(nom);
       s.setPrenom(prenom);
       
       s.setMessage(message);
          s.setNumtel(Integer.parseInt(num_txt.getText()));
           s.setUser(Integer.parseInt(id_txt.getText()));
      
           Rendezvous_service cs = new Rendezvous_service() ;
              cs.insertPStatement(s);
    */
          
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddRendezvous.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        AddRendezousController controller = loader.getController();
        controller.initData(tab_view.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    
    
    
    }
    
    
    
    
    

    private void editRendezvous(ActionEvent event) {
          fos_user selected = tab_view.getSelectionModel().getSelectedItem();
         id_txt.setText(String.valueOf(selected.getId()));
          id_txt.setText(selected.getId()+""); 
           
    
        
    }

    @FXML
    private void testEtat(MouseEvent event) {
    }

    @FXML
    private void back() {
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
      


