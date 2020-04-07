/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.user;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.userService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class UserController implements Initializable {

    @FXML
    private TextField Cin;
    @FXML
    private TextField Nom;
    @FXML
    private TextField Prenom;
    @FXML
    private TextField Username;
    @FXML
    private TextField Ville;
    @FXML
    private Button btnSave;
    @FXML
    private Button Photo;
    @FXML
    private ChoiceBox<String> Role;
    @FXML
    private ChoiceBox<String> Civilite;
    @FXML
    private ChoiceBox<String> Sexe;
    @FXML
    private DatePicker Date_naissance;
 

    @FXML
    private PasswordField Password;
    @FXML
    private TextField Code_postal;
    @FXML
    private TextField Pays;
    @FXML
    private TextField Num_tel;
    @FXML
    private TextField Poste;
    @FXML
    private TextField Email;
    @FXML
    private TextField Adresse;
    @FXML
    private ImageView img;
     File file;
             FileChooser Fc = new FileChooser();
 File selectedFile ;
  @FXML
    private Hyperlink btnSignIn;
     ObservableList<String> Sexes=FXCollections.observableArrayList("Femme","homme");
     ObservableList<String> Civilites=FXCollections.observableArrayList("Madame","Monsieur","Madamemoisel");
     ObservableList<String> Roles=FXCollections.observableArrayList("Admin","Vendeur","Reparateur","Achteur");

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          Sexe.setItems(Sexes);
                    Civilite.setItems(Civilites);

                              Role.setItems(Roles);

    }    

    @FXML
    private void AjouterUserAction(ActionEvent event) {
     
            String Cine = Cin.getText();
            String nom = Nom.getText();
            String prenom = Prenom.getText();
            String username = Username.getText();
            String ville = Ville.getText();
            String code_postal = Code_postal.getText();
            String pays = Pays.getText();
            String num_tel = Num_tel.getText();
            String poste = Poste.getText();
            String email = Email.getText();
            String password = Password.getText();
            String date_naissance = Date_naissance.getValue().toString();
            String Roles = Role.getValue();
            String adresse = Adresse.getText();
            String sexe = Sexe.getValue();
            String civilite = Civilite.getValue();
            String username_canonical = Username.getText();
            String email_canonical = Email.getText();
        
            userService srv = new userService();
           srv.ajouterUser(username,username_canonical,email,email_canonical,password,Roles,Cine,nom,prenom,sexe,date_naissance,num_tel,adresse,poste,civilite,pays,ville,code_postal, selectedFile);
            
            System.out.println("success");
   
            
       
    
    }
    //fetches rows and data from the list
   




        @FXML
    private void choiceFileAction(ActionEvent event) throws IOException {

        
        Fc.setTitle("Open fil Dialog");
        Fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        Fc.setInitialDirectory(new File("C:\\wamp\\www\\Velo"));
        selectedFile = Fc.showOpenDialog(null);
    
try {
               Image imge = new Image(selectedFile.toURI().toURL().toString());
               System.out.println(selectedFile.toURI().toURL().toString());
                this.img.setImage(imge);
           } catch (MalformedURLException ex) {
               Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
           }
      
    
        
}

   

    


    

    
}
