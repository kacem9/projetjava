/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reparateur;

import Entities.fos_user;
import Entities.rendezvous;
import Entities.validrendezvous;
import service.Rendezvous_service;
import service.ValidateRendezvous_service;
import service.fos_user_service;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ReponseRendezvousController implements Initializable {
    ValidateRendezvous_service acr = new ValidateRendezvous_service();
      ObservableList<String> oblistType = FXCollections.observableArrayList("10%","20%","30%","40%","50%","no promotion");
      ObservableList<String> oblistTypes = FXCollections.observableArrayList("At home","at etablissement");

    @FXML
    private AnchorPane anchorpane_center;
    @FXML
    private AnchorPane anchorpane_right;
    @FXML
    private TextArea id_txt;
    @FXML
    private ComboBox<String> combo_type;
    @FXML
    private TextArea price_txt;
    @FXML
    private TextArea email_txt;
    @FXML
    private TextArea message_txt;
    @FXML
    private ComboBox<String> combo_type1;
    @FXML
    private DatePicker date_txt;
    private rendezvous selectedrendezvous;
    @FXML
    private Button btn_back;
    @FXML
    private AnchorPane anchorpane_left;
    @FXML
    private Button btn_add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_type.setItems(oblistType);
        combo_type1.setItems(oblistTypes);
    }    

  

    @FXML
    private void btn_valid(ActionEvent event) throws ParseException {
        String promo =combo_type.getValue().toString();
              String etat =combo_type1.getValue().toString();

           String emailR = email_txt.getText().toString();
            String prix = price_txt.getText().toString();
           String message = message_txt.getText().toString();
          String user = id_txt.getText().toString();
    String dateheure = date_txt.getValue().toString();

        
         
         if (this.validateEmailAddress(emailR)==false){
          Alert alert = new Alert(Alert.AlertType.ERROR);
               
               alert.setHeaderText("erreur email ");
               alert.setContentText("erreur email");
               alert.showAndWait();
         
         }
         if(date_txt.getValue().isBefore(LocalDate.now()))
         {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Date shoul be after valid !!! ");

            alert.showAndWait();
                return;}

       if(emailR.equals("") || prix.equals("") ||message.equals(""))
          {
               Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("email cannot be empty !!! ");

            alert.showAndWait();
                return;
            }
         if (price_txt.getText().length()<0  )
           {
               Alert alert = new Alert(Alert.AlertType.ERROR);
               
               alert.setHeaderText(" Price ");
               alert.setContentText("should be positive and be number");
               alert.showAndWait();
           }
           

       validrendezvous s = new validrendezvous();



     s.setDateheure(dateheure);

     if(validateEmail() && validatePrice()){  
     s.setPrix(prix);

     s.setEmailR(emailR);
      }

           s.setPromo(promo);
            s.setEtat(etat);
            s.setMessage(message);

              s.setMessage(message);


     s.setUser(Integer.parseInt(id_txt.getText()));
  try {

                String obj=message_txt.getText();
               String price = price_txt.getText().toString();
              
               String date = date_txt.getValue().toString();
                
                String to = email_txt.getText();
                acr.sendMail(to,obj,price ,promo,date,etat);
            } catch (Exception ex) {
                Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            }
               Rendezvous_service cs = new Rendezvous_service() ;
                 cs.insertPStatements(s);

    }
    private boolean validatePrice(){
        Pattern p =Pattern.compile("[0-9]+");
        Matcher m=p.matcher(price_txt.getText());
        if(m.find()&& m.group().equals(price_txt.getText())){

        return true;

        }
        else{
        Alert alert =new Alert (AlertType.WARNING);
        alert.setTitle("Validate Price");
        alert.setHeaderText(null);
        alert.setContentText("Please Enter Valid Price");
        alert.showAndWait();
        return false;

        }
    }
            private boolean validateEmail(){
        Pattern p =Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m=p.matcher(email_txt.getText());
        if(m.find()&& m.group().equals(email_txt.getText())){

        return true;

        }
        else{
        Alert alert =new Alert (Alert.AlertType.WARNING);
        alert.setTitle("Validate Email");
        alert.setHeaderText(null);
        alert.setContentText("Please Enter Valid Email");
        alert.showAndWait();
        return false;

        }
    }
        public void sendEmail(ActionEvent event){
          /*  try {

                String obj=message_txt.getText();
               String price = price_txt.getText().toString();
               String promo=combo_type.getValue();
               String date = date_txt.getValue().toString();
                String etat = combo_type1.getValue().toString();
                String to = email_txt.getText();
                acr.sendMail(to,obj,price ,promo,date,etat);
            } catch (Exception ex) {
                Logger.getLogger(ValidateRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        }
  public void initData(rendezvous rendezvous) {
         selectedrendezvous = rendezvous;
          
         id_txt.setText(String.valueOf(selectedrendezvous.getCin()));
          id_txt.setText(selectedrendezvous.getUser()+""); 
 
            email_txt.setText(selectedrendezvous.getEmail());


    }
    public static boolean validateEmailAddress(String emailR) { 
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; 
        Pattern pattern = Pattern.compile(regex); 
        return pattern.matcher(emailR).matches(); }

    @FXML
    private void back(ActionEvent event) {

                Parent root;
                  try {
                                            root= FXMLLoader.load(getClass().getResource("ValidateRendezvous.fxml"));
                                            btn_back.getScene().setRoot(root);
                                        } catch (IOException ex) {
                                            Logger.getLogger(ReponseRendezvousController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                  
    }
}
