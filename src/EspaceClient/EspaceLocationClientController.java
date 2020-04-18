/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EspaceClient;

import entities.Velo;
import service.CategoriesService;
import service.PanierServices;
import service.VeloService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import connection.ConnectionDB;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.in;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EspaceLocationClientController implements Initializable {
 VeloService vs=new VeloService();
 @FXML
    private Connection connexion;
  //@FXML
    private Label quantity;
  //  String URLimg;
  //  @FXML
      //      ImageView photo=new ImageView();


    @FXML
    private VBox vbox1;
Velo v=new Velo();
        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   connexion = ConnectionDB.getinstance().getCnx();

    VeloService sousCatService= new VeloService();
        ArrayList<Velo> lesSousCategories = new ArrayList<>();
        PanierServices      pas = new PanierServices();
         try {
            
            lesSousCategories = (ArrayList<Velo>) sousCatService.readAll();
            System.out.println(lesSousCategories);
            for(int i=0;i<lesSousCategories.size();i++){
                
                CategoriesService catService = new CategoriesService();
                lesSousCategories.get(i).setCategories_id(catService.getCategorieById(
                        lesSousCategories.get(i).getCategories_id().getId()));
                System.out.println(lesSousCategories);
            }
            
    

        for (Velo v1 : lesSousCategories) {
   ImageView photo = new ImageView(new Image("http://localhost/Velo/web/images//"+ v1.getPhoto()));
     Button b1=new Button();
 Label nom = new Label();
            Label quantity = new Label();
            //Label description = new Label();
          //  Label price_location = new Label(); 
              Label category = new Label(); 
              Text add = new Text("quantity: ");
           // Text ty = new Text("description: ");
            //Text dd = new Text("price_location: ");
             Text cc = new Text("category: ");
              //description.setText(v1.getDescription());
            quantity.setText(String.valueOf(v1.getQuantity()));
                //        price_location.setText(String.valueOf(v1.getPrice_location()));
                        System.out.println(v1.getCategories_id());
                         category.setText(v1.getCategories_id().toString());
add.setFill(Color.DARKORANGE);
         
            cc.setFill(Color.DARKORANGE);
            add.setFill(Color.DARKORANGE);
          //zidto lilhbox
            HBox h1 = new HBox();
            HBox btn = new HBox();
 
             h1.setSpacing(10);
            photo.setFitHeight(200);
           photo.setFitWidth(400);
   h1.getChildren().add(photo);
     //  h1.getChildren().add(ty);
       h1.spacingProperty();
            //h1.getChildren().add(description);
           h1.getChildren().add(add);
            h1.getChildren().add(quantity);
                // h1.getChildren().add(dd);
           // h1.getChildren().add(price_location);
              h1.getChildren().add(cc);
            h1.getChildren().add(category);
                  vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                    + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:white;"
                    + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70");
    vbox1.setSpacing(20);
    vbox1.setAlignment(Pos.CENTER);
        
          

    
            Button bt2 = new Button("Détails"); 
               

                  btn.getChildren().add(bt2);

              //zidt hbox lilvbox
              vbox1.getChildren().add(h1);
                  vbox1.getChildren().add(btn);

          
 bt2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                                    Parent homePage;
                    try {
                         FXMLLoader pane = new FXMLLoader
                        (getClass()
                         .getResource("ShowDetailsVelo.fxml"));
                        
                   Stage primaryStage=new Stage();
                Parent root = pane.load();
                Scene scene=new Scene(root);
                primaryStage.setScene(scene);
                primaryStage.show();
                        ShowDetailsVeloController shev = pane.getController();
                        shev.setVelo(v1);
                        shev.showVelo();
                       
                    } catch (IOException ex) {
                        Logger.getLogger(EspaceLocationClientController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

      
      
            
   

        }
        
}    catch (SQLException ex) { 
         Logger.getLogger(EspaceLocationClientController.class.getName()).log(Level.SEVERE, null, ex);
     }
    }  @FXML
    private void PageCart(ActionEvent event) {
        try {   
       AnchorPane anchorPane   = FXMLLoader.load(getClass().getResource("Shop.fxml"));

Stage stage = new Stage();
stage.setScene(new Scene(anchorPane));
stage.show();
    } catch(Exception e)
    {
     System.out.println("eer");
}
    }  
}
    

         
            