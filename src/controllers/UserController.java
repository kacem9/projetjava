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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
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
    File selectedFile;
    ObservableList<String> Sexes = FXCollections.observableArrayList("Femme", "homme");
    ObservableList<String> Civilites = FXCollections.observableArrayList("Madame", "Monsieur", "Madamemoiselle");
    ObservableList<String> Roles = FXCollections.observableArrayList("Admin", "Vendeur", "Reparateur", "Achteur", "chef");
    @FXML
    private Hyperlink btnSignIn;
    private TextField tfcode;
    @FXML
    private Label testcode;
    @FXML
    private Label testemail1;
    @FXML
    private Label ville;
    @FXML
    private Label prenom;
    @FXML
    private Label nom;
    @FXML
    private Label cin;
    @FXML
    private Label testpassword;
    @FXML
    private Label poste;
    @FXML
    private Label numtel;
    @FXML
    private Label username;
    @FXML
    private Label pays;
    @FXML
    private Label code;
    @FXML
    private Label adresse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Sexe.setItems(Sexes);
        Civilite.setItems(Civilites);

        Role.setItems(Roles);

        // TODO
    }

    @FXML
    private void AjouterUserAction(ActionEvent event) throws Exception {
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
        srv.ajouterUser(username, username_canonical, email, email_canonical, password,Roles, Cine, nom, prenom, sexe, date_naissance, num_tel, adresse, poste, civilite, pays, ville, code_postal, selectedFile);

         Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Success!");
                alert.showAndWait();

    }

    private void genererAction(ActionEvent event) {
        userService fs = new userService();
        user u = new user();
        u.setEmail(Email.getText());
        if (testemail1.getText().equals("Email valide")) {
            fs.SendMailAndAddTokenToUser(u);
            testemail1.setTextFill(Paint.valueOf("#0000FF"));
            testemail1.setText("Mail envoyé");

        } else {
            testemail1.setTextFill(Paint.valueOf("RED"));
            testemail1.setText("Vérifiez le mail");
        }
    }

    private void verifMail(MouseEvent event) {
        userService fs = new userService();

        if (fs.CheckIfUserExist(Email.getText())) {
            testemail1.setTextFill(Paint.valueOf("#0000FF"));
            testemail1.setText("Email valide");
        } else {
            testemail1.setTextFill(Paint.valueOf("RED"));
            testemail1.setText("Email non existant");
        }

    }

    private void verfiCode(MouseEvent event) {
        userService fs = new userService();
        if (fs.Checkconfirmationtoken(Email.getText(), tfcode.getText())) {
            testcode.setTextFill(Paint.valueOf("#0000FF"));
            testcode.setText("Code valide");
        } else {
            testcode.setTextFill(Paint.valueOf("RED"));
            testcode.setText("Code non valide");

        }
    }

    @FXML
    private void choiceFileAction(ActionEvent event) {
    
        Fc.setTitle("Open fil Dialog");
        Fc.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        Fc.setInitialDirectory(new File("C:\\wamp64\\www"));
        selectedFile = Fc.showOpenDialog(null);
    
try {
               Image imge = new Image(selectedFile.toURI().toURL().toString());
               System.out.println(selectedFile.toURI().toURL().toString());
                this.img.setImage(imge);
           } catch (MalformedURLException ex) {
               Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
           }
      
    

    }

    /*
  public static boolean checkUsername(String username) {

        if (username.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b")) {
            return true;
        }

        return false;
    }
    private void verifUsername(MouseEvent event) {
        userService fs = new userService();
        if (checkUsername(Username.getText())) {
            if (fs.CheckIfUsernameExist(Username.getText())) {
                Username.setText("Pseudo existe déjà ");
            } else {
                Username.setText("Pseudo valide");

            }

        } else {
            Username.setText("Vérifiez le format de votre pseudo ");
        }

    }


    
public static boolean validPassword(String password) {
        if (password.length() > 7) {
            return true;

        }
        return false;
    }
  public static boolean checkPassword(String password) {
        boolean hasNum = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;
        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasNum = true;
            } else if (Character.isUpperCase(c)) {
                hasCap = true;
            } else if (Character.isLowerCase(c)) {
                hasLow = true;
            }
            if (hasCap && hasLow && hasNum) {
                return true;
            }
        }
        return false;
    }

    private void pw(MouseEvent event) {

        if (!validPassword(Password.getText())) {
      //     password.setTextFill(Paint.valueOf("RED"));
            Password.setText("Le mot de passe doit contenir plus que 7 caractères.");
        } else if (!checkPassword(Password.getText())) {
        //  password.setTextFill(Paint.valueOf("RED"));
            Password.setText("Le mot de passe doit contenir des lettres  en majuscule,\n en miniscule ainsi que des chiffres.");
        } else {
        // password.setTextFill(Paint.valueOf("#0000FF"));
            Password.setText("Mot de passe valide");
        }
    }
     public boolean checkMail(String a) {

        Boolean valide = false;
        int i, j, k;
        for (j = 1; j < a.length(); j++) {
            if (a.charAt(j) == '@') {
                if (j < a.length() - 4) {
                    for (k = j; k < a.length() - 2; k++) {
                        if (a.charAt(k) == '.') {
                            valide = true;
                        }
                    }
                }
            }
        }

        return valide;
    }

    private void verifMail(MouseEvent event) {
        userService fs = new userService();
        if (checkMail(Email.getText())) {
            if (fs.CheckIfUserExist(Email.getText())) {
              //  email.setTextFill(Paint.valueOf("RED"));
                Email.setText("Email utilisé déjà ");
            } else {
              // email.setTextFill(Paint.valueOf("#0000FF"));
                Email.setText("Email valide");
            }
        } else {
         //  email.setTextFill(Paint.valueOf("RED"));
            Email.setText("Vérifiez le format de votr
    @FXML
    private void handleButtonAction(ActionEvent event) {
    }
e adresse mail ");
        }

    }
     */
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("/GUI/Login.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @FXML
    private void verifAdresse(MouseEvent event) {
        userService fs = new userService();
        if (checkUsername(Adresse.getText())) {
            if (fs.CheckIfUsernameExist(Adresse.getText())) {
                adresse.setTextFill(Paint.valueOf("RED"));

                adresse.setText(" existe déjà ");
            } else {
                adresse.setTextFill(Paint.valueOf("#0000FF"));
                adresse.setText("Adresse valide");

            }

        } else if (Adresse.getText().isEmpty()) {
            adresse.setTextFill(Paint.valueOf("RED"));
            adresse.setText("empty ");
        } else {
            adresse.setTextFill(Paint.valueOf("RED"));
            adresse.setText("Vérifiez le format  ");
        }

    }

    @FXML
    private void verifVille(MouseEvent event) {
        userService fs = new userService();
        if (checkUsername(Ville.getText())) {
            if (fs.CheckIfUsernameExist(Ville.getText())) {
                ville.setTextFill(Paint.valueOf("RED"));

                ville.setText(" existe déjà ");
            } else {
                ville.setTextFill(Paint.valueOf("#0000FF"));
                ville.setText("Ville valide");

            }

        } else if (Ville.getText().isEmpty()) {
            ville.setTextFill(Paint.valueOf("RED"));
            ville.setText("empty ");
        } else {
            ville.setTextFill(Paint.valueOf("RED"));
            ville.setText("Vérifiez le format  ");
        }
    }

    @FXML
    private void verifPrenom(MouseEvent event) {
        userService fs = new userService();
        if (checkUsername(Prenom.getText())) {
            if (fs.CheckIfUsernameExist(Prenom.getText())) {
                prenom.setTextFill(Paint.valueOf("RED"));

                prenom.setText("Prenom existe déjà ");
            } else {
                prenom.setTextFill(Paint.valueOf("#0000FF"));
                prenom.setText("Prenom valide");

            }

        } else if (Prenom.getText().isEmpty()) {
            prenom.setTextFill(Paint.valueOf("RED"));
            prenom.setText("empty ");
        } else {
            prenom.setTextFill(Paint.valueOf("RED"));
            prenom.setText("Vérifiez le format de votre Prenom ");
        }
    }

    @FXML
    private void verifNom(MouseEvent event) {
           userService fs = new userService();
        if (checkUsername(Nom.getText())) {
            if (fs.CheckIfUsernameExist(Nom.getText())) {
                nom.setTextFill(Paint.valueOf("RED"));

                nom.setText("Nom existe déjà ");
            } else {
                nom.setTextFill(Paint.valueOf("#0000FF"));
                nom.setText("Nom valide");

            }

        } else if (Nom.getText().isEmpty()) {
            nom.setTextFill(Paint.valueOf("RED"));
            nom.setText("empty ");
        } else {
            nom.setTextFill(Paint.valueOf("RED"));
            nom.setText("Vérifiez le format de votre Nom ");
        }
    }

    @FXML
    private void verifCin(MouseEvent event) {
        userService fs = new userService();
        if (iscin(Cin.getText())) {

            cin.setTextFill(Paint.valueOf("#0000FF"));
            cin.setText("Cin valide");

        } else if (Cin.getText().isEmpty()) {
            cin.setTextFill(Paint.valueOf("RED"));
            cin.setText("empty ");
        } else {
            cin.setTextFill(Paint.valueOf("RED"));
            cin.setText("Vérifiez le format de votre cin ");
        }

    }
 public static boolean validPassword(String password) {
        if (password.length() > 7) {
            return true;

        }
        return false;
    }

    public static boolean checkPassword(String password) {
        boolean hasNum = false;
        boolean hasCap = false;
        boolean hasLow = false;
        char c;
        for (int i = 0; i < password.length(); i++) {
            c = password.charAt(i);
            if (Character.isDigit(c)) {
                hasNum = true;
            } else if (Character.isUpperCase(c)) {
                hasCap = true;
            } else if (Character.isLowerCase(c)) {
                hasLow = true;
            }
            if (hasCap && hasLow && hasNum) {
                return true;
            }
        }
        return false;
    }
    @FXML
    private void pw(MouseEvent event) {
        if (!validPassword(Password.getText())) {
            testpassword.setTextFill(Paint.valueOf("RED"));
            testpassword.setText("Le mot de passe doit contenir plus que 7 caractères.");
        } else if (!checkPassword(Password.getText())) {
            testpassword.setTextFill(Paint.valueOf("RED"));
            testpassword.setText("Le mot de passe doit contenir des lettres  en majuscule,\n en miniscule ainsi que des chiffres.");
        } else if (Password.getText().isEmpty()) {
            testpassword.setTextFill(Paint.valueOf("RED"));
            testpassword.setText("empty ");
        } else {
            testpassword.setTextFill(Paint.valueOf("#0000FF"));
            testpassword.setText("Mot de passe valide");
        }
    }

    @FXML
    private void verifPoste(MouseEvent event) {
        userService fs = new userService();
        if (checkUsername(Poste.getText())) {
            if (fs.CheckIfUsernameExist(Poste.getText())) {
                poste.setTextFill(Paint.valueOf("RED"));

                poste.setText("Poste existe déjà ");
            } else {
                poste.setTextFill(Paint.valueOf("#0000FF"));
                poste.setText("Poste valide");

            }

        } else if (Poste.getText().isEmpty()) {
            poste.setTextFill(Paint.valueOf("RED"));
            poste.setText("empty ");
        } else {
            poste.setTextFill(Paint.valueOf("RED"));
            poste.setText("Vérifiez le format de votre Poste ");
        }
    }

    @FXML
    private void veriftel(MouseEvent event) {
         userService fs = new userService();
        if (isTel(Num_tel.getText())) {

            numtel.setTextFill(Paint.valueOf("#0000FF"));
            numtel.setText("num valide");

        } else if (Num_tel.getText().isEmpty()) {
            numtel.setTextFill(Paint.valueOf("RED"));
            numtel.setText("empty ");
        } else {
            numtel.setTextFill(Paint.valueOf("RED"));
            numtel.setText("Vérifiez le format de votre num ");
        }

    }
public static boolean iscin(String Cin) {

        if (Cin.matches("^[0-9]+$") && Cin.length() == 8) {

            return true;

        } else {

            return false;

        }

    }

    public static boolean isTel(String Num_tel) {

        if (Num_tel.matches("^[0-9]+$") && Num_tel.length() == 8) {

            return true;

        } else {

            return false;

        }

    }

    public static boolean iscodepostale(String Code_postal) {

        if (Code_postal.matches("^[0-9]+$") && Code_postal.length() == 4) {

            return true;

        } else {

            return false;

        }

    }
    @FXML
    private void verifUsername(MouseEvent event) {
        
   
        userService fs = new userService();
        if (checkUsername(Username.getText())) {
            if (fs.CheckIfUsernameExist(Username.getText())) {
                username.setTextFill(Paint.valueOf("RED"));

                username.setText("Pseudo existe déjà ");
            } else {
                username.setTextFill(Paint.valueOf("#0000FF"));
                username.setText("Pseudo valide");

            }

        } else if (Username.getText().isEmpty()) {
            username.setTextFill(Paint.valueOf("RED"));
            username.setText("empty ");
        } else {
            username.setTextFill(Paint.valueOf("RED"));
            username.setText("Vérifiez le format de votre pseudo ");
        }

    }

 public static boolean checkUsername(String username) {

        if (username.matches("\\b[a-zA-Z][a-zA-Z0-9\\-._]{3,}\\b")) {
            return true;
        }
        return false;

    }
    @FXML
    private void verifPays(MouseEvent event) {
          userService fs = new userService();
        if (checkUsername(Pays.getText())) {
            if (fs.CheckIfUsernameExist(Pays.getText())) {
                pays.setTextFill(Paint.valueOf("RED"));

                pays.setText("Pays existe déjà ");
            } else {
                pays.setTextFill(Paint.valueOf("#0000FF"));
                pays.setText("Pays valide");

            }

        } else if (Pays.getText().isEmpty()) {
            pays.setTextFill(Paint.valueOf("RED"));
            pays.setText("empty ");
        } else {
            pays.setTextFill(Paint.valueOf("RED"));
            pays.setText("Vérifiez le format de votre Pays ");
        }
    }

    @FXML
    private void verifcodepostal(MouseEvent event) {
        userService fs = new userService();
        if (iscodepostale(Code_postal.getText())) {

            code.setTextFill(Paint.valueOf("#0000FF"));
            code.setText("code postal valide");

        } else if (Code_postal.getText().isEmpty()) {
            code.setTextFill(Paint.valueOf("RED"));
            code.setText("empty ");
        } else {
            code.setTextFill(Paint.valueOf("RED"));
            code.setText("Vérifiez le format de votre code postal ");
        }

    }
}
