/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;

import entities.Velo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ShowDetailsVeloController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private HBox sh;
    @FXML
    private HBox sh1;
    @FXML
    private HBox sh2;
 Velo v1=new Velo();
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
        
        
        
    }    
      public void setVelo(Velo v1) {
        this.v1 = v1;
        
    }

    void showVelo() {
        
        
 ImageView photo = new ImageView(new Image("http://localhost//Velo//web//images/" +v1.getPhoto()));
        photo.setFitHeight(300);
        photo.setFitWidth(354);
      //photo.setFitHeight(200);
        //photo.setFitWidth(454);
        
        nom.setAlignment(Pos.CENTER);
        Label description = new Label();
        Label localitsation_velo = new Label();
        Label date_circualtion = new Label();
        Label quantity = new Label();
                Label price_location = new Label();
                    Label category = new Label(); 

        //Label ph = new Label();
        Label ad = new Label();
     //   Label pr = new Label();
        Label pl = new Label();
        Text Des = new Text("Description: ");
        Text Localisation = new Text("Localisation: ");
        Text Price_location = new Text("price location ");
         Text cc = new Text("category: ");
        System.out.println(v1.getCategories_id());
                     
        Text Quantity = new Text("Quantity: ");
                description.setText(v1.getDescription());

                        price_location.setText(String.valueOf(v1.getPrice_location()));
        quantity.setText(String.valueOf(v1.getQuantity()));
                localitsation_velo.setText(v1.getLocalitsation_velo());
                    category.setText(v1.getCategories_id().toString());

                       sh1.getChildren().add(photo);
                               sh1.setSpacing(5);

  sh1.getChildren().addAll(Des,description);
                          sh1.getChildren().addAll(Localisation,localitsation_velo);
                               sh1.getChildren().addAll(Quantity,quantity);
                               sh1.getChildren().addAll(Price_location,price_location);
 sh1.getChildren().addAll(cc,category);
  Des.setFill(Color.DARKORANGE);
         Quantity.setFill(Color.DARKORANGE);cc.setFill(Color.DARKORANGE);
              Localisation.setFill(Color.DARKORANGE);cc.setFill(Color.DARKORANGE);
            Price_location.setFill(Color.DARKORANGE);
     sh1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70");
    
    sh1.setAlignment(Pos.CENTER);
    }
}
