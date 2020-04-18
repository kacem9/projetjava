/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.user;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button btnFB;
    @FXML
    private Button btnSignup;
    @FXML
    private Label lblErrors;
    @FXML
    private Hyperlink btnForgot;
    @FXML
    private ImageView image;
    @FXML
    private Hyperlink btnSignIn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(ActionEvent event) throws IOException {
        userService us = new userService();
        user u = new user();

        u.setUsername(txtUsername.getText());
        u.setPassword(txtPassword.getText());
        int attempt = 1;
        if (us.Authentification(u)) {

            /* if (txtUsername.getText().equals("admin")) {
                Parent home_page_parent = FXMLLoader.load(getClass().getResource("Dashbord.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            }*/
            Parent home_page_parent;

            if (us.checkRole(txtUsername.getText()).equals("Admin") && attempt < 4) {
                //SessionUser.getFirstInstance(SessionUser.getUser());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed!");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("/Admin/Admin.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();

            } else if (us.checkRole(txtUsername.getText()).equals("Reparateur") && attempt < 4) {
                //SessionUser.getFirstInstance(SessionUser.getUser());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed!");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("/Reparateur/ValidateRendezvous.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            } else if (us.checkRole(txtUsername.getText()).equals("Vendeur") && attempt < 4) {
                //SessionUser.getFirstInstance(SessionUser.getUser());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed!");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("/EspaceVendeur/Home.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            } else if (us.checkRole(txtUsername.getText()).equals("Achteur") && attempt < 4) {
                //SessionUser.getFirstInstance(SessionUser.getUser());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed!");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("/EspaceClient/Client.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            } else if (us.checkRole(txtUsername.getText()).equals("chef") && attempt < 4) {
                //SessionUser.getFirstInstance(SessionUser.getUser());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Accessed!");
                alert.showAndWait();
                home_page_parent = FXMLLoader.load(getClass().getResource("/View/Home.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
            } else if (attempt != 4) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Denied!" + attempt);
                alert.showAndWait();
                attempt--;

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("attempt exceed!" + attempt);
                alert.showAndWait();

                txtPassword.setDisable(true);
                txtUsername.setEditable(false);
            }

            attempt++;

        }

    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Parent home_page_parent;
        home_page_parent = FXMLLoader.load(getClass().getResource("user.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    /* @FXML
    private void mdpoublieAction(ActionEvent event) throws IOException, SQLException {
        userService us=new userService();
        String Username=txtUsername.getText();
        String Password=txtPassword.getText();
        FXMLLoader loader = new FXMLLoader(getClass()
                                        .getResource("mdpoublié.fxml"));
        Stage primaryStage=new Stage();
        Parent root = loader.load();
        Scene scene=new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        us.mdpoublier(Username,Password);
       
        MdpoubliéController hc = loader.getController();
        hc.setUsername(us.GetUsernameUser(Password));
    }*/
    @FXML
    private void mdpoublieAction(ActionEvent event) throws IOException, SQLException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("ResetFXML.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }
}
