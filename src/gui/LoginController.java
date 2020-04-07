/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.userService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button click;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
  

    @FXML
    private void click(ActionEvent event)throws IOException {

       userService us = new userService();
        user u = new user();

        u.setUsername(txtUsername.getText());
        u.setPassword(txtPassword.getText());

        if (us.Authentification(u)) {

           /* if (txtUsername.getText().equals("admin")) {

                Parent home_page_parent = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }*/
           if (us.checkRole(txtUsername.getText())) {
                    //SessionUser.getFirstInstance(SessionUser.getUser());
                    Parent home_page_parent = FXMLLoader.load(getClass().getResource("Administrator.fxml"));
                    Scene home_page_scene = new Scene(home_page_parent);
                    Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    app_stage.hide(); 
                    app_stage.setScene(home_page_scene);
                    app_stage.show();
            }
             else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Veuillez vérifier vos champs!");
            alert.showAndWait();
            System.out.println("no");
        }

    }
        
    }
}