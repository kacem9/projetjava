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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javax.mail.MessagingException;
import service.userService;

/**
 * FXML Controller class
 *
 * @author firas
 */
public class ConfirmAccountFXMLController implements Initializable {

    @FXML
    private TextField tfemail;
    @FXML
    private TextField tftoken;
    @FXML
    private Label lbconf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ConfirmAccount(ActionEvent event) throws IOException, MessagingException {
        user u = new user();
        u.setEmail(tfemail.getText());
        userService us = new userService();
        if (us.Checkconfirmationtoken(tfemail.getText(), tftoken.getText())) {
            
            us.ConfirmAccount(u);
            lbconf.setTextFill(Paint.valueOf("#0000FF"));
            lbconf.setText("Compte validé");

            Parent home_page_parent = FXMLLoader.load(getClass().getResource("user.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide(); //optional
            app_stage.setScene(home_page_scene);
            app_stage.show();

        } else {
            lbconf.setTextFill(Paint.valueOf("RED"));
            lbconf.setText("Veullez vérifier vos données.");
        }
    }

}
