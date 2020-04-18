    package Reparateur;




   import connection.ConnexionBD;
    import service.ValidateRendezvous_service;

    import Entities.rendezvous;
    import Entities.validrendezvous;
import service.Rendezvous_service;
    import java.net.URL;
    import java.sql.Connection;
    import java.util.ResourceBundle;
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
    import java.io.IOException;
    import java.text.ParseException;
    import java.util.Date;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javafx.scene.control.DatePicker;
    import service.Validrendezvous_service;
    import service.ValidateRendezvous_service;
    import service.fos_user_service;
import java.sql.SQLException;
    import java.text.DateFormat;
    import java.text.SimpleDateFormat;
    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
import java.util.Optional;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
    import javafx.scene.control.Alert;
    import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
    import static jdk.nashorn.internal.objects.NativeFunction.function;
    import jdk.nashorn.internal.runtime.regexp.RegExp;

    public class ValidateRendezvousController implements Initializable {
           Connection c= ConnexionBD.getInstance().getCnx();
            ObservableList<String> list = FXCollections.observableArrayList();
                ObservableList<String> oblistType = FXCollections.observableArrayList("10%","20%","30%","40%","50%","no promotion");
                    ObservableList<String> oblistTypes = FXCollections.observableArrayList("At home","at etablissement");

    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
       ObservableList<rendezvous> oblist = FXCollections.observableArrayList();
        //Validrendezvous_service cr =new Validrendezvous_service();
        Validrendezvous_service t =new Validrendezvous_service();
        Rendezvous_service rv =new Rendezvous_service();
            ValidateRendezvous_service crc =new  ValidateRendezvous_service();
        rendezvous r=new rendezvous();
        fos_user_service acr = new fos_user_service();
        

    @FXML
        private TableView<rendezvous> tab_view;
    @FXML
        private TableColumn<rendezvous, String> clnm2;
    @FXML
        private TableColumn<rendezvous, String> clnm3;
    @FXML
        private TableColumn<rendezvous, String> clnm4;
    @FXML
        private TableColumn<rendezvous, String> clnm5;
    @FXML
        private TableColumn<rendezvous, String> clnm6;
    @FXML
        private TableColumn<rendezvous, String> clnm7;
    @FXML
        private TableColumn<rendezvous, Integer> clnm8;
        @FXML
        private Button btn_add;
        private TextArea id_txt;
        private TextArea email_txt;
        private TextArea adresse_txt;
        private TextArea num_txt;
        private TextArea prenom_txt;
        private TextArea nom_txt;
        private TextArea price_txt;
        private TextArea message_txt;
    @FXML
    private Button refuse;
    @FXML
    private Button delete;
    @FXML
    private Button btn_stat_View;
    @FXML
    private AnchorPane pane1;




        @Override
        public void initialize(URL url, ResourceBundle rb) {

               try {

               
                   Rendezvous_service rv =new Rendezvous_service();
                   this.tab_view.setEditable(true);
                  
                   rv.afficherRendezvous(oblist);
                   
                   tab_view.setItems(oblist);
                   setCellTable();
                   tab_view.setItems(oblist);
                  // oblist = cs.getAlls();
                   setCellTable();
                                 
                                                
               } catch (IOException ex) {
                   Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
               }

        }    

        @FXML
        private void btn_valid(ActionEvent event) throws ParseException, IOException {


     
               FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReponseRendezvous.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        //access the controller and call a method
        ReponseRendezvousController controller = loader.getController();
        controller.initData(tab_view.getSelectionModel().getSelectedItem());
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();



        }

        private void editRendezvous(ActionEvent event) {
              rendezvous selected = tab_view.getSelectionModel().getSelectedItem();
             id_txt.setText(String.valueOf(selected.getUser()));
              id_txt.setText(selected.getUser()+"");
        }


        private void setCellTable(){




            clnm2.setCellValueFactory(new PropertyValueFactory<>("nom"));
            clnm3.setCellValueFactory(new PropertyValueFactory<>("prenom"));

            clnm4.setCellValueFactory(new PropertyValueFactory<>("email"));
          clnm5.setCellValueFactory(new PropertyValueFactory<>("adresse"));
              clnm6.setCellValueFactory(new PropertyValueFactory<>("message"));

            clnm7.setCellValueFactory(new PropertyValueFactory<>("typepanne"));
          clnm8.setCellValueFactory(new PropertyValueFactory<>("numtel"));
             tab_view.setItems(oblist);
        }







    @FXML
    private void testEtat(MouseEvent event) {
    }

   
       @FXML
        public void refuse(ActionEvent event){
            try {
 rendezvous selected = tab_view.getSelectionModel().getSelectedItem();

         clnm4.setText(selected.getEmail()); 
         String to =  clnm4.getText();
           
                t.refuse(to);
            } catch (Exception ex) {
                Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

   @FXML
    public void delete(ActionEvent event) throws IOException{ 
            rendezvous selected = tab_view.getSelectionModel().getSelectedItem();
            if(selected==null)
            {
                Alert dialogW = new Alert(Alert.AlertType.WARNING);
                dialogW.setTitle("Fenêtre d'alerte !");
                dialogW.setContentText("selected appointement");
                dialogW.showAndWait();
            }else{   
                Alert dialogC = new Alert(AlertType.CONFIRMATION);
                dialogC.setTitle("Confirmation de la suppression");
                dialogC.setHeaderText(null);
                dialogC.setContentText("you have to delete appointement ?");
                Optional<ButtonType> answer = dialogC.showAndWait();
                if (answer.get() == ButtonType.OK) 
                {
                    int selectedCin = selected.getCin();
                    t.deleteRen(selectedCin);
                    tab_view.getItems().clear();
                   t.afficherRendezvous(oblist);
                    setCellTable();
                    tab_view.setItems(oblist); 
                    //System.out.println("User chose OK");
                }
                else {
                System.out.println("User chose Cancel or closed the dialog-box");
                }
            }
            
           
            //}
    }


           @FXML
    private void handleStat(ActionEvent event) throws IOException {
        Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("statistique.fxml"));
                                            btn_stat_View.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
                                        }

    }
    
    }